package com.telepathicgrunt.repurposedstructures.mixin.resources;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;
import net.minecraft.server.packs.resources.FallbackResourceManager;
import net.minecraft.server.packs.resources.SimpleReloadableResourceManager;

@Mixin(SimpleReloadableResourceManager.class)
public interface ReloadableResourceManagerImplAccessor {
    @Accessor("namespacedPacks")
    Map<String, FallbackResourceManager> repurposedstructures_getNamespacedPacks();
}
