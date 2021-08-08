package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;

public class RSMineshaftsConfig {

    public static class RSMineshaftsConfigValues {
        public ConfigValueListener<Double> birchMineshaftSpawnrate;
        public ConfigValueListener<Double> jungleMineshaftSpawnrate;
        public ConfigValueListener<Double> desertMineshaftSpawnrate;
        public ConfigValueListener<Double> stoneMineshaftSpawnrate;
        public ConfigValueListener<Double> savannaMineshaftSpawnrate;
        public ConfigValueListener<Double> icyMineshaftSpawnrate;
        public ConfigValueListener<Double> oceanMineshaftSpawnrate;
        public ConfigValueListener<Double> taigaMineshaftSpawnrate;
        public ConfigValueListener<Double> darkForestMineshaftSpawnrate;
        public ConfigValueListener<Double> swampMineshaftSpawnrate;
        public ConfigValueListener<Double> endMineshaftSpawnrate;
        public ConfigValueListener<Double> netherMineshaftSpawnrate;
        public ConfigValueListener<Double> crimsonMineshaftSpawnrate;
        public ConfigValueListener<Double> warpedMineshaftSpawnrate;

        public ConfigValueListener<Integer> birchMineshaftMinHeight;
        public ConfigValueListener<Integer> jungleMineshaftMinHeight;
        public ConfigValueListener<Integer> desertMineshaftMinHeight;
        public ConfigValueListener<Integer> stoneMineshaftMinHeight;
        public ConfigValueListener<Integer> savannaMineshaftMinHeight;
        public ConfigValueListener<Integer> icyMineshaftMinHeight;
        public ConfigValueListener<Integer> oceanMineshaftMinHeight;
        public ConfigValueListener<Integer> taigaMineshaftMinHeight;
        public ConfigValueListener<Integer> darkForestMineshaftMinHeight;
        public ConfigValueListener<Integer> swampMineshaftMinHeight;
        public ConfigValueListener<Integer> endMineshaftMinHeight;
        public ConfigValueListener<Integer> netherMineshaftMinHeight;
        public ConfigValueListener<Integer> crimsonMineshaftMinHeight;
        public ConfigValueListener<Integer> warpedMineshaftMinHeight;

        public ConfigValueListener<Integer> birchMineshaftMaxHeight;
        public ConfigValueListener<Integer> jungleMineshaftMaxHeight;
        public ConfigValueListener<Integer> desertMineshaftMaxHeight;
        public ConfigValueListener<Integer> stoneMineshaftMaxHeight;
        public ConfigValueListener<Integer> savannaMineshaftMaxHeight;
        public ConfigValueListener<Integer> icyMineshaftMaxHeight;
        public ConfigValueListener<Integer> oceanMineshaftMaxHeight;
        public ConfigValueListener<Integer> taigaMineshaftMaxHeight;
        public ConfigValueListener<Integer> darkForestMineshaftMaxHeight;
        public ConfigValueListener<Integer> swampMineshaftMaxHeight;
        public ConfigValueListener<Integer> endMineshaftMaxHeight;
        public ConfigValueListener<Integer> netherMineshaftMaxHeight;
        public ConfigValueListener<Integer> crimsonMineshaftMaxHeight;
        public ConfigValueListener<Integer> warpedMineshaftMaxHeight;

        public ConfigValueListener<Integer> birchMineshaftSize;
        public ConfigValueListener<Integer> jungleMineshaftSize;
        public ConfigValueListener<Integer> desertMineshaftSize;
        public ConfigValueListener<Integer> stoneMineshaftSize;
        public ConfigValueListener<Integer> savannaMineshaftSize;
        public ConfigValueListener<Integer> icyMineshaftSize;
        public ConfigValueListener<Integer> oceanMineshaftSize;
        public ConfigValueListener<Integer> taigaMineshaftSize;
        public ConfigValueListener<Integer> darkForestMineshaftSize;
        public ConfigValueListener<Integer> swampMineshaftSize;
        public ConfigValueListener<Integer> endMineshaftSize;
        public ConfigValueListener<Integer> netherMineshaftSize;
        public ConfigValueListener<Integer> crimsonMineshaftSize;
        public ConfigValueListener<Integer> warpedMineshaftSize;

