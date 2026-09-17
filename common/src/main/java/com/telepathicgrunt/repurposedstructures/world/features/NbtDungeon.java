package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.misc.mobspawners.MobSpawnerManager;
import com.telepathicgrunt.repurposedstructures.mixins.structures.TemplateAccessor;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.RandomizableContainer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.List;
import java.util.Optional;

public record NbtDungeon(
        boolean replaceAir,
        int minAirSpace,
        int maxAirSpace,
        int maxNumOfChests,
        Identifier chestIdentifier,
        List<Pair<Identifier, Integer>> nbtIdentifiersAndWeights,
        Identifier rsSpawnerIdentifier,
        Identifier processor,
        Identifier postProcessor,
        boolean airRequirementIsNowWater,
        int structureYOffset,
        Block lootBlock,
        Optional<Float> chanceOfSpawningLootBlockAtSpot
) implements Feature {

    public static final MapCodec<NbtDungeon> CODEC = RecordCodecBuilder.<NbtDungeon>mapCodec((configInstance) -> configInstance.group(
                    Codec.BOOL.fieldOf("replace_air").orElse(false).forGetter(nbtDungeon -> nbtDungeon.replaceAir),
                    Codec.intRange(0, Integer.MAX_VALUE).fieldOf("min_air_space").forGetter(nbtDungeon -> nbtDungeon.minAirSpace),
                    Codec.intRange(0, Integer.MAX_VALUE).fieldOf("max_air_space").forGetter(nbtDungeon -> nbtDungeon.maxAirSpace),
                    Codec.intRange(0, 100).fieldOf("max_num_of_loot_blocks").forGetter(nbtDungeon -> nbtDungeon.maxNumOfChests),
                    Codec.BOOL.fieldOf("air_requirement_is_now_water").orElse(false).forGetter(nbtDungeon -> nbtDungeon.airRequirementIsNowWater),
                    Codec.INT.fieldOf("structure_y_offset").orElse(0).forGetter(nbtDungeon -> nbtDungeon.structureYOffset),
                    BuiltInRegistries.BLOCK.byNameCodec().fieldOf("loot_block").orElse(Blocks.CHEST).forGetter(nbtDungeon -> nbtDungeon.lootBlock),
                    Identifier.CODEC.fieldOf("loot_block_loottable_identifier").forGetter(nbtDungeon -> nbtDungeon.chestIdentifier),
                    Identifier.CODEC.fieldOf("rs_spawner_identifier").forGetter(nbtDungeon -> nbtDungeon.rsSpawnerIdentifier),
                    Identifier.CODEC.fieldOf("processors").forGetter(nbtDungeon -> nbtDungeon.processor),
                    Identifier.CODEC.fieldOf("post_processors").orElse(Identifier.fromNamespaceAndPath("minecraft", "empty")).forGetter(nbtDungeon -> nbtDungeon.postProcessor),
                    Codec.mapPair(Identifier.CODEC.fieldOf("identifier"), Codec.intRange(1, Integer.MAX_VALUE).fieldOf("weight")).codec().listOf().fieldOf("dungeon_nbt_entries").forGetter(nbtDungeon -> nbtDungeon.nbtIdentifiersAndWeights),
                    Codec.floatRange(0, 1).optionalFieldOf("chance_of_spawning_loot_block_at_spot").forGetter(nbtDungeon -> nbtDungeon.chanceOfSpawningLootBlockAtSpot)
            ).apply(configInstance, NbtDungeon::new))
            .validate((nbtDungeon) -> nbtDungeon.maxAirSpace <= nbtDungeon.minAirSpace ?
                    DataResult.error(() -> "min_air_space has to be smaller than max_air_space") : DataResult.success(nbtDungeon));

    public NbtDungeon(boolean replaceAir, int minAirSpace, int maxAirSpace,
                      int maxNumOfChests, boolean airRequirementIsNowWater, int structureYOffset,
                      Block lootBlock, Identifier chestIdentifier,
                      Identifier rsSpawnerIdentifier, Identifier processor, Identifier postProcessor,
                      List<Pair<Identifier, Integer>> nbtIdentifiersAndWeights, Optional<Float> chanceOfSpawningLootBlockAtSpot) {
        this(replaceAir, minAirSpace, maxAirSpace, maxNumOfChests, chestIdentifier, nbtIdentifiersAndWeights, rsSpawnerIdentifier, processor, postProcessor, airRequirementIsNowWater, structureYOffset, lootBlock, chanceOfSpawningLootBlockAtSpot);
    }

    @Override
    public MapCodec<NbtDungeon> codec() {
        return CODEC;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {
        BlockPos position = origin.above(-1);
        Identifier nbtRL = GeneralUtils.getRandomEntry(nbtIdentifiersAndWeights, random);

        StructureTemplateManager structureTemplateManager = level.getLevel().getStructureTemplateManager();
        Optional<StructureTemplate> template = structureTemplateManager.get(nbtRL);
        if (template.isEmpty()) {
            RepurposedStructures.LOGGER.error("Identifier to the specified nbt file was not found! : {}", nbtRL);
            return false;
        }
        Rotation rotation = Rotation.getRandom(random);
        BlockPos size = new BlockPos(
                template.get().getSize().getX(),
                template.get().getSize().getY(),
                template.get().getSize().getZ());

        // For proper offsetting the dungeon so it rotate properly around position parameter.
        BlockPos halfLengths = new BlockPos(
                size.getX() / 2,
                size.getY() / 2,
                size.getZ() / 2);

        // Rotated blockpos for the nbt's sizes to be used later.
        BlockPos fullLengths = new BlockPos(
                Math.abs(size.rotate(rotation).getX()),
                Math.abs(size.rotate(rotation).getY()),
                Math.abs(size.rotate(rotation).getZ()));

        // For post processing spawners and chests for rotated dungeon.
        BlockPos halfLengthsRotated = new BlockPos(
                fullLengths.getX() / 2,
                fullLengths.getY() / 2,
                fullLengths.getZ() / 2);

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(position);
        ChunkAccess cachedChunk = level.getChunk(mutable);

        int xMin = -halfLengthsRotated.getX();
        int xMax = halfLengthsRotated.getX();
        int zMin = -halfLengthsRotated.getZ();
        int zMax = halfLengthsRotated.getZ();
        int wallOpenings = 0;
        int ceilingOpenings = 0;
        int ceiling = size.getY();

        for (int x = xMin; x <= xMax; x++) {
            for (int z = zMin; z <= zMax; z++) {
                for (int y = 0; y <= ceiling; y++) {
                    mutable.set(position).move(x, y, z);
                    if (mutable.getX() >> 4 != cachedChunk.getPos().x() || mutable.getZ() >> 4 != cachedChunk.getPos().z())
                        cachedChunk = level.getChunk(mutable);

                    BlockState state = cachedChunk.getBlockState(mutable);

                    // Dungeons cannot touch fluids if set to air mode and reverse if opposite
                    if (airRequirementIsNowWater ?
                            state.isAir() || state.getFluidState().is(FluidTags.LAVA) :
                            !state.getFluidState().isEmpty()) {
                        return false;
                    }
                    // Floor must be complete
                    else if (!GeneralUtils.isFullCube(state)) {
                        if (y == 0 && !state.isSolid()) {
                            return false;
                        } else if (state.is(BlockTags.LEAVES)) {
                            continue; // ignore leaves
                        } else if (y == ceiling) {
                            ceilingOpenings++;
                        }
                    }

                    // Check only along wall bottoms for openings
                    if ((x == xMin || x == xMax || z == zMin || z == zMax) && y == 1 && isValidNonSolidBlock(state)) {
                        BlockState aboveState = cachedChunk.getBlockState(mutable);
                        if (airRequirementIsNowWater ?
                                !aboveState.getFluidState().isEmpty() :
                                aboveState.isAir()) {
                            wallOpenings++;
                        }
                    }

                    // Too much open space. Quit
                    if (wallOpenings > maxAirSpace || ceilingOpenings > maxAirSpace) {
                        return false;
                    }
                }
            }
        }

        // Check if we meet minimum for open space.
        if (wallOpenings >= minAirSpace) {

            // offset the dungeon such as ocean dungeons down 1
            position = position.above(structureYOffset);

            Registry<StructureProcessorList> processorListRegistry = level.getLevel().getServer().registryAccess().lookupOrThrow(Registries.PROCESSOR_LIST);
            ResourceKey<StructureProcessorList> emptyKey = ResourceKey.create(Registries.PROCESSOR_LIST, Identifier.fromNamespaceAndPath("minecraft", "empty"));

            //RepurposedStructures.LOGGER.log(Level.INFO, nbtRL + " at X: "+position.getX() +", "+position.getY()+", "+position.getZ());
            StructurePlaceSettings placementsettings = (new StructurePlaceSettings()).setRotation(rotation).setRotationPivot(halfLengths).setIgnoreEntities(false);
            Optional<StructureProcessorList> processorOptional = processorListRegistry.getOptional(processor);
            processorOptional.orElse(processorListRegistry.getValue(emptyKey)).list().forEach(placementsettings::addProcessor); // add all processors
            BlockPos finalPos = mutable.set(position).move(-halfLengths.getX(), 0, -halfLengths.getZ());
            template.get().placeInWorld(level, finalPos, finalPos, placementsettings, random, Block.UPDATE_CLIENTS);

            // Post-processors
            // For all processors that are sensitive to neighboring blocks such as vines.
            // Post processors will place the blocks themselves so we will not do anything with the return of Structure.process
            placementsettings.clearProcessors();
            Optional<StructureProcessorList> postProcessor = processorListRegistry.getOptional(processor);
            postProcessor.orElse(processorListRegistry.getValue(emptyKey)).list().forEach(placementsettings::addProcessor); // add all post processors
            List<StructureTemplate.StructureBlockInfo> list = placementsettings.getRandomPalette(((TemplateAccessor) template.get()).repurposedstructures$getPalettes(), mutable).blocks();
            StructureTemplate.processBlockInfos(level, mutable, mutable, placementsettings, list);

            spawnLootBlocks(level, random, position, fullLengths, halfLengthsRotated, mutable);
            return true;
        }

        return false;
    }

    /**
     * For determining what kind of check to do based on if this dungeon is air or water based.
     */
    private boolean isValidNonSolidBlock(BlockState state) {
        if (airRequirementIsNowWater) {
            return !state.getFluidState().isEmpty();
        }
        return state.isAir();
    }

    /**
     * Makes the targeted slab block now a full block.
     */
    private void SolidifyBlock(WorldGenLevel world, BlockPos pos) {
        BlockState blockBelow = world.getBlockState(pos);
        if (blockBelow.hasProperty(SlabBlock.TYPE)) {
            world.setBlock(pos, blockBelow.setValue(SlabBlock.TYPE, SlabType.DOUBLE), 3);
        }
    }

    /**
     * Places and connects chests on walls of dungeon space
     */
    private void spawnLootBlocks(WorldGenLevel world, RandomSource random, BlockPos position, BlockPos fullLengths, BlockPos halfLengths, BlockPos.MutableBlockPos mutable) {
        boolean isPlacingChestLikeBlock = lootBlock.defaultBlockState().getBlock() instanceof ChestBlock;

        // Add chests that are wall based
        for (int currentChestAttempt = 0; currentChestAttempt < maxNumOfChests; ) {
            boolean addedChestThisAttempt = false;
            for (int currentChestPosAttempt = 0; currentChestPosAttempt < fullLengths.getX() + fullLengths.getZ() + halfLengths.getY(); ++currentChestPosAttempt) {
                if (chanceOfSpawningLootBlockAtSpot.isPresent() &&
                        random.nextFloat() >= chanceOfSpawningLootBlockAtSpot.get()) {
                    continue;
                }

                mutable.set(position).move(
                        random.nextInt(Math.max(fullLengths.getX() - 2, 1)) - halfLengths.getX() + 1,
                        random.nextInt(Math.max(fullLengths.getY() - 1, 1)),
                        random.nextInt(Math.max(fullLengths.getZ() - 2, 1)) - halfLengths.getZ() + 1);

                BlockState currentBlock = world.getBlockState(mutable);
                if (isValidNonSolidBlock(currentBlock)) {
                    BlockState belowState = world.getBlockState(mutable.move(Direction.DOWN));
                    if (belowState.isFaceSturdy(world, mutable, Direction.UP) && belowState.getBlock() != lootBlock) {
                        mutable.move(Direction.UP);
                        boolean isOnWall = false;

                        ResourceKey<LootTable> lootTableResourceKey = ResourceKey.create(Registries.LOOT_TABLE, chestIdentifier);

                        for (Direction neighborDirection : Direction.Plane.HORIZONTAL) {
                            mutable.move(neighborDirection);
                            BlockState neighboringState = world.getBlockState(mutable);
                            mutable.move(neighborDirection.getOpposite());

                            if (isPlacingChestLikeBlock && neighboringState.getBlock() instanceof ChestBlock) {
                                // Only connect to single chests
                                if (neighboringState.getValue(ChestBlock.TYPE) == ChestType.SINGLE) {

                                    BlockState currentStateForChest = GeneralUtils.orientateChest(world, mutable, lootBlock.defaultBlockState());
                                    Direction currentDirection = currentStateForChest.getValue(HorizontalDirectionalBlock.FACING);

                                    // If oriented is on same axis as neighboring chest, find a new direction on sides.
                                    if (neighborDirection.getAxis() == currentDirection.getAxis()) {
                                        currentDirection = currentDirection.getClockWise();
                                        BlockPos wallCheckPos = mutable.relative(currentDirection);
                                        BlockPos wallCheckPos2 = wallCheckPos.relative(neighborDirection);
                                        BlockState blockState = world.getBlockState(wallCheckPos);
                                        BlockState blockState2 = world.getBlockState(wallCheckPos2);

                                        // If first side is solid wall we are facing or neighbor is facing, switch to other side
                                        if ((blockState.isSolid() && !(blockState.getBlock() instanceof SpawnerBlock)) ||
                                                (blockState2.isSolid() && !(blockState2.getBlock() instanceof SpawnerBlock))
                                        ) {

                                            currentDirection = currentDirection.getOpposite();
                                        }
                                    }

                                    boolean chestTyping = neighborDirection.getAxisDirection() == currentDirection.getAxisDirection();
                                    if (neighborDirection.getAxis() == Direction.Axis.Z) {
                                        chestTyping = !chestTyping;
                                    }

                                    // Place chest
                                    world.setBlock(mutable,
                                            lootBlock.defaultBlockState()
                                                    .setValue(ChestBlock.WATERLOGGED, currentBlock.getFluidState().is(FluidTags.WATER))
                                                    .setValue(ChestBlock.FACING, currentDirection)
                                                    .setValue(ChestBlock.TYPE, chestTyping ? ChestType.RIGHT : ChestType.LEFT),
                                            2);
                                    RandomizableContainer.setBlockEntityLootTable(world, random, mutable, lootTableResourceKey);

                                    // Set neighboring chest to face same way too
                                    world.setBlock(mutable.move(neighborDirection),
                                            neighboringState
                                                    .setValue(ChestBlock.FACING, currentDirection)
                                                    .setValue(ChestBlock.TYPE, chestTyping ? ChestType.LEFT : ChestType.RIGHT),
                                            2);
                                    RandomizableContainer.setBlockEntityLootTable(world, random, mutable, lootTableResourceKey);
                                    SolidifyBlock(world, mutable.below());

                                    isOnWall = false; // Skip wall code as we already placed chest
                                    currentChestAttempt++;
                                    addedChestThisAttempt = true;
                                    if (currentChestAttempt == maxNumOfChests) {
                                        return;
                                    }
                                    break;
                                }
                            } else if (GeneralUtils.isFullCube(neighboringState) && !(neighboringState.getBlock() instanceof SpawnerBlock)) {
                                isOnWall = true;
                            }
                        }

                        // Is not next to another chest.
                        if (isOnWall) {
                            BlockState lootBlockState = lootBlock.defaultBlockState();
                            if (lootBlockState.hasProperty(BlockStateProperties.WATERLOGGED)) {
                                lootBlockState.setValue(BlockStateProperties.WATERLOGGED, currentBlock.getFluidState().is(FluidTags.WATER));
                            }
                            if (lootBlockState.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
                                lootBlockState = GeneralUtils.orientateChest(world, mutable, lootBlockState);
                            }

                            // Set chest to face away from wall.
                            world.setBlock(mutable, lootBlockState, 2);
                            currentChestAttempt++;
                            addedChestThisAttempt = true;

                            RandomizableContainer.setBlockEntityLootTable(world, random, mutable, lootTableResourceKey);
                            mutable.move(Direction.DOWN);
                            if (lootBlockState.getBlock() == Blocks.SHULKER_BOX && world.getBlockEntity(mutable) == null) {
                                EntityType<?> entity = MobSpawnerManager.MOB_SPAWNER_MANAGER.getSpawnerMob(rsSpawnerIdentifier, random);
                                if (entity != null) {
                                    world.setBlock(mutable, Blocks.SPAWNER.defaultBlockState(), 2);
                                    BlockEntity blockEntity = world.getBlockEntity(mutable);
                                    if (blockEntity instanceof SpawnerBlockEntity spawnerBlockEntity) {
                                        spawnerBlockEntity.getSpawner().setEntityId(entity, null, random, mutable);
                                    }
                                }
                            } else {
                                SolidifyBlock(world, mutable);
                            }

                            break;
                        }
                    }
                }
            }
            if (!addedChestThisAttempt) currentChestAttempt++;
        }
    }
}
