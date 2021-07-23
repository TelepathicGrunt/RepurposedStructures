package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.placements.DungeonPlacement;
import com.telepathicgrunt.repurposedstructures.world.placements.MinusEightPlacement;
import com.telepathicgrunt.repurposedstructures.world.placements.OffsetYPlacement;
import com.telepathicgrunt.repurposedstructures.world.placements.SnapToLowerNonAirPlacement;
import com.telepathicgrunt.repurposedstructures.world.placements.configs.SingleIntConfig;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.feature.configurations.CountConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneDecoratorConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RangeDecoratorConfiguration;
import net.minecraft.world.level.levelgen.placement.CountDecorator;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;

public class RSPlacements
{
    public static final FeatureDecorator<RangeDecoratorConfiguration> DUNGEON_PLACEMENT = new DungeonPlacement(RangeDecoratorConfiguration.CODEC);
    public static final FeatureDecorator<CountConfiguration> UNLIMITED_COUNT = new CountDecorator(IntProvider.NON_NEGATIVE_CODEC.fieldOf("count").xmap(CountConfiguration::new, CountConfiguration::count).codec());
    public static final FeatureDecorator<NoneDecoratorConfiguration> MINUS_EIGHT_PLACEMENT = new MinusEightPlacement(NoneDecoratorConfiguration.CODEC);
    public static final FeatureDecorator<SingleIntConfig> OFFSET_Y_PLACEMENT = new OffsetYPlacement(SingleIntConfig.CODEC);
    public static final FeatureDecorator<NoneDecoratorConfiguration> SNAP_TO_LOWER_NON_AIR_PLACEMENT = new SnapToLowerNonAirPlacement(NoneDecoratorConfiguration.CODEC);

    public static void registerPlacements() {
        Registry.register(Registry.DECORATOR, new ResourceLocation(RepurposedStructures.MODID, "dungeon_placement"), DUNGEON_PLACEMENT);
        Registry.register(Registry.DECORATOR, new ResourceLocation(RepurposedStructures.MODID, "unlimited_count"), UNLIMITED_COUNT);
        Registry.register(Registry.DECORATOR, new ResourceLocation(RepurposedStructures.MODID, "minus_eight_placement"), MINUS_EIGHT_PLACEMENT);
        Registry.register(Registry.DECORATOR, new ResourceLocation(RepurposedStructures.MODID, "offset_y_placement"), OFFSET_Y_PLACEMENT);
        Registry.register(Registry.DECORATOR, new ResourceLocation(RepurposedStructures.MODID, "snap_to_lower_non_air_placement"), SNAP_TO_LOWER_NON_AIR_PLACEMENT);
    }
}
