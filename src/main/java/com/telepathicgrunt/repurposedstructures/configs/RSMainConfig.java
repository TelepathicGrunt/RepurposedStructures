package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;

public class RSMainConfig {
    public static class RSConfigValues {

        public ConfigValueListener<Integer> jungleFortressMaxChunkDistance;
        public ConfigValueListener<Boolean> addJungleFortressToModdedBiomes;
        public ConfigValueListener<Integer> jungleFortressSize;
        public ConfigValueListener<Integer> jungleFortressMinHeight;
        public ConfigValueListener<Integer> jungleFortressMaxHeight;
        public ConfigValueListener<Integer> jungleFortressVerticalRange;

        public ConfigValueListener<Integer> grassyIglooMaxChunkDistance;
        public ConfigValueListener<Boolean> addGrassyIglooToModdedBiomes;
        public ConfigValueListener<Integer> stoneIglooMaxChunkDistance;
        public ConfigValueListener<Boolean> addStoneIglooToModdedBiomes;

        public ConfigValueListener<Integer> ruinedPortalEndMaxChunkDistance;
        public ConfigValueListener<Boolean> addRuinedPortalEndToModdedBiomes;

        public ConfigValueListener<Integer> ruinsNetherMaxChunkDistance;
        public ConfigValueListener<Boolean> addRuinsNetherToModdedBiomes;
		public ConfigValueListener<Integer> ruinsLandWarmMaxChunkDistance;
		public ConfigValueListener<Boolean> addRuinsLandWarmToModdedBiomes;
		public ConfigValueListener<Integer> ruinsLandHotMaxChunkDistance;
		public ConfigValueListener<Boolean> addRuinsLandHotToModdedBiomes;
        // regexpos1

        public ConfigValueListener<Integer> citiesNetherMaxChunkDistance;
        public ConfigValueListener<Boolean> addCitiesNetherToModdedBiomes;

        public ConfigValueListener<Integer> bastionUndergroundMaxChunkDistance;
        public ConfigValueListener<Boolean> addBastionUndergroundToModdedBiomes;

        public ConfigValueListener<String> blacklistedDimensions;
        public ConfigValueListener<String> blacklistedFortressBiomes;
        public ConfigValueListener<String> blacklistedIglooBiomes;
        public ConfigValueListener<String> blacklistedRuinedPortalsBiomes;
        public ConfigValueListener<String> blacklistedRuinsBiomes;
        public ConfigValueListener<String> blacklistedCitiesBiomes;
        public ConfigValueListener<String> blacklistedUndergroundBastionBiomes;

