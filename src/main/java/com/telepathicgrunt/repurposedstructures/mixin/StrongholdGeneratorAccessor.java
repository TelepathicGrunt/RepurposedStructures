package com.telepathicgrunt.repurposedstructures.mixin;

import net.minecraft.world.gen.feature.structure.StrongholdPieces;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(StrongholdPieces.class)
public interface StrongholdGeneratorAccessor {

    @Accessor("PIECE_WEIGHTS")
    static StrongholdPieces.PieceWeight[] rs_getPIECE_WEIGHTS() {
        throw new UnsupportedOperationException();
    }
}