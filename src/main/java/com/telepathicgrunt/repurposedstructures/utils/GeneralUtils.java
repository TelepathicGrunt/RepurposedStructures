package com.telepathicgrunt.repurposedstructures.utils;

import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.misc.BiomeDimensionAllowDisallow;
import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.material.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class GeneralUtils {
    private GeneralUtils() {}

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

    public static boolean isFullCube(BlockGetter world, BlockPos pos, BlockState state) {
        if(!IS_FULLCUBE_MAP.containsKey(state)) {
            boolean isFullCube = Block.isShapeFullBlock(state.getOcclusionShape(world, pos));
            IS_FULLCUBE_MAP.put(state, isFullCube);
        }
        return IS_FULLCUBE_MAP.get(state);
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

    public static boolean isBlacklistedForWorld(ServerLevelAccessor currentWorld, ResourceLocation worldgenObjectID) {
        ResourceLocation worldID = currentWorld.getLevel().dimension().location();

        // Apply disallow first. (Default behavior is it adds to all dimensions)
        boolean allowInDim = BiomeDimensionAllowDisallow.DIMENSION_DISALLOW.getOrDefault(worldgenObjectID, new ArrayList<>())
                .stream().noneMatch(pattern -> pattern.matcher(worldID.toString()).find());

        // Apply allow to override disallow if dimension is targeted in both.
        // Lets disallow to turn off spawn for a group of dimensions while allow can turn it back one for one of them.
        if(!allowInDim && BiomeDimensionAllowDisallow.DIMENSION_ALLOW.getOrDefault(worldgenObjectID, new ArrayList<>())
                .stream().anyMatch(pattern -> pattern.matcher(worldID.toString()).find())) {
            allowInDim = true;
        }

        return !allowInDim;
    }

    //////////////////////////////

    // Helper method to help reduce amount of code we need to write for adding structures to biomes
    public static void addToBiome(String modificationName, Predicate<BiomeSelectionContext> selectorPredicate, Consumer<BiomeModificationContext> biomeAdditionConsumer) {
        BiomeModifications.create(new ResourceLocation(RepurposedStructures.MODID, modificationName)).add(ModificationPhase.ADDITIONS, selectorPredicate, biomeAdditionConsumer);
    }


    //////////////////////////////

    public static ItemStack enchantRandomly(Random random, ItemStack itemToEnchant, float chance) {
        if(random.nextFloat() < chance) {
            List<Enchantment> list = Registry.ENCHANTMENT.stream().filter(Enchantment::isDiscoverable)
                    .filter((enchantmentToCheck) -> enchantmentToCheck.canEnchant(itemToEnchant)).collect(Collectors.toList());
            if(!list.isEmpty()) {
                Enchantment enchantment = list.get(random.nextInt(list.size()));
                // bias towards weaker enchantments
                int enchantmentLevel = random.nextInt(Mth.nextInt(random, enchantment.getMinLevel(), enchantment.getMaxLevel()) + 1);
                itemToEnchant.enchant(enchantment, enchantmentLevel);
            }
        }

        return itemToEnchant;
    }

    //////////////////////////////

    public static BlockPos getHighestLand(ChunkGenerator chunkGenerator, BoundingBox boundingBox, LevelHeightAccessor heightLimitView, boolean canBeOnLiquid) {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(boundingBox.getCenter().getX(), chunkGenerator.getGenDepth() - 20, boundingBox.getCenter().getZ());
        NoiseColumn blockView = chunkGenerator.getBaseColumn(mutable.getX(), mutable.getZ(), heightLimitView);
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


    public static BlockPos getLowestLand(ChunkGenerator chunkGenerator, BoundingBox boundingBox, LevelHeightAccessor heightLimitView, boolean canBeOnLiquid) {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(boundingBox.getCenter().getX(), chunkGenerator.getSeaLevel() + 1, boundingBox.getCenter().getZ());
        NoiseColumn blockView = chunkGenerator.getBaseColumn(mutable.getX(), mutable.getZ(), heightLimitView);
        BlockState currentBlockstate = blockView.getBlock(mutable.getY());
        while (mutable.getY() <= chunkGenerator.getGenDepth() - 20) {

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
}