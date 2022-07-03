package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


public class MineshaftStructure extends GenericJigsawStructure {

    public static final Codec<MineshaftStructure> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            MineshaftStructure.settingsCodec(instance),
            StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
            Codec.intRange(0, 30).fieldOf("size").forGetter(structure -> structure.size),
            Codec.INT.optionalFieldOf("min_y_allowed").forGetter(structure -> structure.minYAllowed),
            Codec.INT.optionalFieldOf("max_y_allowed").forGetter(structure -> structure.maxYAllowed),
            Codec.intRange(1, 1000).optionalFieldOf("allowed_y_range_from_start").forGetter(structure -> structure.allowedYRangeFromStart),
            HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight),
            Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> structure.projectStartToHeightmap),
            Codec.BOOL.fieldOf("cannot_spawn_in_liquid").orElse(false).forGetter(structure -> structure.cannotSpawnInLiquid),
            Codec.intRange(1, 100).optionalFieldOf("terrain_height_radius_check").forGetter(structure -> structure.terrainHeightCheckRadius),
            Codec.intRange(1, 1000).optionalFieldOf("allowed_terrain_height_range").forGetter(structure -> structure.allowedTerrainHeightRange),
            Codec.intRange(1, 100).optionalFieldOf("valid_biome_radius_check").forGetter(structure -> structure.biomeRadius),
            ResourceLocation.CODEC.listOf().fieldOf("pools_that_ignore_boundaries").orElse(new ArrayList<>()).xmap(HashSet::new, ArrayList::new).forGetter(structure -> structure.poolsThatIgnoreBoundaries),
            Codec.intRange(1, 128).optionalFieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter),
            StringRepresentable.fromEnum(BURYING_TYPE::values).optionalFieldOf("burying_type").forGetter(structure -> structure.buryingType),
            Codec.BOOL.fieldOf("use_bounding_box_hack").orElse(false).forGetter(structure -> structure.useBoundingBoxHack)
    ).apply(instance, MineshaftStructure::new));

    public MineshaftStructure(Structure.StructureSettings config,
                                  Holder<StructureTemplatePool> startPool,
                                  int size,
                                  Optional<Integer> minYAllowed,
                                  Optional<Integer> maxYAllowed,
                                  Optional<Integer> allowedYRangeFromStart,
                                  HeightProvider startHeight,
                                  Optional<Heightmap.Types> projectStartToHeightmap,
                                  boolean cannotSpawnInLiquid,
                                  Optional<Integer> terrainHeightCheckRadius,
                                  Optional<Integer> allowedTerrainHeightRange,
                                  Optional<Integer> biomeRadius,
                                  HashSet<ResourceLocation> poolsThatIgnoreBoundaries,
                                  Optional<Integer> maxDistanceFromCenter,
                                  Optional<BURYING_TYPE> buryingType,
                                  boolean useBoundingBoxHack)
    {
        super(config,
                startPool,
                size,
                minYAllowed,
                maxYAllowed,
                allowedYRangeFromStart,
                startHeight,
                projectStartToHeightmap,
                cannotSpawnInLiquid,
                terrainHeightCheckRadius,
                allowedTerrainHeightRange,
                biomeRadius,
                poolsThatIgnoreBoundaries,
                maxDistanceFromCenter,
                buryingType,
                useBoundingBoxHack);
    }

    @Override
    protected void postLayoutAdjustments(StructurePiecesBuilder structurePiecesBuilder, GenerationContext context, int offsetY, BlockPos blockpos, int topClipOff, int bottomClipOff, List<PoolElementStructurePiece> pieces) {
        int justBelowTerrain = getTerrainHeight(context.chunkPos().getMiddleBlockPosition(0), context) - 15;
        int finalJustBelowTerrain = Math.max(justBelowTerrain, bottomClipOff);
        Optional<PoolElementStructurePiece> topPiece = pieces.stream().max(Comparator.comparingInt(piece -> piece.getBoundingBox().maxY()));
        if(topPiece.isPresent() && finalJustBelowTerrain < topClipOff && finalJustBelowTerrain < topPiece.get().getBoundingBox().maxY()) {
            int topPieceMaxY = topPiece.get().getBoundingBox().maxY();
            pieces.forEach(piece -> piece.move(0, finalJustBelowTerrain - topPieceMaxY, 0));
        }
    }

    private static int getTerrainHeight(BlockPos centerPos, GenerationContext context) {
        int height = context.chunkGenerator().getFirstOccupiedHeight(centerPos.getX(), centerPos.getZ(), Heightmap.Types.OCEAN_FLOOR_WG, context.heightAccessor(), context.randomState());

        BlockPos pos = new BlockPos(centerPos.getX(), GeneralUtils.getMaxTerrainLimit(context.chunkGenerator()), centerPos.getZ());
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        for(Direction direction : Direction.Plane.HORIZONTAL) {
            mutable.set(pos).move(direction, 16);
            height = Math.min(height, context.chunkGenerator().getFirstOccupiedHeight(mutable.getX(), mutable.getZ(), Heightmap.Types.OCEAN_FLOOR_WG, context.heightAccessor(), context.randomState()));
        }

        return height;
    }

    @Override
    public StructureType<?> type() {
        return RSStructures.GENERIC_MINESHAFT;
    }
}