package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber
public class RSDungeonsConfig
{
	public static class RSDungeonsConfigValues
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

		public RSDungeonsConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
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
						.defineInRange("oceanDungeonSpawnrate", 6, 0, 1000));
            

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
		}
	}
}
