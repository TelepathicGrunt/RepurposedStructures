package com.telepathicgrunt.repurposedstructures.misc;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.registries.ForgeRegistries;

public final class MobMapTrades {
    private MobMapTrades() {}

    public static void onVillagerTradesEvent(VillagerTradesEvent event) {
        ResourceLocation profRL = ForgeRegistries.PROFESSIONS.getKey(event.getType());
        for (int tradeLevel = 1; tradeLevel <= 5; tradeLevel++) {
            TagKey<ConfiguredStructureFeature<?,?>> tagKey = TagKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY,
                    new ResourceLocation(RepurposedStructures.MODID, "map_trades/"+profRL.getNamespace()+"/"+profRL.getPath()+"/tier_"+tradeLevel));
            event.getTrades().get(tradeLevel).add(new VillagerTrades.TreasureMapForEmeralds(4, tagKey, "Tier "+ tradeLevel +" Mystery Structure Map", MapDecoration.Type.MANSION, 12, 10));
        }

        for (int tradeLevel = 1; tradeLevel <= 5; tradeLevel++) {
            TagKey<ConfiguredStructureFeature<?,?>> tagKey = TagKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY,
                    new ResourceLocation(RepurposedStructures.MODID, "map_trades/"+profRL.getNamespace()+"/"+profRL.getPath()+"/tier_"+tradeLevel+"_underground"));
            event.getTrades().get(tradeLevel).add(new VillagerTrades.TreasureMapForEmeralds(4, tagKey, "Tier "+ tradeLevel +" Mystery Structure Map", MapDecoration.Type.BANNER_GRAY, 12, 10));
        }
    }

    public static void onWandererTradesEvent(WandererTradesEvent event) {
        TagKey<ConfiguredStructureFeature<?,?>> wtCommonTagKey = TagKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY,
                new ResourceLocation(RepurposedStructures.MODID, "map_trades/minecraft/wandering_trader/common"));
        event.getGenericTrades().add(new VillagerTrades.TreasureMapForEmeralds(20, wtCommonTagKey, "Common Mystery Structure Map", MapDecoration.Type.MANSION, 1, 25));

        TagKey<ConfiguredStructureFeature<?,?>> wtCommonTagKey2 = TagKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY,
                new ResourceLocation(RepurposedStructures.MODID, "map_trades/minecraft/wandering_trader/common_underground"));
        event.getGenericTrades().add(new VillagerTrades.TreasureMapForEmeralds(20, wtCommonTagKey2, "Common Underground Mystery Structure Map", MapDecoration.Type.BANNER_LIGHT_GRAY, 1, 25));

        TagKey<ConfiguredStructureFeature<?,?>> wtRareTagKey = TagKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY,
                new ResourceLocation(RepurposedStructures.MODID, "map_trades/minecraft/wandering_trader/rare"));
        event.getRareTrades().add(new VillagerTrades.TreasureMapForEmeralds(38, wtRareTagKey, "Common Mystery Structure Map", MapDecoration.Type.MANSION, 1, 200));

        TagKey<ConfiguredStructureFeature<?,?>> wtRareTagKey2 = TagKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY,
                new ResourceLocation(RepurposedStructures.MODID, "map_trades/minecraft/wandering_trader/rare_underground"));
        event.getRareTrades().add(new VillagerTrades.TreasureMapForEmeralds(38, wtRareTagKey2, "Common Underground Mystery Structure Map", MapDecoration.Type.BANNER_GRAY, 1, 200));
    }
}