        public ConfigValueListener<Boolean> barrensIslandsEndMineshafts;

        public RSMineshaftsConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber) {
            builder.push("Spawnrate");

            birchMineshaftSpawnrate = subscriber.subscribe(builder
                    .comment("\n Controls how often Mineshafts will spawn. 0 is no Mineshafts while 1000 is max spawnrate.",
                            " Replace Mineshafts in Birch biomes with a Birch themed Mineshaft.",
                            " Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
                    .translation("repurposedstructures.config.mineshaft.birchmineshaftspawnrate")
                    .defineInRange("birchMineshaftSpawnrate", 40D, 0, 1000));

            jungleMineshaftSpawnrate = subscriber.subscribe(builder
                    .comment("\n Controls how often Mineshafts will spawn. 0 is no Mineshafts while 1000 is max spawnrate.",
                            " Replace Mineshafts in Jungle biomes with a Jungle themed Mineshaft.",
                            " Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
                    .translation("repurposedstructures.config.mineshaft.junglemineshaftspawnrate")
                    .defineInRange("jungleMineshaftSpawnrate", 40D, 0, 1000));

            desertMineshaftSpawnrate = subscriber.subscribe(builder
                    .comment("\n Controls how often Mineshafts will spawn. 0 is no Mineshafts while 1000 is max spawnrate.",
                            " Replace Mineshafts in Desert biomes with a Desert themed Mineshaft.",
                            " Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
                    .translation("repurposedstructures.config.mineshaft.desertmineshaftspawnrate")
                    .defineInRange("desertMineshaftSpawnrate", 40D, 0, 1000));

            stoneMineshaftSpawnrate = subscriber.subscribe(builder
                    .comment("\n Controls how often Mineshafts will spawn. 0 is no Mineshafts while 1000 is max spawnrate.",
                            " Replace Mineshafts in Mountain (Extreme Hills) biomes with a Stone themed Mineshaft.",
                            " Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
                    .translation("repurposedstructures.config.mineshaft.stonemineshaftspawnrate")
                    .defineInRange("stoneMineshaftSpawnrate", 40D, 0, 1000));

            savannaMineshaftSpawnrate = subscriber.subscribe(builder
                    .comment("\n Controls how often Mineshafts will spawn. 0 is no Mineshafts while 1000 is max spawnrate.",
                            " Replace Mineshafts in Savanna biomes with a Savanna themed Mineshaft.",
                            " Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
                    .translation("repurposedstructures.config.mineshaft.savannamineshaftspawnrate")
                    .defineInRange("savannaMineshaftSpawnrate", 40D, 0, 1000));

            icyMineshaftSpawnrate = subscriber.subscribe(builder
                    .comment("\n Controls how often Mineshafts will spawn. 0 is no Mineshafts while 1000 is max spawnrate.",
                            " Replace Mineshafts in Snowy/Icy biomes with an Ice themed Mineshaft.",
                            " Note: Snowy Taiga Biomes will get Ice Mineshaft instead of Taiga theme.",
                            " Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
                    .translation("repurposedstructures.config.mineshaft.icymineshaftspawnrate")
                    .defineInRange("icyMineshaftSpawnrate", 40D, 0, 1000));

            oceanMineshaftSpawnrate = subscriber.subscribe(builder
                    .comment("\n Controls how often Mineshafts will spawn. 0 is no Mineshafts while 1000 is max spawnrate.",
                            " Replace Mineshafts in Ocean biomes with an Ocean themed Mineshaft.",
                            " Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
                    .translation("repurposedstructures.config.mineshaft.oceanmineshaftspawnrate")
                    .defineInRange("oceanMineshaftSpawnrate", 40D, 0, 1000));

            taigaMineshaftSpawnrate = subscriber.subscribe(builder
                    .comment("\n Controls how often Mineshafts will spawn. 0 is no Mineshafts while 1000 is max spawnrate.",
                            " Replace Mineshafts in Taiga biomes with a Taiga themed Mineshaft.",
                            " Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
                    .translation("repurposedstructures.config.mineshaft.taigamineshaftspawnrate")
                    .defineInRange("taigaMineshaftSpawnrate", 40D, 0, 1000));

            darkForestMineshaftSpawnrate = subscriber.subscribe(builder
                    .comment("\n Controls how often Mineshafts will spawn. 0 is no Mineshafts while 1000 is max spawnrate.",
                            " Replace Mineshafts in Dark Forests with a dark oak themed Mineshaft.",
                            " Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
                    .translation("repurposedstructures.config.mineshaft.darkforestmineshaftspawnrate")
                    .defineInRange("darkForestMineshaftSpawnrate", 40D, 0, 1000));

            swampMineshaftSpawnrate = subscriber.subscribe(builder
                    .comment("\n Controls how often Mineshafts will spawn. 0 is no Mineshafts while 1000 is max spawnrate.",
                            " Replace Mineshafts in Swamps with a swampy themed Mineshaft.",
                            " Note: Vanilla Mineshafts will start spawning when this is set to 0 and game is restarted.")
                    .translation("repurposedstructures.config.mineshaft.swampmineshaftspawnrate")
                    .defineInRange("swampMineshaftSpawnrate", 40D, 0, 1000));

            endMineshaftSpawnrate = subscriber.subscribe(builder
                    .comment("\n Controls how often Mineshafts will spawn. 0 for no Mineshafts and 1000 for max spawnrate.",
                            " Adds End themed Mineshafts to End biomes outside the Enderdragon island.")
                    .translation("repurposedstructures.config.mineshaft.endmineshaftspawnrate")
                    .defineInRange("endMineshaftSpawnrate", 40D, 0, 1000));

            netherMineshaftSpawnrate = subscriber.subscribe(builder
                    .comment("\n Controls how often Mineshafts will spawn. 0 for no Mineshafts and 1000 for max spawnrate.",
                            " Adds Nether themed Mineshafts to non-crimson and non-warped Nether biomes.")
                    .translation("repurposedstructures.config.mineshaft.nethermineshaftspawnrate")
                    .defineInRange("netherMineshaftSpawnrate", 40D, 0, 1000));

            crimsonMineshaftSpawnrate = subscriber.subscribe(builder
                    .comment("\n Controls how often Mineshafts will spawn. 0 for no Mineshafts and 1000 for max spawnrate.",
                            " Adds Crimson themed Mineshafts to Crimson Nether biomes.")
                    .translation("repurposedstructures.config.mineshaft.crimsonmineshaftspawnrate")
                    .defineInRange("crimsonMineshaftSpawnrate", 40D, 0, 1000));

            warpedMineshaftSpawnrate = subscriber.subscribe(builder
                    .comment("\n Controls how often Mineshafts will spawn. 0 for no Mineshafts and 1000 for max spawnrate.",
                            " Adds Warped themed Mineshafts to Warped Nether biomes.")
                    .translation("repurposedstructures.config.mineshaft.warpedmineshaftspawnrate")
                    .defineInRange("warpedMineshaftSpawnrate", 40D, 0, 1000));

            builder.pop();

            builder.push("Min height");

            birchMineshaftMinHeight = subscriber.subscribe(builder
                    .comment("\n Minimum Y height that this mineshaft can spawn at.",
                            " Note: The mineshaft will spawn between min and max y height set in config.",
                            " Setting this to below min height config will make mineshaft spawn only at min height.")
                    .translation("repurposedstructures.config.mineshaft.birchmineshaftminheight")
                    .defineInRange("birchMineshaftMinHeight", 8, 5, 255));

            jungleMineshaftMinHeight = subscriber.subscribe(builder
                    .comment("\n Minimum Y height that this mineshaft can spawn at.",
                            " Note: The mineshaft will spawn between min and max y height set in config.")
                    .translation("repurposedstructures.config.mineshaft.junglemineshaftminheight")
                    .defineInRange("jungleMineshaftMinHeight", 8, 5, 255));

            desertMineshaftMinHeight = subscriber.subscribe(builder
                    .comment("\n Minimum Y height that this mineshaft can spawn at.",
                            " Note: The mineshaft will spawn between min and max y height set in config.")
                    .translation("repurposedstructures.config.mineshaft.desertmineshaftminheight")
                    .defineInRange("desertMineshaftMinHeight", 8, 5, 255));

            stoneMineshaftMinHeight = subscriber.subscribe(builder
                    .comment("\n Minimum Y height that this mineshaft can spawn at.",
                            " Note: The mineshaft will spawn between min and max y height set in config.")
                    .translation("repurposedstructures.config.mineshaft.stonemineshaftminheight")
                    .defineInRange("stoneMineshaftMinHeight", 8, 5, 255));

            savannaMineshaftMinHeight = subscriber.subscribe(builder
                    .comment("\n Minimum Y height that this mineshaft can spawn at.",
                            " Note: The mineshaft will spawn between min and max y height set in config.")
                    .translation("repurposedstructures.config.mineshaft.savannamineshaftminheight")
                    .defineInRange("savannaMineshaftMinHeight", 8, 5, 255));

            icyMineshaftMinHeight = subscriber.subscribe(builder
                    .comment("\n Minimum Y height that this mineshaft can spawn at.",
                            " Note: The mineshaft will spawn between min and max y height set in config.")
                    .translation("repurposedstructures.config.mineshaft.icymineshaftminheight")
                    .defineInRange("icyMineshaftMinHeight", 8, 5, 255));

            oceanMineshaftMinHeight = subscriber.subscribe(builder
                    .comment("\n Minimum Y height that this mineshaft can spawn at.",
                            " Note: The mineshaft will spawn between min and max y height set in config.")
                    .translation("repurposedstructures.config.mineshaft.oceanmineshaftminheight")
                    .defineInRange("oceanMineshaftMinHeight", 8, 5, 255));

            taigaMineshaftMinHeight = subscriber.subscribe(builder
                    .comment("\n Minimum Y height that this mineshaft can spawn at.",
                            " Note: The mineshaft will spawn between min and max y height set in config.")
                    .translation("repurposedstructures.config.mineshaft.taigamineshaftminheight")
                    .defineInRange("taigaMineshaftMinHeight", 8, 5, 255));

            darkForestMineshaftMinHeight = subscriber.subscribe(builder
                    .comment("\n Minimum Y height that this mineshaft can spawn at.",
                            " Note: The mineshaft will spawn between min and max y height set in config.")
                    .translation("repurposedstructures.config.mineshaft.darkforestmineshaftminheight")
                    .defineInRange("darkForestMineshaftMinHeight", 8, 5, 255));

            swampMineshaftMinHeight = subscriber.subscribe(builder
                    .comment("\n Minimum Y height that this mineshaft can spawn at.",
                            " Note: The mineshaft will spawn between min and max y height set in config.")
                    .translation("repurposedstructures.config.mineshaft.swampmineshaftminheight")
                    .defineInRange("swampMineshaftMinHeight", 8, 5, 255));

            endMineshaftMinHeight = subscriber.subscribe(builder
                    .comment("\n Minimum Y height that this mineshaft can spawn at.",
                            " Note: The mineshaft will spawn between min and max y height set in config.")
                    .translation("repurposedstructures.config.mineshaft.endmineshaftminheight")
                    .defineInRange("endMineshaftMinHeight", 30, 5, 255));

            netherMineshaftMinHeight = subscriber.subscribe(builder
                    .comment("\n Minimum Y height that this mineshaft can spawn at.",
                            " Note: The mineshaft will spawn between min and max y height set in config.")
                    .translation("repurposedstructures.config.mineshaft.nethermineshaftminheight")
                    .defineInRange("netherMineshaftMinHeight", 6, 5, 255));

            crimsonMineshaftMinHeight = subscriber.subscribe(builder
                    .comment("\n Minimum Y height that this mineshaft can spawn at.",
                            " Note: The mineshaft will spawn between min and max y height set in config.")
                    .translation("repurposedstructures.config.mineshaft.crimsonmineshaftminheight")
                    .defineInRange("crimsonMineshaftMinHeight", 6, 5, 255));

            warpedMineshaftMinHeight = subscriber.subscribe(builder
                    .comment("\n Minimum Y height that this mineshaft can spawn at.",
                            " Note: The mineshaft will spawn between min and max y height set in config.")
                    .translation("repurposedstructures.config.mineshaft.warpedmineshaftminheight")
                    .defineInRange("warpedMineshaftMinHeight", 6, 5, 255));

            builder.pop();

            builder.push("Max height");

            birchMineshaftMaxHeight = subscriber.subscribe(builder
                    .comment("\n Minimum Y height that this mineshaft can spawn at.",
                            " Note: The mineshaft will spawn between min and max y height set in config.",
                            " Setting this to below min height config will make mineshaft spawn only at min height.")
                    .translation("repurposedstructures.config.mineshaft.birchmineshaftmaxheight")
                    .defineInRange("birchMineshaftMaxHeight", 45, 5, 255));

            jungleMineshaftMaxHeight = subscriber.subscribe(builder
                    .comment("\n Minimum Y height that this mineshaft can spawn at.",
                            " Note: The mineshaft will spawn between min and max y height set in config.",
                            " Setting this to below min height config will make mineshaft spawn only at min height.")
                    .translation("repurposedstructures.config.mineshaft.junglemineshaftmaxheight")
                    .defineInRange("jungleMineshaftMaxHeight", 45, 5, 255));

            desertMineshaftMaxHeight = subscriber.subscribe(builder
                    .comment("\n Minimum Y height that this mineshaft can spawn at.",
                            " Note: The mineshaft will spawn between min and max y height set in config.",
                            " Setting this to below min height config will make mineshaft spawn only at min height.")
                    .translation("repurposedstructures.config.mineshaft.desertmineshaftmaxheight")
                    .defineInRange("desertMineshaftMaxHeight", 45, 5, 255));

            stoneMineshaftMaxHeight = subscriber.subscribe(builder
                    .comment("\n Minimum Y height that this mineshaft can spawn at.",
                            " Note: The mineshaft will spawn between min and max y height set in config.",
                            " Setting this to below min height config will make mineshaft spawn only at min height.")
                    .translation("repurposedstructures.config.mineshaft.stonemineshaftmaxheight")
                    .defineInRange("stoneMineshaftMaxHeight", 45, 5, 255));

            savannaMineshaftMaxHeight = subscriber.subscribe(builder
                    .comment("\n Minimum Y height that this mineshaft can spawn at.",
                            " Note: The mineshaft will spawn between min and max y height set in config.",
                            " Setting this to below min height config will make mineshaft spawn only at min height.")
                    .translation("repurposedstructures.config.mineshaft.savannamineshaftmaxheight")
                    .defineInRange("savannaMineshaftMaxHeight", 45, 5, 255));

            icyMineshaftMaxHeight = subscriber.subscribe(builder
                    .comment("\n Minimum Y height that this mineshaft can spawn at.",
                            " Note: The mineshaft will spawn between min and max y height set in config.",
                            " Setting this to below min height config will make mineshaft spawn only at min height.")
                    .translation("repurposedstructures.config.mineshaft.icymineshaftmaxheight")
                    .defineInRange("icyMineshaftMaxHeight", 45, 5, 255));

            oceanMineshaftMaxHeight = subscriber.subscribe(builder
                    .comment("\n Minimum Y height that this mineshaft can spawn at.",
                            " Note: The mineshaft will spawn between min and max y height set in config.",
                            " Setting this to below min height config will make mineshaft spawn only at min height.")
                    .translation("repurposedstructures.config.mineshaft.oceanmineshaftmaxheight")
                    .defineInRange("oceanMineshaftMaxHeight", 26, 5, 255));

            taigaMineshaftMaxHeight = subscriber.subscribe(builder
                    .comment("\n Minimum Y height that this mineshaft can spawn at.",
                            " Note: The mineshaft will spawn between min and max y height set in config.",
                            " Setting this to below min height config will make mineshaft spawn only at min height.")
                    .translation("repurposedstructures.config.mineshaft.taigamineshaftmaxheight")
                    .defineInRange("taigaMineshaftMaxHeight", 45, 5, 255));

            darkForestMineshaftMaxHeight = subscriber.subscribe(builder
                    .comment("\n Minimum Y height that this mineshaft can spawn at.",
                            " Note: The mineshaft will spawn between min and max y height set in config.",
                            " Setting this to below min height config will make mineshaft spawn only at min height.")
                    .translation("repurposedstructures.config.mineshaft.darkforestmineshaftmaxheight")
                    .defineInRange("darkForestMineshaftMaxHeight", 45, 5, 255));

            swampMineshaftMaxHeight = subscriber.subscribe(builder
                    .comment("\n Minimum Y height that this mineshaft can spawn at.",
                            " Note: The mineshaft will spawn between min and max y height set in config.",
                            " Setting this to below min height config will make mineshaft spawn only at min height.")
                    .translation("repurposedstructures.config.mineshaft.swampmineshaftmaxheight")
                    .defineInRange("swampMineshaftMaxHeight", 45, 5, 255));

            endMineshaftMaxHeight = subscriber.subscribe(builder
                    .comment("\n Minimum Y height that this mineshaft can spawn at.",
                            " Note: The mineshaft will spawn between min and max y height set in config.",
                            " Setting this to below min height config will make mineshaft spawn only at min height.")
                    .translation("repurposedstructures.config.mineshaft.endmineshaftmaxheight")
                    .defineInRange("endMineshaftMaxHeight", 40, 5, 255));

            netherMineshaftMaxHeight = subscriber.subscribe(builder
                    .comment("\n Minimum Y height that this mineshaft can spawn at.",
                            " Note: The mineshaft will spawn between min and max y height set in config.",
                            " Setting this to below min height config will make mineshaft spawn only at min height.")
                    .translation("repurposedstructures.config.mineshaft.nethermineshaftmaxheight")
                    .defineInRange("netherMineshaftMaxHeight", 17, 5, 255));

            crimsonMineshaftMaxHeight = subscriber.subscribe(builder
                    .comment("\n Minimum Y height that this mineshaft can spawn at.",
                            " Note: The mineshaft will spawn between min and max y height set in config.",
                            " Setting this to below min height config will make mineshaft spawn only at min height.")
                    .translation("repurposedstructures.config.mineshaft.crimsonmineshaftmaxheight")
                    .defineInRange("crimsonMineshaftMaxHeight", 14, 5, 255));

            warpedMineshaftMaxHeight = subscriber.subscribe(builder
                    .comment("\n Minimum Y height that this mineshaft can spawn at.",
                            " Note: The mineshaft will spawn between min and max y height set in config.",
                            " Setting this to below min height config will make mineshaft spawn only at min height.")
                    .translation("repurposedstructures.config.mineshaft.warpedmineshaftmaxheight")
                    .defineInRange("warpedMineshaftMaxHeight", 14, 5, 255));

            builder.pop();

            builder.push("Size");

            birchMineshaftSize = subscriber.subscribe(builder
                    .comment("\n Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
                    .translation("repurposedstructures.config.mineshaft.birchmineshaftsize")
                    .defineInRange("birchMineshaftSize", 9, 1, 30));

            jungleMineshaftSize = subscriber.subscribe(builder
                    .comment("\n Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
                    .translation("repurposedstructures.config.mineshaft.junglemineshaftsize")
                    .defineInRange("jungleMineshaftSize", 9, 1, 30));

            desertMineshaftSize = subscriber.subscribe(builder
                    .comment("\n Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
                    .translation("repurposedstructures.config.mineshaft.desertmineshaftsize")
                    .defineInRange("desertMineshaftSize", 9, 1, 30));

            stoneMineshaftSize = subscriber.subscribe(builder
                    .comment("\n Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
                    .translation("repurposedstructures.config.mineshaft.stonemineshaftsize")
                    .defineInRange("stoneMineshaftSize", 9, 1, 30));

            savannaMineshaftSize = subscriber.subscribe(builder
                    .comment("\n Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
                    .translation("repurposedstructures.config.mineshaft.savannamineshaftsize")
                    .defineInRange("savannaMineshaftSize", 9, 1, 30));

            icyMineshaftSize = subscriber.subscribe(builder
                    .comment("\n Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
                    .translation("repurposedstructures.config.mineshaft.icymineshaftsize")
                    .defineInRange("icyMineshaftSize", 9, 1, 30));

            oceanMineshaftSize = subscriber.subscribe(builder
                    .comment("\n Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
                    .translation("repurposedstructures.config.mineshaft.oceanmineshaftsize")
                    .defineInRange("oceanMineshaftSize", 9, 1, 30));

            taigaMineshaftSize = subscriber.subscribe(builder
                    .comment("\n Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
                    .translation("repurposedstructures.config.mineshaft.taigamineshaftsize")
                    .defineInRange("taigaMineshaftSize", 9, 1, 30));

            darkForestMineshaftSize = subscriber.subscribe(builder
                    .comment("\n Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
                    .translation("repurposedstructures.config.mineshaft.darkforestmineshaftsize")
                    .defineInRange("darkForestMineshaftSize", 9, 1, 30));

            swampMineshaftSize = subscriber.subscribe(builder
                    .comment("\n Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
                    .translation("repurposedstructures.config.mineshaft.swampmineshaftsize")
                    .defineInRange("swampMineshaftSize", 9, 1, 30));

            endMineshaftSize = subscriber.subscribe(builder
                    .comment("\n Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
                    .translation("repurposedstructures.config.mineshaft.endmineshaftsize")
                    .defineInRange("endMineshaftSize", 11, 1, 30));

            netherMineshaftSize = subscriber.subscribe(builder
                    .comment("\n Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
                    .translation("repurposedstructures.config.mineshaft.nethermineshaftsize")
                    .defineInRange("netherMineshaftSize", 10, 1, 30));

            crimsonMineshaftSize = subscriber.subscribe(builder
                    .comment("\n Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
                    .translation("repurposedstructures.config.mineshaft.crimsonmineshaftsize")
                    .defineInRange("crimsonMineshaftSize", 10, 1, 30));

            warpedMineshaftSize = subscriber.subscribe(builder
                    .comment("\n Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
                    .translation("repurposedstructures.config.mineshaft.warpedmineshaftsize")
                    .defineInRange("warpedMineshaftSize", 10, 1, 30));

            builder.pop();

            builder.push("Misc");

            barrensIslandsEndMineshafts = subscriber.subscribe(builder
                    .comment("\n Add End Mineshafts to End Barrens and End Islands biome.")
                    .translation("repurposedstructures.config.mineshaft.barrensislandsendmineshafts")
                    .define("barrensIslandsEndMineshafts", false));

            builder.pop();
        }
    }
}
