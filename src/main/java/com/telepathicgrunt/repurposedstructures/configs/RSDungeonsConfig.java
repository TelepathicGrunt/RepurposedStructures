package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;

public class RSDungeonsConfig
{
	public static class RSDungeonsConfigValues
	{

		public ConfigValueListener<Boolean> addDungeonsToModdedBiomes;
		public ConfigValueListener<String> blacklistedDungeonBiomes;
		public ConfigValueListener<Integer> badlandsDungeonAttemptsPerChunk;
		public ConfigValueListener<Integer> darkForestDungeonAttemptsPerChunk;
		public ConfigValueListener<Integer> desertDungeonAttemptsPerChunk;
		public ConfigValueListener<Integer> jungleDungeonAttemptsPerChunk;
		public ConfigValueListener<Integer> mushroomDungeonAttemptsPerChunk;
		public ConfigValueListener<Integer> snowDungeonAttemptsPerChunk;
		public ConfigValueListener<Integer> swampDungeonAttemptsPerChunk;
		public ConfigValueListener<Integer> endDungeonAttemptsPerChunk;
		public ConfigValueListener<Integer> netherDungeonAttemptsPerChunk;
		public ConfigValueListener<Integer> oceanDungeonAttemptsPerChunk;

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

		public RSDungeonsConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
			builder.push("Dungeons");
				
				addDungeonsToModdedBiomes = subscriber.subscribe(builder
						.comment("\r\n Add the custom dungeons to modded biomes of the same categories/type.")
					.translation("repurposedstructures.config.dungeons.adddungeonstomoddedbiomes")
					.define("addDungeonsToModdedBiomes", true));

