package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;

public class RSOutpostsConfig {
    public static class RSOutpostsConfigValues {
        public ConfigValueListener<Integer> netherBrickOutpostMaxChunkDistance;
        public ConfigValueListener<Integer> warpedOutpostMaxChunkDistance;
        public ConfigValueListener<Integer> crimsonOutpostMaxChunkDistance;
        public ConfigValueListener<Integer> outpostBirchMaxChunkDistance;
        public ConfigValueListener<Integer> outpostJungleMaxChunkDistance;
        public ConfigValueListener<Integer> outpostGiantTreeTaigaMaxChunkDistance;
        public ConfigValueListener<Integer> outpostDesertMaxChunkDistance;
        public ConfigValueListener<Integer> outpostBadlandsMaxChunkDistance;
        public ConfigValueListener<Integer> outpostSnowyMaxChunkDistance;
        public ConfigValueListener<Integer> outpostIcyMaxChunkDistance;
        public ConfigValueListener<Integer> outpostTaigaMaxChunkDistance;
        public ConfigValueListener<Integer> outpostOakMaxChunkDistance;
        public ConfigValueListener<Integer> outpostEndMaxChunkDistance;

        public RSOutpostsConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber) {

            builder.push("Outposts");

            netherBrickOutpostMaxChunkDistance = subscriber.subscribe(builder
                    .comment("\n How rare are Nether Brick Outposts in non-warped Nether biomes.",
                            " 1 for spawning in most chunks and 1001 for none.")
                    .translation("repurposedstructures.config.outposts.netherbrickoutpostmaxchunkdistance")
                    .defineInRange("netherBrickOutpostMaxChunkDistance", 34, 1, 1001));

            warpedOutpostMaxChunkDistance = subscriber.subscribe(builder
                    .comment("\n How rare are Warped Outposts in Warped Nether biomes.",
                            " 1 for spawning in most chunks and 1001 for none.")
                    .translation("repurposedstructures.config.outposts.warpedoutpostmaxchunkdistance")
                    .defineInRange("warpedOutpostMaxChunkDistance", 34, 1, 1001));

            crimsonOutpostMaxChunkDistance = subscriber.subscribe(builder
                    .comment("\n How rare are Crimson Outposts in Warped Nether biomes.",
                            " 1 for spawning in most chunks and 1001 for none.")
                    .translation("repurposedstructures.config.outposts.crimsonoutpostspawnrate")
                    .defineInRange("crimsonOutpostMaxChunkDistance", 34, 1, 1001));

            outpostBirchMaxChunkDistance = subscriber.subscribe(builder
                    .comment("\n How rare are Birch Outposts in Birch Forest biomes.",
                            " 1 for spawning in most chunks and 1001 for none.")
                    .translation("repurposedstructures.config.outposts.outpostbirchmaxchunkdistance")
                    .defineInRange("outpostBirchMaxChunkDistance", 45, 1, 1001));

            outpostJungleMaxChunkDistance = subscriber.subscribe(builder
                    .comment("\n How rare are Jungle Outposts in Jungle biomes.",
                            " 1 for spawning in most chunks and 1001 for none.")
                    .translation("repurposedstructures.config.outposts.outpostjunglemaxchunkdistance")
                    .defineInRange("outpostJungleMaxChunkDistance", 45, 1, 1001));

            outpostGiantTreeTaigaMaxChunkDistance = subscriber.subscribe(builder
                    .comment("\n How rare are Giant Tree Taiga Outposts in Giant Tree Taiga biomes.",
                            " 1 for spawning in most chunks and 1001 for none.")
                    .translation("repurposedstructures.config.outposts.outpostgianttreetaigamaxchunkdistance")
                    .defineInRange("outpostGiantTreeTaigaMaxChunkDistance", 45, 1, 1001));

            outpostDesertMaxChunkDistance = subscriber.subscribe(builder
                    .comment("\n How rare are Desert Outposts in Desert biomes.",
                            " 1 for spawning in most chunks and 1001 for none.")
                    .translation("repurposedstructures.config.outposts.outpostdesertmaxchunkdistance")
                    .defineInRange("outpostDesertMaxChunkDistance", 45, 1, 1001));

            outpostBadlandsMaxChunkDistance = subscriber.subscribe(builder
                    .comment("\n How rare are Badlands Outposts in Badlands biomes.",
                            " 1 for spawning in most chunks and 1001 for none.")
                    .translation("repurposedstructures.config.outposts.outpostbadlandsmaxchunkdistance")
                    .defineInRange("outpostBadlandsMaxChunkDistance", 45, 1, 1001));

            outpostSnowyMaxChunkDistance = subscriber.subscribe(builder
                    .comment("\n How rare are Snowy Outposts in snowy biomes.",
                            " 1 for spawning in most chunks and 1001 for none.")
                    .translation("repurposedstructures.config.outposts.outpostsnowymaxchunkdistance")
                    .defineInRange("outpostSnowyMaxChunkDistance", 45, 1, 1001));

            outpostIcyMaxChunkDistance = subscriber.subscribe(builder
                    .comment("\n How rare are Icy Outposts in icy/extremely cold biomes.",
                            " 1 for spawning in most chunks and 1001 for none.")
                    .translation("repurposedstructures.config.outposts.outposticymaxchunkdistance")
                    .defineInRange("outpostIcyMaxChunkDistance", 41, 1, 1001));

            outpostOakMaxChunkDistance = subscriber.subscribe(builder
                    .comment("\n How rare Taiga Icy Outposts in forest biomes that are not birch or dark forest biomes.",
                            " 1 for spawning in most chunks and 1001 for none.")
                    .translation("repurposedstructures.config.outposts.outpostoakmaxchunkdistance")
                    .defineInRange("outpostOakMaxChunkDistance", 45, 1, 1001));

            outpostTaigaMaxChunkDistance = subscriber.subscribe(builder
                    .comment("\n How rare Taiga Icy Outposts in non-snowy and non-giant taiga biomes.",
                            " 1 for spawning in most chunks and 1001 for none.")
                    .translation("repurposedstructures.config.outposts.outposttaigamaxchunkdistance")
                    .defineInRange("outpostTaigaMaxChunkDistance", 45, 1, 1001));

            netherBrickOutpostMaxChunkDistance = subscriber.subscribe(builder
                    .comment("\n How rare are Nether Brick Outposts in non-warped Nether biomes.",
                            " 1 for spawning in most chunks and 1001 for none.")
                    .translation("repurposedstructures.config.outposts.netherbrickoutpostmaxchunkdistance")
                    .defineInRange("netherBrickOutpostMaxChunkDistance", 34, 1, 1001));

            warpedOutpostMaxChunkDistance = subscriber.subscribe(builder
                    .comment("\n How rare are Warped Outposts in Warped Nether biomes.",
                            " 1 for spawning in most chunks and 1001 for none.")
                    .translation("repurposedstructures.config.outposts.warpedoutpostmaxchunkdistance")
                    .defineInRange("warpedOutpostMaxChunkDistance", 34, 1, 1001));

            crimsonOutpostMaxChunkDistance = subscriber.subscribe(builder
                    .comment("\n How rare are Crimson Outposts in Warped Nether biomes.",
                            " 1 for spawning in most chunks and 1001 for none.")
                    .translation("repurposedstructures.config.outposts.crimsonoutpostspawnrate")
                    .defineInRange("crimsonOutpostMaxChunkDistance", 34, 1, 1001));

            outpostEndMaxChunkDistance = subscriber.subscribe(builder
                    .comment("\n How rare are End Outposts in End biomes.",
                            " 1 for spawning in most chunks and 1001 for none.")
                    .translation("repurposedstructures.config.outposts.outpostendmaxchunkdistance")
                    .defineInRange("outpostEndMaxChunkDistance", 61, 1, 1001));

            builder.pop();
        }
    }
}
