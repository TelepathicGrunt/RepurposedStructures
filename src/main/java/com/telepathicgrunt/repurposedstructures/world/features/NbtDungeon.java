package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.TemplateAccessor;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.features.configs.NbtDungeonConfig;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.ChestType;
import net.minecraft.state.properties.SlabType;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.template.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class NbtDungeon extends Feature<NbtDungeonConfig>{

    public NbtDungeon(Codec<NbtDungeonConfig> configFactory) {
        super(configFactory);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos position, NbtDungeonConfig config) {
        if(GeneralUtils.isWorldBlacklisted(world)) return false;

        ResourceLocation nbtRL = GeneralUtils.getRandomEntry(config.nbtResourcelocationsAndWeights, random);

        TemplateManager structureManager = world.getLevel().getStructureManager();
        Template template = structureManager.get(nbtRL);
        if(template == null){
            RepurposedStructures.LOGGER.error("Identifier to the specified nbt file was not found! : {}", nbtRL);
            return false;
        }
        Rotation rotation = Rotation.getRandom(random);

        // For proper offsetting the dungeon so it rotate properly around position parameter.
        BlockPos halfLengths = new BlockPos(
                template.getSize().getX() / 2,
                template.getSize().getY() / 2,
                template.getSize().getZ() / 2);

        // Rotated blockpos for the nbt's sizes to be used later.
        BlockPos fullLengths = new BlockPos(
                Math.abs(template.getSize().rotate(rotation).getX()),
                Math.abs(template.getSize().rotate(rotation).getY()),
                Math.abs(template.getSize().rotate(rotation).getZ()));

        // For post processing spawners and chests for rotated dungeon.
        BlockPos halfLengthsRotated = new BlockPos(
                fullLengths.getX() / 2,
                fullLengths.getY() / 2,
                fullLengths.getZ() / 2);

        BlockPos.Mutable mutable = new BlockPos.Mutable().set(position);
        IChunk cachedChunk = world.getChunk(mutable);

        int xMin = -halfLengthsRotated.getX();
        int xMax = halfLengthsRotated.getX();
        int zMin = -halfLengthsRotated.getZ();
        int zMax = halfLengthsRotated.getZ();
        int wallOpenings = 0;
        int ceilingOpenings = 0;
        int ceiling = template.getSize().getY();

        for (int x = xMin; x <= xMax; x++) {
            for (int z = zMin; z <= zMax; z++) {
                for (int y = 0; y <= ceiling; y++) {
                    mutable.set(position).move(x, y, z);
                    if(mutable.getX() >> 4 != cachedChunk.getPos().x || mutable.getZ() >> 4 != cachedChunk.getPos().z)
                        cachedChunk = world.getChunk(mutable);

                    BlockState state = cachedChunk.getBlockState(mutable);

                    // Dungeons cannot touch fluids if set to air mode and reverse if opposite
                    if(config.airRequirementIsNowWater ? state.isAir() || state.getFluidState().is(FluidTags.LAVA) : !state.getFluidState().isEmpty()){
                        return false;
                    }
                    // Floor must be complete
                    else if(!GeneralUtils.isFullCube(world, mutable, state)){
                        if (y == 0 && !state.getMaterial().isSolid()) {
                            return false;
                        }
                        else if(state.is(BlockTags.LEAVES)){
                            continue; // ignore leaves
                        }
                        else if (y == ceiling) {
                            ceilingOpenings++;
                        }
                    }

                    // Check only along wall bottoms for openings
                    if ((x == xMin || x == xMax || z == zMin || z == zMax) && y == 1 && isValidNonSolidBlock(config, state))
                    {
                        BlockState aboveState = cachedChunk.getBlockState(mutable);
                        if(config.airRequirementIsNowWater ?
                                !aboveState.getFluidState().isEmpty() :
                                aboveState.isAir())
                        {
                            wallOpenings++;
                        }
                    }

                    // Too much open space. Quit
                    if(wallOpenings > config.maxAirSpace || ceilingOpenings > config.maxAirSpace){
                        return false;
                    }
                }
            }
        }

        // Check if we meet minimum for open space.
        if (wallOpenings >= config.minAirSpace) {
            // offset the dungeon such as ocean dungeons down 1
            position = position.above(config.structureYOffset);

            //UltraAmplifiedDimension.LOGGER.log(Level.INFO, nbtRL + " at X: "+position.getX() +", "+position.getY()+", "+position.getZ());
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(rotation).setRotationPivot(halfLengths).setIgnoreEntities(false);
            Optional<StructureProcessorList> processor = world.getLevel().getServer().registryAccess().registryOrThrow(Registry.PROCESSOR_LIST_REGISTRY).getOptional(config.processor);
            processor.orElse(ProcessorLists.EMPTY).list().forEach(placementsettings::addProcessor); // add all processors
            template.placeInWorldChunk(world, mutable.set(position).move(-halfLengths.getX(), 0, -halfLengths.getZ()), placementsettings, random);

            // Post-processors
            // For all processors that are sensitive to neighboring blocks such as vines.
            // Post processors will place the blocks themselves so we will not do anything with the return of Structure.process
            placementsettings.clearProcessors();
            Optional<StructureProcessorList> postProcessor = world.getLevel().getServer().registryAccess().registryOrThrow(Registry.PROCESSOR_LIST_REGISTRY).getOptional(config.postProcessor);
            postProcessor.orElse(ProcessorLists.EMPTY).list().forEach(placementsettings::addProcessor); // add all post processors
            List<Template.BlockInfo> list = placementsettings.getRandomPalette(((TemplateAccessor)template).rs_getBlocks(), mutable).blocks();
            Template.processBlockInfos(world, mutable, mutable, placementsettings, list);

            spawnLootBlocks(world, random, position, config, fullLengths, halfLengthsRotated, mutable);
            return true;
        }

        return false;
    }

    private boolean isValidNonSolidBlock(NbtDungeonConfig config, BlockState state){
        if(config.airRequirementIsNowWater){
            return !state.getFluidState().isEmpty();
        }
        return state.isAir();
    }

    /**
     * Makes the given block entity now have the correct spawner mob
     */
    private void SetMobSpawnerEntity(Random random, NbtDungeonConfig config, MobSpawnerTileEntity blockEntity) {
        EntityType<?> entity = RepurposedStructures.mobSpawnerManager.getSpawnerMob(config.rsSpawnerResourcelocation, random);
        if(entity != null){
            blockEntity.getSpawner().setEntityId(entity);
        }
        else{
            RepurposedStructures.LOGGER.warn("EntityType in a dungeon does not exist in registry! : {}", config.rsSpawnerResourcelocation);
        }
    }

    /**
     * Makes the targeted slab block now a full block.
     */
    private void SolidifyBlock(ISeedReader world, BlockPos pos) {
        BlockState blockBelow = world.getBlockState(pos);
        if (blockBelow.hasProperty(SlabBlock.TYPE)) {
            world.setBlock(pos, blockBelow.setValue(SlabBlock.TYPE, SlabType.DOUBLE), 3);
        }
    }


    private void spawnLootBlocks(ISeedReader world, Random random, BlockPos position, NbtDungeonConfig config, BlockPos fullLengths, BlockPos halfLengths, BlockPos.Mutable mutable) {
        boolean isPlacingChestLikeBlock = config.lootBlock.getBlock() instanceof ChestBlock;

        // Add chests that are wall based
        for(int currentChestCount = 0; currentChestCount < config.maxNumOfChests; ++currentChestCount) {
            for (int currentChestAttempt = 0; currentChestAttempt < fullLengths.getX() + fullLengths.getZ() + halfLengths.getY(); ++currentChestAttempt) {
                if (currentChestCount == config.maxNumOfChests) {
                    return; // early exit
                }

                mutable.set(position).move(
                        random.nextInt(Math.max(fullLengths.getX() - 2, 1)) - halfLengths.getX() + 1,
                        random.nextInt(Math.max(fullLengths.getY() - 1, 1)),
                        random.nextInt(Math.max(fullLengths.getZ() - 2, 1)) - halfLengths.getZ() + 1);

                BlockState currentBlock = world.getBlockState(mutable);
                if (isValidNonSolidBlock(config, currentBlock)) {
                    if(world.getBlockState(mutable.move(Direction.DOWN)).isFaceSturdy(world, mutable, Direction.UP)){
                        mutable.move(Direction.UP);
                        boolean isOnWall = false;

                        for(Direction neighborDirection : Direction.Plane.HORIZONTAL){

                            mutable.move(neighborDirection);
                            BlockState neighboringState = world.getBlockState(mutable);
                            mutable.move(neighborDirection.getOpposite());

                            if(isPlacingChestLikeBlock && neighboringState.getBlock() instanceof ChestBlock){
                                // Only connect to single chests
                                if(neighboringState.getValue(ChestBlock.TYPE) == ChestType.SINGLE){

                                    BlockState currentStateForChest = GeneralUtils.orientateChest(world, mutable, config.lootBlock);
                                    Direction currentDirection = currentStateForChest.getValue(HorizontalBlock.FACING);

                                    // If oriented is on same axis as neighboring chest, find a new direction on sides.
                                    if(neighborDirection.getAxis() == currentDirection.getAxis()){
                                        currentDirection = currentDirection.getClockWise();
                                        BlockPos wallCheckPos = mutable.relative(currentDirection);
                                        BlockPos wallCheckPos2 = wallCheckPos.relative(neighborDirection);
                                        BlockState blockState = world.getBlockState(wallCheckPos);
                                        BlockState blockState2 = world.getBlockState(wallCheckPos2);

                                        // If first side is solid wall we are facing or neighbor is facing, switch to other side
                                        if((blockState.getMaterial().isSolid() && !(blockState.getBlock() instanceof SpawnerBlock)) ||
                                                (blockState2.getMaterial().isSolid() && !(blockState2.getBlock() instanceof SpawnerBlock))
                                        ){

                                            currentDirection = currentDirection.getOpposite();
                                        }
                                    }

                                    boolean chestTyping = neighborDirection.getAxisDirection() == currentDirection.getAxisDirection();
                                    if(neighborDirection.getAxis() == Direction.Axis.Z){
                                        chestTyping = !chestTyping;
                                    }

                                    // Place chest
                                    world.setBlock(mutable,
                                            config.lootBlock
                                                    .setValue(ChestBlock.WATERLOGGED, currentBlock.getFluidState().is(FluidTags.WATER))
                                                    .setValue(ChestBlock.FACING, currentDirection)
                                                    .setValue(ChestBlock.TYPE, chestTyping ? ChestType.RIGHT : ChestType.LEFT),
                                            2);

                                    // Set neighboring chest to face same way too
                                    world.setBlock(mutable.move(neighborDirection),
                                            neighboringState
                                                    .setValue(ChestBlock.FACING, currentDirection)
                                                    .setValue(ChestBlock.TYPE, chestTyping ? ChestType.LEFT : ChestType.RIGHT),
                                            2);

                                    LockableLootTileEntity.setLootTable(world, random, mutable, config.chestIdentifier);
                                    SolidifyBlock(world, mutable.below());

                                    isOnWall = false; // Skip wall code as we already placed chest
                                    break;
                                }
                            }
                            else if(GeneralUtils.isFullCube(world, mutable, neighboringState) && !(neighboringState.getBlock() instanceof SpawnerBlock)){
                                isOnWall = true;
                            }
                        }

                        // Is not next to another chest.
                        if(isOnWall){
                            BlockState lootBlock = config.lootBlock;
                            if(lootBlock.hasProperty(BlockStateProperties.WATERLOGGED)){
                                lootBlock.setValue(BlockStateProperties.WATERLOGGED, currentBlock.getFluidState().is(FluidTags.WATER));
                            }
                            if(lootBlock.hasProperty(BlockStateProperties.HORIZONTAL_FACING)){
                                lootBlock = GeneralUtils.orientateChest(world, mutable, lootBlock);
                            }

                            // Set chest to face away from wall.
                            world.setBlock(mutable, lootBlock, 2);
                            LockableLootTileEntity.setLootTable(world, random, mutable, config.chestIdentifier);

                            mutable.move(Direction.DOWN);
                            if(lootBlock.getBlock() == Blocks.SHULKER_BOX){
                                world.setBlock(mutable, Blocks.SPAWNER.defaultBlockState(), 2);
                                TileEntity blockEntity = world.getBlockEntity(mutable);
                                if (blockEntity instanceof MobSpawnerTileEntity) {
                                    SetMobSpawnerEntity(random, config, (MobSpawnerTileEntity) blockEntity);
                                }
                            }
                            else {
                                SolidifyBlock(world, mutable);
                            }

                            break;
                        }
                    }
                }
            }
        }
    }
}
