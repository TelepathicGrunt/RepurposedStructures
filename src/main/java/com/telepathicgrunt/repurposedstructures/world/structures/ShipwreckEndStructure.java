package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.StartPoolOnlyCodeConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PieceLimitedJigsawManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import org.apache.commons.lang3.mutable.Mutable;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;


public class ShipwreckEndStructure extends AbstractBaseStructure<NoneFeatureConfiguration> {
    // Special thanks to cannon_foddr for allowing me to use his End Shipwreck design!
//new ResourceLocation(RepurposedStructures.MODID, "shipwrecks/end")

    public ShipwreckEndStructure(Predicate<PieceGeneratorSupplier.Context> locationCheckPredicate, Function<PieceGeneratorSupplier.Context, Optional<PieceGenerator<NoneFeatureConfiguration>>> pieceCreationPredicate) {
        super(NoneFeatureConfiguration.CODEC, locationCheckPredicate, pieceCreationPredicate);
    }

    // Need this constructor wrapper so we can hackly call `this` in the predicates that Minecraft requires in constructors
    public static ShipwreckEndStructure create(StartPoolOnlyCodeConfig startPoolOnlyCodeConfig) {
        final Mutable<ShipwreckEndStructure> box = new MutableObject<>();
        final ShipwreckEndStructure finalInstance = new ShipwreckEndStructure(
                (context) -> box.getValue().isFeatureChunk(context, startPoolOnlyCodeConfig),
                (context) -> box.getValue().generatePieces(context, startPoolOnlyCodeConfig)
        );
        box.setValue(finalInstance);
        return finalInstance;
    }

    protected boolean isFeatureChunk(PieceGeneratorSupplier.Context context, StartPoolOnlyCodeConfig config) {
        return getGenerationHeight(context.chunkPos(), context.chunkGenerator(), context.heightAccessor()) >= Math.min(context.chunkGenerator().getGenDepth(), 20);
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

    public Optional<PieceGenerator<NoneFeatureConfiguration>> generatePieces(PieceGeneratorSupplier.Context context, StartPoolOnlyCodeConfig config) {
        BlockPos blockpos = new BlockPos(context.chunkPos().getMinBlockX(), 64, context.chunkPos().getMinBlockZ());

        ResourceLocation structureID = Registry.STRUCTURE_FEATURE.getKey(this);
        return PieceLimitedJigsawManager.assembleJigsawStructure(
                context,
                new JigsawConfiguration(() -> context.registryAccess().registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(config.startPool), 5),
                structureID,
                blockpos,
                false,
                false,
                Integer.MAX_VALUE,
                Integer.MIN_VALUE,
                (pieces) -> {
                    GeneralUtils.centerAllPieces(blockpos, pieces);
                    WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));
                    random.setLargeFeatureSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);
                    StructurePiecesBuilder structurePiecesBuilder = new StructurePiecesBuilder();
                    pieces.forEach(structurePiecesBuilder::addPiece);

                    BlockPos blockPos = new BlockPos(pieces.get(0).getBoundingBox().getCenter());
                    int highestLandPos = context.chunkGenerator().getBaseHeight(blockPos.getX(), blockPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());
                    highestLandPos = Math.max(30, highestLandPos);
                    structurePiecesBuilder.moveInsideHeights(random, highestLandPos - 5, highestLandPos - 3);
                });
    }
}