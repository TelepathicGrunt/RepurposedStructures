package com.telepathicgrunt.repurposedstructures.world.features;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.TemplateInvoker;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.features.configs.NbtDungeonConfig;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.FluidState;
import net.minecraft.inventory.IClearable;
import net.minecraft.nbt.CompoundNBT;
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
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.shapes.BitSetVoxelShapePart;
import net.minecraft.util.math.shapes.VoxelShapePart;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.template.*;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class NbtDungeon extends Feature<NbtDungeonConfig>{

    public NbtDungeon(Codec<NbtDungeonConfig> configFactory) {
        super(configFactory);
    }

    @Override
    public boolean generate(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos position, NbtDungeonConfig config) {

        ResourceLocation nbtRL = GeneralUtils.getRandomEntry(config.nbtResourcelocationsAndWeights, random);

        TemplateManager structureManager = world.getWorld().getStructureTemplateManager();
        Template template = structureManager.getTemplate(nbtRL);
        if(template == null){
            RepurposedStructures.LOGGER.error("Identifier to the specified nbt file was not found! : {}", nbtRL);
            return false;
        }
        Rotation rotation = Rotation.randomRotation(random);

        // Rotated blockpos for the nbt's sizes to be used later. Ignore Y
        BlockPos fullLengths = new BlockPos(
                Math.abs(template.getSize().rotate(rotation).getX()),
                Math.abs(template.getSize().rotate(rotation).getY()),
                Math.abs(template.getSize().rotate(rotation).getZ()));

        BlockPos halfLengths = new BlockPos(
                fullLengths.getX() / 2,
                fullLengths.getY() / 2,
                fullLengths.getZ() / 2);

        BlockPos.Mutable mutable = new BlockPos.Mutable().setPos(position);
        IChunk cachedChunk = world.getChunk(mutable);

        int xMin = -halfLengths.getX();
        int xMax = halfLengths.getX();
        int zMin = -halfLengths.getZ();
        int zMax = halfLengths.getZ();
        int wallOpenings = 0;
        int ceilingOpenings = 0;
        int ceiling = template.getSize().getY();

        for (int x = xMin; x <= xMax; ++x) {
            for (int z = zMin; z <= zMax; ++z) {
                for (int y = 0; y <= ceiling; ++y) {
                    mutable.setPos(position).move(x, y, z);
                    if(mutable.getX() >> 4 != cachedChunk.getPos().x || mutable.getZ() >> 4 != cachedChunk.getPos().z)
                        cachedChunk = world.getChunk(mutable);

                    BlockState state = cachedChunk.getBlockState(mutable);

                    // Dungeons cannot touch fluids if set to air mode and reverse if opposite
                    if(config.airRequirementIsNowWater ? state.isAir() || state.getFluidState().isTagged(FluidTags.LAVA) : !state.getFluidState().isEmpty()){
                        return false;
                    }
                    // Floor must be complete
                    else if(!GeneralUtils.isFullCube(world, mutable, state)){
                        if (y == 0 && !state.getMaterial().isSolid()) {
                            return false;
                        }
                        else if(state.isIn(BlockTags.LEAVES)){
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
            position = position.up(config.structureYOffset);

            //UltraAmplifiedDimension.LOGGER.log(Level.INFO, nbtRL + " at X: "+position.getX() +", "+position.getY()+", "+position.getZ());
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(rotation).setCenterOffset(halfLengths).setIgnoreEntities(false);
            Optional<StructureProcessorList> processor = world.getWorld().getServer().getRegistryManager().get(Registry.PROCESSOR_LIST_WORLDGEN).getOrEmpty(config.processor);
            processor.orElse(ProcessorLists.EMPTY).getList().forEach(placementsettings::addProcessor); // add all processors
            addBlocksToWorld(template, world, chunkGenerator, mutable.setPos(position).move(-halfLengths.getX(), 0, -halfLengths.getZ()), placementsettings, 2, random, config);
            spawnLootBlocks(world, random, position, config, fullLengths, halfLengths, mutable);
            return true;
        }

        return false;
    }

    private void spawnLootBlocks(ISeedReader world, Random random, BlockPos position, NbtDungeonConfig config, BlockPos fullLengths, BlockPos halfLengths, BlockPos.Mutable mutable) {
        boolean isPlacingChestLikeBlock = config.lootBlock.getBlock() instanceof ChestBlock;

        // Add chests that are wall based
        for(int currentChestCount = 0; currentChestCount < config.maxNumOfChests; ++currentChestCount) {
            for (int currentChestAttempt = 0; currentChestAttempt < fullLengths.getX() + fullLengths.getZ() + halfLengths.getY(); ++currentChestAttempt) {
                if (currentChestCount == config.maxNumOfChests) {
                    return; // early exit
                }

                mutable.setPos(position).move(
                        random.nextInt(Math.max(fullLengths.getX() - 2, 1)) - halfLengths.getX() + 1,
                        random.nextInt(Math.max(fullLengths.getY() - 1, 1)),
                        random.nextInt(Math.max(fullLengths.getZ() - 2, 1)) - halfLengths.getZ() + 1);

                BlockState currentBlock = world.getBlockState(mutable);
                if (isValidNonSolidBlock(config, currentBlock)) {
                    if(world.getBlockState(mutable.move(Direction.DOWN)).isSideSolidFullSquare(world, mutable, Direction.UP)){
                        mutable.move(Direction.UP);
                        boolean isOnWall = false;

                        for(Direction neighborDirection : Direction.Plane.HORIZONTAL){

                            mutable.move(neighborDirection);
                            BlockState neighboringState = world.getBlockState(mutable);
                            mutable.move(neighborDirection.getOpposite());

                            if(isPlacingChestLikeBlock && neighboringState.getBlock() instanceof ChestBlock){
                                // Only connect to single chests
                                if(neighboringState.get(ChestBlock.TYPE) == ChestType.SINGLE){

                                    BlockState currentStateForChest = GeneralUtils.orientateChest(world, mutable, config.lootBlock);
                                    Direction currentDirection = currentStateForChest.get(HorizontalBlock.HORIZONTAL_FACING);

                                    // If oriented is on same axis as neighboring chest, find a new direction on sides.
                                    if(neighborDirection.getAxis() == currentDirection.getAxis()){
                                        currentDirection = currentDirection.rotateY();
                                        BlockPos wallCheckPos = mutable.offset(currentDirection);
                                        BlockPos wallCheckPos2 = wallCheckPos.offset(neighborDirection);
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
                                    world.setBlockState(mutable,
                                            config.lootBlock
                                                    .with(ChestBlock.WATERLOGGED, currentBlock.getFluidState().isTagged(FluidTags.WATER))
                                                    .with(ChestBlock.FACING, currentDirection)
                                                    .with(ChestBlock.TYPE, chestTyping ? ChestType.RIGHT : ChestType.LEFT),
                                            2);

                                    // Set neighboring chest to face same way too
                                    world.setBlockState(mutable.move(neighborDirection),
                                            neighboringState
                                                    .with(ChestBlock.FACING, currentDirection)
                                                    .with(ChestBlock.TYPE, chestTyping ? ChestType.LEFT : ChestType.RIGHT),
                                            2);

                                    LockableLootTileEntity.setLootTable(world, random, mutable, config.chestResourceLocation);
                                    SolidifyBlock(world, mutable.down());

                                    currentChestCount++;
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
                            if(lootBlock.contains(BlockStateProperties.WATERLOGGED)){
                                lootBlock.with(BlockStateProperties.WATERLOGGED, currentBlock.getFluidState().isTagged(FluidTags.WATER));
                            }
                            if(lootBlock.contains(BlockStateProperties.HORIZONTAL_FACING)){
                                lootBlock = GeneralUtils.orientateChest(world, mutable, lootBlock);
                            }

                            // Set chest to face away from wall.
                            world.setBlockState(mutable, lootBlock, 2);
                            LockableLootTileEntity.setLootTable(world, random, mutable, config.chestResourceLocation);

                            mutable.move(Direction.DOWN);
                            if(lootBlock.getBlock() == Blocks.SHULKER_BOX){
                                world.setBlockState(mutable, Blocks.SPAWNER.getDefaultState(), 2);
                                TileEntity blockEntity = world.getTileEntity(mutable);
                                if (blockEntity instanceof MobSpawnerTileEntity) {
                                    SetMobSpawnerEntity(random, config, (MobSpawnerTileEntity) blockEntity);
                                }
                            }
                            else {
                                SolidifyBlock(world, mutable);
                            }

                            currentChestCount++;
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Adds blocks and entities from this structure to the given world.
     */
    public void addBlocksToWorld(Template structure, ISeedReader world, ChunkGenerator chunkGenerator, BlockPos pos, PlacementSettings placementIn, int flags, Random random, NbtDungeonConfig config) {
        TemplateInvoker structureAccessor = ((TemplateInvoker) structure);
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        if (!structureAccessor.rs_getBlocks().isEmpty()) {
            List<Template.BlockInfo> list = placementIn.getRandomBlockInfos(structureAccessor.rs_getBlocks(), pos).getAll();
            if ((!list.isEmpty() || !placementIn.getIgnoreEntities() && !structureAccessor.rs_getEntities().isEmpty()) && structureAccessor.rs_getSize().getX() >= 1 && structureAccessor.rs_getSize().getY() >= 1 && structureAccessor.rs_getSize().getZ() >= 1) {
                MutableBoundingBox mutableboundingbox = placementIn.getBoundingBox();
                List<BlockPos> waterloggablePositions = Lists.newArrayListWithCapacity(placementIn.func_204763_l() ? list.size() : 0);
                List<Pair<BlockPos, CompoundNBT>> list2 = Lists.newArrayListWithCapacity(list.size());
                int minX = Integer.MAX_VALUE;
                int minY = Integer.MAX_VALUE;
                int minZ = Integer.MAX_VALUE;
                int maxX = Integer.MIN_VALUE;
                int maxY = Integer.MIN_VALUE;
                int maxZ = Integer.MIN_VALUE;

                for (Template.BlockInfo template$blockinfo : Template.processBlockInfos(world, pos, pos, placementIn, list, structure)) {
                    BlockPos blockpos = template$blockinfo.pos;

                    if (mutableboundingbox == null || mutableboundingbox.isVecInside(blockpos)) {
                        FluidState ifluidstate = placementIn.func_204763_l() ? world.getFluidState(blockpos) : null;
                        BlockState blockstate = template$blockinfo.state.mirror(placementIn.getMirror()).rotate(world, template$blockinfo.pos, placementIn.getRotation());
                        if (template$blockinfo.nbt != null) {
                            TileEntity blockentity = world.getTileEntity(blockpos);
                            IClearable.clearObj(blockentity);
                            world.setBlockState(blockpos, Blocks.BARRIER.getDefaultState(), 3);
                        }

                        BlockState originalBlockState = world.getBlockState(blockpos);
                        if (!originalBlockState.hasTileEntity())
                        {
                            // No floating chests or spawners
                            BlockState aboveState = world.getBlockState(mutable.setPos(blockpos).move(Direction.UP));

                            boolean forcePlaceBlock = false;
                            if(config.blocksToAlwaysPlace.isPresent()){
                                forcePlaceBlock = config.blocksToAlwaysPlace.get().contains(blockstate);
                            }

                            if(isNotSpawnerOrChest(aboveState) &&
                                    (config.replaceAir ||
                                            forcePlaceBlock ||
                                            GeneralUtils.isFullCube(world, mutable, originalBlockState) ||
                                            blockstate.hasTileEntity()))
                            {
                                // Attempt to let leaves stay in the dungeon space and not be cut off
                                if(!(isValidNonSolidBlock(config, blockstate) && originalBlockState.isIn(BlockTags.LEAVES))){
                                    world.setBlockState(blockpos, blockstate, 3);

                                    minX = Math.min(minX, blockpos.getX());
                                    minY = Math.min(minY, blockpos.getY());
                                    minZ = Math.min(minZ, blockpos.getZ());
                                    maxX = Math.max(maxX, blockpos.getX());
                                    maxY = Math.max(maxY, blockpos.getY());
                                    maxZ = Math.max(maxZ, blockpos.getZ());
                                    list2.add(Pair.of(blockpos, template$blockinfo.nbt));

                                    if (template$blockinfo.nbt != null){
                                        setBlockEntity(world, placementIn, random, config, template$blockinfo, blockpos, blockstate);
                                    }

                                    if (ifluidstate != null && blockstate.getBlock() instanceof ILiquidContainer) {
                                        ((ILiquidContainer) blockstate.getBlock()).receiveFluid(world, blockpos, blockstate, ifluidstate);
                                        if (!ifluidstate.isSource()) {
                                            waterloggablePositions.add(blockpos);
                                        }
                                    }
                                }
                            }

                            // Prevent plants remaining at edge of dungeons like bamboo which then breaks as dungeon floor isn't valid for bamboo.
                            else if(!isValidNonSolidBlock(config, blockstate) && !originalBlockState.isIn(BlockTags.LEAVES) && !originalBlockState.isSolid() && originalBlockState.getFluidState().isEmpty()){
                                world.setBlockState(blockpos, config.airRequirementIsNowWater ? Blocks.WATER.getDefaultState() : Blocks.AIR.getDefaultState(), 3);

                                BlockPos.Mutable mutable1 = new BlockPos.Mutable().setPos(blockpos);
                                BlockState blockState = world.getBlockState(mutable1.move(Direction.UP));

                                while(mutable1.getY() < chunkGenerator.getMaxY() && !blockState.isValidPosition(world, mutable1)){
                                    world.setBlockState(mutable1, Blocks.AIR.getDefaultState(), 3);
                                    blockState = world.getBlockState(mutable1.move(Direction.UP));
                                }
                            }
                        }
                    }
                }

                fillFluidStates(world, waterloggablePositions);

                if (minX <= maxX) {
                    if (!placementIn.func_215218_i()) {
                        VoxelShapePart voxelshapepart = new BitSetVoxelShapePart(maxX - minX + 1, maxY - minY + 1, maxZ - minZ + 1);
                        setVoxelShapeParts(world, flags, list2, minX, minY, minZ, voxelshapepart);
                    }

                    placeBlocks(world, placementIn, list2);
                }

                if (!placementIn.getIgnoreEntities()) {
                    structureAccessor.rs_invokeSpawnEntities(world, pos, placementIn);
                }
            }
        }
    }

    private void setBlockEntity(IServerWorld world, PlacementSettings placementIn, Random random, NbtDungeonConfig config, Template.BlockInfo template$blockinfo, BlockPos blockpos, BlockState blockstate) {
        TileEntity blockEntity = world.getTileEntity(blockpos);
        if (blockEntity != null) {
            template$blockinfo.nbt.putInt("x", blockpos.getX());
            template$blockinfo.nbt.putInt("y", blockpos.getY());
            template$blockinfo.nbt.putInt("z", blockpos.getZ());

            if (blockEntity instanceof MobSpawnerTileEntity) {
                // Remove spawn potentials or else the spawner reverts back to default mob
                template$blockinfo.nbt.remove("SpawnPotentials");

                blockEntity.fromTag(template$blockinfo.state, template$blockinfo.nbt);
                blockEntity.mirror(placementIn.getMirror());
                blockEntity.rotate(placementIn.getRotation());
                SetMobSpawnerEntity(random, config, (MobSpawnerTileEntity) blockEntity);
                return;
            }

            blockEntity.fromTag(template$blockinfo.state, template$blockinfo.nbt);
            blockEntity.mirror(placementIn.getMirror());
            blockEntity.rotate(placementIn.getRotation());
            if(blockEntity instanceof LockableLootTileEntity){
                if(blockstate.isIn(Blocks.CHEST)){
                    world.setBlockState(blockpos, GeneralUtils.orientateChest(world, blockpos, Blocks.CHEST.getDefaultState()), 2);
                }
                LockableLootTileEntity.setLootTable(world, random, blockpos, config.chestResourceLocation);
            }
        }
    }

    private void fillFluidStates(IServerWorld world, List<BlockPos> blockPosList) {
        boolean flag = true;
        while (flag && !blockPosList.isEmpty()) {
            flag = false;
            Iterator<BlockPos> iterator = blockPosList.iterator();

            while (iterator.hasNext()) {
                BlockPos blockpos2 = iterator.next();
                BlockPos blockpos3 = blockpos2;
                FluidState ifluidstate2 = world.getFluidState(blockpos2);

                for (int directionIndex = 1; directionIndex < 6 && !ifluidstate2.isSource(); ++directionIndex) {
                    // Skip down direction
                    Direction direction = Direction.byIndex(directionIndex);
                    BlockPos blockpos1 = blockpos3.offset(direction);
                    FluidState ifluidstate1 = world.getFluidState(blockpos1);
                    if (ifluidstate1.getActualHeight(world, blockpos1) > ifluidstate2.getActualHeight(world, blockpos3) ||
                            ifluidstate1.isSource() && !ifluidstate2.isSource())
                    {
                        ifluidstate2 = ifluidstate1;
                        blockpos3 = blockpos1;
                    }
                }

                if (ifluidstate2.isSource()) {
                    BlockState blockstate2 = world.getBlockState(blockpos2);
                    Block block = blockstate2.getBlock();
                    if (block instanceof ILiquidContainer) {
                        ((ILiquidContainer) block).receiveFluid(world, blockpos2, blockstate2, ifluidstate2);
                        flag = true;
                        iterator.remove();
                    }
                }
            }
        }
    }

    private boolean isNotSpawnerOrChest(BlockState state){
        return !state.isIn(Blocks.SPAWNER) && !(state.getBlock() instanceof AbstractChestBlock);
    }

    protected static void placeBlocks(IServerWorld world, PlacementSettings placementIn, List<Pair<BlockPos, CompoundNBT>> list2) {
        for (Pair<BlockPos, CompoundNBT> pair : list2) {
            BlockPos blockpos4 = pair.getFirst();

            if (!placementIn.func_215218_i()) {
                BlockState blockstate1 = world.getBlockState(blockpos4);
                BlockState blockstate3 = Block.getValidBlockForPosition(blockstate1, world, blockpos4);
                if (blockstate1 != blockstate3) {
                    world.setBlockState(blockpos4, blockstate3, 3);
                }

                world.updateNeighbors(blockpos4, blockstate3.getBlock());
            }

            if (pair.getSecond() != null) {
                TileEntity blockentity2 = world.getTileEntity(blockpos4);
                if (blockentity2 != null) {
                    blockentity2.markDirty();
                }
            }
        }
    }

    protected static void setVoxelShapeParts(IServerWorld world, int flags, List<Pair<BlockPos, CompoundNBT>> list2, int x, int y, int z, VoxelShapePart voxelshapepart) {
        for (Pair<BlockPos, CompoundNBT> pair1 : list2) {
            BlockPos blockpos5 = pair1.getFirst();
            voxelshapepart.setFilled(blockpos5.getX() - x, blockpos5.getY() - y, blockpos5.getZ() - z, true, true);
        }

        Template.func_222857_a(world, flags, voxelshapepart, x, y, z);
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
            blockEntity.getSpawnerBaseLogic().setEntityType(entity);
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
        if (blockBelow.contains(SlabBlock.TYPE)) {
            world.setBlockState(pos, blockBelow.with(SlabBlock.TYPE, SlabType.DOUBLE), 3);
        }
    }

}
