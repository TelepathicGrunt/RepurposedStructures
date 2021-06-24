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
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.BlockView;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
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

    private static Set<RegistryKey<World>> BLACKLISTED_WORLDS = null;
    public static boolean isWorldBlacklisted(ServerWorldAccess currentWorld){
        if(BLACKLISTED_WORLDS == null){
            BLACKLISTED_WORLDS = new HashSet<>();

            for(ServerWorld serverWorld : currentWorld.toServerWorld().getServer().getWorlds()){
                Identifier worldID = serverWorld.getRegistryKey().getValue();

                // Apply disallow first. (Default behavior is it adds to all dimensions)
                boolean allowInDim = BiomeDimensionAllowDisallow.DIMENSION_DISALLOW.getOrDefault(worldID, new ArrayList<>())
                        .stream().noneMatch(pattern -> pattern.matcher(worldID.toString()).find());

                // Apply allow to override disallow if dimension is targeted in both.
                // Lets disallow to turn off spawn for a group of dimensions while allow can turn it back one for one of them.
                if(!allowInDim && BiomeDimensionAllowDisallow.DIMENSION_ALLOW.getOrDefault(worldID, new ArrayList<>())
                        .stream().anyMatch(pattern -> pattern.matcher(worldID.toString()).find())){
                    allowInDim = true;
                }

                if(!allowInDim){
                    BLACKLISTED_WORLDS.add(RegistryKey.of(Registry.WORLD_KEY, worldID));
                }
            }
        }
        return BLACKLISTED_WORLDS.contains(currentWorld.toServerWorld().getRegistryKey());
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
}