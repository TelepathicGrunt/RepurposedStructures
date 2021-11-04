package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.resources.RandomRangesAccessor;
import com.telepathicgrunt.repurposedstructures.world.numberproviders.ProbabilityValue;
import net.minecraft.util.ResourceLocation;

public final class RSNumberProviders {
    private RSNumberProviders() {}

    public static final ResourceLocation PROBABILITY = new ResourceLocation(RepurposedStructures.MODID, "probability");

    public static void registerNumberProviders(){
        RandomRangesAccessor.repurposedstructures_getGENERATORS().put(PROBABILITY, ProbabilityValue.class);
    }
}
