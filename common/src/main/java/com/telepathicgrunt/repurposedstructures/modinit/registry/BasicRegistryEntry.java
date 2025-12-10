package com.telepathicgrunt.repurposedstructures.modinit.registry;

import net.minecraft.resources.Identifier;

import java.util.Objects;

public class BasicRegistryEntry<T> implements RegistryEntry<T> {

    private final Identifier id;
    private final T value;

    public BasicRegistryEntry(Identifier id, T value) {
        this.id = Objects.requireNonNull(id);
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public Identifier getId() {
        return id;
    }
}
