package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.mixin.structures.PoolElementStructurePieceAccessor;
import com.telepathicgrunt.repurposedstructures.mixin.structures.SinglePoolElementAccessor;
import com.telepathicgrunt.repurposedstructures.mixin.structures.StructurePieceAccessor;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.RSMonumentConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.MonumentPieces;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.CheckerboardColumnBiomeSource;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

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
                    int yValue = config.fixedYSpawn.orElse(context.chunkGenerator().getFirstFreeHeight(curChunkX << 4, curChunkZ << 4, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor()));
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

        BlockPos centerPoint = chunkPos.getMiddleBlockPosition(0);
        int finalheight;
        if (config.fixedYSpawn.isPresent()) {
            finalheight = config.fixedYSpawn.get();
        }
        else {
            float centerWeight = config.centerTerrainHeightWeight.orElse(1F);
            int centerHight = context.chunkGenerator().getFirstOccupiedHeight(centerPoint.getX(), centerPoint.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());
            int highestLandPos = centerHight;
            highestLandPos = Math.min(highestLandPos, context.chunkGenerator().getFirstOccupiedHeight(centerPoint.getX() + 29, centerPoint.getZ() + 29, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor()));
            highestLandPos = Math.min(highestLandPos, context.chunkGenerator().getFirstOccupiedHeight(centerPoint.getX() - 29, centerPoint.getZ() + 29, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor()));
            highestLandPos = Math.min(highestLandPos, context.chunkGenerator().getFirstOccupiedHeight(centerPoint.getX() + 29, centerPoint.getZ() - 29, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor()));
            highestLandPos = Math.min(highestLandPos, context.chunkGenerator().getFirstOccupiedHeight(centerPoint.getX() - 29, centerPoint.getZ() - 29, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor()));
            finalheight = (int)((highestLandPos - centerHight) / centerWeight) + centerHight;
        }

        if(finalheight <= context.chunkGenerator().getMinY())
            return Optional.empty();

        return Optional.of((structurePiecesBuilder, contextx) -> {
            List<StructurePiece> list = MonumentPieces.createMonumentBuilding(
                    context.registryAccess(),
                    context.structureManager(),
                    random,
                    centerPoint.getX(),
                    finalheight,
                    centerPoint.getZ(),
                    config);

            Rotation rotation = Rotation.getRandom(random);
            BlockPos mainOffset = new BlockPos(-29, 0, -29).rotate(rotation);
            for(StructurePiece structurePiece : list) {
                if (structurePiece instanceof PoolElementStructurePiece poolPiece) {
                    ((PoolElementStructurePieceAccessor)poolPiece).setRotation(
                            poolPiece.getRotation().getRotated(rotation));

                    // rotate
                    BlockPos piecePos = poolPiece.getPosition();
                    BlockPos offsetPos = piecePos.subtract(centerPoint);
                    BlockPos rotatedOffset = offsetPos.rotate(rotation);
                    poolPiece.move(
                            rotatedOffset.getX() - offsetPos.getX(),
                            0,
                            rotatedOffset.getZ() - offsetPos.getZ());

                    // center
                    poolPiece.move(mainOffset.getX(), 0, mainOffset.getZ());

                    // fix piece bounding boxes
                    if (poolPiece.getElement() instanceof SinglePoolElement singlePoolElement) {
                        StructureTemplate structuretemplate = ((SinglePoolElementAccessor)singlePoolElement).callGetTemplate(context.structureManager());
                        ((StructurePieceAccessor)poolPiece).setBoundingBox(structuretemplate.getBoundingBox((new StructurePlaceSettings()).setRotation(rotation), poolPiece.getPosition()));
                    }
                }
            }
            list.forEach(structurePiecesBuilder::addPiece);
        });
    }
}