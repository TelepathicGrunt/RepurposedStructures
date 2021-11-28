package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.math.Vector3f;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.MineshaftCodeConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PieceLimitedJigsawManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import org.apache.commons.lang3.mutable.Mutable;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;


public class MineshaftEndStructure extends MineshaftStructure {

    public MineshaftEndStructure(Predicate<PieceGeneratorSupplier.Context<NoneFeatureConfiguration>> locationCheckPredicate, Function<PieceGeneratorSupplier.Context<NoneFeatureConfiguration>, Optional<PieceGenerator<NoneFeatureConfiguration>>> pieceCreationPredicate) {
        super(locationCheckPredicate, pieceCreationPredicate);
    }

    // Need this constructor wrapper so we can hackly call `this` in the predicates that Minecraft requires in constructors
    public static MineshaftEndStructure create(MineshaftCodeConfig mineshaftCodeConfig) {
        final Mutable<MineshaftEndStructure> box = new MutableObject<>();
        final MineshaftEndStructure finalInstance = new MineshaftEndStructure(
                (context) -> box.getValue().isFeatureChunk(context, mineshaftCodeConfig),
                (context) -> box.getValue().generatePieces(context, mineshaftCodeConfig)
        );
        box.setValue(finalInstance);
        return finalInstance;
    }

    protected boolean isFeatureChunk(PieceGeneratorSupplier.Context<NoneFeatureConfiguration> context, MineshaftCodeConfig config) {
        boolean superCheck = super.isFeatureChunk(context, config);
        if(!superCheck)
            return false;

        //cannot be near end strongholds
        int structureCheckRadius = 6;
        ChunkPos chunkPos = context.chunkPos();
        for (int curChunkX = chunkPos.x - structureCheckRadius; curChunkX <= chunkPos.x + structureCheckRadius; curChunkX++) {
            for (int curChunkZ = chunkPos.z - structureCheckRadius; curChunkZ <= chunkPos.z + structureCheckRadius; curChunkZ++) {
                for(StructureFeature<?> structureFeature : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.END_MINESHAFT_AVOID_STRUCTURE)) {
                    if(structureFeature == this) continue;

                    StructureFeatureConfiguration structureConfig = context.chunkGenerator().getSettings().getConfig(structureFeature);
                    if(structureConfig != null && structureConfig.spacing() > 10) {
                        ChunkPos chunkPos2 = structureFeature.getPotentialFeatureChunk(structureConfig, context.seed(), curChunkX, curChunkZ);
                        if (curChunkX == chunkPos2.x && curChunkZ == chunkPos2.z) {
                            return false;
                        }
                    }
                }
            }
        }

        int minThickness = RepurposedStructures.RSAllConfig.RSMineshaftsConfig.misc.endMineshaftMinIslandThickness;
        if(minThickness == 0)
            return true;

        BlockPos.MutableBlockPos islandTopBottomThickness = new BlockPos.MutableBlockPos(Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        int xPos = chunkPos.getMinBlockX();
        int zPos = chunkPos.getMinBlockZ();

        int landHeight = Integer.MAX_VALUE;
        // Surrounding far terrain is more likely to fail the check and exit early.
        for(int i = 2; i >= 1; i--) {
            for(Direction direction : Direction.Plane.HORIZONTAL) {
                Vector3f offsetPos = direction.step();
                offsetPos.mul(30f * i);
                landHeight = getHeightAt(context.chunkGenerator(), context.heightAccessor(), xPos + (int)offsetPos.x(), zPos + (int)offsetPos.z(), landHeight);
                if(landHeight - context.chunkGenerator().getMinY() < minThickness) return false;
            }
        }

        analyzeLand(context.chunkGenerator(), xPos, zPos, islandTopBottomThickness, context.heightAccessor());
        return islandTopBottomThickness.getZ() >= minThickness;
    }

    private int getHeightAt(ChunkGenerator chunkGenerator, LevelHeightAccessor heightLimitView, int xPos, int zPos, int landHeight) {
        landHeight = Math.min(landHeight, chunkGenerator.getFirstOccupiedHeight(xPos, zPos, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView));
        return landHeight;
    }

    private void analyzeLand(ChunkGenerator chunkGenerator, int xPos, int zPos, BlockPos.MutableBlockPos islandTopBottomThickness, LevelHeightAccessor heightLimitView) {
        NoiseColumn columnOfBlocks = chunkGenerator.getBaseColumn(xPos, zPos, heightLimitView);
        int minY = chunkGenerator.getMinY();
        int rangeHeight = chunkGenerator.getGenDepth();
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

    public Optional<PieceGenerator<NoneFeatureConfiguration>> generatePieces(PieceGeneratorSupplier.Context<NoneFeatureConfiguration> context, MineshaftCodeConfig config) {
        BlockPos.MutableBlockPos blockpos = new BlockPos.MutableBlockPos(context.chunkPos().getMinBlockX(), 0, context.chunkPos().getMinBlockZ());
        BlockPos.MutableBlockPos islandTopBottomThickness = new BlockPos.MutableBlockPos(Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        analyzeLand(context.chunkGenerator(), blockpos.getX(), blockpos.getZ(), islandTopBottomThickness, context.heightAccessor());

        int minThickness = RepurposedStructures.RSAllConfig.RSMineshaftsConfig.misc.endMineshaftMinIslandThickness;
        int maxY = 53;
        int minY = 15;
        if(minThickness == 0) {
            blockpos.move(Direction.UP, 35);
        }
        else{
            WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));
            random.setLargeFeatureSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);
            int structureStartHeight = random.nextInt(Math.max(islandTopBottomThickness.getZ() - minThickness + 1, 1)) + islandTopBottomThickness.getY() + (minThickness / 2);
            blockpos.move(Direction.UP, structureStartHeight);
            maxY = islandTopBottomThickness.getX() - 5;
            minY = islandTopBottomThickness.getY();
            if(maxY - minY <= 5) {
                minY = maxY - 5;
            }
        }

        ResourceLocation structureID = Registry.STRUCTURE_FEATURE.getKey(this);
        int finalMaxY = maxY;
        return PieceLimitedJigsawManager.assembleJigsawStructure(
                context,
                new JigsawConfiguration(() -> context.registryAccess().registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(config.startPool), config.structureSize),
                structureID,
                blockpos,
                false,
                false,
                maxY,
                minY,
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
}
