package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSDungeonsConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;

	public static ForgeConfigSpec.BooleanValue shulkerBoxInEndDungeons;

	public static ForgeConfigSpec.IntValue badlandsDungeonAttemptsPerChunk;
	public static ForgeConfigSpec.IntValue darkForestDungeonAttemptsPerChunk;
	public static ForgeConfigSpec.IntValue desertDungeonAttemptsPerChunk;
	public static ForgeConfigSpec.IntValue deepDungeonAttemptsPerChunk;
	public static ForgeConfigSpec.IntValue jungleDungeonAttemptsPerChunk;
	public static ForgeConfigSpec.IntValue mushroomDungeonAttemptsPerChunk;
	public static ForgeConfigSpec.IntValue snowDungeonAttemptsPerChunk;
	public static ForgeConfigSpec.IntValue icyDungeonAttemptsPerChunk;
	public static ForgeConfigSpec.IntValue swampDungeonAttemptsPerChunk;
	public static ForgeConfigSpec.IntValue endDungeonAttemptsPerChunk;
	public static ForgeConfigSpec.IntValue netherDungeonAttemptsPerChunk;
	public static ForgeConfigSpec.IntValue oceanDungeonAttemptsPerChunk;

	public static ForgeConfigSpec.ConfigValue<Integer> badlandsDungeonMinHeight;
	public static ForgeConfigSpec.ConfigValue<Integer> darkForestDungeonMinHeight;
	public static ForgeConfigSpec.ConfigValue<Integer> desertDungeonMinHeight;
	public static ForgeConfigSpec.ConfigValue<Integer> deepDungeonMinHeight;
	public static ForgeConfigSpec.ConfigValue<Integer> jungleDungeonMinHeight;
	public static ForgeConfigSpec.ConfigValue<Integer> mushroomDungeonMinHeight;
	public static ForgeConfigSpec.ConfigValue<Integer> snowDungeonMinHeight;
	public static ForgeConfigSpec.ConfigValue<Integer> icyDungeonMinHeight;
	public static ForgeConfigSpec.ConfigValue<Integer> swampDungeonMinHeight;
	public static ForgeConfigSpec.ConfigValue<Integer> endDungeonMinHeight;
	public static ForgeConfigSpec.ConfigValue<Integer> netherDungeonMinHeight;
	public static ForgeConfigSpec.ConfigValue<Integer> oceanDungeonMinHeight;

	public static ForgeConfigSpec.ConfigValue<Integer> badlandsDungeonMaxHeight;
	public static ForgeConfigSpec.ConfigValue<Integer> darkForestDungeonMaxHeight;
	public static ForgeConfigSpec.ConfigValue<Integer> desertDungeonMaxHeight;
	public static ForgeConfigSpec.ConfigValue<Integer> deepDungeonMaxHeight;
	public static ForgeConfigSpec.ConfigValue<Integer> jungleDungeonMaxHeight;
	public static ForgeConfigSpec.ConfigValue<Integer> mushroomDungeonMaxHeight;
	public static ForgeConfigSpec.ConfigValue<Integer> snowDungeonMaxHeight;
	public static ForgeConfigSpec.ConfigValue<Integer> icyDungeonMaxHeight;
	public static ForgeConfigSpec.ConfigValue<Integer> swampDungeonMaxHeight;
	public static ForgeConfigSpec.ConfigValue<Integer> endDungeonMaxHeight;
	public static ForgeConfigSpec.ConfigValue<Integer> netherDungeonMaxHeight;
	public static ForgeConfigSpec.ConfigValue<Integer> oceanDungeonMaxHeight;

	static {
		ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
		setupConfig(configBuilder);
		GENERAL_SPEC = configBuilder.build();
	}

	private static void setupConfig(ForgeConfigSpec.Builder builder) {

		shulkerBoxInEndDungeons = builder
				.comment("\n Whether End Dungeons spawns Shulker Boxes. if false, spawns regular Chests instead.",
						"(Configuredfeatures are unable to be overridden by datapack due to bad Forge hook placement. Hence this config option)")
				.translation("repurposedstructures.shulkerboxinenddungeons")
				.define("shulkerBoxInEndDungeons", true);

		builder.comment("-----------------------------------------------------------------------------------------",
				" How many positions Repurposed Structures Dungeons will pick per chunk to check if it can spawn at those positions.",
				" Does NOT replace vanilla Dungeons at all. Except for Deep Dungeon which replaces Vanilla Dungeons below y = 0",
				" 0 for no Dungeons at all and 1000 for max Dungeon spawnrate.");
		builder.push("AttemptsPerChunk");

			badlandsDungeonAttemptsPerChunk = builder
					.translation("repurposedstructures.badlandsdungeonattemptsperchunk")
					.defineInRange("badlandsDungeonAttemptsPerChunk", 8, 0, 1000);

			darkForestDungeonAttemptsPerChunk = builder
				.translation("repurposedstructures.darkforestdungeonattemptsperchunk")
				.defineInRange("darkForestDungeonAttemptsPerChunk", 8, 0, 1000);

			desertDungeonAttemptsPerChunk = builder
				.translation("repurposedstructures.desertdungeonattemptsperchunk")
				.defineInRange("desertDungeonAttemptsPerChunk", 8, 0, 1000);

			deepDungeonAttemptsPerChunk = builder
				.translation("repurposedstructures.deepdungeonattemptsperchunk")
				.defineInRange("deepDungeonAttemptsPerChunk", 4, 0, 1000);

			jungleDungeonAttemptsPerChunk = builder
				.translation("repurposedstructures.jungledungeonattemptsperchunk")
				.defineInRange("jungleDungeonAttemptsPerChunk", 8, 0, 1000);

			mushroomDungeonAttemptsPerChunk = builder
				.translation("repurposedstructures.mushroomdungeonattemptsperchunk")
				.defineInRange("mushroomDungeonAttemptsPerChunk", 8, 0, 1000);

			snowDungeonAttemptsPerChunk = builder
				.translation("repurposedstructures.snowdungeonattemptsperchunk")
				.defineInRange("snowDungeonAttemptsPerChunk", 8, 0, 1000);

			icyDungeonAttemptsPerChunk = builder
				.translation("repurposedstructures.icydungeonattemptsperchunk")
				.defineInRange("icyDungeonAttemptsPerChunk", 8, 0, 1000);

			swampDungeonAttemptsPerChunk = builder
				.translation("repurposedstructures.swampdungeonattemptsperchunk")
				.defineInRange("swampDungeonAttemptsPerChunk", 8, 0, 1000);

			endDungeonAttemptsPerChunk = builder
				.translation("repurposedstructures.enddungeonattemptsperchunk")
				.defineInRange("endDungeonAttemptsPerChunk", 12, 0, 1000);

			netherDungeonAttemptsPerChunk = builder
				.translation("repurposedstructures.netherdungeonattemptsperchunk")
				.defineInRange("netherDungeonAttemptsPerChunk", 12, 0, 1000);

			oceanDungeonAttemptsPerChunk = builder
				.translation("repurposedstructures.oceandungeonattemptsperchunk")
				.defineInRange("oceanDungeonAttemptsPerChunk", 2, 0, 1000);

		builder.pop();


		builder.comment("-----------------------------------------------------------------------------------------",
				" Minimum Y height that this dungeon can spawn at.",
				" Note: The dungeon will spawn between min and max y height set in config.");
		builder.push("Min Height");

			badlandsDungeonMinHeight = builder
				.translation("repurposedstructures.badlandsdungeonminheight")
				.define("badlandsDungeonMinHeight", 35);

			darkForestDungeonMinHeight = builder
				.translation("repurposedstructures.darkforestdungeonminheight")
				.define("darkForestDungeonMinHeight", 35);

			desertDungeonMinHeight = builder
				.translation("repurposedstructures.desertdungeonminheight")
				.define("desertDungeonMinHeight", 35);

			deepDungeonMinHeight = builder
				.translation("repurposedstructures.deepdungeonminheight")
				.define("deepDungeonMinHeight", -60);

			jungleDungeonMinHeight = builder
				.translation("repurposedstructures.jungledungeonminheight")
				.define("jungleDungeonMinHeight", 35);

			mushroomDungeonMinHeight = builder
				.translation("repurposedstructures.mushroomdungeonminheight")
				.define("mushroomDungeonMinHeight", 35);

			snowDungeonMinHeight = builder
				.translation("repurposedstructures.snowdungeonminheight")
				.define("snowDungeonMinHeight", 35);

			icyDungeonMinHeight = builder
				.translation("repurposedstructures.icydungeonminheight")
				.define("icyDungeonMinHeight", 35);

			swampDungeonMinHeight = builder
				.translation("repurposedstructures.swampdungeonminheight")
				.define("swampDungeonMinHeight", 35);

			endDungeonMinHeight = builder
				.translation("repurposedstructures.enddungeonminheight")
				.define("endDungeonMinHeight", 2);

			netherDungeonMinHeight = builder
				.translation("repurposedstructures.netherdungeonminheight")
				.define("netherDungeonMinHeight", 2);

			oceanDungeonMinHeight = builder
				.translation("repurposedstructures.oceandungeonminheight")
				.define("oceanDungeonMinHeight", 20);

		builder.pop();

		builder.comment("-----------------------------------------------------------------------------------------",
				" Maximum Y height that this dungeon can spawn at.",
				" Note: The dungeon will spawn between min and max y height set in config.",
				" Setting this to below min height config will make dungeon spawn only at min height.");
		builder.push("Max Height");

			badlandsDungeonMaxHeight = builder
				.translation("repurposedstructures.badlandsdungeonmaxheight")
				.define("badlandsDungeonMaxHeight", 255);

			darkForestDungeonMaxHeight = builder
				.translation("repurposedstructures.darkforestdungeonmaxheight")
				.define("darkForestDungeonMaxHeight", 255);

			desertDungeonMaxHeight = builder
				.translation("repurposedstructures.desertdungeonmaxheight")
				.define("desertDungeonMaxHeight", 255);

			deepDungeonMaxHeight = builder
				.translation("repurposedstructures.deepdungeonmaxheight")
				.define("deepDungeonMaxHeight", -10);

			jungleDungeonMaxHeight = builder
				.translation("repurposedstructures.jungledungeonmaxheight")
				.define("jungleDungeonMaxHeight", 255);

			mushroomDungeonMaxHeight = builder
				.translation("repurposedstructures.mushroomdungeonmaxheight")
				.define("mushroomDungeonMaxHeight", 255);

			snowDungeonMaxHeight = builder
				.translation("repurposedstructures.snowdungeonmaxheight")
				.define("snowDungeonMaxHeight", 255);

			icyDungeonMaxHeight = builder
				.translation("repurposedstructures.icydungeonmaxheight")
				.define("icyDungeonMaxHeight", 255);

			swampDungeonMaxHeight = builder
				.translation("repurposedstructures.swampdungeonmaxheight")
				.define("swampDungeonMaxHeight", 255);

			endDungeonMaxHeight = builder
				.translation("repurposedstructures.enddungeonmaxheight")
				.define("endDungeonMaxHeight", 255);

			netherDungeonMaxHeight = builder
				.translation("repurposedstructures.netherdungeonmaxheight")
				.define("netherDungeonMaxHeight", 255);

			oceanDungeonMaxHeight = builder
				.translation("repurposedstructures.oceandungeonmaxheight")
				.define("oceanDungeonMaxHeight", 255);

		builder.pop();
	}
}
