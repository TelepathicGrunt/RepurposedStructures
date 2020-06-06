package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber
public class RSConfig
{
	public static class RSConfigValues
	{
		public ConfigValueListener<Boolean> addDungeonsToModdedBiomes;
		public ConfigValueListener<Integer> badlandsDungeonSpawnrate;
		public ConfigValueListener<Integer> darkForestDungeonSpawnrate;
		public ConfigValueListener<Integer> desertDungeonSpawnrate;
		public ConfigValueListener<Integer> jungleDungeonSpawnrate;
		public ConfigValueListener<Integer> mushroomDungeonSpawnrate;
		public ConfigValueListener<Integer> snowDungeonSpawnrate;
		public ConfigValueListener<Integer> swampDungeonSpawnrate;
		public ConfigValueListener<Integer> endDungeonSpawnrate;
		public ConfigValueListener<Integer> netherDungeonSpawnrate;
		public ConfigValueListener<Integer> oceanDungeonSpawnrate;

		public ConfigValueListener<Integer> badlandsDungeonMinHeight;
		public ConfigValueListener<Integer> darkForestDungeonMinHeight;
		public ConfigValueListener<Integer> desertDungeonMinHeight;
		public ConfigValueListener<Integer> jungleDungeonMinHeight;
		public ConfigValueListener<Integer> mushroomDungeonMinHeight;
		public ConfigValueListener<Integer> snowDungeonMinHeight;
		public ConfigValueListener<Integer> swampDungeonMinHeight;
		public ConfigValueListener<Integer> endDungeonMinHeight;
		public ConfigValueListener<Integer> netherDungeonMinHeight;
		public ConfigValueListener<Integer> oceanDungeonMinHeight;

		public ConfigValueListener<Integer> badlandsDungeonMaxHeight;
		public ConfigValueListener<Integer> darkForestDungeonMaxHeight;
		public ConfigValueListener<Integer> desertDungeonMaxHeight;
		public ConfigValueListener<Integer> jungleDungeonMaxHeight;
		public ConfigValueListener<Integer> mushroomDungeonMaxHeight;
		public ConfigValueListener<Integer> snowDungeonMaxHeight;
		public ConfigValueListener<Integer> swampDungeonMaxHeight;
		public ConfigValueListener<Integer> endDungeonMaxHeight;
		public ConfigValueListener<Integer> netherDungeonMaxHeight;
		public ConfigValueListener<Integer> oceanDungeonMaxHeight;


		public ConfigValueListener<Boolean> addWellsToModdedBiomes;
		public ConfigValueListener<Boolean> canHaveBells;
		public ConfigValueListener<Integer> badlandsWellSpawnrate;
		public ConfigValueListener<Integer> netherWellSpawnrate;
		public ConfigValueListener<Integer> snowWellSpawnrate;
		public ConfigValueListener<Integer> mossyStoneWellSpawnrate;
		public ConfigValueListener<Integer> forestWellSpawnrate;

		public ConfigValueListener<Boolean> addMiscToModdedBiomes;
		public ConfigValueListener<Boolean> boulderTiny;
		public ConfigValueListener<Boolean> boulderGiant;
		public ConfigValueListener<Boolean> hornedSwampTree;
		
		public ConfigValueListener<Boolean> lootChestsMS;
		public ConfigValueListener<Boolean> addMineshaftsToModdedBiomes;
		public ConfigValueListener<Integer> birchMineshaftSpawnrate;
		public ConfigValueListener<Integer> jungleMineshaftSpawnrate;
		public ConfigValueListener<Integer> desertMineshaftSpawnrate;
		public ConfigValueListener<Integer> stoneMineshaftSpawnrate;
		public ConfigValueListener<Integer> savannaMineshaftSpawnrate;
		public ConfigValueListener<Integer> icyMineshaftSpawnrate;
		public ConfigValueListener<Integer> oceanMineshaftSpawnrate;
		public ConfigValueListener<Integer> taigaMineshaftSpawnrate;;
		public ConfigValueListener<Integer> swampAndDarkForestMineshaftSpawnrate;
		public ConfigValueListener<Integer> endMineshaftSpawnrate;
		public ConfigValueListener<Integer> netherMineshaftSpawnrate;

		public ConfigValueListener<Integer> birchMineshaftMinHeight;
		public ConfigValueListener<Integer> jungleMineshaftMinHeight;
		public ConfigValueListener<Integer> desertMineshaftMinHeight;
		public ConfigValueListener<Integer> stoneMineshaftMinHeight;
		public ConfigValueListener<Integer> savannaMineshaftMinHeight;
		public ConfigValueListener<Integer> icyMineshaftMinHeight;
		public ConfigValueListener<Integer> oceanMineshaftMinHeight;
		public ConfigValueListener<Integer> taigaMineshaftMinHeight;;
		public ConfigValueListener<Integer> swampAndDarkForestMineshaftMinHeight;
		public ConfigValueListener<Integer> endMineshaftMinHeight;
		public ConfigValueListener<Integer> netherMineshaftMinHeight;
		
		public ConfigValueListener<Integer> birchMineshaftMaxHeight;
		public ConfigValueListener<Integer> jungleMineshaftMaxHeight;
		public ConfigValueListener<Integer> desertMineshaftMaxHeight;
		public ConfigValueListener<Integer> stoneMineshaftMaxHeight;
		public ConfigValueListener<Integer> savannaMineshaftMaxHeight;
		public ConfigValueListener<Integer> icyMineshaftMaxHeight;
		public ConfigValueListener<Integer> oceanMineshaftMaxHeight;
		public ConfigValueListener<Integer> taigaMineshaftMaxHeight;;
		public ConfigValueListener<Integer> swampAndDarkForestMineshaftMaxHeight;
		public ConfigValueListener<Integer> endMineshaftMaxHeight;
		public ConfigValueListener<Integer> netherMineshaftMaxHeight;

