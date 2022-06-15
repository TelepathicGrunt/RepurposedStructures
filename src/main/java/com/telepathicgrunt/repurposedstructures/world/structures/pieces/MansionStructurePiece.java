package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.telepathicgrunt.repurposedstructures.mixin.structures.PoolElementStructurePieceAccessor;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructurePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class MansionStructurePiece extends PoolElementStructurePiece {

    public final String mansionType;
    public final BlockState foundationBlock;
    public final boolean pillarOnlyToLand;

    public MansionStructurePiece(PoolElementStructurePiece poolElementStructurePiece, String mansionType, BlockState foundationBlock, boolean pillarOnlyToLand) {
        super(((PoolElementStructurePieceAccessor)poolElementStructurePiece).getStructureManager(),
                poolElementStructurePiece.getElement(),
                poolElementStructurePiece.getPosition(),
                poolElementStructurePiece.getGroundLevelDelta(),
                poolElementStructurePiece.getRotation(),
                poolElementStructurePiece.getBoundingBox());
        this.mansionType = mansionType;
        this.foundationBlock = foundationBlock;
        this.pillarOnlyToLand = pillarOnlyToLand;
    }

    public MansionStructurePiece(StructureTemplateManager StructureTemplateManager, StructurePoolElement structurePoolElement, BlockPos blockPos, int groundLevelDelta, Rotation rotation, BoundingBox boundingBox, String mansionType, BlockState foundationBlock, boolean pillarOnlyToLand) {
        super(StructureTemplateManager, structurePoolElement, blockPos, groundLevelDelta, rotation, boundingBox);
        this.mansionType = mansionType;
        this.foundationBlock = foundationBlock;
        this.pillarOnlyToLand = pillarOnlyToLand;
    }

    public MansionStructurePiece(StructurePieceSerializationContext context, CompoundTag tag) {
        super(context, tag);
        this.mansionType = tag.getString("mansion_type");
        this.foundationBlock = NbtUtils.readBlockState(tag.getCompound("foundation_block"));
        this.pillarOnlyToLand = tag.getBoolean("pillar_only_to_land");
    }

    @Override
    public StructurePieceType getType() {
        return RSStructurePieces.MANSION_STRUCTURE_PIECE;
    }

    @Override
    protected void addAdditionalSaveData(StructurePieceSerializationContext structurePieceSerializationContext, CompoundTag compoundTag) {
        compoundTag.putString("mansion_type", this.mansionType);
        compoundTag.put("foundation_block", NbtUtils.writeBlockState(this.foundationBlock));
        compoundTag.putBoolean("pillar_only_to_land", this.pillarOnlyToLand);
        super.addAdditionalSaveData(structurePieceSerializationContext, compoundTag);
    }

    @Override
    public String toString() {
        return String.format("<%s | %s | %s | %s | %s | %s | %s >", this.getClass().getSimpleName(), this.position, this.rotation, this.element, this.mansionType, this.foundationBlock, this.pillarOnlyToLand);
    }
}
