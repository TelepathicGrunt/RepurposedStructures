package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;

public class RSMainConfig
{
	public static class RSConfigValues
	{
		public ConfigValueListener<Boolean> addGiantBouldersModdedBiomes;
		public ConfigValueListener<Double> giantBouldersPerChunk;
		public ConfigValueListener<Integer> diamondChanceInGiantBoulders;
		public ConfigValueListener<Boolean> addTinyBouldersModdedBiomes;
		public ConfigValueListener<Boolean> boulderTiny;
		public ConfigValueListener<Boolean> boulderGiant;

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
		public ConfigValueListener<String> blacklistedBoulderBiomes;
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
						.define("blacklistedDimensions", "the_bumblezone:the_bumblezone"));

			builder.build();

			builder.push("Feature Options");
			
				builder.push("Misc");

					addGiantBouldersModdedBiomes = subscriber.subscribe(builder
						.comment("\n Adds giant boulders to modded Giant Tree Taiga (or Redwood) biomes.")
					.translation("repurposedstructures.config.misc.addgiantbouldersmoddedbiomes")
					.define("addGiantBouldersModdedBiomes", false));

					blacklistedBoulderBiomes = subscriber.subscribe(builder
						.comment("\n Add the ID/resource location of the biome you don't want",
								" RS's boulders to spawn in. Separate each ID with a comma ,",
								"   Example: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
						.translation("repurposedstructures.config.misc.blacklistedboulderbiomes")
						.define("blacklistedBoulderBiomes", " "));



					giantBouldersPerChunk = subscriber.subscribe(builder
					.comment("\n How many Giant Boulders per chunk. (Can be decimal too)")
					.translation("repurposedstructures.config.misc.giantbouldersperchunk")
					.defineInRange("giantBouldersPerChunk", 0.5D, 0, 100));

					diamondChanceInGiantBoulders = subscriber.subscribe(builder
					.comment("\n 1 out of ___ chance of Diamond Ore when placing",
							 " a block in giant Boulders. Lower number = more common.",
							 " Enter 0 to disable Diamond Ores completely.")
					.translation("repurposedstructures.config.misc.diamondchanceingiantboulders")
					.defineInRange("diamondChanceInGiantBoulders", 7000, 0, 1000000));


					addTinyBouldersModdedBiomes = subscriber.subscribe(builder
						.comment("\n Adds tiny boulders to modded Taiga biomes.")
					.translation("repurposedstructures.config.misc.addtinybouldersmoddedbiomes")
					.define("addTinyBouldersModdedBiomes", false));

					boulderTiny = subscriber.subscribe(builder
							.comment("\n Adds tiny boulders to Taiga Mountains and Snowy Taiga Mountains biomes ",
								" that can contain small amounts of Coal and Iron ores.")
						.translation("repurposedstructures.config.misc.bouldertiny")
						.define("boulderTiny", true));
					
					boulderGiant = subscriber.subscribe(builder
							.comment("\n Replaces boulders in Giant Tree Taiga Hills and Giant Spruce Taiga Hills",
								" biomes with a larger boulder that can contain Coal, Iron, and extremely",
								" rarely, can also have Diamond Ores.")
						.translation("repurposedstructures.config.misc.netherdungeons")
						.define("boulderGiant", true));
					
				builder.pop();
				
			builder.pop();

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
							.defineInRange("citiesNetherMaxChunkDistance", 140, 1, 1001));

					addCitiesNetherToModdedBiomes = subscriber.subscribe(builder
							.comment("\n Add Nether Cities to modded Nether category biomes.")
							.translation("repurposedstructures.config.cities.addcitiesnethertomoddedbiomes")
							.define("addCitiesNetherToModdedBiomes", true));

				builder.pop();

			builder.pop();
		}
	}
}
