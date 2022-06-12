package com.telepathicgrunt.repurposedstructures.modinit;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.biomemodifiers.AdditionsModifier;
import com.telepathicgrunt.repurposedstructures.world.biomemodifiers.AdditionsTemperatureModifier;
import com.telepathicgrunt.repurposedstructures.world.biomemodifiers.RemovalsModifier;
import com.telepathicgrunt.repurposedstructures.world.features.*;
import com.telepathicgrunt.repurposedstructures.world.features.configs.GenericMobConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.MinecartConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.MineshaftSupportConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.NbtDungeonConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.NbtFeatureConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureRangeConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetAndLengthConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetAndRangeConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetChanceConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetLengthRangeConfig;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class RSBiomeModifiers {
	public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, RepurposedStructures.MODID);

	public static final RegistryObject<Codec<? extends BiomeModifier>> ADDITIONS_MODIFIER = BIOME_MODIFIER_SERIALIZERS.register("additions_modifier", () -> AdditionsModifier.CODEC);
	public static final RegistryObject<Codec<? extends BiomeModifier>> ADDITIONS_TEMPERATURE_MODIFIER = BIOME_MODIFIER_SERIALIZERS.register("additions_temperature_modifier", () -> AdditionsTemperatureModifier.CODEC);
	public static final RegistryObject<Codec<? extends BiomeModifier>> REMOVALS_MODIFIER = BIOME_MODIFIER_SERIALIZERS.register("removals_modifier", () -> RemovalsModifier.CODEC);
}
