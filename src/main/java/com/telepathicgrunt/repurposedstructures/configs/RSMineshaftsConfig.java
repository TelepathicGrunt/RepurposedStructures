package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber
public class RSMineshaftsConfig
{

	public static class RSMineshaftsConfigValues
	{
		public ConfigValueListener<Boolean> lootChestsMS;
		public ConfigValueListener<Boolean> addMineshaftsToModdedBiomes;
		public ConfigValueListener<String> blacklistedMineshaftBiomes;
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

		public ConfigValueListener<Boolean> barrensIslandsEndMineshafts ;

		public RSMineshaftsConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{

			builder.push("Mineshaft");

				lootChestsMS = subscriber.subscribe(builder
						.comment("\r\n Controls whether loot chests spawn or not in modded Mineshafts.")
					.translation("repurposedstructures.config.mineshaft.lootchestsms")
					.define("lootChestsMS", true));

				addMineshaftsToModdedBiomes = subscriber.subscribe(builder
						.comment("\r\n Add the custom Mineshafts to modded biomes of the same categories/type.")
					.translation("repurposedstructures.config.mineshaft.addmineshaftstomoddedbiomes")
					.define("addMineshaftsToModdedBiomes", true));

				blacklistedMineshaftBiomes = subscriber.subscribe(builder
					.comment("\r\n Add the ID/resource location of the biome you don't want"
							+"\r\n RS's mineshafts to spawn in. Separate each ID with a comma ,"
							+"\r\n"
							+"\r\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
					.translation("repurposedstructures.config.mineshaft.blacklistedmineshaftbiomes")
					.define("blacklistedMineshaftBiomes", ""));

				builder.push("Spawnrate");
					
					birchMineshaftSpawnrate = subscriber.subscribe(builder
							.comment("\r\n Controls how often Mineshafts will spawn. 1000 is max spawnrate.\r\n " 
								+"\r\n Replace Mineshafts in Birch biomes with a Birch themed Mineshaft."
								+"\r\n Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
						.translation("repurposedstructures.config.mineshaft.birchmineshaftspawnrate")
						.defineInRange("birchMineshaftSpawnrate", 40, 0, 1000));

					jungleMineshaftSpawnrate = subscriber.subscribe(builder
							.comment("\r\n Controls how often Mineshafts will spawn. 1000 is max spawnrate.\r\n " 
								+"\r\n Replace Mineshafts in Jungle biomes with a Jungle themed Mineshaft."
								+"\r\n Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
						.translation("repurposedstructures.config.mineshaft.junglemineshaftspawnrate")
						.defineInRange("jungleMineshaftSpawnrate", 40, 0, 1000));

					desertMineshaftSpawnrate = subscriber.subscribe(builder
							.comment("\r\n Controls how often Mineshafts will spawn. 1000 is max spawnrate.\r\n " 
								+"\r\n Replace Mineshafts in Desert biomes with a Desert themed Mineshaft."
								+"\r\n Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
						.translation("repurposedstructures.config.mineshaft.desertmineshaftspawnrate")
						.defineInRange("desertMineshaftSpawnrate", 40, 0, 1000));

					stoneMineshaftSpawnrate = subscriber.subscribe(builder
							.comment("\r\n Controls how often Mineshafts will spawn. 1000 is max spawnrate.\r\n " 
								+"\r\n Replace Mineshafts in Mountain (Extreme Hills) biomes with a Stone themed Mineshaft."
								+"\r\n Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
						.translation("repurposedstructures.config.mineshaft.stonemineshaftspawnrate")
						.defineInRange("stoneMineshaftSpawnrate", 40, 0, 1000));

					savannaMineshaftSpawnrate = subscriber.subscribe(builder
							.comment("\r\n Controls how often Mineshafts will spawn. 1000 is max spawnrate.\r\n " 
								+"\r\n Replace Mineshafts in Savanna biomes with a Savanna themed Mineshaft."
								+"\r\n Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
						.translation("repurposedstructures.config.mineshaft.savannamineshaftspawnrate")
						.defineInRange("savannaMineshaftSpawnrate", 40, 0, 1000));

					icyMineshaftSpawnrate = subscriber.subscribe(builder
							.comment("\r\n Controls how often Mineshafts will spawn. 1000 is max spawnrate.\r\n " 
								+"\r\n Replace Mineshafts in Snowy/Icy biomes with an Ice themed Mineshaft."
								+"\r\n Note: Snowy Taiga Biomes will get Ice Mineshaft instead of Taiga theme."
								+"\r\n Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
						.translation("repurposedstructures.config.mineshaft.icymineshaftspawnrate")
						.defineInRange("icyMineshaftSpawnrate", 40, 0, 1000));

					oceanMineshaftSpawnrate = subscriber.subscribe(builder
							.comment("\r\n Controls how often Mineshafts will spawn. 1000 is max spawnrate.\r\n " 
								+"\r\n Replace Mineshafts in Ocean biomes with an Ocean themed Mineshaft."
								+"\r\n Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
						.translation("repurposedstructures.config.mineshaft.oceanmineshaftspawnrate")
						.defineInRange("oceanMineshaftSpawnrate", 40, 0, 1000));

					taigaMineshaftSpawnrate = subscriber.subscribe(builder
							.comment("\r\n Controls how often Mineshafts will spawn. 1000 is max spawnrate.\r\n " 
								+"\r\n Replace Mineshafts in Taiga biomes with a Taiga themed Mineshaft."
								+" Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
						.translation("repurposedstructures.config.mineshaft.taigamineshaftspawnrate")
						.defineInRange("taigaMineshaftSpawnrate", 40, 0, 1000));
					
					swampAndDarkForestMineshaftSpawnrate = subscriber.subscribe(builder
							.comment("\r\n Controls how often Mineshafts will spawn. 1000 is max spawnrate.\r\n " 
								+"\r\n Replace Mineshafts in Swamps and Dark Forests with a swampy/dark oak themed Mineshaft."
								+"\r\n Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
						.translation("repurposedstructures.config.mineshaft.swampanddarkforestmineshaftspawnrate"
							+"\r\n How often Mineshafts will spawn.\r\n " 
							+ "0 for no Mineshafts and 1000 for max spawnrate.")
						.defineInRange("swampAndDarkForestMineshaftSpawnrate", 40, 0, 1000));
					
					endMineshaftSpawnrate = subscriber.subscribe(builder
							.comment("\r\n Controls how often Mineshafts will spawn.\r\n " 
								+"\r\n Add End themed Mineshafts to End biomes outside the Enderdragon island."
								+ "0 for no Mineshafts and 1000 for max spawnrate.")
						.translation("repurposedstructures.config.mineshaft.endmineshaftspawnrate")
						.defineInRange("endMineshaftSpawnrate", 40, 0, 1000));
					
					netherMineshaftSpawnrate = subscriber.subscribe(builder
							.comment("\r\n Controls how often Mineshafts will spawn.\r\n " 
								+"\r\n Add Nether themed Mineshafts to Nether biomes."
								+ "0 for no Mineshafts and 1000 for max spawnrate.")
						.translation("repurposedstructures.config.mineshaft.nethermineshaftspawnrate")
						.defineInRange("netherMineshaftSpawnrate", 40, 0, 1000));

				builder.pop();

				builder.push("Min height");
				
					birchMineshaftMinHeight = subscriber.subscribe(builder
						.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 8.\r\n"
							+" Note: The mineshaft will spawn between min and max y height set in config.\r\n"
							+" Setting this to below min height config will make mineshaft spawn only at min height.")
						.translation("repurposedstructures.config.mineshaft.birchmineshaftminheight")
						.defineInRange("birchMineshaftMinHeight", 8, 5, 255));

					
					jungleMineshaftMinHeight = subscriber.subscribe(builder
						.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 8.\r\n"
							+" Note: The mineshaft will spawn between min and max y height set in config.\r\n")
						.translation("repurposedstructures.config.mineshaft.junglemineshaftminheight")
						.defineInRange("jungleMineshaftMinHeight", 8, 5, 255));

					
					desertMineshaftMinHeight = subscriber.subscribe(builder
						.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 8.\r\n"
							+" Note: The mineshaft will spawn between min and max y height set in config.\r\n")
						.translation("repurposedstructures.config.mineshaft.desertmineshaftminheight")
						.defineInRange("desertMineshaftMinHeight", 8, 5, 255));

					
					stoneMineshaftMinHeight = subscriber.subscribe(builder
						.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 8.\r\n"
							+" Note: The mineshaft will spawn between min and max y height set in config.\r\n")
						.translation("repurposedstructures.config.mineshaft.stonemineshaftminheight")
						.defineInRange("stoneMineshaftMinHeight", 8, 5, 255));

					
					savannaMineshaftMinHeight = subscriber.subscribe(builder
						.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 8.\r\n"
							+" Note: The mineshaft will spawn between min and max y height set in config.\r\n")
						.translation("repurposedstructures.config.mineshaft.savannamineshaftminheight")
						.defineInRange("savannaMineshaftMinHeight", 8, 5, 255));

					
					icyMineshaftMinHeight = subscriber.subscribe(builder
						.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 8.\r\n"
							+" Note: The mineshaft will spawn between min and max y height set in config.\r\n")
						.translation("repurposedstructures.config.mineshaft.icymineshaftminheight")
						.defineInRange("icyMineshaftMinHeight", 8, 5, 255));

					
					oceanMineshaftMinHeight = subscriber.subscribe(builder
						.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 8.\r\n"
							+" Note: The mineshaft will spawn between min and max y height set in config.\r\n")
						.translation("repurposedstructures.config.mineshaft.oceanmineshaftminheight")
						.defineInRange("oceanMineshaftMinHeight", 8, 5, 255));

					
					taigaMineshaftMinHeight = subscriber.subscribe(builder
						.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 8.\r\n"
							+" Note: The mineshaft will spawn between min and max y height set in config.\r\n")
						.translation("repurposedstructures.config.mineshaft.taigamineshaftminheight")
						.defineInRange("taigaMineshaftMinHeight", 8, 5, 255));

					
					swampAndDarkForestMineshaftMinHeight = subscriber.subscribe(builder
						.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 8.\r\n"
							+" Note: The mineshaft will spawn between min and max y height set in config.\r\n")
						.translation("repurposedstructures.config.mineshaft.swampanddarkforestmineshaftminheight")
						.defineInRange("swampAndDarkForestMineshaftMinHeight", 8, 5, 255));

					endMineshaftMinHeight = subscriber.subscribe(builder
						.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 30.\r\n"
							+" Note: The mineshaft will spawn between min and max y height set in config.\r\n")
						.translation("repurposedstructures.config.mineshaft.endmineshaftminheight")
						.defineInRange("endMineshaftMinHeight", 30, 5, 255));

					netherMineshaftMinHeight = subscriber.subscribe(builder
						.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 8.\r\n"
							+" Note: The mineshaft will spawn between min and max y height set in config.\r\n")
						.translation("repurposedstructures.config.mineshaft.nethermineshaftminheight")
						.defineInRange("netherMineshaftMinHeight", 8, 5, 255));

