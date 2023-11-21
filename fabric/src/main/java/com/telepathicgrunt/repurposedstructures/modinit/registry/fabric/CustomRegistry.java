package com.telepathicgrunt.repurposedstructures.modinit.registry.fabric;

import com.telepathicgrunt.repurposedstructures.modinit.registry.CustomRegistryLookup;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Iterator;

public class CustomRegistry<T> implements CustomRegistryLookup<T, T> {

    private final Registry<T> registry;

    public CustomRegistry(Registry<T> registry) {
        this.registry = registry;
    }

    @Override
    public boolean containsKey(ResourceLocation id) {
        return registry.containsKey(id);
    }

    @Override
    public boolean containsValue(T value) {
        return registry.getKey(value) != null;
    }

    @Override
    public @Nullable T get(ResourceLocation id) {
        return registry.get(id);
    }

    @Override
    public @Nullable ResourceLocation getKey(T value) {
        return registry.getKey(value);
    }

    @Override
    public Collection<T> getValues() {
        return registry.stream().toList();
    }

    @Override
    public Collection<ResourceLocation> getKeys() {
        return registry.keySet();
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return registry.iterator();
    }
}
