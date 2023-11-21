package com.telepathicgrunt.repurposedstructures.modinit.registry;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface CustomRegistryLookup<T, K extends T> extends Iterable<T> {

    boolean containsKey(ResourceLocation id);

    boolean containsValue(T value);

    @Nullable
    T get(ResourceLocation id);

    @Nullable
    ResourceLocation getKey(T value);

    Collection<T> getValues();

    Collection<ResourceLocation> getKeys();

}
