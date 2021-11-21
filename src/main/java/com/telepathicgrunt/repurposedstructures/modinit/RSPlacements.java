package com.telepathicgrunt.repurposedstructures.modinit;

import com.google.common.base.Suppliers;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.placements.MinusEightPlacement;
import com.telepathicgrunt.repurposedstructures.world.placements.SnapToLowerNonAirPlacement;
import com.telepathicgrunt.repurposedstructures.world.placements.UnlimitedCountPlacement;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

public final class RSPlacements {
    private RSPlacements() {}

    public static final PlacementModifierType<?> MINUS_EIGHT_PLACEMENT = (PlacementModifierType<?>) Suppliers.memoize(() -> MinusEightPlacement.CODEC);
    public static final PlacementModifierType<?> UNLIMITED_COUNT = (PlacementModifierType<?>) Suppliers.memoize(() -> UnlimitedCountPlacement.CODEC);
    public static final PlacementModifierType<?> SNAP_TO_LOWER_NON_AIR_PLACEMENT = (PlacementModifierType<?>) Suppliers.memoize(() -> SnapToLowerNonAirPlacement.CODEC);

    public static void registerPlacements() {
        Registry.register(Registry.PLACEMENT_MODIFIERS, new ResourceLocation(RepurposedStructures.MODID, "dungeon_placement"), MINUS_EIGHT_PLACEMENT);
        Registry.register(Registry.PLACEMENT_MODIFIERS, new ResourceLocation(RepurposedStructures.MODID, "unlimited_count"), UNLIMITED_COUNT);
        Registry.register(Registry.PLACEMENT_MODIFIERS, new ResourceLocation(RepurposedStructures.MODID, "snap_to_lower_non_air_placement"), SNAP_TO_LOWER_NON_AIR_PLACEMENT);
    }
}
