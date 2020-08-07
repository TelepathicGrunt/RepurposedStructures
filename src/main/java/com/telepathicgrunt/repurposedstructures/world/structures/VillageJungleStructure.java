package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RSFeatures;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;

public class VillageJungleStructure extends AbstractVillageStructure {
    public VillageJungleStructure(Codec<NoFeatureConfig> config) {
        super(config);
    }


    @Override
    public Structure<NoFeatureConfig> getVillageInstance() {
        return RSFeatures.JUNGLE_VILLAGE;
    }

    public Structure.IStartFactory<NoFeatureConfig> getStartFactory() {
        return VillageJungleStructure.Start::new;
    }

    public static class Start extends AbstractStart {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public static ResourceLocation VILLAGE_ResourceLocation = new ResourceLocation(RepurposedStructures.MODID + ":village/jungle/town_centers");
        @Override
        public ResourceLocation getResourceLocation() {
            return VILLAGE_ResourceLocation;
        }

        @Override
        public int getSize() {
            return 8;
        }
    }
}