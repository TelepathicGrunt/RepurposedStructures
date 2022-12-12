package com.telepathicgrunt.repurposedstructures.misc.maptrades;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;

import javax.annotation.Nullable;

public class StructureSpecificMaps {
    public static class TreasureMapForEmeralds implements VillagerTrades.ItemListing {
        private final int emeraldCost;
        private final ResourceKey<Structure> destination;
        private final TagKey<Structure> destinationTag;
        private final String displayName;
        private final MapDecoration.Type destinationType;
        private final int maxUses;
        private final int villagerXp;
        private final int spawnRegionSearchRadius;

        public TreasureMapForEmeralds(int emeraldCost, String csf, String displayName, MapDecoration.Type mapIcon, int maxUse, int xp, int spawnRegionSearchRadius) {
            this.emeraldCost = emeraldCost;

            if(csf.startsWith("#")) {
                this.destination = null;
                this.destinationTag = TagKey.create(Registries.STRUCTURE, new ResourceLocation(csf.replaceFirst("#","")));
            }
            else {
                this.destination = ResourceKey.create(Registries.STRUCTURE, new ResourceLocation(csf));
                this.destinationTag = null;
            }

            this.displayName = displayName;
            this.destinationType = mapIcon;
            this.maxUses = maxUse;
            this.villagerXp = xp;
            this.spawnRegionSearchRadius = spawnRegionSearchRadius;
        }

        @Nullable
        public MerchantOffer getOffer(Entity entity, RandomSource random) {
            if (!(entity.level instanceof ServerLevel serverlevel)) {
                return null;
            }

            return getOffer(serverlevel, entity);
            /*
            return CompatCommon.isAsyncLocatorLoaded()
                ? getOfferAsync(serverlevel, entity)
                : getOffer(serverlevel, entity);
             */
        }

        private HolderSet<Structure> getHolderSet(ServerLevel level) {
            Registry<Structure> registry =
                level.registryAccess().registryOrThrow(Registries.STRUCTURE);
            return HolderSet.direct(registry.getHolderOrThrow(destination));
        }

        private MerchantOffer getOffer(ServerLevel level, Entity entity) {
            BlockPos blockpos = null;
            if (destinationTag == null) {
                HolderSet<Structure> holderSet = getHolderSet(level);
                Pair<BlockPos, Holder<Structure>> pairResult =
                    level.getChunkSource()
                        .getGenerator()
                        .findNearestMapStructure(level, holderSet, entity.blockPosition(), spawnRegionSearchRadius, true);
                if (pairResult != null) {
                    blockpos = pairResult.getFirst();
                }
            } else {
                blockpos = level.findNearestMapStructure(
                    this.destinationTag,
                    entity.blockPosition(),
                    spawnRegionSearchRadius,
                    true
                );
            }

            if (blockpos != null) {
                ItemStack itemstack = MapItem.create(level, blockpos.getX(), blockpos.getZ(), (byte) 2, true, true);
                MapItem.renderBiomePreviewMap(level, itemstack);
                MapItemSavedData.addTargetDecoration(itemstack, blockpos, "+", this.destinationType);
                itemstack.setHoverName(Component.translatable(this.displayName));
                return new MerchantOffer(
                    new ItemStack(Items.EMERALD, this.emeraldCost),
                    new ItemStack(Items.COMPASS),
                    itemstack,
                    this.maxUses,
                    this.villagerXp,
                    0.2F
                );
            }
            return null;
        }

        /*
        private MerchantOffer getOfferAsync(ServerLevel level, Entity trader) {
            if (destinationTag == null) {
                return AsyncLocatorCompat.updateMapAsync(
                    trader,
                    emeraldCost,
                    displayName,
                    destinationType,
                    maxUses,
                    villagerXp,
                    getHolderSet(level)
                );
            } else {
                return AsyncLocatorCompat.updateMapAsync(
                    trader,
                    emeraldCost,
                    displayName,
                    destinationType,
                    maxUses,
                    villagerXp,
                    destinationTag
                );
            }
        }*/
    }
}
