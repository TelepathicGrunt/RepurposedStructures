package com.telepathicgrunt.repurposedstructures.world.placements;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;

public class RSPlacements
{
    public static final Placement<CountRangeConfig> RS_DUNGEON_PLACEMENT = new RSDungeonPlacement(CountRangeConfig.CODEC);
    public static final Placement<CountRangeConfig> RS_VINE_PLACEMENT = new RSVinePlacement(CountRangeConfig.CODEC);

    public static void registerPlacements(final RegistryEvent.Register<Placement<?>> event) {
        Registry.register(Registry.DECORATOR, RepurposedStructures.MODID+":rs_dungeon_placement", RS_DUNGEON_PLACEMENT);
        Registry.register(Registry.DECORATOR, RepurposedStructures.MODID+":rs_vine_placement", RS_VINE_PLACEMENT);
    }
}