        public RSConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber) {

            builder.push("Mod-wide Impacting Options");

            blacklistedDimensions = subscriber.subscribe(builder
                    .comment("\n Add the identifier for the dimension that you want",
                            " no Repurposed Structures structure to spawn in.",
                            " Separate multiple entries with a comma.",
                            "   Example: \"minecraft:the_end,awesome_mod:awesome_dimension\"")
                    .translation("repurposedstructures.config.all.blacklisteddimensions")
                    .define("blacklistedDimensions", "the_bumblezone:the_bumblezone, " +
                            "twilightforest:twilightforest, " +
                            "undergarden:undergarden, " +
                            "the_midnight:the_midnight, " +
                            "theabyss:theabyssdim, " +
                            "theabyss:theabyssiceworld, " +
                            "theabyss:death, " +
                            "theabyss:the_end_of_time, " +
                            "theabyss:the_end_of_time_2, " +
                            "theabyss:dream, " +
                            "theabyss:dream_2, " +
                            "theabyss:dream_3, " +
                            "theabyss:radio, " +
                            "theabyss:theabyssdimgroundlands, " +
                            "theabyss:theabyssdimskylands"));

            builder.build();

            builder.push("Structure Options");

            builder.push("Jungle Fortress");

            jungleFortressMaxChunkDistance = subscriber.subscribe(builder
                    .comment("\n How rare are Jungle Fortresses.",
                            " 1 for spawning in most chunks and 1001 for no spawn.")
                    .translation("repurposedstructures.config.junglefortress.junglefortressmaxchunkdistance")
                    .defineInRange("jungleFortressMaxChunkDistance", 50, 1, 1001));

            addJungleFortressToModdedBiomes = subscriber.subscribe(builder
                    .comment("\n Add Jungle Fortress to modded jungle biomes.")
                    .translation("repurposedstructures.config.junglefortress.addjunglefortresstomoddedbiomes")
                    .define("addJungleFortressToModdedBiomes", true));

            blacklistedFortressBiomes = subscriber.subscribe(builder
                    .comment("\n Add the ID/resource location of the biome you don't want",
                            " RS's Jungle Fortresses to spawn in. Separate each ID with a comma ,",
                            "   Example: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
                    .translation("repurposedstructures.config.junglefortress.blacklistedfortressbiomes")
                    .define("blacklistedFortressBiomes", " "));

            jungleFortressSize = subscriber.subscribe(builder
                    .comment("\n Size of the fortress. This is how many pieces long a branch can be from the start piece.")
                    .translation("repurposedstructures.config.junglefortress.jungleFortressSize")
                    .defineInRange("jungleFortressSize", 10, 1, 18));

            jungleFortressMinHeight = subscriber.subscribe(builder
                    .comment("\n Min Y height that the starting point can spawn at."
                            + "\nDefault is 56.")
                    .translation("repurposedstructures.config.junglefortress.junglefortressminheight")
                    .defineInRange("jungleFortressMinHeight", 56, 0, 255));

            jungleFortressMaxHeight = subscriber.subscribe(builder
                    .comment("\n Max Y height that the starting point can spawn at."
                            + "\nDefault is 63."
                            + "\nIf below min height, this will be read as min.")
                    .translation("repurposedstructures.config.junglefortress.junglefortressmaxheight")
                    .defineInRange("jungleFortressMaxHeight", 63, 0, 255));

            jungleFortressVerticalRange = subscriber.subscribe(builder
                    .comment("\n How far above or below the fortress's pieces can generate away from the center piece.")
                    .translation("repurposedstructures.config.junglefortress.junglefortressverticalrange")
                    .defineInRange("jungleFortressVerticalRange", 33, 0, 255));

            builder.pop();

            builder.push("Igloos");

            blacklistedIglooBiomes = subscriber.subscribe(builder
                    .comment("\n Add the ID/resource location of the biome you don't want",
                            " RS's Igloos to spawn in. Separate each ID with a comma ,",
                            "   Example: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
                    .translation("repurposedstructures.config.igloo.blacklistedigloobiomes")
                    .define("blacklistedIglooBiomes", " "));

            grassyIglooMaxChunkDistance = subscriber.subscribe(builder
                    .comment("\n How rare are Grassy Igloos in Plains and Forests.",
                            " 1 for spawning in most chunks and 1001 for no spawn.")
                    .translation("repurposedstructures.config.igloo.grassyigloomaxchunkdistance")
                    .defineInRange("grassyIglooMaxChunkDistance", 20, 1, 1001));

            addGrassyIglooToModdedBiomes = subscriber.subscribe(builder
                    .comment("\n Add Grassy Igloos to modded biomes that are most likely grassy fields or temperate forests.")
                    .translation("repurposedstructures.config.igloo.addgrassyiglootomoddedbiomes")
                    .define("addGrassyIglooToModdedBiomes", true));

            stoneIglooMaxChunkDistance = subscriber.subscribe(builder
                    .comment("\n How rare are Stone Igloos in Giant Tree Taiga biomes.",
                            " 1 for spawning in most chunks and 1001 for no spawn.")
                    .translation("repurposedstructures.config.igloo.stoneigloomaxchunkdistance")
                    .defineInRange("stoneIglooMaxChunkDistance", 20, 1, 1001));

            addStoneIglooToModdedBiomes = subscriber.subscribe(builder
                    .comment("\n Add Stone Igloos to modded biomes that are most likely Giant Tree Taiga variants.")
                    .translation("repurposedstructures.config.igloo.addstoneiglootomoddedbiomes")
                    .define("addStoneIglooToModdedBiomes", true));

            builder.pop();

            builder.push("Ruined Portals");

            blacklistedRuinedPortalsBiomes = subscriber.subscribe(builder
                    .comment("\n Add the ID/resource location of the biome you don't want",
                            " RS's Ruined Portals to spawn in. Separate each ID with a comma ,",
                            "   Example: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
                    .translation("repurposedstructures.config.ruinedportals.blacklistedruinedportalsbiomes")
                    .define("blacklistedRuinedPortalsBiomes", " "));

            ruinedPortalEndMaxChunkDistance = subscriber.subscribe(builder
                    .comment("\n How rare are End themed Ruined Portals in End category biomes. 1 for spawning in most",
                            " chunks and 1001 for none.")
                    .translation("repurposedstructures.config.ruinedPortals.ruinedportalendmaxchunkdistance")
                    .defineInRange("ruinedPortalEndMaxChunkDistance", 57, 1, 1001));

            addRuinedPortalEndToModdedBiomes = subscriber.subscribe(builder
                    .comment("\n Add End themed ruined portals to modded End category biomes.")
                    .translation("repurposedstructures.config.ruinedPortals.addruinedportalendtomoddedbiomes")
                    .define("addRuinedPortalEndToModdedBiomes", true));

            builder.pop();

            builder.push("Ruins");

            blacklistedUndergroundBastionBiomes = subscriber.subscribe(builder
                    .comment("\n Add the ID/resource location of the biome you don't want",
                            " RS's Ruins to spawn in. Separate each ID with a comma ,",
                            "   Example: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
                    .translation("repurposedstructures.config.ruins.blacklistedundergroundbastionsbiomes")
                    .define("blacklistedUndergroundBastionBiomes", " "));

                bastionUndergroundMaxChunkDistance = subscriber.subscribe(builder
                    .comment("How rare are Underground Bastions in non-ocean and non-beach Overworld biomes.",
                            "1 for spawning in most chunks and 10001 for none.")
                    .translation("repurposedstructures.config.pyramids.bastionundergroundmaxchunkdistance")
                    .defineInRange("bastionUndergroundMaxChunkDistance", 800, 1, 1001));

                addBastionUndergroundToModdedBiomes = subscriber.subscribe(builder
                    .comment("\n Add Underground Bastions to modded non-ocean and non-beach Overworld biomes.")
                    .translation("repurposedstructures.config.pyramids.addbastionundergroundtomoddedbiomes")
                    .define("addBastionUndergroundToModdedBiomes", true));

            builder.pop();

            builder.push("Ruins");

            blacklistedRuinsBiomes = subscriber.subscribe(builder
                    .comment("\n Add the ID/resource location of the biome you don't want",
                            " RS's Ruins to spawn in. Separate each ID with a comma ,",
                            "   Example: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
                    .translation("repurposedstructures.config.ruins.blacklistedruinsbiomes")
                    .define("blacklistedRuinsBiomes", " "));

            ruinsNetherMaxChunkDistance = subscriber.subscribe(builder
                    .comment("\n How rare are Nether Ruins. 1 for spawning in most chunks and 1001 for none.")
                    .translation("repurposedstructures.config.ruins.ruinsnethermaxchunkdistance")
                    .defineInRange("ruinsNetherMaxChunkDistance", 35, 1, 1001));

            addRuinsNetherToModdedBiomes = subscriber.subscribe(builder
                    .comment("\n Add Nether Ruins to modded Nether category biomes.")
                    .translation("repurposedstructures.config.ruins.addruinsnethertomoddedbiomes")
                    .define("addRuinsNetherToModdedBiomes", true));

                ruinsLandWarmMaxChunkDistance = subscriber.subscribe(builder
                    .comment("How rare are Warm Land Ruins in Plains, Forests, and non-snowy Taiga biomes.",
                    "\n1 for spawning in most chunks and 1001 for none.")
                    .translation("repurposedstructures.config.ruins.ruinslandwarmmaxchunkdistance")
                    .defineInRange("ruinsLandWarmMaxChunkDistance", 48, 1, 1001));

                addRuinsLandWarmToModdedBiomes = subscriber.subscribe(builder
                    .comment("\n Add Warm Land Ruins to modded Plains, Forests,",
                    "\nand non-snowy Taiga biomes.")
                    .translation("repurposedstructures.config.ruins.addruinslandwarmtomoddedbiomes")
                    .define("addRuinsLandWarmToModdedBiomes", true));

                ruinsLandHotMaxChunkDistance = subscriber.subscribe(builder
                    .comment("How rare are Hot Land Ruins in Desert biomes.",
                    "\n1 for spawning in most chunks and 1001 for none.")
                    .translation("repurposedstructures.config.ruins.ruinslandhotmaxchunkdistance")
                    .defineInRange("ruinsLandHotMaxChunkDistance", 48, 1, 1001));

                addRuinsLandHotToModdedBiomes = subscriber.subscribe(builder
                    .comment("\n Add Hot Land Ruins to modded Desert biomes.")
                    .translation("repurposedstructures.config.ruins.addruinslandhottomoddedbiomes")
                    .define("addRuinsLandHotToModdedBiomes", true));
            // regexpos2
            builder.pop();


            builder.push("Cities");

            blacklistedCitiesBiomes = subscriber.subscribe(builder
                    .comment("\n Add the ID/resource location of the biome you don't want",
                            " RS's Cities to spawn in. Separate each ID with a comma ,",
                            "   Example: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
                    .translation("repurposedstructures.config.cities.blacklistedruinsbiomes")
                    .define("blacklistedCitiesBiomes", " "));

            citiesNetherMaxChunkDistance = subscriber.subscribe(builder
                    .comment("\n How rare are Nether Cities. 1 for spawning in most chunks and 1001 for none.")
                    .translation("repurposedstructures.config.cities.citiesnethermaxchunkdistance")
                    .defineInRange("citiesNetherMaxChunkDistance", 120, 1, 1001));

            addCitiesNetherToModdedBiomes = subscriber.subscribe(builder
                    .comment("\n Add Nether Cities to modded Nether category biomes.")
                    .translation("repurposedstructures.config.cities.addcitiesnethertomoddedbiomes")
                    .define("addCitiesNetherToModdedBiomes", true));

            builder.pop();

            builder.pop();
        }
    }
}
