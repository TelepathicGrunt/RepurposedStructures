package com.telepathicgrunt.repurposedstructures.mixin.structures;

import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(StructureManager.class)
public interface TemplateManagerAccessor {
    @Accessor("resourceManager")
    ResourceManager repurposedstructures_getResourceManager();
}
