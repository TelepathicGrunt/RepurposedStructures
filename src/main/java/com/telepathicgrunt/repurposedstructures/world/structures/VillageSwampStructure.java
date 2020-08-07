package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RSFeatures;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class VillageSwampStructure extends AbstractVillageStructure
{
    public VillageSwampStructure(Codec<NoFeatureConfig> config) {
	super(config);
    }

	@Override
	public Structure<NoFeatureConfig> getVillageInstance() {
		return RSFeatures.SWAMP_VILLAGE;
	}

    public Structure.IStartFactory<NoFeatureConfig> getStartFactory() {
		return VillageSwampStructure.Start::new;
    }

    public static class Start extends AbstractStart
    {
		public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
			super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
		}

		public static ResourceLocation VILLAGE_ResourceLocation = new ResourceLocation(RepurposedStructures.MODID + ":village/swamp/town_centers");
		@Override
		public ResourceLocation getResourceLocation() {
			return VILLAGE_ResourceLocation;
		}

		@Override
		public int getSize() {
			return 6;
		}

		@Override
		public void init(ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig NoFeatureConfig) {
			super.init(chunkGenerator, structureManager, chunkX, chunkZ, biome, NoFeatureConfig);
			this.components.get(0).offset(0,-1,0);
		}
    }
}