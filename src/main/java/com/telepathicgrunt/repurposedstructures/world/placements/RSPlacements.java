package com.telepathicgrunt.repurposedstructures.world.placements;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.SimplePlacement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;

public class RSPlacements
{
    public static final SimplePlacement<TopSolidRangeConfig> RS_DUNGEON_PLACEMENT = new RSDungeonPlacement(TopSolidRangeConfig.CODEC);
    public static final Placement<TopSolidRangeConfig> RS_VINE_PLACEMENT = new RSVinePlacement(TopSolidRangeConfig.CODEC);

    public static void registerPlacements() {
        Registry.register(Registry.DECORATOR, RepurposedStructures.MODID+":rs_dungeon_placement", RS_DUNGEON_PLACEMENT);
        Registry.register(Registry.DECORATOR, RepurposedStructures.MODID+":rs_vine_placement", RS_VINE_PLACEMENT);
    }
}
