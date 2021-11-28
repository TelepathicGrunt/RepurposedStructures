package com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.resources.ResourceLocation;

public class AdvancedDistanceJigsawStructureCodeConfig extends AdvancedJigsawStructureCodeConfig {

    public final int distanceFromWorldOrigin;

    public AdvancedDistanceJigsawStructureCodeConfig(ResourceLocation poolID, int structureSize, int biomeRange,
                                                     int maxY, int minY, boolean clipOutOfBoundsPieces, Integer verticalRange,
                                                     int distanceFromWorldOrigin) {
        super(poolID, structureSize, biomeRange, maxY, minY, clipOutOfBoundsPieces, verticalRange);
        this.distanceFromWorldOrigin = distanceFromWorldOrigin;

        RSStructures.RS_STRUCTURE_START_PIECES.add(startPool);
    }

    public static class Builder<T extends Builder<T>> extends AdvancedJigsawStructureCodeConfig.Builder<T> {

        protected int distanceFromWorldOrigin = 2817;

        public Builder(ResourceLocation startPool) {
            super(startPool);
        }

        public T setDistanceFromWorldOrigin(int distanceFromWorldOrigin) {
            this.distanceFromWorldOrigin = distanceFromWorldOrigin;
            return getThis();
        }

        public AdvancedDistanceJigsawStructureCodeConfig build() {
            return new AdvancedDistanceJigsawStructureCodeConfig(
                    startPool,
                    structureSize,
                    biomeRange,
                    maxY,
                    minY,
                    clipOutOfBoundsPieces,
                    verticalRange,
                    distanceFromWorldOrigin);
        }
    }
}