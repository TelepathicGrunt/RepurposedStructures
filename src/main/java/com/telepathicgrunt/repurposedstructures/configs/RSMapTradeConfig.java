package com.telepathicgrunt.repurposedstructures.configs;


import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;


@me.shedaniel.autoconfig.annotation.Config(name = "Structure Map Trades")
public class RSMapTradeConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("""



             If you are in the config GUI in-game and are looking for the configs to
             control what RS structure map is added to Villager/Wandering Trader's trades,
             please take a look at the map_trade_configs.json5 file in the config folder.
             Cloth Config API cannot show maps so you will need to edit the file itself.""")
    public String seeConfigFileForStructureMapTradeConfig = "Read comment above";
}
