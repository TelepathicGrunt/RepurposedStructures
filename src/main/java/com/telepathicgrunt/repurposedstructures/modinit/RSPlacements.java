package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.placements.RSDungeonPlacement;
import com.telepathicgrunt.repurposedstructures.world.placements.RSMinusEightPlacement;
import com.telepathicgrunt.repurposedstructures.world.placements.RSVinePlacement;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.FeatureSpreadConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.placement.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class RSPlacements
{
	public static final DeferredRegister<Placement<?>> DECORATORS = DeferredRegister.create(ForgeRegistries.DECORATORS, RepurposedStructures.MODID);
	
	public static final RegistryObject<SimplePlacement<TopSolidRangeConfig>> RS_DUNGEON_PLACEMENT = DECORATORS.register("rs_dungeon_placement", () -> new RSDungeonPlacement(TopSolidRangeConfig.CODEC));
	public static final RegistryObject<Placement<TopSolidRangeConfig>> RS_VINE_PLACEMENT = DECORATORS.register("rs_vine_placement", () -> new RSVinePlacement(TopSolidRangeConfig.CODEC));
	public static final RegistryObject<Placement<FeatureSpreadConfig>> RS_UNLIMITED_COUNT = DECORATORS.register("rs_unlimited_count", () -> new CountPlacement(FeatureSpread.CODEC.fieldOf("count").xmap(FeatureSpreadConfig::new, FeatureSpreadConfig::count).codec()));
	public static final RegistryObject<Placement<NoPlacementConfig>> RS_MINUS_EIGHT_PLACEMENT = DECORATORS.register("rs_minus_eight_placement", () -> new RSMinusEightPlacement(NoPlacementConfig.CODEC));
}
