package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.BuriableStructureCodeConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PieceLimitedJigsawManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import org.apache.commons.lang3.mutable.Mutable;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;


public class BuriableStructure extends AbstractBaseStructure<NoneFeatureConfiguration> {

    public BuriableStructure(Predicate<PieceGeneratorSupplier.Context<NoneFeatureConfiguration>> locationCheckPredicate, Function<PieceGeneratorSupplier.Context<NoneFeatureConfiguration>, Optional<PieceGenerator<NoneFeatureConfiguration>>> pieceCreationPredicate) {
        super(NoneFeatureConfiguration.CODEC, locationCheckPredicate, pieceCreationPredicate);
    }

    // Need this constructor wrapper so we can hackly call `this` in the predicates that Minecraft requires in constructors
    public static BuriableStructure create(BuriableStructureCodeConfig buriableStructureCodeConfig) {
        final Mutable<BuriableStructure> box = new MutableObject<>();
        final BuriableStructure finalInstance = new BuriableStructure(
                (context) -> box.getValue().isFeatureChunk(context, buriableStructureCodeConfig),
                (context) -> box.getValue().generatePieces(context, buriableStructureCodeConfig)
        );
        box.setValue(finalInstance);
        return finalInstance;
    }


    protected boolean isFeatureChunk(PieceGeneratorSupplier.Context<NoneFeatureConfiguration> context, BuriableStructureCodeConfig config) {
        if(config.cannotSpawnInWater) {
            BlockPos cornerOfSpawnChunk = context.chunkPos().getWorldPosition();
            int landHeight = context.chunkGenerator().getFirstOccupiedHeight(cornerOfSpawnChunk.getX(), cornerOfSpawnChunk.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());
            NoiseColumn columnOfBlocks = context.chunkGenerator().getBaseColumn(cornerOfSpawnChunk.getX(), cornerOfSpawnChunk.getZ(), context.heightAccessor());
            BlockState topBlock = columnOfBlocks.getBlock(cornerOfSpawnChunk.getY() + landHeight);
            return topBlock.getFluidState().isEmpty();
        }

        return true;
    }

    public Optional<PieceGenerator<NoneFeatureConfiguration>> generatePieces(PieceGeneratorSupplier.Context<NoneFeatureConfiguration> context, BuriableStructureCodeConfig config) {
        BlockPos blockpos = new BlockPos(context.chunkPos().getMinBlockX(), context.chunkGenerator().getSeaLevel(), context.chunkPos().getMinBlockZ());

        ResourceLocation structureID = Registry.STRUCTURE_FEATURE.getKey(this);
        return PieceLimitedJigsawManager.assembleJigsawStructure(
                context,
                new JigsawConfiguration(() -> context.registryAccess().registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(config.startPool), 11),
                structureID,
                blockpos,
                false,
                false,
                Integer.MAX_VALUE,
                Integer.MIN_VALUE,
                (structurePiecesBuilder, pieces) -> {
                    GeneralUtils.centerAllPieces(blockpos, pieces);
                    Heightmap.Types heightMapToUse = config.onLand ? Heightmap.Types.WORLD_SURFACE_WG : Heightmap.Types.OCEAN_FLOOR_WG;

                    BoundingBox box = pieces.get(0).getBoundingBox();
                    int highestLandPos = context.chunkGenerator().getBaseHeight(box.minX(), box.minZ(), heightMapToUse, context.heightAccessor());
                    highestLandPos = Math.min(highestLandPos, context.chunkGenerator().getBaseHeight(box.minX(), box.maxZ(), heightMapToUse, context.heightAccessor()));
                    highestLandPos = Math.min(highestLandPos, context.chunkGenerator().getBaseHeight(box.maxX(), box.minZ(), heightMapToUse, context.heightAccessor()));
                    highestLandPos = Math.min(highestLandPos, context.chunkGenerator().getBaseHeight(box.maxX(), box.maxZ(), heightMapToUse, context.heightAccessor()));

                    if(!config.onLand) {
                        int maxHeightForSubmerging = context.chunkGenerator().getSeaLevel() - box.getYSpan();
                        highestLandPos = Math.min(highestLandPos, maxHeightForSubmerging);
                    }

                    WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));
                    random.setLargeFeatureSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);
                    structurePiecesBuilder.moveInsideHeights(random, highestLandPos - (config.offsetAmount + 1), highestLandPos - config.offsetAmount);
                });
    }
}