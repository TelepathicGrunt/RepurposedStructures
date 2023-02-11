package com.telepathicgrunt.repurposedstructures.misc.structurepiececounter;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.registry.CustomRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public final class JSONConditionsRegistry {
    private JSONConditionsRegistry() {}

    public static final ResourceKey<Registry<Supplier<Boolean>>> RS_JSON_CONDITIONS_KEY = ResourceKey.createRegistryKey(new ResourceLocation(RepurposedStructures.MODID, "json_conditions"));
    public static final CustomRegistry<Supplier<Boolean>> RS_JSON_CONDITIONS_REGISTRY = CustomRegistry.of(RepurposedStructures.MODID, RS_JSON_CONDITIONS_KEY, false, false, true);

    public static void registerTestJSONCondition() {
        // Registers a condition for testing purposes.
        RS_JSON_CONDITIONS_REGISTRY.register("test", () -> () -> false);
    }
}
