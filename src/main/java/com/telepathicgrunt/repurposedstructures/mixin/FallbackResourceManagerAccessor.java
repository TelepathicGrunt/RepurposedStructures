package com.telepathicgrunt.repurposedstructures.mixin;

import net.minecraft.resources.FallbackResourceManager;
import net.minecraft.resources.IResourcePack;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Mixin(FallbackResourceManager.class)
public interface FallbackResourceManagerAccessor {
    @Accessor("fallbacks")
    List<IResourcePack> rs_getPackList();

    @Invoker("getWrappedResource")
    InputStream rs_callGetWrappedResource(ResourceLocation id, IResourcePack pack) throws IOException;
}
