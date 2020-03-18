package com.telepathicgrunt.repurposedstructures;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber
public class RSConfig
{
	/*
	 * Config to control all sorts of settings used for world generation with this mod. This ranges from ore rarity, what
	 * biomes spawn, structure spawning, and more.
	 */

	//Putting this at start of option will put it at top of all options: '\0'+
	// '\u00a7'+ is used to replace § since § will be turned to gibberish when this mod is ran on Minecraft outside a development environment.

	public static final ServerConfig SERVER;
	public static final ForgeConfigSpec SERVER_SPEC;
	static
	{
		final Pair<ServerConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
		SERVER_SPEC = specPair.getRight();
		SERVER = specPair.getLeft();
	}

	public static boolean addDungeonsToModdedBiomes = false;
	public static boolean badlandsDungeons = true;
	public static boolean darkForestDungeons = true;
	public static boolean desertDungeons = true;
	public static boolean jungleDungeons = true;
	public static boolean mushroomDungeons = true;
	public static boolean snowDungeons = true;
	public static boolean swampDungeons = true;
	public static boolean endDungeons = true;
	public static boolean netherDungeons = true;

	public static boolean addMiscToModdedBiomes = false;
	public static boolean boulderTiny = true;
	public static boolean boulderGiant = true;
	public static boolean hornedSwampTree = true;

	public static boolean lootChestsMS = true;
	public static int mineshaftSpawnrate = 40;
	public static boolean addMineshaftsToModdedBiomes = false;
	public static boolean birchMineshafts = true;
	public static boolean jungleMineshafts = true;
	public static boolean desertMineshafts = true;
	public static boolean stoneMineshafts = true;
	public static boolean savannaMineshafts = true;
	public static boolean icyMineshafts = true;
	public static boolean oceanMineshafts = true;
	public static boolean taigaMineshafts = true;
	public static boolean swampAndDarkForestMineshafts = true;
	public static boolean endMineshafts = true;
	public static boolean netherMineshafts = true;
	
	public static int strongholdSpawnrate = 62;
	public static double silverfishSpawnrateSH = 0.8D;
	public static boolean allowExtraSilverfishSpawnerSH = true;
	public static boolean lootChestsSH = true;
	
	public static int jungleFortressSpawnrate = 16;
	public static double silverfishSpawnrateJF = 0.5D;
	public static boolean allowSilverfishSpawnerJF = true;
	public static boolean lootChestsJF = true;
	public static boolean addJungleFortressToModdedBiomes = false;

	public static class ServerConfig
	{
		public final BooleanValue addDungeonsToModdedBiomes;
		public final BooleanValue badlandsDungeons;
		public final BooleanValue darkForestDungeons;
		public final BooleanValue desertDungeons;
		public final BooleanValue jungleDungeons;
		public final BooleanValue mushroomDungeons;
		public final BooleanValue snowDungeons;
		public final BooleanValue swampDungeons;
		public final BooleanValue endDungeons;
		public final BooleanValue netherDungeons;

		public final BooleanValue addMiscToModdedBiomes;
		public final BooleanValue boulderTiny;
		public final BooleanValue boulderGiant;
		public final BooleanValue hornedSwampTree;
		
		public final BooleanValue lootChestsMS;
		public final IntValue mineshaftSpawnrate;
		public final BooleanValue addMineshaftsToModdedBiomes;
		public final BooleanValue birchMineshafts;
		public final BooleanValue jungleMineshafts;
		public final BooleanValue desertMineshafts;
		public final BooleanValue stoneMineshafts;
		public final BooleanValue savannaMineshafts;
		public final BooleanValue icyMineshafts;
		public final BooleanValue oceanMineshafts;
		public final BooleanValue taigaMineshafts;
		public final BooleanValue swampAndDarkForestMineshafts;
		public final BooleanValue endMineshafts;
		public final BooleanValue netherMineshafts;
		
		public final IntValue strongholdSpawnrate;
		public final DoubleValue silverfishSpawnrateSH;
		public final BooleanValue allowExtraSilverfishSpawnerSH;
		public final BooleanValue lootChestsSH;
		
		public final IntValue jungleFortressSpawnrate;
		public final DoubleValue silverfishSpawnrateJF;
		public final BooleanValue allowSilverfishSpawnerJF;
		public final BooleanValue lootChestsJF;
		public final BooleanValue addJungleFortressToModdedBiomes;


