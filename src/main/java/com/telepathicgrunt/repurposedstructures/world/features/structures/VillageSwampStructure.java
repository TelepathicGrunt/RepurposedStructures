package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RSFeatures;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.VillageGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

public class VillageSwampStructure extends AbstractVillageStructure
{
    public VillageSwampStructure(Codec<DefaultFeatureConfig> config) {
	super(config);
    }

	@Override
	public StructureFeature<DefaultFeatureConfig> getVillageInstance() {
		return RSFeatures.SWAMP_VILLAGE;
	}

    public StructureFeature.StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
		return VillageSwampStructure.Start::new;
    }

    public static class Start extends AbstractStart
    {
		public Start(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
			super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
		}

		public static Identifier VILLAGE_IDENTIFIER = new Identifier(RepurposedStructures.MODID + ":village/swamp/town_centers");

		@Override
		public Identifier getIdentifier() {
			return VILLAGE_IDENTIFIER;
		}

		@Override
		public int getSize() {
			return 6;
		}

		@Override
		public void init(ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
			super.init(chunkGenerator, structureManager, chunkX, chunkZ, biome, defaultFeatureConfig);
			this.children.get(0).translate(0,-1,0);
		}
    }
}