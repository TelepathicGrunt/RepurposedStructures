package com.telepathicgrunt.repurposedstructures.utils;

import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

    public static boolean isFullCube(StructureWorldAccess world, BlockPos pos, BlockState state){
        if(!IS_FULLCUBE_MAP.containsKey(state)){
            boolean isFullCube = Block.isShapeFullCube(state.getCullingShape(world, pos));
            IS_FULLCUBE_MAP.put(state, isFullCube);
        }
        return IS_FULLCUBE_MAP.get(state);
    }
}