		public ConfigValueListener<Boolean> useVanillaStronghold;
		public ConfigValueListener<Boolean> allowStonebrickStronghold;
		public ConfigValueListener<Boolean> allowNetherStronghold;
		public ConfigValueListener<Integer> strongholdSpawnrate;
		public ConfigValueListener<Double> silverfishSpawnrateSH;
		public ConfigValueListener<Boolean> allowExtraSpawnersSH;
		public ConfigValueListener<Double> strongholdSizeSH;
		public ConfigValueListener<Boolean> lootChestsSH;
		
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

		RSConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
			builder.push("Feature Options");

				builder.push("Dungeons");
				
					addDungeonsToModdedBiomes = subscriber.subscribe(builder
							.comment("\r\n Add the custom dungeons to modded biomes of the same categories/type.")
						.translation("repurposedstructures.config.feature.dungeons.adddungeonstomoddedbiomes")
						.define("addDungeonsToModdedBiomes", false));

					builder.push("Spawnrate");
					
        					badlandsDungeonSpawnrate = subscriber.subscribe(builder
        							.comment("\r\n Replace vanilla dungeon in Badlands biomes with Badlands themed dungeon.\r\n"
        								+" How often dungeons will attempt to spawn per chunk.\r\n " 
        								+" 0 for no Dungeons at all and 1000 for max Dungeon spawnrate.\r\n"
        								+" Note: Vanilla Dungeons will spawn again when this is set to 0.")
        						.translation("repurposedstructures.config.feature.dungeons.badlandsdungeonspawnrate")
        						.defineInRange("badlandsDungeonSpawnrate", 8, 0, 1000));
        				
        					darkForestDungeonSpawnrate = subscriber.subscribe(builder
        							.comment("\r\n Replace vanilla dungeon in Dark Forest biomes with Dark Forest themed dungeon.\r\n"
        								+" How often dungeons will attempt to spawn per chunk.\r\n " 
        								+" 0 for no Dungeons at all and 1000 for max Dungeon spawnrate.\r\n"
        								+" Note: Vanilla Dungeons will spawn again when this is set to 0.")
        						.translation("repurposedstructures.config.feature.dungeons.darkforestdungeonspawnrate")
        						.defineInRange("darkForestDungeonSpawnrate", 8, 0, 1000));
        				
        					desertDungeonSpawnrate = subscriber.subscribe(builder
        							.comment("\r\n Replace vanilla dungeon in Desert biomes with Desert themed dungeon.\r\n"
        								+" How often dungeons will attempt to spawn per chunk.\r\n " 
        								+" 0 for no Dungeons at all and 1000 for max Dungeon spawnrate.\r\n"
        								+" Note: Vanilla Dungeons will spawn again when this is set to 0.")
        						.translation("repurposedstructures.config.feature.dungeons.desertdungeonspawnrate")
        						.defineInRange("desertDungeonSpawnrate", 8, 0, 1000));
        				
        					jungleDungeonSpawnrate = subscriber.subscribe(builder
        							.comment("\r\n Replace vanilla dungeon in Jungle biomes with Jungle themed dungeon.\r\n"
        								+" How often dungeons will attempt to spawn per chunk.\r\n " 
        								+" 0 for no Dungeons at all and 1000 for max Dungeon spawnrate.\r\n"
        								+" Note: Vanilla Dungeons will spawn again when this is set to 0.")
        						.translation("repurposedstructures.config.feature.dungeons.jungledungeonspawnrate")
        						.defineInRange("jungleDungeonSpawnrate", 8, 0, 1000));
        				
        					mushroomDungeonSpawnrate = subscriber.subscribe(builder
        							.comment("\r\n Replace vanilla dungeon in Mushroom biomes with Mushroom themed dungeon.\r\n"
        								+" How often dungeons will attempt to spawn per chunk.\r\n " 
        								+" 0 for no Dungeons at all and 1000 for max Dungeon spawnrate.\r\n"
        								+" Note: Vanilla Dungeons will spawn again when this is set to 0.")
        						.translation("repurposedstructures.config.feature.dungeons.mushroomdungeonspawnrate")
        						.defineInRange("mushroomDungeonSpawnrate", 8, 0, 1000));
        				
        					snowDungeonSpawnrate = subscriber.subscribe(builder
        							.comment("\r\n Replace vanilla dungeon in icy/snow biomes with icy/snow themed dungeon.\r\n"
        								+" How often dungeons will attempt to spawn per chunk.\r\n " 
        								+" 0 for no Dungeons at all and 1000 for max Dungeon spawnrate.\r\n"
        								+" Note: Vanilla Dungeons will spawn again when this is set to 0.")
        						.translation("repurposedstructures.config.feature.dungeons.snowdungeonspawnrate")
        						.defineInRange("snowDungeonSpawnrate", 8, 0, 1000));
        				
        					swampDungeonSpawnrate = subscriber.subscribe(builder
        							.comment("\r\n Replace vanilla dungeon in Swamp biomes with Swamp themed dungeon.\r\n"
        								+" How often dungeons will attempt to spawn per chunk.\r\n " 
        								+" 0 for no Dungeons at all and 1000 for max Dungeon spawnrate.\r\n"
        								+" Note: Vanilla Dungeons will spawn again when this is set to 0.")
        						.translation("repurposedstructures.config.feature.dungeons.swampdungeonspawnrate")
        						.defineInRange("swampDungeonSpawnrate", 8, 0, 1000));
        				
        					endDungeonSpawnrate = subscriber.subscribe(builder
        							.comment("\r\n Add End themed dungeon to End biomes outside the Enderdragon island.\r\n"
        								+" How often dungeons will attempt to spawn per chunk.\r\n " 
        								+" 0 for no Dungeons at all and 1000 for max Dungeon spawnrate.")
        						.translation("repurposedstructures.config.feature.dungeons.enddungeonspawnrate")
        						.defineInRange("endDungeonSpawnrate", 8, 0, 1000));
        
