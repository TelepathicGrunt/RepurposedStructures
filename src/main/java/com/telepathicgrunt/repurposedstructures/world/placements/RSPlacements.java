package com.telepathicgrunt.repurposedstructures.world.placements;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;

public class RSPlacements
{
    public static final Decorator<RangeDecoratorConfig> RS_DUNGEON_PLACEMENT = new RSDungeonPlacement(RangeDecoratorConfig.CODEC);
    public static final Decorator<RangeDecoratorConfig> RS_VINE_PLACEMENT = new RSVinePlacement(RangeDecoratorConfig.CODEC);

    public static void registerPlacements() {
        Registry.register(Registry.DECORATOR, RepurposedStructures.MODID+":rs_dungeon_placement", RS_DUNGEON_PLACEMENT);
        Registry.register(Registry.DECORATOR, RepurposedStructures.MODID+":rs_vine_placement", RS_VINE_PLACEMENT);
    }
}
