package com.telepathicgrunt.repurposedstructures.misc.maptrades;

import com.google.gson.annotations.Expose;

import java.util.List;
import java.util.Map;

public class StructureMapCollectionObj {
    @Expose
    public Map<String, List<VillagerMapObj>> villagerMaps;

    @Expose
    public Map<WanderingTraderMapObj.TRADE_TYPE, List<WanderingTraderMapObj>> wanderingTraderMap;

    public StructureMapCollectionObj(Map<String, List<VillagerMapObj>> villagerMapObjs, Map<WanderingTraderMapObj.TRADE_TYPE, List<WanderingTraderMapObj>> wanderingTraderMapObjs) {
        this.villagerMaps = villagerMapObjs;
        this.wanderingTraderMap = wanderingTraderMapObjs;
    }
}
