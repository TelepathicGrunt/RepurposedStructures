package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class RuinedPortals {

    public static void addRuinedPortals(BiomeLoadingEvent event) {

        if (RepurposedStructures.RSRuinedPortalsConfig.ruinedPortalEndMaxChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.RUINED_PORTAL_END.get(),
                    () -> BiomeSelection.haveCategories(event, Category.THEEND) && !BiomeSelection.isBiome(event, Biomes.THE_END)))
        {
            event.getGeneration().getStructures().add(() -> RSConfiguredStructures.RUINED_PORTAL_END);
        }
    }
}
