package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.RSShipwreckEndConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PieceLimitedJigsawManager;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;

import java.util.Optional;


public class ShipwreckEndStructure <C extends RSShipwreckEndConfig> extends AbstractBaseStructure<C> {

    // Special thanks to cannon_foddr for allowing me to use his End Shipwreck design!
    public ShipwreckEndStructure(Codec<C> codec) {
        super(codec, ShipwreckEndStructure::isShipwreckEndFeatureChunk, ShipwreckEndStructure::generateShipwreckEndPieces);
    }

    protected static <CC extends RSShipwreckEndConfig> boolean isShipwreckEndFeatureChunk(PieceGeneratorSupplier.Context<CC> context) {
        // Check to see if there some air where the structure wants to spawn.
        // Doesn't account for rotation of structure.
        ChunkPos chunkPos = context.chunkPos();
        BlockPos blockPos = new BlockPos(chunkPos.getMinBlockX(), context.chunkGenerator().getSeaLevel(), chunkPos.getMinBlockZ());
        CC config = context.config();

        int radius = config.distanceFromOrigin;
        int xBlockPos = context.chunkPos().getMinBlockX();
        int zBlockPos = context.chunkPos().getMinBlockZ();
        if((xBlockPos * xBlockPos) + (zBlockPos * zBlockPos) <= radius * radius) {
            return false;
        }

        int checkRadius = 16;
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for(int xOffset = -checkRadius; xOffset <= checkRadius; xOffset += 8) {
            for(int zOffset = -checkRadius; zOffset <= checkRadius; zOffset += 8) {
                NoiseColumn blockView = context.chunkGenerator().getBaseColumn(xOffset + blockPos.getX(), zOffset + blockPos.getZ(), context.heightAccessor());
                int topY = context.chunkGenerator().getFirstOccupiedHeight(xOffset + blockPos.getX(), zOffset + blockPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());
                for(int yOffset = 0; yOffset <= 10; yOffset += 5) {
                    mutable.set(blockPos).move(xOffset, topY-yOffset, zOffset);
                    if (blockView.getBlock(mutable.getY()).isAir()) {
                        return false;
                    }
                }
            }
        }

        //cannot be near other specified structure
        for (ResourceKey<StructureSet> structureSetToAvoid : config.structureSetToAvoid) {
            if (context.chunkGenerator().hasFeatureChunkInRange(structureSetToAvoid, context.seed(), chunkPos.x, chunkPos.z, config.structureAvoidRadius)) {
                return false;
            }
        }

        return getGenerationHeight(context.chunkPos(), context.chunkGenerator(), context.heightAccessor()) >= Math.min(GeneralUtils.getMaxTerrainLimit(context.chunkGenerator()), config.minYAllowed);
    }

    private static int getGenerationHeight(ChunkPos chunkPos1, ChunkGenerator chunkGenerator, LevelHeightAccessor heightLimitView) {
        int x = chunkPos1.x * 16;
        int z = chunkPos1.z * 16;
        int heightmap1 = chunkGenerator.getFirstOccupiedHeight(x + 5, z, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
        int heightmap2 = chunkGenerator.getFirstOccupiedHeight(x, z + 5, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
        int heightmap3 = chunkGenerator.getFirstOccupiedHeight(x - 5, z, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
        int heightmap4 = chunkGenerator.getFirstOccupiedHeight(x, z - 5, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
        return Math.min(Math.min(heightmap1, heightmap2), Math.min(heightmap3, heightmap4));
    }

    public static <CC extends RSShipwreckEndConfig> Optional<PieceGenerator<CC>> generateShipwreckEndPieces(PieceGeneratorSupplier.Context<CC> context) {
        CC config = context.config();
        BlockPos blockpos = new BlockPos(
                context.chunkPos().getMinBlockX(),
                getGenerationHeight(context.chunkPos(), context.chunkGenerator(), context.heightAccessor()),
                context.chunkPos().getMinBlockZ());

        return PieceLimitedJigsawManager.assembleJigsawStructure(
                context,
                new JigsawConfiguration(config.startPool, config.size),
                GeneralUtils.getCsfNameForConfig(config, context.registryAccess()),
                blockpos,
                false,
                false,
                Integer.MAX_VALUE,
                Integer.MIN_VALUE,
                (structurePiecesBuilder, pieces) -> GeneralUtils.centerAllPieces(blockpos, pieces));
    }
}