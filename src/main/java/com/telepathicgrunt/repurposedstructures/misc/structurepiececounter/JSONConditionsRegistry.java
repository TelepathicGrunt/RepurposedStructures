package com.telepathicgrunt.repurposedstructures.misc.structurepiececounter;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.function.Supplier;

public final class JSONConditionsRegistry {
    private JSONConditionsRegistry() {}

    public static final ResourceKey<Registry<Supplier<Boolean>>> RS_JSON_CONDITIONS_KEY = ResourceKey.createRegistryKey(new ResourceLocation(RepurposedStructures.MODID, "json_conditions"));
    public static Supplier<IForgeRegistry<Supplier<Boolean>>> RS_JSON_CONDITIONS_REGISTRY;

    public static void createNewRegistry(NewRegistryEvent event) {
        RS_JSON_CONDITIONS_REGISTRY = event.create(new RegistryBuilder<Supplier<Boolean>>()
                .setName(RS_JSON_CONDITIONS_KEY.location())
                .setIDRange(1, Integer.MAX_VALUE - 1)
                .disableSaving());
    }

    public static void registerTestJSONCondition() {
        // Registers a condition for testing purposes.
        RS_JSON_CONDITIONS_REGISTRY.get().register(
                new ResourceLocation(RepurposedStructures.MODID, "test"),
                () -> false);
    }
}
