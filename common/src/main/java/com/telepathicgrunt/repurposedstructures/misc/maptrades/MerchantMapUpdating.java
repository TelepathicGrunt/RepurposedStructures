package com.telepathicgrunt.repurposedstructures.misc.maptrades;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixins.entities.MerchantOfferAccessor;
import com.telepathicgrunt.repurposedstructures.mixins.items.MapItemAccessor;
import com.telepathicgrunt.repurposedstructures.utils.AsyncLocator;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
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
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.saveddata.maps.MapDecorationType;
import net.minecraft.world.level.saveddata.maps.MapId;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;

import java.util.Optional;


// Source: https://github.com/thebrightspark/AsyncLocator/blob/1.19.x/src/main/java/brightspark/asynclocator/logic/MerchantLogic.java
public class MerchantMapUpdating {
    private MerchantMapUpdating() {}

    public static void invalidateMap(AbstractVillager merchant, ItemStack mapStack) {
        Component customName = mapStack.getComponents().get(DataComponents.CUSTOM_NAME);
        if (customName != null) {
            mapStack.set(DataComponents.CUSTOM_NAME, Component.translatable("item.minecraft.map"));
        }
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
        ((MerchantOfferAccessor) offer).repurposedstructures$setMaxUses(0);
        offer.setToOutOfStock();
    }

    public static void handleLocationFound(
            ServerLevel level,
            AbstractVillager merchant,
            ItemStack mapStack,
            String displayName,
            Holder.Reference<MapDecorationType> destinationType,
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
                    merchant instanceof Villager villager ? villager.getVillagerData().level() : 1,
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
            Holder.Reference<MapDecorationType> destinationType,
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
            Holder.Reference<MapDecorationType> destinationType,
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
                    new ItemCost(Items.EMERALD, emeraldCost),
                    Optional.of(new ItemCost(Items.COMPASS, 1)),
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
        Component customName = stack.getComponents().get(DataComponents.CUSTOM_NAME);
        if (customName != null) {
            stack.set(DataComponents.CUSTOM_NAME, Component.translatable("Locating... (Do not buy this map until finished)"));
        }
        return stack;
    }

    public static void updateMap(
            ItemStack mapStack,
            ServerLevel level,
            BlockPos pos,
            int scale,
            Holder.Reference<MapDecorationType> destinationType,
            String displayName
    ) {
        MapId mapId = MapItemAccessor.repurposedstructures$callCreateNewSavedData(level, pos.getX(), pos.getZ(), scale, true, true, level.dimension());
        mapStack.set(DataComponents.MAP_ID, mapId);
        MapItem.renderBiomePreviewMap(level, mapStack);
        MapItemSavedData.addTargetDecoration(mapStack, pos, "+", destinationType);
        if (displayName != null) {
            mapStack.set(DataComponents.CUSTOM_NAME, Component.translatable(displayName));
        }
    }
}

