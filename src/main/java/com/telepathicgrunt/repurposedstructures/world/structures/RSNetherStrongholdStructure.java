package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.RSStrongholdPieces;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.settings.StructureSeparationSettings;

import java.util.List;
import java.util.Random;


public class RSNetherStrongholdStructure extends StrongholdStructure {
    private static final List<MobSpawnInfo.Spawners> MONSTER_SPAWNS =
            Lists.newArrayList(new MobSpawnInfo.Spawners(EntityType.BLAZE, 10, 2, 3),
                    new MobSpawnInfo.Spawners(EntityType.ZOMBIFIED_PIGLIN, 3, 4, 4),
                    new MobSpawnInfo.Spawners(EntityType.WITHER_SKELETON, 10, 5, 5),
                    new MobSpawnInfo.Spawners(EntityType.SKELETON, 2, 5, 5),
                    new MobSpawnInfo.Spawners(EntityType.MAGMA_CUBE, 3, 4, 4));

    public RSNetherStrongholdStructure() {
        super(NoFeatureConfig.CODEC);
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int x, int z, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
        return (x * x) + (z * z) > 10000;
    }

    @Override
    public BlockPos getNearestGeneratedFeature(IWorldReader worldView, StructureManager structureAccessor, BlockPos blockPos, int radius, boolean skipExistingChunks, long seed, StructureSeparationSettings structureConfig) {
        return AbstractBaseStructure.locateStructureFast(worldView, structureAccessor, blockPos, radius, skipExistingChunks, seed, structureConfig, this);
    }

    @Override
    public Structure.IStartFactory<NoFeatureConfig> getStartFactory() {
        return RSNetherStrongholdStructure.Start::new;
    }

    @Override
    public List<MobSpawnInfo.Spawners> getDefaultSpawnList() {
        return MONSTER_SPAWNS;
    }

    public static class Start extends StructureStart<NoFeatureConfig> {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }


        @Override
        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig NoFeatureConfig) {
            RSStrongholdPieces.prepareStructurePieces();
            RSStrongholdPieces.EntranceStairs strongholdpieces$entrancestairs = new RSStrongholdPieces.EntranceStairs(this.random, (chunkX << 4) + 2, (chunkZ << 4) + 2, RSStrongholdPieces.Type.NETHER);
            this.pieces.add(strongholdpieces$entrancestairs);
            strongholdpieces$entrancestairs.addChildren(strongholdpieces$entrancestairs, this.pieces, this.random);
            List<StructurePiece> list = strongholdpieces$entrancestairs.pendingChildren;

            while (!list.isEmpty()) {
                int i = this.random.nextInt(list.size());
                StructurePiece structurepiece = list.remove(i);
                structurepiece.addChildren(strongholdpieces$entrancestairs, this.pieces, this.random);
            }

            if (strongholdpieces$entrancestairs.strongholdPortalRoom == null) {
                MutableBoundingBox box = this.pieces.get(this.pieces.size() - 1).getBoundingBox();
                RSStrongholdPieces.Stronghold portalRoom = RSStrongholdPieces.PortalRoom.createPiece(this.pieces, this.random, box.x0, box.y0 + 1, box.z0, Direction.NORTH, RSStrongholdPieces.Type.NETHER);
                this.pieces.add(portalRoom);
                strongholdpieces$entrancestairs.pendingChildren.add(portalRoom);
                list = strongholdpieces$entrancestairs.pendingChildren;

                while (!list.isEmpty()) {
                    int i = this.random.nextInt(list.size());
                    StructurePiece structurepiece = list.remove(i);
                    structurepiece.addChildren(strongholdpieces$entrancestairs, this.pieces, this.random);
                }
            }

            this.calculateBoundingBox();
            int lowestBounds = this.boundingBox.y0 - 2;
            int maxYConfig = RepurposedStructures.RSStrongholdsConfig.netherStrongholdMaxHeight.get();
            int minYConfig = RepurposedStructures.RSStrongholdsConfig.netherStrongholdMinHeight.get();

            offsetStronghold(lowestBounds, maxYConfig, minYConfig, this.random, this.pieces, this.boundingBox, strongholdpieces$entrancestairs.strongholdPortalRoom);
        }
    }

    public static void offsetStronghold(int lowestBounds, int maxYConfig, int minYConfig, Random random, List<StructurePiece> pieces, MutableBoundingBox bounds, RSStrongholdPieces.PortalRoom strongholdPortalRoom) {
        int minimum = minYConfig;
        int maximum = Math.max(maxYConfig, minimum) + 1;

        // Sets stronghold's bottom most y to a random range between min and max y config.
        int offset = random.nextInt(maximum - minimum) + minimum;
        int offset2 = 0;

        //apply first offset to be able to do some calculations in next few lines
        bounds.move(0, offset - lowestBounds, 0);

        // If the stronghold's max y is over the config's max y, lower the stronghold as
        // much as possible without hitting bedrock.
        if (bounds.y1 > maxYConfig) {
            int heightDiff = maxYConfig - bounds.y1;
            offset2 = bounds.y0 + heightDiff < 2 ? 2 - bounds.y0 : heightDiff;
        }

        // Apply the final offsets
        bounds.move(0, offset2, 0);
        int finalOffset = offset2;
        pieces.forEach(piece -> piece.move(0, offset + finalOffset - lowestBounds, 0));
        int portalRoomY = strongholdPortalRoom.getBoundingBox().y0;

        // Now remove rooms that are above max y config if portal room is below it.
        if (portalRoomY < maxYConfig) {
            pieces.removeIf(piece -> piece.getBoundingBox().y0 > maxYConfig);
        }
        // Portal room too high, lower stronghold and delete rooms too low.
        else {
            int topDiff = strongholdPortalRoom.getBoundingBox().y1 - maxYConfig;
            pieces.forEach(piece -> piece.move(0, -topDiff, 0));
            pieces.removeIf(piece -> piece.getBoundingBox().y0 < Math.max(minYConfig, 4));
            bounds.move(0, -topDiff, 0);
        }
    }
}