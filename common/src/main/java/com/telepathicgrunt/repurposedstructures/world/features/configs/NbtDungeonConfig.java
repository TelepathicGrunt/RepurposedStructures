package com.telepathicgrunt.repurposedstructures.world.features.configs;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class NbtDungeonConfig implements FeatureConfiguration {
    public static final Codec<NbtDungeonConfig> CODEC = RecordCodecBuilder.<NbtDungeonConfig>create((configInstance) -> configInstance.group(
            Codec.BOOL.fieldOf("replace_air").orElse(false).forGetter(nbtDungeonConfig -> nbtDungeonConfig.replaceAir),
            Codec.intRange(0, Integer.MAX_VALUE).fieldOf("min_air_space").forGetter(nbtFeatureConfig -> nbtFeatureConfig.minAirSpace),
            Codec.intRange(0, Integer.MAX_VALUE).fieldOf("max_air_space").forGetter(nbtFeatureConfig -> nbtFeatureConfig.maxAirSpace),
            Codec.intRange(0, 100).fieldOf("max_num_of_loot_blocks").forGetter(nbtFeatureConfig -> nbtFeatureConfig.maxNumOfChests),
            Codec.BOOL.fieldOf("air_requirement_is_now_water").orElse(false).forGetter(nbtDungeonConfig -> nbtDungeonConfig.airRequirementIsNowWater),
            Codec.INT.fieldOf("structure_y_offset").orElse(0).forGetter(nbtFeatureConfig -> nbtFeatureConfig.structureYOffset),
            BuiltInRegistries.BLOCK.byNameCodec().fieldOf("loot_block").orElse(Blocks.CHEST).forGetter(nbtDungeonConfig -> nbtDungeonConfig.lootBlock),
            Identifier.CODEC.fieldOf("loot_block_loottable_Identifier").forGetter(nbtDungeonConfig -> nbtDungeonConfig.chestIdentifier),
            Identifier.CODEC.fieldOf("rs_spawner_Identifier").forGetter(nbtDungeonConfig -> nbtDungeonConfig.rsSpawnerIdentifier),
            Identifier.CODEC.fieldOf("processors").forGetter(nbtDungeonConfig -> nbtDungeonConfig.processor),
            Identifier.CODEC.fieldOf("post_processors").orElse(Identifier.fromNamespaceAndPath("minecraft", "empty")).forGetter(nbtDungeonConfig -> nbtDungeonConfig.postProcessor),
            Codec.mapPair(Identifier.CODEC.fieldOf("Identifier"), Codec.intRange(1, Integer.MAX_VALUE).fieldOf("weight")).codec().listOf().fieldOf("dungeon_nbt_entries").forGetter(nbtFeatureConfig -> nbtFeatureConfig.nbtIdentifiersAndWeights),
            Codec.floatRange(0, 1).optionalFieldOf("chance_of_spawning_loot_block_at_spot").forGetter(nbtFeatureConfig -> nbtFeatureConfig.chanceOfSpawningLootBlockAtSpot)
    ).apply(configInstance, NbtDungeonConfig::new))
            .comapFlatMap((nbtDungeonConfig) -> nbtDungeonConfig.maxAirSpace <= nbtDungeonConfig.minAirSpace ?
                    DataResult.error(() -> "min_air_space has to be smaller than max_air_space") : DataResult.success(nbtDungeonConfig), Function.identity());

    public final boolean replaceAir;
    public final int minAirSpace;
    public final int maxAirSpace;
    public final int maxNumOfChests;
    public final Identifier chestIdentifier;
    public final List<Pair<Identifier, Integer>> nbtIdentifiersAndWeights;
    public final Identifier rsSpawnerIdentifier;
    public final Identifier processor;
    public final Identifier postProcessor;
    public final boolean airRequirementIsNowWater;
    public final int structureYOffset;
    public final Block lootBlock;
    public final Optional<Float> chanceOfSpawningLootBlockAtSpot;

    public NbtDungeonConfig(boolean replaceAir, int minAirSpace, int maxAirSpace,
                            int maxNumOfChests, boolean airRequirementIsNowWater, int structureYOffset,
                            Block lootBlock, Identifier chestIdentifier,
                            Identifier rsSpawnerIdentifier, Identifier processor, Identifier postProcessor,
                            List<Pair<Identifier, Integer>> nbtIdentifiersAndWeights, Optional<Float> chanceOfSpawningLootBlockAtSpot)
    {
        this.replaceAir = replaceAir;
        this.minAirSpace = minAirSpace;
        this.maxAirSpace = maxAirSpace;
        this.maxNumOfChests = maxNumOfChests;
        this.chestIdentifier = chestIdentifier;
        this.nbtIdentifiersAndWeights = nbtIdentifiersAndWeights;
        this.rsSpawnerIdentifier = rsSpawnerIdentifier;
        this.processor = processor;
        this.postProcessor = postProcessor;
        this.airRequirementIsNowWater = airRequirementIsNowWater;
        this.structureYOffset = structureYOffset;
        this.lootBlock = lootBlock;
        this.chanceOfSpawningLootBlockAtSpot = chanceOfSpawningLootBlockAtSpot;
    }
}
