package com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.util.Lazy;

public class MineshaftCodeConfig extends AdvancedJigsawStructureCodeConfig {

    public final Lazy<Double> probability;

    public MineshaftCodeConfig(ResourceLocation poolID, Lazy<Integer> structureSize, int biomeRange,
                               Lazy<Integer> maxY, Lazy<Integer> minY, boolean clipOutOfBoundsPieces,
                               Lazy<Integer> verticalRange, Lazy<Double> probability)
    {
        super(poolID, structureSize, biomeRange, maxY, minY, clipOutOfBoundsPieces, verticalRange);
        this.probability = probability;
    }

    public static class Builder<T extends Builder<T>> extends AdvancedJigsawStructureCodeConfig.Builder<T> {

        protected Lazy<Double> probability = () -> 0.01;

        public Builder(ResourceLocation startPool) {
            super(startPool);
        }

        public T setProbability(double probability) {
            this.probability = () -> probability;
            return getThis();
        }
        public T setProbability(ForgeConfigSpec.DoubleValue probability) {
            this.probability = Lazy.of(probability::get);
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