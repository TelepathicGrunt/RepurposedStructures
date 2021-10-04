package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.numberproviders.ProbabilityValue;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.providers.number.LootNumberProviderType;

public class RSNumberProviders {

    public static final LootNumberProviderType PROBABILITY = new LootNumberProviderType(new ProbabilityValue.Serializer());

    public static void registerNumberProviders() {
        Registry.register(Registry.LOOT_NUMBER_PROVIDER_TYPE, new ResourceLocation(RepurposedStructures.MODID, "probability"), PROBABILITY);
    }
}
