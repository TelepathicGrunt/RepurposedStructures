package com.telepathicgrunt.repurposedstructures.world.neoforge.biomemodifiers;

import com.google.common.collect.Maps;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.neoforge.RSBiomeModifiers;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

public record AdditionsTemperatureModifier(HolderSet<Biome> biomes, Holder<PlacedFeature> feature, GenerationStep.Decoration step, TEMPERATURE_RANGE temperatureRange) implements BiomeModifier {

    public static Codec<AdditionsTemperatureModifier> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Biome.LIST_CODEC.fieldOf("biomes").forGetter(AdditionsTemperatureModifier::biomes),
            PlacedFeature.CODEC.fieldOf("feature").forGetter(AdditionsTemperatureModifier::feature),
            GenerationStep.Decoration.CODEC.fieldOf("step").forGetter(AdditionsTemperatureModifier::step),
            StringRepresentable.fromEnum(TEMPERATURE_RANGE::values).fieldOf("biome_temperature_allowed").stable().forGetter(AdditionsTemperatureModifier::temperatureRange)
        ).apply(builder, AdditionsTemperatureModifier::new));

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        // add a feature to all specified biomes
        if (phase == Phase.ADD && biomes.contains(biome)) {
            String biomeNamespace = biome.unwrapKey().get().location().getNamespace();
            String biomePath = biome.unwrapKey().get().location().getPath();
            float biomeTemp = biome.value().getBaseTemperature();
            switch (temperatureRange) {
                case WARM -> {
                    if ((GeneralUtils.nameMatch(biomePath, "hot", "tropic", "warm") && !GeneralUtils.nameMatch(biomePath, "lukewarm")) ||
                        (!GeneralUtils.nameExactMatch(biomeNamespace, "minecraft") && biomeTemp >= 1.5f))
                    {
                        builder.getGenerationSettings().addFeature(step, feature);
                    }
                }
                case LUKEWARM -> {
                    if (GeneralUtils.nameMatch(biomePath, "lukewarm") ||
                        (!GeneralUtils.nameExactMatch(biomeNamespace, "minecraft")
                         && biomeTemp >= 0.9f
                         && biomeTemp < 1.5f))
                    {
                        builder.getGenerationSettings().addFeature(step, feature);
                    }
                }
                case NEUTRAL -> {
                    if (!GeneralUtils.nameMatch(biomePath, "hot", "tropic", "warm", "cold", "chilly", "frozen", "snow", "ice", "frost") ||
                        (!GeneralUtils.nameExactMatch(biomeNamespace, "minecraft")
                        && biomeTemp >= 0.5f
                        && biomeTemp < 0.9f))
                    {
                        builder.getGenerationSettings().addFeature(step, feature);
                    }
                }
                case COLD -> {
                    if (GeneralUtils.nameMatch(biomePath, "cold", "chilly") ||
                        (!GeneralUtils.nameExactMatch(biomeNamespace, "minecraft")
                        && biomeTemp >= 0.0f
                        && biomeTemp < 0.5f))
                    {
                        builder.getGenerationSettings().addFeature(step, feature);
                    }
                }
                case FROZEN -> {
                    if (GeneralUtils.nameMatch(biomePath, "frozen", "snow", "ice", "frost") ||
                        (!GeneralUtils.nameExactMatch(biomeNamespace, "minecraft")
                        && biomeTemp < 0.0f))
                    {
                        builder.getGenerationSettings().addFeature(step, feature);
                    }
                }
            }
        }
    }

    public Codec<? extends BiomeModifier> codec() {
        return RSBiomeModifiers.ADDITIONS_TEMPERATURE_MODIFIER.get();
    }

    public enum TEMPERATURE_RANGE implements StringRepresentable {
        WARM("WARM"),
        LUKEWARM("LUKEWARM"),
        NEUTRAL("NEUTRAL"),
        COLD("COLD"),
        FROZEN("FROZEN");

        private final String name;

        TEMPERATURE_RANGE(String name) {
            this.name = name;
        }

        private static final Map<String, TEMPERATURE_RANGE> BY_NAME = Util.make(Maps.newHashMap(), (hashMap) -> {
            TEMPERATURE_RANGE[] var1 = values();
            for (TEMPERATURE_RANGE type : var1) {
                hashMap.put(type.name, type);
            }
        });

        public static TEMPERATURE_RANGE byName(String name) {
            return BY_NAME.get(name.toUpperCase(Locale.ROOT));
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }
    }
}