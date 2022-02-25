package com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.resources.ResourceLocation;

public class AdvancedJigsawStructureCodeConfig {

    public final ResourceLocation startPool;
    public final int structureSize;
    public final int biomeRange;
    public final int maxY;
    public final int minY;
    public final boolean clipOutOfBoundsPieces;
    public final Integer verticalRange;

    public AdvancedJigsawStructureCodeConfig(ResourceLocation poolID, int structureSize, int biomeRange,
                                             int maxY, int minY, boolean clipOutOfBoundsPieces, Integer verticalRange) {
        this.startPool = poolID;
        this.structureSize = structureSize;
        this.biomeRange = biomeRange;
        this.maxY = maxY;
        this.minY = minY;
        this.clipOutOfBoundsPieces = clipOutOfBoundsPieces;
        this.verticalRange = verticalRange;
    }

    public static class Builder<T extends Builder<T>> {
        protected final ResourceLocation startPool;
        protected int structureSize = 1;
        protected int biomeRange = 0;
        protected int maxY = 255;
        protected int minY = 0;
        protected boolean clipOutOfBoundsPieces = true;
        protected Integer verticalRange = null;

        public Builder(ResourceLocation startPool) {
            this.startPool = startPool;
        }

        @SuppressWarnings("unchecked")
        protected T getThis() {
            return (T) this;
        }

        public T setStructureSize(int structureSize) {
            this.structureSize = structureSize;
            return getThis();
        }

        public T setBiomeRange(int biomeRange) {
            this.biomeRange = biomeRange;
            return getThis();
        }

        public T setMaxY(int maxY) {
            this.maxY = maxY;
            return getThis();
        }

        public T setMinY(int minY) {
            this.minY = minY;
            return getThis();
        }

        public T setVerticalRange(int verticalRange) {
            this.verticalRange = verticalRange;
            return getThis();
        }

        public T doNotClipOutOfBoundsPieces() {
            this.clipOutOfBoundsPieces = false;
            return getThis();
        }

        public AdvancedJigsawStructureCodeConfig build() {
            return new AdvancedJigsawStructureCodeConfig(
                    startPool,
                    structureSize,
                    biomeRange,
                    maxY,
                    minY,
                    clipOutOfBoundsPieces,
                    verticalRange
            );
        }
    }
}