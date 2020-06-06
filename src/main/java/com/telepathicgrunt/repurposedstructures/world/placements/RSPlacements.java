package com.telepathicgrunt.repurposedstructures.world.placements;

import com.telepathicgrunt.repurposedstructures.utils.RegUtil;

import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class RSPlacements
{
    public static final Placement<CountRangeConfig> RS_DUNGEON_PLACEMENT = new RSDungeonPlacement(CountRangeConfig::deserialize);


    public static void registerPlacements(RegistryEvent.Register<Placement<?>> event) {
	IForgeRegistry<Placement<?>> registry = event.getRegistry();
	RegUtil.register(registry, RS_DUNGEON_PLACEMENT, "rs_dungeon_placement");
    }
}
