package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.Maps;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class GenericNetherJigsawStructure extends GenericJigsawStructure {

    public static final Codec<GenericNetherJigsawStructure> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            GenericNetherJigsawStructure.settingsCodec(instance),
            StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
            Codec.intRange(0, 30).fieldOf("size").forGetter(structure -> structure.size),
            Codec.INT.optionalFieldOf("min_y_allowed").forGetter(structure -> structure.minYAllowed),
            Codec.INT.optionalFieldOf("max_y_allowed").forGetter(structure -> structure.maxYAllowed),
            Codec.intRange(1, 1000).optionalFieldOf("allowed_y_range_from_start").forGetter(structure -> structure.allowedYRangeFromStart),
            HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight),
            Codec.BOOL.fieldOf("cannot_spawn_in_liquid").orElse(false).forGetter(structure -> structure.cannotSpawnInLiquid),
            Codec.intRange(1, 100).optionalFieldOf("valid_biome_radius_check").forGetter(structure -> structure.biomeRadius),
            ResourceLocation.CODEC.listOf().fieldOf("pools_that_ignore_boundaries").orElse(new ArrayList<>()).xmap(HashSet::new, ArrayList::new).forGetter(structure -> structure.poolsThatIgnoreBoundaries),
            Codec.intRange(1, 128).optionalFieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter),
            Codec.intRange(0, 100).optionalFieldOf("ledge_offset_y").forGetter(structure -> structure.ledgeOffsetY),
            StringRepresentable.fromEnum(LAND_SEARCH_DIRECTION::values).fieldOf("land_search_direction").forGetter(structure -> structure.searchDirection),
            Codec.BOOL.fieldOf("use_bounding_box_hack").orElse(false).forGetter(structure -> structure.useBoundingBoxHack)
    ).apply(instance, GenericNetherJigsawStructure::new));

    public final Optional<Integer> ledgeOffsetY;
    public final LAND_SEARCH_DIRECTION searchDirection;

    public GenericNetherJigsawStructure(Structure.StructureSettings config,
                                    Holder<StructureTemplatePool> startPool,
                                    int size,
                                    Optional<Integer> minYAllowed,
                                    Optional<Integer> maxYAllowed,
                                    Optional<Integer> allowedYRangeFromStart,
                                    HeightProvider startHeight,
                                    boolean cannotSpawnInLiquid,
                                    Optional<Integer> biomeRadius,
                                    HashSet<ResourceLocation> poolsThatIgnoreBoundaries,
                                    Optional<Integer> maxDistanceFromCenter,
                                    Optional<Integer> ledgeOffsetY,
                                    LAND_SEARCH_DIRECTION searchDirection,
                                    boolean useBoundingBoxHack) {
        super(config,
            startPool,
            size,
            minYAllowed,
            maxYAllowed,
            allowedYRangeFromStart,
            startHeight,
            Optional.empty(),
            cannotSpawnInLiquid,
            Optional.empty(),
            Optional.empty(),
            biomeRadius,
            poolsThatIgnoreBoundaries,
            maxDistanceFromCenter,
            Optional.empty(),
            useBoundingBoxHack);

        this.ledgeOffsetY = ledgeOffsetY;
        this.searchDirection = searchDirection;
    }

    @Override
    protected void postLayoutAdjustments(StructurePiecesBuilder structurePiecesBuilder, GenerationContext context, int offsetY, BlockPos blockpos, int topClipOff, int bottomClipOff, List<PoolElementStructurePiece> pieces) {
        GeneralUtils.centerAllPieces(blockpos, pieces);

        WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));
        random.setLargeFeatureSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);
        BlockPos placementPos;

        if (this.searchDirection == LAND_SEARCH_DIRECTION.HIGHEST_LAND) {
            placementPos = GeneralUtils.getHighestLand(context.chunkGenerator(), context.randomState(), structurePiecesBuilder.getBoundingBox(), context.heightAccessor(), !this.cannotSpawnInLiquid);
        }
        else {
            placementPos = GeneralUtils.getLowestLand(context.chunkGenerator(), context.randomState(), structurePiecesBuilder.getBoundingBox(), context.heightAccessor(), !this.cannotSpawnInLiquid);
        }

        if (placementPos.getY() >= GeneralUtils.getMaxTerrainLimit(context.chunkGenerator()) || placementPos.getY() <= context.chunkGenerator().getSeaLevel() + 1) {
            int yDiff = (context.chunkGenerator().getSeaLevel() + this.ledgeOffsetY.orElse(0)) - pieces.get(0).getBoundingBox().minY();
            pieces.forEach(piece -> piece.move(0, yDiff, 0));
        }
        else {
            int yDiff = (placementPos.getY() + this.ledgeOffsetY.orElse(0)) - pieces.get(0).getBoundingBox().minY();
            pieces.forEach(piece -> piece.move(0, yDiff, 0));
        }

        pieces.forEach(piece -> piece.move(0, offsetY, 0));
    }

    public enum LAND_SEARCH_DIRECTION implements StringRepresentable {
        HIGHEST_LAND("HIGHEST_LAND"),
        LOWEST_LAND("LOWEST_LAND");

        private final String name;

        LAND_SEARCH_DIRECTION(String name) {
            this.name = name;
        }

        private static final Map<String, LAND_SEARCH_DIRECTION> BY_NAME = Util.make(Maps.newHashMap(), (hashMap) -> {
            LAND_SEARCH_DIRECTION[] var1 = values();
            for (LAND_SEARCH_DIRECTION type : var1) {
                hashMap.put(type.name, type);
            }
        });

        public static LAND_SEARCH_DIRECTION byName(String name) {
            return BY_NAME.get(name.toUpperCase(Locale.ROOT));
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }
    }

    @Override
    public StructureType<?> type() {
        return RSStructures.GENERIC_NETHER_JIGSAW_STRUCTURE;
    }
}