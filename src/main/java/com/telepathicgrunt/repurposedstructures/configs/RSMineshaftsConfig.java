package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;

public class RSMineshaftsConfig
{

	public static class RSMineshaftsConfigValues
	{
		public ConfigValueListener<Boolean> lootChestsMS;
		public ConfigValueListener<Boolean> addMineshaftsToModdedBiomes;
		public ConfigValueListener<String> blacklistedMineshaftBiomes;
		public ConfigValueListener<Double> birchMineshaftSpawnrate;
		public ConfigValueListener<Double> jungleMineshaftSpawnrate;
		public ConfigValueListener<Double> desertMineshaftSpawnrate;
		public ConfigValueListener<Double> stoneMineshaftSpawnrate;
		public ConfigValueListener<Double> savannaMineshaftSpawnrate;
		public ConfigValueListener<Double> icyMineshaftSpawnrate;
		public ConfigValueListener<Double> oceanMineshaftSpawnrate;
		public ConfigValueListener<Double> taigaMineshaftSpawnrate;
		public ConfigValueListener<Double> swampAndDarkForestMineshaftSpawnrate;
		public ConfigValueListener<Double> endMineshaftSpawnrate;
		public ConfigValueListener<Double> netherMineshaftSpawnrate;
		public ConfigValueListener<Double> crimsonMineshaftSpawnrate;
		public ConfigValueListener<Double> warpedMineshaftSpawnrate;

		public ConfigValueListener<Integer> birchMineshaftMinHeight;
		public ConfigValueListener<Integer> jungleMineshaftMinHeight;
		public ConfigValueListener<Integer> desertMineshaftMinHeight;
		public ConfigValueListener<Integer> stoneMineshaftMinHeight;
		public ConfigValueListener<Integer> savannaMineshaftMinHeight;
		public ConfigValueListener<Integer> icyMineshaftMinHeight;
		public ConfigValueListener<Integer> oceanMineshaftMinHeight;
		public ConfigValueListener<Integer> taigaMineshaftMinHeight;
		public ConfigValueListener<Integer> swampAndDarkForestMineshaftMinHeight;
		public ConfigValueListener<Integer> endMineshaftMinHeight;
		public ConfigValueListener<Integer> netherMineshaftMinHeight;
		public ConfigValueListener<Integer> crimsonMineshaftMinHeight;
		public ConfigValueListener<Integer> warpedMineshaftMinHeight;
		
		public ConfigValueListener<Integer> birchMineshaftMaxHeight;
		public ConfigValueListener<Integer> jungleMineshaftMaxHeight;
		public ConfigValueListener<Integer> desertMineshaftMaxHeight;
		public ConfigValueListener<Integer> stoneMineshaftMaxHeight;
		public ConfigValueListener<Integer> savannaMineshaftMaxHeight;
		public ConfigValueListener<Integer> icyMineshaftMaxHeight;
		public ConfigValueListener<Integer> oceanMineshaftMaxHeight;
		public ConfigValueListener<Integer> taigaMineshaftMaxHeight;
		public ConfigValueListener<Integer> swampAndDarkForestMineshaftMaxHeight;
		public ConfigValueListener<Integer> endMineshaftMaxHeight;
		public ConfigValueListener<Integer> netherMineshaftMaxHeight;
		public ConfigValueListener<Integer> crimsonMineshaftMaxHeight;
		public ConfigValueListener<Integer> warpedMineshaftMaxHeight;

		public ConfigValueListener<Boolean> barrensIslandsEndMineshafts;

