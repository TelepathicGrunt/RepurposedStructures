package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biomes;

public final class Strongholds {
    private Strongholds() {}

    public static void addStrongholds() {

        GeneralUtils.addToBiome("nether_stronghold",
                (context) ->
                        BiomeSelection.isBiomeAllowed(context, RSStructures.STRONGHOLD_NETHER,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.NETHER))
                        && RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdAverageChunkDistance != 1001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.STRONGHOLD_NETHER));

        GeneralUtils.addToBiome("stronghold_end",
                (context) -> 
						BiomeSelection.isBiomeAllowed(context, RSStructures.STRONGHOLD_END,
                                () -> BiomeSelection.haveCategories(context, BiomeCategory.THEEND)
                                && !BiomeSelection.isBiome(context, Biomes.SMALL_END_ISLANDS))
						&& RepurposedStructures.RSAllConfig.RSStrongholdsConfig.end.endStrongholdAverageChunkDistance != 10001,
                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures.STRONGHOLD_END));

    }
}
