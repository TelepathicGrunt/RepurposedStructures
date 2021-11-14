package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSOutpostsConfig {
    public static final ForgeConfigSpec GENERAL_SPEC;

    public static ForgeConfigSpec.IntValue outpostBirchAverageChunkDistance;
    public static ForgeConfigSpec.IntValue outpostJungleAverageChunkDistance;
    public static ForgeConfigSpec.IntValue outpostGiantTreeTaigaAverageChunkDistance;
    public static ForgeConfigSpec.IntValue outpostDesertAverageChunkDistance;
    public static ForgeConfigSpec.IntValue outpostBadlandsAverageChunkDistance;
    public static ForgeConfigSpec.IntValue outpostSnowyAverageChunkDistance;
    public static ForgeConfigSpec.IntValue outpostIcyAverageChunkDistance;
    public static ForgeConfigSpec.IntValue outpostTaigaAverageChunkDistance;
    public static ForgeConfigSpec.IntValue outpostOakAverageChunkDistance;
    public static ForgeConfigSpec.IntValue outpostNetherBrickAverageChunkDistance;
    public static ForgeConfigSpec.IntValue outpostWarpedAverageChunkDistance;
    public static ForgeConfigSpec.IntValue outpostCrimsonAverageChunkDistance;
    public static ForgeConfigSpec.IntValue outpostEndAverageChunkDistance;

    static {
        ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
        setupConfig(configBuilder);
        GENERAL_SPEC = configBuilder.build();
    }

    private static void setupConfig(ForgeConfigSpec.Builder builder) {

        builder.comment("-----------------------------------------------------------------------------------------",
                " Average distance between spawn attempts for Repurposed Structures Outposts.",
                " Will replace Vanilla Outposts with RS's themed Outposts if both would've been in same biome.",
                " 1 for spawning in most chunks and 1001 for none.",
                " Note: Vanilla Outposts will spawn again if a RS Outpost's entry is set to 1001 for the biome.");
        builder.push("Outposts");

        outpostBirchAverageChunkDistance = builder
                .translation("repurposedstructures.config.outposts.outpostbirchmaxchunkdistance")
                .defineInRange("outpostBirchMaxChunkDistance", 45, 1, 1001);

        outpostJungleAverageChunkDistance = builder
                .translation("repurposedstructures.config.outposts.outpostjunglemaxchunkdistance")
                .defineInRange("outpostJungleMaxChunkDistance", 45, 1, 1001);

        outpostGiantTreeTaigaAverageChunkDistance = builder
                .translation("repurposedstructures.config.outposts.outpostgianttreetaigamaxchunkdistance")
                .defineInRange("outpostGiantTreeTaigaMaxChunkDistance", 45, 1, 1001);

        outpostDesertAverageChunkDistance = builder
                .translation("repurposedstructures.config.outposts.outpostdesertmaxchunkdistance")
                .defineInRange("outpostDesertMaxChunkDistance", 45, 1, 1001);

        outpostBadlandsAverageChunkDistance = builder
                .translation("repurposedstructures.config.outposts.outpostbadlandsmaxchunkdistance")
                .defineInRange("outpostBadlandsMaxChunkDistance", 45, 1, 1001);

        outpostSnowyAverageChunkDistance = builder
                .translation("repurposedstructures.config.outposts.outpostsnowymaxchunkdistance")
                .defineInRange("outpostSnowyMaxChunkDistance", 45, 1, 1001);

        outpostIcyAverageChunkDistance = builder
                .translation("repurposedstructures.config.outposts.outposticymaxchunkdistance")
                .defineInRange("outpostIcyMaxChunkDistance", 41, 1, 1001);

        outpostOakAverageChunkDistance = builder
                .translation("repurposedstructures.config.outposts.outpostoakmaxchunkdistance")
                .defineInRange("outpostOakMaxChunkDistance", 45, 1, 1001);

        outpostTaigaAverageChunkDistance = builder
                .translation("repurposedstructures.config.outposts.outposttaigamaxchunkdistance")
                .defineInRange("outpostTaigaMaxChunkDistance", 45, 1, 1001);

        outpostNetherBrickAverageChunkDistance = builder
                .translation("repurposedstructures.config.outposts.netherbrickoutpostmaxchunkdistance")
                .defineInRange("netherBrickOutpostMaxChunkDistance", 34, 1, 1001);

        outpostWarpedAverageChunkDistance = builder
                .translation("repurposedstructures.config.outposts.warpedoutpostmaxchunkdistance")
                .defineInRange("warpedOutpostMaxChunkDistance", 34, 1, 1001);

        outpostCrimsonAverageChunkDistance = builder
                .translation("repurposedstructures.config.outposts.crimsonoutpostspawnrate")
                .defineInRange("crimsonOutpostMaxChunkDistance", 34, 1, 1001);

        outpostEndAverageChunkDistance = builder
                .translation("repurposedstructures.config.outposts.outpostendmaxchunkdistance")
                .defineInRange("outpostEndMaxChunkDistance", 61, 1, 1001);

        builder.pop();
    }
}
