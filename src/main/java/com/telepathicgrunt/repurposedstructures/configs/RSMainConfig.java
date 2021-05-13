package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;

public class RSMainConfig
{
	public static class RSConfigValues
	{

		public ConfigValueListener<Integer> jungleFortressMaxChunkDistance;
		public ConfigValueListener<Double> silverfishSpawnrate;
		public ConfigValueListener<Boolean> allowSilverfishSpawner;
		public ConfigValueListener<Boolean> lootChests;
		public ConfigValueListener<Boolean> addJungleFortressToModdedBiomes;

		public ConfigValueListener<Integer> grassyIglooMaxChunkDistance;
		public ConfigValueListener<Boolean> addGrassyIglooToModdedBiomes;
		public ConfigValueListener<Integer> stoneIglooMaxChunkDistance;
		public ConfigValueListener<Boolean> addStoneIglooToModdedBiomes;

		public ConfigValueListener<Integer> ruinedPortalEndMaxChunkDistance;
		public ConfigValueListener<Boolean> addRuinedPortalEndToModdedBiomes;

		public ConfigValueListener<Integer> ruinsNetherMaxChunkDistance;
		public ConfigValueListener<Boolean> addRuinsNetherToModdedBiomes;

		public ConfigValueListener<Integer> citiesNetherMaxChunkDistance;
		public ConfigValueListener<Boolean> addCitiesNetherToModdedBiomes;

		public ConfigValueListener<String> blacklistedDimensions;
		public ConfigValueListener<String> blacklistedFortressBiomes;
		public ConfigValueListener<String> blacklistedIglooBiomes;
		public ConfigValueListener<String> blacklistedRuinedPortalsBiomes;
		public ConfigValueListener<String> blacklistedRuinsBiomes;
		public ConfigValueListener<String> blacklistedCitiesBiomes;

		public RSConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{

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
								"theabyss:theabyssdimskylands, " +
								"lostcities:lostcity"));

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

					silverfishSpawnrate = subscriber.subscribe(builder
							.comment("\n How often Silverfish Blocks will generate in Jungle Fortress as a percentage.",
								 " Note: Mossy Stone Bricks block cannot be infected by Silverfish",
								 " 0 for no Silverfish Blocks and 100 for max spawnrate.")
							.translation("repurposedstructures.config.junglefortress.silverfishspawnrate")
							.defineInRange("silverfishSpawnrate", 0.5D, 0, 100));
					
					allowSilverfishSpawner = subscriber.subscribe(builder
							.comment("\n Silverfish Mob Spawners generate in Stone Fortresses.",
								" If turned off, the spawners will become Skeleton spawners.")
							.translation("repurposedstructures.config.junglefortress.allowsilverfishspawner")
							.define("allowSilverfishSpawner", true));

					lootChests = subscriber.subscribe(builder
							.comment("\n Controls whether loot chests spawn or not in Jungle Fortresses.")
						.translation("repurposedstructures.config.junglefortress.lootchests")
						.define("lootChests", true));


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
