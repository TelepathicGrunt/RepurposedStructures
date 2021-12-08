package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.utils.Mutable;
import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.GenericJigsawStructureCodeConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PieceLimitedJigsawManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class LandBasedEndStructure extends GenericJigsawStructure {

    public LandBasedEndStructure(Predicate<PieceGeneratorSupplier.Context<NoneFeatureConfiguration>> locationCheckPredicate, Function<PieceGeneratorSupplier.Context<NoneFeatureConfiguration>, Optional<PieceGenerator<NoneFeatureConfiguration>>> pieceCreationPredicate) {
        super(locationCheckPredicate, pieceCreationPredicate);
    }

    // Need this constructor wrapper so we can hackly call `this` in the predicates that Minecraft requires in constructors
    public static LandBasedEndStructure create(GenericJigsawStructureCodeConfig genericJigsawStructureCodeConfig) {
        final Mutable<LandBasedEndStructure> box = new Mutable<>();
        final LandBasedEndStructure finalInstance = new LandBasedEndStructure(
                (context) -> box.getValue().isFeatureChunk(context, genericJigsawStructureCodeConfig),
                (context) -> box.getValue().generatePieces(context, genericJigsawStructureCodeConfig)
        );
        box.setValue(finalInstance);
        return finalInstance;
    }

    protected boolean isFeatureChunk(PieceGeneratorSupplier.Context<NoneFeatureConfiguration> context, GenericJigsawStructureCodeConfig config) {
        return getTerrainHeight(context.chunkPos(), context.chunkGenerator(), context.heightAccessor()) >= Math.min(GeneralUtils.getMaxTerrainLimit(context.chunkGenerator()), 50);
    }

    // must be on land
    private static int getTerrainHeight(ChunkPos chunkPos1, ChunkGenerator chunkGenerator, LevelHeightAccessor heightLimitView) {
        int xPos = chunkPos1.x << 4;
        int zPos = chunkPos1.z << 4;
        int height = chunkGenerator.getFirstOccupiedHeight(xPos, zPos, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);

        BlockPos pos = new BlockPos(xPos, GeneralUtils.getMaxTerrainLimit(chunkGenerator), zPos);
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        for(Direction direction : Direction.Plane.HORIZONTAL) {
            mutable.set(pos).move(direction, 16);
            height = Math.min(height, chunkGenerator.getFirstOccupiedHeight(mutable.getX(), mutable.getZ(), Heightmap.Types.WORLD_SURFACE_WG, heightLimitView));
        }

        return height;
    }

    public Optional<PieceGenerator<NoneFeatureConfiguration>> generatePieces(PieceGeneratorSupplier.Context<NoneFeatureConfiguration> context, GenericJigsawStructureCodeConfig config) {
        BlockPos blockpos = new BlockPos(context.chunkPos().getMinBlockX(), config.fixedYSpawn, context.chunkPos().getMinBlockZ());

        ResourceLocation structureID = ForgeRegistries.STRUCTURE_FEATURES.getKey(this);
        return PieceLimitedJigsawManager.assembleJigsawStructure(
                context,
                new JigsawConfiguration(() -> context.registryAccess().registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(config.startPool), config.structureSize.get()),
                structureID,
                blockpos,
                config.useHeightmap,
                config.useHeightmap,
                Integer.MAX_VALUE,
                Integer.MIN_VALUE,
                (structurePiecesBuilder, pieces) -> {
                    GeneralUtils.centerAllPieces(blockpos, pieces);
                    pieces.get(0).move(0, config.centerOffset, 0);

                    BoundingBox box = pieces.get(0).getBoundingBox();
                    BlockPos centerPos = new BlockPos(box.getCenter());
                    int radius = (int) Math.sqrt((box.getLength().getX() * box.getLength().getX()) + (box.getLength().getZ() * box.getLength().getZ())) / 2;

                    List<Integer> landHeights = new ArrayList<>();
                    for(int xOffset = -radius; xOffset <= radius; xOffset += (radius/2)) {
                        for(int zOffset = -radius; zOffset <= radius; zOffset += (radius/2)) {
                            int landHeight = context.chunkGenerator().getFirstOccupiedHeight(centerPos.getX() + xOffset, centerPos.getZ() + zOffset, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());
                            landHeights.add(landHeight);
                        }
                    }

                    // Offset structure to average land around it
                    int avgHeight = (int) Math.max(landHeights.stream().mapToInt(Integer::intValue).average().orElse(0), 50);
                    int parentHeight = pieces.get(0).getBoundingBox().minY();
                    int offsetAmount = (avgHeight - parentHeight) + config.centerOffset;
                    pieces.forEach(child -> child.move(0, offsetAmount, 0));
                    GeneralUtils.centerAllPieces(centerPos, pieces);
                });
    }
}