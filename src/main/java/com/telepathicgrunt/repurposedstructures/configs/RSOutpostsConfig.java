package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSOutpostsConfig {
    public static final ForgeConfigSpec GENERAL_SPEC;
    
    public static ForgeConfigSpec.IntValue netherBrickOutpostMaxChunkDistance;
    public static ForgeConfigSpec.IntValue warpedOutpostMaxChunkDistance;
    public static ForgeConfigSpec.IntValue crimsonOutpostMaxChunkDistance;
    public static ForgeConfigSpec.IntValue outpostBirchMaxChunkDistance;
    public static ForgeConfigSpec.IntValue outpostJungleMaxChunkDistance;
    public static ForgeConfigSpec.IntValue outpostGiantTreeTaigaMaxChunkDistance;
    public static ForgeConfigSpec.IntValue outpostDesertMaxChunkDistance;
    public static ForgeConfigSpec.IntValue outpostBadlandsMaxChunkDistance;
    public static ForgeConfigSpec.IntValue outpostSnowyMaxChunkDistance;
    public static ForgeConfigSpec.IntValue outpostIcyMaxChunkDistance;
    public static ForgeConfigSpec.IntValue outpostTaigaMaxChunkDistance;
    public static ForgeConfigSpec.IntValue outpostOakMaxChunkDistance;
    public static ForgeConfigSpec.IntValue outpostEndMaxChunkDistance;

    static {
        ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
        setupConfig(configBuilder);
        GENERAL_SPEC = configBuilder.build();
    }

    private static void setupConfig(ForgeConfigSpec.Builder builder) {

        builder.push("Outposts");

        netherBrickOutpostMaxChunkDistance = builder
                .comment("\n How rare are Nether Brick Outposts in non-warped Nether biomes.",
                        " 1 for spawning in most chunks and 1001 for none.")
                .translation("repurposedstructures.config.outposts.netherbrickoutpostmaxchunkdistance")
                .defineInRange("netherBrickOutpostMaxChunkDistance", 34, 1, 1001);

        warpedOutpostMaxChunkDistance = builder
                .comment("\n How rare are Warped Outposts in Warped Nether biomes.",
                        " 1 for spawning in most chunks and 1001 for none.")
                .translation("repurposedstructures.config.outposts.warpedoutpostmaxchunkdistance")
                .defineInRange("warpedOutpostMaxChunkDistance", 34, 1, 1001);

        crimsonOutpostMaxChunkDistance = builder
                .comment("\n How rare are Crimson Outposts in Warped Nether biomes.",
                        " 1 for spawning in most chunks and 1001 for none.")
                .translation("repurposedstructures.config.outposts.crimsonoutpostspawnrate")
                .defineInRange("crimsonOutpostMaxChunkDistance", 34, 1, 1001);

        outpostBirchMaxChunkDistance = builder
                .comment("\n How rare are Birch Outposts in Birch Forest biomes.",
                        " 1 for spawning in most chunks and 1001 for none.")
                .translation("repurposedstructures.config.outposts.outpostbirchmaxchunkdistance")
                .defineInRange("outpostBirchMaxChunkDistance", 45, 1, 1001);

        outpostJungleMaxChunkDistance = builder
                .comment("\n How rare are Jungle Outposts in Jungle biomes.",
                        " 1 for spawning in most chunks and 1001 for none.")
                .translation("repurposedstructures.config.outposts.outpostjunglemaxchunkdistance")
                .defineInRange("outpostJungleMaxChunkDistance", 45, 1, 1001);

        outpostGiantTreeTaigaMaxChunkDistance = builder
                .comment("\n How rare are Giant Tree Taiga Outposts in Giant Tree Taiga biomes.",
                        " 1 for spawning in most chunks and 1001 for none.")
                .translation("repurposedstructures.config.outposts.outpostgianttreetaigamaxchunkdistance")
                .defineInRange("outpostGiantTreeTaigaMaxChunkDistance", 45, 1, 1001);

        outpostDesertMaxChunkDistance = builder
                .comment("\n How rare are Desert Outposts in Desert biomes.",
                        " 1 for spawning in most chunks and 1001 for none.")
                .translation("repurposedstructures.config.outposts.outpostdesertmaxchunkdistance")
                .defineInRange("outpostDesertMaxChunkDistance", 45, 1, 1001);

        outpostBadlandsMaxChunkDistance = builder
                .comment("\n How rare are Badlands Outposts in Badlands biomes.",
                        " 1 for spawning in most chunks and 1001 for none.")
                .translation("repurposedstructures.config.outposts.outpostbadlandsmaxchunkdistance")
                .defineInRange("outpostBadlandsMaxChunkDistance", 45, 1, 1001);

        outpostSnowyMaxChunkDistance = builder
                .comment("\n How rare are Snowy Outposts in snowy biomes.",
                        " 1 for spawning in most chunks and 1001 for none.")
                .translation("repurposedstructures.config.outposts.outpostsnowymaxchunkdistance")
                .defineInRange("outpostSnowyMaxChunkDistance", 45, 1, 1001);

        outpostIcyMaxChunkDistance = builder
                .comment("\n How rare are Icy Outposts in icy/extremely cold biomes.",
                        " 1 for spawning in most chunks and 1001 for none.")
                .translation("repurposedstructures.config.outposts.outposticymaxchunkdistance")
                .defineInRange("outpostIcyMaxChunkDistance", 41, 1, 1001);

        outpostOakMaxChunkDistance = builder
                .comment("\n How rare Taiga Icy Outposts in forest biomes that are not birch or dark forest biomes.",
                        " 1 for spawning in most chunks and 1001 for none.")
                .translation("repurposedstructures.config.outposts.outpostoakmaxchunkdistance")
                .defineInRange("outpostOakMaxChunkDistance", 45, 1, 1001);

        outpostTaigaMaxChunkDistance = builder
                .comment("\n How rare Taiga Icy Outposts in non-snowy and non-giant taiga biomes.",
                        " 1 for spawning in most chunks and 1001 for none.")
                .translation("repurposedstructures.config.outposts.outposttaigamaxchunkdistance")
                .defineInRange("outpostTaigaMaxChunkDistance", 45, 1, 1001);

        netherBrickOutpostMaxChunkDistance = builder
                .comment("\n How rare are Nether Brick Outposts in non-warped Nether biomes.",
                        " 1 for spawning in most chunks and 1001 for none.")
                .translation("repurposedstructures.config.outposts.netherbrickoutpostmaxchunkdistance")
                .defineInRange("netherBrickOutpostMaxChunkDistance", 34, 1, 1001);

        warpedOutpostMaxChunkDistance = builder
                .comment("\n How rare are Warped Outposts in Warped Nether biomes.",
                        " 1 for spawning in most chunks and 1001 for none.")
                .translation("repurposedstructures.config.outposts.warpedoutpostmaxchunkdistance")
                .defineInRange("warpedOutpostMaxChunkDistance", 34, 1, 1001);

        crimsonOutpostMaxChunkDistance = builder
                .comment("\n How rare are Crimson Outposts in Warped Nether biomes.",
                        " 1 for spawning in most chunks and 1001 for none.")
                .translation("repurposedstructures.config.outposts.crimsonoutpostspawnrate")
                .defineInRange("crimsonOutpostMaxChunkDistance", 34, 1, 1001);

        outpostEndMaxChunkDistance = builder
                .comment("\n How rare are End Outposts in End biomes.",
                        " 1 for spawning in most chunks and 1001 for none.")
                .translation("repurposedstructures.config.outposts.outpostendmaxchunkdistance")
                .defineInRange("outpostEndMaxChunkDistance", 61, 1, 1001);

        builder.pop();
    }
}
