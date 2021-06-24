package com.telepathicgrunt.repurposedstructures.configs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;


@Config(name = "Ruined Portals")
public class RSRuinedPortalsConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Gui.RequiresRestart
    @Comment("""



            How rare are End themed Ruined Portals in
            End category biomes. 1 for spawning in most
            chunks and 10001 for none.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 10001)
    public int ruinedPortalEndMaxChunkDistance = 57;
}
