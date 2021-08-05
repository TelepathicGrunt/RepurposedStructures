package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.google.common.collect.Queues;
import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.JigsawPatternAccessor;
import com.telepathicgrunt.repurposedstructures.mixin.SingleJigsawPieceAccessor;
import com.telepathicgrunt.repurposedstructures.utils.BoxOctree;
import net.minecraft.block.JigsawBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.jigsaw.EmptyJigsawPiece;
import net.minecraft.world.gen.feature.jigsaw.JigsawJunction;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPatternRegistry;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.jigsaw.SingleJigsawPiece;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

/**
 * Special thanks to YUNGNICKYOUNG for allowing me to use his piece count limiting jigsaw manager!
 * Some changes were done to make it more usable by multiple structures.
 * Source: https://github.com/yungnickyoung/YUNGs-Better-Strongholds/blob/forge-1.16/src/main/java/com/yungnickyoung/minecraft/betterstrongholds/world/jigsaw/JigsawManager.java
 */
public class PieceLimitedJigsawManager {
    public static void assembleJigsawStructure(
            DynamicRegistries dynamicRegistries,
            VillageConfig jigsawConfig,
            ChunkGenerator chunkGenerator,
            TemplateManager templateManager,
            BlockPos startPos,
            List<? super StructurePiece> components,
            Random random,
            boolean doBoundaryAdjustments,
            boolean useHeightmap,
            ResourceLocation structureID,
            int maxY,
            int minY
    ) {
        // Get jigsaw pool registry
        MutableRegistry<JigsawPattern> jigsawPoolRegistry = dynamicRegistries.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY);

        // Get a random orientation for the starting piece
        Rotation rotation = Rotation.getRandom(random);

        // Get starting pool
        JigsawPattern startPool = jigsawConfig.startPool().get();
        if(startPool.size() == 0){
            RepurposedStructures.LOGGER.warn("Repurposed Structures: Empty or nonexistent start pool: {}  Crash is imminent", startPool.getName());
        }

        // Grab a random starting piece from the start pool. This is just the piece design itself, without rotation or position information.
        // Think of it as a blueprint.
        JigsawPiece startPieceBlueprint = startPool.getRandomTemplate(random);

        // Instantiate a piece using the "blueprint" we just got.
        AbstractVillagePiece startPiece = new AbstractVillagePiece(
                templateManager,
                startPieceBlueprint,
                startPos,
                startPieceBlueprint.getGroundLevelDelta(),
                rotation,
                startPieceBlueprint.getBoundingBox(templateManager, startPos, rotation)
        );

        // Store center position of starting piece's bounding box
        MutableBoundingBox pieceBoundingBox = startPiece.getBoundingBox();
        int pieceCenterX = (pieceBoundingBox.x1 + pieceBoundingBox.x0) / 2;
        int pieceCenterZ = (pieceBoundingBox.z1 + pieceBoundingBox.z0) / 2;
        int pieceCenterY = useHeightmap
                ? startPos.getY() + chunkGenerator.getFirstFreeHeight(pieceCenterX, pieceCenterZ, Heightmap.Type.WORLD_SURFACE_WG)
                : startPos.getY();

        int yAdjustment = pieceBoundingBox.y0 + startPiece.getGroundLevelDelta();
        startPiece.move(0, pieceCenterY - yAdjustment, 0);

