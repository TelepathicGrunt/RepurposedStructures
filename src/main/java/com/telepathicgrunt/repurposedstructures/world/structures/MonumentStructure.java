package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.RSMonumentConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.MonumentPieces;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.CheckerboardColumnBiomeSource;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;

import java.util.List;
import java.util.Optional;


public class MonumentStructure<C extends RSMonumentConfig> extends AbstractBaseStructure<C> {

    public MonumentStructure(Codec<C> codec) {
        super(codec, MonumentStructure::isMonumentFeatureChunk, MonumentStructure::generateMonumentPieces);
    }

    protected static <CC extends RSMonumentConfig> boolean isMonumentFeatureChunk(PieceGeneratorSupplier.Context<CC> context) {
        ChunkPos chunkPos = context.chunkPos();
        CC config = context.config();

        if (!(context.biomeSource() instanceof CheckerboardColumnBiomeSource)) {
            for (int curChunkX = chunkPos.x - config.biomeRadius; curChunkX <= chunkPos.x + config.biomeRadius; curChunkX++) {
                for (int curChunkZ = chunkPos.z - config.biomeRadius; curChunkZ <= chunkPos.z + config.biomeRadius; curChunkZ++) {
                    int yValue = context.chunkGenerator().getFirstFreeHeight(curChunkX << 4, curChunkZ << 4, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());
                    Holder<Biome> biome = context.biomeSource().getNoiseBiome(curChunkX << 2, yValue >> 2, curChunkZ << 2, context.chunkGenerator().climateSampler());
                    if (!context.validBiome().test(biome)) {
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

        return true;
    }

    public static <CC extends RSMonumentConfig> Optional<PieceGenerator<CC>> generateMonumentPieces(PieceGeneratorSupplier.Context<CC> context) {
        CC config = context.config();
        ChunkPos chunkPos = context.chunkPos();
        WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));
        random.setLargeFeatureSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);

        int centerX = chunkPos.getMiddleBlockX();
        int centerZ = chunkPos.getMiddleBlockZ();
        int finalheight = context.chunkGenerator().getFirstOccupiedHeight(centerX, centerZ, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());

        if(finalheight <= context.chunkGenerator().getMinY())
            return Optional.empty();

        return Optional.of((structurePiecesBuilder, contextx) -> {
            int offsetX = centerX - 29;
            int offsetZ = centerZ - 29;
            Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
            List<StructurePiece> list = MonumentPieces.createMonumentBuilding(context.registryAccess(), context.structureManager(), random, offsetX, 60, offsetZ, direction, config);
            //GeneralUtils.centerAllPieces(chunkPos.getMiddleBlockPosition(60), list);
            list.forEach(structurePiecesBuilder::addPiece);
        });
    }
}