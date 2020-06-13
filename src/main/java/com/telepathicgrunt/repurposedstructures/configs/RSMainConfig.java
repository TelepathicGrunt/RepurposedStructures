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
		public ConfigValueListener<Boolean> addMiscToModdedBiomes;
		public ConfigValueListener<Boolean> boulderTiny;
		public ConfigValueListener<Boolean> boulderGiant;
		public ConfigValueListener<Boolean> hornedSwampTree;
		
		public ConfigValueListener<Integer> jungleFortressSpawnrate;
		public ConfigValueListener<Double> silverfishSpawnrateJF;
		public ConfigValueListener<Boolean> allowSilverfishSpawnerJF;
		public ConfigValueListener<Boolean> lootChestsJF;
		public ConfigValueListener<Boolean> addJungleFortressToModdedBiomes;
		
		public ConfigValueListener<Integer> netherTempleSpawnrate;
		public ConfigValueListener<Boolean> lootChestsNT;
		public ConfigValueListener<Boolean> addNetherTempleToModdedBiomes;
		
		public ConfigValueListener<Integer> badlandsTempleSpawnrate;
		public ConfigValueListener<Boolean> lootChestsBT;
		public ConfigValueListener<Boolean> addBadlandsTempleToModdedBiomes;

		public ConfigValueListener<Integer> grassyIglooSpawnrate;
		public ConfigValueListener<Boolean> addGrassyIglooToModdedBiomes;
		public ConfigValueListener<Integer> stoneIglooSpawnrate;
		public ConfigValueListener<Boolean> addStoneIglooToModdedBiomes;

		public RSConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
			builder.push("Feature Options");
			
				builder.push("Misc");
				
					addMiscToModdedBiomes = subscriber.subscribe(builder
							.comment("\r\n Add the custom features to modded biomes of the same categories/type.")
						.translation("repurposedstructures.config.feature.misc.addmisctomoddedbiomes")
						.define("addMiscToModdedBiomes", false));

					boulderTiny = subscriber.subscribe(builder
							.comment("\r\n Adds tiny boulders to Taiga Mountains and Snowy Taiga Mountains biomes "
								+"\r\n that can contain small amounts of Coal and Iron ores.")
						.translation("repurposedstructures.config.feature.misc.bouldertiny")
						.define("boulderTiny", true));
					
					boulderGiant = subscriber.subscribe(builder
							.comment("\r\n Replaces boulders in Giant Tree Taiga Hills and Giant Spruce Taiga Hills"
								+"\r\n biomes with a larger boulder that can contain Coal, Iron, and extremely"
								+"\r\n rarely, can also have Diamond Ores.")
						.translation("repurposedstructures.config.feature.misc.netherdungeons")
						.define("boulderGiant", true));
					
					hornedSwampTree = subscriber.subscribe(builder
							.comment("\r\n Adds a large tree somewhat uncommonly to Swamp biome and replaces"
								+"\r\n all vanilla trees in Swamp Hills biome with the larger tree.")
						.translation("repurposedstructures.config.feature.misc.hornedSwampTree")
						.define("hornedSwampTree", true));
					
				builder.pop();
				
			builder.pop();

			builder.push("Structure Options");
	
				builder.push("Jungle Fortress");
	
					jungleFortressSpawnrate = subscriber.subscribe(builder
							.comment("\r\n How rare are Jungle Fortresses." 
								+"\r\n "
								+"\r\n 1 for spawning in most chunks and 1001 for no spawn.")
							.translation("repurposedstructures.config.structure.junglefortress.junglefortressspawnrate")
							.defineInRange("jungleFortressSpawnrate", 32, 1, 1001));

					silverfishSpawnrateJF = subscriber.subscribe(builder
							.comment("\r\n How often Silverfish Blocks will generate in Jungle Fortress as a percentage."
								+ "\r\n Note: Mossy Stone Bricks block cannot be infected by Silverfish"
								+ "\n "
								+ "\r\n 0 for no Silverfish Blocks and 100 for max spawnrate.")
							.translation("repurposedstructures.config.structure.junglefortress.silverfishspawnratejf")
							.defineInRange("silverfishSpawnrateJF", 0.5D, 0, 100));
					
					allowSilverfishSpawnerJF = subscriber.subscribe(builder
							.comment("\r\n Silverfish Mob Spawners generate in Stone Fortresses."
								+"\r\n If turned off, the spawners will become Skeleton spawners.")
							.translation("repurposedstructures.config.structure.junglefortress.allowsilverfishspawnerjf")
							.define("allowSilverfishSpawnerJF", true));

					lootChestsJF = subscriber.subscribe(builder
							.comment("\r\n Controls whether loot chests spawn or not in Jungle Fortresses.")
						.translation("repurposedstructures.config.structure.junglefortress.lootchestsjf")
						.define("lootChestsJF", true));

					addJungleFortressToModdedBiomes = subscriber.subscribe(builder
							.comment("\r\n Add Jungle Fortress to modded jungle biomes.")
						.translation("repurposedstructures.config.structure.junglefortress.addjunglefortresstomoddedbiomes")
						.define("addJungleFortressToModdedBiomes", false));

				builder.pop();
				
				builder.push("Nether Temple");
	
					netherTempleSpawnrate = subscriber.subscribe(builder
							.comment("\r\n How rare are Nether Temples in Nether." 
								+ "\n "
								+ "\r\n 1 for spawning in most chunks and 1001 for no spawn.")
							.translation("repurposedstructures.config.structure.nethertemple.nethertemplespawnrate")
							.defineInRange("netherTempleSpawnrate", 20, 1, 1001));

					lootChestsNT = subscriber.subscribe(builder
							.comment("\r\n Controls whether loot chests spawn or not in Nether Temples.")
						.translation("repurposedstructures.config.structure.nethertemple.lootchestsnt")
						.define("lootChestsNT", true));

					addNetherTempleToModdedBiomes = subscriber.subscribe(builder
							.comment("\r\n Add Jungle Fortress to modded jungle biomes.")
						.translation("repurposedstructures.config.structure.nethertemple.addnethertempletomoddedbiomes")
						.define("addNetherTempleToModdedBiomes", false));
					
				builder.pop();
				
				builder.push("Badlands Temple");
				
					badlandsTempleSpawnrate = subscriber.subscribe(builder
							.comment("\r\n How rare are Nether Temples in Nether." 
								+ "\n "
								+ "\r\n 1 for spawning in most chunks and 1001 for no spawn.")
							.translation("repurposedstructures.config.structure.badlandstemple.badlandstemplespawnrate")
							.defineInRange("badlandsTempleSpawnrate", 20, 1, 1001));
	
					lootChestsBT = subscriber.subscribe(builder
							.comment("\r\n Controls whether loot chests spawn or not in Badlands Temples.")
						.translation("repurposedstructures.config.structure.badlandstemple.lootchestsbt")
						.define("lootChestsBT", true));
	
					addBadlandsTempleToModdedBiomes = subscriber.subscribe(builder
							.comment("\r\n Add Jungle Fortress to modded jungle biomes.")
						.translation("repurposedstructures.config.structure.badlandstemple.addbadlandstempletomoddedbiomes")
						.define("addBadlandsTempleToModdedBiomes", false));
				
				builder.pop();
				
				builder.push("Igloos");
				
					grassyIglooSpawnrate = subscriber.subscribe(builder
						.comment("\r\n How rare are Grassy Igloos in Plains and Forests." 
							+ "\n "
							+ "\r\n 1 for spawning in most chunks and 1001 for no spawn.")
						.translation("repurposedstructures.config.structure.igloo.grassyigloospawnrate")
						.defineInRange("grassyIglooSpawnrate", 20, 1, 1001));
	
					addGrassyIglooToModdedBiomes = subscriber.subscribe(builder
							.comment("\r\n Add Grassy Igloos to modded biomes that are"
								+"\r\n most likely grassy fields or temperate forests.")
						.translation("repurposedstructures.config.structure.igloo.addgrassyiglootomoddedbiomes")
						.define("addGrassyIglooToModdedBiomes", false));

					stoneIglooSpawnrate = subscriber.subscribe(builder
						.comment("\r\n How rare are Stone Igloos in Giant Tree Taiga biomes." 
							+ "\n "
							+ "\r\n 1 for spawning in most chunks and 1001 for no spawn.")
						.translation("repurposedstructures.config.structure.igloo.stoneigloospawnrate")
						.defineInRange("stoneIglooSpawnrate", 20, 1, 1001));
	
					addStoneIglooToModdedBiomes = subscriber.subscribe(builder
							.comment("\r\n Add Stone Igloos to modded biomes that are"
								+"\r\n most likely Giant Tree Taiga variants.")
						.translation("repurposedstructures.config.structure.igloo.addstoneiglootomoddedbiomes")
						.define("addStoneIglooToModdedBiomes", false));
					
				builder.pop();
				
			builder.pop();
		}
		
	}
}
