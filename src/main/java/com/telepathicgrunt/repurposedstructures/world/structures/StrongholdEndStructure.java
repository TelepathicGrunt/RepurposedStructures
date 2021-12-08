package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.math.Vector3f;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.utils.Mutable;
import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.AdvancedDistanceJigsawStructureCodeConfig;
import net.minecraft.core.Direction;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;


public class StrongholdEndStructure extends AdvancedDistanceJigsawStructure {

    public StrongholdEndStructure(Predicate<PieceGeneratorSupplier.Context<NoneFeatureConfiguration>> locationCheckPredicate, Function<PieceGeneratorSupplier.Context<NoneFeatureConfiguration>, Optional<PieceGenerator<NoneFeatureConfiguration>>> pieceCreationPredicate) {
        super(locationCheckPredicate, pieceCreationPredicate);
    }

    // Need this constructor wrapper so we can hackly call `this` in the predicates that Minecraft requires in constructors
    public static StrongholdEndStructure create(AdvancedDistanceJigsawStructureCodeConfig advancedDistanceJigsawStructureCodeConfig) {
        final Mutable<StrongholdEndStructure> box = new Mutable<>();
        final StrongholdEndStructure finalInstance = new StrongholdEndStructure(
                (context) -> box.getValue().isFeatureChunk(context, advancedDistanceJigsawStructureCodeConfig),
                (context) -> box.getValue().generatePieces(context, advancedDistanceJigsawStructureCodeConfig)
        );
        box.setValue(finalInstance);
        return finalInstance;
    }


    protected boolean isFeatureChunk(PieceGeneratorSupplier.Context<NoneFeatureConfiguration> context, AdvancedDistanceJigsawStructureCodeConfig config) {
        boolean superCheck = super.isFeatureChunk(context, config);
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

    private int getHeightAt(ChunkGenerator chunkGenerator, LevelHeightAccessor heightLimitView, int xPos, int zPos, int landHeight) {
        landHeight = Math.min(landHeight, chunkGenerator.getFirstOccupiedHeight(xPos, zPos, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView));
        return landHeight;
    }
}
