package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.structures.TemplateAccessor;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.features.configs.NbtDungeonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
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
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.List;
import java.util.Optional;

public class NbtDungeon extends Feature<NbtDungeonConfig>{

    public NbtDungeon(Codec<NbtDungeonConfig> configFactory) {
        super(configFactory);
    }

    @Override
    public boolean place(FeaturePlaceContext<NbtDungeonConfig> context) {
        BlockPos position = context.origin().above(-1);
        ResourceLocation nbtRL = GeneralUtils.getRandomEntry(context.config().nbtResourcelocationsAndWeights, context.random());

        StructureTemplateManager structureTemplateManager = context.level().getLevel().getStructureManager();
        Optional<StructureTemplate> template = structureTemplateManager.get(nbtRL);
        if(template.isEmpty()) {
            RepurposedStructures.LOGGER.error("Identifier to the specified nbt file was not found! : {}", nbtRL);
            return false;
        }
        Rotation rotation = Rotation.getRandom(context.random());
        BlockPos size = new BlockPos(template.get().getSize());

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
        ChunkAccess cachedChunk = context.level().getChunk(mutable);

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
                    if(mutable.getX() >> 4 != cachedChunk.getPos().x || mutable.getZ() >> 4 != cachedChunk.getPos().z)
                        cachedChunk = context.level().getChunk(mutable);

                    BlockState state = cachedChunk.getBlockState(mutable);

                    // Dungeons cannot touch fluids if set to air mode and reverse if opposite
                    if(context.config().airRequirementIsNowWater ?
                            state.isAir() || state.getFluidState().is(FluidTags.LAVA) :
                            !state.getFluidState().isEmpty()) {
                        return false;
                    }
                    // Floor must be complete
                    else if(!GeneralUtils.isFullCube(context.level(), mutable, state)) {
                        if (y == 0 && !state.getMaterial().isSolid()) {
                            return false;
                        }
                        else if(state.is(BlockTags.LEAVES)) {
                            continue; // ignore leaves
                        }
                        else if (y == ceiling) {
                            ceilingOpenings++;
                        }
                    }

                    // Check only along wall bottoms for openings
                    if ((x == xMin || x == xMax || z == zMin || z == zMax) && y == 1 && isValidNonSolidBlock(context.config(), state))
                    {
                        BlockState aboveState = cachedChunk.getBlockState(mutable);
                        if(context.config().airRequirementIsNowWater ?
                            !aboveState.getFluidState().isEmpty() :
                            aboveState.isAir())
                        {
                            wallOpenings++;
                        }
                    }

                    // Too much open space. Quit
                    if(wallOpenings > context.config().maxAirSpace || ceilingOpenings > context.config().maxAirSpace) {
                        return false;
                    }
                }
            }
        }

        // Check if we meet minimum for open space.
        if (wallOpenings >= context.config().minAirSpace) {

            // offset the dungeon such as ocean dungeons down 1
            position = position.above(context.config().structureYOffset);

            //RepurposedStructures.LOGGER.log(Level.INFO, nbtRL + " at X: "+position.getX() +", "+position.getY()+", "+position.getZ());
            StructurePlaceSettings placementsettings = (new StructurePlaceSettings()).setRotation(rotation).setRotationPivot(halfLengths).setIgnoreEntities(false);
            Optional<StructureProcessorList> processor = context.level().getLevel().getServer().registryAccess().registryOrThrow(Registry.PROCESSOR_LIST_REGISTRY).getOptional(context.config().processor);
            processor.orElse(ProcessorLists.EMPTY.value()).list().forEach(placementsettings::addProcessor); // add all processors
            BlockPos finalPos = mutable.set(position).move(-halfLengths.getX(), 0, -halfLengths.getZ());
            template.get().placeInWorld(context.level(), finalPos, finalPos, placementsettings, context.random(), Block.UPDATE_CLIENTS);

            // Post-processors
            // For all processors that are sensitive to neighboring blocks such as vines.
            // Post processors will place the blocks themselves so we will not do anything with the return of Structure.process
            placementsettings.clearProcessors();
            Optional<StructureProcessorList> postProcessor = context.level().getLevel().getServer().registryAccess().registryOrThrow(Registry.PROCESSOR_LIST_REGISTRY).getOptional(context.config().postProcessor);
            postProcessor.orElse(ProcessorLists.EMPTY.value()).list().forEach(placementsettings::addProcessor); // add all post processors
            List<StructureTemplate.StructureBlockInfo> list = placementsettings.getRandomPalette(((TemplateAccessor)template.get()).repurposedstructures_getPalettes(), mutable).blocks();
            StructureTemplate.processBlockInfos(context.level(), mutable, mutable, placementsettings, list);

            spawnLootBlocks(context.level(), context.random(), position, context.config(), fullLengths, halfLengthsRotated, mutable);
            return true;
        }

        return false;
    }

    /**
     * For determining what kind of check to do based on if this dungeon is air or water based.
     */
    private boolean isValidNonSolidBlock(NbtDungeonConfig config, BlockState state) {
        if(config.airRequirementIsNowWater) {
            return !state.getFluidState().isEmpty();
        }
        return state.isAir();
    }

    /**
     * Makes the given block entity now have the correct spawner mob
     */
    private void SetMobSpawnerEntity(RandomSource random, NbtDungeonConfig config, SpawnerBlockEntity blockEntity) {
        EntityType<?> entity = RepurposedStructures.mobSpawnerManager.getSpawnerMob(config.rsSpawnerResourcelocation, random);
        if(entity != null) {
            blockEntity.getSpawner().setEntityId(entity);
        }
        else{
            RepurposedStructures.LOGGER.warn("EntityType in a dungeon does not exist in registry! : {}", config.rsSpawnerResourcelocation);
        }
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
    private void spawnLootBlocks(WorldGenLevel world, RandomSource random, BlockPos position, NbtDungeonConfig config, BlockPos fullLengths, BlockPos halfLengths, BlockPos.MutableBlockPos mutable) {
        boolean isPlacingChestLikeBlock = config.lootBlock.defaultBlockState().getBlock() instanceof ChestBlock;

        // Add chests that are wall based
        for(int currentChestAttempt = 0; currentChestAttempt < config.maxNumOfChests;) {
            boolean addedChestThisAttempt = false;
            for (int currentChestPosAttempt = 0; currentChestPosAttempt < fullLengths.getX() + fullLengths.getZ() + halfLengths.getY(); ++currentChestPosAttempt) {
                if (config.chanceOfSpawningLootBlockAtSpot.isPresent() &&
                    random.nextFloat() >= config.chanceOfSpawningLootBlockAtSpot.get())
                {
                    continue;
                }

                mutable.set(position).move(
                        random.nextInt(Math.max(fullLengths.getX() - 2, 1)) - halfLengths.getX() + 1,
                        random.nextInt(Math.max(fullLengths.getY() - 1, 1)),
                        random.nextInt(Math.max(fullLengths.getZ() - 2, 1)) - halfLengths.getZ() + 1);

                BlockState currentBlock = world.getBlockState(mutable);
                if (isValidNonSolidBlock(config, currentBlock)) {
                    BlockState belowState = world.getBlockState(mutable.move(Direction.DOWN));
                    if(belowState.isFaceSturdy(world, mutable, Direction.UP) && belowState.getBlock() != config.lootBlock) {
                        mutable.move(Direction.UP);
                        boolean isOnWall = false;

                        for(Direction neighborDirection : Direction.Plane.HORIZONTAL) {
                            mutable.move(neighborDirection);
                            BlockState neighboringState = world.getBlockState(mutable);
                            mutable.move(neighborDirection.getOpposite());

                            if(isPlacingChestLikeBlock && neighboringState.getBlock() instanceof ChestBlock) {
                                // Only connect to single chests
                                if(neighboringState.getValue(ChestBlock.TYPE) == ChestType.SINGLE) {

                                    BlockState currentStateForChest = GeneralUtils.orientateChest(world, mutable, config.lootBlock.defaultBlockState());
                                    Direction currentDirection = currentStateForChest.getValue(HorizontalDirectionalBlock.FACING);

                                    // If oriented is on same axis as neighboring chest, find a new direction on sides.
                                    if(neighborDirection.getAxis() == currentDirection.getAxis()) {
                                        currentDirection = currentDirection.getClockWise();
                                        BlockPos wallCheckPos = mutable.relative(currentDirection);
                                        BlockPos wallCheckPos2 = wallCheckPos.relative(neighborDirection);
                                        BlockState blockState = world.getBlockState(wallCheckPos);
                                        BlockState blockState2 = world.getBlockState(wallCheckPos2);

                                        // If first side is solid wall we are facing or neighbor is facing, switch to other side
                                        if((blockState.getMaterial().isSolid() && !(blockState.getBlock() instanceof SpawnerBlock)) ||
                                                (blockState2.getMaterial().isSolid() && !(blockState2.getBlock() instanceof SpawnerBlock))
                                        ) {

                                            currentDirection = currentDirection.getOpposite();
                                        }
                                    }

                                    boolean chestTyping = neighborDirection.getAxisDirection() == currentDirection.getAxisDirection();
                                    if(neighborDirection.getAxis() == Direction.Axis.Z) {
                                        chestTyping = !chestTyping;
                                    }

                                    // Place chest
                                    world.setBlock(mutable,
                                            config.lootBlock.defaultBlockState()
                                                    .setValue(ChestBlock.WATERLOGGED, currentBlock.getFluidState().is(FluidTags.WATER))
                                                    .setValue(ChestBlock.FACING, currentDirection)
                                                    .setValue(ChestBlock.TYPE, chestTyping ? ChestType.RIGHT : ChestType.LEFT),
                                            2);
                                    RandomizableContainerBlockEntity.setLootTable(world, random, mutable, config.chestResourcelocation);

                                    // Set neighboring chest to face same way too
                                    world.setBlock(mutable.move(neighborDirection),
                                            neighboringState
                                                    .setValue(ChestBlock.FACING, currentDirection)
                                                    .setValue(ChestBlock.TYPE, chestTyping ? ChestType.LEFT : ChestType.RIGHT),
                                            2);
                                    RandomizableContainerBlockEntity.setLootTable(world, random, mutable, config.chestResourcelocation);
                                    SolidifyBlock(world, mutable.below());

                                    isOnWall = false; // Skip wall code as we already placed chest
                                    currentChestAttempt++;
                                    addedChestThisAttempt = true;
                                    if(currentChestAttempt == config.maxNumOfChests) {
                                        return;
                                    }
                                    break;
                                }
                            }
                            else if(GeneralUtils.isFullCube(world, mutable, neighboringState) && !(neighboringState.getBlock() instanceof SpawnerBlock)) {
                                isOnWall = true;
                            }
                        }

                        // Is not next to another chest.
                        if(isOnWall) {
                            BlockState lootBlock = config.lootBlock.defaultBlockState();
                            if(lootBlock.hasProperty(BlockStateProperties.WATERLOGGED)) {
                                lootBlock.setValue(BlockStateProperties.WATERLOGGED, currentBlock.getFluidState().is(FluidTags.WATER));
                            }
                            if(lootBlock.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
                                lootBlock = GeneralUtils.orientateChest(world, mutable, lootBlock);
                            }

                            // Set chest to face away from wall.
                            world.setBlock(mutable, lootBlock, 2);
                            currentChestAttempt++;
                            addedChestThisAttempt = true;

                            RandomizableContainerBlockEntity.setLootTable(world, random, mutable, config.chestResourcelocation);
                            mutable.move(Direction.DOWN);
                            if(lootBlock.getBlock() == Blocks.SHULKER_BOX && world.getBlockEntity(mutable) == null) {
                                world.setBlock(mutable, Blocks.SPAWNER.defaultBlockState(), 2);
                                BlockEntity blockEntity = world.getBlockEntity(mutable);
                                if (blockEntity instanceof SpawnerBlockEntity) {
                                    SetMobSpawnerEntity(random, config, (SpawnerBlockEntity) blockEntity);
                                }
                            }
                            else{
                                SolidifyBlock(world, mutable);
                            }

                            break;
                        }
                    }
                }
            }
            if(!addedChestThisAttempt) currentChestAttempt++;
        }
    }
}
