package com.telepathicgrunt.repurposedstructures.configs;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;


@Config(name = "Villages")
public class RSVillagesConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("Add RS villages to modded biomes of same categories/type.")
    public boolean addVillagesToModdedBiomes = true;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("Add the ID/resource location of the biome you don't want"
            +"\nRS's villages to spawn in. Separate each ID with a comma ,"
            +"\n"
            +"\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
    public String blacklistedVillageBiomes = "";

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("How rare are Badlands Villages in Badland biomes."
            + "\n1 for spawning in most chunks and 1001 for no spawn.")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int badlandsVillageMaxChunkDistance = 30;
    
    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("How rare are Birch Villages in Birch biomes."
            + "\n1 for spawning in most chunks and 1001 for no spawn.")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int birchVillageMaxChunkDistance = 38;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("How rare are Dark Forest Villages in Dark Forest biomes."
            + "\n1 for spawning in most chunks and 1001 for no spawn.")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int darkForestVillageMaxChunkDistance = 38;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("How rare are Jungle Villages in Jungle biomes."
            + "\n1 for spawning in most chunks and 1001 for no spawn.")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int jungleVillageMaxChunkDistance = 38;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("How rare are Swamp Villages in Swamp biomes."
            + "\n1 for spawning in most chunks and 1001 for no spawn.")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int swampVillageMaxChunkDistance = 38;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("How rare are Mountains Villages in Mountains biomes."
            + "\n1 for spawning in most chunks and 1001 for no spawn.")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int mountainsVillageMaxChunkDistance = 38;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("How rare are Giant Taiga Villages in Giant Taiga biomes."
            + "\n1 for spawning in most chunks and 1001 for no spawn.")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int giantTaigaVillageMaxChunkDistance = 38;


    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("How rare are Crimson Village in Crimson Forest biomes."
            + "\n1 for spawning in most chunks and 1001 for none.")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int crimsonVillageMaxChunkDistance = 30;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("How rare are Warped Village in Warped Forest biomes."
            + "\n1 for spawning in most chunks and 1001 for none.")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int warpedVillageMaxChunkDistance = 30;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("How rare are Oak Villages in forest category"
            + "\nbiomes that are not birch or dark forest.")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int villageOakMaxChunkDistance = 38;
}
