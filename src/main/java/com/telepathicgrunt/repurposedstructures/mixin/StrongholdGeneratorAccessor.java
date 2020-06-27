package com.telepathicgrunt.repurposedstructures.mixin;

import net.minecraft.structure.StrongholdGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(StrongholdGenerator.class)
public interface StrongholdGeneratorAccessor {

    @Accessor("ALL_PIECE_SETTINGS")
    static StrongholdGenerator.PieceSetting[] getALL_PIECE_SETTINGS() {
        throw new UnsupportedOperationException();
    }
}