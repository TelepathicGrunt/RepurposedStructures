package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.math.Vector3f;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.RSAdvancedDistanceConfig;
import net.minecraft.core.Direction;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;


public class StrongholdEndStructure <C extends RSAdvancedDistanceConfig> extends AdvancedDistanceJigsawStructure<C> {

    public StrongholdEndStructure(Codec<C> codec) {
        super(codec, StrongholdEndStructure::isStrongholdEndFeatureChunk, AdvancedDistanceJigsawStructure::generateDistancePieces);
    }

    protected static <CC extends RSAdvancedDistanceConfig> boolean isStrongholdEndFeatureChunk(PieceGeneratorSupplier.Context<CC> context) {
        boolean superCheck = AdvancedDistanceJigsawStructure.isDistanceFeatureChunk(context);
        if(!superCheck)
            return false;

        ChunkPos chunkPos = context.chunkPos();
        int minLandHeight = Math.min(GeneralUtils.getMaxTerrainLimit(context.chunkGenerator()), context.chunkGenerator().getMinY() + 45);
        int xPos = chunkPos.getMinBlockX();
        int zPos = chunkPos.getMinBlockZ();
        int landHeight = Integer.MAX_VALUE;

        for(int i = 2; i >= 1; i--) {
            for(Direction direction : Direction.Plane.HORIZONTAL) {
                Vector3f offsetPos = direction.step();
                offsetPos.mul(35f * i);
                landHeight = getHeightAt(context.chunkGenerator(), context.heightAccessor(), xPos + (int)offsetPos.x(), zPos + (int)offsetPos.z(), landHeight);
                if(landHeight < minLandHeight) return false;
            }
        }

        landHeight = getHeightAt(context.chunkGenerator(), context.heightAccessor(), xPos, zPos, landHeight);
        return landHeight >= minLandHeight;
    }

    private static int getHeightAt(ChunkGenerator chunkGenerator, LevelHeightAccessor heightLimitView, int xPos, int zPos, int landHeight) {
        landHeight = Math.min(landHeight, chunkGenerator.getFirstOccupiedHeight(xPos, zPos, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView));
        return landHeight;
    }
}
