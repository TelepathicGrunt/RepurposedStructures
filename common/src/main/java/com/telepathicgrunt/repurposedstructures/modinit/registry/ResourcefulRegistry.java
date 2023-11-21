package com.telepathicgrunt.repurposedstructures.modinit.registry;

import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface ResourcefulRegistry<T> {

    <I extends T> RegistryEntry<I> register(String id, Supplier<I> supplier);

    Collection<RegistryEntry<T>> getEntries();

    default Stream<RegistryEntry<T>> stream() {
        return getEntries().stream();
    }

    default Stream<T> boundStream() {
        return stream().map(RegistryEntry::get);
    }

    void init();
}