package com.telepathicgrunt.repurposedstructures.misc.maptrades;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StructureMapManager extends SimpleJsonResourceReloadListener implements IdentifiableResourceReloadListener {
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().setLenient().disableHtmlEscaping().excludeFieldsWithoutExposeAnnotation().create();
    private final ResourceLocation STRUCTURE_MAP_MANAGER_ID = new ResourceLocation(RepurposedStructures.MODID, "structure_map_manager");

    public Map<String, List<VillagerMapObj>> VILLAGER_MAP_TRADES = new HashMap<>();
    public Map<WanderingTraderMapObj.TRADE_TYPE, List<WanderingTraderMapObj>> WANDERING_TRADER_MAP_TRADES = new HashMap<>();

    public StructureMapManager() {
        super(GSON, "structure_map_trades");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> loader, ResourceManager manager, ProfilerFiller profiler) {
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

    @Override
    public ResourceLocation getFabricId() {
        return STRUCTURE_MAP_MANAGER_ID;
    }
}
