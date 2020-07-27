package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RSFeatures;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class VillageMountainsStructure extends AbstractVillageStructure {
    public VillageMountainsStructure(Codec<NoFeatureConfig> config) {
        super(config);
    }

    @Override
    public StructureFeature<NoFeatureConfig> getVillageInstance() {
        return RSFeatures.MOUNTAINS_VILLAGE;
    }

    public StructureFeature.StructureStartFactory<NoFeatureConfig> getStructureStartFactory() {
        return VillageMountainsStructure.Start::new;
    }

    public static class Start extends AbstractStart {
        public Start(StructureFeature<NoFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public static ResourceLocation VILLAGE_ResourceLocation = new ResourceLocation(RepurposedStructures.MODID + ":village/mountains/town_centers");
        @Override
        public ResourceLocation getResourceLocation() {
            return VILLAGE_ResourceLocation;
        }

        @Override
        public int getSize() {
            return 6;
        }
    }
}