        Map<ResourceLocation, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces = StructurePiecesBehavior.REQUIRED_PIECES_COUNT.get(structureID);
        boolean runOnce = requiredPieces == null;
        for(int attempts = 0; runOnce || doesNotHaveAllRequiredPieces(components, requiredPieces); attempts++){
            if(attempts == 100){
                RepurposedStructures.LOGGER.error(
                        "\n-------------------------------------------------------------------\n"+
                        "Repurposed Structures: Failed to create valid structure with all required pieces starting from this pool file: {}. Required pieces are: {}\n"+
                        "  Make sure the max height and min height for this structure in the config is not too close together.\n" +
                        "  If min and max height is super close together, the structure's pieces may not be able to fit in the narrow range and spawn.\n"+
                        "  Otherwise, if the min and max height ranges aren't close and this message still appears, please report the issue to Repurposed Structures's dev with latest.log file!\n\n",
                        startPool.getName(), Arrays.toString(requiredPieces.keySet().toArray()));
                break;
            }

            components.clear();
            components.add(startPiece); // Add start piece to list of pieces

            if (jigsawConfig.maxDepth() > 0) {
                AxisAlignedBB axisAlignedBB = new AxisAlignedBB(pieceCenterX - 80, pieceCenterY - 120, pieceCenterZ - 80, pieceCenterX + 80 + 1, pieceCenterY + 180 + 1, pieceCenterZ + 80 + 1);
                BoxOctree boxOctree = new BoxOctree(axisAlignedBB); // The maximum boundary of the entire structure
                boxOctree.addBox(AxisAlignedBB.of(pieceBoundingBox));
                Entry startPieceEntry = new Entry(startPiece, new MutableObject<>(boxOctree), pieceCenterY + 80, 0);

                Assembler assembler = new Assembler(jigsawPoolRegistry, jigsawConfig.maxDepth(), chunkGenerator, templateManager, components, random, requiredPieces, maxY, minY);
                assembler.availablePieces.addLast(startPieceEntry);

                while (!assembler.availablePieces.isEmpty()) {
                    Entry entry = assembler.availablePieces.removeFirst();
                    assembler.generatePiece(entry.piece, entry.parentBoxOctree, entry.topYLimit, entry.depth, doBoundaryAdjustments);
                }
            }

            if(runOnce) break;
        }
    }

    private static boolean doesNotHaveAllRequiredPieces(List<? super StructurePiece> components, Map<ResourceLocation, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces){
        Map<ResourceLocation, Integer> counter = new HashMap<>();
        requiredPieces.forEach((key, value) -> counter.put(key, value.getRequiredAmount()));
        for(Object piece : components){
            if(piece instanceof AbstractVillagePiece){
                JigsawPiece poolElement = ((AbstractVillagePiece)piece).getElement();
                if(poolElement instanceof SingleJigsawPiece){
                    ResourceLocation pieceID = ((SingleJigsawPieceAccessor) poolElement).repurposedstructures_getTemplate().left().orElse(null);
                    if(counter.containsKey(pieceID)){
                        counter.put(pieceID, counter.get(pieceID) - 1);
                    }
                }
            }
        }

        return counter.values().stream().anyMatch(count -> count > 0);
    }


    public static final class Assembler {
        private final Registry<JigsawPattern> poolRegistry;
        private final int maxDepth;
        private final ChunkGenerator chunkGenerator;
        private final TemplateManager templateManager;
        private final List<? super AbstractVillagePiece> structurePieces;
        private final Random rand;
        public final Deque<Entry> availablePieces = Queues.newArrayDeque();
        private final Map<ResourceLocation, Integer> pieceCounts;
        private final Map<ResourceLocation, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces;
        private final int maxY;
        private final int minY;

        public Assembler(Registry<JigsawPattern> poolRegistry, int maxDepth, ChunkGenerator chunkGenerator, TemplateManager templateManager, List<? super AbstractVillagePiece> structurePieces, Random rand, Map<ResourceLocation, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces, int maxY, int minY) {
            this.poolRegistry = poolRegistry;
            this.maxDepth = maxDepth;
            this.chunkGenerator = chunkGenerator;
            this.templateManager = templateManager;
            this.structurePieces = structurePieces;
            this.rand = rand;
            this.maxY = maxY;
            this.minY = minY;

            // Create map clone so we do not modify the original map.
            this.requiredPieces = requiredPieces == null ? new HashMap<>() : new HashMap<>(requiredPieces);
            this.pieceCounts = new HashMap<>(StructurePiecesBehavior.PIECES_COUNT);
            // pieceCounts will keep track of how many required pieces were spawned
            this.requiredPieces.forEach((key, value) -> this.pieceCounts.putIfAbsent(key, value.getRequiredAmount()));
        }

        public void generatePiece(AbstractVillagePiece piece, MutableObject<BoxOctree> boxOctree, int minY, int depth, boolean doBoundaryAdjustments) {
            // Collect data from params regarding piece to process
            JigsawPiece pieceBlueprint = piece.getElement();
            BlockPos piecePos = piece.getPosition();
            Rotation pieceRotation = piece.getRotation();
            MutableBoundingBox pieceBoundingBox = piece.getBoundingBox();
            MutableObject<BoxOctree> parentOctree = new MutableObject<>();
            int pieceMinY = pieceBoundingBox.y0;

            // Get list of all jigsaw blocks in this piece
            List<Template.BlockInfo> pieceJigsawBlocks = pieceBlueprint.getShuffledJigsawBlocks(this.templateManager, piecePos, pieceRotation, this.rand);

            for (Template.BlockInfo jigsawBlock : pieceJigsawBlocks) {
                // Gather jigsaw block information
                Direction direction = JigsawBlock.getFrontFacing(jigsawBlock.state);
                BlockPos jigsawBlockPos = jigsawBlock.pos;
                BlockPos jigsawBlockTargetPos = jigsawBlockPos.relative(direction);

                // Get the jigsaw block's piece pool
                ResourceLocation jigsawBlockPool = new ResourceLocation(jigsawBlock.nbt.getString("pool"));
                Optional<JigsawPattern> poolOptional = this.poolRegistry.getOptional(jigsawBlockPool);

                // Only continue if we are using the jigsaw pattern registry and if it is not empty
                if (!(poolOptional.isPresent() && (poolOptional.get().size() != 0 || Objects.equals(jigsawBlockPool, JigsawPatternRegistry.EMPTY.location())))) {
                    RepurposedStructures.LOGGER.warn("Repurposed Structures: Empty or nonexistent pool: {} which is being called from {}", jigsawBlockPool, pieceBlueprint instanceof SingleJigsawPiece ? ((SingleJigsawPieceAccessor) pieceBlueprint).repurposedstructures_getTemplate().left().get() : "not a SingleJigsawPiece class");
                    continue;
                }

                // Get the jigsaw block's fallback pool (which is a part of the pool's JSON)
                ResourceLocation jigsawBlockFallback = poolOptional.get().getFallback();
                Optional<JigsawPattern> fallbackOptional = this.poolRegistry.getOptional(jigsawBlockFallback);

                // Only continue if the fallback pool is present and valid
                if (!(fallbackOptional.isPresent() && (fallbackOptional.get().size() != 0 || Objects.equals(jigsawBlockFallback, JigsawPatternRegistry.EMPTY.location())))) {
                    RepurposedStructures.LOGGER.warn("Repurposed Structures: Empty or nonexistent pool: {} which is being called from {}", jigsawBlockFallback, pieceBlueprint instanceof SingleJigsawPiece ? ((SingleJigsawPieceAccessor) pieceBlueprint).repurposedstructures_getTemplate().left().get() : "not a SingleJigsawPiece class");
                    continue;
                }

                // Adjustments for if the target block position is inside the current piece
                // Sets which octree to use for bounds checking
                boolean isTargetInsideCurrentPiece = pieceBoundingBox.isInside(jigsawBlockTargetPos);
                int targetPieceBoundsTop;
                MutableObject<BoxOctree> octreeToUse;
                if (isTargetInsideCurrentPiece) {
                    targetPieceBoundsTop = pieceMinY;
                    octreeToUse = parentOctree;
                    if(parentOctree.getValue() == null) {
                        parentOctree.setValue(new BoxOctree(AxisAlignedBB.of(pieceBoundingBox)));
                    }
                }
                else {
                    targetPieceBoundsTop = minY;
                    octreeToUse = boxOctree;
                }

                // Process the pool pieces, randomly choosing different pieces from the pool to spawn
                if (depth != this.maxDepth) {
                    JigsawPiece generatedPiece = this.processList(new ArrayList<>(((JigsawPatternAccessor)poolOptional.get()).repurposedstructures_getRawTemplates()), doBoundaryAdjustments, jigsawBlock, jigsawBlockTargetPos, pieceMinY, jigsawBlockPos, octreeToUse, piece, depth, targetPieceBoundsTop);
                    if (generatedPiece != null) continue; // Stop here since we've already generated the piece
                }

                // Process the fallback pieces in the event none of the pool pieces work
                this.processList(new ArrayList<>(((JigsawPatternAccessor)fallbackOptional.get()).repurposedstructures_getRawTemplates()), doBoundaryAdjustments, jigsawBlock, jigsawBlockTargetPos, pieceMinY, jigsawBlockPos, octreeToUse, piece, depth, targetPieceBoundsTop);
            }
        }

        /**
         * Helper function. Searches candidatePieces for a suitable piece to spawn.
         * All other params are intended to be passed directly from {@link Assembler#generatePiece}
         * @return The piece genereated, or null if no suitable pieces were found.
         */
        private JigsawPiece processList(
                List<Pair<JigsawPiece, Integer>> candidatePieces,
                boolean doBoundaryAdjustments,
                Template.BlockInfo jigsawBlock,
                BlockPos jigsawBlockTargetPos,
                int pieceMinY,
                BlockPos jigsawBlockPos,
                MutableObject<BoxOctree> mutableObjectBoxOctree,
                AbstractVillagePiece piece,
                int depth,
                int targetPieceBoundsTop
        ) {
            JigsawPattern.PlacementBehaviour piecePlacementBehavior = piece.getElement().getProjection();
            boolean isPieceRigid = piecePlacementBehavior == JigsawPattern.PlacementBehaviour.RIGID;
            int jigsawBlockRelativeY = jigsawBlockPos.getY() - pieceMinY;
            int surfaceHeight = -1; // The y-coordinate of the surface. Only used if isPieceRigid is false.

            int totalCount = candidatePieces.stream().mapToInt(Pair::getSecond).reduce(0, Integer::sum);

            while (candidatePieces.size() > 0) {
                // Prioritize required piece if the following conditions are met:
                // 1. It's a potential candidate for this pool
                // 2. It hasn't already been placed
                // 3. We are at least certain amount of pieces away from the starting piece.
                Pair<JigsawPiece, Integer> chosenPiecePair = null;
                // Condition 2
                Optional<ResourceLocation> pieceNeededToSpawn = this.requiredPieces.keySet().stream().filter(key -> {
                    int currentCount = this.pieceCounts.get(key);
                    StructurePiecesBehavior.RequiredPieceNeeds requiredPieceNeeds = this.requiredPieces.get(key);
                    int requireCount = requiredPieceNeeds == null ? 0 : requiredPieceNeeds.getRequiredAmount();
                    int startCount = StructurePiecesBehavior.PIECES_COUNT.getOrDefault(key, requireCount);

                    return currentCount > 0 && startCount - currentCount < requireCount;
                }).findFirst();

                if (pieceNeededToSpawn.isPresent()) {
                    for (int i = 0; i < candidatePieces.size(); i++) {
                        Pair<JigsawPiece, Integer> candidatePiecePair = candidatePieces.get(i);
                        JigsawPiece candidatePiece = candidatePiecePair.getFirst();
                        if (candidatePiece instanceof SingleJigsawPiece && ((SingleJigsawPieceAccessor) candidatePiece).repurposedstructures_getTemplate().left().get().equals(pieceNeededToSpawn.get())) { // Condition 1
                            if (depth >= this.requiredPieces.get(pieceNeededToSpawn.get()).getMinDistanceFromCenter()) { // Condition 3
                                // All conditions are met. Use required piece as chosen piece.
                                chosenPiecePair = candidatePiecePair;
                            } else {
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

                    for (Pair<JigsawPiece, Integer> candidate : candidatePieces) {
                        chosenWeight -= candidate.getSecond();
                        if (chosenWeight <= 0) {
                            chosenPiecePair = candidate;
                            break;
                        }
                    }
                }

                JigsawPiece candidatePiece = chosenPiecePair.getFirst();

                // Vanilla check. Not sure on the implications of this.
                if (candidatePiece == EmptyJigsawPiece.INSTANCE) {
                    return null;
                }

                // Before performing any logic, check to ensure we haven't reached the max number of instances of this piece.
                // This logic is my own additional logic - vanilla does not offer this behavior.
                ResourceLocation pieceName = null;
                if(candidatePiece instanceof SingleJigsawPiece){
                    pieceName = ((SingleJigsawPieceAccessor) candidatePiece).repurposedstructures_getTemplate().left().get();
                    if (this.pieceCounts.containsKey(pieceName)) {
                        if (this.pieceCounts.get(pieceName) <= 0) {
                            // Remove this piece from the list of candidates and retry.
                            totalCount -= chosenPiecePair.getSecond();
                            candidatePieces.remove(chosenPiecePair);
                            continue;
                        }
                    }
                }

                // Try different rotations to see which sides of the piece are fit to be the receiving end
                for (Rotation rotation : Rotation.getShuffled(this.rand)) {
                    List<Template.BlockInfo> candidateJigsawBlocks = candidatePiece.getShuffledJigsawBlocks(this.templateManager, BlockPos.ZERO, rotation, this.rand);
                    MutableBoundingBox tempCandidateBoundingBox = candidatePiece.getBoundingBox(this.templateManager, BlockPos.ZERO, rotation);

                    // Some sort of logic for setting the candidateHeightAdjustments var if doBoundaryAdjustments.
                    // Not sure on this - personally, I never enable doBoundaryAdjustments.
                    int candidateHeightAdjustments;
                    if (doBoundaryAdjustments && tempCandidateBoundingBox.getYSpan() <= 16) {
                        candidateHeightAdjustments = candidateJigsawBlocks.stream().mapToInt((pieceCandidateJigsawBlock) -> {
                            if (!tempCandidateBoundingBox.isInside(pieceCandidateJigsawBlock.pos.relative(JigsawBlock.getFrontFacing(pieceCandidateJigsawBlock.state)))) {
                                return 0;
                            } else {
                                ResourceLocation candidateTargetPool = new ResourceLocation(pieceCandidateJigsawBlock.nbt.getString("pool"));
                                Optional<JigsawPattern> candidateTargetPoolOptional = this.poolRegistry.getOptional(candidateTargetPool);
                                Optional<JigsawPattern> candidateTargetFallbackOptional = candidateTargetPoolOptional.flatMap((p_242843_1_) -> this.poolRegistry.getOptional(p_242843_1_.getFallback()));
                                int tallestCandidateTargetPoolPieceHeight = candidateTargetPoolOptional.map((p_242842_1_) -> p_242842_1_.getMaxSize(this.templateManager)).orElse(0);
                                int tallestCandidateTargetFallbackPieceHeight = candidateTargetFallbackOptional.map((p_242840_1_) -> p_242840_1_.getMaxSize(this.templateManager)).orElse(0);
                                return Math.max(tallestCandidateTargetPoolPieceHeight, tallestCandidateTargetFallbackPieceHeight);
                            }
                        }).max().orElse(0);
                    } else {
                        candidateHeightAdjustments = 0;
                    }

                    // Check for each of the candidate's jigsaw blocks for a match
                    for (Template.BlockInfo candidateJigsawBlock : candidateJigsawBlocks) {
                        if (JigsawBlock.canAttach(jigsawBlock, candidateJigsawBlock)) {
                            BlockPos candidateJigsawBlockPos = candidateJigsawBlock.pos;
                            BlockPos candidateJigsawBlockRelativePos = new BlockPos(jigsawBlockTargetPos.getX() - candidateJigsawBlockPos.getX(), jigsawBlockTargetPos.getY() - candidateJigsawBlockPos.getY(), jigsawBlockTargetPos.getZ() - candidateJigsawBlockPos.getZ());

                            // Get the bounding box for the piece, offset by the relative position difference
                            MutableBoundingBox candidateBoundingBox = candidatePiece.getBoundingBox(this.templateManager, candidateJigsawBlockRelativePos, rotation);

                            // Determine if candidate is rigid
                            JigsawPattern.PlacementBehaviour candidatePlacementBehavior = candidatePiece.getProjection();
                            boolean isCandidateRigid = candidatePlacementBehavior == JigsawPattern.PlacementBehaviour.RIGID;

                            // Determine how much the candidate jigsaw block is off in the y direction.
                            // This will be needed to offset the candidate piece so that the jigsaw blocks line up properly.
                            int candidateJigsawBlockRelativeY = candidateJigsawBlockPos.getY();
                            int candidateJigsawYOffsetNeeded = jigsawBlockRelativeY - candidateJigsawBlockRelativeY + JigsawBlock.getFrontFacing(jigsawBlock.state).getStepY();

                            // Determine how much we need to offset the candidate piece itself in order to have the jigsaw blocks aligned.
                            // Depends on if the placement of both pieces is rigid or not
                            int adjustedCandidatePieceMinY;
                            if (isPieceRigid && isCandidateRigid) {
                                adjustedCandidatePieceMinY = pieceMinY + candidateJigsawYOffsetNeeded;
                            } else {
                                if (surfaceHeight == -1) {
                                    surfaceHeight = this.chunkGenerator.getFirstFreeHeight(jigsawBlockPos.getX(), jigsawBlockPos.getZ(), Heightmap.Type.WORLD_SURFACE_WG);
                                }

                                adjustedCandidatePieceMinY = surfaceHeight - candidateJigsawBlockRelativeY;
                            }
                            int candidatePieceYOffsetNeeded = adjustedCandidatePieceMinY - candidateBoundingBox.y0;

                            // Offset the candidate's bounding box by the necessary amount
                            MutableBoundingBox adjustedCandidateBoundingBox = candidateBoundingBox.moved(0, candidatePieceYOffsetNeeded, 0);

                            // Add this offset to the relative jigsaw block position as well
                            BlockPos adjustedCandidateJigsawBlockRelativePos = candidateJigsawBlockRelativePos.offset(0, candidatePieceYOffsetNeeded, 0);

                            // Final adjustments to the bounding box.
                            if (candidateHeightAdjustments > 0) {
                                int heightAdjustement = Math.max(candidateHeightAdjustments + 1, adjustedCandidateBoundingBox.y1 - adjustedCandidateBoundingBox.y0);
                                adjustedCandidateBoundingBox.y1 = adjustedCandidateBoundingBox.y0 + heightAdjustement;
                            }

                            // Prevent pieces from spawning above max Y or below min Y
                            if (adjustedCandidateBoundingBox.y1 > this.maxY || adjustedCandidateBoundingBox.y0 < this.minY) {
                                continue;
                            }

                            AxisAlignedBB axisAlignedBB = AxisAlignedBB.of(adjustedCandidateBoundingBox);
                            AxisAlignedBB axisAlignedBBDeflated = axisAlignedBB.deflate(0.25D); // Avoid any edge case weirdness if size is exact with bounding boxes.

                            // debugging
//                            if(!(mutableObjectBoxOctree.getValue().boundaryContains(axisAlignedBBDeflated) && !mutableObjectBoxOctree.getValue().intersectsAnyBox(axisAlignedBBDeflated))){
//                                if(piece.toString().contains("bastion")){
//                                    RepurposedStructures.LOGGER.warn(" Failed to spawn pieces. Parent: {} - General info: {} - Child: {} - General info: {} - Octree: {}", piece.getBoundingBox().toString(), piece.toString(), axisAlignedBBDeflated.toString(), candidatePiece.toString(), mutableObjectBoxOctree.toString());
//                                }
//                            }

                            // Make sure new piece fits within the chosen octree without intersecting any other piece.
                            if (mutableObjectBoxOctree.getValue().boundaryContains(axisAlignedBBDeflated) && !mutableObjectBoxOctree.getValue().intersectsAnyBox(axisAlignedBBDeflated)) {
                                mutableObjectBoxOctree.getValue().addBox(axisAlignedBB);

                                // Determine ground level delta for this new piece
                                int newPieceGroundLevelDelta = piece.getGroundLevelDelta();
                                int groundLevelDelta;
                                if (isCandidateRigid) {
                                    groundLevelDelta = newPieceGroundLevelDelta - candidateJigsawYOffsetNeeded;
                                } else {
                                    groundLevelDelta = candidatePiece.getGroundLevelDelta();
                                }

                                // Create new piece
                                AbstractVillagePiece newPiece = new AbstractVillagePiece(
                                        this.templateManager,
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
                                } else if (isCandidateRigid) {
                                    candidateJigsawBlockY = adjustedCandidatePieceMinY + candidateJigsawBlockRelativeY;
                                } else {
                                    if (surfaceHeight == -1) {
                                        surfaceHeight = this.chunkGenerator.getFirstFreeHeight(jigsawBlockPos.getX(), jigsawBlockPos.getZ(), Heightmap.Type.WORLD_SURFACE_WG);
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
                                    this.availablePieces.addLast(new Entry(newPiece, mutableObjectBoxOctree, targetPieceBoundsTop, depth + 1));
                                }
                                // Update piece count, if an entry exists for this piece
                                if (pieceName != null && this.pieceCounts.containsKey(pieceName)) {
                                    this.pieceCounts.put(pieceName, this.pieceCounts.get(pieceName) - 1);
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

    public static final class Entry {
        public final AbstractVillagePiece piece;
        public final MutableObject<BoxOctree> parentBoxOctree;
        public final int topYLimit;
        public final int depth;

        public Entry(AbstractVillagePiece piece, MutableObject<BoxOctree> parentBoxOctree, int topYLimit, int depth) {
            this.piece = piece;
            this.parentBoxOctree = parentBoxOctree;
            this.topYLimit = topYLimit;
            this.depth = depth;
        }
    }
}
