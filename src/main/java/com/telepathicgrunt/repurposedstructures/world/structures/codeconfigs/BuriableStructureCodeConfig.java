package com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.resources.ResourceLocation;

public class BuriableStructureCodeConfig {

    public final ResourceLocation startPool;
    public final int offsetAmount;
    public final boolean onLand;
    public final boolean cannotSpawnInWater;

    public BuriableStructureCodeConfig(ResourceLocation startPool, int offsetAmount, boolean onLand, boolean cannotSpawnInWater) {
        this.startPool = startPool;
        this.offsetAmount = offsetAmount;
        this.onLand = onLand;
        this.cannotSpawnInWater = cannotSpawnInWater;
    }

    public static class Builder<T extends Builder<T>> {
        private final ResourceLocation startPool;
        private int offsetAmount = 14;
        private boolean onLand = true;
        private boolean cannotSpawnInWater = true;

        public Builder(ResourceLocation startPool) {
            this.startPool = startPool;
        }

        @SuppressWarnings("unchecked")
        protected T getThis() {
            return (T) this;
        }

        public T setOffsetAmount(int offsetAmount) {
            this.offsetAmount = offsetAmount;
            return getThis();
        }

        public T useOceanHeightmap() {
            this.onLand = false;
            return getThis();
        }

        public T cannotSpawnInWater() {
            this.cannotSpawnInWater = false;
            return getThis();
        }

        public BuriableStructureCodeConfig build() {
            return new BuriableStructureCodeConfig(
                    startPool,
                    offsetAmount,
                    onLand,
                    cannotSpawnInWater
            );
        }
    }
}