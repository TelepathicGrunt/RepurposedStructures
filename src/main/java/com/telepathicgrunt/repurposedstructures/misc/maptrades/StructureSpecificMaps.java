package com.telepathicgrunt.repurposedstructures.misc.maptrades;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
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

public class StructureSpecificMaps {
    public static class TreasureMapForEmeralds implements VillagerTrades.ItemListing {
        private final int emeraldCost;
        private final ResourceLocation destination;
        private final ResourceLocation destinationTag;
        private final String displayName;
        private final MapDecoration.Type destinationType;
        private final int maxUses;
        private final int villagerXp;
        private final int spawnRegionSearchRadius;

        public TreasureMapForEmeralds(int emeraldCost, String csf, String displayName, MapDecoration.Type mapIcon, int maxUse, int xp, int spawnRegionSearchRadius) {
            this.emeraldCost = emeraldCost;

            if(csf.startsWith("#")) {
                this.destination = null;
                this.destinationTag = new ResourceLocation(csf.replaceFirst("#",""));
            }
            else {
                this.destination = new ResourceLocation(csf);
                this.destinationTag = null;
            }

            this.displayName = displayName;
            this.destinationType = mapIcon;
            this.maxUses = maxUse;
            this.villagerXp = xp;
            this.spawnRegionSearchRadius = spawnRegionSearchRadius;
        }

        public MerchantOffer getOffer(Entity entity, RandomSource random) {
            BlockPos blockpos = null;
            if (!(entity.level instanceof ServerLevel serverlevel)) {
                return null;
            }
            else if (this.destinationTag != null) {
                blockpos = serverlevel.findNearestMapStructure(TagKey.create(Registry.STRUCTURE_REGISTRY, this.destinationTag), entity.blockPosition(), spawnRegionSearchRadius, true);
            }
            else {
                Registry<Structure> registry = serverlevel.registryAccess().registryOrThrow(Registry.STRUCTURE_REGISTRY);
                HolderSet<Structure> holderset = HolderSet.direct(registry.getHolderOrThrow(ResourceKey.create(Registry.STRUCTURE_REGISTRY, this.destination)));
                Pair<BlockPos, Holder<Structure>> pairResult = serverlevel.getChunkSource().getGenerator().findNearestMapStructure(serverlevel, holderset, entity.blockPosition(), spawnRegionSearchRadius, true);
                if (pairResult != null) {
                    blockpos = pairResult.getFirst();
                }
            }

            if (blockpos != null) {
                ItemStack itemstack = MapItem.create(serverlevel, blockpos.getX(), blockpos.getZ(), (byte)2, true, true);
                MapItem.renderBiomePreviewMap(serverlevel, itemstack);
                MapItemSavedData.addTargetDecoration(itemstack, blockpos, "+", this.destinationType);
                itemstack.setHoverName(Component.translatable(this.displayName));
                return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), new ItemStack(Items.COMPASS), itemstack, this.maxUses, this.villagerXp, 0.2F);
            }
            else {
                return null;
            }
        }
    }
}