        					netherDungeonSpawnrate = subscriber.subscribe(builder
        							.comment("\r\n Add Nether themed dungeon to Nether biomes.\r\n"
        								+" How often dungeons will attempt to spawn per chunk.\r\n " 
        								+" 0 for no Dungeons at all and 1000 for max Dungeon spawnrate.")
        						.translation("repurposedstructures.config.feature.dungeons.netherdungeonspawnrate")
        						.defineInRange("netherDungeonSpawnrate", 8, 0, 1000));
        
            					oceanDungeonSpawnrate = subscriber.subscribe(builder
            							.comment("\r\n Add ocean themed dungeon to ocean biomes. These will spawn on"
            								+"\r\n the ocean's floor and inside water filled caves and ravines.\r\n"
            								+"\r\n"
            								+" How often dungeons will attempt to spawn per chunk.\r\n " 
            								+" 0 for no dungeons at all and 1000 for max dungeon spawnrate."
            								+"\r\n"
            								+"\r\n Note: Vanilla Dungeons will still generate if the biome has "
            								+"\r\n them which is unlike the other modded dungeons from this mod"
            								+"\r\n as those would normally replace the Vanilla Dungeons.")
        						.translation("repurposedstructures.config.feature.dungeons.oceandungeonspawnrate")
        						.defineInRange("oceanDungeonSpawnrate", 8, 0, 1000));
        

    					builder.pop();
    					
    					builder.push("Min Height");

        					badlandsDungeonMinHeight = subscriber.subscribe(builder
        							.comment("\r\n Minimum Y height that this dungeon can spawn at. Default is 2.\r\n"
        								+" Note: The dungeon will spawn between min and max y height set in config.")
        						.translation("repurposedstructures.config.feature.dungeons.badlandsdungeonminheight")
        						.defineInRange("badlandsDungeonMinHeight", 2, 2, 255));

        					darkForestDungeonMinHeight = subscriber.subscribe(builder
        							.comment("\r\n Minimum Y height that this dungeon can spawn at. Default is 2.\r\n"
        								+" Note: The dungeon will spawn between min and max y height set in config.")
        						.translation("repurposedstructures.config.feature.dungeons.darkforestdungeonminheight")
        						.defineInRange("darkForestDungeonMinHeight", 2, 2, 255));

        					desertDungeonMinHeight = subscriber.subscribe(builder
        							.comment("\r\n Minimum Y height that this dungeon can spawn at. Default is 2.\r\n"
        								+" Note: The dungeon will spawn between min and max y height set in config.")
        						.translation("repurposedstructures.config.feature.dungeons.desertdungeonminheight")
        						.defineInRange("desertDungeonMinHeight", 2, 2, 255));

        					jungleDungeonMinHeight = subscriber.subscribe(builder
        							.comment("\r\n Minimum Y height that this dungeon can spawn at. Default is 2.\r\n"
        								+" Note: The dungeon will spawn between min and max y height set in config.")
        						.translation("repurposedstructures.config.feature.dungeons.jungledungeonminheight")
        						.defineInRange("jungleDungeonMinHeight", 2, 2, 255));

        					mushroomDungeonMinHeight = subscriber.subscribe(builder
        							.comment("\r\n Minimum Y height that this dungeon can spawn at. Default is 2.\r\n"
        								+" Note: The dungeon will spawn between min and max y height set in config.")
        						.translation("repurposedstructures.config.feature.dungeons.mushroomdungeonminheight")
        						.defineInRange("mushroomDungeonMinHeight", 2, 2, 255));

        					snowDungeonMinHeight = subscriber.subscribe(builder
        							.comment("\r\n Minimum Y height that this dungeon can spawn at. Default is 2.\r\n"
        								+" Note: The dungeon will spawn between min and max y height set in config.")
        						.translation("repurposedstructures.config.feature.dungeons.snowdungeonminheight")
        						.defineInRange("snowDungeonMinHeight", 2, 2, 255));

        					swampDungeonMinHeight = subscriber.subscribe(builder
        							.comment("\r\n Minimum Y height that this dungeon can spawn at. Default is 2.\r\n"
        								+" Note: The dungeon will spawn between min and max y height set in config.")
        						.translation("repurposedstructures.config.feature.dungeons.swampdungeonminheight")
        						.defineInRange("swampDungeonMinHeight", 2, 2, 255));

        					endDungeonMinHeight = subscriber.subscribe(builder
        							.comment("\r\n Minimum Y height that this dungeon can spawn at. Default is 2.\r\n"
        								+" Note: The dungeon will spawn between min and max y height set in config.")
        						.translation("repurposedstructures.config.feature.dungeons.enddungeonminheight")
        						.defineInRange("endDungeonMinHeight", 2, 2, 255));

        					netherDungeonMinHeight = subscriber.subscribe(builder
        							.comment("\r\n Minimum Y height that this dungeon can spawn at. Default is 2.\r\n"
        								+" Note: The dungeon will spawn between min and max y height set in config.")
        						.translation("repurposedstructures.config.feature.dungeons.netherdungeonminheight")
        						.defineInRange("netherDungeonMinHeight", 2, 2, 255));

        					oceanDungeonMinHeight = subscriber.subscribe(builder
        							.comment("\r\n Minimum Y height that this dungeon can spawn at. Default is 3.\r\n"
        								+" Note: The dungeon will spawn between min and max y height set in config.")
        						.translation("repurposedstructures.config.feature.dungeons.oceandungeonminheight")
        						.defineInRange("oceanDungeonMinHeight", 3, 3, 255));

    					builder.pop();
    					

    					builder.push("Max Height");

        					badlandsDungeonMaxHeight = subscriber.subscribe(builder
        							.comment("\r\n Maximum Y height that this dungeon can spawn at. Default is 255.\r\n"
        								+" Note: The dungeon will spawn between min and max y height set in config.\r\n"
        								+" Setting this to below min height config will make dungeon spawn only at min height.")
        						.translation("repurposedstructures.config.feature.dungeons.badlandsdungeonmaxheight")
        						.defineInRange("badlandsDungeonMaxHeight", 255, 2, 255));

