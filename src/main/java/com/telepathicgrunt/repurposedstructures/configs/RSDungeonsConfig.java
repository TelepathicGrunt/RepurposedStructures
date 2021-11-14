package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSDungeonsConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;

	public static ForgeConfigSpec.BooleanValue shulkerBoxInEndDungeons;

	public static ForgeConfigSpec.IntValue badlandsDungeonAttemptsPerChunk;
	public static ForgeConfigSpec.IntValue darkForestDungeonAttemptsPerChunk;
	public static ForgeConfigSpec.IntValue desertDungeonAttemptsPerChunk;
	public static ForgeConfigSpec.IntValue jungleDungeonAttemptsPerChunk;
	public static ForgeConfigSpec.IntValue mushroomDungeonAttemptsPerChunk;
	public static ForgeConfigSpec.IntValue snowDungeonAttemptsPerChunk;
	public static ForgeConfigSpec.IntValue icyDungeonAttemptsPerChunk;
	public static ForgeConfigSpec.IntValue swampDungeonAttemptsPerChunk;
	public static ForgeConfigSpec.IntValue endDungeonAttemptsPerChunk;
	public static ForgeConfigSpec.IntValue netherDungeonAttemptsPerChunk;
	public static ForgeConfigSpec.IntValue oceanDungeonAttemptsPerChunk;

	public static ForgeConfigSpec.IntValue badlandsDungeonMinHeight;
	public static ForgeConfigSpec.IntValue darkForestDungeonMinHeight;
	public static ForgeConfigSpec.IntValue desertDungeonMinHeight;
	public static ForgeConfigSpec.IntValue jungleDungeonMinHeight;
	public static ForgeConfigSpec.IntValue mushroomDungeonMinHeight;
	public static ForgeConfigSpec.IntValue snowDungeonMinHeight;
	public static ForgeConfigSpec.IntValue icyDungeonMinHeight;
	public static ForgeConfigSpec.IntValue swampDungeonMinHeight;
	public static ForgeConfigSpec.IntValue endDungeonMinHeight;
	public static ForgeConfigSpec.IntValue netherDungeonMinHeight;
	public static ForgeConfigSpec.IntValue oceanDungeonMinHeight;

	public static ForgeConfigSpec.IntValue badlandsDungeonMaxHeight;
	public static ForgeConfigSpec.IntValue darkForestDungeonMaxHeight;
	public static ForgeConfigSpec.IntValue desertDungeonMaxHeight;
	public static ForgeConfigSpec.IntValue jungleDungeonMaxHeight;
	public static ForgeConfigSpec.IntValue mushroomDungeonMaxHeight;
	public static ForgeConfigSpec.IntValue snowDungeonMaxHeight;
	public static ForgeConfigSpec.IntValue icyDungeonMaxHeight;
	public static ForgeConfigSpec.IntValue swampDungeonMaxHeight;
	public static ForgeConfigSpec.IntValue endDungeonMaxHeight;
	public static ForgeConfigSpec.IntValue netherDungeonMaxHeight;
	public static ForgeConfigSpec.IntValue oceanDungeonMaxHeight;

	static {
		ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
		setupConfig(configBuilder);
		GENERAL_SPEC = configBuilder.build();
	}

	private static void setupConfig(ForgeConfigSpec.Builder builder) {

		shulkerBoxInEndDungeons = builder
				.comment("\n Whether End Dungeons spawns Shulker Boxes. if false, spawns regular Chests instead.",
						"(Configuredfeatures are unable to be overridden by datapack due to bad Forge hook placement. Hence this config option)")
				.translation("repurposedstructures.config.dungeons.shulkerboxinenddungeons")
				.define("shulkerBoxInEndDungeons", true);

		builder.comment("-----------------------------------------------------------------------------------------",
				" How many positions Repurposed Structures Dungeons will pick per chunk to check if it can spawn at those positions.",
				" Will replace Vanilla Dungeons with RS's themed dungeons if both would've been in same biome. (except for RS's Ocean Dungeon)",
				" 0 for no Dungeons at all and 1000 for max Dungeon spawnrate.",
				" Note: Vanilla Dungeons will spawn again if a RS Dungeon's entry is set to 0 for the biome.");
		builder.push("AttemptsPerChunk");

			badlandsDungeonAttemptsPerChunk = builder
					.translation("repurposedstructures.config.dungeons.badlandsdungeonattemptsperchunk")
					.defineInRange("badlandsDungeonAttemptsPerChunk", 8, 0, 1000);

			darkForestDungeonAttemptsPerChunk = builder
				.translation("repurposedstructures.config.dungeons.darkforestdungeonattemptsperchunk")
				.defineInRange("darkForestDungeonAttemptsPerChunk", 8, 0, 1000);

			desertDungeonAttemptsPerChunk = builder
				.translation("repurposedstructures.config.dungeons.desertdungeonattemptsperchunk")
				.defineInRange("desertDungeonAttemptsPerChunk", 8, 0, 1000);

			jungleDungeonAttemptsPerChunk = builder
				.translation("repurposedstructures.config.dungeons.jungledungeonattemptsperchunk")
				.defineInRange("jungleDungeonAttemptsPerChunk", 8, 0, 1000);

			mushroomDungeonAttemptsPerChunk = builder
				.translation("repurposedstructures.config.dungeons.mushroomdungeonattemptsperchunk")
				.defineInRange("mushroomDungeonAttemptsPerChunk", 8, 0, 1000);

			snowDungeonAttemptsPerChunk = builder
				.translation("repurposedstructures.config.dungeons.snowdungeonattemptsperchunk")
				.defineInRange("snowDungeonAttemptsPerChunk", 8, 0, 1000);

			icyDungeonAttemptsPerChunk = builder
				.translation("repurposedstructures.config.dungeons.snowdungeonattemptsperchunk")
				.defineInRange("snowDungeonAttemptsPerChunk", 8, 0, 1000);

			swampDungeonAttemptsPerChunk = builder
				.translation("repurposedstructures.config.dungeons.swampdungeonattemptsperchunk")
				.defineInRange("swampDungeonAttemptsPerChunk", 8, 0, 1000);

			endDungeonAttemptsPerChunk = builder
				.translation("repurposedstructures.config.dungeons.enddungeonattemptsperchunk")
				.defineInRange("endDungeonAttemptsPerChunk", 12, 0, 1000);

			netherDungeonAttemptsPerChunk = builder
				.translation("repurposedstructures.config.dungeons.netherdungeonattemptsperchunk")
				.defineInRange("netherDungeonAttemptsPerChunk", 12, 0, 1000);

			oceanDungeonAttemptsPerChunk = builder
				.translation("repurposedstructures.config.dungeons.oceandungeonattemptsperchunk")
				.defineInRange("oceanDungeonAttemptsPerChunk", 4, 0, 1000);


		builder.pop();


		builder.comment("-----------------------------------------------------------------------------------------",
				" Minimum Y height that this dungeon can spawn at.",
				" Note: The dungeon will spawn between min and max y height set in config.");
		builder.push("Min Height");

			badlandsDungeonMinHeight = builder
				.translation("repurposedstructures.config.dungeons.badlandsdungeonminheight")
				.defineInRange("badlandsDungeonMinHeight", 2, 2, 255);

			darkForestDungeonMinHeight = builder
				.translation("repurposedstructures.config.dungeons.darkforestdungeonminheight")
				.defineInRange("darkForestDungeonMinHeight", 2, 2, 255);

			desertDungeonMinHeight = builder
				.translation("repurposedstructures.config.dungeons.desertdungeonminheight")
				.defineInRange("desertDungeonMinHeight", 2, 2, 255);

			jungleDungeonMinHeight = builder
				.translation("repurposedstructures.config.dungeons.jungledungeonminheight")
				.defineInRange("jungleDungeonMinHeight", 2, 2, 255);

			mushroomDungeonMinHeight = builder
				.translation("repurposedstructures.config.dungeons.mushroomdungeonminheight")
				.defineInRange("mushroomDungeonMinHeight", 2, 2, 255);

			snowDungeonMinHeight = builder
				.translation("repurposedstructures.config.dungeons.snowdungeonminheight")
				.defineInRange("snowDungeonMinHeight", 2, 2, 255);

			icyDungeonMinHeight = builder
				.translation("repurposedstructures.config.dungeons.icydungeonminheight")
				.defineInRange("icyDungeonMinHeight", 2, 2, 255);

			swampDungeonMinHeight = builder
				.translation("repurposedstructures.config.dungeons.swampdungeonminheight")
				.defineInRange("swampDungeonMinHeight", 2, 2, 255);

			endDungeonMinHeight = builder
				.translation("repurposedstructures.config.dungeons.enddungeonminheight")
				.defineInRange("endDungeonMinHeight", 2, 2, 255);

			netherDungeonMinHeight = builder
				.translation("repurposedstructures.config.dungeons.netherdungeonminheight")
				.defineInRange("netherDungeonMinHeight", 2, 2, 255);

			oceanDungeonMinHeight = builder
				.translation("repurposedstructures.config.dungeons.oceandungeonminheight")
				.defineInRange("oceanDungeonMinHeight", 3, 3, 255);

		builder.pop();

		builder.comment("-----------------------------------------------------------------------------------------",
				" Maximum Y height that this dungeon can spawn at.",
				" Note: The dungeon will spawn between min and max y height set in config.",
				" Setting this to below min height config will make dungeon spawn only at min height.");
		builder.push("Max Height");

			badlandsDungeonMaxHeight = builder
				.translation("repurposedstructures.config.dungeons.badlandsdungeonmaxheight")
				.defineInRange("badlandsDungeonMaxHeight", 255, 2, 255);

			darkForestDungeonMaxHeight = builder
				.translation("repurposedstructures.config.dungeons.darkforestdungeonmaxheight")
				.defineInRange("darkForestDungeonMaxHeight", 255, 2, 255);

			desertDungeonMaxHeight = builder
				.translation("repurposedstructures.config.dungeons.desertdungeonmaxheight")
				.defineInRange("desertDungeonMaxHeight", 255, 2, 255);

			jungleDungeonMaxHeight = builder
				.translation("repurposedstructures.config.dungeons.jungledungeonmaxheight")
				.defineInRange("jungleDungeonMaxHeight", 255, 2, 255);

			mushroomDungeonMaxHeight = builder
				.translation("repurposedstructures.config.dungeons.mushroomdungeonmaxheight")
				.defineInRange("mushroomDungeonMaxHeight", 255, 2, 255);

			snowDungeonMaxHeight = builder
				.translation("repurposedstructures.config.dungeons.snowdungeonmaxheight")
				.defineInRange("snowDungeonMaxHeight", 255, 2, 255);

			icyDungeonMaxHeight = builder
				.translation("repurposedstructures.config.dungeons.icydungeonmaxheight")
				.defineInRange("icyDungeonMaxHeight", 255, 2, 255);

			swampDungeonMaxHeight = builder
				.translation("repurposedstructures.config.dungeons.swampdungeonmaxheight")
				.defineInRange("swampDungeonMaxHeight", 255, 2, 255);

			endDungeonMaxHeight = builder
				.translation("repurposedstructures.config.dungeons.enddungeonmaxheight")
				.defineInRange("endDungeonMaxHeight", 255, 2, 255);

			netherDungeonMaxHeight = builder
				.translation("repurposedstructures.config.dungeons.netherdungeonmaxheight")
				.defineInRange("netherDungeonMaxHeight", 255, 2, 255);

			oceanDungeonMaxHeight = builder
				.translation("repurposedstructures.config.dungeons.oceandungeonmaxheight")
				.defineInRange("oceanDungeonMaxHeight", 255, 3, 255);

		builder.pop();
	}
}
