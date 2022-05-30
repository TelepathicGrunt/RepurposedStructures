package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.google.common.collect.Queues;
import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.misc.structurepiececounter.StructurePieceCountsManager;
import com.telepathicgrunt.repurposedstructures.mixin.structures.SinglePoolElementAccessor;
import com.telepathicgrunt.repurposedstructures.mixin.structures.StructurePoolAccessor;
import com.telepathicgrunt.repurposedstructures.utils.BoxOctree;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.QuartPos;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.block.JigsawBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.pools.EmptyPoolElement;
import net.minecraft.world.level.levelgen.structure.pools.JigsawJunction;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.AABB;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * Special thanks to YUNGNICKYOUNG for allowing me to use his piece count limiting jigsaw manager!
 * Some changes were done to make it more usable by multiple structures.
 * Source: https://github.com/yungnickyoung/YUNGs-Better-Strongholds/blob/fabric-1.16/src/main/java/com/yungnickyoung/minecraft/betterstrongholds/world/jigsaw/JigsawManager.java
 */
public class PieceLimitedJigsawManager {

    // Record for entries
    public record Entry(PoolElementStructurePiece piece, MutableObject<BoxOctree> boxOctreeMutableObject, int topYLimit, int depth) { }

    public static <C extends FeatureConfiguration> Optional<PieceGenerator<C>> assembleJigsawStructure(
            PieceGeneratorSupplier.Context<C> context,
            JigsawConfiguration jigsawConfig,
            ResourceLocation structureID,
            BlockPos startPos,
            boolean doBoundaryAdjustments,
            boolean useHeightmap,
            int maxY,
            int minY,
            BiConsumer<StructurePiecesBuilder, List<PoolElementStructurePiece>> structureBoundsAdjuster
    ) {
        return assembleJigsawStructure(context, jigsawConfig, structureID, startPos, doBoundaryAdjustments, useHeightmap, maxY, minY, new HashSet<>(), structureBoundsAdjuster);
    }

