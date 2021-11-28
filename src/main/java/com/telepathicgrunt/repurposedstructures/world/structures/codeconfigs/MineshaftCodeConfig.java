package com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs;

import net.minecraft.resources.ResourceLocation;

public class MineshaftCodeConfig extends AdvancedJigsawStructureCodeConfig {

    public final double probability;

    public MineshaftCodeConfig(ResourceLocation poolID, int structureSize, int biomeRange,
                              int maxY, int minY, boolean clipOutOfBoundsPieces,
                              Integer verticalRange, double probability)
    {
        super(poolID, structureSize, biomeRange, maxY, minY, clipOutOfBoundsPieces, verticalRange);
        this.probability = probability;
    }

    public static class Builder<T extends Builder<T>> extends AdvancedJigsawStructureCodeConfig.Builder<T> {

        protected double probability = 0.01;

        public Builder(ResourceLocation startPool) {
            super(startPool);
        }

        public T setProbability(double probability) {
            this.probability = probability;
            return getThis();
        }

        public MineshaftCodeConfig build() {
            return new MineshaftCodeConfig(
                    startPool,
                    structureSize,
                    biomeRange,
                    maxY,
                    minY,
                    clipOutOfBoundsPieces,
                    verticalRange,
                    probability);
        }
    }
}