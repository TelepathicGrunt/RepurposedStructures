package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;


public class IglooGrassyStructure extends AbstractIglooStructure {
    public IglooGrassyStructure(Codec<NoFeatureConfig> config) {
        super(config);
    }

    @Override
    public StructureFeature.StructureStartFactory<NoFeatureConfig> getStructureStartFactory() {
        return IglooGrassyStructure.Start::new;
    }

    public static class Start extends AbstractStart {
        public Start(StructureFeature<NoFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        private static ResourceLocation TOP_PIECE_ResourceLocation = new ResourceLocation(RepurposedStructures.MODID + ":igloos/grassy_top");
        @Override
        public ResourceLocation getTopPieceResourceLocation() {
            return TOP_PIECE_ResourceLocation;
        }
    }
}