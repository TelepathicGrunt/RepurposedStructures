package com.telepathicgrunt.repurposedstructures.mixin.resources;

import net.minecraft.server.packs.resources.FallbackResourceManager;
import net.minecraft.server.packs.resources.MultiPackResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(MultiPackResourceManager.class)
public interface ReloadableResourceManagerImplAccessor {
    @Accessor("namespacedManagers")
    Map<String, FallbackResourceManager> repurposedstructures_getNamespacedManagers();
}
