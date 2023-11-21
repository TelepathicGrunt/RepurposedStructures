package com.telepathicgrunt.repurposedstructures.modinit.registry;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class RegistryEntries<T> {

    private final List<RegistryEntry<T, T>> entries = new ArrayList<>();

    public <I extends T> RegistryEntry<I, I> add(RegistryEntry<I, I> entry) {
        //noinspection unchecked
        entries.add((RegistryEntry<T, T>) entry);
        return entry;
    }

    public List<RegistryEntry<T, T>> getEntries() {
        return ImmutableList.copyOf(entries);
    }

}