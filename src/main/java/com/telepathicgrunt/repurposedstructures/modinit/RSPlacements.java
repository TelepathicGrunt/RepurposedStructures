package com.telepathicgrunt.repurposedstructures.modinit;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.misc.lootmanager.StructureModdedLootImporter;
import com.telepathicgrunt.repurposedstructures.world.placements.MinusEightPlacement;
import com.telepathicgrunt.repurposedstructures.world.placements.SnapToLowerNonAirPlacement;
import com.telepathicgrunt.repurposedstructures.world.placements.UnlimitedCountPlacement;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class RSPlacements {
	public static final DeferredRegister<PlacementModifierType<?>> PLACEMENT_MODIFIER = DeferredRegister.create(Registry.PLACEMENT_MODIFIER_REGISTRY, RepurposedStructures.MODID);

	public static final RegistryObject<PlacementModifierType<MinusEightPlacement>> MINUS_EIGHT_PLACEMENT = PLACEMENT_MODIFIER.register("minus_eight_placement", () -> () -> MinusEightPlacement.CODEC);
	public static final RegistryObject<PlacementModifierType<UnlimitedCountPlacement>> UNLIMITED_COUNT = PLACEMENT_MODIFIER.register("unlimited_count", () -> () -> UnlimitedCountPlacement.CODEC);
	public static final RegistryObject<PlacementModifierType<SnapToLowerNonAirPlacement>> SNAP_TO_LOWER_NON_AIR_PLACEMENT = PLACEMENT_MODIFIER.register("snap_to_lower_non_air_placement", () -> () -> SnapToLowerNonAirPlacement.CODEC);
}
