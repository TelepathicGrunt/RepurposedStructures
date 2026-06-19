package com.telepathicgrunt.repurposedstructures.misc.structurepiececounter;

import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConditionsRegistry;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static com.telepathicgrunt.repurposedstructures.RepurposedStructures.GSON;

public class StructurePieceCountsManager extends SimpleJsonResourceReloadListener<JsonElement> {
    public final static StructurePieceCountsManager STRUCTURE_PIECE_COUNTS_MANAGER = new StructurePieceCountsManager();

    private Map<Identifier, List<StructurePieceCountsObj>> StructureToPieceCountsObjs = new HashMap<>();
    private final Map<Identifier, Map<Identifier, RequiredPieceNeeds>> cachedRequirePiecesMap = new HashMap<>();
    private final Map<Identifier, Map<Identifier, Integer>> cachedMaxCountPiecesMap = new HashMap<>();

    public StructurePieceCountsManager() {
        // NOTE: Anyone copying this class, PLEASE CHANGE THE BELOW STRING TO BE UNIQUE!!!!
        // If you do not, both of our mods will read the same file twice and apply the file twice, causing duplicate spawn pieces application!!!
        super(ExtraCodecs.JSON, FileToIdConverter.json("rs_pieces_spawn_counts"));
    }

    private List<StructurePieceCountsObj> getStructurePieceCountsObjs(Identifier fileIdentifier, JsonElement jsonElement) throws Exception {
        List<StructurePieceCountsObj> piecesSpawnCounts = GSON.fromJson(jsonElement.getAsJsonObject().get("pieces_spawn_counts"), new TypeToken<List<StructurePieceCountsObj>>() {}.getType());
        for(int i = piecesSpawnCounts.size() - 1; i >= 0; i--) {
            StructurePieceCountsObj entry = piecesSpawnCounts.get(i);
            if(entry.alwaysSpawnThisMany != null && entry.neverSpawnMoreThanThisMany != null && entry.alwaysSpawnThisMany > entry.neverSpawnMoreThanThisMany) {
                throw new Exception("Repurposed Structures Error: Found " + entry.nbtPieceName + " entry has alwaysSpawnThisMany greater than neverSpawnMoreThanThisMany which is invalid.");
            }
            if(entry.condition != null) {
                Supplier<Boolean> supplier = RSConditionsRegistry.RS_JSON_CONDITIONS_REGISTRY.lookup().get(Identifier.tryParse(entry.condition));
                if (supplier != null) {
                    if(!supplier.get()) {
                        piecesSpawnCounts.remove(entry);
                    }
                }
                else {
                    RepurposedStructures.LOGGER.error("Repurposed Structures Error: Found {} entry has a condition that does not exist. Extra info: {}", entry.nbtPieceName, fileIdentifier);
                }
            }
        }
        return piecesSpawnCounts;
    }

    @Override
    protected void apply(Map<Identifier, JsonElement> loader, ResourceManager manager, ProfilerFiller profiler) {
        Map<Identifier, List<StructurePieceCountsObj>> mapBuilder = new HashMap<>();
        loader.forEach((fileIdentifier, jsonElement) -> {
            try {
                mapBuilder.put(Identifier.parse(jsonElement.getAsJsonObject().get("target_structure").getAsString()), getStructurePieceCountsObjs(fileIdentifier, jsonElement));
            }
            catch (Exception e) {
                RepurposedStructures.LOGGER.error("Repurposed Structures Error: Couldn't parse rs_pieces_spawn_counts file {} - JSON looks like: {}", fileIdentifier, jsonElement, e);
            }
        });
        this.StructureToPieceCountsObjs = mapBuilder;
        cachedRequirePiecesMap.clear();
    }

    public void parseAndAddCountsJSONObj(Identifier structureRL, JsonElement jsonElement) {
        try {
            this.StructureToPieceCountsObjs.computeIfAbsent(structureRL, rl -> new ArrayList<>()).addAll(getStructurePieceCountsObjs(structureRL, jsonElement));
        }
        catch (Exception e) {
            RepurposedStructures.LOGGER.error("Repurposed Structures Error: Couldn't parse rs_pieces_spawn_counts file {} - JSON looks like: {}", structureRL, jsonElement, e);
        }
    }

    @Nullable
    public Map<Identifier, RequiredPieceNeeds> getRequirePieces(Identifier structureRL) {
        // check to make sure we do have entries for this structure
        if(!this.StructureToPieceCountsObjs.containsKey(structureRL))
            return null;

        // if cached, return cached map
        if(cachedRequirePiecesMap.containsKey(structureRL)) {
            return cachedRequirePiecesMap.get(structureRL);
        }
        // otherwise, compute the required pieces map to return and cache
        else {
            Map<Identifier, RequiredPieceNeeds> requirePiecesMap = new HashMap<>();
            List<StructurePieceCountsObj> structurePieceCountsObjs = this.StructureToPieceCountsObjs.get(structureRL);
            if(structurePieceCountsObjs != null) {
                structurePieceCountsObjs.forEach(entry -> {
                    if (entry.alwaysSpawnThisMany != null)
                        requirePiecesMap.put(Identifier.tryParse(entry.nbtPieceName), new RequiredPieceNeeds(entry.alwaysSpawnThisMany, entry.minimumDistanceFromCenterPiece != null ? entry.minimumDistanceFromCenterPiece : 0));
                });
            }
            cachedRequirePiecesMap.put(structureRL, requirePiecesMap);
            return requirePiecesMap;
        }
    }

    public Map<Identifier, Integer> getMaximumCountForPieces(Identifier structureRL) {
        // if cached, return cached map
        if(cachedMaxCountPiecesMap.containsKey(structureRL)) {
            return cachedMaxCountPiecesMap.get(structureRL);
        }
        // otherwise, compute the max count pieces map to return and cache
        else {
            Map<Identifier, Integer> maxCountPiecesMap = new HashMap<>();
            List<StructurePieceCountsObj> structurePieceCountsObjs = this.StructureToPieceCountsObjs.get(structureRL);
            if(structurePieceCountsObjs != null) {
                structurePieceCountsObjs.forEach(entry -> {
                    if(entry.neverSpawnMoreThanThisMany != null)
                        maxCountPiecesMap.put(Identifier.tryParse(entry.nbtPieceName), entry.neverSpawnMoreThanThisMany);
                });
            }
            cachedMaxCountPiecesMap.put(structureRL, maxCountPiecesMap);
            return maxCountPiecesMap;
        }
    }

    public record RequiredPieceNeeds(int maxLimit, int minDistanceFromCenter) {
        public int getRequiredAmount() {
            return maxLimit;
        }

        public int getMinDistanceFromCenter() {
            return minDistanceFromCenter;
        }
    }
}
