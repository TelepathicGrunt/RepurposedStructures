package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.RSStrongholdPieces;
import net.minecraft.util.Direction;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.settings.StructureSeparationSettings;

import java.util.List;
import java.util.Random;


public class RSStonebrickStrongholdStructure extends StrongholdStructure {
    public RSStonebrickStrongholdStructure() {
        super(NoFeatureConfig.CODEC);
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int x, int z, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
        return (x * x) + (z * z) > 10000;
    }

    @Override
    public BlockPos locateStructure(IWorldReader worldView, StructureManager structureAccessor, BlockPos blockPos, int radius, boolean skipExistingChunks, long seed, StructureSeparationSettings structureConfig) {
        return AbstractBaseStructure.locateStructureFast(worldView, structureAccessor, blockPos, radius, skipExistingChunks, seed, structureConfig, this);
    }

    @Override
    public Structure.IStartFactory<NoFeatureConfig> getStartFactory() {
        return RSStonebrickStrongholdStructure.Start::new;
    }


    public static class Start extends StructureStart<NoFeatureConfig> {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }


        @Override
        public void init(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig NoFeatureConfig) {
            RSStrongholdPieces.prepareStructurePieces();
            RSStrongholdPieces.EntranceStairs strongholdpieces$entrancestairs = new RSStrongholdPieces.EntranceStairs(this.rand, (chunkX << 4) + 2, (chunkZ << 4) + 2, RSStrongholdPieces.Type.NORMAL);
            this.components.add(strongholdpieces$entrancestairs);
            strongholdpieces$entrancestairs.buildComponent(strongholdpieces$entrancestairs, this.components, this.rand);
            List<StructurePiece> list = strongholdpieces$entrancestairs.pendingChildren;

            while (!list.isEmpty()) {
                int i = this.rand.nextInt(list.size());
                StructurePiece structurepiece = list.remove(i);
                structurepiece.buildComponent(strongholdpieces$entrancestairs, this.components, this.rand);
            }

            if (strongholdpieces$entrancestairs.strongholdPortalRoom == null) {
                MutableBoundingBox box = this.components.get(this.components.size() - 1).getBoundingBox();
                RSStrongholdPieces.Stronghold portalRoom = RSStrongholdPieces.PortalRoom.createPiece(this.components, this.rand, box.minX, box.minY + 1, box.minZ, Direction.NORTH, RSStrongholdPieces.Type.NORMAL);
                this.components.add(portalRoom);
                strongholdpieces$entrancestairs.pendingChildren.add(portalRoom);
                list = strongholdpieces$entrancestairs.pendingChildren;

                while (!list.isEmpty()) {
                    int i = this.rand.nextInt(list.size());
                    StructurePiece structurepiece = list.remove(i);
                    structurepiece.buildComponent(strongholdpieces$entrancestairs, this.components, this.rand);
                }
            }

            this.recalculateStructureSize();
            int lowestBounds = this.bounds.minY - 2;
            int maxYConfig = RepurposedStructures.RSStrongholdsConfig.stonebrickStrongholdMaxHeight.get();
            int minYConfig = RepurposedStructures.RSStrongholdsConfig.stonebrickStrongholdMinHeight.get();

            RSStonebrickStrongholdStructure.offsetStronghold(lowestBounds, maxYConfig, minYConfig, this.rand, this.components, this.bounds, strongholdpieces$entrancestairs.strongholdPortalRoom);
        }
    }

    public static void offsetStronghold(int lowestBounds, int maxYConfig, int minYConfig, Random random, List<StructurePiece> pieces, MutableBoundingBox bounds, RSStrongholdPieces.PortalRoom strongholdPortalRoom) {
        int minimum = minYConfig;
        int maximum = Math.max(maxYConfig, minimum) + 1;

        // Sets stronghold's bottom most y to a random range between min and max y config.
        int offset = random.nextInt(maximum - minimum) + minimum;
        int offset2 = 0;

        //apply first offset to be able to do some calculations in next few lines
        bounds.offset(0, offset - lowestBounds, 0);

        // If the stronghold's max y is over the config's max y, lower the stronghold as
        // much as possible without hitting bedrock.
        if (bounds.maxY > maxYConfig) {
            int heightDiff = maxYConfig - bounds.maxY;
            offset2 = bounds.minY + heightDiff < 2 ? 2 - bounds.minY : heightDiff;
        }

        // Apply the final offsets
        bounds.offset(0, offset2, 0);
        int finalOffset = offset2;
        pieces.forEach(piece -> piece.offset(0, offset + finalOffset - lowestBounds, 0));

        int portalRoomY = strongholdPortalRoom.getBoundingBox().minY;

        // Now remove rooms that are above max y config if portal room is below it.
        if(portalRoomY < maxYConfig){
            pieces.removeIf(piece -> piece.getBoundingBox().minY > maxYConfig);
        }
        // Portal room too high, lower stronghold and delete rooms too low.
        else {
            int topDiff = strongholdPortalRoom.getBoundingBox().maxY - maxYConfig;
            pieces.forEach(piece -> piece.offset(0, -topDiff, 0));
            pieces.removeIf(piece -> piece.getBoundingBox().minY < Math.max(minYConfig, 4));
            bounds.offset(0, -topDiff, 0);
        }
    }
}