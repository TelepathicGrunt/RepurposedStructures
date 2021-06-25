package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.placements.DungeonPlacement;
import com.telepathicgrunt.repurposedstructures.world.placements.MinusEightPlacement;
import com.telepathicgrunt.repurposedstructures.world.placements.OffsetYPlacement;
import com.telepathicgrunt.repurposedstructures.world.placements.SnapToLowerNonAirPlacement;
import com.telepathicgrunt.repurposedstructures.world.placements.configs.SingleIntConfig;
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
    public static final Decorator<RangeDecoratorConfig> DUNGEON_PLACEMENT = new DungeonPlacement(RangeDecoratorConfig.CODEC);
    public static final Decorator<CountConfig> UNLIMITED_COUNT = new CountDecorator(IntProvider.field_33450.fieldOf("count").xmap(CountConfig::new, CountConfig::getCount).codec());
    public static final Decorator<NopeDecoratorConfig> MINUS_EIGHT_PLACEMENT = new MinusEightPlacement(NopeDecoratorConfig.CODEC);
    public static final Decorator<SingleIntConfig> OFFSET_Y_PLACEMENT = new OffsetYPlacement(SingleIntConfig.CODEC);
    public static final Decorator<NopeDecoratorConfig> SNAP_TO_LOWER_NON_AIR_PLACEMENT = new SnapToLowerNonAirPlacement(NopeDecoratorConfig.CODEC);

    public static void registerPlacements() {
        Registry.register(Registry.DECORATOR, new Identifier(RepurposedStructures.MODID, "dungeon_placement"), DUNGEON_PLACEMENT);
        Registry.register(Registry.DECORATOR, new Identifier(RepurposedStructures.MODID, "unlimited_count"), UNLIMITED_COUNT);
        Registry.register(Registry.DECORATOR, new Identifier(RepurposedStructures.MODID, "minus_eight_placement"), MINUS_EIGHT_PLACEMENT);
        Registry.register(Registry.DECORATOR, new Identifier(RepurposedStructures.MODID, "offset_y_placement"), OFFSET_Y_PLACEMENT);
        Registry.register(Registry.DECORATOR, new Identifier(RepurposedStructures.MODID, "snap_to_lower_non_air_placement"), SNAP_TO_LOWER_NON_AIR_PLACEMENT);
    }
}
