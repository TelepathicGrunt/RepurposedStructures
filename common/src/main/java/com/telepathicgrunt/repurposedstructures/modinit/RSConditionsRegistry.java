package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.registry.CustomRegistry;
import com.telepathicgrunt.repurposedstructures.modinit.registry.RegistryEntry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public final class RSConditionsRegistry {
    private RSConditionsRegistry() {}

    public static final ResourceKey<Registry<Supplier<Boolean>>> RS_JSON_CONDITIONS_KEY = ResourceKey.createRegistryKey(new ResourceLocation(RepurposedStructures.MODID, "json_conditions"));
    public static final CustomRegistry<Supplier<Boolean>> RS_JSON_CONDITIONS_REGISTRY = CustomRegistry.of(RepurposedStructures.MODID, RS_JSON_CONDITIONS_KEY, false, false, true);
    public static final RegistryEntry<Supplier<Boolean>> ALWAYS_TRUE = RS_JSON_CONDITIONS_REGISTRY.register("always_true", () -> () -> true);
    public static final RegistryEntry<Supplier<Boolean>> ALWAYS_FALSE = RS_JSON_CONDITIONS_REGISTRY.register("always_false", () -> () -> true);

    /*
     * This registry is for hooking up the pool_additions json files to a code base config to enable/disable it.
     * Best for direct mod compat where a mod wants to add houses to Repurposed Structures by the pool_additions
     * json files like the many Repurposed Structures datapacks works but want a code config to control it.
     *
     * Add "condition" to the individual entries in the template pool in pool_additions folder and give it the
     * ResourceLocation of the condition you registered. The rs_pieces_spawn_counts folder files can also take
     * a "condition" field for its entries as well.
     *
     * You can register what the condition is to this registry by doing this in your mod so now your config can control the json files:

     * FABRIC/QUILT:
         BuiltInRegistries.REGISTRY.getOptional(new ResourceLocation("repurposed_structures", "json_conditions"))
             .ifPresent(registry -> Registry.register(
                 (Registry<Supplier<Boolean>>)registry,
                 new ResourceLocation("repurposed_structures", "test"),
                 () -> SomeConfig.EnableJson()));

     * FORGE:
        RS_JSON_CONDITIONS_REGISTRY.register("test", () -> () -> SomeConfig.EnableJson());

     */
}
