package com.telepathicgrunt.repurposedstructures.mixins.structures;

import net.minecraft.world.level.levelgen.structure.pools.JigsawJunction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(JigsawJunction.class)
public interface JigsawJunctionAccessor {
    @Mutable
    @Accessor("sourceX")
    void setSourceX(int sourceX);

    @Mutable
    @Accessor("sourceGroundY")
    void setSourceGroundY(int sourceGroundY);

    @Mutable
    @Accessor("sourceZ")
    void setSourceZ(int sourceZ);
}
