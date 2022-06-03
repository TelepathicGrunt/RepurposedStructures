package com.telepathicgrunt.repurposedstructures.compat;

import brightspark.asynclocator.logic.MerchantLogic;
import net.minecraft.core.HolderSet;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.saveddata.maps.MapDecoration;

public class AsyncLocatorCompat {
	public static MerchantOffer updateMapAsync(
		Entity trader,
		int emeraldCost,
		String displayName,
		MapDecoration.Type destinationType,
		int maxUses,
		int villagerXp,
		TagKey<ConfiguredStructureFeature<?, ?>> destination
	) {
		return MerchantLogic.updateMapAsync(
			trader, emeraldCost, displayName, destinationType, maxUses, villagerXp, destination
		);
	}

	public static MerchantOffer updateMapAsync(
		Entity trader,
		int emeraldCost,
		String displayName,
		MapDecoration.Type destinationType,
		int maxUses,
		int villagerXp,
		HolderSet<ConfiguredStructureFeature<?, ?>> structureSet
	) {
		return MerchantLogic.updateMapAsync(
			trader, emeraldCost, displayName, destinationType, maxUses, villagerXp, structureSet
		);
	}
}
