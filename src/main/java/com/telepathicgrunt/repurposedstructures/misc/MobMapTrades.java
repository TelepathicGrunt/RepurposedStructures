package com.telepathicgrunt.repurposedstructures.misc;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.ConfiguredStructureTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import org.apache.commons.lang3.EnumUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public final class MobMapTrades {
    private MobMapTrades() {}

    public static void addMapTrades() {
        for (Map.Entry<ResourceKey<VillagerProfession>, VillagerProfession> professionEntry : Registry.VILLAGER_PROFESSION.entrySet()) {
            ResourceLocation profRL = professionEntry.getKey().location();
            for (int tradeLevel = 1; tradeLevel <= 5; tradeLevel++) {
                TagKey<ConfiguredStructureFeature<?,?>> tagKey = TagKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY,
                        new ResourceLocation(RepurposedStructures.MODID, "map_trades/"+profRL.getNamespace()+"/"+profRL.getPath()+"/tier_"+tradeLevel));

                int finalTradeLevel = tradeLevel;
                TradeOfferHelper.registerVillagerOffers(professionEntry.getValue(), tradeLevel, (factories) -> factories.add(new VillagerTrades.TreasureMapForEmeralds(4, tagKey, "Tier "+finalTradeLevel+" Mystery Structure Map", MapDecoration.Type.MANSION, 12, 10)));
            }

            for (int tradeLevel = 1; tradeLevel <= 5; tradeLevel++) {
                TagKey<ConfiguredStructureFeature<?,?>> tagKey = TagKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY,
                        new ResourceLocation(RepurposedStructures.MODID, "map_trades/"+profRL.getNamespace()+"/"+profRL.getPath()+"/tier_"+tradeLevel+"_underground"));

                int finalTradeLevel = tradeLevel;
                TradeOfferHelper.registerVillagerOffers(professionEntry.getValue(), tradeLevel, (factories) -> factories.add(new VillagerTrades.TreasureMapForEmeralds(4, tagKey, "Tier "+finalTradeLevel+" Underground Mystery Structure Map", MapDecoration.Type.BANNER_GRAY, 12, 10)));
            }
        }

        TagKey<ConfiguredStructureFeature<?,?>> wtCommonTagKey = TagKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY,
                new ResourceLocation(RepurposedStructures.MODID, "map_trades/minecraft/wandering_trader/common"));
        TradeOfferHelper.registerWanderingTraderOffers(1, (factories) -> factories.add(new VillagerTrades.TreasureMapForEmeralds(20, wtCommonTagKey, "Common Mystery Structure Map", MapDecoration.Type.MANSION, 1, 25)));

        TagKey<ConfiguredStructureFeature<?,?>> wtCommonTagKey2 = TagKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY,
                new ResourceLocation(RepurposedStructures.MODID, "map_trades/minecraft/wandering_trader/common_underground"));
        TradeOfferHelper.registerWanderingTraderOffers(1, (factories) -> factories.add(new VillagerTrades.TreasureMapForEmeralds(20, wtCommonTagKey2, "Common Underground Mystery Structure Map", MapDecoration.Type.BANNER_LIGHT_GRAY, 1, 25)));

        TagKey<ConfiguredStructureFeature<?,?>> wtRareTagKey = TagKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY,
                new ResourceLocation(RepurposedStructures.MODID, "map_trades/minecraft/wandering_trader/rare"));
        TradeOfferHelper.registerWanderingTraderOffers(2, (factories) -> factories.add(new VillagerTrades.TreasureMapForEmeralds(38, wtRareTagKey, "Rare Mystery Structure Map", MapDecoration.Type.MANSION, 1, 200)));

        TagKey<ConfiguredStructureFeature<?,?>> wtRareTagKey2 = TagKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY,
                new ResourceLocation(RepurposedStructures.MODID, "map_trades/minecraft/wandering_trader/rare_underground"));
        TradeOfferHelper.registerWanderingTraderOffers(2, (factories) -> factories.add(new VillagerTrades.TreasureMapForEmeralds(38, wtRareTagKey2, "Rare Underground Mystery Structure Map", MapDecoration.Type.BANNER_GRAY, 1, 200)));
    }
}
