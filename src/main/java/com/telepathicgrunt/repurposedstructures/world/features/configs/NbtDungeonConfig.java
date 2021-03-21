package com.telepathicgrunt.repurposedstructures.world.features.configs;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
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
            BlockState.CODEC.listOf().optionalFieldOf("blocks_to_always_place").forGetter((config) -> config.blocksToAlwaysPlace),
            Codec.INT.fieldOf("structure_y_offset").orElse(0).forGetter(nbtFeatureConfig -> nbtFeatureConfig.structureYOffset),
            BlockState.CODEC.fieldOf("loot_block").orElse(Blocks.CHEST.getDefaultState()).forGetter(nbtDungeonConfig -> nbtDungeonConfig.lootBlock),
            ResourceLocation.CODEC.fieldOf("chest_loottable_resourcelocation").forGetter(nbtDungeonConfig -> nbtDungeonConfig.chestResourceLocation),
            ResourceLocation.CODEC.fieldOf("rsSpawnerResourcelocation").forGetter(nbtDungeonConfig -> nbtDungeonConfig.rsSpawnerResourcelocation),
            ResourceLocation.CODEC.fieldOf("processors").forGetter(nbtDungeonConfig -> nbtDungeonConfig.processor),
            Codec.mapPair(ResourceLocation.CODEC.fieldOf("resourcelocation"), Codec.intRange(1, Integer.MAX_VALUE).fieldOf("weight")).codec().listOf().fieldOf("dungeon_nbt_entries").forGetter(nbtFeatureConfig -> nbtFeatureConfig.nbtResourcelocationsAndWeights)
    ).apply(configInstance, NbtDungeonConfig::new))
            .comapFlatMap((nbtDungeonConfig) -> nbtDungeonConfig.maxAirSpace <= nbtDungeonConfig.minAirSpace ?
                    DataResult.error("min_air_space has to be smaller than max_air_space") : DataResult.success(nbtDungeonConfig), Function.identity());

    public final boolean replaceAir;
    public final int minAirSpace;
    public final int maxAirSpace;
    public final int maxNumOfChests;
    public final ResourceLocation chestResourceLocation;
    public final List<Pair<ResourceLocation, Integer>> nbtResourcelocationsAndWeights;
    public final ResourceLocation rsSpawnerResourcelocation;
    public final ResourceLocation processor;
    public final boolean airRequirementIsNowWater;
    public final Optional<List<BlockState>> blocksToAlwaysPlace;
    public final int structureYOffset;
    public final BlockState lootBlock;

    public NbtDungeonConfig(boolean replaceAir, int minAirSpace, int maxAirSpace,
                            int maxNumOfChests, boolean airRequirementIsNowWater,
                            Optional<List<BlockState>> blocksToAlwaysPlace, int structureYOffset,
                            BlockState lootBlock, ResourceLocation chestResourceLocation,
                            ResourceLocation rsSpawnerResourcelocation, ResourceLocation processor,
                            List<Pair<ResourceLocation, Integer>> nbtResourcelocationsAndWeights)
    {
        this.replaceAir = replaceAir;
        this.minAirSpace = minAirSpace;
        this.maxAirSpace = maxAirSpace;
        this.maxNumOfChests = maxNumOfChests;
        this.chestResourceLocation = chestResourceLocation;
        this.nbtResourcelocationsAndWeights = nbtResourcelocationsAndWeights;
        this.rsSpawnerResourcelocation = rsSpawnerResourcelocation;
        this.processor = processor;
        this.airRequirementIsNowWater = airRequirementIsNowWater;
        this.blocksToAlwaysPlace = blocksToAlwaysPlace;
        this.structureYOffset = structureYOffset;
        this.lootBlock = lootBlock;
    }
}
