package com.telepathicgrunt.repurposedstructures.misc.structurepiececounter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;

import java.util.List;
import java.util.Map;

public final class StructurePieceCountsAdditionsMerger {
    private StructurePieceCountsAdditionsMerger() {}

    // Needed for detecting the correct files, ignoring file extension, and what JSON parser to use for parsing the files
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().setLenient().disableHtmlEscaping().create();
    private static final String DATA_TYPE = "rs_pieces_spawn_counts_additions";
    private static final int FILE_SUFFIX_LENGTH = ".json".length();

    /**
     * Call this at end of StructurePieceCountsManager's apply to make sure we merge in all found counts additions into the parsed based counts.
     */
    static void performCountsAdditionsDetectionAndMerger(ResourceManager resourceManager) {
        Map<ResourceLocation, List<JsonElement>> countsAdditionJSON = GeneralUtils.getAllDatapacksJSONElement(resourceManager, GSON, DATA_TYPE, FILE_SUFFIX_LENGTH);
        parseCountsAndBeginMerger(countsAdditionJSON);
    }

    /**
     * Will iterate over all of our found counts additions and parse our JSON objects to shove the final product into StructurePiecesCountsManager
     */
    private static void parseCountsAndBeginMerger(Map<ResourceLocation, List<JsonElement>> countsAdditionJSON) {
        for (Map.Entry<ResourceLocation, List<JsonElement>> entry : countsAdditionJSON.entrySet()) {
            RepurposedStructures.structurePieceCountsManager.parseAndAddCountsJSONObj(entry.getKey(), entry.getValue());
        }
    }
}