        					darkForestDungeonMaxHeight = subscriber.subscribe(builder
        							.comment("\r\n Maximum Y height that this dungeon can spawn at. Default is 255.\r\n"
        								+" Note: The dungeon will spawn between min and max y height set in config.\r\n"
        								+" Setting this to below min height config will make dungeon spawn only at min height.")
        						.translation("repurposedstructures.config.feature.dungeons.darkforestdungeonmaxheight")
        						.defineInRange("darkForestDungeonMaxHeight", 255, 2, 255));

        					desertDungeonMaxHeight = subscriber.subscribe(builder
        							.comment("\r\n Maximum Y height that this dungeon can spawn at. Default is 255.\r\n"
        								+" Note: The dungeon will spawn between min and max y height set in config.\r\n"
        								+" Setting this to below min height config will make dungeon spawn only at min height.")
        						.translation("repurposedstructures.config.feature.dungeons.desertdungeonmaxheight")
        						.defineInRange("desertDungeonMaxHeight", 255, 2, 255));

        					jungleDungeonMaxHeight = subscriber.subscribe(builder
        							.comment("\r\n Maximum Y height that this dungeon can spawn at. Default is 255.\r\n"
        								+" Note: The dungeon will spawn between min and max y height set in config.\r\n"
        								+" Setting this to below min height config will make dungeon spawn only at min height.")
        						.translation("repurposedstructures.config.feature.dungeons.jungledungeonmaxheight")
        						.defineInRange("jungleDungeonMaxHeight", 255, 2, 255));

        					mushroomDungeonMaxHeight = subscriber.subscribe(builder
        							.comment("\r\n Maximum Y height that this dungeon can spawn at. Default is 255.\r\n"
        								+" Note: The dungeon will spawn between min and max y height set in config.\r\n"
        								+" Setting this to below min height config will make dungeon spawn only at min height.")
        						.translation("repurposedstructures.config.feature.dungeons.mushroomdungeonmaxheight")
        						.defineInRange("mushroomDungeonMaxHeight", 255, 2, 255));

        					snowDungeonMaxHeight = subscriber.subscribe(builder
        							.comment("\r\n Maximum Y height that this dungeon can spawn at. Default is 255.\r\n"
        								+" Note: The dungeon will spawn between min and max y height set in config.\r\n"
        								+" Setting this to below min height config will make dungeon spawn only at min height.")
        						.translation("repurposedstructures.config.feature.dungeons.snowdungeonmaxheight")
        						.defineInRange("snowDungeonMaxHeight", 255, 2, 255));

        					swampDungeonMaxHeight = subscriber.subscribe(builder
        							.comment("\r\n Maximum Y height that this dungeon can spawn at. Default is 255.\r\n"
        								+" Note: The dungeon will spawn between min and max y height set in config.\r\n"
        								+" Setting this to below min height config will make dungeon spawn only at min height.")
        						.translation("repurposedstructures.config.feature.dungeons.swampdungeonmaxheight")
        						.defineInRange("swampDungeonMaxHeight", 255, 2, 255));

        					endDungeonMaxHeight = subscriber.subscribe(builder
        							.comment("\r\n Maximum Y height that this dungeon can spawn at. Default is 255.\r\n"
        								+" Note: The dungeon will spawn between min and max y height set in config.\r\n"
        								+" Setting this to below min height config will make dungeon spawn only at min height.")
        						.translation("repurposedstructures.config.feature.dungeons.enddungeonmaxheight")
        						.defineInRange("endDungeonMaxHeight", 255, 2, 255));

        					netherDungeonMaxHeight = subscriber.subscribe(builder
        							.comment("\r\n Maximum Y height that this dungeon can spawn at. Default is 255.\r\n"
        								+" Note: The dungeon will spawn between min and max y height set in config.\r\n"
        								+" Setting this to below min height config will make dungeon spawn only at min height.")
        						.translation("repurposedstructures.config.feature.dungeons.netherdungeonmaxheight")
        						.defineInRange("netherDungeonMaxHeight", 255, 2, 255));

        					oceanDungeonMaxHeight = subscriber.subscribe(builder
        							.comment("\r\n Maximum Y height that this dungeon can spawn at. Default is 255.\r\n"
        								+" Note: The dungeon will spawn between min and max y height set in config.\r\n"
        								+" Setting this to below min height config will make dungeon spawn only at min height.")
        						.translation("repurposedstructures.config.feature.dungeons.oceandungeonmaxheight")
        						.defineInRange("oceanDungeonMaxHeight", 255, 3, 255));

    					builder.pop();
    					
				builder.pop();

				builder.push("Small Wells");
				
					addWellsToModdedBiomes = subscriber.subscribe(builder
							.comment("\r\n Add the custom wells to modded biomes of the same categories/type.")
						.translation("repurposedstructures.config.feature.smallwells.addwellstomoddedbiomes")
						.define("addWellsToModdedBiomes", false));

					canHaveBells = subscriber.subscribe(builder
							.comment("\r\n Determines if Wells can have a chance of spawning a Bell.")
						.translation("repurposedstructures.config.feature.smallwells.canhavebells")
						.define("canHaveBells", true));
					
					badlandsWellSpawnrate  = subscriber.subscribe(builder
							.comment("\r\n Adds Badlands themed wells to Badlands biomes."
								+ "\r\n This effects how often wells will attempt to spawn per chunk." 
								+ "\r\n The chance of a well generating at a chunk is 1/spawnrate."
								+ "\r\n 1 for wells spawning in every chunk and 10000 for no wells.")
						.translation("repurposedstructures.config.feature.smallwells.badlandswellspawnrate")
						.defineInRange("badlandsWellSpawnrate", 350, 1, 10000));
					
