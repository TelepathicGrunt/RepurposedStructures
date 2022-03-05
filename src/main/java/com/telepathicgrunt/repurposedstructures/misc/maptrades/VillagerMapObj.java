package com.telepathicgrunt.repurposedstructures.misc.maptrades;

import com.google.gson.annotations.Expose;

public class VillagerMapObj {
    @Expose
    public final String structure;

    @Expose
    public final String mapIcon;

    @Expose
    public final String mapName;

    @Expose
    public final int tradeLevel;

    @Expose
    public final int emeraldsRequired;

    @Expose
    public final int tradesAllowed;

    @Expose
    public final int xpReward;

    @Expose
    public final int spawnRegionSearchRadius;

    public VillagerMapObj(String structure, String mapIcon, String mapName, int tradeLevel, int emeraldsRequired, int tradesAllowed, int xpReward, int spawnRegionSearchRadius) {
        this.structure = structure;
        this.mapIcon = mapIcon;
        this.mapName = mapName;
        this.tradeLevel = tradeLevel;
        this.emeraldsRequired = emeraldsRequired;
        this.tradesAllowed = tradesAllowed;
        this.xpReward = xpReward;
        this.spawnRegionSearchRadius = spawnRegionSearchRadius;
    }
}
