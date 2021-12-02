package com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.resources.ResourceLocation;

public class StartPoolOnlyCodeConfig {

    public final ResourceLocation startPool;

    public StartPoolOnlyCodeConfig(ResourceLocation poolID) {
        this.startPool = poolID;
        RSStructures.RS_STRUCTURE_START_PIECES.add(startPool);
    }

    public static class Builder<T extends Builder<T>> {
        protected final ResourceLocation startPool;

        public Builder(ResourceLocation startPool) {
            this.startPool = startPool;
        }

        @SuppressWarnings("unchecked")
        protected T getThis() {
            return (T) this;
        }

        public StartPoolOnlyCodeConfig build() {
            return new StartPoolOnlyCodeConfig(
                    startPool
            );
        }
    }
}