package com.telepathicgrunt.repurposedstructures.mixin;

import net.minecraft.resource.NamespaceResourceManager;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourceType;
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
    List<ResourcePack> rs_getPackList();

    @Accessor("type")
    ResourceType rs_getType();

    @Invoker("open")
    InputStream rs_callOpen(Identifier id, ResourcePack pack) throws IOException;
}