		ServerConfig(ForgeConfigSpec.Builder builder)
		{
			builder.push("Feature Options");

				builder.push("Dungeons");
				
					addDungeonsToModdedBiomes = builder
							.comment("\r\n Add the custom dungeons to modded biomes of the same categories/type.")
						.translation("repurposedstructures.config.feature.dungeons.adddungeonstomoddedbiomes")
						.define("addDungeonsToModdedBiomes", false);
				
					badlandsDungeons = builder
							.comment("\r\n Replace vanilla dungeon in Badlands biomes with Badlands themed dungeon.")
						.translation("repurposedstructures.config.feature.dungeons.badlandsdungeons")
						.define("badlandsDungeons", true);
				
					darkForestDungeons = builder
							.comment("\r\n Replace vanilla dungeon in Dark Forest biomes with Dark Forest themed dungeon.")
						.translation("repurposedstructures.config.feature.dungeons.darkforestdungeons")
						.define("darkForestDungeons", true);
				
					desertDungeons = builder
							.comment("\r\n Replace vanilla dungeon in Desert biomes with Desert themed dungeon.")
						.translation("repurposedstructures.config.feature.dungeons.desertdungeons")
						.define("desertDungeons", true);
				
					jungleDungeons = builder
							.comment("\r\n Replace vanilla dungeon in Jungle biomes with Jungle themed dungeon.")
						.translation("repurposedstructures.config.feature.dungeons.jungledungeons")
						.define("jungleDungeons", true);
				
					mushroomDungeons = builder
							.comment("\r\n Replace vanilla dungeon in Mushroom biomes with Mushroom themed dungeon.")
						.translation("repurposedstructures.config.feature.dungeons.mushroomdungeons")
						.define("mushroomDungeons", true);
				
					snowDungeons = builder
							.comment("\r\n Replace vanilla dungeon in icy/snow biomes with icy/snow themed dungeon.")
						.translation("repurposedstructures.config.feature.dungeons.snowdungeons")
						.define("snowDungeons", true);
				
					swampDungeons = builder
							.comment("\r\n Replace vanilla dungeon in Swamp biomes with Swamp themed dungeon.")
						.translation("repurposedstructures.config.feature.dungeons.swampdungeons")
						.define("swampDungeons", true);
				
					endDungeons = builder
							.comment("\r\n Add End themed dungeon to End biomes outside the Enderdragon island.")
						.translation("repurposedstructures.config.feature.dungeons.enddungeons")
						.define("endDungeons", true);

					netherDungeons = builder
							.comment("\r\n Add Nether themed dungeon to Nether biomes.")
						.translation("repurposedstructures.config.feature.dungeons.netherdungeons")
						.define("netherDungeons", true);
					
				builder.pop();
				
				builder.push("Misc");
				
					addMiscToModdedBiomes = builder
							.comment("\r\n Add the custom features to modded biomes of the same categories/type.")
						.translation("repurposedstructures.config.feature.dungeons.addmisctomoddedbiomes")
						.define("addMiscToModdedBiomes", false);

					boulderTiny = builder
							.comment("\r\n Adds tiny boulders to Taiga Mountains and Snowy Taiga Mountains biomes "
									+"\r\n that can contain small amounts of Coal and Iron ores.")
						.translation("repurposedstructures.config.feature.misc.bouldertiny")
						.define("boulderTiny", true);
					
					boulderGiant = builder
							.comment("\r\n Replaces boulders in Giant Tree Taiga Hills and Giant Spruce Taiga Hills"
									+"\r\n biomes with a larger boulder that can contain Coal, Iron, and extremely"
									+"\r\n rarely, can also have Diamond Ores.")
						.translation("repurposedstructures.config.feature.misc.netherdungeons")
						.define("boulderGiant", true);
					
					hornedSwampTree = builder
							.comment("\r\n Adds a large tree somewhat uncommonly to Swamp biome and replaces"
									+"\r\n all vanilla trees in Swamp Hills biome with the larger tree.")
						.translation("repurposedstructures.config.feature.misc.hornedSwampTree")
						.define("hornedSwampTree", true);
					
				builder.pop();
				
			builder.pop();

			builder.push("Structure Options");

				builder.push("Mineshaft");
				
					mineshaftSpawnrate = builder
							.comment( "\r\n How often Mineshafts will spawn.\r\n " 
									+ "0 for no Mineshafts and 1000 for max spawnrate.")
							.translation("repurposedstructures.config.structure.mineshaft.mineshaftspawnrate")
							.defineInRange("mineshaftSpawnrate", 40, 0, 1000);

					lootChestsMS = builder
							.comment("\r\n Controls whether loot chests spawn or not in modded Mineshafts.")
						.translation("repurposedstructures.config.structure.mineshaft.lootchestsms")
						.define("lootChestsMS", true);

					addMineshaftsToModdedBiomes = builder
							.comment("\r\n Add the custom Mineshafts to modded biomes of the same categories/type.")
						.translation("repurposedstructures.config.feature.dungeons.addmineshaftstomoddedbiomes")
						.define("addMineshaftsToModdedBiomes", false);
					
					birchMineshafts = builder
							.comment("\r\n Replace Mineshafts in Birch biomes with a Birch themed Mineshaft.")
						.translation("repurposedstructures.config.feature.mineshaft.birchmineshafts")
						.define("birchMineshafts", true);

					jungleMineshafts = builder
							.comment("\r\n Replace Mineshafts in Jungle biomes with a Jungle themed Mineshaft.")
						.translation("repurposedstructures.config.feature.mineshaft.junglemineshafts")
						.define("jungleMineshafts", true);

					desertMineshafts = builder
							.comment("\r\n Replace Mineshafts in Desert biomes with a Desert themed Mineshaft.")
						.translation("repurposedstructures.config.feature.mineshaft.desertmineshafts")
						.define("desertMineshafts", true);

					stoneMineshafts = builder
							.comment("\r\n Replace Mineshafts in Mountain (Extreme Hills) biomes with a Stone themed Mineshaft.")
						.translation("repurposedstructures.config.feature.mineshaft.stonemineshafts")
						.define("stoneMineshafts", true);

					savannaMineshafts = builder
							.comment("\r\n Replace Mineshafts in Savanna biomes with a Savanna themed Mineshaft.")
						.translation("repurposedstructures.config.feature.mineshaft.savannamineshafts")
						.define("savannaMineshafts", true);

					icyMineshafts = builder
							.comment("\r\n Replace Mineshafts in Snowy/Icy biomes with an Ice themed Mineshaft."
									+"\r\n Note: Snowy Taiga Biomes will get Ice Mineshaft instead of Taiga theme.")
						.translation("repurposedstructures.config.feature.mineshaft.icymineshafts")
						.define("icyMineshafts", true);

					oceanMineshafts = builder
							.comment("\r\n Replace Mineshafts in Ocean biomes with an Ocean themed Mineshaft.")
						.translation("repurposedstructures.config.feature.mineshaft.oceanmineshafts")
						.define("oceanMineshafts", true);

					taigaMineshafts = builder
							.comment("\r\n Replace Mineshafts in Taiga biomes with a Taiga themed Mineshaft.")
						.translation("repurposedstructures.config.feature.mineshaft.taigamineshafts")
						.define("taigaMineshafts", true);
					
					swampAndDarkForestMineshafts = builder
							.comment("\r\n Replace Mineshafts in Swamps and Dark Forests with a"
									+"\r\n swampy/dark oak themed Mineshaft.")
						.translation("repurposedstructures.config.feature.mineshaft.swampanddarkforestmineshafts")
						.define("swampAndDarkForestMineshafts", true);
					
					endMineshafts = builder
							.comment("\r\n Add End themed Mineshafts to End biomes outside the Enderdragon island.")
						.translation("repurposedstructures.config.feature.mineshaft.endmineshafts")
						.define("endMineshafts", true);
					
					netherMineshafts = builder
							.comment("\r\n Add Nether themed Mineshafts to Nether biomes.")
						.translation("repurposedstructures.config.feature.mineshaft.nethermineshafts")
						.define("netherMineshafts", true);
					
				builder.pop();

				builder.push("Stronghold");
	
					strongholdSpawnrate = builder
							.comment("\r\n How rare are Strongholds." 
									+ "\n " 
									+ "1 for spawning in most chunks and 1001 for no spawn.")
							.translation("repurposedstructures.config.structure.stronghold.strongholdspawnrate")
							.defineInRange("strongholdSpawnrate", 62, 1, 1001);
		
					silverfishSpawnrateSH = builder
							.comment("\r\n How often Silverfish Blocks will generate in Strongholds as a percentage." 
									+ "\r\n Note: Mossy Stone Bricks block cannot be infected by Silverfish"
									+ "\n "
									+ "0 for no Silverfish Blocks and 100 for max spawnrate.")
							.translation("repurposedstructures.config.structure.stronghold.silverfishspawnratesh")
							.defineInRange("silverfishSpawnrateSH", 0.8D, 0, 100);
		
					allowExtraSilverfishSpawnerSH = builder
							.comment("\r\n Can additional Silverfish Mob Spawners generate in Stronghold?\r\n"
									+" Note: Silverfish spawner in Portal Room will always remain.")
							.translation("repurposedstructures.config.structure.stronghold.allowextrasilverfishspawnersh")
							.define("allowExtraSilverfishSpawnerSH", true);
					
					lootChestsSH = builder
							.comment("\r\n Controls whether loot chests spawn or not in the Stronghold.")
						.translation("repurposedstructures.config.structure.stronghold.lootchestssh")
						.define("lootChestsSH", true);
	
				builder.pop();
	
				builder.push("Jungle Fortress");
	
					jungleFortressSpawnrate = builder
							.comment("\r\n How rare are Jungle Fortresses." 
									+ "\n "
									+ "1 for spawning in most chunks and 1001 for no spawn.")
							.translation("repurposedstructures.config.structure.junglefortress.junglefortressspawnrate")
							.defineInRange("jungleFortressSpawnrate", 16, 1, 1001);

					silverfishSpawnrateJF = builder
							.comment("\r\n How often Silverfish Blocks will generate in Jungle Fortress as a percentage."
									+ "\r\n Note: Mossy Stone Bricks block cannot be infected by Silverfish"
									+ "\n "
									+ "0 for no Silverfish Blocks and 100 for max spawnrate.")
							.translation("repurposedstructures.config.structure.junglefortress.silverfishspawnratejf")
							.defineInRange("silverfishSpawnrateJF", 0.5D, 0, 100);
					
					allowSilverfishSpawnerJF = builder
							.comment("\r\n Silverfish Mob Spawners generate in Stone Fortresses."
									+"\r\n If turned off, the spawners will become Skeleton spawners.")
							.translation("repurposedstructures.config.structure.junglefortress.allowsilverfishspawnerjf")
							.define("allowSilverfishSpawnerJF", true);

					lootChestsJF = builder
							.comment("\r\n Controls whether loot chests spawn or not in Jungle Fortresses.")
						.translation("repurposedstructures.config.structure.junglefortress.lootchestsjf")
						.define("lootChestsJF", true);

					addJungleFortressToModdedBiomes = builder
							.comment("\r\n Add Jungle Fortress to modded jungle biomes.")
						.translation("repurposedstructures.config.feature.dungeons.addjunglefortresstomoddedbiomes")
						.define("addJungleFortressToModdedBiomes", false);
					
				builder.pop();
			builder.pop();
		}
	}


