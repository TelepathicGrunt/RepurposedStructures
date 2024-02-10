package com.telepathicgrunt.repurposedstructures.misc.maptrades;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixins.entities.MerchantOfferAccessor;
import com.telepathicgrunt.repurposedstructures.mixins.items.MapItemAccessor;
import com.telepathicgrunt.repurposedstructures.utils.AsyncLocator;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderSet;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;


// Source: https://github.com/thebrightspark/AsyncLocator/blob/1.19.x/src/main/java/brightspark/asynclocator/logic/MerchantLogic.java
public class MerchantMapUpdating {
    private MerchantMapUpdating() {}

    public static void invalidateMap(AbstractVillager merchant, ItemStack mapStack) {
        mapStack.setHoverName(Component.translatable("item.minecraft.map"));
        merchant.getOffers()
                .stream()
                .filter(offer -> offer.getResult() == mapStack)
                .findFirst()
                .ifPresentOrElse(
                        offer -> removeOffer(merchant, offer),
                        () -> RepurposedStructures.LOGGER.warn("Failed to find merchant offer for map")
                );
    }

    public static void removeOffer(AbstractVillager merchant, MerchantOffer offer) {
        ((MerchantOfferAccessor) offer).setMaxUses(0);
        offer.setToOutOfStock();
    }

    public static void handleLocationFound(
            ServerLevel level,
            AbstractVillager merchant,
            ItemStack mapStack,
            String displayName,
            MapDecoration.Type destinationType,
            BlockPos pos
    ) {
        if (pos == null) {
            invalidateMap(merchant, mapStack);
        }
        else {
            updateMap(mapStack, level, pos, 2, destinationType, displayName);
        }

        if (merchant.getTradingPlayer() instanceof ServerPlayer tradingPlayer) {
            tradingPlayer.sendMerchantOffers(
                    tradingPlayer.containerMenu.containerId,
                    merchant.getOffers(),
                    merchant instanceof Villager villager ? villager.getVillagerData().getLevel() : 1,
                    merchant.getVillagerXp(),
                    merchant.showProgressBar(),
                    merchant.canRestock()
            );
        }
    }

    public static MerchantOffer updateMapAsync(
            Entity pTrader,
            int emeraldCost,
            String displayName,
            MapDecoration.Type destinationType,
            int maxUses,
            int villagerXp,
            TagKey<Structure> destination,
            int searchRadius
    ) {
        return updateMapAsyncInternal(
                pTrader,
                emeraldCost,
                maxUses,
                villagerXp,
                (level, merchant, mapStack) -> AsyncLocator.locate(level, destination, merchant.blockPosition(), searchRadius, true)
                        .thenOnServerThread(pos -> handleLocationFound(
                                level,
                                merchant,
                                mapStack,
                                displayName,
                                destinationType,
                                pos
                        ))
        );
    }

    public static MerchantOffer updateMapAsync(
            Entity pTrader,
            int emeraldCost,
            String displayName,
            MapDecoration.Type destinationType,
            int maxUses,
            int villagerXp,
            HolderSet<Structure> structureSet,
            int searchRadius
    ) {
        return updateMapAsyncInternal(
                pTrader,
                emeraldCost,
                maxUses,
                villagerXp,
                (level, merchant, mapStack) -> AsyncLocator.locate(level, structureSet, merchant.blockPosition(), searchRadius, true)
                        .thenOnServerThread(pair -> handleLocationFound(
                                level,
                                merchant,
                                mapStack,
                                displayName,
                                destinationType,
                                pair.getFirst()
                        ))
        );
    }

    private static MerchantOffer updateMapAsyncInternal(
            Entity trader, int emeraldCost, int maxUses, int villagerXp, MapUpdateTask task
    ) {
        if (trader instanceof AbstractVillager merchant) {
            ItemStack mapStack = createEmptyMap();
            task.apply((ServerLevel) trader.level(), merchant, mapStack);

            return new MerchantOffer(
                    new ItemStack(Items.EMERALD, emeraldCost),
                    new ItemStack(Items.COMPASS),
                    mapStack,
                    maxUses,
                    villagerXp,
                    0.2F
            );
        }
        else {
            return null;
        }
    }

    public interface MapUpdateTask {
        void apply(ServerLevel level, AbstractVillager merchant, ItemStack mapStack);
    }

    public static ItemStack createEmptyMap() {
        ItemStack stack = new ItemStack(Items.FILLED_MAP);
        stack.setHoverName(Component.translatable("Locating... (Do not buy this map until finished)"));
        return stack;
    }

    public static void updateMap(
            ItemStack mapStack,
            ServerLevel level,
            BlockPos pos,
            int scale,
            MapDecoration.Type destinationType,
            String displayName
    ) {
        MapItemAccessor.callCreateAndStoreSavedData(
                mapStack, level, pos.getX(), pos.getZ(), scale, true, true, level.dimension()
        );
        MapItem.renderBiomePreviewMap(level, mapStack);
        MapItemSavedData.addTargetDecoration(mapStack, pos, "+", destinationType);
        if (displayName != null)
            mapStack.setHoverName(Component.translatable(displayName));
    }
}

