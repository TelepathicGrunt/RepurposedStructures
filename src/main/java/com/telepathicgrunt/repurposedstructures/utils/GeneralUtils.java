package com.telepathicgrunt.repurposedstructures.utils;

import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.misc.BiomeDimensionAllowDisallow;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.SectionPos;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.RegistryObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class GeneralUtils {

    // Weighted Random from: https://stackoverflow.com/a/6737362
    public static <T> T getRandomEntry(List<Pair<T, Integer>> rlList, Random random) {
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
    private static final Map<BlockState, Boolean> IS_FULLCUBE_MAP = new HashMap<>();

    public static boolean isFullCube(IWorldReader world, BlockPos pos, BlockState state){
        if(!IS_FULLCUBE_MAP.containsKey(state)){
            boolean isFullCube = Block.isShapeFullBlock(state.getOcclusionShape(world, pos)) || state.getBlock() instanceof LeavesBlock;
            IS_FULLCUBE_MAP.put(state, isFullCube);
        }
        return IS_FULLCUBE_MAP.get(state);
    }

    //////////////////////////////

    // Helper method to make chests always face away from walls
    public static BlockState orientateChest(IServerWorld blockView, BlockPos blockPos, BlockState blockState) {
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        Direction bestDirection = blockState.getValue(HorizontalBlock.FACING);

        for(Direction facing : Direction.Plane.HORIZONTAL) {
            mutable.set(blockPos).move(facing);

            // Checks if wall is in this side
            if (isFullCube(blockView, mutable, blockView.getBlockState(mutable))) {
                bestDirection = facing;

                // Exit early if facing open space opposite of wall
                mutable.move(facing.getOpposite(), 2);
                if(!blockView.getBlockState(mutable).getMaterial().isSolid()){
                    break;
                }
            }
        }

        // Make chest face away from wall
        return blockState.setValue(HorizontalBlock.FACING, bestDirection.getOpposite());
    }

    ///////////////////////////////////////////

    /**
     * Will serialize (if possible) both features and check if they are the same feature.
     * If cannot serialize, compare the feature itself to see if it is the same.
     */
    public static boolean serializeAndCompareFeature(ConfiguredFeature<?, ?> configuredFeature1, ConfiguredFeature<?, ?> configuredFeature2) {

        Optional<JsonElement> configuredFeatureJSON1 = ConfiguredFeature.DIRECT_CODEC.encode(configuredFeature1, JsonOps.INSTANCE, JsonOps.INSTANCE.empty()).get().left();
        Optional<JsonElement> configuredFeatureJSON2 = ConfiguredFeature.DIRECT_CODEC.encode(configuredFeature2, JsonOps.INSTANCE, JsonOps.INSTANCE.empty()).get().left();

        // One of the configuredfeatures cannot be serialized
        if(!configuredFeatureJSON1.isPresent() || !configuredFeatureJSON2.isPresent()) {
            return false;
        }

        // Compare the JSON to see if it's the same ConfiguredFeature in the end.
        return configuredFeatureJSON1.equals(configuredFeatureJSON2);
    }

    //////////////////////////////////////////////

    public static boolean isBlacklistedForWorld(ISeedReader currentWorld, ResourceLocation worldgenObjectID){
        ResourceLocation worldID = currentWorld.getLevel().dimension().location();

        // Apply disallow first. (Default behavior is it adds to all dimensions)
        boolean allowInDim = BiomeDimensionAllowDisallow.DIMENSION_DISALLOW.getOrDefault(worldgenObjectID, new ArrayList<>())
                .stream().noneMatch(pattern -> pattern.matcher(worldID.toString()).find());

        // Apply allow to override disallow if dimension is targeted in both.
        // Lets disallow to turn off spawn for a group of dimensions while allow can turn it back one for one of them.
        if(!allowInDim && BiomeDimensionAllowDisallow.DIMENSION_ALLOW.getOrDefault(worldgenObjectID, new ArrayList<>())
                .stream().anyMatch(pattern -> pattern.matcher(worldID.toString()).find())){
            allowInDim = true;
        }

        return !allowInDim;
    }

    //////////////////////////////////////////////

    public static <T extends IFeatureConfig> void registerStructureDebugging(RegistryObject<Structure<T>> structure){
        MinecraftForge.EVENT_BUS.addListener(
                (PlayerInteractEvent.RightClickBlock event) -> {
                    if(!event.getWorld().isClientSide() && event.getHand() == Hand.MAIN_HAND){
                        RepurposedStructures.LOGGER.info("Started search");

                        ServerWorld serverWorld = (ServerWorld) event.getWorld();
                        ChunkGenerator chunkGenerator = serverWorld.getChunkSource().getGenerator();
                        StructureSeparationSettings structureseparationsettings = chunkGenerator.getSettings().getConfig(RSStructures.STRONGHOLD_NETHER.get());
                        Structure<?> structureToFind = structure.get();
                        List<Pair<BlockPos, Integer>> structureStarts = new ArrayList<>();

                        int spacing = structureseparationsettings.spacing();
                        int startX = 0;
                        int startZ = 0;
                        int currentRadius = 0;
                        int maxRadius = 20;

                        for(SharedSeedRandom sharedseedrandom = new SharedSeedRandom(); currentRadius <= maxRadius; ++currentRadius) {
                            for(int xRadius = -currentRadius; xRadius <= currentRadius; ++xRadius) {
                                boolean onXEdge = xRadius == -currentRadius || xRadius == currentRadius;

                                for(int zRadius = -currentRadius; zRadius <= currentRadius; ++zRadius) {
                                    boolean onZEdge = zRadius == -currentRadius || zRadius == currentRadius;
                                    if (onXEdge || onZEdge) {
                                        int k1 = startX + spacing * xRadius;
                                        int l1 = startZ + spacing * zRadius;
                                        ChunkPos chunkpos = structureToFind.getPotentialFeatureChunk(structureseparationsettings, serverWorld.getSeed(), sharedseedrandom, k1, l1);
                                        IChunk ichunk = serverWorld.getChunk(chunkpos.x, chunkpos.z, ChunkStatus.STRUCTURE_STARTS);
                                        StructureStart<?> structurestart = serverWorld.structureFeatureManager().getStartForFeature(SectionPos.of(ichunk.getPos(), 0), structureToFind, ichunk);
                                        if (structurestart != null && structurestart.isValid()) {
                                            BlockPos pos = structurestart.getLocatePos();
                                            structureStarts.add(Pair.of(pos, (int)Math.sqrt((pos.getX() * pos.getX()) + (pos.getZ() * pos.getZ()))));
                                        }

                                        if (currentRadius == 0) {
                                            break;
                                        }
                                    }
                                    else{
                                        zRadius = currentRadius - 1;
                                    }
                                }

                                if (currentRadius == 0) {
                                    break;
                                }
                            }
                        }

                        structureStarts.sort(Comparator.comparingInt(Pair::getSecond));
                        structureStarts.forEach(pair -> RepurposedStructures.LOGGER.info(
                                "position: {} - distance: {}", pair.getFirst(), pair.getSecond()
                        ));

                        boolean breakpointHere = true;
                    }
                }
        );
    }

    //////////////////////////////

    public static ItemStack enchantRandomly(Random random, ItemStack itemToEnchant, float chance) {
        if(random.nextFloat() < chance){
            List<Enchantment> list = Registry.ENCHANTMENT.stream().filter(Enchantment::isDiscoverable)
                    .filter((enchantmentToCheck) -> enchantmentToCheck.canEnchant(itemToEnchant)).collect(Collectors.toList());
            if(!list.isEmpty()){
                Enchantment enchantment = list.get(random.nextInt(list.size()));
                // bias towards weaker enchantments
                int enchantmentLevel = random.nextInt(MathHelper.nextInt(random, enchantment.getMinLevel(), enchantment.getMaxLevel()) + 1);
                itemToEnchant.enchant(enchantment, enchantmentLevel);
            }
        }

        return itemToEnchant;
    }
    //////////////////////////////

    public static BlockPos getHighestLand(ChunkGenerator chunkGenerator, MutableBoundingBox boundingBox, boolean canBeOnLiquid) {
        BlockPos.Mutable mutable = new BlockPos.Mutable().set(
                boundingBox.getCenter().getX(),
                chunkGenerator.getGenDepth() - 20,
                boundingBox.getCenter().getZ());

        IBlockReader blockView = chunkGenerator.getBaseColumn(mutable.getX(), mutable.getZ());
        BlockState currentBlockstate;
        while (mutable.getY() > chunkGenerator.getSeaLevel()) {
            currentBlockstate = blockView.getBlockState(mutable);
            if (!currentBlockstate.canOcclude()) {
                mutable.move(Direction.DOWN);
                continue;
            }
            else if (blockView.getBlockState(mutable.offset(0, 3, 0)).getMaterial() == Material.AIR && (canBeOnLiquid ? !currentBlockstate.isAir() : currentBlockstate.canOcclude())) {
                return mutable;
            }
            mutable.move(Direction.DOWN);
        }

        return mutable;
    }


    public static BlockPos getLowestLand(ChunkGenerator chunkGenerator, MutableBoundingBox boundingBox, boolean canBeOnLiquid){
        BlockPos.Mutable mutable = new BlockPos.Mutable().set(
                boundingBox.getCenter().getX(),
                chunkGenerator.getSeaLevel() + 1,
                boundingBox.getCenter().getZ());

        IBlockReader blockView = chunkGenerator.getBaseColumn(mutable.getX(), mutable.getZ());
        BlockState currentBlockstate = blockView.getBlockState(mutable);
        while (mutable.getY() <= chunkGenerator.getGenDepth() - 20) {

            if((canBeOnLiquid ? !currentBlockstate.isAir() : currentBlockstate.canOcclude()) &&
                    blockView.getBlockState(mutable.above()).getMaterial() == Material.AIR &&
                    blockView.getBlockState(mutable.above(5)).getMaterial() == Material.AIR)
            {
                mutable.move(Direction.UP);
                return mutable;
            }

            mutable.move(Direction.UP);
            currentBlockstate = blockView.getBlockState(mutable);
        }

        return mutable.set(mutable.getX(), chunkGenerator.getSeaLevel(), mutable.getZ());
    }

    //////////////////////////////////////////////

    public static void centerAllPieces(BlockPos targetPos, List<StructurePiece> pieces){
        if(pieces.isEmpty()) return;

        Vector3i structureCenter = pieces.get(0).getBoundingBox().getCenter();
        int xOffset = targetPos.getX() - structureCenter.getX();
        int zOffset = targetPos.getZ() - structureCenter.getZ();

        for(StructurePiece structurePiece : pieces){
            structurePiece.move(xOffset, 0, zOffset);
        }
    }
}