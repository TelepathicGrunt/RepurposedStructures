package com.telepathicgrunt.repurposedstructures.mixin;

import com.telepathicgrunt.repurposedstructures.world.features.structures.StrongholdModifications;
import net.minecraft.block.BlockState;
import net.minecraft.structure.StructurePiece;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(StructurePiece.class)
public class StrongholdBlockMixin {

    @ModifyVariable(method = "addBlock",
            at = @At(value = "HEAD", ordinal = 1), argsOnly = true)
    private BlockState onBlockAdded(BlockState block) {
        return StrongholdModifications.strongholdBlockConversion((StructurePiece)(Object)this, block);
    }
}