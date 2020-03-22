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
		public ConfigValueListener<Integer> dungeonSpawnrate;
		public ConfigValueListener<Boolean> badlandsDungeons;
		public ConfigValueListener<Boolean> darkForestDungeons;
		public ConfigValueListener<Boolean> desertDungeons;
		public ConfigValueListener<Boolean> jungleDungeons;
		public ConfigValueListener<Boolean> mushroomDungeons;
		public ConfigValueListener<Boolean> snowDungeons;
		public ConfigValueListener<Boolean> swampDungeons;
		public ConfigValueListener<Boolean> endDungeons;
		public ConfigValueListener<Boolean> netherDungeons;

		public ConfigValueListener<Boolean> addWellsToModdedBiomes;
		public ConfigValueListener<Integer> wellSpawnrate;
		public ConfigValueListener<Boolean> canHaveBells;
		public ConfigValueListener<Boolean> badlandsWells;
		public ConfigValueListener<Boolean> netherWells;
		public ConfigValueListener<Boolean> snowWells;
		public ConfigValueListener<Boolean> mossyStoneWells;
		public ConfigValueListener<Boolean> forestWells;

		public ConfigValueListener<Boolean> addMiscToModdedBiomes;
		public ConfigValueListener<Boolean> boulderTiny;
		public ConfigValueListener<Boolean> boulderGiant;
		public ConfigValueListener<Boolean> hornedSwampTree;
		
		public ConfigValueListener<Boolean> lootChestsMS;
		public ConfigValueListener<Integer> mineshaftSpawnrate;
		public ConfigValueListener<Boolean> addMineshaftsToModdedBiomes;
		public ConfigValueListener<Boolean> birchMineshafts;
		public ConfigValueListener<Boolean> jungleMineshafts;
		public ConfigValueListener<Boolean> desertMineshafts;
		public ConfigValueListener<Boolean> stoneMineshafts;
		public ConfigValueListener<Boolean> savannaMineshafts;
		public ConfigValueListener<Boolean> icyMineshafts;
		public ConfigValueListener<Boolean> oceanMineshafts;
		public ConfigValueListener<Boolean> taigaMineshafts;
		public ConfigValueListener<Boolean> swampAndDarkForestMineshafts;
		public ConfigValueListener<Boolean> endMineshafts;
		public ConfigValueListener<Boolean> netherMineshafts;

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


		RSConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
			builder.push("Feature Options");

				builder.push("Dungeons");
				
					addDungeonsToModdedBiomes = subscriber.subscribe(builder
							.comment("\r\n Add the custom dungeons to modded biomes of the same categories/type.")
						.translation("repurposedstructures.config.feature.dungeons.adddungeonstomoddedbiomes")
						.define("addDungeonsToModdedBiomes", false));
					
					dungeonSpawnrate = subscriber.subscribe(builder
							.comment( "\r\n How often dungeons will attempt to spawn per chunk.\r\n " 
									+ "1 for extremely rare Dungeons and 1000 for max Dungeon spawnrate.")
							.translation("repurposedstructures.config.structure.dungeons.dungeonspawnrate")
							.defineInRange("dungeonSpawnrate", 8, 1, 1000));
				
					badlandsDungeons = subscriber.subscribe(builder
							.comment("\r\n Replace vanilla dungeon in Badlands biomes with Badlands themed dungeon.")
						.translation("repurposedstructures.config.feature.dungeons.badlandsdungeons")
						.define("badlandsDungeons", true));
				
					darkForestDungeons = subscriber.subscribe(builder
							.comment("\r\n Replace vanilla dungeon in Dark Forest biomes with Dark Forest themed dungeon.")
						.translation("repurposedstructures.config.feature.dungeons.darkforestdungeons")
						.define("darkForestDungeons", true));
				
					desertDungeons = subscriber.subscribe(builder
							.comment("\r\n Replace vanilla dungeon in Desert biomes with Desert themed dungeon.")
						.translation("repurposedstructures.config.feature.dungeons.desertdungeons")
						.define("desertDungeons", true));
				
					jungleDungeons = subscriber.subscribe(builder
							.comment("\r\n Replace vanilla dungeon in Jungle biomes with Jungle themed dungeon.")
						.translation("repurposedstructures.config.feature.dungeons.jungledungeons")
						.define("jungleDungeons", true));
				
					mushroomDungeons = subscriber.subscribe(builder
							.comment("\r\n Replace vanilla dungeon in Mushroom biomes with Mushroom themed dungeon.")
						.translation("repurposedstructures.config.feature.dungeons.mushroomdungeons")
						.define("mushroomDungeons", true));
				
					snowDungeons = subscriber.subscribe(builder
							.comment("\r\n Replace vanilla dungeon in icy/snow biomes with icy/snow themed dungeon.")
						.translation("repurposedstructures.config.feature.dungeons.snowdungeons")
						.define("snowDungeons", true));
				
					swampDungeons = subscriber.subscribe(builder
							.comment("\r\n Replace vanilla dungeon in Swamp biomes with Swamp themed dungeon.")
						.translation("repurposedstructures.config.feature.dungeons.swampdungeons")
						.define("swampDungeons", true));
				
					endDungeons = subscriber.subscribe(builder
							.comment("\r\n Add End themed dungeon to End biomes outside the Enderdragon island.")
						.translation("repurposedstructures.config.feature.dungeons.enddungeons")
						.define("endDungeons", true));

					netherDungeons = subscriber.subscribe(builder
							.comment("\r\n Add Nether themed dungeon to Nether biomes.")
						.translation("repurposedstructures.config.feature.dungeons.netherdungeons")
						.define("netherDungeons", true));
					
				builder.pop();

				builder.push("Small Wells");
				
					addWellsToModdedBiomes = subscriber.subscribe(builder
							.comment("\r\n Add the custom wells to modded biomes of the same categories/type.")
						.translation("repurposedstructures.config.feature.smallwells.addwellstomoddedbiomes")
						.define("addWellsToModdedBiomes", false));

					wellSpawnrate = subscriber.subscribe(builder
							.comment( "\r\n How often wells will attempt to spawn per chunk." 
									+ "\r\n The chance of a well generating at a chunk is 1/spawnrate."
									+ "\r\n 1 for wells spawning in every chunk and 10000 for extremely rare wells.")
							.translation("repurposedstructures.config.feature.smallwells.wellSpawnrate")
							.defineInRange("wellSpawnrate", 350, 1, 10000));

					canHaveBells = subscriber.subscribe(builder
							.comment("\r\n Determines if Wells can have a chance of spawning a Bell.")
						.translation("repurposedstructures.config.feature.smallwells.canhavebells")
						.define("canHaveBells", true));
					
					badlandsWells = subscriber.subscribe(builder
							.comment("\r\n Add Badlands themed wells to Badlands biomes.")
						.translation("repurposedstructures.config.feature.smallwells.badlandswells")
						.define("badlandsWells", true));
					
					netherWells = subscriber.subscribe(builder
							.comment("\r\n Add Nether themed wells to Nether biomes.")
						.translation("repurposedstructures.config.feature.smallwells.netherwells")
						.define("netherWells", true));
					
					snowWells = subscriber.subscribe(builder
							.comment("\r\n Add Snow themed wells to snowy and icy biomes.")
						.translation("repurposedstructures.config.feature.smallwells.snowwells")
						.define("snowWells", true));
					
					mossyStoneWells = subscriber.subscribe(builder
							.comment("\r\n Add mossy stone themed wells to Jungles, Dark Oak, and Swamp biomes.")
						.translation("repurposedstructures.config.feature.smallwells.mossystonewells")
						.define("mossyStoneWells", true));

					forestWells = subscriber.subscribe(builder
							.comment("\r\n Add a wood themed wells to Forest and Birch Forest biomes.")
						.translation("repurposedstructures.config.feature.smallwells.forestwells")
						.define("forestWells", true));
					
				builder.pop();
				
				builder.push("Misc");
				
					addMiscToModdedBiomes = subscriber.subscribe(builder
							.comment("\r\n Add the custom features to modded biomes of the same categories/type.")
						.translation("repurposedstructures.config.feature.dungeons.addmisctomoddedbiomes")
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
				
					mineshaftSpawnrate = subscriber.subscribe(builder
							.comment( "\r\n How often Mineshafts will spawn.\r\n " 
									+ "0 for no Mineshafts and 1000 for max spawnrate.")
							.translation("repurposedstructures.config.structure.mineshaft.mineshaftspawnrate")
							.defineInRange("mineshaftSpawnrate", 40, 0, 1000));

					lootChestsMS = subscriber.subscribe(builder
							.comment("\r\n Controls whether loot chests spawn or not in modded Mineshafts.")
						.translation("repurposedstructures.config.structure.mineshaft.lootchestsms")
						.define("lootChestsMS", true));

					addMineshaftsToModdedBiomes = subscriber.subscribe(builder
							.comment("\r\n Add the custom Mineshafts to modded biomes of the same categories/type.")
						.translation("repurposedstructures.config.feature.dungeons.addmineshaftstomoddedbiomes")
						.define("addMineshaftsToModdedBiomes", false));
					
					birchMineshafts = subscriber.subscribe(builder
							.comment("\r\n Replace Mineshafts in Birch biomes with a Birch themed Mineshaft.")
						.translation("repurposedstructures.config.feature.mineshaft.birchmineshafts")
						.define("birchMineshafts", true));

					jungleMineshafts = subscriber.subscribe(builder
							.comment("\r\n Replace Mineshafts in Jungle biomes with a Jungle themed Mineshaft.")
						.translation("repurposedstructures.config.feature.mineshaft.junglemineshafts")
						.define("jungleMineshafts", true));

					desertMineshafts = subscriber.subscribe(builder
							.comment("\r\n Replace Mineshafts in Desert biomes with a Desert themed Mineshaft.")
						.translation("repurposedstructures.config.feature.mineshaft.desertmineshafts")
						.define("desertMineshafts", true));

					stoneMineshafts = subscriber.subscribe(builder
							.comment("\r\n Replace Mineshafts in Mountain (Extreme Hills) biomes with a Stone themed Mineshaft.")
						.translation("repurposedstructures.config.feature.mineshaft.stonemineshafts")
						.define("stoneMineshafts", true));

					savannaMineshafts = subscriber.subscribe(builder
							.comment("\r\n Replace Mineshafts in Savanna biomes with a Savanna themed Mineshaft.")
						.translation("repurposedstructures.config.feature.mineshaft.savannamineshafts")
						.define("savannaMineshafts", true));

					icyMineshafts = subscriber.subscribe(builder
							.comment("\r\n Replace Mineshafts in Snowy/Icy biomes with an Ice themed Mineshaft."
									+"\r\n Note: Snowy Taiga Biomes will get Ice Mineshaft instead of Taiga theme.")
						.translation("repurposedstructures.config.feature.mineshaft.icymineshafts")
						.define("icyMineshafts", true));

					oceanMineshafts = subscriber.subscribe(builder
							.comment("\r\n Replace Mineshafts in Ocean biomes with an Ocean themed Mineshaft.")
						.translation("repurposedstructures.config.feature.mineshaft.oceanmineshafts")
						.define("oceanMineshafts", true));

					taigaMineshafts = subscriber.subscribe(builder
							.comment("\r\n Replace Mineshafts in Taiga biomes with a Taiga themed Mineshaft.")
						.translation("repurposedstructures.config.feature.mineshaft.taigamineshafts")
						.define("taigaMineshafts", true));
					
					swampAndDarkForestMineshafts = subscriber.subscribe(builder
							.comment("\r\n Replace Mineshafts in Swamps and Dark Forests with a"
									+"\r\n swampy/dark oak themed Mineshaft.")
						.translation("repurposedstructures.config.feature.mineshaft.swampanddarkforestmineshafts")
						.define("swampAndDarkForestMineshafts", true));
					
					endMineshafts = subscriber.subscribe(builder
							.comment("\r\n Add End themed Mineshafts to End biomes outside the Enderdragon island.")
						.translation("repurposedstructures.config.feature.mineshaft.endmineshafts")
						.define("endMineshafts", true));
					
					netherMineshafts = subscriber.subscribe(builder
							.comment("\r\n Add Nether themed Mineshafts to Nether biomes.")
						.translation("repurposedstructures.config.feature.mineshaft.nethermineshafts")
						.define("netherMineshafts", true));
					
				builder.pop();

				builder.push("Stronghold");

					useVanillaStronghold = subscriber.subscribe(builder
						.comment("\r\n Use vanilla Stronghold instead of using this mod's modded version.\r\n"
								+" Note: The other Stronghold configs below will have no effect on vanilla Strongholds.")
						.translation("repurposedstructures.config.structure.stronghold.usevanillastronghold")
						.define("useVanillaStronghold", false));

					allowStonebrickStronghold = subscriber.subscribe(builder
						.comment("\r\n Allow Stonebrick styled Stronghold to generate in non-Nether biomes.\r\n")
						.translation("repurposedstructures.config.structure.stronghold.allowstonebrickstronghold")
						.define("allowStonebrickStronghold", true));

					allowNetherStronghold = subscriber.subscribe(builder
						.comment("\r\n Allow Nether-styled Strongholds to spawn in Nether category biomes.\r\n")
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
			builder.pop();
		}
	}
}