					netherWellSpawnrate = subscriber.subscribe(builder
							.comment("\r\n Adds Nether themed wells to Nether biomes."
								+ "\r\n This effects how often wells will attempt to spawn per chunk." 
								+ "\r\n The chance of a well generating at a chunk is 1/spawnrate."
								+ "\r\n 1 for wells spawning in every chunk and 10000 for no wells.")
						.translation("repurposedstructures.config.feature.smallwells.netherwellspawnrate")
						.defineInRange("netherWellSpawnrate", 350, 1, 10000));
					
					snowWellSpawnrate = subscriber.subscribe(builder
							.comment("\r\n Adds Snow themed wells to snowy and icy biomes."
								+ "\r\n This effects how often wells will attempt to spawn per chunk." 
								+ "\r\n The chance of a well generating at a chunk is 1/spawnrate."
								+ "\r\n 1 for wells spawning in every chunk and 10000 for no wells.")
						.translation("repurposedstructures.config.feature.smallwells.snowwellspawnrate")
						.defineInRange("snowWellSpawnrate", 350, 1, 10000));
					
					mossyStoneWellSpawnrate = subscriber.subscribe(builder
							.comment("\r\n Adds mossy stone themed wells to Jungles, Dark Oak, and Swamp biomes."
								+ "\r\n This effects how often wells will attempt to spawn per chunk." 
								+ "\r\n The chance of a well generating at a chunk is 1/spawnrate."
								+ "\r\n 1 for wells spawning in every chunk and 10000 for no wells.")
						.translation("repurposedstructures.config.feature.smallwells.mossystonewellspawnrate")
						.defineInRange("mossyStoneWellSpawnrate", 350, 1, 10000));

					forestWellSpawnrate = subscriber.subscribe(builder
							.comment("\r\n Adds a wood themed wells to Forest and Birch Forest biomes."
								+ "\r\n This effects how often wells will attempt to spawn per chunk." 
								+ "\r\n The chance of a well generating at a chunk is 1/spawnrate."
								+ "\r\n 1 for wells spawning in every chunk and 10000 for no wells.")
						.translation("repurposedstructures.config.feature.smallwells.forestwellspawnrate")
						.defineInRange("forestWellSpawnrate", 350, 1, 10000));
					
				builder.pop();
				
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

				builder.push("Mineshaft");

					lootChestsMS = subscriber.subscribe(builder
							.comment("\r\n Controls whether loot chests spawn or not in modded Mineshafts.")
						.translation("repurposedstructures.config.structure.mineshaft.lootchestsms")
						.define("lootChestsMS", true));

					addMineshaftsToModdedBiomes = subscriber.subscribe(builder
							.comment("\r\n Add the custom Mineshafts to modded biomes of the same categories/type.")
						.translation("repurposedstructures.config.feature.dungeons.addmineshaftstomoddedbiomes")
						.define("addMineshaftsToModdedBiomes", false));

					builder.push("Spawnrate");
        					
        					birchMineshaftSpawnrate = subscriber.subscribe(builder
        							.comment("\r\n Replace Mineshafts in Birch biomes with a Birch themed Mineshaft."
        								+"\r\n How often Mineshafts will spawn.\r\n " 
        								+ "0 for no Mineshafts and 1000 for max spawnrate."
        								+"\r\n Note: Vanilla Mineshafts will spawn again when this is set to 0 and game is restarted.")
        						.translation("repurposedstructures.config.feature.mineshaft.birchmineshaftspawnrate")
        						.defineInRange("birchMineshaftSpawnrate", 40, 0, 1000));
        
        					jungleMineshaftSpawnrate = subscriber.subscribe(builder
        							.comment("\r\n Replace Mineshafts in Jungle biomes with a Jungle themed Mineshaft."
        								+"\r\n How often Mineshafts will spawn.\r\n " 
        								+ "0 for no Mineshafts and 1000 for max spawnrate."
        								+"\r\n Note: Vanilla Mineshafts will spawn again when this is set to 0 and game is restarted.")
        						.translation("repurposedstructures.config.feature.mineshaft.junglemineshaftspawnrate")
        						.defineInRange("jungleMineshaftSpawnrate", 40, 0, 1000));
        
        					desertMineshaftSpawnrate = subscriber.subscribe(builder
        							.comment("\r\n Replace Mineshafts in Desert biomes with a Desert themed Mineshaft."
        								+"\r\n How often Mineshafts will spawn.\r\n " 
        								+ "0 for no Mineshafts and 1000 for max spawnrate."
        								+"\r\n Note: Vanilla Mineshafts will spawn again when this is set to 0 and game is restarted.")
        						.translation("repurposedstructures.config.feature.mineshaft.desertmineshaftspawnrate")
        						.defineInRange("desertMineshaftSpawnrate", 40, 0, 1000));
        
        					stoneMineshaftSpawnrate = subscriber.subscribe(builder
        							.comment("\r\n Replace Mineshafts in Mountain (Extreme Hills) biomes with a Stone themed Mineshaft."
        								+"\r\n How often Mineshafts will spawn.\r\n " 
        								+ "0 for no Mineshafts and 1000 for max spawnrate."
        								+"\r\n Note: Vanilla Mineshafts will spawn again when this is set to 0 and game is restarted.")
        						.translation("repurposedstructures.config.feature.mineshaft.stonemineshaftspawnrate")
        						.defineInRange("stoneMineshaftSpawnrate", 40, 0, 1000));
        
        					savannaMineshaftSpawnrate = subscriber.subscribe(builder
        							.comment("\r\n Replace Mineshafts in Savanna biomes with a Savanna themed Mineshaft."
        								+"\r\n How often Mineshafts will spawn.\r\n " 
        								+ "0 for no Mineshafts and 1000 for max spawnrate."
        								+"\r\n Note: Vanilla Mineshafts will spawn again when this is set to 0 and game is restarted.")
        						.translation("repurposedstructures.config.feature.mineshaft.savannamineshaftspawnrate")
        						.defineInRange("savannaMineshaftSpawnrate", 40, 0, 1000));
        
