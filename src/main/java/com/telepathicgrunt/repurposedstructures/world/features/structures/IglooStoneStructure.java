package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;


public class IglooStoneStructure extends AbstractIglooStructure {
    public IglooStoneStructure(Codec<NoFeatureConfig> config) {
        super(config);
    }

    @Override
    public Structure.IStartFactory<NoFeatureConfig> getStartFactory() {
        return IglooStoneStructure.Start::new;
    }

    public static class Start extends AbstractStart {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public static ResourceLocation TOP_PIECE_ResourceLocation = new ResourceLocation(RepurposedStructures.MODID + ":igloos/stone_top");
        @Override
        public ResourceLocation getTopPieceResourceLocation() {
            return TOP_PIECE_ResourceLocation;
        }
    }
}