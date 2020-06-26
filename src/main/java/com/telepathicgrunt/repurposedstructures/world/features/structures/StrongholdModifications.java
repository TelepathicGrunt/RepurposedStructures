package com.telepathicgrunt.repurposedstructures.world.features.structures;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.structure.StrongholdGenerator;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.math.BlockBox;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

public class StrongholdModifications {
//    private static final StrongholdGenerator.PieceSetting[] ALL_PIECE_SETTINGS = new StrongholdGenerator.PieceSetting[]{
//            new StrongholdGenerator.PieceSetting(StrongholdGenerator.Corridor.class, 40, 0),
//            new StrongholdGenerator.PieceSetting(StrongholdGenerator.PrisonHall.class, 5, 5),
//            new StrongholdGenerator.PieceSetting(StrongholdGenerator.LeftTurn.class, 20, 0),
//            new StrongholdGenerator.PieceSetting(StrongholdGenerator.RightTurn.class, 20, 0),
//            new StrongholdGenerator.PieceSetting(StrongholdGenerator.SquareRoom.class, 10, 6),
//            new StrongholdGenerator.PieceSetting(StrongholdGenerator.Stairs.class, 5, 5),
//            new StrongholdGenerator.PieceSetting(StrongholdGenerator.SpiralStaircase.class, 5, 5),
//            new StrongholdGenerator.PieceSetting(StrongholdGenerator.FiveWayCrossing.class, 5, 4),
//            new StrongholdGenerator.PieceSetting(StrongholdGenerator.ChestCorridor.class, 5, 4),
//            new StrongholdGenerator.PieceSetting(StrongholdGenerator.Library.class, 10, 2) {
//                public boolean canGenerate(int depth) {
//                    return super.canGenerate(depth) && depth > 4;
//                }
//            },
//            new StrongholdGenerator.PieceSetting(StrongholdGenerator.PortalRoom.class, 20, 1) {
//                public boolean canGenerate(int depth) {
//                    return super.canGenerate(depth) && depth > 5;
//                }
//            }};
//    private static final RSStrongholdPieces.PieceWeight[] PIECE_WEIGHTS = new RSStrongholdPieces.PieceWeight[] {
//            new StrongholdGenerator.PieceWeight(RSStrongholdPieces.Straight.class, 40, 0),
//            new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.Prison.class, 5, 8),
//            new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.LeftTurn.class, 20, 0),
//            new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.RightTurn.class, 20, 0),
//            new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.RoomCrossing.class, 10, 9),
//            new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.StairsStraight.class, 5, 7),
//            new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.Stairs.class, 5, 7),
//            new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.Crossing.class, 5, 7),
//            new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.ChestCorridor.class, 5, 16),
//            new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.Library.class, 10, 6) {
//                @Override
//                public boolean canSpawnMoreStructures(int distanceFromStart) {
//                    return super.canSpawnMoreStructures(distanceFromStart) && distanceFromStart > 4;
//                }
//            },
//            new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.PortalRoom.class, 20, 1) {
//                @Override
//                public boolean canSpawnMoreStructures(int distanceFromStart) {
//                    return this.instancesSpawned < 1 && distanceFromStart > 5;
//                }
//
//                @Override
//                public boolean canSpawnMoreStructures() {
//                    return this.instancesSpawned < 1;
//                }
//            }
//    };

    private static final Map<Block, Block> NETHER_BLOCK_MAP;
    static {
        NETHER_BLOCK_MAP = new HashMap<Block, Block>();
        NETHER_BLOCK_MAP.put(Blocks.STONE_BRICKS, Blocks.NETHER_BRICKS);
        NETHER_BLOCK_MAP.put(Blocks.STONE_BRICK_SLAB, Blocks.NETHER_BRICK_SLAB);
        NETHER_BLOCK_MAP.put(Blocks.STONE_BRICK_STAIRS, Blocks.NETHER_BRICK_STAIRS);
        NETHER_BLOCK_MAP.put(Blocks.COBBLESTONE, Blocks.SOUL_SAND);
        NETHER_BLOCK_MAP.put(Blocks.COBBLESTONE_STAIRS, Blocks.RED_NETHER_BRICK_STAIRS);
        NETHER_BLOCK_MAP.put(Blocks.COBBLESTONE_SLAB, Blocks.RED_NETHER_BRICK_SLAB);
        NETHER_BLOCK_MAP.put(Blocks.STONE_SLAB, Blocks.RED_NETHER_BRICK_SLAB);
        NETHER_BLOCK_MAP.put(Blocks.IRON_BARS, Blocks.NETHER_BRICK_FENCE);
        NETHER_BLOCK_MAP.put(Blocks.OAK_PLANKS, Blocks.DARK_OAK_PLANKS);
        NETHER_BLOCK_MAP.put(Blocks.OAK_FENCE, Blocks.DARK_OAK_FENCE);
        NETHER_BLOCK_MAP.put(Blocks.WATER, Blocks.LAVA);
        NETHER_BLOCK_MAP.put(Blocks.WALL_TORCH, Blocks.REDSTONE_WALL_TORCH);
        NETHER_BLOCK_MAP.put(Blocks.TORCH, Blocks.REDSTONE_TORCH);
    }

    public static BlockState strongholdBlockConversion(StructurePiece piece, BlockState block){
//        if(piece instanceof StrongholdGenerator.Piece){
//
//            return
//        }

        return block;
    }
}