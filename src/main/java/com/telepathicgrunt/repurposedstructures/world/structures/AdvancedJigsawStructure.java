package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.RSAdvancedConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PieceLimitedJigsawManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.CheckerboardColumnBiomeSource;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class AdvancedJigsawStructure <C extends RSAdvancedConfig> extends AbstractBaseStructure<C> {

    public AdvancedJigsawStructure(Codec<C> codec) {
        super(codec, AdvancedJigsawStructure::isAdvancedFeatureChunk, AdvancedJigsawStructure::generateAdvancedPieces);
    }

    public AdvancedJigsawStructure(Codec<C> codec, Predicate<PieceGeneratorSupplier.Context<C>> locationCheckPredicate, Function<PieceGeneratorSupplier.Context<C>, Optional<PieceGenerator<C>>> pieceCreationPredicate) {
        super(codec, locationCheckPredicate, pieceCreationPredicate);
    }

    protected static <CC extends RSAdvancedConfig> boolean isAdvancedFeatureChunk(PieceGeneratorSupplier.Context<CC> context) {
        ChunkPos chunkPos = context.chunkPos();
        CC config = context.config();

        if(!(context.biomeSource() instanceof CheckerboardColumnBiomeSource)) {
            for (int curChunkX = chunkPos.x - config.biomeRadius; curChunkX <= chunkPos.x + config.biomeRadius; curChunkX++) {
                for (int curChunkZ = chunkPos.z - config.biomeRadius; curChunkZ <= chunkPos.z + config.biomeRadius; curChunkZ++) {
                    WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));
                    random.setLargeFeatureSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);
                    int structureStartHeight = random.nextInt(config.maxY - config.minY) + config.minY;
                    if (!context.validBiome().test(context.biomeSource().getNoiseBiome(curChunkX << 2, structureStartHeight >> 2, curChunkZ << 2, context.chunkGenerator().climateSampler()))) {
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

    public static <CC extends RSAdvancedConfig> Optional<PieceGenerator<CC>> generateAdvancedPieces(PieceGeneratorSupplier.Context<CC> context) {
        BlockPos.MutableBlockPos blockpos = new BlockPos.MutableBlockPos(context.chunkPos().getMinBlockX(), 0, context.chunkPos().getMinBlockZ());
        CC config = context.config();

        if(config.maxY - config.minY <= 0) {
            RepurposedStructures.LOGGER.error("MinY should always be less than MaxY or else a crash will occur or no pieces will spawn. Problematic structure is:" + config.startPool.unwrapKey().get().location());
        }
        WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));
        random.setLargeFeatureSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);
        int structureStartHeight = random.nextInt(config.maxY - config.minY) + config.minY;
        blockpos.move(Direction.UP, structureStartHeight);

        int topClipOff;
        int bottomClipOff;
        if(config.verticalRange.isEmpty()) {
            // Help make sure the Jigsaw Blocks have room to spawn new pieces if structure is right on edge of maxY or topYLimit
            topClipOff = config.clipOutOfBoundsPieces ? config.maxY + 5 : Integer.MAX_VALUE;
            bottomClipOff = config.clipOutOfBoundsPieces ? config.minY - 5 : Integer.MIN_VALUE;
        }
        else {
            topClipOff = structureStartHeight + config.verticalRange.get();
            bottomClipOff = structureStartHeight - config.verticalRange.get();
        }
        
        return PieceLimitedJigsawManager.assembleJigsawStructure(
                context,
                new JigsawConfiguration(config.startPool, config.size),
                GeneralUtils.getCsfNameForConfig(config, context.registryAccess()),
                blockpos,
                false,
                false,
                topClipOff,
                bottomClipOff,
                config.poolsThatIgnoreBoundaries,
                (structurePiecesBuilder, pieces) -> {});
    }
}