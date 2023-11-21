package com.telepathicgrunt.repurposedstructures.modinit.registry.neoforge;

import com.telepathicgrunt.repurposedstructures.modinit.registry.ResourcefulRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ResourcefulRegistriesImpl {

    private static final List<CustomRegistryInfo<?>> CUSTOM_REGISTRIES = new ArrayList<>();

    public static <T> ResourcefulRegistry<T> create(Registry<T> registry, String id) {
        return new NeoForgeResourcefulRegistry<>(registry, id);
    }

    public static void onRegisterForgeRegistries(NewRegistryEvent event) {
        CUSTOM_REGISTRIES.forEach(registry -> registry.build(event));
    }

    public static class LateSupplier<T> implements Supplier<T> {
        private T value;
        private boolean initialized = false;

        public void set(T value) {
            this.value = value;
            this.initialized = true;
        }

        @Override
        public T get() {
            if (!initialized) {
                throw new IllegalStateException("LateSupplier not initialized");
            }
            return value;
        }
    }

    public record CustomRegistryInfo<T>(
            LateSupplier<Registry<T>> lookup,
            ResourceKey<? extends Registry<T>> key,
            boolean save,
            boolean sync,
            boolean allowModification
    ) {

        public void build(NewRegistryEvent event) {
            lookup.set(event.create(getBuilder()));
        }

        public RegistryBuilder<T> getBuilder() {
            return new RegistryBuilder<>(key)
                    .disableRegistrationCheck()
                    .sync(sync);
        }
    }
}