        					icyMineshaftSpawnrate = subscriber.subscribe(builder
        							.comment("\r\n Replace Mineshafts in Snowy/Icy biomes with an Ice themed Mineshaft."
        									+"\r\n Note: Snowy Taiga Biomes will get Ice Mineshaft instead of Taiga theme."
        									+"\r\n How often Mineshafts will spawn.\r\n " 
        									+ "0 for no Mineshafts and 1000 for max spawnrate."
        									+"\r\n Note: Vanilla Mineshafts will spawn again when this is set to 0 and game is restarted.")
        						.translation("repurposedstructures.config.feature.mineshaft.icymineshaftspawnrate")
        						.defineInRange("icyMineshaftSpawnrate", 40, 0, 1000));
        
        					oceanMineshaftSpawnrate = subscriber.subscribe(builder
        							.comment("\r\n Replace Mineshafts in Ocean biomes with an Ocean themed Mineshaft."
        								+"\r\n How often Mineshafts will spawn.\r\n " 
        								+ "0 for no Mineshafts and 1000 for max spawnrate."
        								+"\r\n Note: Vanilla Mineshafts will spawn again when this is set to 0 and game is restarted.")
        						.translation("repurposedstructures.config.feature.mineshaft.oceanmineshaftspawnrate")
        						.defineInRange("oceanMineshaftSpawnrate", 40, 0, 1000));
        
        					taigaMineshaftSpawnrate = subscriber.subscribe(builder
        							.comment("\r\n Replace Mineshafts in Taiga biomes with a Taiga themed Mineshaft."
        								+"\r\n How often Mineshafts will spawn.\r\n " 
        								+ "0 for no Mineshafts and 1000 for max spawnrate."
        								+" Note: Vanilla Mineshafts will spawn again when this is set to 0 and game is restarted.")
        						.translation("repurposedstructures.config.feature.mineshaft.taigamineshaftspawnrate")
        						.defineInRange("taigaMineshaftSpawnrate", 40, 0, 1000));
        					
        					swampAndDarkForestMineshaftSpawnrate = subscriber.subscribe(builder
        							.comment("\r\n Replace Mineshafts in Swamps and Dark Forests with a"
        								+"\r\n swampy/dark oak themed Mineshaft."
        								+"\r\n Note: Vanilla Mineshafts will spawn again when this is set to 0 and game is restarted.")
        						.translation("repurposedstructures.config.feature.mineshaft.swampanddarkforestmineshaftspawnrate"
        							+"\r\n How often Mineshafts will spawn.\r\n " 
        							+ "0 for no Mineshafts and 1000 for max spawnrate.")
        						.defineInRange("swampAndDarkForestMineshaftSpawnrate", 40, 0, 1000));
        					
        					endMineshaftSpawnrate = subscriber.subscribe(builder
        							.comment("\r\n Add End themed Mineshafts to End biomes outside the Enderdragon island."
        								+"\r\n How often Mineshafts will spawn.\r\n " 
        								+ "0 for no Mineshafts and 1000 for max spawnrate.")
        						.translation("repurposedstructures.config.feature.mineshaft.endmineshaftspawnrate")
        						.defineInRange("endMineshaftSpawnrate", 40, 0, 1000));
        					
        					netherMineshaftSpawnrate = subscriber.subscribe(builder
        							.comment("\r\n Add Nether themed Mineshafts to Nether biomes."
        								+"\r\n How often Mineshafts will spawn.\r\n " 
        								+ "0 for no Mineshafts and 1000 for max spawnrate.")
        						.translation("repurposedstructures.config.feature.mineshaft.nethermineshaftspawnrate")
        						.defineInRange("netherMineshaftSpawnrate", 40, 0, 1000));

    					builder.pop();

					builder.push("Min height");
					
