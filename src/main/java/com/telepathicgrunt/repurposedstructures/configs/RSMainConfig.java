package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber
public class RSMainConfig
{
	public static class RSConfigValues
	{
		public ConfigValueListener<Boolean> addLargeSwampTreeModdedBiomes;
		public ConfigValueListener<Boolean> addGiantBouldersModdedBiomes;
		public ConfigValueListener<Double> giantBouldersPerChunk;
		public ConfigValueListener<Integer> diamondChanceInGiantBoulders;
		public ConfigValueListener<Boolean> addTinyBouldersModdedBiomes;
		public ConfigValueListener<Boolean> boulderTiny;
		public ConfigValueListener<Boolean> boulderGiant;
		public ConfigValueListener<Boolean> hornedSwampTree;

		public ConfigValueListener<Integer> jungleFortressMaxChunkDistance;
		public ConfigValueListener<Double> silverfishSpawnrate;
		public ConfigValueListener<Boolean> allowSilverfishSpawner;
		public ConfigValueListener<Boolean> lootChests;
		public ConfigValueListener<Boolean> addJungleFortressToModdedBiomes;

		public ConfigValueListener<Integer> grassyIglooMaxChunkDistance;
		public ConfigValueListener<Boolean> addGrassyIglooToModdedBiomes;
		public ConfigValueListener<Integer> stoneIglooMaxChunkDistance;
		public ConfigValueListener<Boolean> addStoneIglooToModdedBiomes;

		public ConfigValueListener<String> blacklistedDimensions;
		public ConfigValueListener<String> blacklistedSwampTreeBiomes;
		public ConfigValueListener<String> blacklistedBoulderBiomes;
		public ConfigValueListener<String> blacklistedFortressBiomes;
		public ConfigValueListener<String> blacklistedIglooBiomes;

