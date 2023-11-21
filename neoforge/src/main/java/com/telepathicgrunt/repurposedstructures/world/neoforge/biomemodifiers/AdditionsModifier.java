package com.telepathicgrunt.repurposedstructures.world.neoforge.biomemodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.neoforge.RSBiomeModifiers;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;


public record AdditionsModifier(HolderSet<Biome> biomes, Holder<PlacedFeature> feature, GenerationStep.Decoration step) implements BiomeModifier {

    public static Codec<AdditionsModifier> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Biome.LIST_CODEC.fieldOf("biomes").forGetter(AdditionsModifier::biomes),
            PlacedFeature.CODEC.fieldOf("feature").forGetter(AdditionsModifier::feature),
            GenerationStep.Decoration.CODEC.fieldOf("step").forGetter(AdditionsModifier::step)
        ).apply(builder, AdditionsModifier::new));

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        // add a feature to all specified biomes
        if (phase == Phase.ADD && biomes.contains(biome)) {
            builder.getGenerationSettings().addFeature(step, feature);
        }
    }

    public Codec<? extends BiomeModifier> codec() {
        return RSBiomeModifiers.ADDITIONS_MODIFIER.get();
    }
}