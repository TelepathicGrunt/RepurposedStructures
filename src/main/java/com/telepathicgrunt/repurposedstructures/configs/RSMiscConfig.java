package com.telepathicgrunt.repurposedstructures.configs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "Misc")
public class RSMiscConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("""
            Adds modded loot from vanilla structure's loot tables and injects them into Repurposed Structure's loot tables.
            Example: Snowy Pyramid gets all modded items that vanilla Desert Temple can have""")
    public boolean importModdedItems = true;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Gui.RequiresRestart
    @Comment("""
            Add the identifiers for Repurposed Structures's loottable you want to
             turn off the automatic modded item importing code for.\s
             Separate multiple entries with a comma.
            Example: "repurposed_structures:chests/mansions/birch, repurposed_structures:chests/mineshafts/jungle\"""")
    public String blacklistedRSLoottablesFromImportingModdedItems = "";
}