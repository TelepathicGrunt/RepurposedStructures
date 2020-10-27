package com.telepathicgrunt.repurposedstructures.world.placements;

import java.util.function.Supplier;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;

import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.SimplePlacement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RSPlacements
{
	public static final DeferredRegister<Placement<?>> DECORATORS = DeferredRegister.create(ForgeRegistries.DECORATORS, RepurposedStructures.MODID);
	
	public static final RegistryObject<SimplePlacement<TopSolidRangeConfig>> RS_DUNGEON_PLACEMENT = createDecorator("rs_dungeon_placement", () -> new RSDungeonPlacement(TopSolidRangeConfig.CODEC));
    public static final RegistryObject<Placement<TopSolidRangeConfig>> RS_VINE_PLACEMENT = createDecorator("rs_vine_placement", () -> new RSVinePlacement(TopSolidRangeConfig.CODEC));

    private static <P extends Placement<?>> RegistryObject<P> createDecorator(String name, Supplier<P> decorator)
    {
		return DECORATORS.register(name, decorator);
	}
}
