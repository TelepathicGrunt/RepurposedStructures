package com.telepathicgrunt.repurposedstructures.services.fabric;

import com.telepathicgrunt.repurposedstructures.modinit.registry.CustomRegistryLookup;
import com.telepathicgrunt.repurposedstructures.modinit.registry.ResourcefulRegistry;
import com.telepathicgrunt.repurposedstructures.modinit.registry.ResourcefulRegistryChild;
import com.telepathicgrunt.repurposedstructures.modinit.registry.fabric.CustomRegistry;
import com.telepathicgrunt.repurposedstructures.modinit.registry.fabric.CustomResourcefulRegistry;
import com.telepathicgrunt.repurposedstructures.services.ResourcefulRegistriesService;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import org.apache.commons.lang3.tuple.Pair;

import java.util.function.Supplier;

public class FabricResourcefulRegistriesService implements ResourcefulRegistriesService {

    @Override
    public <T> ResourcefulRegistry<T> create(ResourcefulRegistry<T> parent) {
        return new ResourcefulRegistryChild<>(parent);
    }

    @Override
    public <T> ResourcefulRegistry<T> create(Registry<T> registry, String id) {
        return new CustomResourcefulRegistry<>(registry, id);
    }

    @Override
    public <T, K extends Registry<T>> Pair<Supplier<CustomRegistryLookup<T, T>>, ResourcefulRegistry<T>> createCustomRegistryInternal(String modId, ResourceKey<K> key, boolean save, boolean sync, boolean allowModification) {
        FabricRegistryBuilder<T, MappedRegistry<T>> registry = FabricRegistryBuilder.createSimple(null, key.identifier());
        if (sync) registry.attribute(RegistryAttribute.SYNCED);
        if (allowModification) registry.attribute(RegistryAttribute.MODDED);
        MappedRegistry<T> builtRegistry = registry.buildAndRegister();
        CustomRegistry<T> customRegistry = new CustomRegistry<>(builtRegistry);
        return Pair.of(() -> customRegistry, new CustomResourcefulRegistry<>(builtRegistry, modId));
    }
}
