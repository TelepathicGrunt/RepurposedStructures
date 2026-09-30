package com.telepathicgrunt.repurposedstructures.world.neoforge.biomemodifiers;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.neoforge.RSBiomeModifiers;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;

public record RemovalsModifier(HolderSet<Biome> biomes, Holder<PlacedFeature> feature, GenerationStep.Decoration step) implements BiomeModifier {

    public static MapCodec<RemovalsModifier> CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(
            Biome.LIST_CODEC.fieldOf("biomes").forGetter(RemovalsModifier::biomes),
            PlacedFeature.CODEC.fieldOf("feature").forGetter(RemovalsModifier::feature),
            GenerationStep.Decoration.CODEC.fieldOf("step").forGetter(RemovalsModifier::step)
        ).apply(builder, RemovalsModifier::new));

    @Override
    public void modify(RegistryAccess registryAccess, Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        // add a feature to all specified biomes
        if (phase == Phase.REMOVE && biomes.contains(biome)) {
            builder.getGenerationSettings().getFeatures(step).remove(feature);
        }
    }

    public MapCodec<? extends BiomeModifier> codec() {
        return RSBiomeModifiers.REMOVALS_MODIFIER.get();
    }
}