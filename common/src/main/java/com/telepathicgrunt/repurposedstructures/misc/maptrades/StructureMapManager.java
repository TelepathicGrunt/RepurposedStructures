package com.telepathicgrunt.repurposedstructures.misc.maptrades;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonElement;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.telepathicgrunt.repurposedstructures.RepurposedStructures.GSON;

public class StructureMapManager extends SimpleJsonResourceReloadListener<JsonElement> {
    public final static StructureMapManager STRUCTURE_MAP_MANAGER = new StructureMapManager();

    public Map<String, List<VillagerMapObj>> VILLAGER_MAP_TRADES = new HashMap<>();
    public Map<WanderingTraderMapObj.TRADE_TYPE, List<WanderingTraderMapObj>> WANDERING_TRADER_MAP_TRADES = new HashMap<>();

    public StructureMapManager() {
        super(ExtraCodecs.JSON, FileToIdConverter.json("structure_map_trades"));
    }

    @Override
    protected void apply(Map<Identifier, JsonElement> loader, ResourceManager manager, ProfilerFiller profiler) {
        ImmutableMap.Builder<String, List<VillagerMapObj>> builderVillager = ImmutableMap.builder();
        ImmutableMap.Builder<WanderingTraderMapObj.TRADE_TYPE, List<WanderingTraderMapObj>> builderWandering = ImmutableMap.builder();
        loader.forEach((fileIdentifier, jsonElement) -> {
            try {
                StructureMapCollectionObj spawnerMobEntries = GSON.fromJson(jsonElement, StructureMapCollectionObj.class);
                builderVillager.putAll(spawnerMobEntries.villagerMaps);
                builderWandering.putAll(spawnerMobEntries.wanderingTraderMap);
            }
            catch (Exception e) {
                RepurposedStructures.LOGGER.error("Repurposed Structures Error: Couldn't parse structure map file {}", fileIdentifier, e);
            }
        });
        VILLAGER_MAP_TRADES =  builderVillager.build();
        WANDERING_TRADER_MAP_TRADES =  builderWandering.build();
    }
}
