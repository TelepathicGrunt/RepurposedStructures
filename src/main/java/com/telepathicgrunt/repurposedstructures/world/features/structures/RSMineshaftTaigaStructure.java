package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.util.math.BlockBox;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;


public class RSMineshaftTaigaStructure extends AbstractMineshaftStructure {
    public RSMineshaftTaigaStructure(Codec<DefaultFeatureConfig> config) {
        super(config);
    }

    public double getProbability() {
        return RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.taigaMineshaftSpawnrate;
    }

    @Override
    public StructureFeature.StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return RSMineshaftTaigaStructure.Start::new;
    }


    public static class Start extends AbstractStart {
        public Start(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public RSMineshaftPieces.Type getMineshaftType() {
            return RSMineshaftPieces.Type.TAIGA;
        }
        @Override
        public int getMaxHeight() {
            return RepurposedStructures.RSAllConfig.RSMineshaftsConfig.maxHeight.taigaMineshaftMaxHeight;
        }
        @Override
        public int getMinHeight() {
            return RepurposedStructures.RSAllConfig.RSMineshaftsConfig.minHeight.taigaMineshaftMinHeight;
        }
    }
}
