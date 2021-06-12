package com.telepathicgrunt.repurposedstructures.misc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.FallbackResourceManagerAccessor;
import com.telepathicgrunt.repurposedstructures.mixin.JigsawPatternAccessor;
import com.telepathicgrunt.repurposedstructures.mixin.SimpleReloadableResourceManagerAccessor;
import com.telepathicgrunt.repurposedstructures.mixin.TemplateManagerAccessor;
import com.telepathicgrunt.repurposedstructures.utils.SafeDecodingRegistryOps;
import net.minecraft.resources.FallbackResourceManager;
import net.minecraft.resources.IResourceManager;
import net.minecraft.resources.IResourcePack;
import net.minecraft.resources.ResourcePackType;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;

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
     * Register this at mod init so we can subscribe our pool merging to run at server startup as that's when the dynamic registry exists.
     */
    public static void mergeAdditionPools(final FMLServerAboutToStartEvent event) {
        IResourceManager resourceManager = ((TemplateManagerAccessor) event.getServer().getStructureManager()).repurposedstructures_getResourceManager();
        Map<ResourceLocation, List<JsonElement>> poolAdditionJSON = getPoolAdditionJSON(resourceManager);
        parsePoolsAndBeginMerger(poolAdditionJSON, event.getServer().registryAccess());
    }

    /**
     * Will grab all JSON objects from all datapacks's pool_additions folder.
     *
     * @return - A map of paths (resourceLocations) to a list of all JSON elements found under it from all datapacks.
     */
    private static Map<ResourceLocation, List<JsonElement>> getPoolAdditionJSON(IResourceManager resourceManager) {
        Map<ResourceLocation, List<JsonElement>> map = new HashMap<>();
        int dataTypeLength = DATA_TYPE.length() + 1;

        // Finds all JSON files paths within the pool_additions folder. NOTE: this is just the path rn. Not the actual files yet.
        for (ResourceLocation fileIDWithExtension : resourceManager.listResources(DATA_TYPE, (fileString) -> fileString.endsWith(".json"))) {
            String resourceLocationPath = fileIDWithExtension.getPath();
            ResourceLocation fileID = new ResourceLocation(
                    fileIDWithExtension.getNamespace(),
                    resourceLocationPath.substring(dataTypeLength, resourceLocationPath.length() - FILE_SUFFIX_LENGTH));

            try {
                // getAllFileStreams will find files with the given ID. This part is what will loop over all matching files from all datapacks.
                for (InputStream fileStream : getAllFileStreams(resourceManager, fileIDWithExtension)) {
                    try (Reader bufferedReader = new BufferedReader(new InputStreamReader(fileStream, StandardCharsets.UTF_8))) {

                        // Get the JSON from the file
                        JsonElement poolJSONElement = JSONUtils.fromJson(GSON, bufferedReader, (Class<? extends JsonElement>) JsonElement.class);
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
    private static List<InputStream> getAllFileStreams(IResourceManager resourceManager, ResourceLocation fileID) throws IOException {
        List<InputStream> fileStreams = new ArrayList<>();

        FallbackResourceManager namespaceResourceManager = ((SimpleReloadableResourceManagerAccessor) resourceManager).repurposedstructures_getFallbackResourceManager().get(fileID.getNamespace());
        List<IResourcePack> allResourcePacks = ((FallbackResourceManagerAccessor) namespaceResourceManager).repurposedstructures_getPackList();

        // Find the file with the given id and add its filestream to the list
        for (IResourcePack resourcePack : allResourcePacks) {
            if (resourcePack.hasResource(ResourcePackType.SERVER_DATA, fileID)) {
                InputStream inputStream = ((FallbackResourceManagerAccessor) namespaceResourceManager).repurposedstructures_callGetWrappedResource(fileID, resourcePack);
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
    private static void parsePoolsAndBeginMerger(Map<ResourceLocation, List<JsonElement>> poolAdditionJSON, DynamicRegistries dynamicRegistries) {
        MutableRegistry<JigsawPattern> poolRegistry = dynamicRegistries.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY);
        // A WorldSettingsImport that doesn't break everything under the sun and can take a DynamicRegistries instead of DynamicRegistries.Impl.
        SafeDecodingRegistryOps<JsonElement> customRegistryOps = new SafeDecodingRegistryOps<>(JsonOps.INSTANCE, dynamicRegistries);

        // Will iterate over all of our found pool additions and make sure the target pool exists before we parse our JSON objects
        for (Map.Entry<ResourceLocation, List<JsonElement>> entry : poolAdditionJSON.entrySet()) {
            if (poolRegistry.get(entry.getKey()) == null) continue;

            // Parse the given pool addition JSON objects and add their pool to the dynamic registry pool
            for (JsonElement jsonElement : entry.getValue()) {
                JigsawPattern.DIRECT_CODEC.parse(customRegistryOps, jsonElement)
                        .resultOrPartial(messageString -> logBadData(entry.getKey(), messageString))
                        .ifPresent(validPool -> mergeIntoExistingPool(validPool, poolRegistry.get(entry.getKey())));
            }
        }
    }

    /**
     * Merges the incoming pool with the given target pool in an additive manner that does not affect any other pools and can be stacked safely.
     */
    private static void mergeIntoExistingPool(JigsawPattern feedingPool, JigsawPattern gluttonyPool) {
        // Make new copies of lists as the originals are immutable lists and we want to make sure our changes only stays with this pool element
        List<JigsawPiece> elements = new ArrayList<>(((JigsawPatternAccessor) gluttonyPool).repurposedstructures_getTemplates());
        List<Pair<JigsawPiece, Integer>> elementCounts = new ArrayList<>(((JigsawPatternAccessor) gluttonyPool).repurposedstructures_getRawTemplates());

        elements.addAll(((JigsawPatternAccessor) feedingPool).repurposedstructures_getTemplates());
        elementCounts.addAll(((JigsawPatternAccessor) feedingPool).repurposedstructures_getRawTemplates());

        ((JigsawPatternAccessor) gluttonyPool).repurposedstructures_setTemplates(elements);
        ((JigsawPatternAccessor) gluttonyPool).repurposedstructures_setRawTemplates(elementCounts);
    }

    /**
     * Log out the pool that failed to be parsed and what the error is.
     */
    private static void logBadData(ResourceLocation poolPath, String messageString) {
        RepurposedStructures.LOGGER.error("(POOL MERGER) Failed to parse {} additions file. Error is: {}", poolPath, messageString);
    }
}
