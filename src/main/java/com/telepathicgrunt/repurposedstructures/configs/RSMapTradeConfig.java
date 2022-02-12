package com.telepathicgrunt.repurposedstructures.configs;


import com.telepathicgrunt.repurposedstructures.configs.omegaconfig.api.Comment;
import com.telepathicgrunt.repurposedstructures.configs.omegaconfig.api.Config;
import com.telepathicgrunt.repurposedstructures.misc.MobMapTrades;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RSMapTradeConfig implements Config {

    @Override
    public String getName() {
        return "repurposed_structures-forge/map_trade_configs";
    }

    @Override
    public String getExtension() {
        return "json5";
    }

    @Override
    public void save() {
        // Add logic here when adding new mob spawn structures to config."
        // The logic needs to do a putIfAbsent to add the missing structures between the config versions when updating."

        configVersion = 1;
        Config.super.save();
    }

    @Comment("""







            // Adds structure maps to any Villager's trades.
            // This should work with modded villager professions as well, but you will need to know its registry name.
            // Disabling a structure will not automatically remove the structure from the trades (The explorer map to the structure becomes an empty map)."""
    )
    public final Map<String, List<MobMapTrades.VillagerTradeEntry>> villagerMapTrades = new HashMap<>() {{
        put("minecraft:cartographer", Arrays.asList(
                new MobMapTrades.VillagerTradeEntry("repurposed_structures:fortress_jungle", "BANNER_GREEN", 3, 13, 12, 10),
                new MobMapTrades.VillagerTradeEntry("repurposed_structures:fortress_jungle", "BANNER_GREEN", 4, 14, 12, 10),
                new MobMapTrades.VillagerTradeEntry("repurposed_structures:mansion_birch", "MANSION", 4, 14, 12, 10),
                new MobMapTrades.VillagerTradeEntry("repurposed_structures:mansion_desert", "MANSION", 4, 14, 12, 10),
                new MobMapTrades.VillagerTradeEntry("repurposed_structures:mansion_jungle", "MANSION", 4, 14, 12, 10),
                new MobMapTrades.VillagerTradeEntry("repurposed_structures:mansion_oak", "MANSION", 4, 14, 12, 10),
                new MobMapTrades.VillagerTradeEntry("repurposed_structures:mansion_savannna", "MANSION", 4, 14, 12, 10),
                new MobMapTrades.VillagerTradeEntry("repurposed_structures:mansion_snowy", "MANSION", 4, 14, 12, 10),
                new MobMapTrades.VillagerTradeEntry("repurposed_structures:mansion_taiga", "MANSION", 4, 14, 12, 10)
        ));
    }};


    @Comment("""







            // Adds structure maps to Wandering Trader's trades.
            // There is only two trade types for Wandering Trader and that's RARE and COMMON.
            // Disabling a structure will not automatically remove the structure from the trades (The explorer map to the structure becomes an empty map)."""
    )
    public final Map<String, List<MobMapTrades.WandingTraderTradeEntry>> wanderingTraderMapTrades = new HashMap<>() {{
            put("RARE", Arrays.asList(
                    new MobMapTrades.WandingTraderTradeEntry("repurposed_structures:bastion_underground", "BANNER_GRAY", 38, 1, 100),
                    new MobMapTrades.WandingTraderTradeEntry("repurposed_structures:village_mushroom", "MANSION", 38, 1, 100),
                    new MobMapTrades.WandingTraderTradeEntry("repurposed_structures:city_overworld", "MANSION", 38, 1, 200)
            ));
        }};


    @Comment("""







            // for internal use only. Do not change this."""
    )
    public int configVersion = 1;
}
