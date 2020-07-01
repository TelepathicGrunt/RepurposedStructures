package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;


public class RSMineshaftJungleStructure extends AbstractMineshaftStructure {
    public RSMineshaftJungleStructure(Codec<DefaultFeatureConfig> config) {
        super(config);
    }

    public double mineshaftProbability() {
        return RepurposedStructures.RSAllConfig.RSMineshaftsConfig.spawnrate.jungleMineshaftSpawnrate;
    }

    @Override
    public StructureFeature.StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return RSMineshaftJungleStructure.Start::new;
    }

    public static class Start extends StructureStart<DefaultFeatureConfig> {
        public Start(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }


        @Override
        public void init(ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            RSMineshaftPieces.Room structuremineshaftpiecesua$room = new RSMineshaftPieces.Room(0, this.random, (chunkX << 4) + 2, (chunkZ << 4) + 2, RSMineshaftPieces.Type.JUNGLE);
            this.children.add(structuremineshaftpiecesua$room);

            structuremineshaftpiecesua$room.placeJigsaw(structuremineshaftpiecesua$room, this.children, this.random);
            this.setBoundingBoxFromChildren();

            int minimum = RepurposedStructures.RSAllConfig.RSMineshaftsConfig.minHeight.jungleMineshaftMinHeight;
            int maximum = Math.max(RepurposedStructures.RSAllConfig.RSMineshaftsConfig.maxHeight.jungleMineshaftMaxHeight, minimum) + 1;

            int offset = this.random.nextInt(maximum - minimum) + minimum;
            this.boundingBox.offset(0, offset - 50, 0);

            for (StructurePiece structurepiece : this.children) {
                structurepiece.translate(0, offset - 50, 0);
            }
        }
    }

}
