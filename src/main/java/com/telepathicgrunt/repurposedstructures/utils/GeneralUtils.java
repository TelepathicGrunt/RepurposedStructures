package com.telepathicgrunt.repurposedstructures.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.resources.NamespaceResourceManagerAccessor;
import com.telepathicgrunt.repurposedstructures.mixin.resources.ReloadableResourceManagerImplAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.FrontAndTop;
import net.minecraft.core.Registry;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.FallbackResourceManager;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.JigsawBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

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
                if(!blockView.getBlockState(mutable).getMaterial().isSolid()) {
                    break;
                }
            }
        }

        // Make chest face away from wall
        return blockState.setValue(HorizontalDirectionalBlock.FACING, wallDirection.getOpposite());
    }

    //////////////////////////////////////////////

    public static ItemStack enchantRandomly(RandomSource random, ItemStack itemToEnchant, float chance) {
        if(random.nextFloat() < chance) {
            List<Enchantment> list = Registry.ENCHANTMENT.stream().filter(Enchantment::isDiscoverable)
                    .filter((enchantmentToCheck) -> enchantmentToCheck.canEnchant(itemToEnchant)).toList();
            if(!list.isEmpty()) {
                Enchantment enchantment = list.get(random.nextInt(list.size()));
                // bias towards weaker enchantments
                int enchantmentLevel = random.nextInt(Mth.nextInt(random, enchantment.getMinLevel(), enchantment.getMaxLevel()) + 1);
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
            else if (blockView.getBlock(mutable.getY() + 3).getMaterial() == Material.AIR && (canBeOnLiquid ? !currentBlockstate.isAir() : currentBlockstate.canOcclude())) {
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
                    blockView.getBlock(mutable.getY() + 1).getMaterial() == Material.AIR &&
                    blockView.getBlock(mutable.getY() + 5).getMaterial() == Material.AIR)
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
        return blockState.isAir() || blockState.getMaterial().isLiquid() || blockState.getMaterial().isReplaceable();
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

    // More optimized with checking if the jigsaw blocks can connect
    public static boolean canJigsawsAttach(StructureTemplate.StructureBlockInfo jigsaw1, StructureTemplate.StructureBlockInfo jigsaw2) {
        FrontAndTop prop1 = jigsaw1.state.getValue(JigsawBlock.ORIENTATION);
        FrontAndTop prop2 = jigsaw2.state.getValue(JigsawBlock.ORIENTATION);
        String joint = jigsaw1.nbt.getString("joint");
        if(joint.isEmpty()) {
            joint = prop1.front().getAxis().isHorizontal() ? "aligned" : "rollable";
        }

        boolean isRollable = joint.equals("rollable");
        return prop1.front() == prop2.front().getOpposite() &&
                (isRollable || prop1.top() == prop2.top()) &&
                jigsaw1.nbt.getString("target").equals(jigsaw2.nbt.getString("name"));
    }

    //////////////////////////////////////////////

    /**
     * Obtains all of the file streams for all files found in all datapacks with the given id.
     *
     * @return - Filestream list of all files found with id
     */
    public static List<InputStream> getAllFileStreams(ResourceManager resourceManager, ResourceLocation fileID) throws IOException {
        List<InputStream> fileStreams = new ArrayList<>();

        FallbackResourceManager namespaceResourceManager = ((ReloadableResourceManagerImplAccessor) resourceManager).repurposedstructures_getNamespacedManagers().get(fileID.getNamespace());
        List<FallbackResourceManager.PackEntry> allResourcePacks = ((NamespaceResourceManagerAccessor) namespaceResourceManager).repurposedstructures_getFallbacks();

        // Find the file with the given id and add its filestream to the list
        for (FallbackResourceManager.PackEntry packEntry : allResourcePacks) {
            PackResources resourcePack = packEntry.resources();
            if (resourcePack != null && resourcePack.hasResource(PackType.SERVER_DATA, fileID)) {
                InputStream inputStream = ((NamespaceResourceManagerAccessor) namespaceResourceManager).repurposedstructures_callCreateResourceGetter(fileID, resourcePack).get();
                fileStreams.add(inputStream);
            }
        }

        // Return filestream of all files matching id path
        return fileStreams;
    }

    /**
     * Will grab all JSON objects from all datapacks's folder that is specified by the dataType parameter.
     *
     * @return - A map of paths (identifiers) to a list of all JSON elements found under it from all datapacks.
     */
    public static Map<ResourceLocation, List<JsonElement>> getAllDatapacksJSONElement(ResourceManager resourceManager, Gson gson, String dataType, int fileSuffixLength) {
        Map<ResourceLocation, List<JsonElement>> map = new HashMap<>();
        int dataTypeLength = dataType.length() + 1;

        // Finds all JSON files paths within the pool_additions folder. NOTE: this is just the path rn. Not the actual files yet.
        for (ResourceLocation fileIDWithExtension : resourceManager.listResources(dataType, (fileString) -> fileString.toString().endsWith(".json")).keySet()) {
            String identifierPath = fileIDWithExtension.getPath();
            ResourceLocation fileID = new ResourceLocation(
                    fileIDWithExtension.getNamespace(),
                    identifierPath.substring(dataTypeLength, identifierPath.length() - fileSuffixLength));

            try {
                // getAllFileStreams will find files with the given ID. This part is what will loop over all matching files from all datapacks.
                for (InputStream fileStream : GeneralUtils.getAllFileStreams(resourceManager, fileIDWithExtension)) {
                    try (Reader bufferedReader = new BufferedReader(new InputStreamReader(fileStream, StandardCharsets.UTF_8))) {

                        // Get the JSON from the file
                        JsonElement countsJSONElement = GsonHelper.fromJson(gson, bufferedReader, (Class<? extends JsonElement>) JsonElement.class);
                        if (countsJSONElement != null) {

                            // Create list in map for the ID if non exists yet for that ID
                            if (!map.containsKey(fileID)) {
                                map.put(fileID, new ArrayList<>());
                            }
                            // Add the parsed json to the list we will merge later on
                            map.get(fileID).add(countsJSONElement);
                        }
                        else {
                            RepurposedStructures.LOGGER.error(
                                    "(Repurposed Structures {} MERGER) Couldn't load data file {} from {} as it's null or empty",
                                    dataType,
                                    fileID,
                                    fileIDWithExtension);
                        }
                    }
                }
            }
            catch (IllegalArgumentException | IOException | JsonParseException exception) {
                RepurposedStructures.LOGGER.error(
                        "(Repurposed Structures {} MERGER) Couldn't parse data file {} from {}",
                        dataType,
                        fileID,
                        fileIDWithExtension,
                        exception);
            }
        }

        return map;
    }

    ////////////////////////////

    public static boolean isInvalidLootTableFound(MinecraftServer minecraftServer, Map.Entry<ResourceLocation, ResourceLocation> entry) {
        boolean invalidLootTableFound = false;
        if(minecraftServer.getLootTables().get(entry.getKey()) == LootTable.EMPTY) {
            RepurposedStructures.LOGGER.error("Unable to find loot table key: {}", entry.getKey());
            invalidLootTableFound = true;
        }
        if(minecraftServer.getLootTables().get(entry.getValue()) == LootTable.EMPTY) {
            RepurposedStructures.LOGGER.error("Unable to find loot table value: {}", entry.getValue());
            invalidLootTableFound = true;
        }
        return invalidLootTableFound;
    }

    public static boolean isMissingLootImporting(MinecraftServer minecraftServer, Set<ResourceLocation> tableKeys) {
        AtomicBoolean invalidLootTableFound = new AtomicBoolean(false);
        minecraftServer.getLootTables().getIds().forEach(rl -> {
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

                RepurposedStructures.LOGGER.error("No loot importing found for: {}", rl);
                invalidLootTableFound.set(true);
            }
        });
        return invalidLootTableFound.get();
    }
}