		public RSMineshaftsConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{

			builder.push("Mineshaft");

				lootChestsMS = subscriber.subscribe(builder
						.comment("\n Controls whether loot chests spawn or not in modded Mineshafts.")
					.translation("repurposedstructures.config.mineshaft.lootchestsms")
					.define("lootChestsMS", true));

				addMineshaftsToModdedBiomes = subscriber.subscribe(builder
						.comment("\n Add the custom Mineshafts to modded biomes of the same categories/type.")
					.translation("repurposedstructures.config.mineshaft.addmineshaftstomoddedbiomes")
					.define("addMineshaftsToModdedBiomes", true));

				blacklistedMineshaftBiomes = subscriber.subscribe(builder
					.comment("\n Add the ID/resource location of the biome you don't want",
							" RS's mineshafts to spawn in. Separate each ID with a comma ,",
							"   Example: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
					.translation("repurposedstructures.config.mineshaft.blacklistedmineshaftbiomes")
					.define("blacklistedMineshaftBiomes", "betterendforge:sulphur_springs"));

				builder.push("Spawnrate");
					
					birchMineshaftSpawnrate = subscriber.subscribe(builder
							.comment("\n Controls how often Mineshafts will spawn. 0 is no Mineshafts while 1000 is max spawnrate.",
								" Replace Mineshafts in Birch biomes with a Birch themed Mineshaft.",
								" Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
						.translation("repurposedstructures.config.mineshaft.birchmineshaftspawnrate")
						.defineInRange("birchMineshaftSpawnrate", 40D, 0, 1000));

					jungleMineshaftSpawnrate = subscriber.subscribe(builder
							.comment("\n Controls how often Mineshafts will spawn. 0 is no Mineshafts while 1000 is max spawnrate.",
								" Replace Mineshafts in Jungle biomes with a Jungle themed Mineshaft.",
								" Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
						.translation("repurposedstructures.config.mineshaft.junglemineshaftspawnrate")
						.defineInRange("jungleMineshaftSpawnrate", 40D, 0, 1000));

					desertMineshaftSpawnrate = subscriber.subscribe(builder
							.comment("\n Controls how often Mineshafts will spawn. 0 is no Mineshafts while 1000 is max spawnrate.",
								" Replace Mineshafts in Desert biomes with a Desert themed Mineshaft.",
								" Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
						.translation("repurposedstructures.config.mineshaft.desertmineshaftspawnrate")
						.defineInRange("desertMineshaftSpawnrate", 40D, 0, 1000));

					stoneMineshaftSpawnrate = subscriber.subscribe(builder
							.comment("\n Controls how often Mineshafts will spawn. 0 is no Mineshafts while 1000 is max spawnrate.",
								" Replace Mineshafts in Mountain (Extreme Hills) biomes with a Stone themed Mineshaft.",
								" Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
						.translation("repurposedstructures.config.mineshaft.stonemineshaftspawnrate")
						.defineInRange("stoneMineshaftSpawnrate", 40D, 0, 1000));

					savannaMineshaftSpawnrate = subscriber.subscribe(builder
							.comment("\n Controls how often Mineshafts will spawn. 0 is no Mineshafts while 1000 is max spawnrate.",
								" Replace Mineshafts in Savanna biomes with a Savanna themed Mineshaft.",
								" Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
						.translation("repurposedstructures.config.mineshaft.savannamineshaftspawnrate")
						.defineInRange("savannaMineshaftSpawnrate", 40D, 0, 1000));

					icyMineshaftSpawnrate = subscriber.subscribe(builder
							.comment("\n Controls how often Mineshafts will spawn. 0 is no Mineshafts while 1000 is max spawnrate.",
								" Replace Mineshafts in Snowy/Icy biomes with an Ice themed Mineshaft.",
								" Note: Snowy Taiga Biomes will get Ice Mineshaft instead of Taiga theme.",
								" Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
						.translation("repurposedstructures.config.mineshaft.icymineshaftspawnrate")
						.defineInRange("icyMineshaftSpawnrate", 40D, 0, 1000));

					oceanMineshaftSpawnrate = subscriber.subscribe(builder
							.comment("\n Controls how often Mineshafts will spawn. 0 is no Mineshafts while 1000 is max spawnrate.",
								" Replace Mineshafts in Ocean biomes with an Ocean themed Mineshaft.",
								" Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
						.translation("repurposedstructures.config.mineshaft.oceanmineshaftspawnrate")
						.defineInRange("oceanMineshaftSpawnrate", 40D, 0, 1000));

					taigaMineshaftSpawnrate = subscriber.subscribe(builder
							.comment("\n Controls how often Mineshafts will spawn. 0 is no Mineshafts while 1000 is max spawnrate.",
								" Replace Mineshafts in Taiga biomes with a Taiga themed Mineshaft.",
								" Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
						.translation("repurposedstructures.config.mineshaft.taigamineshaftspawnrate")
						.defineInRange("taigaMineshaftSpawnrate", 40D, 0, 1000));
					
					swampAndDarkForestMineshaftSpawnrate = subscriber.subscribe(builder
						.comment("\n Controls how often Mineshafts will spawn. 0 is no Mineshafts while 1000 is max spawnrate.",
							" Replace Mineshafts in Swamps and Dark Forests with a swampy/dark oak themed Mineshaft.",
							" Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
						.translation("repurposedstructures.config.mineshaft.swampanddarkforestmineshaftspawnrate")
						.defineInRange("swampAndDarkForestMineshaftSpawnrate", 40D, 0, 1000));
					
					endMineshaftSpawnrate = subscriber.subscribe(builder
							.comment("\n Controls how often Mineshafts will spawn. 0 for no Mineshafts and 1000 for max spawnrate.",
								" Adds End themed Mineshafts to End biomes outside the Enderdragon island.")
						.translation("repurposedstructures.config.mineshaft.endmineshaftspawnrate")
						.defineInRange("endMineshaftSpawnrate", 40D, 0, 1000));

					netherMineshaftSpawnrate = subscriber.subscribe(builder
						.comment("\n Controls how often Mineshafts will spawn. 0 for no Mineshafts and 1000 for max spawnrate.",
								" Adds Nether themed Mineshafts to non-crimson and non-warped Nether biomes.")
						.translation("repurposedstructures.config.mineshaft.nethermineshaftspawnrate")
						.defineInRange("netherMineshaftSpawnrate", 40D, 0, 1000));

					crimsonMineshaftSpawnrate = subscriber.subscribe(builder
						.comment("\n Controls how often Mineshafts will spawn. 0 for no Mineshafts and 1000 for max spawnrate.",
								" Adds Crimson themed Mineshafts to Crimson Nether biomes.")
						.translation("repurposedstructures.config.mineshaft.crimsonmineshaftspawnrate")
						.defineInRange("crimsonMineshaftSpawnrate", 40D, 0, 1000));

					warpedMineshaftSpawnrate = subscriber.subscribe(builder
						.comment("\n Controls how often Mineshafts will spawn. 0 for no Mineshafts and 1000 for max spawnrate.",
								" Adds Warped themed Mineshafts to Warped Nether biomes.")
						.translation("repurposedstructures.config.mineshaft.warpedmineshaftspawnrate")
						.defineInRange("warpedMineshaftSpawnrate", 40D, 0, 1000));

				builder.pop();

				builder.push("Min height");
				
					birchMineshaftMinHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that this mineshaft can spawn at. Default is 8.",
							" Note: The mineshaft will spawn between min and max y height set in config.",
							" Setting this to below min height config will make mineshaft spawn only at min height.")
						.translation("repurposedstructures.config.mineshaft.birchmineshaftminheight")
						.defineInRange("birchMineshaftMinHeight", 8, 5, 255));

					
					jungleMineshaftMinHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that this mineshaft can spawn at. Default is 8.",
							" Note: The mineshaft will spawn between min and max y height set in config.")
						.translation("repurposedstructures.config.mineshaft.junglemineshaftminheight")
						.defineInRange("jungleMineshaftMinHeight", 8, 5, 255));

					
					desertMineshaftMinHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that this mineshaft can spawn at. Default is 8.",
							" Note: The mineshaft will spawn between min and max y height set in config.")
						.translation("repurposedstructures.config.mineshaft.desertmineshaftminheight")
						.defineInRange("desertMineshaftMinHeight", 8, 5, 255));

					
					stoneMineshaftMinHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that this mineshaft can spawn at. Default is 8.",
							" Note: The mineshaft will spawn between min and max y height set in config.")
						.translation("repurposedstructures.config.mineshaft.stonemineshaftminheight")
						.defineInRange("stoneMineshaftMinHeight", 8, 5, 255));

					
					savannaMineshaftMinHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that this mineshaft can spawn at. Default is 8.",
							" Note: The mineshaft will spawn between min and max y height set in config.")
						.translation("repurposedstructures.config.mineshaft.savannamineshaftminheight")
						.defineInRange("savannaMineshaftMinHeight", 8, 5, 255));

					
					icyMineshaftMinHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that this mineshaft can spawn at. Default is 8.",
							" Note: The mineshaft will spawn between min and max y height set in config.")
						.translation("repurposedstructures.config.mineshaft.icymineshaftminheight")
						.defineInRange("icyMineshaftMinHeight", 8, 5, 255));

					
					oceanMineshaftMinHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that this mineshaft can spawn at. Default is 8.",
							" Note: The mineshaft will spawn between min and max y height set in config.")
						.translation("repurposedstructures.config.mineshaft.oceanmineshaftminheight")
						.defineInRange("oceanMineshaftMinHeight", 8, 5, 255));

					
					taigaMineshaftMinHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that this mineshaft can spawn at. Default is 8.",
							" Note: The mineshaft will spawn between min and max y height set in config.")
						.translation("repurposedstructures.config.mineshaft.taigamineshaftminheight")
						.defineInRange("taigaMineshaftMinHeight", 8, 5, 255));

					
					swampAndDarkForestMineshaftMinHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that this mineshaft can spawn at. Default is 8.",
							" Note: The mineshaft will spawn between min and max y height set in config.")
						.translation("repurposedstructures.config.mineshaft.swampanddarkforestmineshaftminheight")
						.defineInRange("swampAndDarkForestMineshaftMinHeight", 8, 5, 255));

					endMineshaftMinHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that this mineshaft can spawn at. Default is 30.",
							" Note: The mineshaft will spawn between min and max y height set in config.")
						.translation("repurposedstructures.config.mineshaft.endmineshaftminheight")
						.defineInRange("endMineshaftMinHeight", 30, 5, 255));

					netherMineshaftMinHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that this mineshaft can spawn at. Default is 8.",
								" Note: The mineshaft will spawn between min and max y height set in config.")
						.translation("repurposedstructures.config.mineshaft.nethermineshaftminheight")
						.defineInRange("netherMineshaftMinHeight", 8, 5, 255));

					crimsonMineshaftMinHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that this mineshaft can spawn at. Default is 8.",
								" Note: The mineshaft will spawn between min and max y height set in config.")
						.translation("repurposedstructures.config.mineshaft.crimsonmineshaftminheight")
						.defineInRange("crimsonMineshaftMinHeight", 8, 5, 255));

					warpedMineshaftMinHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that this mineshaft can spawn at. Default is 8.",
								" Note: The mineshaft will spawn between min and max y height set in config.")
						.translation("repurposedstructures.config.mineshaft.warpedmineshaftminheight")
						.defineInRange("warpedMineshaftMinHeight", 8, 5, 255));

				builder.pop();

				builder.push("Max height");
				
					birchMineshaftMaxHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that this mineshaft can spawn at. Default is 45.",
							" Note: The mineshaft will spawn between min and max y height set in config.",
							" Setting this to below min height config will make mineshaft spawn only at min height.")
						.translation("repurposedstructures.config.mineshaft.birchmineshaftmaxheight")
						.defineInRange("birchMineshaftMaxHeight", 45, 5, 255));

					
					jungleMineshaftMaxHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that this mineshaft can spawn at. Default is 45.",
							" Note: The mineshaft will spawn between min and max y height set in config.",
							" Setting this to below min height config will make mineshaft spawn only at min height.")
						.translation("repurposedstructures.config.mineshaft.junglemineshaftmaxheight")
						.defineInRange("jungleMineshaftMaxHeight", 45, 5, 255));

					
					desertMineshaftMaxHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that this mineshaft can spawn at. Default is 45.",
							" Note: The mineshaft will spawn between min and max y height set in config.",
							" Setting this to below min height config will make mineshaft spawn only at min height.")
						.translation("repurposedstructures.config.mineshaft.desertmineshaftmaxheight")
						.defineInRange("desertMineshaftMaxHeight", 45, 5, 255));

					
					stoneMineshaftMaxHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that this mineshaft can spawn at. Default is 45.",
							" Note: The mineshaft will spawn between min and max y height set in config.",
							" Setting this to below min height config will make mineshaft spawn only at min height.")
						.translation("repurposedstructures.config.mineshaft.stonemineshaftmaxheight")
						.defineInRange("stoneMineshaftMaxHeight", 45, 5, 255));

					
					savannaMineshaftMaxHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that this mineshaft can spawn at. Default is 45.",
							" Note: The mineshaft will spawn between min and max y height set in config.",
							" Setting this to below min height config will make mineshaft spawn only at min height.")
						.translation("repurposedstructures.config.mineshaft.savannamineshaftmaxheight")
						.defineInRange("savannaMineshaftMaxHeight", 45, 5, 255));

					
					icyMineshaftMaxHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that this mineshaft can spawn at. Default is 45.",
							" Note: The mineshaft will spawn between min and max y height set in config.",
							" Setting this to below min height config will make mineshaft spawn only at min height.")
						.translation("repurposedstructures.config.mineshaft.icymineshaftmaxheight")
						.defineInRange("icyMineshaftMaxHeight", 45, 5, 255));

					
					oceanMineshaftMaxHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that this mineshaft can spawn at. Default is 25.",
							" Note: The mineshaft will spawn between min and max y height set in config.",
							" Setting this to below min height config will make mineshaft spawn only at min height.")
						.translation("repurposedstructures.config.mineshaft.oceanmineshaftmaxheight")
						.defineInRange("oceanMineshaftMaxHeight", 25, 5, 255));

					
					taigaMineshaftMaxHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that this mineshaft can spawn at. Default is 45.",
							" Note: The mineshaft will spawn between min and max y height set in config.",
							" Setting this to below min height config will make mineshaft spawn only at min height.")
						.translation("repurposedstructures.config.mineshaft.taigamineshaftmaxheight")
						.defineInRange("taigaMineshaftMaxHeight", 45, 5, 255));

					
					swampAndDarkForestMineshaftMaxHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that this mineshaft can spawn at. Default is 45.",
							" Note: The mineshaft will spawn between min and max y height set in config.",
							" Setting this to below min height config will make mineshaft spawn only at min height.")
						.translation("repurposedstructures.config.mineshaft.swampanddarkforestmineshaftmaxheight")
						.defineInRange("swampAndDarkForestMineshaftMaxHeight", 45, 5, 255));

					endMineshaftMaxHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that this mineshaft can spawn at. Default is 37.",
							" Note: The mineshaft will spawn between min and max y height set in config.",
							" Setting this to below min height config will make mineshaft spawn only at min height.")
						.translation("repurposedstructures.config.mineshaft.endmineshaftmaxheight")
						.defineInRange("endMineshaftMaxHeight", 37, 5, 255));

					netherMineshaftMaxHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that this mineshaft can spawn at. Default is 13.",
							" Note: The mineshaft will spawn between min and max y height set in config.",
							" Setting this to below min height config will make mineshaft spawn only at min height.")
						.translation("repurposedstructures.config.mineshaft.nethermineshaftmaxheight")
						.defineInRange("netherMineshaftMaxHeight", 13, 5, 255));

					crimsonMineshaftMaxHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that this mineshaft can spawn at. Default is 13.",
								" Note: The mineshaft will spawn between min and max y height set in config.",
								" Setting this to below min height config will make mineshaft spawn only at min height.")
						.translation("repurposedstructures.config.mineshaft.crimsonmineshaftmaxheight")
						.defineInRange("crimsonMineshaftMaxHeight", 13, 5, 255));

					warpedMineshaftMaxHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that this mineshaft can spawn at. Default is 13.",
								" Note: The mineshaft will spawn between min and max y height set in config.",
								" Setting this to below min height config will make mineshaft spawn only at min height.")
						.translation("repurposedstructures.config.mineshaft.warpedmineshaftmaxheight")
						.defineInRange("warpedMineshaftMaxHeight", 13, 5, 255));

				builder.pop();

				builder.push("Misc");
					barrensIslandsEndMineshafts = subscriber.subscribe(builder
							.comment("\n Add End Mineshafts to End Barrens and End Islands biome.")
							.translation("repurposedstructures.config.mineshaft.barrensislandsendmineshafts")
							.define("barrensIslandsEndMineshafts", false));
				builder.pop();
			builder.pop();
		}
	}
}
