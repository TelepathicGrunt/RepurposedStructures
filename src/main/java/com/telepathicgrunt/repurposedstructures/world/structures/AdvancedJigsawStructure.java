package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.utils.Mutable;
import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.AdvancedJigsawStructureCodeConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PieceLimitedJigsawManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.CheckerboardColumnBiomeSource;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class AdvancedJigsawStructure extends AbstractBaseStructure<NoneFeatureConfiguration> {

    public AdvancedJigsawStructure(Predicate<PieceGeneratorSupplier.Context<NoneFeatureConfiguration>> locationCheckPredicate, Function<PieceGeneratorSupplier.Context<NoneFeatureConfiguration>, Optional<PieceGenerator<NoneFeatureConfiguration>>> pieceCreationPredicate) {
        super(NoneFeatureConfiguration.CODEC, locationCheckPredicate, pieceCreationPredicate);
    }

    // Need this constructor wrapper so we can hackly call `this` in the predicates that Minecraft requires in constructors
    public static AdvancedJigsawStructure create(AdvancedJigsawStructureCodeConfig advancedJigsawStructureCodeConfig) {
        final Mutable<AdvancedJigsawStructure> box = new Mutable<>();
        final AdvancedJigsawStructure finalInstance = new AdvancedJigsawStructure(
                (context) -> box.getValue().isFeatureChunk(context, advancedJigsawStructureCodeConfig),
                (context) -> box.getValue().generatePieces(context, advancedJigsawStructureCodeConfig)
        );
        box.setValue(finalInstance);
        return finalInstance;
    }

    protected boolean isFeatureChunk(PieceGeneratorSupplier.Context<NoneFeatureConfiguration> context, AdvancedJigsawStructureCodeConfig config) {
        ChunkPos chunkPos = context.chunkPos();

        if(!(context.biomeSource() instanceof CheckerboardColumnBiomeSource)) {
            for (int curChunkX = chunkPos.x - config.biomeRange; curChunkX <= chunkPos.x + config.biomeRange; curChunkX++) {
                for (int curChunkZ = chunkPos.z - config.biomeRange; curChunkZ <= chunkPos.z + config.biomeRange; curChunkZ++) {
                    WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));
                    random.setLargeFeatureSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);
                    int structureStartHeight = random.nextInt(config.maxY.get() - config.minY.get()) + config.minY.get();
                    if (!context.validBiome().test(context.biomeSource().getNoiseBiome(curChunkX << 2, structureStartHeight >> 2, curChunkZ << 2, context.chunkGenerator().climateSampler()))) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public Optional<PieceGenerator<NoneFeatureConfiguration>> generatePieces(PieceGeneratorSupplier.Context<NoneFeatureConfiguration> context, AdvancedJigsawStructureCodeConfig config) {
        BlockPos.MutableBlockPos blockpos = new BlockPos.MutableBlockPos(context.chunkPos().getMinBlockX(), 0, context.chunkPos().getMinBlockZ());
        if(config.maxY.get() - config.minY.get() <= 0) {
            RepurposedStructures.LOGGER.error("MinY should always be less than MaxY or else a crash will occur or no pieces will spawn. Problematic structure is:" + Registry.STRUCTURE_FEATURE.getKey(this));
        }
        WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));
        random.setLargeFeatureSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);
        int structureStartHeight = random.nextInt(config.maxY.get() - config.minY.get()) + config.minY.get();
        blockpos.move(Direction.UP, structureStartHeight);

        int topClipOff;
        int bottomClipOff;
        if(config.verticalRange == null) {
            // Help make sure the Jigsaw Blocks have room to spawn new pieces if structure is right on edge of maxY or topYLimit
            topClipOff = config.clipOutOfBoundsPieces ? config.maxY.get() + 5 : Integer.MAX_VALUE;
            bottomClipOff = config.clipOutOfBoundsPieces ? config.minY.get() - 5 : Integer.MIN_VALUE;
        }
        else {
            topClipOff = structureStartHeight + config.verticalRange.get();
            bottomClipOff = structureStartHeight - config.verticalRange.get();
        }

//            long startTime = System.currentTimeMillis();
//            for(int i = 0; i < 50; i++) {
//                this.pieces.clear();

        ResourceLocation structureID = ForgeRegistries.STRUCTURE_FEATURES.getKey(this);
        return PieceLimitedJigsawManager.assembleJigsawStructure(
                context,
                new JigsawConfiguration(() -> context.registryAccess().registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(config.startPool), config.structureSize.get()),
                structureID,
                blockpos,
                false,
                false,
                topClipOff,
                bottomClipOff,
                (structurePiecesBuilder, pieces) -> {});

//            }
//            long endTime = System.currentTimeMillis();
//            long duration = (endTime - startTime);
//            RepurposedStructures.LOGGER.warn("Time taken {} milliseconds at {}, {} for {}", duration, chunkPos1.x, chunkPos1.z, startPool);

    }
}