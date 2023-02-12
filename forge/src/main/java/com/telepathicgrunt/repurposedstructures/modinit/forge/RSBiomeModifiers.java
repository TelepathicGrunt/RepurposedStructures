package com.telepathicgrunt.repurposedstructures.modinit.forge;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.forge.biomemodifiers.AdditionsModifier;
import com.telepathicgrunt.repurposedstructures.world.forge.biomemodifiers.AdditionsTemperatureModifier;
import com.telepathicgrunt.repurposedstructures.world.forge.biomemodifiers.RemovalsModifier;
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
