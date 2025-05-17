package com.telepathicgrunt.repurposedstructures.services;

import com.telepathicgrunt.repurposedstructures.modinit.registry.CustomRegistryLookup;
import com.telepathicgrunt.repurposedstructures.modinit.registry.ResourcefulRegistry;
import com.telepathicgrunt.repurposedstructures.modinit.registry.ResourcefulRegistryChild;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.tuple.Pair;

import java.util.function.Supplier;

public interface ResourcefulRegistriesService {

    ResourcefulRegistriesService INSTANCE = GeneralUtils.loadService(ResourcefulRegistriesService.class);

    default <T> ResourcefulRegistry<T> create(ResourcefulRegistry<T> parent) {
        return new ResourcefulRegistryChild<>(parent);
    }

    default <T> ResourcefulRegistry<T> create(Registry<T> registry, String id) {
        throw new NotImplementedException();
    }

    default <T, K extends Registry<T>> Pair<Supplier<CustomRegistryLookup<T>>, ResourcefulRegistry<T>> createCustomRegistryInternal(String modId, ResourceKey<K> key, boolean save, boolean sync, boolean allowModification) {
        throw new NotImplementedException();
    }
}
