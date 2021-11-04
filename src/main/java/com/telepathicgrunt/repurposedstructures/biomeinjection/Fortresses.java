package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.world.level.biome.Biome.BiomeCategory;

public final class Fortresses {
    private Fortresses() {}

    public static void addJungleFortress() {

        GeneralUtils.addToBiome("jungle_fortress",
                (context) -> BiomeSelection.isBiomeAllowed(context, RSStructures.FORTRESS_JUNGLE,
                            () -> BiomeSelection.haveCategories(context, BiomeCategory.JUNGLE))
                        && RepurposedStructures.RSAllConfig.RSFortressesConfig.jungleFortress.jungleFortressAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.FORTRESS_JUNGLE));
    }
}
