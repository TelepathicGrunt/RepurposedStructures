package com.telepathicgrunt.repurposedstructures.misc.maptrades;

import com.google.gson.annotations.Expose;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Map;

public class StructureMapCollectionObj {
    @Expose
    public Map<ResourceLocation, List<VillagerMapObj>> villagerMaps;

    @Expose
    public Map<WanderingTraderMapObj.TRADE_TYPE, List<WanderingTraderMapObj>> wanderingTraderMap;

    public StructureMapCollectionObj(Map<ResourceLocation, List<VillagerMapObj>> villagerMapObjs, Map<WanderingTraderMapObj.TRADE_TYPE, List<WanderingTraderMapObj>> wanderingTraderMapObjs) {
        this.villagerMaps = villagerMapObjs;
        this.wanderingTraderMap = wanderingTraderMapObjs;
    }
}
