package com.telepathicgrunt.repurposedstructures.modinit.registry.fabric;

import com.telepathicgrunt.repurposedstructures.modinit.registry.BasicRegistryEntry;
import com.telepathicgrunt.repurposedstructures.modinit.registry.RegistryEntries;
import com.telepathicgrunt.repurposedstructures.modinit.registry.RegistryEntry;
import com.telepathicgrunt.repurposedstructures.modinit.registry.ResourcefulRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

import java.util.Collection;
import java.util.function.Supplier;

public class CustomResourcefulRegistry<T> implements ResourcefulRegistry<T> {

    private final RegistryEntries<T> entries = new RegistryEntries<>();
    private final Registry<T> registry;
    private final String id;

    public CustomResourcefulRegistry(Registry<T> registry, String id) {
        this.registry = registry;
        this.id = id;
    }

    @Override
    public <I extends T> RegistryEntry<I, I> register(String id, Supplier<I> supplier) {
        I value = Registry.register(registry, new ResourceLocation(this.id, id), supplier.get());
        return entries.add(new BasicRegistryEntry<>(new ResourceLocation(this.id, id), value));
    }

    @Override
    public Collection<RegistryEntry<T, T>> getEntries() {
        return this.entries.getEntries();
    }

    @Override
    public void init() {
        // NO-OP
    }
}
