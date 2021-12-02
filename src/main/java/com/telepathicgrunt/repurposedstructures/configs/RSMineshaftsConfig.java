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

    public static ForgeConfigSpec.ConfigValue<Integer> birchMineshaftMinHeight;
    public static ForgeConfigSpec.ConfigValue<Integer> jungleMineshaftMinHeight;
    public static ForgeConfigSpec.ConfigValue<Integer> desertMineshaftMinHeight;
    public static ForgeConfigSpec.ConfigValue<Integer> stoneMineshaftMinHeight;
    public static ForgeConfigSpec.ConfigValue<Integer> savannaMineshaftMinHeight;
    public static ForgeConfigSpec.ConfigValue<Integer> icyMineshaftMinHeight;
    public static ForgeConfigSpec.ConfigValue<Integer> oceanMineshaftMinHeight;
    public static ForgeConfigSpec.ConfigValue<Integer> taigaMineshaftMinHeight;
    public static ForgeConfigSpec.ConfigValue<Integer> darkForestMineshaftMinHeight;
    public static ForgeConfigSpec.ConfigValue<Integer> swampMineshaftMinHeight;
    public static ForgeConfigSpec.ConfigValue<Integer> netherMineshaftMinHeight;
    public static ForgeConfigSpec.ConfigValue<Integer> crimsonMineshaftMinHeight;
    public static ForgeConfigSpec.ConfigValue<Integer> warpedMineshaftMinHeight;

    public static ForgeConfigSpec.ConfigValue<Integer> birchMineshaftMaxHeight;
    public static ForgeConfigSpec.ConfigValue<Integer> jungleMineshaftMaxHeight;
    public static ForgeConfigSpec.ConfigValue<Integer> desertMineshaftMaxHeight;
    public static ForgeConfigSpec.ConfigValue<Integer> stoneMineshaftMaxHeight;
    public static ForgeConfigSpec.ConfigValue<Integer> savannaMineshaftMaxHeight;
    public static ForgeConfigSpec.ConfigValue<Integer> icyMineshaftMaxHeight;
    public static ForgeConfigSpec.ConfigValue<Integer> oceanMineshaftMaxHeight;
    public static ForgeConfigSpec.ConfigValue<Integer> taigaMineshaftMaxHeight;
    public static ForgeConfigSpec.ConfigValue<Integer> darkForestMineshaftMaxHeight;
    public static ForgeConfigSpec.ConfigValue<Integer> swampMineshaftMaxHeight;
    public static ForgeConfigSpec.ConfigValue<Integer> netherMineshaftMaxHeight;
    public static ForgeConfigSpec.ConfigValue<Integer> crimsonMineshaftMaxHeight;
    public static ForgeConfigSpec.ConfigValue<Integer> warpedMineshaftMaxHeight;

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

        builder.comment("-----------------------------------------------------------------------------------------",
                " Controls the probability of spawning a Repurposed Structures Mineshafts per chunk.",
                " Will replace Vanilla Mineshafts with RS's themed Mineshafts if both would've been in same biome.",
                " 0 is no Mineshafts while 1000 is max spawnrate.",
                " Note: Vanilla Mineshafts will spawn again if a RS Mineshafts's entry is set to 0 for the biome.");
        builder.push("Spawnrate");

        birchMineshaftSpawnrate = builder
                .translation("repurposedstructures.birchmineshaftspawnrate")
                .defineInRange("birchMineshaftSpawnrate", 10D, 0, 1000);

        jungleMineshaftSpawnrate = builder
                .translation("repurposedstructures.junglemineshaftspawnrate")
                .defineInRange("jungleMineshaftSpawnrate", 10D, 0, 1000);

        desertMineshaftSpawnrate = builder
                .translation("repurposedstructures.desertmineshaftspawnrate")
                .defineInRange("desertMineshaftSpawnrate", 10D, 0, 1000);

        stoneMineshaftSpawnrate = builder
                .translation("repurposedstructures.stonemineshaftspawnrate")
                .defineInRange("stoneMineshaftSpawnrate", 20D, 0, 1000);

        savannaMineshaftSpawnrate = builder
                .translation("repurposedstructures.savannamineshaftspawnrate")
                .defineInRange("savannaMineshaftSpawnrate", 20D, 0, 1000);

        icyMineshaftSpawnrate = builder
                .translation("repurposedstructures.icymineshaftspawnrate")
                .defineInRange("icyMineshaftSpawnrate", 20D, 0, 1000);

        taigaMineshaftSpawnrate = builder
                .translation("repurposedstructures.taigamineshaftspawnrate")
                .defineInRange("taigaMineshaftSpawnrate", 10D, 0, 1000);

        darkForestMineshaftSpawnrate = builder
                .translation("repurposedstructures.darkforestmineshaftspawnrate")
                .defineInRange("darkForestMineshaftSpawnrate", 10D, 0, 1000);

        swampMineshaftSpawnrate = builder
                .translation("repurposedstructures.swampmineshaftspawnrate")
                .defineInRange("swampMineshaftSpawnrate", 10D, 0, 1000);

        oceanMineshaftSpawnrate = builder
                .translation("repurposedstructures.oceanmineshaftspawnrate")
                .defineInRange("oceanMineshaftSpawnrate", 15D, 0, 1000);

        netherMineshaftSpawnrate = builder
                .translation("repurposedstructures.nethermineshaftspawnrate")
                .defineInRange("netherMineshaftSpawnrate", 40D, 0, 1000);

        crimsonMineshaftSpawnrate = builder
                .translation("repurposedstructures.crimsonmineshaftspawnrate")
                .defineInRange("crimsonMineshaftSpawnrate", 40D, 0, 1000);

        warpedMineshaftSpawnrate = builder
                .translation("repurposedstructures.warpedmineshaftspawnrate")
                .defineInRange("warpedMineshaftSpawnrate", 40D, 0, 1000);

        endMineshaftSpawnrate = builder
                .translation("repurposedstructures.endmineshaftspawnrate")
                .defineInRange("endMineshaftSpawnrate", 60D, 0, 1000);

        builder.pop();

        builder.comment("-----------------------------------------------------------------------------------------",
                " Minimum Y height that this mineshaft can spawn at.",
                " Note: The mineshaft will spawn between min and max y height set in config.");
        builder.push("Min height");

        birchMineshaftMinHeight = builder
                .translation("repurposedstructures.birchmineshaftminheight")
                .define("birchMineshaftMinHeight", 40);

        jungleMineshaftMinHeight = builder
                .translation("repurposedstructures.junglemineshaftminheight")
                .define("jungleMineshaftMinHeight", 40);

        desertMineshaftMinHeight = builder
                .translation("repurposedstructures.desertmineshaftminheight")
                .define("desertMineshaftMinHeight", 40);

        stoneMineshaftMinHeight = builder
                .translation("repurposedstructures.stonemineshaftminheight")
                .define("stoneMineshaftMinHeight", 40);

        savannaMineshaftMinHeight = builder
                .translation("repurposedstructures.savannamineshaftminheight")
                .define("savannaMineshaftMinHeight", 40);

        icyMineshaftMinHeight = builder
                .translation("repurposedstructures.icymineshaftminheight")
                .define("icyMineshaftMinHeight", 40);

        taigaMineshaftMinHeight = builder
                .translation("repurposedstructures.taigamineshaftminheight")
                .define("taigaMineshaftMinHeight", 40);

        darkForestMineshaftMinHeight = builder
                .translation("repurposedstructures.darkforestmineshaftminheight")
                .define("darkForestMineshaftMinHeight", 40);

        swampMineshaftMinHeight = builder
                .translation("repurposedstructures.swampmineshaftminheight")
                .define("swampMineshaftMinHeight", 40);

        oceanMineshaftMinHeight = builder
                .translation("repurposedstructures.oceanmineshaftminheight")
                .define("oceanMineshaftMinHeight", 15);

        netherMineshaftMinHeight = builder
                .translation("repurposedstructures.nethermineshaftminheight")
                .define("netherMineshaftMinHeight", 6);

        crimsonMineshaftMinHeight = builder
                .translation("repurposedstructures.crimsonmineshaftminheight")
                .define("crimsonMineshaftMinHeight", 6);

        warpedMineshaftMinHeight = builder
                .translation("repurposedstructures.warpedmineshaftminheight")
                .define("warpedMineshaftMinHeight", 6);

        builder.pop();

        builder.comment("-----------------------------------------------------------------------------------------",
                " Maximum Y height that this mineshaft can spawn at.",
                " Note: The mineshaft will spawn between min and max y height set in config.",
                " Setting this to below min height config will make mineshaft spawn only at min height.");
        builder.push("Max height");

        birchMineshaftMaxHeight = builder
                .translation("repurposedstructures.birchmineshaftmaxheight")
                .define("birchMineshaftMaxHeight", 55);

        jungleMineshaftMaxHeight = builder
                .translation("repurposedstructures.junglemineshaftmaxheight")
                .define("jungleMineshaftMaxHeight", 55);

        desertMineshaftMaxHeight = builder
                .translation("repurposedstructures.desertmineshaftmaxheight")
                .define("desertMineshaftMaxHeight", 55);

        stoneMineshaftMaxHeight = builder
                .translation("repurposedstructures.stonemineshaftmaxheight")
                .define("stoneMineshaftMaxHeight", 150);

        savannaMineshaftMaxHeight = builder
                .translation("repurposedstructures.savannamineshaftmaxheight")
                .define("savannaMineshaftMaxHeight", 120);

        icyMineshaftMaxHeight = builder
                .translation("repurposedstructures.icymineshaftmaxheight")
                .define("icyMineshaftMaxHeight", 150);

        taigaMineshaftMaxHeight = builder
                .translation("repurposedstructures.taigamineshaftmaxheight")
                .define("taigaMineshaftMaxHeight", 55);

        darkForestMineshaftMaxHeight = builder
                .translation("repurposedstructures.darkforestmineshaftmaxheight")
                .define("darkForestMineshaftMaxHeight", 55);

        swampMineshaftMaxHeight = builder
                .translation("repurposedstructures.swampmineshaftmaxheight")
                .define("swampMineshaftMaxHeight", 55);

        oceanMineshaftMaxHeight = builder
                .translation("repurposedstructures.oceanmineshaftmaxheight")
                .define("oceanMineshaftMaxHeight", 30);

        netherMineshaftMaxHeight = builder
                .translation("repurposedstructures.nethermineshaftmaxheight")
                .define("netherMineshaftMaxHeight", 17);

        crimsonMineshaftMaxHeight = builder
                .translation("repurposedstructures.crimsonmineshaftmaxheight")
                .define("crimsonMineshaftMaxHeight", 14);

        warpedMineshaftMaxHeight = builder
                .translation("repurposedstructures.warpedmineshaftmaxheight")
                .define("warpedMineshaftMaxHeight", 14);

        builder.pop();

        builder.comment("-----------------------------------------------------------------------------------------",
                " Size of the mineshaft. This is how many pieces long a branch can be from the start piece.");
        builder.push("Size");

        birchMineshaftSize = builder
                .translation("repurposedstructures.birchmineshaftsize")
                .defineInRange("birchMineshaftSize", 9, 1, 30);

        jungleMineshaftSize = builder
                .translation("repurposedstructures.junglemineshaftsize")
                .defineInRange("jungleMineshaftSize", 9, 1, 30);

        desertMineshaftSize = builder
                .translation("repurposedstructures.desertmineshaftsize")
                .defineInRange("desertMineshaftSize", 9, 1, 30);

        stoneMineshaftSize = builder
                .translation("repurposedstructures.stonemineshaftsize")
                .defineInRange("stoneMineshaftSize", 9, 1, 30);

        savannaMineshaftSize = builder
                .translation("repurposedstructures.savannamineshaftsize")
                .defineInRange("savannaMineshaftSize", 9, 1, 30);

        icyMineshaftSize = builder
                .translation("repurposedstructures.icymineshaftsize")
                .defineInRange("icyMineshaftSize", 9, 1, 30);

        oceanMineshaftSize = builder
                .translation("repurposedstructures.oceanmineshaftsize")
                .defineInRange("oceanMineshaftSize", 9, 1, 30);

        taigaMineshaftSize = builder
                .translation("repurposedstructures.taigamineshaftsize")
                .defineInRange("taigaMineshaftSize", 9, 1, 30);

        darkForestMineshaftSize = builder
                .translation("repurposedstructures.darkforestmineshaftsize")
                .defineInRange("darkForestMineshaftSize", 9, 1, 30);

        swampMineshaftSize = builder
                .translation("repurposedstructures.swampmineshaftsize")
                .defineInRange("swampMineshaftSize", 9, 1, 30);

        endMineshaftSize = builder
                .translation("repurposedstructures.endmineshaftsize")
                .defineInRange("endMineshaftSize", 11, 1, 30);

        netherMineshaftSize = builder
                .translation("repurposedstructures.nethermineshaftsize")
                .defineInRange("netherMineshaftSize", 10, 1, 30);

        crimsonMineshaftSize = builder
                .translation("repurposedstructures.crimsonmineshaftsize")
                .defineInRange("crimsonMineshaftSize", 10, 1, 30);

        warpedMineshaftSize = builder
                .translation("repurposedstructures.warpedmineshaftsize")
                .defineInRange("warpedMineshaftSize", 10, 1, 30);

        builder.pop();

        builder.push("Misc");

        endMineshaftMinIslandThickness = builder
                .comment("\n The minimum thickness of End islands that the End Mineshaft can spawn in.",
                        "So 30 means the End Mineshaft will spawn in land that is at least 30 blocks vertically in the area.",
                        "Do 0 to turn off this check and allow the End Mineshaft to spawn anywhere including floating in midair.")
                .translation("repurposedstructures.endMineshaftMinIslandThickness")
                .defineInRange("endMineshaftMinIslandThickness", 30, 0, 256);

        builder.pop();
    }
}