        					birchMineshaftMinHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 0.\r\n"
								+" Note: The mineshaft will spawn between min and max y height set in config.\r\n"
								+" Setting this to below min height config will make mineshaft spawn only at min height.")
        						.translation("repurposedstructures.config.feature.mineshaft.birchmineshaftminheight")
        						.defineInRange("birchMineshaftMinHeight", 0, 0, 255));

        					
        					jungleMineshaftMinHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 0.\r\n"
								+" Note: The mineshaft will spawn between min and max y height set in config.\r\n")
        						.translation("repurposedstructures.config.feature.mineshaft.junglemineshaftminheight")
        						.defineInRange("jungleMineshaftMinHeight", 0, 0, 255));

        					
        					desertMineshaftMinHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 0.\r\n"
								+" Note: The mineshaft will spawn between min and max y height set in config.\r\n")
        						.translation("repurposedstructures.config.feature.mineshaft.desertmineshaftminheight")
        						.defineInRange("desertMineshaftMinHeight", 0, 0, 255));

        					
        					stoneMineshaftMinHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 0.\r\n"
								+" Note: The mineshaft will spawn between min and max y height set in config.\r\n")
        						.translation("repurposedstructures.config.feature.mineshaft.stonemineshaftminheight")
        						.defineInRange("stoneMineshaftMinHeight", 0, 0, 255));

        					
        					savannaMineshaftMinHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 0.\r\n"
								+" Note: The mineshaft will spawn between min and max y height set in config.\r\n")
        						.translation("repurposedstructures.config.feature.mineshaft.savannamineshaftminheight")
        						.defineInRange("savannaMineshaftMinHeight", 0, 0, 255));

        					
        					icyMineshaftMinHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 0.\r\n"
								+" Note: The mineshaft will spawn between min and max y height set in config.\r\n")
        						.translation("repurposedstructures.config.feature.mineshaft.icymineshaftminheight")
        						.defineInRange("icyMineshaftMinHeight", 0, 0, 255));

        					
        					oceanMineshaftMinHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 0.\r\n"
								+" Note: The mineshaft will spawn between min and max y height set in config.\r\n")
        						.translation("repurposedstructures.config.feature.mineshaft.oceanmineshaftminheight")
        						.defineInRange("oceanMineshaftMinHeight", 0, 0, 255));

        					
        					taigaMineshaftMinHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 0.\r\n"
								+" Note: The mineshaft will spawn between min and max y height set in config.\r\n")
        						.translation("repurposedstructures.config.feature.mineshaft.taigamineshaftminheight")
        						.defineInRange("taigaMineshaftMinHeight", 0, 0, 255));

        					
        					swampAndDarkForestMineshaftMinHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 0.\r\n"
								+" Note: The mineshaft will spawn between min and max y height set in config.\r\n")
        						.translation("repurposedstructures.config.feature.mineshaft.swampanddarkforestmineshaftminheight")
        						.defineInRange("swampAndDarkForestMineshaftMinHeight", 0, 0, 255));

        					endMineshaftMinHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 30.\r\n"
								+" Note: The mineshaft will spawn between min and max y height set in config.\r\n")
        						.translation("repurposedstructures.config.feature.mineshaft.endmineshaftminheight")
        						.defineInRange("endMineshaftMinHeight", 30, 0, 255));

        					netherMineshaftMinHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 0.\r\n"
								+" Note: The mineshaft will spawn between min and max y height set in config.\r\n")
        						.translation("repurposedstructures.config.feature.mineshaft.nethermineshaftminheight")
        						.defineInRange("netherMineshaftMinHeight", 0, 0, 255));

    					builder.pop();

					builder.push("Max height");
					
        					birchMineshaftMaxHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 50.\r\n"
								+" Note: The mineshaft will spawn between min and max y height set in config.\r\n"
								+" Setting this to below min height config will make mineshaft spawn only at min height.")
        						.translation("repurposedstructures.config.feature.mineshaft.birchmineshaftmaxheight")
        						.defineInRange("birchMineshaftMaxHeight", 50, 0, 255));

        					
        					jungleMineshaftMaxHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 50.\r\n"
								+" Note: The mineshaft will spawn between min and max y height set in config.\r\n"
								+" Setting this to below min height config will make mineshaft spawn only at min height.")
        						.translation("repurposedstructures.config.feature.mineshaft.junglemineshaftmaxheight")
        						.defineInRange("jungleMineshaftMaxHeight", 0, 0, 255));

        					
        					desertMineshaftMaxHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 50.\r\n"
								+" Note: The mineshaft will spawn between min and max y height set in config.\r\n"
								+" Setting this to below min height config will make mineshaft spawn only at min height.")
        						.translation("repurposedstructures.config.feature.mineshaft.desertmineshaftmaxheight")
        						.defineInRange("desertMineshaftMaxHeight", 50, 0, 255));

        					
        					stoneMineshaftMaxHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 50.\r\n"
								+" Note: The mineshaft will spawn between min and max y height set in config.\r\n"
								+" Setting this to below min height config will make mineshaft spawn only at min height.")
        						.translation("repurposedstructures.config.feature.mineshaft.stonemineshaftmaxheight")
        						.defineInRange("stoneMineshaftMaxHeight", 50, 0, 255));

        					
        					savannaMineshaftMaxHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 50.\r\n"
								+" Note: The mineshaft will spawn between min and max y height set in config.\r\n"
								+" Setting this to below min height config will make mineshaft spawn only at min height.")
        						.translation("repurposedstructures.config.feature.mineshaft.savannamineshaftmaxheight")
        						.defineInRange("savannaMineshaftMaxHeight", 50, 0, 255));

        					
        					icyMineshaftMaxHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 50.\r\n"
								+" Note: The mineshaft will spawn between min and max y height set in config.\r\n"
								+" Setting this to below min height config will make mineshaft spawn only at min height.")
        						.translation("repurposedstructures.config.feature.mineshaft.icymineshaftmaxheight")
        						.defineInRange("icyMineshaftMaxHeight", 50, 0, 255));

        					
        					oceanMineshaftMaxHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 50.\r\n"
								+" Note: The mineshaft will spawn between min and max y height set in config.\r\n"
								+" Setting this to below min height config will make mineshaft spawn only at min height.")
        						.translation("repurposedstructures.config.feature.mineshaft.oceanmineshaftmaxheight")
        						.defineInRange("oceanMineshaftMaxHeight", 50, 0, 255));

        					
        					taigaMineshaftMaxHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 50.\r\n"
								+" Note: The mineshaft will spawn between min and max y height set in config.\r\n"
								+" Setting this to below min height config will make mineshaft spawn only at min height.")
        						.translation("repurposedstructures.config.feature.mineshaft.taigamineshaftmaxheight")
        						.defineInRange("taigaMineshaftMaxHeight", 50, 0, 255));

        					
        					swampAndDarkForestMineshaftMaxHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 50.\r\n"
								+" Note: The mineshaft will spawn between min and max y height set in config.\r\n"
								+" Setting this to below min height config will make mineshaft spawn only at min height.")
        						.translation("repurposedstructures.config.feature.mineshaft.swampanddarkforestmineshaftmaxheight")
        						.defineInRange("swampAndDarkForestMineshaftMaxHeight", 50, 0, 255));

        					endMineshaftMaxHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 50.\r\n"
								+" Note: The mineshaft will spawn between min and max y height set in config.\r\n"
								+" Setting this to below min height config will make mineshaft spawn only at min height.")
        						.translation("repurposedstructures.config.feature.mineshaft.endmineshaftmaxheight")
        						.defineInRange("endMineshaftMaxHeight", 40, 0, 255));

        					netherMineshaftMaxHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 22.\r\n"
								+" Note: The mineshaft will spawn between min and max y height set in config.\r\n"
								+" Setting this to below min height config will make mineshaft spawn only at min height.")
        						.translation("repurposedstructures.config.feature.mineshaft.nethermineshaftmaxheight")
        						.defineInRange("netherMineshaftMaxHeight", 22, 0, 255));

    					builder.pop();
				builder.pop();

				builder.push("Stronghold");

					useVanillaStronghold = subscriber.subscribe(builder
						.comment("\r\n Use vanilla Stronghold instead of using this mod's modded version.\r\n"
								+" Note: The other Stronghold configs here will have no effect on vanilla Strongholds. \r\n"
								+" WARNING: Nether Strongholds will be turned off too when this option is on.")
						.translation("repurposedstructures.config.structure.stronghold.usevanillastronghold")
						.define("useVanillaStronghold", false));

					allowStonebrickStronghold = subscriber.subscribe(builder
						.comment("\r\n Allow Stonebrick styled Stronghold to generate in non-Nether biomes.")
						.translation("repurposedstructures.config.structure.stronghold.allowstonebrickstronghold")
						.define("allowStonebrickStronghold", true));

					allowNetherStronghold = subscriber.subscribe(builder
						.comment("\r\n Allow Nether-styled Strongholds to spawn in Nether category biomes."
								+ "\r\n Note: Eyes of Ender will work and show the closest Nether Stronghold too.")
						.translation("repurposedstructures.config.structure.stronghold.allownetherstronghold")
						.define("allowNetherStronghold", true));
				
					strongholdSpawnrate = subscriber.subscribe(builder
							.comment("\r\n How rare are Strongholds." 
									+ "\n " 
									+ "1 for spawning in most chunks and 1001 for no spawn.")
							.translation("repurposedstructures.config.structure.stronghold.strongholdspawnrate")
							.defineInRange("strongholdSpawnrate", 85, 1, 1001));
		
					silverfishSpawnrateSH = subscriber.subscribe(builder
							.comment("\r\n How often Silverfish Blocks will generate in Strongholds as a percentage." 
									+ "\r\n Note: Mossy Stone Bricks block cannot be infected by Silverfish"
									+ "\n "
									+ "0 for no Silverfish Blocks and 100 for max spawnrate.")
							.translation("repurposedstructures.config.structure.stronghold.silverfishspawnratesh")
							.defineInRange("silverfishSpawnrateSH", 0.8D, 0, 100));
		
					allowExtraSpawnersSH = subscriber.subscribe(builder
							.comment("\r\n Make Mob Spawners generate in rooms other than the Portal Room in Strongholds.\r\n"
									+" Note: Spawners in Portal Room will always remain.")
							.translation("repurposedstructures.config.structure.stronghold.allowextraspawnerssh")
							.define("allowExtraSpawnersSH", true));
		
					strongholdSizeSH = subscriber.subscribe(builder
							.comment("\r\n How large the Stronghold is on average as a percentage." 
									+ "\r\n Note: The Stonghold is much larger by default. To get something "
									+ "\r\n closer to vanilla stronghold size, use the value of 60."
									+ "\n "
									+ "10 for supertiny Strongholds and 2000 for supermassive Strongholds.")
							.translation("repurposedstructures.config.structure.stronghold.strongholdsizesh")
							.defineInRange("strongholdSizeSH", 100D, 10, 2000));
					
					lootChestsSH = subscriber.subscribe(builder
							.comment("\r\n Controls whether loot chests spawn or not in the Stronghold.")
						.translation("repurposedstructures.config.structure.stronghold.lootchestssh")
						.define("lootChestsSH", true));
	
				builder.pop();
	
				builder.push("Jungle Fortress");
	
					jungleFortressSpawnrate = subscriber.subscribe(builder
							.comment("\r\n How rare are Jungle Fortresses." 
									+ "\n "
									+ "1 for spawning in most chunks and 1001 for no spawn.")
							.translation("repurposedstructures.config.structure.junglefortress.junglefortressspawnrate")
							.defineInRange("jungleFortressSpawnrate", 32, 1, 1001));

					silverfishSpawnrateJF = subscriber.subscribe(builder
							.comment("\r\n How often Silverfish Blocks will generate in Jungle Fortress as a percentage."
									+ "\r\n Note: Mossy Stone Bricks block cannot be infected by Silverfish"
									+ "\n "
									+ "0 for no Silverfish Blocks and 100 for max spawnrate.")
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
									+ "1 for spawning in most chunks and 1001 for no spawn.")
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
									+ "1 for spawning in most chunks and 1001 for no spawn.")
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
								+ "1 for spawning in most chunks and 1001 for no spawn.")
						.translation("repurposedstructures.config.structure.igloo.grassyigloospawnrate")
						.defineInRange("grassyIglooSpawnrate", 20, 1, 1001));
	
					addGrassyIglooToModdedBiomes = subscriber.subscribe(builder
							.comment("\r\n Add Grassy Igloos to modded biomes that are\r\n"
									+" most likely grassy fields or temperate forests.")
						.translation("repurposedstructures.config.structure.igloo.addgrassyiglootomoddedbiomes")
						.define("addGrassyIglooToModdedBiomes", false));

					stoneIglooSpawnrate = subscriber.subscribe(builder
						.comment("\r\n How rare are Stone Igloos in Giant Tree Taiga biomes." 
								+ "\n "
								+ "1 for spawning in most chunks and 1001 for no spawn.")
						.translation("repurposedstructures.config.structure.igloo.stoneigloospawnrate")
						.defineInRange("stoneIglooSpawnrate", 20, 1, 1001));
	
					addStoneIglooToModdedBiomes = subscriber.subscribe(builder
							.comment("\r\n Add Stone Igloos to modded biomes that are\r\n"
									+" most likely Giant Tree Taiga variants.")
						.translation("repurposedstructures.config.structure.igloo.addstoneiglootomoddedbiomes")
						.define("addStoneIglooToModdedBiomes", false));
				builder.pop();
			builder.pop();
		}
	}
}
