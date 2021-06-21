package com.telepathicgrunt.repurposedstructures.mixin;

import net.minecraft.resource.ResourceManager;
import net.minecraft.structure.StructureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(StructureManager.class)
public interface StructureManagerAccessor {
    @Accessor("resourceManager")
    ResourceManager repurposedstructures_getResourceManager();
}
