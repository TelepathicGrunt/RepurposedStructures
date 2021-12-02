package com.telepathicgrunt.repurposedstructures.modinit;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.placements.MinusEightPlacement;
import com.telepathicgrunt.repurposedstructures.world.placements.SnapToLowerNonAirPlacement;
import com.telepathicgrunt.repurposedstructures.world.placements.UnlimitedCountPlacement;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

public final class RSPlacements {
	private RSPlacements() {}

	public static PlacementModifierType<?> MINUS_EIGHT_PLACEMENT;
	public static PlacementModifierType<?> UNLIMITED_COUNT;
	public static PlacementModifierType<?> SNAP_TO_LOWER_NON_AIR_PLACEMENT;

	public static void registerPlacements() {
		MINUS_EIGHT_PLACEMENT = register(new ResourceLocation(RepurposedStructures.MODID, "minus_eight_placement"), MinusEightPlacement.CODEC);
		UNLIMITED_COUNT = register(new ResourceLocation(RepurposedStructures.MODID, "unlimited_count"), UnlimitedCountPlacement.CODEC);
		SNAP_TO_LOWER_NON_AIR_PLACEMENT = register(new ResourceLocation(RepurposedStructures.MODID, "snap_to_lower_non_air_placement"), SnapToLowerNonAirPlacement.CODEC);
	}

	private static <P extends PlacementModifier> PlacementModifierType<P> register(ResourceLocation resourceLocation, Codec<P> codec) {
		return Registry.register(Registry.PLACEMENT_MODIFIERS, resourceLocation, () -> codec);
	}
}