				blacklistedDungeonBiomes = subscriber.subscribe(builder
					.comment("\r\n Add the ID/resource location of the biome you don't want"
							+"\r\n RS's dungeons to spawn in. Separate each ID with a comma ,"
							+"\r\n"
							+"\r\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
					.translation("repurposedstructures.config.dungeons.blacklisteddungeonbiomes")
					.define("blacklistedDungeonBiomes", ""));

				builder.push("AttemptsPerChunk");
        					
					badlandsDungeonAttemptsPerChunk = subscriber.subscribe(builder
            						.comment("\r\n Replace vanilla dungeon in Badlands biomes with Badlands themed dungeon.\r\n"
        							+" How often dungeons will attempt to spawn per chunk.\r\n " 
        							+" 0 for no Dungeons at all and 1000 for max Dungeon spawnrate.\r\n"
        							+" Note: Vanilla Dungeons will spawn again when this is set to 0.")
        					.translation("repurposedstructures.config.dungeons.badlandsdungeonattemptsperchunk")
        					.defineInRange("badlandsDungeonAttemptsPerChunk", 8, 0, 1000));
				
					darkForestDungeonAttemptsPerChunk = subscriber.subscribe(builder
							.comment("\r\n Replace vanilla dungeon in Dark Forest biomes with Dark Forest themed dungeon.\r\n"
								+" How often dungeons will attempt to spawn per chunk.\r\n " 
								+" 0 for no Dungeons at all and 1000 for max Dungeon spawnrate.\r\n"
								+" Note: Vanilla Dungeons will spawn again when this is set to 0.")
						.translation("repurposedstructures.config.dungeons.darkforestdungeonattemptsperchunk")
						.defineInRange("darkForestDungeonAttemptsPerChunk", 8, 0, 1000));
				
					desertDungeonAttemptsPerChunk = subscriber.subscribe(builder
							.comment("\r\n Replace vanilla dungeon in Desert biomes with Desert themed dungeon.\r\n"
								+" How often dungeons will attempt to spawn per chunk.\r\n " 
								+" 0 for no Dungeons at all and 1000 for max Dungeon spawnrate.\r\n"
								+" Note: Vanilla Dungeons will spawn again when this is set to 0.")
						.translation("repurposedstructures.config.dungeons.desertdungeonattemptsperchunk")
						.defineInRange("desertDungeonAttemptsPerChunk", 8, 0, 1000));
				
					jungleDungeonAttemptsPerChunk = subscriber.subscribe(builder
							.comment("\r\n Replace vanilla dungeon in Jungle biomes with Jungle themed dungeon.\r\n"
								+" How often dungeons will attempt to spawn per chunk.\r\n " 
								+" 0 for no Dungeons at all and 1000 for max Dungeon spawnrate.\r\n"
								+" Note: Vanilla Dungeons will spawn again when this is set to 0.")
						.translation("repurposedstructures.config.dungeons.jungledungeonattemptsperchunk")
						.defineInRange("jungleDungeonAttemptsPerChunk", 8, 0, 1000));
				
					mushroomDungeonAttemptsPerChunk = subscriber.subscribe(builder
							.comment("\r\n Replace vanilla dungeon in Mushroom biomes with Mushroom themed dungeon.\r\n"
								+" How often dungeons will attempt to spawn per chunk.\r\n " 
								+" 0 for no Dungeons at all and 1000 for max Dungeon spawnrate.\r\n"
								+" Note: Vanilla Dungeons will spawn again when this is set to 0.")
						.translation("repurposedstructures.config.dungeons.mushroomdungeonattemptsperchunk")
						.defineInRange("mushroomDungeonAttemptsPerChunk", 8, 0, 1000));
				
					snowDungeonAttemptsPerChunk = subscriber.subscribe(builder
							.comment("\r\n Replace vanilla dungeon in icy/snow biomes with icy/snow themed dungeon.\r\n"
								+" How often dungeons will attempt to spawn per chunk.\r\n " 
								+" 0 for no Dungeons at all and 1000 for max Dungeon spawnrate.\r\n"
								+" Note: Vanilla Dungeons will spawn again when this is set to 0.")
						.translation("repurposedstructures.config.dungeons.snowdungeonattemptsperchunk")
						.defineInRange("snowDungeonAttemptsPerChunk", 8, 0, 1000));
				
					swampDungeonAttemptsPerChunk = subscriber.subscribe(builder
							.comment("\r\n Replace vanilla dungeon in Swamp biomes with Swamp themed dungeon.\r\n"
								+" How often dungeons will attempt to spawn per chunk.\r\n " 
								+" 0 for no Dungeons at all and 1000 for max Dungeon spawnrate.\r\n"
								+" Note: Vanilla Dungeons will spawn again when this is set to 0.")
						.translation("repurposedstructures.config.dungeons.swampdungeonattemptsperchunk")
						.defineInRange("swampDungeonAttemptsPerChunk", 8, 0, 1000));
				
					endDungeonAttemptsPerChunk = subscriber.subscribe(builder
							.comment("\r\n Add End themed dungeon to End biomes outside the Enderdragon island.\r\n"
								+" How often dungeons will attempt to spawn per chunk.\r\n " 
								+" 0 for no Dungeons at all and 1000 for max Dungeon spawnrate.")
						.translation("repurposedstructures.config.dungeons.enddungeonattemptsperchunk")
						.defineInRange("endDungeonAttemptsPerChunk", 8, 0, 1000));

					netherDungeonAttemptsPerChunk = subscriber.subscribe(builder
							.comment("\r\n Add Nether themed dungeon to Nether biomes.\r\n"
								+" How often dungeons will attempt to spawn per chunk.\r\n " 
								+" 0 for no Dungeons at all and 1000 for max Dungeon spawnrate.")
						.translation("repurposedstructures.config.dungeons.netherdungeonattemptsperchunk")
						.defineInRange("netherDungeonAttemptsPerChunk", 8, 0, 1000));

					oceanDungeonAttemptsPerChunk = subscriber.subscribe(builder
							.comment("\r\n Add ocean themed dungeon to ocean biomes. These will spawn on"
								+"\r\n the ocean's floor and inside water filled caves and ravines.\r\n"
								+"\r\n"
								+" How often dungeons will attempt to spawn per chunk.\r\n " 
								+" 0 for no dungeons at all and 1000 for max Dungeon spawnrate."
								+"\r\n"
								+"\r\n Note: Vanilla Dungeons will still generate if the biome has "
								+"\r\n them which is unlike the other modded dungeons from this mod"
								+"\r\n as those would normally replace the Vanilla Dungeons.")
						.translation("repurposedstructures.config.dungeons.oceandungeonattemptsperchunk")
						.defineInRange("oceanDungeonAttemptsPerChunk", 5, 0, 1000));
            

				builder.pop();
					
				builder.push("Min Height");

					badlandsDungeonMinHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this dungeon can spawn at. Default is 2.\r\n"
								+" Note: The dungeon will spawn between min and max y height set in config.")
						.translation("repurposedstructures.config.dungeons.badlandsdungeonminheight")
						.defineInRange("badlandsDungeonMinHeight", 2, 2, 255));

					darkForestDungeonMinHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this dungeon can spawn at. Default is 2.\r\n"
								+" Note: The dungeon will spawn between min and max y height set in config.")
						.translation("repurposedstructures.config.dungeons.darkforestdungeonminheight")
						.defineInRange("darkForestDungeonMinHeight", 2, 2, 255));

					desertDungeonMinHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this dungeon can spawn at. Default is 2.\r\n"
								+" Note: The dungeon will spawn between min and max y height set in config.")
						.translation("repurposedstructures.config.dungeons.desertdungeonminheight")
						.defineInRange("desertDungeonMinHeight", 2, 2, 255));

					jungleDungeonMinHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this dungeon can spawn at. Default is 2.\r\n"
								+" Note: The dungeon will spawn between min and max y height set in config.")
						.translation("repurposedstructures.config.dungeons.jungledungeonminheight")
						.defineInRange("jungleDungeonMinHeight", 2, 2, 255));

					mushroomDungeonMinHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this dungeon can spawn at. Default is 2.\r\n"
								+" Note: The dungeon will spawn between min and max y height set in config.")
						.translation("repurposedstructures.config.dungeons.mushroomdungeonminheight")
						.defineInRange("mushroomDungeonMinHeight", 2, 2, 255));

					snowDungeonMinHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this dungeon can spawn at. Default is 2.\r\n"
								+" Note: The dungeon will spawn between min and max y height set in config.")
						.translation("repurposedstructures.config.dungeons.snowdungeonminheight")
						.defineInRange("snowDungeonMinHeight", 2, 2, 255));

					swampDungeonMinHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this dungeon can spawn at. Default is 2.\r\n"
								+" Note: The dungeon will spawn between min and max y height set in config.")
						.translation("repurposedstructures.config.dungeons.swampdungeonminheight")
						.defineInRange("swampDungeonMinHeight", 2, 2, 255));

					endDungeonMinHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this dungeon can spawn at. Default is 2.\r\n"
								+" Note: The dungeon will spawn between min and max y height set in config.")
						.translation("repurposedstructures.config.dungeons.enddungeonminheight")
						.defineInRange("endDungeonMinHeight", 2, 2, 255));

					netherDungeonMinHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this dungeon can spawn at. Default is 2.\r\n"
								+" Note: The dungeon will spawn between min and max y height set in config.")
						.translation("repurposedstructures.config.dungeons.netherdungeonminheight")
						.defineInRange("netherDungeonMinHeight", 2, 2, 255));

					oceanDungeonMinHeight = subscriber.subscribe(builder
							.comment("\r\n Minimum Y height that this dungeon can spawn at. Default is 3.\r\n"
								+" Note: The dungeon will spawn between min and max y height set in config.")
						.translation("repurposedstructures.config.dungeons.oceandungeonminheight")
						.defineInRange("oceanDungeonMinHeight", 3, 3, 255));

				builder.pop();
					

				builder.push("Max Height");

					badlandsDungeonMaxHeight = subscriber.subscribe(builder
							.comment("\r\n Maximum Y height that this dungeon can spawn at. Default is 255.\r\n"
								+" Note: The dungeon will spawn between min and max y height set in config.\r\n"
								+" Setting this to below min height config will make dungeon spawn only at min height.")
						.translation("repurposedstructures.config.dungeons.badlandsdungeonmaxheight")
						.defineInRange("badlandsDungeonMaxHeight", 255, 2, 255));

					darkForestDungeonMaxHeight = subscriber.subscribe(builder
							.comment("\r\n Maximum Y height that this dungeon can spawn at. Default is 255.\r\n"
								+" Note: The dungeon will spawn between min and max y height set in config.\r\n"
								+" Setting this to below min height config will make dungeon spawn only at min height.")
						.translation("repurposedstructures.config.dungeons.darkforestdungeonmaxheight")
						.defineInRange("darkForestDungeonMaxHeight", 255, 2, 255));

					desertDungeonMaxHeight = subscriber.subscribe(builder
							.comment("\r\n Maximum Y height that this dungeon can spawn at. Default is 255.\r\n"
								+" Note: The dungeon will spawn between min and max y height set in config.\r\n"
								+" Setting this to below min height config will make dungeon spawn only at min height.")
						.translation("repurposedstructures.config.dungeons.desertdungeonmaxheight")
						.defineInRange("desertDungeonMaxHeight", 255, 2, 255));

					jungleDungeonMaxHeight = subscriber.subscribe(builder
							.comment("\r\n Maximum Y height that this dungeon can spawn at. Default is 255.\r\n"
								+" Note: The dungeon will spawn between min and max y height set in config.\r\n"
								+" Setting this to below min height config will make dungeon spawn only at min height.")
						.translation("repurposedstructures.config.dungeons.jungledungeonmaxheight")
						.defineInRange("jungleDungeonMaxHeight", 255, 2, 255));

					mushroomDungeonMaxHeight = subscriber.subscribe(builder
							.comment("\r\n Maximum Y height that this dungeon can spawn at. Default is 255.\r\n"
								+" Note: The dungeon will spawn between min and max y height set in config.\r\n"
								+" Setting this to below min height config will make dungeon spawn only at min height.")
						.translation("repurposedstructures.config.dungeons.mushroomdungeonmaxheight")
						.defineInRange("mushroomDungeonMaxHeight", 255, 2, 255));

					snowDungeonMaxHeight = subscriber.subscribe(builder
							.comment("\r\n Maximum Y height that this dungeon can spawn at. Default is 255.\r\n"
								+" Note: The dungeon will spawn between min and max y height set in config.\r\n"
								+" Setting this to below min height config will make dungeon spawn only at min height.")
						.translation("repurposedstructures.config.dungeons.snowdungeonmaxheight")
						.defineInRange("snowDungeonMaxHeight", 255, 2, 255));

					swampDungeonMaxHeight = subscriber.subscribe(builder
							.comment("\r\n Maximum Y height that this dungeon can spawn at. Default is 255.\r\n"
								+" Note: The dungeon will spawn between min and max y height set in config.\r\n"
								+" Setting this to below min height config will make dungeon spawn only at min height.")
						.translation("repurposedstructures.config.dungeons.swampdungeonmaxheight")
						.defineInRange("swampDungeonMaxHeight", 255, 2, 255));

					endDungeonMaxHeight = subscriber.subscribe(builder
							.comment("\r\n Maximum Y height that this dungeon can spawn at. Default is 255.\r\n"
								+" Note: The dungeon will spawn between min and max y height set in config.\r\n"
								+" Setting this to below min height config will make dungeon spawn only at min height.")
						.translation("repurposedstructures.config.dungeons.enddungeonmaxheight")
						.defineInRange("endDungeonMaxHeight", 255, 2, 255));

					netherDungeonMaxHeight = subscriber.subscribe(builder
							.comment("\r\n Maximum Y height that this dungeon can spawn at. Default is 255.\r\n"
								+" Note: The dungeon will spawn between min and max y height set in config.\r\n"
								+" Setting this to below min height config will make dungeon spawn only at min height.")
						.translation("repurposedstructures.config.dungeons.netherdungeonmaxheight")
						.defineInRange("netherDungeonMaxHeight", 255, 2, 255));

					oceanDungeonMaxHeight = subscriber.subscribe(builder
							.comment("\r\n Maximum Y height that this dungeon can spawn at. Default is 255.\r\n"
								+" Note: The dungeon will spawn between min and max y height set in config.\r\n"
								+" Setting this to below min height config will make dungeon spawn only at min height.")
						.translation("repurposedstructures.config.dungeons.oceandungeonmaxheight")
						.defineInRange("oceanDungeonMaxHeight", 255, 3, 255));

				builder.pop();
					
			builder.pop();
		}
	}
}
