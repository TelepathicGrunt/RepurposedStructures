package com.telepathicgrunt.repurposedstructures.configs;


import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;


@me.shedaniel.autoconfig.annotation.Config(name = "Structure Natural Mob Spawning")
public class RSNaturalMobSpawningConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("""



             If you are in the config GUI in-game and are looking for the configs to
             control what mobs spawn over time in Repurposed Structure's structures,
             please take a look at the natural_mob_spawning_configs.json5 file in the config folder.
             Cloth Config API cannot show maps so you will need to edit the file itself.""")
    public String seeConfigFileForStructureNaturalMobSpawning = "Read comment above";
}
