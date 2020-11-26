package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.placements.RSDungeonPlacement;
import com.telepathicgrunt.repurposedstructures.world.placements.RSVinePlacement;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.FeatureSpreadConfig;
import net.minecraft.world.gen.placement.CountPlacement;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.SimplePlacement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class RSPlacements
{
	public static final DeferredRegister<Placement<?>> DECORATORS = DeferredRegister.create(ForgeRegistries.DECORATORS, RepurposedStructures.MODID);
	
	public static final RegistryObject<SimplePlacement<TopSolidRangeConfig>> RS_DUNGEON_PLACEMENT = createDecorator("rs_dungeon_placement", () -> new RSDungeonPlacement(TopSolidRangeConfig.CODEC));
	public static final RegistryObject<Placement<TopSolidRangeConfig>> RS_VINE_PLACEMENT = createDecorator("rs_vine_placement", () -> new RSVinePlacement(TopSolidRangeConfig.CODEC));
	public static final RegistryObject<Placement<FeatureSpreadConfig>> RS_UNLIMITED_COUNT = createDecorator("rs_unlimited_count", () -> new CountPlacement(FeatureSpread.CODEC.fieldOf("count").xmap(FeatureSpreadConfig::new, FeatureSpreadConfig::getCount).codec()));

    private static <P extends Placement<?>> RegistryObject<P> createDecorator(String name, Supplier<P> decorator)
    {
		return DECORATORS.register(name, decorator);
	}
}
