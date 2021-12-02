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
                .translation("repurposedstructures.outpostbirchaveragechunkdistance")
                .defineInRange("outpostBirchAverageChunkDistance", 49, 1, 1001);

        outpostJungleAverageChunkDistance = builder
                .translation("repurposedstructures.outpostjungleaveragechunkdistance")
                .defineInRange("outpostJungleAverageChunkDistance", 49, 1, 1001);

        outpostGiantTreeTaigaAverageChunkDistance = builder
                .translation("repurposedstructures.outpostgianttreetaigaaveragechunkdistance")
                .defineInRange("outpostGiantTreeTaigaAverageChunkDistance", 49, 1, 1001);

        outpostDesertAverageChunkDistance = builder
                .translation("repurposedstructures.outpostdesertaveragechunkdistance")
                .defineInRange("outpostDesertAverageChunkDistance", 49, 1, 1001);

        outpostBadlandsAverageChunkDistance = builder
                .translation("repurposedstructures.outpostbadlandsaveragechunkdistance")
                .defineInRange("outpostBadlandsAverageChunkDistance", 49, 1, 1001);

        outpostSnowyAverageChunkDistance = builder
                .translation("repurposedstructures.outpostsnowyaveragechunkdistance")
                .defineInRange("outpostSnowyAverageChunkDistance", 49, 1, 1001);

        outpostIcyAverageChunkDistance = builder
                .translation("repurposedstructures.outposticyaveragechunkdistance")
                .defineInRange("outpostIcyAverageChunkDistance", 41, 1, 1001);

        outpostOakAverageChunkDistance = builder
                .translation("repurposedstructures.outpostoakaveragechunkdistance")
                .defineInRange("outpostOakAverageChunkDistance", 49, 1, 1001);

        outpostTaigaAverageChunkDistance = builder
                .translation("repurposedstructures.outposttaigaaveragechunkdistance")
                .defineInRange("outpostTaigaAverageChunkDistance", 49, 1, 1001);

        outpostNetherBrickAverageChunkDistance = builder
                .translation("repurposedstructures.netherbrickoutpostaveragechunkdistance")
                .defineInRange("netherBrickOutpostAverageChunkDistance", 34, 1, 1001);

        outpostWarpedAverageChunkDistance = builder
                .translation("repurposedstructures.warpedoutpostaveragechunkdistance")
                .defineInRange("warpedOutpostAverageChunkDistance", 34, 1, 1001);

        outpostCrimsonAverageChunkDistance = builder
                .translation("repurposedstructures.crimsonoutpostspawnrate")
                .defineInRange("crimsonOutpostAverageChunkDistance", 34, 1, 1001);

        outpostEndAverageChunkDistance = builder
                .translation("repurposedstructures.outpostendaveragechunkdistance")
                .defineInRange("outpostEndAverageChunkDistance", 61, 1, 1001);

        builder.pop();
    }
}