		public RSConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{

			builder.push("Mod-wide Impacting Options");

				blacklistedDimensions = subscriber.subscribe(builder
						.comment("\r\n Add the identifier for the dimension that you want"
								+"\r\n no Repurposed Structures structure to spawn in."
								+"\r\n Separate multiple entries with a comma."
								+"\r\n"
								+"\r\nExample: \"minecraft:the_end,awesome_mod:awesome_dimension\"")
						.translation("repurposedstructures.config.all.blacklisteddimensions")
						.define("blacklistedDimensions", "the_bumblezone:the_bumblezone"));

			builder.build();

			builder.push("Feature Options");
			
				builder.push("Misc");

					addLargeSwampTreeModdedBiomes = subscriber.subscribe(builder
							.comment("\r\n Add 2x2 Swamp Trees to modded swamp biomes.")
						.translation("repurposedstructures.config.misc.addlargeswamptreemoddedbiomes")
						.define("addLargeSwampTreeModdedBiomes", true));
					
					addGiantBouldersModdedBiomes = subscriber.subscribe(builder
						.comment("\r\n Adds giant boulders to modded Giant Tree Taiga (or Redwood) biomes.")
					.translation("repurposedstructures.config.misc.addgiantbouldersmoddedbiomes")
					.define("addGiantBouldersModdedBiomes", false));

					blacklistedSwampTreeBiomes = subscriber.subscribe(builder
							.comment("\r\n Add the ID/resource location of the biome you don't want"
									+"\r\n RS's 2x2 Swamp trees to spawn in. Separate each ID with a comma ,"
									+"\r\n"
									+"\r\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
							.translation("repurposedstructures.config.misc.blacklistedswamptreebiomes")
							.define("blacklistedSwampTreeBiomes", ""));

					blacklistedBoulderBiomes = subscriber.subscribe(builder
						.comment("\r\n Add the ID/resource location of the biome you don't want"
								+"\r\n RS's boulders to spawn in. Separate each ID with a comma ,"
								+"\r\n"
								+"\r\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
						.translation("repurposedstructures.config.misc.blacklistedboulderbiomes")
						.define("blacklistedBoulderBiomes", ""));



					giantBouldersPerChunk = subscriber.subscribe(builder
					.comment("\r\n How many Giant Boulders per chunk. (Can be decimal too)")
					.translation("repurposedstructures.config.misc.giantbouldersperchunk")
					.defineInRange("giantBouldersPerChunk", 0.5D, 0, 100));

					diamondChanceInGiantBoulders = subscriber.subscribe(builder
					.comment("\r\n 1 out of ___ chance of Diamond Ore when placing"
							+ "\n a block in giant Boulders. Lower number = more common."
							+ "\n Enter 0 to disable Diamond Ores completely.")
					.translation("repurposedstructures.config.misc.diamondchanceingiantboulders")
					.defineInRange("diamondChanceInGiantBoulders", 7000, 0, 1000000));


					addTinyBouldersModdedBiomes = subscriber.subscribe(builder
						.comment("\r\n Adds tiny boulders to modded Taiga biomes.")
					.translation("repurposedstructures.config.misc.addtinybouldersmoddedbiomes")
					.define("addTinyBouldersModdedBiomes", false));

					boulderTiny = subscriber.subscribe(builder
							.comment("\r\n Adds tiny boulders to Taiga Mountains and Snowy Taiga Mountains biomes "
								+"\r\n that can contain small amounts of Coal and Iron ores.")
						.translation("repurposedstructures.config.misc.bouldertiny")
						.define("boulderTiny", true));
					
					boulderGiant = subscriber.subscribe(builder
							.comment("\r\n Replaces boulders in Giant Tree Taiga Hills and Giant Spruce Taiga Hills"
								+"\r\n biomes with a larger boulder that can contain Coal, Iron, and extremely"
								+"\r\n rarely, can also have Diamond Ores.")
						.translation("repurposedstructures.config.misc.netherdungeons")
						.define("boulderGiant", true));
					
					hornedSwampTree = subscriber.subscribe(builder
							.comment("\r\n Adds a large tree somewhat uncommonly to Swamp biome and replaces"
								+"\r\n all vanilla trees in Swamp Hills biome with the larger tree.")
						.translation("repurposedstructures.config.misc.hornedswamptree")
						.define("hornedSwampTree", true));
					
				builder.pop();
				
			builder.pop();

			builder.push("Structure Options");
	
				builder.push("Jungle Fortress");
	
					jungleFortressMaxChunkDistance = subscriber.subscribe(builder
							.comment("\r\n How rare are Jungle Fortresses." 
								+"\r\n "
								+"\r\n 1 for spawning in most chunks and 1001 for no spawn.")
							.translation("repurposedstructures.config.junglefortress.junglefortressmaxchunkdistance")
							.defineInRange("jungleFortressMaxChunkDistance", 32, 1, 1001));

					addJungleFortressToModdedBiomes = subscriber.subscribe(builder
							.comment("\r\n Add Jungle Fortress to modded jungle biomes.")
							.translation("repurposedstructures.config.junglefortress.addjunglefortresstomoddedbiomes")
							.define("addJungleFortressToModdedBiomes", true));

					blacklistedFortressBiomes = subscriber.subscribe(builder
							.comment("\r\n Add the ID/resource location of the biome you don't want"
									+"\r\n RS's Jungle Fortresses to spawn in. Separate each ID with a comma ,"
									+"\r\n"
									+"\r\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
							.translation("repurposedstructures.config.junglefortress.blacklistedfortressbiomes")
							.define("blacklistedFortressBiomes", ""));

					silverfishSpawnrate = subscriber.subscribe(builder
							.comment("\r\n How often Silverfish Blocks will generate in Jungle Fortress as a percentage."
								+ "\r\n Note: Mossy Stone Bricks block cannot be infected by Silverfish"
								+ "\n "
								+ "\r\n 0 for no Silverfish Blocks and 100 for max spawnrate.")
							.translation("repurposedstructures.config.junglefortress.silverfishspawnrate")
							.defineInRange("silverfishSpawnrate", 0.5D, 0, 100));
					
					allowSilverfishSpawner = subscriber.subscribe(builder
							.comment("\r\n Silverfish Mob Spawners generate in Stone Fortresses."
								+"\r\n If turned off, the spawners will become Skeleton spawners.")
							.translation("repurposedstructures.config.junglefortress.allowsilverfishspawner")
							.define("allowSilverfishSpawner", true));

					lootChests = subscriber.subscribe(builder
							.comment("\r\n Controls whether loot chests spawn or not in Jungle Fortresses.")
						.translation("repurposedstructures.config.junglefortress.lootchests")
						.define("lootChests", true));


				builder.pop();
				
				builder.push("Igloos");

					blacklistedIglooBiomes = subscriber.subscribe(builder
							.comment("\r\n Add the ID/resource location of the biome you don't want"
									+"\r\n RS's Igloos to spawn in. Separate each ID with a comma ,"
									+"\r\n"
									+"\r\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
							.translation("repurposedstructures.config.igloo.blacklistedigloobiomes")
							.define("blacklistedIglooBiomes", ""));

					grassyIglooMaxChunkDistance = subscriber.subscribe(builder
						.comment("\r\n How rare are Grassy Igloos in Plains and Forests." 
							+ "\n "
							+ "\r\n 1 for spawning in most chunks and 1001 for no spawn.")
						.translation("repurposedstructures.config.igloo.grassyigloomaxchunkdistance")
						.defineInRange("grassyIglooMaxChunkDistance", 20, 1, 1001));
	
					addGrassyIglooToModdedBiomes = subscriber.subscribe(builder
							.comment("\r\n Add Grassy Igloos to modded biomes that are"
								+"\r\n most likely grassy fields or temperate forests.")
						.translation("repurposedstructures.config.igloo.addgrassyiglootomoddedbiomes")
						.define("addGrassyIglooToModdedBiomes", true));

					stoneIglooMaxChunkDistance = subscriber.subscribe(builder
						.comment("\r\n How rare are Stone Igloos in Giant Tree Taiga biomes." 
							+ "\n "
							+ "\r\n 1 for spawning in most chunks and 1001 for no spawn.")
						.translation("repurposedstructures.config.igloo.stoneigloomaxchunkdistance")
						.defineInRange("stoneIglooMaxChunkDistance", 20, 1, 1001));
	
					addStoneIglooToModdedBiomes = subscriber.subscribe(builder
							.comment("\r\n Add Stone Igloos to modded biomes that are"
								+"\r\n most likely Giant Tree Taiga variants.")
						.translation("repurposedstructures.config.igloo.addstoneiglootomoddedbiomes")
						.define("addStoneIglooToModdedBiomes", true));
					
				builder.pop();
				
			builder.pop();
		}
		
	}
}
