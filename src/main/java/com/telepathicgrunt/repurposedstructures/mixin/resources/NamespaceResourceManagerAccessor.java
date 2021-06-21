package com.telepathicgrunt.repurposedstructures.mixin.resources;

import net.minecraft.resource.NamespaceResourceManager;
import net.minecraft.resource.ResourcePack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Mixin(NamespaceResourceManager.class)
public interface NamespaceResourceManagerAccessor {
    @Accessor("packList")
    List<ResourcePack> repurposedstructures_getPackList();

    @Invoker("open")
    InputStream repurposedstructures_callOpen(Identifier id, ResourcePack pack) throws IOException;
}
