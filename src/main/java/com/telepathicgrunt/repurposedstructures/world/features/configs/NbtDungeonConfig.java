package com.telepathicgrunt.repurposedstructures.world.features.configs;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.FeatureConfig;

import java.util.List;
import java.util.function.Function;

public class NbtDungeonConfig implements FeatureConfig {
    public static final Codec<NbtDungeonConfig> CODEC = RecordCodecBuilder.<NbtDungeonConfig>create((configInstance) -> configInstance.group(
            Codec.BOOL.fieldOf("replace_air").orElse(false).forGetter(nbtDungeonConfig -> nbtDungeonConfig.replaceAir),
            Codec.intRange(0, Integer.MAX_VALUE).fieldOf("min_air_space").forGetter(nbtFeatureConfig -> nbtFeatureConfig.minAirSpace),
            Codec.intRange(0, Integer.MAX_VALUE).fieldOf("max_air_space").forGetter(nbtFeatureConfig -> nbtFeatureConfig.maxAirSpace),
            Codec.intRange(0, 100).fieldOf("max_num_of_chests").forGetter(nbtFeatureConfig -> nbtFeatureConfig.maxNumOfChests),
            Codec.BOOL.fieldOf("air_requirement_is_now_water").orElse(false).forGetter(nbtDungeonConfig -> nbtDungeonConfig.airRequirementIsNowWater),
            Codec.INT.fieldOf("structure_y_offset").orElse(0).forGetter(nbtFeatureConfig -> nbtFeatureConfig.structureYOffset),
            BlockState.CODEC.fieldOf("loot_block").orElse(Blocks.CHEST.getDefaultState()).forGetter(nbtDungeonConfig -> nbtDungeonConfig.lootBlock),
            Identifier.CODEC.fieldOf("chest_loottable_resourcelocation").forGetter(nbtDungeonConfig -> nbtDungeonConfig.chestResourcelocation),
            Identifier.CODEC.fieldOf("rs_spawner_resourcelocation").forGetter(nbtDungeonConfig -> nbtDungeonConfig.rsSpawnerResourcelocation),
            Identifier.CODEC.fieldOf("processors").forGetter(nbtDungeonConfig -> nbtDungeonConfig.processor),
            Identifier.CODEC.fieldOf("post_processors").orElse(new Identifier("minecraft:empty")).forGetter(nbtDungeonConfig -> nbtDungeonConfig.postProcessor),
            Codec.mapPair(Identifier.CODEC.fieldOf("resourcelocation"), Codec.intRange(1, Integer.MAX_VALUE).fieldOf("weight")).codec().listOf().fieldOf("dungeon_nbt_entries").forGetter(nbtFeatureConfig -> nbtFeatureConfig.nbtResourcelocationsAndWeights)
    ).apply(configInstance, NbtDungeonConfig::new))
            .comapFlatMap((nbtDungeonConfig) -> nbtDungeonConfig.maxAirSpace <= nbtDungeonConfig.minAirSpace ?
                    DataResult.error("min_air_space has to be smaller than max_air_space") : DataResult.success(nbtDungeonConfig), Function.identity());

    public final boolean replaceAir;
    public final int minAirSpace;
    public final int maxAirSpace;
    public final int maxNumOfChests;
    public final Identifier chestResourcelocation;
    public final List<Pair<Identifier, Integer>> nbtResourcelocationsAndWeights;
    public final Identifier rsSpawnerResourcelocation;
    public final Identifier processor;
    public final Identifier postProcessor;
    public final boolean airRequirementIsNowWater;
    public final int structureYOffset;
    public final BlockState lootBlock;

    public NbtDungeonConfig(boolean replaceAir, int minAirSpace, int maxAirSpace,
                            int maxNumOfChests, boolean airRequirementIsNowWater, int structureYOffset,
                            BlockState lootBlock, Identifier chestIdentifier,
                            Identifier rsSpawnerIdentifier, Identifier processor, Identifier postProcessor,
                            List<Pair<Identifier, Integer>> nbtIdentifiersAndWeights)
    {
        this.replaceAir = replaceAir;
        this.minAirSpace = minAirSpace;
        this.maxAirSpace = maxAirSpace;
        this.maxNumOfChests = maxNumOfChests;
        this.chestResourcelocation = chestIdentifier;
        this.nbtResourcelocationsAndWeights = nbtIdentifiersAndWeights;
        this.rsSpawnerResourcelocation = rsSpawnerIdentifier;
        this.processor = processor;
        this.postProcessor = postProcessor;
        this.airRequirementIsNowWater = airRequirementIsNowWater;
        this.structureYOffset = structureYOffset;
        this.lootBlock = lootBlock;
    }

    public NbtDungeonConfig(String dungeonType, Identifier postProcessor){
        this(dungeonType, dungeonType, postProcessor);
    }

    public NbtDungeonConfig(String dungeonType, String spawnerType, Identifier postProcessor){
        this(dungeonType, spawnerType, postProcessor, 13, false, 0);
    }

    public NbtDungeonConfig(String dungeonType, String spawnerType, Identifier processor, Identifier postProcessor){
        this(dungeonType, spawnerType, processor, postProcessor, 13, false, 0);
    }

    public NbtDungeonConfig(String dungeonType, String spawnerType, Identifier postProcessor,
                            int maxAirSpace, boolean airRequirementIsNowWater, int structureYOffset){
        this(dungeonType, spawnerType, new Identifier(RepurposedStructures.MODID, "dungeons/"+dungeonType), postProcessor, maxAirSpace, airRequirementIsNowWater, structureYOffset);
    }

    public NbtDungeonConfig(String dungeonType, String spawnerType, Identifier processor, Identifier postProcessor,
                            int maxAirSpace, boolean airRequirementIsNowWater, int structureYOffset){
        this(false,1, maxAirSpace, 2,
                airRequirementIsNowWater,
                structureYOffset, Blocks.CHEST.getDefaultState(),
                new Identifier(RepurposedStructures.MODID, "chests/dungeon/"+dungeonType),
                new Identifier(RepurposedStructures.MODID, "dungeon_"+spawnerType),
                processor,
                postProcessor,
                ImmutableList.of(
                        Pair.of(new Identifier(RepurposedStructures.MODID, "dungeons/"+dungeonType+"_1"), 1),
                        Pair.of(new Identifier(RepurposedStructures.MODID, "dungeons/"+dungeonType+"_2"), 1),
                        Pair.of(new Identifier(RepurposedStructures.MODID, "dungeons/"+dungeonType+"_3"), 1)
                ));
    }

    public NbtDungeonConfig(String dungeonType, Identifier postProcessor,
                            int maxAirSpace, BlockState lootBlock){
        this(false, 1, maxAirSpace, 2,
                false, 0, lootBlock,
                new Identifier(RepurposedStructures.MODID, "chests/dungeon/"+dungeonType),
                new Identifier(RepurposedStructures.MODID, "dungeon_"+dungeonType),
                new Identifier(RepurposedStructures.MODID, "dungeons/"+dungeonType),
                postProcessor,
                ImmutableList.of(
                        Pair.of(new Identifier(RepurposedStructures.MODID, "dungeons/"+dungeonType+"_1"), 1),
                        Pair.of(new Identifier(RepurposedStructures.MODID, "dungeons/"+dungeonType+"_2"), 1),
                        Pair.of(new Identifier(RepurposedStructures.MODID, "dungeons/"+dungeonType+"_3"), 1)
                ));
    }
}
