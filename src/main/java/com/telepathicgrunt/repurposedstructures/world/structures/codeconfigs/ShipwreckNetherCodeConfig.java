package com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.resources.ResourceLocation;

public class ShipwreckNetherCodeConfig {

    public final ResourceLocation startPool;
    public final int sealevelOffset;

    public ShipwreckNetherCodeConfig(ResourceLocation startPool, int sealevelOffset) {
        this.startPool = startPool;
        this.sealevelOffset = sealevelOffset;
        RSStructures.RS_STRUCTURE_START_PIECES.add(startPool);
    }

    public static class Builder<T extends Builder<T>> {
        protected final ResourceLocation startPool;
        protected int sealevelOffset;

        public Builder(ResourceLocation startPool) {
            this.startPool = startPool;
        }

        @SuppressWarnings("unchecked")
        protected T getThis() {
            return (T) this;
        }

        public T setSealevelOffset(int sealevelOffset) {
            this.sealevelOffset = sealevelOffset;
            return getThis();
        }

        public ShipwreckNetherCodeConfig build() {
            return new ShipwreckNetherCodeConfig(
                    startPool,
                    sealevelOffset
            );
        }
    }
}