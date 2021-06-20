package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.placements.RSDungeonPlacement;
import com.telepathicgrunt.repurposedstructures.world.placements.RSMinusEightPlacement;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.CountConfig;
import net.minecraft.world.gen.decorator.CountDecorator;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.NopeDecoratorConfig;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;

public class RSPlacements
{
    public static final Decorator<RangeDecoratorConfig> RS_DUNGEON_PLACEMENT = new RSDungeonPlacement(RangeDecoratorConfig.CODEC);
    public static final Decorator<CountConfig> RS_UNLIMITED_COUNT = new CountDecorator(IntProvider.field_33450.fieldOf("count").xmap(CountConfig::new, CountConfig::getCount).codec());
    public static final Decorator<NopeDecoratorConfig> RS_MINUS_EIGHT_PLACEMENT = new RSMinusEightPlacement(NopeDecoratorConfig.CODEC);

    public static void registerPlacements() {
        Registry.register(Registry.DECORATOR, new Identifier(RepurposedStructures.MODID, "rs_dungeon_placement"), RS_DUNGEON_PLACEMENT);
        Registry.register(Registry.DECORATOR, new Identifier(RepurposedStructures.MODID, "rs_unlimited_count"), RS_UNLIMITED_COUNT);
        Registry.register(Registry.DECORATOR, new Identifier(RepurposedStructures.MODID, "rs_minus_eight_placement"), RS_MINUS_EIGHT_PLACEMENT);
    }
}
