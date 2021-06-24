package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.world.biome.Biome.Category;

public class Strongholds {

    public static void addStrongholds() {
        GeneralUtils.addToBiome("nether_stronghold",
                (context) -> BiomeSelection.haveCategories(context, Category.NETHER)
                        && RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdMaxChunkDistance != 1001
                        && BiomeSelection.doesNotHaveStructureType(context, RSStructureTagMap.STRUCTURE_TAGS.STRONGHOLD)
                        && BiomeSelection.isBiomeAllowed(context, "strongholds")),
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.NETHER_STRONGHOLD));
    }
}
