package com.telepathicgrunt.repurposedstructures.mixins.world;

import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.levelgen.structure.StructureCheck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(StructureManager.class)
public interface StructureManagerAccessor {
    @Accessor("structureCheck")
    StructureCheck repurposedstructures$getStructureCheck();
}
