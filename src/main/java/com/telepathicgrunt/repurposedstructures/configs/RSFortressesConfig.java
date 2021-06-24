package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.biomeinjection.Cities;
import com.telepathicgrunt.repurposedstructures.biomeinjection.Igloos;
import com.telepathicgrunt.repurposedstructures.biomeinjection.RuinedPortals;
import com.telepathicgrunt.repurposedstructures.biomeinjection.Ruins;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;


@Config(name = "Fortresses")
public class RSFortressesConfig implements ConfigData {

    @ConfigEntry.Gui.CollapsibleObject
    public JungleFortress jungleFortress = new JungleFortress();

    public static class JungleFortress {

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Jungle Fortresses."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int jungleFortressMaxChunkDistance = 50;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Size of the fortress. This is how many pieces long a branch can be from the start piece.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 18)
        public int jungleFortressSize = 10;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Min Y height that the starting point can spawn at."
                +"\nDefault is 56.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 255)
        public int jungleFortressMinHeight = 56;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("""
                Max Y height that the starting point can spawn at.
                Default is 63.
                If below min height, this will be read as min.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 255)
        public int jungleFortressMaxHeight = 63;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How far above or below the fortress's pieces can generate away from the center piece.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 255)
        public int jungleFortressVerticalRange = 33;
    }
}