				builder.pop();

				builder.push("Max height");
				
					birchMineshaftMaxHeight = subscriber.subscribe(builder
						.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 50.\r\n"
							+" Note: The mineshaft will spawn between min and max y height set in config.\r\n"
							+" Setting this to below min height config will make mineshaft spawn only at min height.")
						.translation("repurposedstructures.config.mineshaft.birchmineshaftmaxheight")
						.defineInRange("birchMineshaftMaxHeight", 50, 5, 255));

					
					jungleMineshaftMaxHeight = subscriber.subscribe(builder
						.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 50.\r\n"
							+" Note: The mineshaft will spawn between min and max y height set in config.\r\n"
							+" Setting this to below min height config will make mineshaft spawn only at min height.")
						.translation("repurposedstructures.config.mineshaft.junglemineshaftmaxheight")
						.defineInRange("jungleMineshaftMaxHeight", 50, 5, 255));

					
					desertMineshaftMaxHeight = subscriber.subscribe(builder
						.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 50.\r\n"
							+" Note: The mineshaft will spawn between min and max y height set in config.\r\n"
							+" Setting this to below min height config will make mineshaft spawn only at min height.")
						.translation("repurposedstructures.config.mineshaft.desertmineshaftmaxheight")
						.defineInRange("desertMineshaftMaxHeight", 50, 5, 255));

					
					stoneMineshaftMaxHeight = subscriber.subscribe(builder
						.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 50.\r\n"
							+" Note: The mineshaft will spawn between min and max y height set in config.\r\n"
							+" Setting this to below min height config will make mineshaft spawn only at min height.")
						.translation("repurposedstructures.config.mineshaft.stonemineshaftmaxheight")
						.defineInRange("stoneMineshaftMaxHeight", 50, 5, 255));

					
					savannaMineshaftMaxHeight = subscriber.subscribe(builder
						.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 50.\r\n"
							+" Note: The mineshaft will spawn between min and max y height set in config.\r\n"
							+" Setting this to below min height config will make mineshaft spawn only at min height.")
						.translation("repurposedstructures.config.mineshaft.savannamineshaftmaxheight")
						.defineInRange("savannaMineshaftMaxHeight", 50, 5, 255));

					
					icyMineshaftMaxHeight = subscriber.subscribe(builder
						.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 50.\r\n"
							+" Note: The mineshaft will spawn between min and max y height set in config.\r\n"
							+" Setting this to below min height config will make mineshaft spawn only at min height.")
						.translation("repurposedstructures.config.mineshaft.icymineshaftmaxheight")
						.defineInRange("icyMineshaftMaxHeight", 50, 5, 255));

					
					oceanMineshaftMaxHeight = subscriber.subscribe(builder
						.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 25.\r\n"
							+" Note: The mineshaft will spawn between min and max y height set in config.\r\n"
							+" Setting this to below min height config will make mineshaft spawn only at min height.")
						.translation("repurposedstructures.config.mineshaft.oceanmineshaftmaxheight")
						.defineInRange("oceanMineshaftMaxHeight", 25, 5, 255));

					
					taigaMineshaftMaxHeight = subscriber.subscribe(builder
						.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 50.\r\n"
							+" Note: The mineshaft will spawn between min and max y height set in config.\r\n"
							+" Setting this to below min height config will make mineshaft spawn only at min height.")
						.translation("repurposedstructures.config.mineshaft.taigamineshaftmaxheight")
						.defineInRange("taigaMineshaftMaxHeight", 50, 5, 255));

					
					swampAndDarkForestMineshaftMaxHeight = subscriber.subscribe(builder
						.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 50.\r\n"
							+" Note: The mineshaft will spawn between min and max y height set in config.\r\n"
							+" Setting this to below min height config will make mineshaft spawn only at min height.")
						.translation("repurposedstructures.config.mineshaft.swampanddarkforestmineshaftmaxheight")
						.defineInRange("swampAndDarkForestMineshaftMaxHeight", 50, 5, 255));

					endMineshaftMaxHeight = subscriber.subscribe(builder
						.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 50.\r\n"
							+" Note: The mineshaft will spawn between min and max y height set in config.\r\n"
							+" Setting this to below min height config will make mineshaft spawn only at min height.")
						.translation("repurposedstructures.config.mineshaft.endmineshaftmaxheight")
						.defineInRange("endMineshaftMaxHeight", 40, 5, 255));

					netherMineshaftMaxHeight = subscriber.subscribe(builder
						.comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 22.\r\n"
							+" Note: The mineshaft will spawn between min and max y height set in config.\r\n"
							+" Setting this to below min height config will make mineshaft spawn only at min height.")
						.translation("repurposedstructures.config.mineshaft.nethermineshaftmaxheight")
						.defineInRange("netherMineshaftMaxHeight", 22, 5, 255));

				builder.pop();

				builder.push("Misc");
					barrensIslandsEndMineshafts = subscriber.subscribe(builder
							.comment("\r\n Add End Mineshafts to End Barrens and End Islands biome.")
							.translation("repurposedstructures.config.mineshaft.barrensislandsendmineshafts")
							.define("barrensIslandsEndMineshafts", false));
				builder.pop();
			builder.pop();
		}
	}
}
