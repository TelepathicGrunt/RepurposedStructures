package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.GenericJigsawStructureCodeConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PieceLimitedJigsawManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.CheckerboardColumnBiomeSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import org.apache.commons.lang3.mutable.Mutable;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class GenericJigsawStructure extends AbstractBaseStructure<NoneFeatureConfiguration> {

    public GenericJigsawStructure(Predicate<PieceGeneratorSupplier.Context<NoneFeatureConfiguration>> locationCheckPredicate, Function<PieceGeneratorSupplier.Context<NoneFeatureConfiguration>, Optional<PieceGenerator<NoneFeatureConfiguration>>> pieceCreationPredicate) {
        super(NoneFeatureConfiguration.CODEC, locationCheckPredicate, pieceCreationPredicate);
    }

    // Need this constructor wrapper so we can hackly call `this` in the predicates that Minecraft requires in constructors
    public static GenericJigsawStructure create(GenericJigsawStructureCodeConfig genericJigsawStructureCodeConfig) {
        final Mutable<GenericJigsawStructure> box = new MutableObject<>();
        final GenericJigsawStructure finalInstance = new GenericJigsawStructure(
            (context) -> box.getValue().isFeatureChunk(context, genericJigsawStructureCodeConfig),
            (context) -> box.getValue().generatePieces(context, genericJigsawStructureCodeConfig)
        );
        box.setValue(finalInstance);
        return finalInstance;
    }

    protected boolean isFeatureChunk(PieceGeneratorSupplier.Context<NoneFeatureConfiguration> context, GenericJigsawStructureCodeConfig config) {
        ChunkPos chunkPos = context.chunkPos();

        if (!(context.biomeSource() instanceof CheckerboardColumnBiomeSource)) {
            for (int curChunkX = chunkPos.x - config.biomeRange; curChunkX <= chunkPos.x + config.biomeRange; curChunkX++) {
                for (int curChunkZ = chunkPos.z - config.biomeRange; curChunkZ <= chunkPos.z + config.biomeRange; curChunkZ++) {
                    int yValue = config.useHeightmap ?
                            config.fixedYSpawn + context.chunkGenerator().getFirstFreeHeight(curChunkX << 4, curChunkZ << 4, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor())
                            : config.fixedYSpawn;
                    Biome biome = context.biomeSource().getNoiseBiome(curChunkX << 2, yValue >> 2, curChunkZ << 2, context.chunkGenerator().climateSampler());
                    if (!context.validBiome().test(biome)) {
                        return false;
                    }
                }
            }
        }

        //cannot be near other specified structure
        for (int curChunkX = chunkPos.x - config.structureBlacklistRange; curChunkX <= chunkPos.x + config.structureBlacklistRange; curChunkX++) {
            for (int curChunkZ = chunkPos.z - config.structureBlacklistRange; curChunkZ <= chunkPos.z + config.structureBlacklistRange; curChunkZ++) {
                for (RSStructureTagMap.STRUCTURE_TAGS tag : config.avoidStructuresSet) {
                    for (StructureFeature<?> structureFeature : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(tag)) {
                        if (structureFeature == this) continue;

                        StructureFeatureConfiguration structureConfig = context.chunkGenerator().getSettings().getConfig(structureFeature);
                        if (structureConfig != null && structureConfig.spacing() > 8) {
                            ChunkPos chunkPos2 = structureFeature.getPotentialFeatureChunk(structureConfig, context.seed(), curChunkX, curChunkZ);
                            if (curChunkX == chunkPos2.x && curChunkZ == chunkPos2.z) {
                                return false;
                            }
                        }
                    }
                }
            }
        }

        if (config.allowTerrainHeightRange != -1) {
            int maxTerrainHeight = Integer.MIN_VALUE;
            int minTerrainHeight = Integer.MAX_VALUE;

            for (int curChunkX = chunkPos.x - config.terrainHeightRadius; curChunkX <= chunkPos.x + config.terrainHeightRadius; curChunkX++) {
                for (int curChunkZ = chunkPos.z - config.terrainHeightRadius; curChunkZ <= chunkPos.z + config.terrainHeightRadius; curChunkZ++) {
                    int height = context.chunkGenerator().getBaseHeight((curChunkX << 4) + 7, (curChunkZ << 4) + 7, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());
                    maxTerrainHeight = Math.max(maxTerrainHeight, height);
                    minTerrainHeight = Math.min(minTerrainHeight, height);

                    if (minTerrainHeight < config.minHeightLimit) {
                        return false;
                    }
                }
            }

            return maxTerrainHeight - minTerrainHeight <= config.allowTerrainHeightRange;
        }

        if (config.cannotSpawnInWater) {
            BlockPos centerOfChunk = chunkPos.getMiddleBlockPosition(0);
            int landHeight = context.chunkGenerator().getFirstOccupiedHeight(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());
            NoiseColumn columnOfBlocks = context.chunkGenerator().getBaseColumn(centerOfChunk.getX(), centerOfChunk.getZ(), context.heightAccessor());
            BlockState topBlock = columnOfBlocks.getBlock(centerOfChunk.getY() + landHeight);
            return topBlock.getFluidState().isEmpty();
        }

        return true;
    }

    public Optional<PieceGenerator<NoneFeatureConfiguration>> generatePieces(PieceGeneratorSupplier.Context<NoneFeatureConfiguration> context, GenericJigsawStructureCodeConfig config) {
        BlockPos blockpos = new BlockPos(context.chunkPos().getMinBlockX(), config.fixedYSpawn, context.chunkPos().getMinBlockZ());

        ResourceLocation structureID = Registry.STRUCTURE_FEATURE.getKey(this);
        return PieceLimitedJigsawManager.assembleJigsawStructure(
                context,
                new JigsawConfiguration(() -> context.registryAccess().registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(config.startPool), config.structureSize),
                structureID,
                blockpos,
                config.useHeightmap,
                config.useHeightmap,
                Integer.MAX_VALUE,
                Integer.MIN_VALUE,
                (structurePiecesBuilder, pieces) -> {
                    GeneralUtils.centerAllPieces(blockpos, pieces);
                    pieces.get(0).move(0, config.centerOffset, 0);
                });
    }
}