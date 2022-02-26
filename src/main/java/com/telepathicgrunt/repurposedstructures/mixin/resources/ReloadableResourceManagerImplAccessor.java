package com.telepathicgrunt.repurposedstructures.mixin.resources;

import net.fabricmc.fabric.impl.resource.loader.GroupResourcePack;
import net.minecraft.server.packs.resources.FallbackResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(GroupResourcePack.class)
public interface ReloadableResourceManagerImplAccessor {
    @Accessor("namespacedPacks")
    Map<String, FallbackResourceManager> repurposedstructures_getNamespacedPacks();
}
