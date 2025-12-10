package com.telepathicgrunt.repurposedstructures.modinit.registry;

import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface CustomRegistryLookup<T, K extends T> extends Iterable<T> {

    boolean containsKey(Identifier id);

    boolean containsValue(T value);

    @Nullable
    T get(Identifier id);

    @Nullable
    Identifier getKey(T value);

    Collection<T> getValues();

    Collection<Identifier> getKeys();

}
