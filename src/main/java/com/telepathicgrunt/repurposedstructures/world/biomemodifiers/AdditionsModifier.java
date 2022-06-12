package com.telepathicgrunt.repurposedstructures.world.biomemodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSBiomeModifiers;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;


public record AdditionsModifier(HolderSet<Biome> biomes, Holder<PlacedFeature> feature, GenerationStep.Decoration step) implements BiomeModifier {

    public static Codec<AdditionsModifier> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Biome.LIST_CODEC.fieldOf("biomes").forGetter(AdditionsModifier::biomes),
            PlacedFeature.CODEC.fieldOf("feature").forGetter(AdditionsModifier::feature),
            GenerationStep.Decoration.CODEC.fieldOf("step").forGetter(AdditionsModifier::step)
        ).apply(builder, AdditionsModifier::new));

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