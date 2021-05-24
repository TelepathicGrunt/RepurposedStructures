package com.telepathicgrunt.repurposedstructures.utils;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.NamespaceResourceManagerAccessor;
import com.telepathicgrunt.repurposedstructures.mixin.ReloadableResourceManagerImplAccessor;
import com.telepathicgrunt.repurposedstructures.mixin.StructureManagerAccessor;
import com.telepathicgrunt.repurposedstructures.mixin.StructurePoolAccessor;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.resource.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.dynamic.ForwardingDynamicOps;
import net.minecraft.util.dynamic.RegistryOps;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PoolAdditionMerger {
    public static void mergeAdditionPools() {
        ServerLifecycleEvents.SERVER_STARTING.register((MinecraftServer minecraftServer) -> {
            ResourceManager resourceManager = ((StructureManagerAccessor) minecraftServer.getStructureManager()).rs_getField_25189();
            Map<Identifier, List<JsonElement>> poolAdditionJSON = getPoolAdditionJSON(resourceManager);
            getPoolAdditionJSON(poolAdditionJSON, minecraftServer.getRegistryManager(), resourceManager);
        });
    }

    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().setLenient().disableHtmlEscaping().create();
    private static final String DATA_TYPE = "pool_additions";
    private static final int FILE_SUFFIX_LENGTH = ".json".length();

    private static Map<Identifier, List<JsonElement>> getPoolAdditionJSON(ResourceManager resourceManager) {
        Map<Identifier, List<JsonElement>> map = new HashMap<>();
        int dataTypeLength = DATA_TYPE.length() + 1;

        for (Identifier identifier : resourceManager.findResources(DATA_TYPE, (stringx) -> stringx.endsWith(".json"))) {
            String string = identifier.getPath();
            Identifier identifier2 = new Identifier(identifier.getNamespace(), string.substring(dataTypeLength, string.length() - FILE_SUFFIX_LENGTH));

            try {
                for (InputStream streamToOpen : getAllFiles(resourceManager, identifier)) {
                    try (InputStream inputStream = streamToOpen) {
                        try (Reader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

                            JsonElement jsonElement = JsonHelper.deserialize(GSON, reader, (Class<? extends JsonElement>) JsonElement.class);
                            if (jsonElement != null) {
                                // Create list
                                if (!map.containsKey(identifier2)) {
                                    map.put(identifier2, new ArrayList<>());
                                }
                                // Add the pool to the list we will merge later on
                                map.get(identifier2).add(jsonElement);
                            }
                            else {
                                RepurposedStructures.LOGGER.error(
                                        "Couldn't load data file {} from {} as it's null or empty",
                                        identifier2,
                                        identifier);
                            }
                        }
                    }
                }
            }
            catch (IllegalArgumentException | IOException | JsonParseException var68) {
                RepurposedStructures.LOGGER.error(
                        "Couldn't parse data file {} from {}",
                        identifier2,
                        identifier,
                        var68);
            }
        }

        return map;
    }


    private static List<InputStream> getAllFiles(ResourceManager resourceManager, Identifier id) throws IOException {
        List<InputStream> inputStreams = new ArrayList<>();

        NamespaceResourceManager namespaceResourceManager = ((ReloadableResourceManagerImplAccessor)resourceManager).rs_getNamespaceManagers().get(id.getNamespace());
        List<ResourcePack> resourcePacks = ((NamespaceResourceManagerAccessor)namespaceResourceManager).rs_getPackList();
        ResourceType resourceType = ((NamespaceResourceManagerAccessor)namespaceResourceManager).rs_getType();

        ResourcePack resourcePack = null;
        Identifier identifier = new Identifier(id.getNamespace(), id.getPath() + ".mcmeta");

        for(int i = resourcePacks.size() - 1; i >= 0; --i) {
            ResourcePack resourcePack2 = resourcePacks.get(i);
            if (resourcePack == null && resourcePack2.contains(resourceType, identifier)) {
                resourcePack = resourcePack2;
            }

            if (resourcePack2.contains(resourceType, id)) {
                InputStream inputStream = ((NamespaceResourceManagerAccessor)namespaceResourceManager).rs_callOpen(id, resourcePack2);
                if(inputStream != null) inputStreams.add(inputStream);
            }
        }
        return inputStreams;
    }

    private static void getPoolAdditionJSON(Map<Identifier, List<JsonElement>> poolAdditionJSON, DynamicRegistryManager dynamicRegistryManager, ResourceManager resourceManager) {
        MutableRegistry<StructurePool> poolRegistry = dynamicRegistryManager.get(Registry.TEMPLATE_POOL_WORLDGEN);
        for(Map.Entry<Identifier, List<JsonElement>> entry : poolAdditionJSON.entrySet()){
            if(!poolRegistry.containsId(entry.getKey())) continue;

            for(JsonElement jsonElement : entry.getValue()){
                SafeDecodingRegistryOps<JsonElement> customRegistryOps = new SafeDecodingRegistryOps(
                        JsonOps.INSTANCE,
                        dynamicRegistryManager);

                StructurePool.CODEC.parse(customRegistryOps, jsonElement)
                        .resultOrPartial(messageString -> logBadData(entry.getKey(), messageString))
                        .ifPresent(validPool -> mergeIntoExistingPool(validPool, poolRegistry.get(entry.getKey())));
            }
        }
    }

    private static void mergeIntoExistingPool(StructurePool feedingPool, StructurePool gluttonyPool) {
        List<StructurePoolElement> elements = new ArrayList<>(((StructurePoolAccessor)gluttonyPool).rs_getElements());
        List<Pair<StructurePoolElement, Integer>> elementCounts = new ArrayList<>(((StructurePoolAccessor)gluttonyPool).rs_getElementCounts());

        elements.addAll(((StructurePoolAccessor)feedingPool).rs_getElements());
        elementCounts.addAll(((StructurePoolAccessor)feedingPool).rs_getElementCounts());

        ((StructurePoolAccessor)gluttonyPool).rs_setElements(elements);
        ((StructurePoolAccessor)gluttonyPool).rs_setElementCounts(elementCounts);
    }

    private static void logBadData(Identifier poolPath, String messageString) {
        RepurposedStructures.LOGGER.error("Failed to parse {} additions file. Error is: {}", poolPath, messageString);
    }
}
