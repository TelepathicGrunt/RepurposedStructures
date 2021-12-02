package com.telepathicgrunt.repurposedstructures.misc;

import com.mojang.serialization.Lifecycle;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.WritableRegistry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public final class JSONConditionsRegistry {
    private JSONConditionsRegistry() {}

    public static final ResourceKey<Registry<Supplier<Boolean>>> RS_JSON_CONDITIONS_KEY = ResourceKey.createRegistryKey(new ResourceLocation(RepurposedStructures.MODID, "json_conditions"));
    public static final Registry<Supplier<Boolean>> RS_JSON_CONDITIONS_REGISTRY = createRegistry(RS_JSON_CONDITIONS_KEY);

    public static void registerTestJSONCondition() {
        // Registers a condition for testing purposes.
        Registry.REGISTRY.getOptional(new ResourceLocation(RepurposedStructures.MODID, "json_conditions"))
            .ifPresent(registry -> Registry.register(
                (Registry<Supplier<Boolean>>)registry,
                new ResourceLocation(RepurposedStructures.MODID, "test"),
                () -> false));
    }

    private static <T, R extends Registry<T>> R createRegistry(ResourceKey<R> resourceKey) {
        return ((WritableRegistry<R>)Registry.REGISTRY).register(resourceKey, (R)new MappedRegistry<>(resourceKey, Lifecycle.stable()), Lifecycle.stable());
    }
}
