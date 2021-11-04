package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSMineshaftsConfig {
    public static final ForgeConfigSpec GENERAL_SPEC;

    public static ForgeConfigSpec.DoubleValue birchMineshaftSpawnrate;
    public static ForgeConfigSpec.DoubleValue jungleMineshaftSpawnrate;
    public static ForgeConfigSpec.DoubleValue desertMineshaftSpawnrate;
    public static ForgeConfigSpec.DoubleValue stoneMineshaftSpawnrate;
    public static ForgeConfigSpec.DoubleValue savannaMineshaftSpawnrate;
    public static ForgeConfigSpec.DoubleValue icyMineshaftSpawnrate;
    public static ForgeConfigSpec.DoubleValue oceanMineshaftSpawnrate;
    public static ForgeConfigSpec.DoubleValue taigaMineshaftSpawnrate;
    public static ForgeConfigSpec.DoubleValue darkForestMineshaftSpawnrate;
    public static ForgeConfigSpec.DoubleValue swampMineshaftSpawnrate;
    public static ForgeConfigSpec.DoubleValue endMineshaftSpawnrate;
    public static ForgeConfigSpec.DoubleValue netherMineshaftSpawnrate;
    public static ForgeConfigSpec.DoubleValue crimsonMineshaftSpawnrate;
    public static ForgeConfigSpec.DoubleValue warpedMineshaftSpawnrate;

    public static ForgeConfigSpec.IntValue birchMineshaftMinHeight;
    public static ForgeConfigSpec.IntValue jungleMineshaftMinHeight;
    public static ForgeConfigSpec.IntValue desertMineshaftMinHeight;
    public static ForgeConfigSpec.IntValue stoneMineshaftMinHeight;
    public static ForgeConfigSpec.IntValue savannaMineshaftMinHeight;
    public static ForgeConfigSpec.IntValue icyMineshaftMinHeight;
    public static ForgeConfigSpec.IntValue oceanMineshaftMinHeight;
    public static ForgeConfigSpec.IntValue taigaMineshaftMinHeight;
    public static ForgeConfigSpec.IntValue darkForestMineshaftMinHeight;
    public static ForgeConfigSpec.IntValue swampMineshaftMinHeight;
    public static ForgeConfigSpec.IntValue netherMineshaftMinHeight;
    public static ForgeConfigSpec.IntValue crimsonMineshaftMinHeight;
    public static ForgeConfigSpec.IntValue warpedMineshaftMinHeight;

    public static ForgeConfigSpec.IntValue birchMineshaftMaxHeight;
    public static ForgeConfigSpec.IntValue jungleMineshaftMaxHeight;
    public static ForgeConfigSpec.IntValue desertMineshaftMaxHeight;
    public static ForgeConfigSpec.IntValue stoneMineshaftMaxHeight;
    public static ForgeConfigSpec.IntValue savannaMineshaftMaxHeight;
    public static ForgeConfigSpec.IntValue icyMineshaftMaxHeight;
    public static ForgeConfigSpec.IntValue oceanMineshaftMaxHeight;
    public static ForgeConfigSpec.IntValue taigaMineshaftMaxHeight;
    public static ForgeConfigSpec.IntValue darkForestMineshaftMaxHeight;
    public static ForgeConfigSpec.IntValue swampMineshaftMaxHeight;
    public static ForgeConfigSpec.IntValue netherMineshaftMaxHeight;
    public static ForgeConfigSpec.IntValue crimsonMineshaftMaxHeight;
    public static ForgeConfigSpec.IntValue warpedMineshaftMaxHeight;

    public static ForgeConfigSpec.IntValue birchMineshaftSize;
    public static ForgeConfigSpec.IntValue jungleMineshaftSize;
    public static ForgeConfigSpec.IntValue desertMineshaftSize;
    public static ForgeConfigSpec.IntValue stoneMineshaftSize;
    public static ForgeConfigSpec.IntValue savannaMineshaftSize;
    public static ForgeConfigSpec.IntValue icyMineshaftSize;
    public static ForgeConfigSpec.IntValue oceanMineshaftSize;
    public static ForgeConfigSpec.IntValue taigaMineshaftSize;
    public static ForgeConfigSpec.IntValue darkForestMineshaftSize;
    public static ForgeConfigSpec.IntValue swampMineshaftSize;
    public static ForgeConfigSpec.IntValue endMineshaftSize;
    public static ForgeConfigSpec.IntValue netherMineshaftSize;
    public static ForgeConfigSpec.IntValue crimsonMineshaftSize;
    public static ForgeConfigSpec.IntValue warpedMineshaftSize;

    public static ForgeConfigSpec.IntValue endMineshaftMinIslandThickness;

    static {
        ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
        setupConfig(configBuilder);
        GENERAL_SPEC = configBuilder.build();
    }

    private static void setupConfig(ForgeConfigSpec.Builder builder) {
        builder.push("Spawnrate");

        birchMineshaftSpawnrate = builder
                .comment("\n Controls how often Mineshafts will spawn. 0 is no Mineshafts while 1000 is max spawnrate.",
                        " Replace Mineshafts in Birch biomes with a Birch themed Mineshaft.",
                        " Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
                .translation("repurposedstructures.config.mineshaft.birchmineshaftspawnrate")
                .defineInRange("birchMineshaftSpawnrate", 40D, 0, 1000);

        jungleMineshaftSpawnrate = builder
                .comment("\n Controls how often Mineshafts will spawn. 0 is no Mineshafts while 1000 is max spawnrate.",
                        " Replace Mineshafts in Jungle biomes with a Jungle themed Mineshaft.",
                        " Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
                .translation("repurposedstructures.config.mineshaft.junglemineshaftspawnrate")
                .defineInRange("jungleMineshaftSpawnrate", 40D, 0, 1000);

        desertMineshaftSpawnrate = builder
                .comment("\n Controls how often Mineshafts will spawn. 0 is no Mineshafts while 1000 is max spawnrate.",
                        " Replace Mineshafts in Desert biomes with a Desert themed Mineshaft.",
                        " Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
                .translation("repurposedstructures.config.mineshaft.desertmineshaftspawnrate")
                .defineInRange("desertMineshaftSpawnrate", 40D, 0, 1000);

        stoneMineshaftSpawnrate = builder
                .comment("\n Controls how often Mineshafts will spawn. 0 is no Mineshafts while 1000 is max spawnrate.",
                        " Replace Mineshafts in Mountain (Extreme Hills) biomes with a Stone themed Mineshaft.",
                        " Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
                .translation("repurposedstructures.config.mineshaft.stonemineshaftspawnrate")
                .defineInRange("stoneMineshaftSpawnrate", 40D, 0, 1000);

        savannaMineshaftSpawnrate = builder
                .comment("\n Controls how often Mineshafts will spawn. 0 is no Mineshafts while 1000 is max spawnrate.",
                        " Replace Mineshafts in Savanna biomes with a Savanna themed Mineshaft.",
                        " Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
                .translation("repurposedstructures.config.mineshaft.savannamineshaftspawnrate")
                .defineInRange("savannaMineshaftSpawnrate", 40D, 0, 1000);

        icyMineshaftSpawnrate = builder
                .comment("\n Controls how often Mineshafts will spawn. 0 is no Mineshafts while 1000 is max spawnrate.",
                        " Replace Mineshafts in Snowy/Icy biomes with an Ice themed Mineshaft.",
                        " Note: Snowy Taiga Biomes will get Ice Mineshaft instead of Taiga theme.",
                        " Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
                .translation("repurposedstructures.config.mineshaft.icymineshaftspawnrate")
                .defineInRange("icyMineshaftSpawnrate", 40D, 0, 1000);

        oceanMineshaftSpawnrate = builder
                .comment("\n Controls how often Mineshafts will spawn. 0 is no Mineshafts while 1000 is max spawnrate.",
                        " Replace Mineshafts in Ocean biomes with an Ocean themed Mineshaft.",
                        " Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
                .translation("repurposedstructures.config.mineshaft.oceanmineshaftspawnrate")
                .defineInRange("oceanMineshaftSpawnrate", 40D, 0, 1000);

        taigaMineshaftSpawnrate = builder
                .comment("\n Controls how often Mineshafts will spawn. 0 is no Mineshafts while 1000 is max spawnrate.",
                        " Replace Mineshafts in Taiga biomes with a Taiga themed Mineshaft.",
                        " Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
                .translation("repurposedstructures.config.mineshaft.taigamineshaftspawnrate")
                .defineInRange("taigaMineshaftSpawnrate", 40D, 0, 1000);

        darkForestMineshaftSpawnrate = builder
                .comment("\n Controls how often Mineshafts will spawn. 0 is no Mineshafts while 1000 is max spawnrate.",
                        " Replace Mineshafts in Dark Forests with a dark oak themed Mineshaft.",
                        " Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
                .translation("repurposedstructures.config.mineshaft.darkforestmineshaftspawnrate")
                .defineInRange("darkForestMineshaftSpawnrate", 40D, 0, 1000);

        swampMineshaftSpawnrate = builder
                .comment("\n Controls how often Mineshafts will spawn. 0 is no Mineshafts while 1000 is max spawnrate.",
                        " Replace Mineshafts in Swamps with a swampy themed Mineshaft.",
                        " Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
                .translation("repurposedstructures.config.mineshaft.swampmineshaftspawnrate")
                .defineInRange("swampMineshaftSpawnrate", 40D, 0, 1000);

        endMineshaftSpawnrate = builder
                .comment("\n Controls how often Mineshafts will spawn. 0 for no Mineshafts and 1000 for max spawnrate.",
                        " Adds End themed Mineshafts to End biomes outside the Enderdragon island.")
                .translation("repurposedstructures.config.mineshaft.endmineshaftspawnrate")
                .defineInRange("endMineshaftSpawnrate", 60D, 0, 1000);

        netherMineshaftSpawnrate = builder
                .comment("\n Controls how often Mineshafts will spawn. 0 for no Mineshafts and 1000 for max spawnrate.",
                        " Adds Nether themed Mineshafts to non-crimson and non-warped Nether biomes.")
                .translation("repurposedstructures.config.mineshaft.nethermineshaftspawnrate")
                .defineInRange("netherMineshaftSpawnrate", 40D, 0, 1000);

        crimsonMineshaftSpawnrate = builder
                .comment("\n Controls how often Mineshafts will spawn. 0 for no Mineshafts and 1000 for max spawnrate.",
                        " Adds Crimson themed Mineshafts to Crimson Nether biomes.")
                .translation("repurposedstructures.config.mineshaft.crimsonmineshaftspawnrate")
                .defineInRange("crimsonMineshaftSpawnrate", 40D, 0, 1000);

        warpedMineshaftSpawnrate = builder
                .comment("\n Controls how often Mineshafts will spawn. 0 for no Mineshafts and 1000 for max spawnrate.",
                        " Adds Warped themed Mineshafts to Warped Nether biomes.")
                .translation("repurposedstructures.config.mineshaft.warpedmineshaftspawnrate")
                .defineInRange("warpedMineshaftSpawnrate", 40D, 0, 1000);

        builder.pop();

        builder.push("Min height");

        birchMineshaftMinHeight = builder
                .comment("\n Minimum Y height that this mineshaft can spawn at.",
                        " Note: The mineshaft will spawn between min and max y height set in config.",
                        " Setting this to below min height config will make mineshaft spawn only at min height.")
                .translation("repurposedstructures.config.mineshaft.birchmineshaftminheight")
                .defineInRange("birchMineshaftMinHeight", 8, 5, 255);

        jungleMineshaftMinHeight = builder
                .comment("\n Minimum Y height that this mineshaft can spawn at.",
                        " Note: The mineshaft will spawn between min and max y height set in config.")
                .translation("repurposedstructures.config.mineshaft.junglemineshaftminheight")
                .defineInRange("jungleMineshaftMinHeight", 8, 5, 255);

        desertMineshaftMinHeight = builder
                .comment("\n Minimum Y height that this mineshaft can spawn at.",
                        " Note: The mineshaft will spawn between min and max y height set in config.")
                .translation("repurposedstructures.config.mineshaft.desertmineshaftminheight")
                .defineInRange("desertMineshaftMinHeight", 8, 5, 255);

        stoneMineshaftMinHeight = builder
                .comment("\n Minimum Y height that this mineshaft can spawn at.",
                        " Note: The mineshaft will spawn between min and max y height set in config.")
                .translation("repurposedstructures.config.mineshaft.stonemineshaftminheight")
                .defineInRange("stoneMineshaftMinHeight", 8, 5, 255);

        savannaMineshaftMinHeight = builder
                .comment("\n Minimum Y height that this mineshaft can spawn at.",
                        " Note: The mineshaft will spawn between min and max y height set in config.")
                .translation("repurposedstructures.config.mineshaft.savannamineshaftminheight")
                .defineInRange("savannaMineshaftMinHeight", 8, 5, 255);

        icyMineshaftMinHeight = builder
                .comment("\n Minimum Y height that this mineshaft can spawn at.",
                        " Note: The mineshaft will spawn between min and max y height set in config.")
                .translation("repurposedstructures.config.mineshaft.icymineshaftminheight")
                .defineInRange("icyMineshaftMinHeight", 8, 5, 255);

        oceanMineshaftMinHeight = builder
                .comment("\n Minimum Y height that this mineshaft can spawn at.",
                        " Note: The mineshaft will spawn between min and max y height set in config.")
                .translation("repurposedstructures.config.mineshaft.oceanmineshaftminheight")
                .defineInRange("oceanMineshaftMinHeight", 8, 5, 255);

        taigaMineshaftMinHeight = builder
                .comment("\n Minimum Y height that this mineshaft can spawn at.",
                        " Note: The mineshaft will spawn between min and max y height set in config.")
                .translation("repurposedstructures.config.mineshaft.taigamineshaftminheight")
                .defineInRange("taigaMineshaftMinHeight", 8, 5, 255);

        darkForestMineshaftMinHeight = builder
                .comment("\n Minimum Y height that this mineshaft can spawn at.",
                        " Note: The mineshaft will spawn between min and max y height set in config.")
                .translation("repurposedstructures.config.mineshaft.darkforestmineshaftminheight")
                .defineInRange("darkForestMineshaftMinHeight", 8, 5, 255);

        swampMineshaftMinHeight = builder
                .comment("\n Minimum Y height that this mineshaft can spawn at.",
                        " Note: The mineshaft will spawn between min and max y height set in config.")
                .translation("repurposedstructures.config.mineshaft.swampmineshaftminheight")
                .defineInRange("swampMineshaftMinHeight", 8, 5, 255);

        netherMineshaftMinHeight = builder
                .comment("\n Minimum Y height that this mineshaft can spawn at.",
                        " Note: The mineshaft will spawn between min and max y height set in config.")
                .translation("repurposedstructures.config.mineshaft.nethermineshaftminheight")
                .defineInRange("netherMineshaftMinHeight", 6, 5, 255);

        crimsonMineshaftMinHeight = builder
                .comment("\n Minimum Y height that this mineshaft can spawn at.",
                        " Note: The mineshaft will spawn between min and max y height set in config.")
                .translation("repurposedstructures.config.mineshaft.crimsonmineshaftminheight")
                .defineInRange("crimsonMineshaftMinHeight", 6, 5, 255);

        warpedMineshaftMinHeight = builder
                .comment("\n Minimum Y height that this mineshaft can spawn at.",
                        " Note: The mineshaft will spawn between min and max y height set in config.")
                .translation("repurposedstructures.config.mineshaft.warpedmineshaftminheight")
                .defineInRange("warpedMineshaftMinHeight", 6, 5, 255);

        builder.pop();

        builder.push("Max height");

        birchMineshaftMaxHeight = builder
                .comment("\n Minimum Y height that this mineshaft can spawn at.",
                        " Note: The mineshaft will spawn between min and max y height set in config.",
                        " Setting this to below min height config will make mineshaft spawn only at min height.")
                .translation("repurposedstructures.config.mineshaft.birchmineshaftmaxheight")
                .defineInRange("birchMineshaftMaxHeight", 45, 5, 255);

        jungleMineshaftMaxHeight = builder
                .comment("\n Minimum Y height that this mineshaft can spawn at.",
                        " Note: The mineshaft will spawn between min and max y height set in config.",
                        " Setting this to below min height config will make mineshaft spawn only at min height.")
                .translation("repurposedstructures.config.mineshaft.junglemineshaftmaxheight")
                .defineInRange("jungleMineshaftMaxHeight", 45, 5, 255);

        desertMineshaftMaxHeight = builder
                .comment("\n Minimum Y height that this mineshaft can spawn at.",
                        " Note: The mineshaft will spawn between min and max y height set in config.",
                        " Setting this to below min height config will make mineshaft spawn only at min height.")
                .translation("repurposedstructures.config.mineshaft.desertmineshaftmaxheight")
                .defineInRange("desertMineshaftMaxHeight", 45, 5, 255);

        stoneMineshaftMaxHeight = builder
                .comment("\n Minimum Y height that this mineshaft can spawn at.",
                        " Note: The mineshaft will spawn between min and max y height set in config.",
                        " Setting this to below min height config will make mineshaft spawn only at min height.")
                .translation("repurposedstructures.config.mineshaft.stonemineshaftmaxheight")
                .defineInRange("stoneMineshaftMaxHeight", 45, 5, 255);

        savannaMineshaftMaxHeight = builder
                .comment("\n Minimum Y height that this mineshaft can spawn at.",
                        " Note: The mineshaft will spawn between min and max y height set in config.",
                        " Setting this to below min height config will make mineshaft spawn only at min height.")
                .translation("repurposedstructures.config.mineshaft.savannamineshaftmaxheight")
                .defineInRange("savannaMineshaftMaxHeight", 45, 5, 255);

        icyMineshaftMaxHeight = builder
                .comment("\n Minimum Y height that this mineshaft can spawn at.",
                        " Note: The mineshaft will spawn between min and max y height set in config.",
                        " Setting this to below min height config will make mineshaft spawn only at min height.")
                .translation("repurposedstructures.config.mineshaft.icymineshaftmaxheight")
                .defineInRange("icyMineshaftMaxHeight", 45, 5, 255);

        oceanMineshaftMaxHeight = builder
                .comment("\n Minimum Y height that this mineshaft can spawn at.",
                        " Note: The mineshaft will spawn between min and max y height set in config.",
                        " Setting this to below min height config will make mineshaft spawn only at min height.")
                .translation("repurposedstructures.config.mineshaft.oceanmineshaftmaxheight")
                .defineInRange("oceanMineshaftMaxHeight", 26, 5, 255);

        taigaMineshaftMaxHeight = builder
                .comment("\n Minimum Y height that this mineshaft can spawn at.",
                        " Note: The mineshaft will spawn between min and max y height set in config.",
                        " Setting this to below min height config will make mineshaft spawn only at min height.")
                .translation("repurposedstructures.config.mineshaft.taigamineshaftmaxheight")
                .defineInRange("taigaMineshaftMaxHeight", 45, 5, 255);

        darkForestMineshaftMaxHeight = builder
                .comment("\n Minimum Y height that this mineshaft can spawn at.",
                        " Note: The mineshaft will spawn between min and max y height set in config.",
                        " Setting this to below min height config will make mineshaft spawn only at min height.")
                .translation("repurposedstructures.config.mineshaft.darkforestmineshaftmaxheight")
                .defineInRange("darkForestMineshaftMaxHeight", 45, 5, 255);

        swampMineshaftMaxHeight = builder
                .comment("\n Minimum Y height that this mineshaft can spawn at.",
                        " Note: The mineshaft will spawn between min and max y height set in config.",
                        " Setting this to below min height config will make mineshaft spawn only at min height.")
                .translation("repurposedstructures.config.mineshaft.swampmineshaftmaxheight")
                .defineInRange("swampMineshaftMaxHeight", 45, 5, 255);

        netherMineshaftMaxHeight = builder
                .comment("\n Minimum Y height that this mineshaft can spawn at.",
                        " Note: The mineshaft will spawn between min and max y height set in config.",
                        " Setting this to below min height config will make mineshaft spawn only at min height.")
                .translation("repurposedstructures.config.mineshaft.nethermineshaftmaxheight")
                .defineInRange("netherMineshaftMaxHeight", 17, 5, 255);

        crimsonMineshaftMaxHeight = builder
                .comment("\n Minimum Y height that this mineshaft can spawn at.",
                        " Note: The mineshaft will spawn between min and max y height set in config.",
                        " Setting this to below min height config will make mineshaft spawn only at min height.")
                .translation("repurposedstructures.config.mineshaft.crimsonmineshaftmaxheight")
                .defineInRange("crimsonMineshaftMaxHeight", 14, 5, 255);

        warpedMineshaftMaxHeight = builder
                .comment("\n Minimum Y height that this mineshaft can spawn at.",
                        " Note: The mineshaft will spawn between min and max y height set in config.",
                        " Setting this to below min height config will make mineshaft spawn only at min height.")
                .translation("repurposedstructures.config.mineshaft.warpedmineshaftmaxheight")
                .defineInRange("warpedMineshaftMaxHeight", 14, 5, 255);

        builder.pop();

        builder.push("Size");

        birchMineshaftSize = builder
                .comment("\n Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
                .translation("repurposedstructures.config.mineshaft.birchmineshaftsize")
                .defineInRange("birchMineshaftSize", 9, 1, 30);

        jungleMineshaftSize = builder
                .comment("\n Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
                .translation("repurposedstructures.config.mineshaft.junglemineshaftsize")
                .defineInRange("jungleMineshaftSize", 9, 1, 30);

        desertMineshaftSize = builder
                .comment("\n Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
                .translation("repurposedstructures.config.mineshaft.desertmineshaftsize")
                .defineInRange("desertMineshaftSize", 9, 1, 30);

        stoneMineshaftSize = builder
                .comment("\n Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
                .translation("repurposedstructures.config.mineshaft.stonemineshaftsize")
                .defineInRange("stoneMineshaftSize", 9, 1, 30);

        savannaMineshaftSize = builder
                .comment("\n Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
                .translation("repurposedstructures.config.mineshaft.savannamineshaftsize")
                .defineInRange("savannaMineshaftSize", 9, 1, 30);

        icyMineshaftSize = builder
                .comment("\n Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
                .translation("repurposedstructures.config.mineshaft.icymineshaftsize")
                .defineInRange("icyMineshaftSize", 9, 1, 30);

        oceanMineshaftSize = builder
                .comment("\n Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
                .translation("repurposedstructures.config.mineshaft.oceanmineshaftsize")
                .defineInRange("oceanMineshaftSize", 9, 1, 30);

        taigaMineshaftSize = builder
                .comment("\n Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
                .translation("repurposedstructures.config.mineshaft.taigamineshaftsize")
                .defineInRange("taigaMineshaftSize", 9, 1, 30);

        darkForestMineshaftSize = builder
                .comment("\n Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
                .translation("repurposedstructures.config.mineshaft.darkforestmineshaftsize")
                .defineInRange("darkForestMineshaftSize", 9, 1, 30);

        swampMineshaftSize = builder
                .comment("\n Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
                .translation("repurposedstructures.config.mineshaft.swampmineshaftsize")
                .defineInRange("swampMineshaftSize", 9, 1, 30);

        endMineshaftSize = builder
                .comment("\n Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
                .translation("repurposedstructures.config.mineshaft.endmineshaftsize")
                .defineInRange("endMineshaftSize", 11, 1, 30);

        netherMineshaftSize = builder
                .comment("\n Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
                .translation("repurposedstructures.config.mineshaft.nethermineshaftsize")
                .defineInRange("netherMineshaftSize", 10, 1, 30);

        crimsonMineshaftSize = builder
                .comment("\n Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
                .translation("repurposedstructures.config.mineshaft.crimsonmineshaftsize")
                .defineInRange("crimsonMineshaftSize", 10, 1, 30);

        warpedMineshaftSize = builder
                .comment("\n Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
                .translation("repurposedstructures.config.mineshaft.warpedmineshaftsize")
                .defineInRange("warpedMineshaftSize", 10, 1, 30);

        builder.pop();

        builder.push("Misc");

        endMineshaftMinIslandThickness = builder
                .comment("\n The minimum thickness of End islands that the End Mineshaft can spawn in.",
                        "So 30 means the End Mineshaft will spawn in land that is at least 30 blocks vertically in the area.",
                        "Do 0 to turn off this check and allow the End Mineshaft to spawn anywhere including floating in midair.")
                .translation("repurposedstructures.config.mineshaft.endMineshaftMinIslandThickness")
                .defineInRange("endMineshaftMinIslandThickness", 30, 0, 256);

        builder.pop();
    }
}
