package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.features.configs.OceanTemperatureRandomSelectorConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class OceanTemperatureRandomSelector extends Feature<OceanTemperatureRandomSelectorConfig> {
    public OceanTemperatureRandomSelector(Codec<OceanTemperatureRandomSelectorConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<OceanTemperatureRandomSelectorConfig> featurePlaceContext) {
        BlockPos blockPos = featurePlaceContext.origin();
        RandomSource randomSource = featurePlaceContext.random();
        randomSource.setSeed(blockPos.asLong() * blockPos.asLong());
        OceanTemperatureRandomSelectorConfig config = featurePlaceContext.config();
        WorldGenLevel worldGenLevel = featurePlaceContext.level();
        ChunkGenerator chunkGenerator = featurePlaceContext.chunkGenerator();
        Holder<Biome> biome = featurePlaceContext.level().getBiome(blockPos);
        float biomeTemp = biome.value().getBaseTemperature();
        String biomeNamespace = biome.unwrapKey().get().location().getNamespace();
        String biomePath = biome.unwrapKey().get().location().getPath();

        PlacedFeature placedFeature;

        // Neutral temp
        if (!GeneralUtils.nameMatch(biomePath, "hot", "tropic", "warm", "cold", "chilly", "frozen", "snow", "ice", "frost") ||
            (!GeneralUtils.nameExactMatch(biomeNamespace, "minecraft")
            && biomeTemp >= 0.5f
            && biomeTemp < 0.9f))
        {
            if (randomSource.nextFloat() < 0.5f) {
                int i = randomSource.nextInt(config.coldFeatures.size());
                placedFeature = config.coldFeatures.get(i).value();
            }
            else {
                int i = randomSource.nextInt(config.warmFeatures.size());
                placedFeature = config.warmFeatures.get(i).value();
            }
        }
        // Cold temp
        else if (GeneralUtils.nameMatch(biomePath, "cold", "chilly") ||
                (!GeneralUtils.nameExactMatch(biomeNamespace, "minecraft")
                && biomeTemp >= 0.0f
                && biomeTemp < 0.5f))
        {
            int i = randomSource.nextInt(config.coldFeatures.size());
            placedFeature = config.coldFeatures.get(i).value();
        }
        // Frozen temp
        else if (GeneralUtils.nameMatch(biomePath, "frozen", "snow", "ice", "frost") ||
                (!GeneralUtils.nameExactMatch(biomeNamespace, "minecraft")
                && biomeTemp < 0.0f))
        {
            int i = randomSource.nextInt(config.coldFeatures.size());
            placedFeature = config.coldFeatures.get(i).value();
        }
        else {
            int i = randomSource.nextInt(config.warmFeatures.size());
            placedFeature = config.warmFeatures.get(i).value();
        }

        return placedFeature.place(worldGenLevel, chunkGenerator, randomSource, blockPos);
    }
}
