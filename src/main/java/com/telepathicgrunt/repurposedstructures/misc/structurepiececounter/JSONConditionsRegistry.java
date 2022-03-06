package com.telepathicgrunt.repurposedstructures.misc.structurepiececounter;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public final class JSONConditionsRegistry {
    private JSONConditionsRegistry() {}

    public static final ResourceKey<Registry<Supplier<Boolean>>> RS_JSON_CONDITIONS_KEY = ResourceKey.createRegistryKey(new ResourceLocation(RepurposedStructures.MODID, "json_conditions"));
    private static final Supplier<Boolean> TEMP_CLASS_TYPE = () -> true;
    public static final Registry<Supplier<Boolean>> RS_JSON_CONDITIONS_REGISTRY = (Registry<Supplier<Boolean>>) FabricRegistryBuilder.createSimple(TEMP_CLASS_TYPE.getClass(), RS_JSON_CONDITIONS_KEY.location()).buildAndRegister();

    public static void createJSONConditionsRegistry() {
        // Classloads the fields that creates the registries.
        // Registers a condition for testing purposes.
        Registry.REGISTRY.getOptional(new ResourceLocation(RepurposedStructures.MODID, "json_conditions"))
            .ifPresent(registry -> Registry.register(
                (Registry<Supplier<Boolean>>)registry,
                new ResourceLocation(RepurposedStructures.MODID, "test"),
                () -> false));
    }
}
