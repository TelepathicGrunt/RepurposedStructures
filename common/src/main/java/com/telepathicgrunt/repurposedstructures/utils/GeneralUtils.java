package com.telepathicgrunt.repurposedstructures.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixins.structures.JigsawJunctionAccessor;
import com.telepathicgrunt.repurposedstructures.mixins.world.StructureCheckAccessor;
import com.telepathicgrunt.repurposedstructures.mixins.world.StructureManagerAccessor;
import it.unimi.dsi.fastutil.longs.LongSet;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.FrontAndTop;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.SectionPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.JigsawBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.ChunkGeneratorStructureState;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureCheck;
import net.minecraft.world.level.levelgen.structure.StructureCheckResult;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.placement.ConcentricRingsStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.storage.loot.LootTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class GeneralUtils {
    private GeneralUtils() {}

    // Weighted Random from: https://stackoverflow.com/a/6737362
    public static <T> T getRandomEntry(List<Pair<T, Integer>> rlList, RandomSource random) {
        double totalWeight = 0.0;

        // Compute the total weight of all items together.
        for (Pair<T, Integer> pair : rlList) {
            totalWeight += pair.getSecond();
        }

        // Now choose a random item.
        int index = 0;
        for (double randomWeightPicked = random.nextFloat() * totalWeight; index < rlList.size() - 1; ++index) {
            randomWeightPicked -= rlList.get(index).getSecond();
            if (randomWeightPicked <= 0.0) break;
        }

        return rlList.get(index).getFirst();
    }

    //////////////////////////////

    private static final Map<BlockState, Boolean> IS_FULLCUBE_MAP = new ConcurrentHashMap<>();

    public static boolean isFullCube(BlockGetter world, BlockPos pos, BlockState state) {
        if(state == null) return false;
        return IS_FULLCUBE_MAP.computeIfAbsent(state, (stateIn) -> Block.isShapeFullBlock(stateIn.getOcclusionShape(world, pos)));
    }

    //////////////////////////////

    // Helper method to make chests always face away from walls
    public static BlockState orientateChest(ServerLevelAccessor blockView, BlockPos blockPos, BlockState blockState) {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        Direction wallDirection = blockState.getValue(HorizontalDirectionalBlock.FACING);

        for(Direction facing : Direction.Plane.HORIZONTAL) {
            mutable.set(blockPos).move(facing);

            // Checks if wall is in this side
            if (isFullCube(blockView, mutable, blockView.getBlockState(mutable))) {
                wallDirection = facing;

                // Exit early if facing open space opposite of wall
                mutable.move(facing.getOpposite(), 2);
                if(!blockView.getBlockState(mutable).isSolid()) {
                    break;
                }
            }
        }

        // Make chest face away from wall
        return blockState.setValue(HorizontalDirectionalBlock.FACING, wallDirection.getOpposite());
    }

    //////////////////////////////////////////////

    public static ItemStack enchantRandomly(RegistryAccess registryAccess, RandomSource random, ItemStack itemToEnchant, float chance) {
        if(random.nextFloat() < chance) {
            List<Holder.Reference<Enchantment>> list = registryAccess.registryOrThrow(Registries.ENCHANTMENT).holders()
                    .filter(holder -> holder.value().canEnchant(itemToEnchant)).toList();
            if(!list.isEmpty()) {
                Holder.Reference<Enchantment> enchantment = list.get(random.nextInt(list.size()));
                // bias towards weaker enchantments
                int enchantmentLevel = random.nextInt(Mth.nextInt(random, enchantment.value().getMinLevel(), enchantment.value().getMaxLevel()) + 1);
                itemToEnchant.enchant(enchantment, enchantmentLevel);
            }
        }

        return itemToEnchant;
    }

    //////////////////////////////////////////////

    public static int getMaxTerrainLimit(ChunkGenerator chunkGenerator) {
        return chunkGenerator.getMinY() + chunkGenerator.getGenDepth();
    }

    //////////////////////////////

    public static BlockPos getHighestLand(ChunkGenerator chunkGenerator, RandomState randomState, BoundingBox boundingBox, LevelHeightAccessor heightLimitView, boolean canBeOnLiquid) {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(boundingBox.getCenter().getX(), getMaxTerrainLimit(chunkGenerator) - 40, boundingBox.getCenter().getZ());
        NoiseColumn blockView = chunkGenerator.getBaseColumn(mutable.getX(), mutable.getZ(), heightLimitView, randomState);
        BlockState currentBlockstate;
        while (mutable.getY() > chunkGenerator.getSeaLevel()) {
            currentBlockstate = blockView.getBlock(mutable.getY());
            if (!currentBlockstate.canOcclude()) {
                mutable.move(Direction.DOWN);
                continue;
            }
            else if (blockView.getBlock(mutable.getY() + 3).isAir() && (canBeOnLiquid ? !currentBlockstate.isAir() : currentBlockstate.canOcclude())) {
                return mutable;
            }
            mutable.move(Direction.DOWN);
        }

        return mutable;
    }


    public static BlockPos getLowestLand(ChunkGenerator chunkGenerator, RandomState randomState, BoundingBox boundingBox, LevelHeightAccessor heightLimitView, boolean canBeOnLiquid) {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(boundingBox.getCenter().getX(), chunkGenerator.getSeaLevel() + 1, boundingBox.getCenter().getZ());
        NoiseColumn blockView = chunkGenerator.getBaseColumn(mutable.getX(), mutable.getZ(), heightLimitView, randomState);
        BlockState currentBlockstate = blockView.getBlock(mutable.getY());
        while (mutable.getY() <= getMaxTerrainLimit(chunkGenerator) - 40) {

            if((canBeOnLiquid ? !currentBlockstate.isAir() : currentBlockstate.canOcclude()) &&
                    blockView.getBlock(mutable.getY() + 1).isAir() &&
                    blockView.getBlock(mutable.getY() + 5).isAir())
            {
                mutable.move(Direction.UP);
                return mutable;
            }

            mutable.move(Direction.UP);
            currentBlockstate = blockView.getBlock(mutable.getY());
        }

        return mutable.set(mutable.getX(), chunkGenerator.getSeaLevel(), mutable.getZ());
    }

    //////////////////////////////////////////////

    public static int getFirstLandYFromPos(LevelReader worldView, BlockPos pos) {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        mutable.set(pos);
        ChunkAccess currentChunk = worldView.getChunk(mutable);
        BlockState currentState = currentChunk.getBlockState(mutable);

        while(mutable.getY() >= worldView.getMinBuildHeight() && isReplaceableByStructures(currentState)) {
            mutable.move(Direction.DOWN);
            currentState = currentChunk.getBlockState(mutable);
        }

        return mutable.getY();
    }

    private static boolean isReplaceableByStructures(BlockState blockState) {
        return blockState.isAir() || !blockState.getFluidState().isEmpty() || blockState.is(BlockTags.REPLACEABLE_BY_TREES);
    }

    //////////////////////////////////////////////

    public static void centerAllPieces(BlockPos targetPos, List<? extends StructurePiece> pieces) {
        if(pieces.isEmpty()) return;

        Vec3i structureCenter = pieces.get(0).getBoundingBox().getCenter();
        int xOffset = targetPos.getX() - structureCenter.getX();
        int zOffset = targetPos.getZ() - structureCenter.getZ();

        for(StructurePiece structurePiece : pieces) {
            structurePiece.move(xOffset, 0, zOffset);
        }
    }

    //////////////////////////////////////////////

    public static void movePieceProperly(StructurePiece piece, int x, int y, int z) {
        piece.move(x, y, z);
        if (piece instanceof PoolElementStructurePiece poolElementStructurePiece) {
            poolElementStructurePiece.getJunctions().forEach(junction -> {
                ((JigsawJunctionAccessor)junction).setSourceX(junction.getSourceX() + x);
                ((JigsawJunctionAccessor)junction).setSourceX(junction.getSourceGroundY() + y);
                ((JigsawJunctionAccessor)junction).setSourceX(junction.getSourceZ() + z);
            });
        }
    }

    //////////////////////////////////////////////

    // More optimized with checking if the jigsaw blocks can connect
    public static boolean canJigsawsAttach(StructureTemplate.StructureBlockInfo jigsaw1, StructureTemplate.StructureBlockInfo jigsaw2) {
        FrontAndTop prop1 = jigsaw1.state().getValue(JigsawBlock.ORIENTATION);
        FrontAndTop prop2 = jigsaw2.state().getValue(JigsawBlock.ORIENTATION);

        return prop1.front() == prop2.front().getOpposite() &&
                (prop1.top() == prop2.top() || isRollableJoint(jigsaw1, prop1)) &&
                getStringMicroOptimised(jigsaw1.nbt(), "target").equals(getStringMicroOptimised(jigsaw2.nbt(), "name"));
    }

    private static boolean isRollableJoint(StructureTemplate.StructureBlockInfo jigsaw1, FrontAndTop prop1) {
        String joint = getStringMicroOptimised(jigsaw1.nbt(), "joint");
        if(!joint.equals("rollable") && !joint.equals("aligned")) {
            return !prop1.front().getAxis().isHorizontal();
        }
        else {
            return joint.equals("rollable");
        }
    }

    public static String getStringMicroOptimised(CompoundTag tag, String key) {
        return tag.get(key) instanceof StringTag stringTag ? stringTag.getAsString() : "";
    }

    //////////////////////////////////////////////

    /**
     * Will grab JSON objects that is specified by the dataType parameter.
     */
    public static Map<ResourceLocation, JsonElement> getDatapacksJSONElement(ResourceManager resourceManager, Gson gson, String dataType, int fileSuffixLength) {
        Map<ResourceLocation, JsonElement> map = new HashMap<>();
        int dataTypeLength = dataType.length() + 1;

        // Finds all JSON files paths within the rs_pool_additions folder. NOTE: this is just the path rn. Not the actual files yet.
        for (Map.Entry<ResourceLocation, Resource> resourceStackEntry : resourceManager.listResources(dataType, (fileString) -> true).entrySet()) {
            String identifierPath = resourceStackEntry.getKey().getPath();
            ResourceLocation fileID = ResourceLocation.fromNamespaceAndPath(
                    resourceStackEntry.getKey().getNamespace(),
                    identifierPath.substring(dataTypeLength, identifierPath.length() - fileSuffixLength));

            try {
                InputStream fileStream = resourceStackEntry.getValue().open();
                try (Reader bufferedReader = new BufferedReader(new InputStreamReader(fileStream, StandardCharsets.UTF_8))) {

                    // Get the JSON from the file
                    JsonElement countsJSONElement = GsonHelper.fromJson(gson, bufferedReader, (Class<? extends JsonElement>) JsonElement.class);

                    // Add the parsed json to the list we will merge later on
                    map.put(fileID, countsJSONElement);
                }
            }
            catch (IllegalArgumentException | IOException | JsonParseException exception) {
                RepurposedStructures.LOGGER.error(
                        "(Repurposed Structures {} MERGER) Couldn't parse data file {} from {}",
                        dataType,
                        fileID,
                        resourceStackEntry,
                        exception);
            }
        }

        return map;
    }

    ////////////////////////////

    public static boolean isInvalidLootTableFound(MinecraftServer minecraftServer, Map.Entry<ResourceLocation, ResourceLocation> entry) {
        boolean invalidLootTableFound = false;
        Registry<LootTable> lootTableRegistry = minecraftServer.reloadableRegistries().get().registryOrThrow(Registries.LOOT_TABLE);
        if(lootTableRegistry.get(entry.getKey()) == LootTable.EMPTY || lootTableRegistry.get(entry.getKey()) == null) {
            RepurposedStructures.LOGGER.error("Unable to find loot table key: {}", entry.getKey());
            invalidLootTableFound = true;
        }
        if(lootTableRegistry.get(entry.getValue()) == LootTable.EMPTY || lootTableRegistry.get(entry.getValue()) == null) {
            RepurposedStructures.LOGGER.error("Unable to find loot table value: {}", entry.getValue());
            invalidLootTableFound = true;
        }
        return invalidLootTableFound;
    }

    public static boolean isMissingLootImporting(MinecraftServer minecraftServer, Set<ResourceLocation> tableKeys) {
        AtomicBoolean invalidLootTableFound = new AtomicBoolean(false);
        Registry<LootTable> lootTableRegistry = minecraftServer.reloadableRegistries().get().registryOrThrow(Registries.LOOT_TABLE);
        lootTableRegistry.keySet().forEach(rl -> {
            if(rl.getNamespace().equals(RepurposedStructures.MODID) && !tableKeys.contains(rl)) {
                if(rl.getPath().contains("mansions") && rl.getPath().contains("storage")) {
                    return;
                }

                if(rl.getPath().contains("monuments")) {
                    return;
                }

                if(rl.getPath().contains("dispensers/temples/wasteland_lava")) {
                    return;
                }

                if(rl.getPath().contains("lucky_pool")) {
                    return;
                }

                if(rl.getPath().contains("archaeology")) {
                    return;
                }

                RepurposedStructures.LOGGER.error("No loot importing found for: {}", rl);
                invalidLootTableFound.set(true);
            }
        });
        return invalidLootTableFound.get();
    }

    ///////////////////////////////////////////

    public static boolean nameMatch(String biomeName, String... targetMatch) {
        return Arrays.stream(targetMatch).anyMatch(biomeName::contains);
    }

    public static boolean nameExactMatch(String biomeName, String... targetMatch) {
        return Arrays.asList(targetMatch).contains(biomeName);
    }

    //////////////////////////////////////////

    public static BlockState copyBlockProperties(BlockState oldBlockState, BlockState newBlockState) {
        for (Property<?> property : oldBlockState.getProperties()) {
            if (newBlockState.hasProperty(property)) {
                newBlockState = getStateWithProperty(newBlockState, oldBlockState, property);
            }
        }
        return newBlockState;
    }

    public static <T extends Comparable<T>> BlockState getStateWithProperty(BlockState state, BlockState stateToCopy, Property<T> property) {
        return state.setValue(property, stateToCopy.getValue(property));
    }

    ///////////////////////////////////////////

    public static List<StructureStart> inboundsValidStartsForAllStructure(WorldGenRegion level, BlockPos position, Predicate<Structure> structureMatch) {
        StructureManager structureManager = level.getLevel().structureManager();
        SectionPos sectionPos = SectionPos.of(position);

        ChunkAccess chunkAccess = level.getChunk(sectionPos.x(), sectionPos.z(), ChunkStatus.STRUCTURE_REFERENCES);
        if (!chunkAccess.getHighestGeneratedStatus().isOrAfter(ChunkStatus.STRUCTURE_REFERENCES)) {
            return new ArrayList<>();
        }

        Map<Structure, LongSet> references = chunkAccess.getAllReferences();
        List<StructureStart> list = new ArrayList<>();
        for (Map.Entry<Structure, LongSet> entry : references.entrySet()) {
            if (structureMatch.test(entry.getKey())) {
                fillStartsForStructure(level, structureManager, entry.getKey(), entry.getValue(), position, list::add);
            }
        }
        return list;
    }

    public static void fillStartsForStructure(LevelReader level, StructureManager structureManager, Structure structure, LongSet references, BlockPos position, Consumer<StructureStart> consumer) {
        for (long ref : references) {
            SectionPos sectionPos = SectionPos.of(new ChunkPos(ref), level.getMinSection());
            if (!level.hasChunk(sectionPos.x(), sectionPos.z())) {
                continue;
            }
            StructureStart structureStart = structureManager.getStartForStructure(sectionPos, structure, level.getChunk(sectionPos.x(), sectionPos.z(), ChunkStatus.STRUCTURE_STARTS));
            if (structureStart != null && structureStart.isValid() && structureStart.getBoundingBox().isInside(position)) {
                consumer.accept(structureStart);
            }
        }
    }

    //////////////////////////////////////////////////////////////////////////////////

    private static final ConcurrentHashMap<HeightKey, Integer> CACHED_HEIGHT = new ConcurrentHashMap<>(2048);
    private record HeightKey(ChunkGenerator chunkGenerator, int x, int z){}

    public static int getCachedFreeHeight(ChunkGenerator chunkGenerator, int x, int z, Heightmap.Types types, LevelHeightAccessor levelHeightAccessor, RandomState randomState) {
        HeightKey key = new HeightKey(chunkGenerator, x , z);
        Integer y = CACHED_HEIGHT.get(key);

        if (y == null) {
            if (CACHED_HEIGHT.size() >= 2048) {
                CACHED_HEIGHT.clear();
            }
            y = chunkGenerator.getFirstFreeHeight(x, z, types, levelHeightAccessor, randomState);
            CACHED_HEIGHT.put(key, y);
        }

        return y;
    }
    /////////////////////////////////////////////////////////////////////////////////

    // Below copied from ChunkGenerator as I needed to make a new StructureCheck instance so that the structure search is threadsafe
    // So much freaking duplicate code

    public static Pair<BlockPos, Holder<Structure>> findNearestMapStructureAsyncSafe(
            ServerLevel level, HolderSet<Structure> structure, BlockPos pos, int searchRadius, boolean skipKnownStructures
    ) {
        ChunkGeneratorStructureState chunkgeneratorstructurestate = level.getChunkSource().getGeneratorState();
        Map<StructurePlacement, Set<Holder<Structure>>> map = new Object2ObjectArrayMap<>();

        for (Holder<Structure> holder : structure) {
            for (StructurePlacement structureplacement : chunkgeneratorstructurestate.getPlacementsForStructure(holder)) {
                map.computeIfAbsent(structureplacement, p_223127_ -> new ObjectArraySet<>()).add(holder);
            }
        }

        if (map.isEmpty()) {
            return null;
        } else {
            Pair<BlockPos, Holder<Structure>> pair2 = null;
            double d2 = Double.MAX_VALUE;
            StructureManager structuremanager = level.structureManager();
            List<Map.Entry<StructurePlacement, Set<Holder<Structure>>>> list = new ArrayList<>(map.size());

            for (Map.Entry<StructurePlacement, Set<Holder<Structure>>> entry : map.entrySet()) {
                StructurePlacement structureplacement1 = entry.getKey();
                if (structureplacement1 instanceof ConcentricRingsStructurePlacement concentricringsstructureplacement) {
                    Pair<BlockPos, Holder<Structure>> pair = getNearestGeneratedStructureAsyncSafe(
                            entry.getValue(), level, structuremanager, pos, skipKnownStructures, concentricringsstructureplacement
                    );
                    if (pair != null) {
                        BlockPos blockpos = pair.getFirst();
                        double d0 = pos.distSqr(blockpos);
                        if (d0 < d2) {
                            d2 = d0;
                            pair2 = pair;
                        }
                    }
                } else if (structureplacement1 instanceof RandomSpreadStructurePlacement) {
                    list.add(entry);
                }
            }

            if (!list.isEmpty()) {
                int i = SectionPos.blockToSectionCoord(pos.getX());
                int j = SectionPos.blockToSectionCoord(pos.getZ());

                for (int k = 0; k <= searchRadius; k++) {
                    boolean flag = false;

                    for (Map.Entry<StructurePlacement, Set<Holder<Structure>>> entry1 : list) {
                        RandomSpreadStructurePlacement randomspreadstructureplacement = (RandomSpreadStructurePlacement)entry1.getKey();
                        Pair<BlockPos, Holder<Structure>> pair1 = getNearestGeneratedStructureAsyncSafe(
                                entry1.getValue(),
                                level,
                                structuremanager,
                                i,
                                j,
                                k,
                                skipKnownStructures,
                                chunkgeneratorstructurestate.getLevelSeed(),
                                randomspreadstructureplacement
                        );
                        if (pair1 != null) {
                            flag = true;
                            double d1 = pos.distSqr(pair1.getFirst());
                            if (d1 < d2) {
                                d2 = d1;
                                pair2 = pair1;
                            }
                        }
                    }

                    if (flag) {
                        return pair2;
                    }
                }
            }

            return pair2;
        }
    }

    private static Pair<BlockPos, Holder<Structure>> getNearestGeneratedStructureAsyncSafe(
            Set<Holder<Structure>> structureHoldersSet,
            ServerLevel level,
            StructureManager structureManager,
            BlockPos pos,
            boolean skipKnownStructures,
            ConcentricRingsStructurePlacement placement
    ) {
        List<ChunkPos> list = level.getChunkSource().getGeneratorState().getRingPositionsFor(placement);
        if (list == null) {
            throw new IllegalStateException("Somehow tried to find structures for a placement that doesn't exist");
        } else {
            Pair<BlockPos, Holder<Structure>> pair = null;
            double d0 = Double.MAX_VALUE;
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for (ChunkPos chunkpos : list) {
                blockpos$mutableblockpos.set(SectionPos.sectionToBlockCoord(chunkpos.x, 8), 32, SectionPos.sectionToBlockCoord(chunkpos.z, 8));
                double d1 = blockpos$mutableblockpos.distSqr(pos);
                boolean flag = pair == null || d1 < d0;
                if (flag) {
                    Pair<BlockPos, Holder<Structure>> pair1 = getStructureGeneratingAtAsyncSafe(structureHoldersSet, level, structureManager, skipKnownStructures, placement, chunkpos);
                    if (pair1 != null) {
                        pair = pair1;
                        d0 = d1;
                    }
                }
            }

            return pair;
        }
    }

    private static Pair<BlockPos, Holder<Structure>> getNearestGeneratedStructureAsyncSafe(
            Set<Holder<Structure>> structureHoldersSet,
            LevelReader level,
            StructureManager structureManager,
            int x,
            int y,
            int z,
            boolean skipKnownStructures,
            long seed,
            RandomSpreadStructurePlacement spreadPlacement
    ) {
        int i = spreadPlacement.spacing();

        for (int j = -z; j <= z; j++) {
            boolean flag = j == -z || j == z;

            for (int k = -z; k <= z; k++) {
                boolean flag1 = k == -z || k == z;
                if (flag || flag1) {
                    int l = x + i * j;
                    int i1 = y + i * k;
                    ChunkPos chunkpos = spreadPlacement.getPotentialStructureChunk(seed, l, i1);
                    Pair<BlockPos, Holder<Structure>> pair = getStructureGeneratingAtAsyncSafe(structureHoldersSet, level, structureManager, skipKnownStructures, spreadPlacement, chunkpos);
                    if (pair != null) {
                        return pair;
                    }
                }
            }
        }

        return null;
    }

    private static Pair<BlockPos, Holder<Structure>> getStructureGeneratingAtAsyncSafe(
            Set<Holder<Structure>> structureHoldersSet,
            LevelReader level,
            StructureManager structureManager,
            boolean skipKnownStructures,
            StructurePlacement placement,
            ChunkPos chunkPos
    ) {
        // Need to create new instance as StructureCheck is not threadsafe.
        StructureCheckAccessor originalStructureCheck = ((StructureCheckAccessor)((StructureManagerAccessor)structureManager).repurposedstructures$getStructureCheck());
        StructureCheck tempStructureCheck = new StructureCheck(
                originalStructureCheck.repurposedstructures$getStorageAccess(),
                originalStructureCheck.repurposedstructures$getRegistryAccess(),
                originalStructureCheck.repurposedstructures$getStructureTemplateManager(),
                originalStructureCheck.repurposedstructures$getDimension(),
                originalStructureCheck.repurposedstructures$getChunkGenerator(),
                originalStructureCheck.repurposedstructures$getRandomState(),
                originalStructureCheck.repurposedstructures$getHeightAccessor(),
                originalStructureCheck.repurposedstructures$getBiomeSource(),
                originalStructureCheck.repurposedstructures$getSeed(),
                originalStructureCheck.getFixerUpper()
        );
        for (Holder<Structure> holder : structureHoldersSet) {
            StructureCheckResult structurecheckresult = tempStructureCheck.checkStart(chunkPos, holder.value(), placement, skipKnownStructures);
            if (structurecheckresult != StructureCheckResult.START_NOT_PRESENT) {
                if (!skipKnownStructures && structurecheckresult == StructureCheckResult.START_PRESENT) {
                    return Pair.of(placement.getLocatePos(chunkPos), holder);
                }

                ChunkAccess chunkaccess = level.getChunk(chunkPos.x, chunkPos.z, ChunkStatus.STRUCTURE_STARTS);
                StructureStart structurestart = structureManager.getStartForStructure(SectionPos.bottomOf(chunkaccess), holder.value(), chunkaccess);
                if (structurestart != null && structurestart.isValid() && (!skipKnownStructures || tryAddReferenceAsyncSafe(tempStructureCheck, structurestart))) {
                    return Pair.of(placement.getLocatePos(structurestart.getChunkPos()), holder);
                }
            }
        }

        return null;
    }

    private static boolean tryAddReferenceAsyncSafe(StructureCheck tempStructureCheck, StructureStart structureStart) {
        if (structureStart.canBeReferenced()) {
            structureStart.addReference();
            tempStructureCheck.incrementReference(structureStart.getChunkPos(), structureStart.getStructure());
            return true;
        } else {
            return false;
        }
    }

    /////////////////////////////////////////////////////////////////////////////////

}