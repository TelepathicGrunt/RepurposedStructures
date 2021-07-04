package com.telepathicgrunt.repurposedstructures.utils;

import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.misc.BiomeDimensionAllowDisallow;
import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.VerticalBlockSample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;
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

    public static boolean isFullCube(BlockView world, BlockPos pos, BlockState state){
        if(!IS_FULLCUBE_MAP.containsKey(state)){
            boolean isFullCube = Block.isShapeFullCube(state.getCullingShape(world, pos));
            IS_FULLCUBE_MAP.put(state, isFullCube);
        }
        return IS_FULLCUBE_MAP.get(state);
    }

    //////////////////////////////

    // Helper method to make chests always face away from walls
    public static BlockState orientateChest(ServerWorldAccess blockView, BlockPos blockPos, BlockState blockState) {
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        Direction wallDirection = blockState.get(HorizontalFacingBlock.FACING);

        for(Direction facing : Direction.Type.HORIZONTAL) {
            mutable.set(blockPos).move(facing);

            // Checks if wall is in this side
            if (isFullCube(blockView, mutable, blockView.getBlockState(mutable))) {
                wallDirection = facing;

                // Exit early if facing open space opposite of wall
                mutable.move(facing.getOpposite(), 2);
                if(!blockView.getBlockState(mutable).getMaterial().isSolid()){
                    break;
                }
            }
        }

        // Make chest face away from wall
        return blockState.with(HorizontalFacingBlock.FACING, wallDirection.getOpposite());
    }

    //////////////////////////////////////////////

    public static boolean isBlacklistedForWorld(ServerWorldAccess currentWorld, Identifier worldgenObjectID){
        Identifier worldID = currentWorld.toServerWorld().getRegistryKey().getValue();

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

    //////////////////////////////

    // Helper method to help reduce amount of code we need to write for adding structures to biomes
    @SuppressWarnings("deprecation")
    public static void addToBiome(String modificationName, Predicate<BiomeSelectionContext> selectorPredicate, Consumer<BiomeModificationContext> biomeAdditionConsumer) {
        BiomeModifications.create(new Identifier(RepurposedStructures.MODID, modificationName)).add(ModificationPhase.ADDITIONS, selectorPredicate, biomeAdditionConsumer);
    }


    //////////////////////////////

    public static ItemStack enchantRandomly(Random random, ItemStack itemToEnchant, float chance) {
        if(random.nextFloat() < chance){
            List<Enchantment> list = Registry.ENCHANTMENT.stream().filter(Enchantment::isAvailableForRandomSelection)
                    .filter((enchantmentToCheck) -> enchantmentToCheck.isAcceptableItem(itemToEnchant)).collect(Collectors.toList());
            if(!list.isEmpty()){
                Enchantment enchantment = list.get(random.nextInt(list.size()));
                // bias towards weaker enchantments
                int enchantmentLevel = random.nextInt(MathHelper.nextInt(random, enchantment.getMinLevel(), enchantment.getMaxLevel()) + 1);
                itemToEnchant.addEnchantment(enchantment, enchantmentLevel);
            }
        }

        return itemToEnchant;
    }

    //////////////////////////////

    public static BlockPos getHighestLand(ChunkGenerator chunkGenerator, BlockBox boundingBox, HeightLimitView heightLimitView, boolean canBeOnLiquid) {
        BlockPos.Mutable mutable = new BlockPos.Mutable().set(boundingBox.getCenter().getX(), chunkGenerator.getWorldHeight() - 20, boundingBox.getCenter().getZ());
        VerticalBlockSample blockView = chunkGenerator.getColumnSample(mutable.getX(), mutable.getZ(), heightLimitView);
        BlockState currentBlockstate;
        while (mutable.getY() > chunkGenerator.getSeaLevel()) {
            currentBlockstate = blockView.getState(mutable);
            if (!currentBlockstate.isOpaque()) {
                mutable.move(Direction.DOWN);
                continue;
            }
            else if (blockView.getState(mutable.add(0, 3, 0)).getMaterial() == Material.AIR && (canBeOnLiquid ? !currentBlockstate.isAir() : currentBlockstate.isOpaque())) {
                return mutable;
            }
            mutable.move(Direction.DOWN);
        }

        return mutable;
    }


    public static BlockPos getLowestLand(ChunkGenerator chunkGenerator, BlockBox boundingBox, HeightLimitView heightLimitView, boolean canBeOnLiquid){
        BlockPos.Mutable mutable = new BlockPos.Mutable().set(boundingBox.getCenter().getX(), chunkGenerator.getSeaLevel() + 1, boundingBox.getCenter().getZ());
        VerticalBlockSample blockView = chunkGenerator.getColumnSample(mutable.getX(), mutable.getZ(), heightLimitView);
        BlockState currentBlockstate = blockView.getState(mutable);
        while (mutable.getY() <= chunkGenerator.getWorldHeight() - 20) {

            if((canBeOnLiquid ? !currentBlockstate.isAir() : currentBlockstate.isOpaque()) &&
                    blockView.getState(mutable.up()).getMaterial() == Material.AIR &&
                    blockView.getState(mutable.up(5)).getMaterial() == Material.AIR)
            {
                mutable.move(Direction.UP);
                return mutable;
            }

            mutable.move(Direction.UP);
            currentBlockstate = blockView.getState(mutable);
        }

        return mutable.set(mutable.getX(), chunkGenerator.getSeaLevel(), mutable.getZ());
    }
}