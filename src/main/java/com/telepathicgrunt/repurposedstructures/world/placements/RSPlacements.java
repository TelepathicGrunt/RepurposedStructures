package com.telepathicgrunt.repurposedstructures.world.placements;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;

public class RSPlacements
{
    public static final Decorator<RangeDecoratorConfig> RS_DUNGEON_PLACEMENT = new RSDungeonPlacement(RangeDecoratorConfig.CODEC);


    public static void registerPlacements() {
	Registry.register(Registry.DECORATOR, "rs_dungeon_placement", RS_DUNGEON_PLACEMENT);
    }
}
