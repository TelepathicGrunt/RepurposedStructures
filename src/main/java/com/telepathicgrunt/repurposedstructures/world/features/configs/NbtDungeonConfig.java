package com.telepathicgrunt.repurposedstructures.world.features.configs;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.IFeatureConfig;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class NbtDungeonConfig implements IFeatureConfig {
    public static final Codec<NbtDungeonConfig> CODEC = RecordCodecBuilder.<NbtDungeonConfig>create((configInstance) -> configInstance.group(
            Codec.BOOL.fieldOf("replace_air").orElse(false).forGetter(nbtDungeonConfig -> nbtDungeonConfig.replaceAir),
            Codec.intRange(0, Integer.MAX_VALUE).fieldOf("min_air_space").forGetter(nbtFeatureConfig -> nbtFeatureConfig.minAirSpace),
            Codec.intRange(0, Integer.MAX_VALUE).fieldOf("max_air_space").forGetter(nbtFeatureConfig -> nbtFeatureConfig.maxAirSpace),
            Codec.intRange(0, 100).fieldOf("max_num_of_chests").forGetter(nbtFeatureConfig -> nbtFeatureConfig.maxNumOfChests),
            Codec.BOOL.fieldOf("air_requirement_is_now_water").orElse(false).forGetter(nbtDungeonConfig -> nbtDungeonConfig.airRequirementIsNowWater),
            Codec.INT.fieldOf("structure_y_offset").orElse(0).forGetter(nbtFeatureConfig -> nbtFeatureConfig.structureYOffset),
            BlockState.CODEC.fieldOf("loot_block").orElse(Blocks.CHEST.getDefaultState()).forGetter(nbtDungeonConfig -> nbtDungeonConfig.lootBlock),
            ResourceLocation.CODEC.fieldOf("chest_loottable_resourcelocation").forGetter(nbtDungeonConfig -> nbtDungeonConfig.chestIdentifier),
            ResourceLocation.CODEC.fieldOf("rs_spawner_resourcelocation").forGetter(nbtDungeonConfig -> nbtDungeonConfig.rsSpawnerResourcelocation),
            ResourceLocation.CODEC.fieldOf("processors").forGetter(nbtDungeonConfig -> nbtDungeonConfig.processor),
            ResourceLocation.CODEC.fieldOf("post_processors").orElse(new ResourceLocation("minecraft:empty")).forGetter(nbtDungeonConfig -> nbtDungeonConfig.postProcessor),
            Codec.mapPair(ResourceLocation.CODEC.fieldOf("resourcelocation"), Codec.intRange(1, Integer.MAX_VALUE).fieldOf("weight")).codec().listOf().fieldOf("dungeon_nbt_entries").forGetter(nbtFeatureConfig -> nbtFeatureConfig.nbtResourcelocationsAndWeights)
    ).apply(configInstance, NbtDungeonConfig::new))
            .comapFlatMap((nbtDungeonConfig) -> nbtDungeonConfig.maxAirSpace <= nbtDungeonConfig.minAirSpace ?
                    DataResult.error("min_air_space has to be smaller than max_air_space") : DataResult.success(nbtDungeonConfig), Function.identity());

    public final boolean replaceAir;
    public final int minAirSpace;
    public final int maxAirSpace;
    public final int maxNumOfChests;
    public final ResourceLocation chestIdentifier;
    public final List<Pair<ResourceLocation, Integer>> nbtResourcelocationsAndWeights;
    public final ResourceLocation rsSpawnerResourcelocation;
    public final ResourceLocation processor;
    public final ResourceLocation postProcessor;
    public final boolean airRequirementIsNowWater;
    public final int structureYOffset;
    public final BlockState lootBlock;

    public NbtDungeonConfig(boolean replaceAir, int minAirSpace, int maxAirSpace,
                            int maxNumOfChests, boolean airRequirementIsNowWater, int structureYOffset,
                            BlockState lootBlock, ResourceLocation chestIdentifier,
                            ResourceLocation rsSpawnerResourceLocation, ResourceLocation processor, ResourceLocation postProcessor,
                            List<Pair<ResourceLocation, Integer>> nbtResourceLocationsAndWeights)
    {
        this.replaceAir = replaceAir;
        this.minAirSpace = minAirSpace;
        this.maxAirSpace = maxAirSpace;
        this.maxNumOfChests = maxNumOfChests;
        this.nbtResourcelocationsAndWeights = nbtResourceLocationsAndWeights;
        this.chestIdentifier = chestIdentifier;
        this.rsSpawnerResourcelocation = rsSpawnerResourceLocation;
        this.processor = processor;
        this.postProcessor = postProcessor;
        this.airRequirementIsNowWater = airRequirementIsNowWater;
        this.structureYOffset = structureYOffset;
        this.lootBlock = lootBlock;
    }

    public NbtDungeonConfig(String dungeonType, ResourceLocation postProcessor){
        this(dungeonType, dungeonType, postProcessor);
    }

    public NbtDungeonConfig(String dungeonType, String spawnerType, ResourceLocation postProcessor){
        this(dungeonType, spawnerType, postProcessor, 13, false, 0);
    }

    public NbtDungeonConfig(String dungeonType, String spawnerType, ResourceLocation processor, ResourceLocation postProcessor){
        this(dungeonType, spawnerType, processor, postProcessor, 13, false, 0);
    }

    public NbtDungeonConfig(String dungeonType, String spawnerType, ResourceLocation postProcessor,
                            int maxAirSpace, boolean airRequirementIsNowWater, int structureYOffset){
        this(dungeonType, spawnerType, new ResourceLocation(RepurposedStructures.MODID, "dungeons/"+dungeonType), postProcessor, maxAirSpace, airRequirementIsNowWater, structureYOffset);
    }

    public NbtDungeonConfig(String dungeonType, String spawnerType, ResourceLocation processor, ResourceLocation postProcessor,
                            int maxAirSpace, boolean airRequirementIsNowWater, int structureYOffset){
        this(false,1, maxAirSpace, 2,
                airRequirementIsNowWater,
                structureYOffset, Blocks.CHEST.getDefaultState(),
                new ResourceLocation(RepurposedStructures.MODID, "chests/dungeon/"+dungeonType),
                new ResourceLocation(RepurposedStructures.MODID, "dungeon_"+spawnerType),
                processor,
                postProcessor,
                ImmutableList.of(
                        Pair.of(new ResourceLocation(RepurposedStructures.MODID, "dungeons/"+dungeonType+"_1"), 1),
                        Pair.of(new ResourceLocation(RepurposedStructures.MODID, "dungeons/"+dungeonType+"_2"), 1),
                        Pair.of(new ResourceLocation(RepurposedStructures.MODID, "dungeons/"+dungeonType+"_3"), 1)
                ));
    }

    public NbtDungeonConfig(String dungeonType, ResourceLocation postProcessor,
                            int maxAirSpace, BlockState lootBlock){
        this(false, 1, maxAirSpace, 2,
                false, 0, lootBlock,
                new ResourceLocation(RepurposedStructures.MODID, "chests/dungeon/"+dungeonType),
                new ResourceLocation(RepurposedStructures.MODID, "dungeon_"+dungeonType),
                new ResourceLocation(RepurposedStructures.MODID, "dungeons/"+dungeonType),
                postProcessor,
                ImmutableList.of(
                        Pair.of(new ResourceLocation(RepurposedStructures.MODID, "dungeons/"+dungeonType+"_1"), 1),
                        Pair.of(new ResourceLocation(RepurposedStructures.MODID, "dungeons/"+dungeonType+"_2"), 1),
                        Pair.of(new ResourceLocation(RepurposedStructures.MODID, "dungeons/"+dungeonType+"_3"), 1)
                ));
    }
}
