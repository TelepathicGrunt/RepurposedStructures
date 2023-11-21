package com.telepathicgrunt.repurposedstructures.modinit.registry;

import java.util.Collection;
import java.util.function.Supplier;

public class ResourcefulRegistryChild<T> implements ResourcefulRegistry<T> {

    private final ResourcefulRegistry<T> parent;
    private final RegistryEntries<T> entries = new RegistryEntries<>();

    public ResourcefulRegistryChild(ResourcefulRegistry<T> parent) {
        this.parent = parent;
    }

    @Override
    public <I extends T> RegistryEntry<I, I> register(String id, Supplier<I> supplier) {
        return this.entries.add(parent.register(id, supplier));
    }

    @Override
    public Collection<RegistryEntry<T, T>> getEntries() {
        return entries.getEntries();
    }

    @Override
    public void init() {
        //NO-OP
    }
}