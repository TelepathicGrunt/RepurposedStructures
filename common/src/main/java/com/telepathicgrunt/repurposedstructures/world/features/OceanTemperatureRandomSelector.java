package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public record OceanTemperatureRandomSelector(
        HolderSet<PlacedFeature> warmFeatures,
        HolderSet<PlacedFeature> coldFeatures
) implements Feature {

    public static final MapCodec<OceanTemperatureRandomSelector> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            ExtraCodecs.nonEmptyHolderSet(PlacedFeature.LIST_CODEC).fieldOf("warm_features").forGetter(selector -> selector.warmFeatures),
            ExtraCodecs.nonEmptyHolderSet(PlacedFeature.LIST_CODEC).fieldOf("cold_features").forGetter(selector -> selector.coldFeatures)
    ).apply(instance, OceanTemperatureRandomSelector::new));

    @Override
    public MapCodec<OceanTemperatureRandomSelector> codec() {
        return CODEC;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {

        random.setSeed(origin.asLong() * origin.asLong());
        Holder<Biome> biome = level.getBiome(origin);
        float biomeTemp = biome.value().getBaseTemperature();
        String biomeNamespace = biome.unwrapKey().get().identifier().getNamespace();
        String biomePath = biome.unwrapKey().get().identifier().getPath();

        PlacedFeature placedFeature;

        // Neutral temp
        if (!GeneralUtils.nameMatch(biomePath, "hot", "tropic", "warm", "cold", "chilly", "frozen", "snow", "ice", "frost") ||
                (!GeneralUtils.nameExactMatch(biomeNamespace, "minecraft")
                        && biomeTemp >= 0.5f
                        && biomeTemp < 0.9f)) {
            if (random.nextFloat() < 0.5f) {
                int i = random.nextInt(coldFeatures.size());
                placedFeature = coldFeatures.get(i).value();
            } else {
                int i = random.nextInt(warmFeatures.size());
                placedFeature = warmFeatures.get(i).value();
            }
        }
        // Cold temp
        else if (GeneralUtils.nameMatch(biomePath, "cold", "chilly") ||
                (!GeneralUtils.nameExactMatch(biomeNamespace, "minecraft")
                        && biomeTemp >= 0.0f
                        && biomeTemp < 0.5f)) {
            int i = random.nextInt(coldFeatures.size());
            placedFeature = coldFeatures.get(i).value();
        }
        // Frozen temp
        else if (GeneralUtils.nameMatch(biomePath, "frozen", "snow", "ice", "frost") ||
                (!GeneralUtils.nameExactMatch(biomeNamespace, "minecraft")
                        && biomeTemp < 0.0f)) {
            int i = random.nextInt(coldFeatures.size());
            placedFeature = coldFeatures.get(i).value();
        } else {
            int i = random.nextInt(warmFeatures.size());
            placedFeature = warmFeatures.get(i).value();
        }

        return placedFeature.place(level, chunkGenerator, random, origin);
    }
}
