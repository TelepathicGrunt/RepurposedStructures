package com.telepathicgrunt.repurposedstructures.configs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;


@Config(name = "repurposed_structures-fabric/general_configs")
public class RSAllConfig implements ConfigData {

    @ConfigEntry.Category("Misc")
    @ConfigEntry.Gui.TransitiveObject
    public RSMiscConfig RSMiscConfig = new RSMiscConfig();

    @ConfigEntry.Category("Dungeons")
    @ConfigEntry.Gui.TransitiveObject
    public RSDungeonsConfig RSDungeonsConfig = new RSDungeonsConfig();

    @ConfigEntry.Category("Wells")
    @ConfigEntry.Gui.TransitiveObject
    public RSWellsConfig RSWellsConfig = new RSWellsConfig();
}
