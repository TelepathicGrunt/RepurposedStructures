package com.telepathicgrunt.repurposedstructures.configs;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;


@Config(name = "Villages")
public class RSVillagesConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip
    @Comment("Add RS villages to modded biomes of same categories/type.")
    public boolean addVillagesToModdedBiomes = false;
    
    @ConfigEntry.Gui.Tooltip(count = 2)
    @Comment("How rare are Badlands Villages in Badland biomes."
            + "\n1 for spawning in most chunks and 1001 for no spawn.")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int badlandsVillageSpawnrate = 17;
    
    @ConfigEntry.Gui.Tooltip(count = 2)
    @Comment("How rare are Birch Villages in Birch biomes."
            + "\n1 for spawning in most chunks and 1001 for no spawn.")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int birchVillageSpawnrate = 28;

    @ConfigEntry.Gui.Tooltip(count = 2)
    @Comment("How rare are Dark Forest Villages in Dark Forest biomes."
            + "\n1 for spawning in most chunks and 1001 for no spawn.")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int darkForestVillageSpawnrate = 28;

    @ConfigEntry.Gui.Tooltip(count = 2)
    @Comment("How rare are Jungle Villages in Jungle biomes."
            + "\n1 for spawning in most chunks and 1001 for no spawn.")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int jungleVillageSpawnrate = 26;

    @ConfigEntry.Gui.Tooltip(count = 2)
    @Comment("How rare are Swamp Villages in Swamp biomes."
            + "\n1 for spawning in most chunks and 1001 for no spawn.")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int swampVillageSpawnrate = 28;

    @ConfigEntry.Gui.Tooltip(count = 2)
    @Comment("How rare are Mountains Villages in Mountains biomes."
            + "\n1 for spawning in most chunks and 1001 for no spawn.")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int mountainsVillageSpawnrate = 28;

    @ConfigEntry.Gui.Tooltip(count = 2)
    @Comment("How rare are Giant Taiga Villages in Giant Taiga biomes."
            + "\n1 for spawning in most chunks and 1001 for no spawn.")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int giantTaigaVillageSpawnrate = 26;
}