    public static <C extends FeatureConfiguration> Optional<PieceGenerator<C>> assembleJigsawStructure(
            PieceGeneratorSupplier.Context<C> context,
            JigsawConfiguration jigsawConfig,
            ResourceLocation structureID,
            BlockPos startPos,
            boolean doBoundaryAdjustments,
            boolean useHeightmap,
            int maxY,
            int minY,
            Set<ResourceLocation> poolsThatIgnoreBounds,
            BiConsumer<StructurePiecesBuilder, List<PoolElementStructurePiece>> structureBoundsAdjuster
    ) {
        // Get jigsaw pool registry
        Registry<StructureTemplatePool> jigsawPoolRegistry = context.registryAccess().ownedRegistryOrThrow(Registry.TEMPLATE_POOL_REGISTRY);

        // Get a random orientation for the starting piece
        WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));
        random.setLargeFeatureSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);
        Rotation rotation = Rotation.getRandom(random);

        // Get starting pool
        StructureTemplatePool startPool = jigsawConfig.startPool().value();
        if(startPool == null || startPool.size() == 0) {
            RepurposedStructures.LOGGER.warn("Repurposed Structures: Empty or nonexistent start pool in structure: {}  Crash is imminent", structureID);
            throw new RuntimeException("Repurposed Structures: Empty or nonexistent start pool in structure: " + structureID + " Crash is imminent");
        }

        // Grab a random starting piece from the start pool. This is just the piece design itself, without rotation or position information.
        // Think of it as a blueprint.
        StructurePoolElement startPieceBlueprint = startPool.getRandomTemplate(random);
        if (startPieceBlueprint == EmptyPoolElement.INSTANCE) {
            return Optional.empty();
        }

        // Instantiate a piece using the "blueprint" we just got.
        PoolElementStructurePiece startPiece = new PoolElementStructurePiece(
                context.structureManager(),
                startPieceBlueprint,
                startPos,
                startPieceBlueprint.getGroundLevelDelta(),
                rotation,
                startPieceBlueprint.getBoundingBox(context.structureManager(), startPos, rotation)
        );

        // Store center position of starting piece's bounding box
        BoundingBox pieceBoundingBox = startPiece.getBoundingBox();
        int pieceCenterX = (pieceBoundingBox.maxX() + pieceBoundingBox.minX()) / 2;
        int pieceCenterZ = (pieceBoundingBox.maxZ() + pieceBoundingBox.minZ()) / 2;
        int pieceCenterY = useHeightmap
                ? startPos.getY() + context.chunkGenerator().getFirstFreeHeight(pieceCenterX, pieceCenterZ, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor())
                : startPos.getY();

        int yAdjustment = pieceBoundingBox.minY() + startPiece.getGroundLevelDelta();
        startPiece.move(0, pieceCenterY - yAdjustment, 0);
        if (!context.validBiome().test(context.chunkGenerator().getNoiseBiome(QuartPos.fromBlock(pieceCenterX), QuartPos.fromBlock(pieceCenterY), QuartPos.fromBlock(pieceCenterZ)))) {
            return Optional.empty();
        }

        return Optional.of((structurePiecesBuilder, contextx) -> {
            List<PoolElementStructurePiece> components = new ArrayList<>();
            components.add(startPiece);
            Map<ResourceLocation, StructurePieceCountsManager.RequiredPieceNeeds> requiredPieces = RepurposedStructures.structurePieceCountsManager.getRequirePieces(structureID);
            boolean runOnce = requiredPieces == null || requiredPieces.isEmpty();
            Map<ResourceLocation, Integer> currentPieceCounter = new HashMap<>();
            for (int attempts = 0; runOnce || doesNotHaveAllRequiredPieces(components, requiredPieces, currentPieceCounter); attempts++) {
                if (attempts == 100) {
                    RepurposedStructures.LOGGER.error(
                            """
                                            
                                    -------------------------------------------------------------------
                                    Repurposed Structures: Failed to create valid structure with all required pieces starting from this pool file: {}. Required pieces failed to generate the required amount are: {}
                                      This can happen if a structure has a required piece but the structure size is set too low.
                                      However, this is most likely caused by a structure unable to spawn properly due to hitting the world's min y or max y build thresholds or a broken RS datapack.
                                      Try teleporting to: {} and see if the structure generated fine with the required structure piece or if it is indeed missing it.
                                      Please report the issue to Repurposed Structures's dev with latest.log file if the structure is not cut off by world min/max y build thresholds.
                                            
                                    """,
                            startPool.getName(), Arrays.toString(currentPieceCounter.entrySet().stream().filter(entry -> entry.getValue() > 0).toArray()), new BlockPos(pieceCenterX, pieceCenterY, pieceCenterZ));
                    break;
                }

                components.clear();
                components.add(startPiece); // Add start piece to list of pieces

                if (jigsawConfig.maxDepth() > 0) {
                    AABB axisAlignedBB = new AABB(pieceCenterX - 80, pieceCenterY - 120, pieceCenterZ - 80, pieceCenterX + 80 + 1, pieceCenterY + 180 + 1, pieceCenterZ + 80 + 1);
                    BoxOctree boxOctree = new BoxOctree(axisAlignedBB); // The maximum boundary of the entire structure
                    boxOctree.addBox(AABB.of(pieceBoundingBox));
                    Entry startPieceEntry = new Entry(startPiece, new MutableObject<>(boxOctree), pieceCenterY + 80, 0);

                    Assembler assembler = new Assembler(structureID, jigsawPoolRegistry, jigsawConfig.maxDepth(), context, components, random, requiredPieces, maxY, minY, poolsThatIgnoreBounds);
                    assembler.availablePieces.addLast(startPieceEntry);

                    while (!assembler.availablePieces.isEmpty()) {
                        Entry entry = assembler.availablePieces.removeFirst();
                        assembler.generatePiece(entry.piece, entry.boxOctreeMutableObject, entry.topYLimit, entry.depth, doBoundaryAdjustments, context.heightAccessor());
                    }
                }

                if (runOnce) break;
            }

            components.forEach(structurePiecesBuilder::addPiece);
            structureBoundsAdjuster.accept(structurePiecesBuilder, components);

            // Do not generate if out of bounds
            if(structurePiecesBuilder.getBoundingBox().maxY() > context.heightAccessor().getMaxBuildHeight()) {
                structurePiecesBuilder.clear();
            }
        });
    }

    private static boolean doesNotHaveAllRequiredPieces(List<? extends StructurePiece> components, Map<ResourceLocation, StructurePieceCountsManager.RequiredPieceNeeds> requiredPieces, Map<ResourceLocation, Integer> counter) {
        counter.clear();
        requiredPieces.forEach((key, value) -> counter.put(key, value.getRequiredAmount()));
        for(Object piece : components) {
            if(piece instanceof PoolElementStructurePiece) {
                StructurePoolElement poolElement = ((PoolElementStructurePiece)piece).getElement();
                if(poolElement instanceof SinglePoolElement) {
                    ResourceLocation pieceID = ((SinglePoolElementAccessor) poolElement).repurposedstructures_getTemplate().left().orElse(null);
                    if(counter.containsKey(pieceID)) {
                        counter.put(pieceID, counter.get(pieceID) - 1);
                    }
                }
            }
        }

        return counter.values().stream().anyMatch(count -> count > 0);
    }


    public static final class Assembler {
        private final Registry<StructureTemplatePool> poolRegistry;
        private final int maxDepth;
        private final ChunkGenerator chunkGenerator;
        private final StructureManager structureManager;
        private final List<? super PoolElementStructurePiece> structurePieces;
        private final Random rand;
        public final Deque<Entry> availablePieces = Queues.newArrayDeque();
        private final Map<ResourceLocation, Integer> currentPieceCounts;
        private final Map<ResourceLocation, Integer> maximumPieceCounts;
        private final Map<ResourceLocation, StructurePieceCountsManager.RequiredPieceNeeds> requiredPieces;
        private final int maxY;
        private final int minY;
        private final Set<ResourceLocation> poolsThatIgnoreBounds;

        public <C extends FeatureConfiguration> Assembler(ResourceLocation structureID, Registry<StructureTemplatePool> poolRegistry, int maxDepth, PieceGeneratorSupplier.Context<C> context, List<? super PoolElementStructurePiece> structurePieces, Random rand, Map<ResourceLocation, StructurePieceCountsManager.RequiredPieceNeeds> requiredPieces, int maxY, int minY, Set<ResourceLocation> poolsThatIgnoreBounds) {
            this.poolRegistry = poolRegistry;
            this.maxDepth = maxDepth;
            this.chunkGenerator = context.chunkGenerator();
            this.structureManager = context.structureManager();
            this.structurePieces = structurePieces;
            this.rand = rand;
            this.maxY = maxY;
            this.minY = minY;

            // Create map clone so we do not modify the original map.
            this.requiredPieces = requiredPieces == null ? new HashMap<>() : new HashMap<>(requiredPieces);
            this.maximumPieceCounts = new HashMap<>(RepurposedStructures.structurePieceCountsManager.getMaximumCountForPieces(structureID));
            this.poolsThatIgnoreBounds = poolsThatIgnoreBounds;

            // pieceCounts will keep track of how many of the pieces we are checking were spawned
            this.currentPieceCounts = new HashMap<>();
            this.requiredPieces.forEach((key, value) -> this.currentPieceCounts.putIfAbsent(key, 0));
            this.maximumPieceCounts.forEach((key, value) -> this.currentPieceCounts.putIfAbsent(key, 0));
        }

        public void generatePiece(PoolElementStructurePiece piece, MutableObject<BoxOctree> boxOctree, int minY, int depth, boolean doBoundaryAdjustments, LevelHeightAccessor heightLimitView) {
            // Collect data from params regarding piece to process
            StructurePoolElement pieceBlueprint = piece.getElement();
            BlockPos piecePos = piece.getPosition();
            Rotation pieceRotation = piece.getRotation();
            BoundingBox pieceBoundingBox = piece.getBoundingBox();
            int pieceMinY = pieceBoundingBox.minY();
            MutableObject<BoxOctree> parentOctree = new MutableObject<>();

            // Get list of all jigsaw blocks in this piece
            List<StructureTemplate.StructureBlockInfo> pieceJigsawBlocks = pieceBlueprint.getShuffledJigsawBlocks(this.structureManager, piecePos, pieceRotation, this.rand);

            for (StructureTemplate.StructureBlockInfo jigsawBlock : pieceJigsawBlocks) {
                // Gather jigsaw block information
                Direction direction = JigsawBlock.getFrontFacing(jigsawBlock.state);
                BlockPos jigsawBlockPos = jigsawBlock.pos;
                BlockPos jigsawBlockTargetPos = jigsawBlockPos.relative(direction);

                // Get the jigsaw block's piece pool
                ResourceLocation jigsawBlockPool = new ResourceLocation(jigsawBlock.nbt.getString("pool"));
                Optional<StructureTemplatePool> poolOptional = this.poolRegistry.getOptional(jigsawBlockPool);

                // Only continue if we are using the jigsaw pattern registry and if it is not empty
                if (!(poolOptional.isPresent() && (poolOptional.get().size() != 0 || Objects.equals(jigsawBlockPool, Pools.EMPTY.location())))) {
                    RepurposedStructures.LOGGER.warn("Repurposed Structures: Empty or nonexistent pool: {} which is being called from {}", jigsawBlockPool, pieceBlueprint instanceof SinglePoolElement ? ((SinglePoolElementAccessor) pieceBlueprint).repurposedstructures_getTemplate().left().get() : "not a SinglePoolElement class");
                    continue;
                }

                // Get the jigsaw block's fallback pool (which is a part of the pool's JSON)
                ResourceLocation jigsawBlockFallback = poolOptional.get().getFallback();
                Optional<StructureTemplatePool> fallbackOptional = this.poolRegistry.getOptional(jigsawBlockFallback);

                // Only continue if the fallback pool is present and valid
                if (!(fallbackOptional.isPresent() && (fallbackOptional.get().size() != 0 || Objects.equals(jigsawBlockFallback, Pools.EMPTY.location())))) {
                    RepurposedStructures.LOGGER.warn("Repurposed Structures: Empty or nonexistent pool: {} which is being called from {}", jigsawBlockFallback, pieceBlueprint instanceof SinglePoolElement ? ((SinglePoolElementAccessor) pieceBlueprint).repurposedstructures_getTemplate().left().get() : "not a SinglePoolElement class");
                    continue;
                }

                // Adjustments for if the target block position is inside the current piece
                boolean isTargetInsideCurrentPiece = pieceBoundingBox.isInside(jigsawBlockTargetPos);
                int targetPieceBoundsTop;
                MutableObject<BoxOctree> octreeToUse;
                if (isTargetInsideCurrentPiece) {
                    octreeToUse = parentOctree;
                    targetPieceBoundsTop = pieceMinY;
                    if (parentOctree.getValue() == null) {
                        parentOctree.setValue(new BoxOctree(AABB.of(pieceBoundingBox)));
                    }
                }
                else {
                    octreeToUse = boxOctree;
                    targetPieceBoundsTop = minY;
                }

                // Process the pool pieces, randomly choosing different pieces from the pool to spawn
                if (depth != this.maxDepth) {
                    StructurePoolElement generatedPiece = this.processList(new ArrayList<>(((StructurePoolAccessor)poolOptional.get()).repurposedstructures_getRawTemplates()), doBoundaryAdjustments, jigsawBlock, jigsawBlockTargetPos, pieceMinY, jigsawBlockPos, octreeToUse, piece, depth, targetPieceBoundsTop, heightLimitView, false);
                    if (generatedPiece != null) continue; // Stop here since we've already generated the piece
                }

                // Process the fallback pieces in the event none of the pool pieces work
                boolean ignoreBounds = false;
                if(poolsThatIgnoreBounds != null) {
                    ignoreBounds = poolsThatIgnoreBounds.contains(jigsawBlockFallback);
                }
                this.processList(new ArrayList<>(((StructurePoolAccessor)fallbackOptional.get()).repurposedstructures_getRawTemplates()), doBoundaryAdjustments, jigsawBlock, jigsawBlockTargetPos, pieceMinY, jigsawBlockPos, octreeToUse, piece, depth, targetPieceBoundsTop, heightLimitView, ignoreBounds);
            }
        }

        /**
         * Helper function. Searches candidatePieces for a suitable piece to spawn.
         * All other params are intended to be passed directly from {@link Assembler#generatePiece}
         * @return The piece genereated, or null if no suitable pieces were found.
         */
        private StructurePoolElement processList(
                List<Pair<StructurePoolElement, Integer>> candidatePieces,
                boolean doBoundaryAdjustments,
                StructureTemplate.StructureBlockInfo jigsawBlock,
                BlockPos jigsawBlockTargetPos,
                int pieceMinY,
                BlockPos jigsawBlockPos,
                MutableObject<BoxOctree> boxOctreeMutableObject,
                PoolElementStructurePiece piece,
                int depth,
                int targetPieceBoundsTop,
                LevelHeightAccessor heightLimitView,
                boolean ignoreBounds
        ) {
            StructureTemplatePool.Projection piecePlacementBehavior = piece.getElement().getProjection();
            boolean isPieceRigid = piecePlacementBehavior == StructureTemplatePool.Projection.RIGID;
            int jigsawBlockRelativeY = jigsawBlockPos.getY() - pieceMinY;
            int surfaceHeight = -1; // The y-coordinate of the surface. Only used if isPieceRigid is false.

            int totalCount = candidatePieces.stream().mapToInt(Pair::getSecond).reduce(0, Integer::sum);

            while (candidatePieces.size() > 0) {
                // Prioritize required piece if the following conditions are met:
                // 1. It's a potential candidate for this pool
                // 2. It hasn't already been placed
                // 3. We are at least certain amount of pieces away from the starting piece.
                Pair<StructurePoolElement, Integer> chosenPiecePair = null;
                // Condition 2
                Optional<ResourceLocation> pieceNeededToSpawn = this.requiredPieces.keySet().stream().filter(key -> {
                    int currentCount = this.currentPieceCounts.get(key);
                    StructurePieceCountsManager.RequiredPieceNeeds requiredPieceNeeds = this.requiredPieces.get(key);
                    int requireCount = requiredPieceNeeds == null ? 0 : requiredPieceNeeds.getRequiredAmount();
                    return currentCount < requireCount;
                }).findFirst();

                if (pieceNeededToSpawn.isPresent()) {
                    for (int i = 0; i < candidatePieces.size(); i++) {
                        Pair<StructurePoolElement, Integer> candidatePiecePair = candidatePieces.get(i);
                        StructurePoolElement candidatePiece = candidatePiecePair.getFirst();
                        if (candidatePiece instanceof SinglePoolElement && ((SinglePoolElementAccessor) candidatePiece).repurposedstructures_getTemplate().left().get().equals(pieceNeededToSpawn.get())) { // Condition 1
                            if (depth >= Math.min(maxDepth - 1, this.requiredPieces.get(pieceNeededToSpawn.get()).getMinDistanceFromCenter())) { // Condition 3
                                // All conditions are met. Use required piece  as chosen piece.
                                chosenPiecePair = candidatePiecePair;
                            }
                            else {
                                // If not far enough from starting room, remove the required piece from the list
                                totalCount -= candidatePiecePair.getSecond();
                                candidatePieces.remove(candidatePiecePair);
                            }
                            break;
                        }
                    }
                }

                // Choose piece if required piece wasn't selected
                if (chosenPiecePair == null) {
                    int chosenWeight = rand.nextInt(totalCount) + 1;

                    for (Pair<StructurePoolElement, Integer> candidate : candidatePieces) {
                        chosenWeight -= candidate.getSecond();
                        if (chosenWeight <= 0) {
                            chosenPiecePair = candidate;
                            break;
                        }
                    }
                }

                StructurePoolElement candidatePiece = chosenPiecePair.getFirst();

                // Vanilla check. Not sure on the implications of this.
                if (candidatePiece == EmptyPoolElement.INSTANCE) {
                    return null;
                }

                // Before performing any logic, check to ensure we haven't reached the max number of instances of this piece.
                // This logic is my own additional logic - vanilla does not offer this behavior.
                ResourceLocation pieceName = null;
                if(candidatePiece instanceof SinglePoolElement) {
                    pieceName = ((SinglePoolElementAccessor) candidatePiece).repurposedstructures_getTemplate().left().get();
                    if (this.currentPieceCounts.containsKey(pieceName) && this.maximumPieceCounts.containsKey(pieceName)) {
                        if (this.currentPieceCounts.get(pieceName) >= this.maximumPieceCounts.get(pieceName)) {
                            // Remove this piece from the list of candidates and retry.
                            totalCount -= chosenPiecePair.getSecond();
                            candidatePieces.remove(chosenPiecePair);
                            continue;
                        }
                    }
                }

                // Try different rotations to see which sides of the piece are fit to be the receiving end
                for (Rotation rotation : Rotation.getShuffled(this.rand)) {
                    List<StructureTemplate.StructureBlockInfo> candidateJigsawBlocks = candidatePiece.getShuffledJigsawBlocks(this.structureManager, BlockPos.ZERO, rotation, this.rand);
                    BoundingBox tempCandidateBoundingBox = candidatePiece.getBoundingBox(this.structureManager, BlockPos.ZERO, rotation);

                    // Some sort of logic for setting the candidateHeightAdjustments var if doBoundaryAdjustments.
                    // Not sure on this - personally, I never enable doBoundaryAdjustments.
                    int candidateHeightAdjustments;
                    if (doBoundaryAdjustments && tempCandidateBoundingBox.getYSpan() <= 16) {
                        candidateHeightAdjustments = candidateJigsawBlocks.stream().mapToInt((pieceCandidateJigsawBlock) -> {
                            if (!tempCandidateBoundingBox.isInside(pieceCandidateJigsawBlock.pos.relative(JigsawBlock.getFrontFacing(pieceCandidateJigsawBlock.state)))) {
                                return 0;
                            }
                            else {
                                ResourceLocation candidateTargetPool = new ResourceLocation(pieceCandidateJigsawBlock.nbt.getString("pool"));
                                Optional<StructureTemplatePool> candidateTargetPoolOptional = this.poolRegistry.getOptional(candidateTargetPool);
                                Optional<StructureTemplatePool> candidateTargetFallbackOptional = candidateTargetPoolOptional.flatMap((p_242843_1_) -> this.poolRegistry.getOptional(p_242843_1_.getFallback()));
                                int tallestCandidateTargetPoolPieceHeight = candidateTargetPoolOptional.map((p_242842_1_) -> p_242842_1_.getMaxSize(this.structureManager)).orElse(0);
                                int tallestCandidateTargetFallbackPieceHeight = candidateTargetFallbackOptional.map((p_242840_1_) -> p_242840_1_.getMaxSize(this.structureManager)).orElse(0);
                                return Math.max(tallestCandidateTargetPoolPieceHeight, tallestCandidateTargetFallbackPieceHeight);
                            }
                        }).max().orElse(0);
                    } 
                    else {
                        candidateHeightAdjustments = 0;
                    }

                    // Check for each of the candidate's jigsaw blocks for a match
                    for (StructureTemplate.StructureBlockInfo candidateJigsawBlock : candidateJigsawBlocks) {
                        if (GeneralUtils.canJigsawsAttach(jigsawBlock, candidateJigsawBlock)) {
                            BlockPos candidateJigsawBlockPos = candidateJigsawBlock.pos;
                            BlockPos candidateJigsawBlockRelativePos = new BlockPos(jigsawBlockTargetPos.getX() - candidateJigsawBlockPos.getX(), jigsawBlockTargetPos.getY() - candidateJigsawBlockPos.getY(), jigsawBlockTargetPos.getZ() - candidateJigsawBlockPos.getZ());

                            // Get the bounding box for the piece, offset by the relative position difference
                            BoundingBox candidateBoundingBox = candidatePiece.getBoundingBox(this.structureManager, candidateJigsawBlockRelativePos, rotation);

                            // Determine if candidate is rigid
                            StructureTemplatePool.Projection candidatePlacementBehavior = candidatePiece.getProjection();
                            boolean isCandidateRigid = candidatePlacementBehavior == StructureTemplatePool.Projection.RIGID;

                            // Determine how much the candidate jigsaw block is off in the y direction.
                            // This will be needed to offset the candidate piece so that the jigsaw blocks line up properly.
                            int candidateJigsawBlockRelativeY = candidateJigsawBlockPos.getY();
                            int candidateJigsawYOffsetNeeded = jigsawBlockRelativeY - candidateJigsawBlockRelativeY + JigsawBlock.getFrontFacing(jigsawBlock.state).getStepY();

                            // Determine how much we need to offset the candidate piece itself in order to have the jigsaw blocks aligned.
                            // Depends on if the placement of both pieces is rigid or not
                            int adjustedCandidatePieceMinY;
                            if (isPieceRigid && isCandidateRigid) {
                                adjustedCandidatePieceMinY = pieceMinY + candidateJigsawYOffsetNeeded;
                            }
                            else {
                                if (surfaceHeight == -1) {
                                    surfaceHeight = this.chunkGenerator.getFirstFreeHeight(jigsawBlockPos.getX(), jigsawBlockPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
                                }

                                adjustedCandidatePieceMinY = surfaceHeight - candidateJigsawBlockRelativeY;
                            }
                            int candidatePieceYOffsetNeeded = adjustedCandidatePieceMinY - candidateBoundingBox.minY();

                            // Offset the candidate's bounding box by the necessary amount
                            BoundingBox adjustedCandidateBoundingBox = candidateBoundingBox.moved(0, candidatePieceYOffsetNeeded, 0);

                            // Add this offset to the relative jigsaw block position as well
                            BlockPos adjustedCandidateJigsawBlockRelativePos = candidateJigsawBlockRelativePos.offset(0, candidatePieceYOffsetNeeded, 0);

                            // Final adjustments to the bounding box.
                            if (candidateHeightAdjustments > 0) {
                                int k2 = Math.max(candidateHeightAdjustments + 1, adjustedCandidateBoundingBox.maxY() - adjustedCandidateBoundingBox.minY());
                                adjustedCandidateBoundingBox.encapsulate(new BlockPos(adjustedCandidateBoundingBox.minX(), adjustedCandidateBoundingBox.minY() + k2, adjustedCandidateBoundingBox.minZ()));
                            }

                            // Prevent pieces from spawning above max Y or below min Y
                            if (adjustedCandidateBoundingBox.maxY() > this.maxY || adjustedCandidateBoundingBox.minY() < this.minY) {
                                continue;
                            }

                            AABB axisAlignedBB = AABB.of(adjustedCandidateBoundingBox);
                            AABB axisAlignedBBDeflated = axisAlignedBB.deflate(0.25D);
                            boolean validBounds = false;

                            // Make sure new piece fits within the chosen octree without intersecting any other piece.
                            if (ignoreBounds || (boxOctreeMutableObject.getValue().boundaryContains(axisAlignedBBDeflated) && !boxOctreeMutableObject.getValue().intersectsAnyBox(axisAlignedBBDeflated))) {
                                boxOctreeMutableObject.getValue().addBox(axisAlignedBB);
                                validBounds = true;
                            }

                            if (validBounds) {

                                // Determine ground level delta for this new piece
                                int newPieceGroundLevelDelta = piece.getGroundLevelDelta();
                                int groundLevelDelta;
                                if (isCandidateRigid) {
                                    groundLevelDelta = newPieceGroundLevelDelta - candidateJigsawYOffsetNeeded;
                                }
                                else {
                                    groundLevelDelta = candidatePiece.getGroundLevelDelta();
                                }

                                // Create new piece
                                PoolElementStructurePiece newPiece = new PoolElementStructurePiece(
                                        this.structureManager,
                                        candidatePiece,
                                        adjustedCandidateJigsawBlockRelativePos,
                                        groundLevelDelta,
                                        rotation,
                                        adjustedCandidateBoundingBox
                                );

                                // Determine actual y-value for the new jigsaw block
                                int candidateJigsawBlockY;
                                if (isPieceRigid) {
                                    candidateJigsawBlockY = pieceMinY + jigsawBlockRelativeY;
                                }
                                else if (isCandidateRigid) {
                                    candidateJigsawBlockY = adjustedCandidatePieceMinY + candidateJigsawBlockRelativeY;
                                }
                                else {
                                    if (surfaceHeight == -1) {
                                        surfaceHeight = this.chunkGenerator.getFirstFreeHeight(jigsawBlockPos.getX(), jigsawBlockPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
                                    }

                                    candidateJigsawBlockY = surfaceHeight + candidateJigsawYOffsetNeeded / 2;
                                }

                                // Add the junction to the existing piece
                                piece.addJunction(
                                        new JigsawJunction(
                                                jigsawBlockTargetPos.getX(),
                                                candidateJigsawBlockY - jigsawBlockRelativeY + newPieceGroundLevelDelta,
                                                jigsawBlockTargetPos.getZ(),
                                                candidateJigsawYOffsetNeeded,
                                                candidatePlacementBehavior)
                                );

                                // Add the junction to the new piece
                                newPiece.addJunction(
                                        new JigsawJunction(
                                                jigsawBlockPos.getX(),
                                                candidateJigsawBlockY - candidateJigsawBlockRelativeY + groundLevelDelta,
                                                jigsawBlockPos.getZ(),
                                                -candidateJigsawYOffsetNeeded,
                                                piecePlacementBehavior)
                                );

                                // Add the piece
                                this.structurePieces.add(newPiece);
                                if (depth + 1 <= this.maxDepth) {
                                    this.availablePieces.addLast(new Entry(newPiece, boxOctreeMutableObject, targetPieceBoundsTop, depth + 1));
                                }
                                // Update piece count, if an entry exists for this piece
                                if (pieceName != null && this.currentPieceCounts.containsKey(pieceName)) {
                                    this.currentPieceCounts.put(pieceName, this.currentPieceCounts.get(pieceName) + 1);
                                }
                                return candidatePiece;
                            }
                        }
                    }
                }
                totalCount -= chosenPiecePair.getSecond();
                candidatePieces.remove(chosenPiecePair);
            }
            return null;
        }
    }
}
