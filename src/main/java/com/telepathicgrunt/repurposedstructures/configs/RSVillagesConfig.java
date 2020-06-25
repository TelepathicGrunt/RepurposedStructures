package com.telepathicgrunt.repurposedstructures.configs;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;


@Config(name = "Repurposed_Structures-Villages")
public class RSVillagesConfig implements ConfigData {

    @Comment("\r\n Add the custom villages to modded biomes of the same categories/type.")
    public boolean addVillagesToModdedBiomes = false;


    @Comment("\r\n How rare are Badlands Villages in Badland biomes.\r\n"
            + "\n "
            + " 1 for spawning in most chunks and 1001 for no spawn.")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int badlandsVillageSpawnrate = 17;

}