	public static void refreshServer()
	{
		addDungeonsToModdedBiomes = SERVER.addDungeonsToModdedBiomes.get();
		badlandsDungeons = SERVER.badlandsDungeons.get();
		darkForestDungeons = SERVER.darkForestDungeons.get();
		desertDungeons = SERVER.desertDungeons.get();
		jungleDungeons = SERVER.jungleDungeons.get();
		mushroomDungeons = SERVER.mushroomDungeons.get();
		snowDungeons = SERVER.snowDungeons.get();
		swampDungeons = SERVER.swampDungeons.get();
		endDungeons = SERVER.endDungeons.get();
		netherDungeons = SERVER.netherDungeons.get();

		addMiscToModdedBiomes = SERVER.addMiscToModdedBiomes.get();
		boulderTiny = SERVER.boulderTiny.get();
		boulderGiant = SERVER.boulderGiant.get();
		hornedSwampTree = SERVER.hornedSwampTree.get();
		
		lootChestsMS = SERVER.lootChestsMS.get();
		mineshaftSpawnrate = SERVER.mineshaftSpawnrate.get();
		addMineshaftsToModdedBiomes = SERVER.addMineshaftsToModdedBiomes.get();
		birchMineshafts = SERVER.birchMineshafts.get();
		jungleMineshafts = SERVER.jungleMineshafts.get();
		desertMineshafts = SERVER.desertMineshafts.get();
		stoneMineshafts = SERVER.stoneMineshafts.get();
		savannaMineshafts = SERVER.savannaMineshafts.get();
		icyMineshafts = SERVER.icyMineshafts.get();
		oceanMineshafts = SERVER.oceanMineshafts.get();
		taigaMineshafts = SERVER.taigaMineshafts.get();
		swampAndDarkForestMineshafts = SERVER.swampAndDarkForestMineshafts.get();
		endMineshafts = SERVER.endMineshafts.get();
		netherMineshafts = SERVER.netherMineshafts.get();
		
		strongholdSpawnrate = SERVER.strongholdSpawnrate.get();
		silverfishSpawnrateSH = SERVER.silverfishSpawnrateSH.get();
		allowExtraSilverfishSpawnerSH = SERVER.allowExtraSilverfishSpawnerSH.get();
		lootChestsSH = SERVER.lootChestsSH.get();
		
		jungleFortressSpawnrate = SERVER.jungleFortressSpawnrate.get();
		silverfishSpawnrateJF = SERVER.silverfishSpawnrateJF.get();
		allowSilverfishSpawnerJF = SERVER.allowSilverfishSpawnerJF.get();
		lootChestsJF = SERVER.lootChestsJF.get();
		addJungleFortressToModdedBiomes = SERVER.addJungleFortressToModdedBiomes.get();
	}
}
