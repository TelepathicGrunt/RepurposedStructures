package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.placements.OffsetYPlacement;
import com.telepathicgrunt.repurposedstructures.world.placements.RSDungeonPlacement;
import com.telepathicgrunt.repurposedstructures.world.placements.RSMinusEightPlacement;
import com.telepathicgrunt.repurposedstructures.world.placements.SnapToLowerNonAirPlacement;
import com.telepathicgrunt.repurposedstructures.world.placements.configs.SingleIntConfig;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.FeatureSpreadConfig;
import net.minecraft.world.gen.placement.CountPlacement;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.SimplePlacement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RSPlacements
{
	public static final DeferredRegister<Placement<?>> DECORATORS = DeferredRegister.create(ForgeRegistries.DECORATORS, RepurposedStructures.MODID);
	
	public static final RegistryObject<SimplePlacement<TopSolidRangeConfig>> RS_DUNGEON_PLACEMENT = DECORATORS.register("rs_dungeon_placement", () -> new RSDungeonPlacement(TopSolidRangeConfig.CODEC));
	public static final RegistryObject<Placement<SingleIntConfig>> OFFSET_Y_PLACEMENT = DECORATORS.register("offset_y_placement", () -> new OffsetYPlacement(SingleIntConfig.CODEC));
	public static final RegistryObject<Placement<NoPlacementConfig>> SNAP_TO_LOWER_NON_AIR_PLACEMENT = DECORATORS.register("snap_to_lower_non_air_placement", () -> new SnapToLowerNonAirPlacement(NoPlacementConfig.CODEC));
	public static final RegistryObject<Placement<FeatureSpreadConfig>> RS_UNLIMITED_COUNT = DECORATORS.register("rs_unlimited_count", () -> new CountPlacement(FeatureSpread.CODEC.fieldOf("count").xmap(FeatureSpreadConfig::new, FeatureSpreadConfig::count).codec()));
	public static final RegistryObject<Placement<NoPlacementConfig>> RS_MINUS_EIGHT_PLACEMENT = DECORATORS.register("rs_minus_eight_placement", () -> new RSMinusEightPlacement(NoPlacementConfig.CODEC));
}
