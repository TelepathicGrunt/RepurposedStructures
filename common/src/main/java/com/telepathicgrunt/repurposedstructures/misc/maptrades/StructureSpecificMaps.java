package com.telepathicgrunt.repurposedstructures.misc.maptrades;

import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.saveddata.maps.MapDecoration;

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
        }

        private HolderSet<Structure> getHolderSet(ServerLevel level) {
            Registry<Structure> registry =
                level.registryAccess().registryOrThrow(Registries.STRUCTURE);
            return HolderSet.direct(registry.getHolderOrThrow(destination));
        }

        private MerchantOffer getOffer(ServerLevel level, Entity entity) {
            if (destinationTag == null) {
                HolderSet<Structure> holderSet = getHolderSet(level);
                return MerchantMapUpdating.updateMapAsync(
                        entity,
                        emeraldCost,
                        displayName,
                        destinationType,
                        maxUses,
                        villagerXp,
                        holderSet,
                        spawnRegionSearchRadius
                );
            }
            else {
                return MerchantMapUpdating.updateMapAsync(
                        entity,
                        emeraldCost,
                        displayName,
                        destinationType,
                        maxUses,
                        villagerXp,
                        destinationTag,
                        spawnRegionSearchRadius
                );
            }
        }
    }
}
