package com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.util.Lazy;

public class AdvancedJigsawStructureCodeConfig {

    public final ResourceLocation startPool;
    public final Lazy<Integer> structureSize;
    public final int biomeRange;
    public final Lazy<Integer> maxY;
    public final Lazy<Integer> minY;
    public final boolean clipOutOfBoundsPieces;
    public final Lazy<Integer> verticalRange;

    public AdvancedJigsawStructureCodeConfig(ResourceLocation poolID, Lazy<Integer> structureSize, int biomeRange,
                                             Lazy<Integer> maxY, Lazy<Integer> minY, boolean clipOutOfBoundsPieces,
                                             Lazy<Integer> verticalRange) {
        this.startPool = poolID;
        this.structureSize = structureSize;
        this.biomeRange = biomeRange;
        this.maxY = maxY;
        this.minY = minY;
        this.clipOutOfBoundsPieces = clipOutOfBoundsPieces;
        this.verticalRange = verticalRange;

        RSStructures.RS_STRUCTURE_START_PIECES.add(startPool);
    }

    public static class Builder<T extends Builder<T>> {
        protected final ResourceLocation startPool;
        protected Lazy<Integer> structureSize = () -> 1;
        protected int biomeRange = 0;
        protected Lazy<Integer> maxY = () -> 255;
        protected Lazy<Integer> minY = () -> 0;
        protected boolean clipOutOfBoundsPieces = true;
        protected Lazy<Integer> verticalRange = null;

        public Builder(ResourceLocation startPool) {
            this.startPool = startPool;
        }

        @SuppressWarnings("unchecked")
        protected T getThis() {
            return (T) this;
        }

        public T setStructureSize(int structureSize) {
            this.structureSize = () -> structureSize;
            return getThis();
        }

        public T setStructureSize(ForgeConfigSpec.IntValue structureSize) {
            this.structureSize = Lazy.of(structureSize::get);
            return getThis();
        }

        public T setBiomeRange(int biomeRange) {
            this.biomeRange = biomeRange;
            return getThis();
        }

        public T setMaxY(int maxY) {
            this.maxY = () -> maxY;
            return getThis();
        }

        public T setMaxY(ForgeConfigSpec.ConfigValue<Integer> maxY) {
            this.maxY = Lazy.of(maxY::get);
            return getThis();
        }

        public T setMinY(int minY) {
            this.minY = () -> minY;
            return getThis();
        }

        public T setMinY(ForgeConfigSpec.ConfigValue<Integer> minY) {
            this.minY = Lazy.of(minY::get);
            return getThis();
        }

        public T setVerticalRange(int verticalRange) {
            this.verticalRange = () -> verticalRange;
            return getThis();
        }
        public T setVerticalRange(ForgeConfigSpec.ConfigValue<Integer> verticalRange) {
            this.verticalRange = Lazy.of(verticalRange::get);
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