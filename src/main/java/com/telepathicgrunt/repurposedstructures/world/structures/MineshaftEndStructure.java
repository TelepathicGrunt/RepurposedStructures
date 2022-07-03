package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.math.Vector3f;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PieceLimitedJigsawManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;


public class MineshaftEndStructure extends Structure {

    public static final Codec<MineshaftEndStructure> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            MineshaftEndStructure.settingsCodec(instance),
            StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
            Codec.intRange(0, 30).fieldOf("size").forGetter(structure -> structure.size),
            Codec.INT.optionalFieldOf("min_y_allowed").forGetter(structure -> structure.minYAllowed),
            Codec.INT.optionalFieldOf("max_y_allowed").forGetter(structure -> structure.maxYAllowed),
            Codec.intRange(1, 1000).optionalFieldOf("allowed_y_range_from_start").forGetter(structure -> structure.allowedYRangeFromStart),
            HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight),
            Codec.intRange(1, 100).optionalFieldOf("valid_biome_radius_check").forGetter(structure -> structure.biomeRadius),
            ResourceLocation.CODEC.listOf().fieldOf("pools_that_ignore_boundaries").orElse(new ArrayList<>()).xmap(HashSet::new, ArrayList::new).forGetter(structure -> structure.poolsThatIgnoreBoundaries),
            Codec.intRange(1, 128).optionalFieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter),
            Codec.intRange(1, 1000).optionalFieldOf("min_island_thickness_allowed").forGetter(config -> config.minIslandThickness)
    ).apply(instance, MineshaftEndStructure::new));

    public final Holder<StructureTemplatePool> startPool;
    public final int size;
    public final Optional<Integer> minYAllowed;
    public final Optional<Integer> maxYAllowed;
    public final Optional<Integer> allowedYRangeFromStart;
    public final HeightProvider startHeight;
    public final Optional<Integer> biomeRadius;
    public final HashSet<ResourceLocation> poolsThatIgnoreBoundaries;
    public final Optional<Integer> maxDistanceFromCenter;
    public final Optional<Integer> minIslandThickness;

    public MineshaftEndStructure(Structure.StructureSettings config,
                                  Holder<StructureTemplatePool> startPool,
                                  int size,
                                  Optional<Integer> minYAllowed,
                                  Optional<Integer> maxYAllowed,
                                  Optional<Integer> allowedYRangeFromStart,
                                  HeightProvider startHeight,
                                  Optional<Integer> biomeRadius,
                                  HashSet<ResourceLocation> poolsThatIgnoreBoundaries,
                                  Optional<Integer> maxDistanceFromCenter,
                                  Optional<Integer> minIslandThickness)
    {
        super(config);
        this.startPool = startPool;
        this.size = size;
        this.minYAllowed = minYAllowed;
        this.maxYAllowed = maxYAllowed;
        this.allowedYRangeFromStart = allowedYRangeFromStart;
        this.startHeight = startHeight;
        this.biomeRadius = biomeRadius;
        this.poolsThatIgnoreBoundaries = poolsThatIgnoreBoundaries;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
        this.minIslandThickness = minIslandThickness;

        if (maxYAllowed.isPresent() && minYAllowed.isPresent() && maxYAllowed.get() < minYAllowed.get()) {
            throw new RuntimeException("""
                Repurposed Structures: maxYAllowed cannot be less than minYAllowed.
                Please correct this error as there's no way to spawn this structure properly
                    Structure pool of problematic structure: %s
            """.formatted(startPool.value().getName()));
        }
    }


    protected boolean extraSpawningChecks(GenerationContext context, BlockPos blockPos) {
        if(this.minIslandThickness.isEmpty()) {
            return true;
        }

        BlockPos.MutableBlockPos islandTopBottomThickness = new BlockPos.MutableBlockPos(Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        int xPos = blockPos.getX();
        int zPos = blockPos.getZ();

        int landHeight = Integer.MAX_VALUE;
        // Surrounding far terrain is more likely to fail the check and exit early.
        for(int i = 2; i >= 1; i--) {
            for(Direction direction : Direction.Plane.HORIZONTAL) {
                Vector3f offsetPos = direction.step();
                offsetPos.mul(30f * i);
                landHeight = getHeightAt(context, xPos + (int)offsetPos.x(), zPos + (int)offsetPos.z(), landHeight);
                if(landHeight - context.chunkGenerator().getMinY() < this.minIslandThickness.get()) return false;
            }
        }

        analyzeLand(context, xPos, zPos, islandTopBottomThickness, context.heightAccessor());
        return islandTopBottomThickness.getZ() >= this.minIslandThickness.get();
    }

    private static int getHeightAt(GenerationContext context, int xPos, int zPos, int landHeight) {
        landHeight = Math.min(landHeight, context.chunkGenerator().getFirstOccupiedHeight(xPos, zPos, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState()));
        return landHeight;
    }

    private static void analyzeLand(GenerationContext context, int xPos, int zPos, BlockPos.MutableBlockPos islandTopBottomThickness, LevelHeightAccessor heightLimitView) {
        NoiseColumn columnOfBlocks = context.chunkGenerator().getBaseColumn(xPos, zPos, heightLimitView, context.randomState());
        int minY = context.chunkGenerator().getMinY();
        int rangeHeight = GeneralUtils.getMaxTerrainLimit(context.chunkGenerator());
        int maxY = minY + rangeHeight;
        BlockPos.MutableBlockPos currentPos = new BlockPos.MutableBlockPos(xPos, maxY, zPos);
        boolean isInIsland = false;

        while(currentPos.getY() >= minY) {
            BlockState state = columnOfBlocks.getBlock(currentPos.getY());

            // Detects top of island
            if(!state.isAir() && !isInIsland) {
                isInIsland = true;
                int topIslandY = Math.min(currentPos.getY(), islandTopBottomThickness.getX());
                islandTopBottomThickness.set(topIslandY, islandTopBottomThickness.getY(), islandTopBottomThickness.getZ());
            }

            // Detects bottom of island or land
            else if((state.isAir() && isInIsland) || currentPos.getY() == minY) {
                int bottomIslandY = Math.max(currentPos.getY(), islandTopBottomThickness.getY());
                islandTopBottomThickness.set(islandTopBottomThickness.getX(), bottomIslandY, islandTopBottomThickness.getZ());
                break;
            }

            currentPos.move(Direction.DOWN);
        }

        // Never hit land since isInIsland was never set to true for terrain top.
        if(!isInIsland) {
            islandTopBottomThickness.set(0, 0, 0);
        }

        int thickness = islandTopBottomThickness.getX() - islandTopBottomThickness.getY();
        islandTopBottomThickness.set(islandTopBottomThickness.getX(), islandTopBottomThickness.getY(), thickness);
    }

    @Override
    public Optional<Structure.GenerationStub> findGenerationPoint(Structure.GenerationContext context) {
        BlockPos.MutableBlockPos blockpos = new BlockPos.MutableBlockPos(context.chunkPos().getMinBlockX(), 0, context.chunkPos().getMinBlockZ());
        if (!extraSpawningChecks(context, blockpos)) {
            return Optional.empty();
        }

        BlockPos.MutableBlockPos islandTopBottomThickness = new BlockPos.MutableBlockPos(Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        analyzeLand(context, blockpos.getX(), blockpos.getZ(), islandTopBottomThickness, context.heightAccessor());

        int maxY = 53;
        int minY = 15;
        if(this.minIslandThickness.isEmpty()) {
            blockpos.move(Direction.UP, 35);
        }
        else{
            WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));
            random.setLargeFeatureSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);
            int structureStartHeight = random.nextInt(Math.max(islandTopBottomThickness.getZ() - this.minIslandThickness.get() + 1, 1)) + islandTopBottomThickness.getY() + (this.minIslandThickness.get() / 2);
            blockpos.move(Direction.UP, structureStartHeight);
            maxY = islandTopBottomThickness.getX() - 10;
            minY = islandTopBottomThickness.getY();
            if(maxY - minY <= 10) {
                minY = maxY - 10;
            }
        }

        int finalMaxY = maxY;
        return PieceLimitedJigsawManager.assembleJigsawStructure(
                context,
                this.startPool,
                this.size,
                context.registryAccess().registryOrThrow(Registry.STRUCTURE_REGISTRY).getKey(this),
                blockpos,
                false,
                Optional.empty(),
                maxY,
                minY,
                this.poolsThatIgnoreBoundaries,
                this.maxDistanceFromCenter,
                (structurePiecesBuilder, pieces) -> {
                    Optional<PoolElementStructurePiece> highestPiece = pieces.stream().max(Comparator.comparingInt(p -> p.getBoundingBox().maxY()));
                    int topY = highestPiece.map(poolElementStructurePiece -> poolElementStructurePiece.getBoundingBox().maxY()).orElseGet(blockpos::getY);
                    if(topY > finalMaxY) {
                        int newOffset = finalMaxY - topY;
                        for (StructurePiece piece : pieces) {
                            piece.move(0, newOffset, 0);
                        }
                    }
                });
    }

    @Override
    public StructureType<?> type() {
        return RSStructures.MINESHAFT_END;
    }
}
