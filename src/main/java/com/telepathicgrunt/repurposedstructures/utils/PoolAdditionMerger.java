package com.telepathicgrunt.repurposedstructures.utils;

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
import net.minecraft.resource.NamespaceResourceManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourceType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
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

    // Needed for detecting the correct files, ignoring file extension, and what JSON parser to use for parsing the files
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().setLenient().disableHtmlEscaping().create();
    private static final String DATA_TYPE = "pool_additions";
    private static final int FILE_SUFFIX_LENGTH = ".json".length();

    /**
     * Call this at mod init so we can subscribe our pool merging to run at server startup as that's when the dynamic registry exists.
     */
    public static void mergeAdditionPools() {
        ServerLifecycleEvents.SERVER_STARTING.register((MinecraftServer minecraftServer) -> {
            ResourceManager resourceManager = ((StructureManagerAccessor) minecraftServer.getStructureManager()).rs_getField_25189();
            Map<Identifier, List<JsonElement>> poolAdditionJSON = getPoolAdditionJSON(resourceManager);
            parsePoolsAndBeginMerger(poolAdditionJSON, minecraftServer.getRegistryManager());
        });
    }

    /**
     * Will grab all JSON objects from all datapacks's pool_additions folder.
     *
     * @return - A map of paths (identifiers) to a list of all JSON elements found under it from all datapacks.
     */
    private static Map<Identifier, List<JsonElement>> getPoolAdditionJSON(ResourceManager resourceManager) {
        Map<Identifier, List<JsonElement>> map = new HashMap<>();
        int dataTypeLength = DATA_TYPE.length() + 1;

        // Finds all JSON files paths within the pool_additions folder. NOTE: this is just the path rn. Not the actual files yet.
        for (Identifier fileIDWithExtension : resourceManager.findResources(DATA_TYPE, (fileString) -> fileString.endsWith(".json"))) {
            String identifierPath = fileIDWithExtension.getPath();
            Identifier fileID = new Identifier(
                    fileIDWithExtension.getNamespace(),
                    identifierPath.substring(dataTypeLength, identifierPath.length() - FILE_SUFFIX_LENGTH));

            try {
                // getAllFileStreams will find files with the given ID. This part is what will loop over all matching files from all datapacks.
                for (InputStream fileStream : getAllFileStreams(resourceManager, fileIDWithExtension)) {
                    try (Reader bufferedReader = new BufferedReader(new InputStreamReader(fileStream, StandardCharsets.UTF_8))) {

                        // Get the JSON from the file
                        JsonElement poolJSONElement = JsonHelper.deserialize(GSON, bufferedReader, (Class<? extends JsonElement>) JsonElement.class);
                        if (poolJSONElement != null) {

                            // Create list in map for the ID if non exists yet for that ID
                            if (!map.containsKey(fileID)) {
                                map.put(fileID, new ArrayList<>());
                            }
                            // Add the pool to the list we will merge later on
                            map.get(fileID).add(poolJSONElement);
                        }
                        else {
                            RepurposedStructures.LOGGER.error(
                                    "(POOL MERGER) Couldn't load data file {} from {} as it's null or empty",
                                    fileID,
                                    fileIDWithExtension);
                        }
                    }
                }
            }
            catch (IllegalArgumentException | IOException | JsonParseException exception) {
                RepurposedStructures.LOGGER.error(
                        "(POOL MERGER) Couldn't parse data file {} from {}",
                        fileID,
                        fileIDWithExtension,
                        exception);
            }
        }

        return map;
    }


    /**
     * Obtains all of the file streams for all files found in all datapacks with the given id.
     *
     * @return - Filestream list of all files found with id
     */
    private static List<InputStream> getAllFileStreams(ResourceManager resourceManager, Identifier fileID) throws IOException {
        List<InputStream> fileStreams = new ArrayList<>();

        NamespaceResourceManager namespaceResourceManager = ((ReloadableResourceManagerImplAccessor) resourceManager).rs_getNamespaceManagers().get(fileID.getNamespace());
        List<ResourcePack> allResourcePacks = ((NamespaceResourceManagerAccessor) namespaceResourceManager).rs_getPackList();

        // Find the file with the given id and add its filestream to the list
        for (ResourcePack resourcePack : allResourcePacks) {
            if (resourcePack.contains(ResourceType.SERVER_DATA, fileID)) {
                InputStream inputStream = ((NamespaceResourceManagerAccessor) namespaceResourceManager).rs_callOpen(fileID, resourcePack);
                if (inputStream != null) fileStreams.add(inputStream);
            }
        }

        // Return filestream of all files matching id path
        return fileStreams;
    }

    /**
     * Using the given dynamic registry, will now parse the JSON objects of pools and resolve their processors with the dynamic registry.
     * Afterwards, it will merge the parsed pool into the targeted pool found in the dynamic registry.
     */
    private static void parsePoolsAndBeginMerger(Map<Identifier, List<JsonElement>> poolAdditionJSON, DynamicRegistryManager dynamicRegistryManager) {
        MutableRegistry<StructurePool> poolRegistry = dynamicRegistryManager.get(Registry.TEMPLATE_POOL_WORLDGEN);
        // A RegistryOps that doesn't break everything under the sun and can take a DynamicRegistryManager instead of DynamicRegistryManager.Impl.
        SafeDecodingRegistryOps<JsonElement> customRegistryOps = new SafeDecodingRegistryOps<>(JsonOps.INSTANCE, dynamicRegistryManager);

        // Will iterate over all of our found pool additions and make sure the target pool exists before we parse our JSON objects
        for (Map.Entry<Identifier, List<JsonElement>> entry : poolAdditionJSON.entrySet()) {
            if (!poolRegistry.containsId(entry.getKey())) continue;

            // Parse the given pool addition JSON objects and add their pool to the dynamic registry pool
            for (JsonElement jsonElement : entry.getValue()) {
                StructurePool.CODEC.parse(customRegistryOps, jsonElement)
                        .resultOrPartial(messageString -> logBadData(entry.getKey(), messageString))
                        .ifPresent(validPool -> mergeIntoExistingPool(validPool, poolRegistry.get(entry.getKey())));
            }
        }
    }

    /**
     * Merges the incoming pool with the given target pool in an additive manner that does not affect any other pools and can be stacked safely.
     */
    private static void mergeIntoExistingPool(StructurePool feedingPool, StructurePool gluttonyPool) {
        // Make new copies of lists as the originals are immutable lists and we want to make sure our changes only stays with this pool element
        List<StructurePoolElement> elements = new ArrayList<>(((StructurePoolAccessor) gluttonyPool).rs_getElements());
        List<Pair<StructurePoolElement, Integer>> elementCounts = new ArrayList<>(((StructurePoolAccessor) gluttonyPool).rs_getElementCounts());

        elements.addAll(((StructurePoolAccessor) feedingPool).rs_getElements());
        elementCounts.addAll(((StructurePoolAccessor) feedingPool).rs_getElementCounts());

        ((StructurePoolAccessor) gluttonyPool).rs_setElements(elements);
        ((StructurePoolAccessor) gluttonyPool).rs_setElementCounts(elementCounts);
    }

    /**
     * Log out the pool that failed to be parsed and what the error is.
     */
    private static void logBadData(Identifier poolPath, String messageString) {
        RepurposedStructures.LOGGER.error("(POOL MERGER) Failed to parse {} additions file. Error is: {}", poolPath, messageString);
    }
}
