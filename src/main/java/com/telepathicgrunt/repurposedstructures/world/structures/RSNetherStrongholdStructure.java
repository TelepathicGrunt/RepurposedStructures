package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.RSStrongholdPieces;
import net.minecraft.entity.EntityType;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.WorldView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StrongholdFeature;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.List;


public class RSNetherStrongholdStructure extends StrongholdFeature {
    private static final List<SpawnSettings.SpawnEntry> MONSTER_SPAWNS =
            Lists.newArrayList(new SpawnSettings.SpawnEntry(EntityType.BLAZE, 10, 2, 3),
                    new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 3, 4, 4),
                    new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 10, 5, 5),
                    new SpawnSettings.SpawnEntry(EntityType.SKELETON, 2, 5, 5),
                    new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 3, 4, 4));

    public RSNetherStrongholdStructure() {
        super(DefaultFeatureConfig.CODEC);
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, int x, int z, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig featureConfig) {
        return (x * x) + (z * z) > 10000;
    }

    @Override
    public BlockPos locateStructure(WorldView worldView, StructureAccessor structureAccessor, BlockPos blockPos, int radius, boolean skipExistingChunks, long seed, StructureConfig structureConfig) {
        return AbstractBaseStructure.locateStructureFast(worldView, structureAccessor, blockPos, radius, skipExistingChunks, seed, structureConfig, this);
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return RSNetherStrongholdStructure.Start::new;
    }

    @Override
    public List<SpawnSettings.SpawnEntry> getMonsterSpawns() {
        return MONSTER_SPAWNS;
    }

    public static class Start extends StructureStart<DefaultFeatureConfig> {
        public Start(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }


        @Override
        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            RSStrongholdPieces.prepareStructurePieces();
            RSStrongholdPieces.EntranceStairs strongholdpieces$entrancestairs = new RSStrongholdPieces.EntranceStairs(this.random, (chunkX << 4) + 2, (chunkZ << 4) + 2, RSStrongholdPieces.Type.NETHER);
            this.children.add(strongholdpieces$entrancestairs);
            strongholdpieces$entrancestairs.placeJigsaw(strongholdpieces$entrancestairs, this.children, this.random);
            List<StructurePiece> list = strongholdpieces$entrancestairs.pendingChildren;

            while (!list.isEmpty()) {
                int i = this.random.nextInt(list.size());
                StructurePiece structurepiece = list.remove(i);
                structurepiece.placeJigsaw(strongholdpieces$entrancestairs, this.children, this.random);
            }

            if (strongholdpieces$entrancestairs.strongholdPortalRoom == null) {
                BlockBox box = this.children.get(this.children.size() - 1).getBoundingBox();
                RSStrongholdPieces.Stronghold portalRoom = RSStrongholdPieces.PortalRoom.createPiece(this.children, this.random, box.minX, box.minY + 1, box.minZ, Direction.NORTH, RSStrongholdPieces.Type.NETHER);
                this.children.add(portalRoom);
                strongholdpieces$entrancestairs.pendingChildren.add(portalRoom);
                list = strongholdpieces$entrancestairs.pendingChildren;

                while (!list.isEmpty()) {
                    int i = this.random.nextInt(list.size());
                    StructurePiece structurepiece = list.remove(i);
                    structurepiece.placeJigsaw(strongholdpieces$entrancestairs, this.children, this.random);
                }
            }

            this.setBoundingBoxFromChildren();
            int lowestBounds = this.boundingBox.minY - 2;
            int maxYConfig = RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdMaxHeight;
            int minYConfig = RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdMinHeight;


            int minimum = minYConfig;
            int maximum = Math.max(maxYConfig, minimum) + 1;

            // Sets stronghold's bottom most y to a random range between min and max y config.
            int offset = this.random.nextInt(maximum - minimum) + minimum;
            int offset2 = 0;

            //apply first offset to be able to do some calculations in next few lines
            this.boundingBox.offset(0, offset - lowestBounds, 0);

            // If the stronghold's max y is over the config's max y, lower the stronghold as
            // much as possible without hitting bedrock.
            if (this.boundingBox.maxY > maxYConfig) {
                int heightDiff = maxYConfig - this.boundingBox.maxY;
                offset2 = Math.max(heightDiff, -this.boundingBox.minY);
            }

            // Apply the final offsets
            this.boundingBox.offset(0, offset2, 0);
            for (StructurePiece structurepiece : this.children) {
                structurepiece.translate(0, offset + offset2 - lowestBounds, 0);
            }
        }
    }
}