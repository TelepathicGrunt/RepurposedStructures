package com.telepathicgrunt.repurposedstructures.misc;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StructurePieceCountsManager extends SimpleJsonResourceReloadListener implements IdentifiableResourceReloadListener {
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().setLenient().disableHtmlEscaping().excludeFieldsWithoutExposeAnnotation().create();
    private Map<ResourceLocation, List<StructurePieceCountsObj>> StructureToPieceCountsObjs = ImmutableMap.of();
    private final ResourceLocation STRUCTURE_PIECE_COUNT_MANAGER_ID = new ResourceLocation(RepurposedStructures.MODID, "structure_piece_count_manager");
    private final Map<ResourceLocation, Map<ResourceLocation, RequiredPieceNeeds>> cachedRequirePiecesMap = new HashMap<>();
    private final Map<ResourceLocation, Map<ResourceLocation, Integer>> cachedMaxCountPiecesMap = new HashMap<>();

    public StructurePieceCountsManager() {
        super(GSON, "rs_pieces_spawn_counts");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> loader, ResourceManager manager, ProfilerFiller profiler) {
        ImmutableMap.Builder<ResourceLocation, List<StructurePieceCountsObj>> mapBuilder = ImmutableMap.builder();
        loader.forEach((fileIdentifier, jsonElement) -> {
            try {
                List<StructurePieceCountsObj> piecesSpawnCounts = GSON.fromJson(jsonElement.getAsJsonObject().get("pieces_spawn_counts"), new TypeToken<List<StructurePieceCountsObj>>(){}.getType());
                for(int i = piecesSpawnCounts.size() - 1; i >= 0; i--){
                    StructurePieceCountsObj entry = piecesSpawnCounts.get(i);
                    if(entry.alwaysSpawnThisMany != null && entry.neverSpawnMoreThanThisMany != null && entry.alwaysSpawnThisMany > entry.neverSpawnMoreThanThisMany){
                        throw new Exception("Error: Found " + entry.nbtPieceName + " entry has alwaysSpawnThisMany greater than neverSpawnMoreThanThisMany which is invalid.");
                    }
                }
                mapBuilder.put(fileIdentifier, ImmutableList.copyOf(piecesSpawnCounts));
            }
            catch (Exception e) {
                RepurposedStructures.LOGGER.error("Repurposed Structures Error: Couldn't parse spawner mob list {}", fileIdentifier, e);
            }
        });
        this.StructureToPieceCountsObjs = mapBuilder.build();
        cachedRequirePiecesMap.clear();
    }

    @Nullable
    public Map<ResourceLocation, RequiredPieceNeeds> getRequirePieces(ResourceLocation structureRL) {
        // check to make sure we do have entries for this structure
        if(!this.StructureToPieceCountsObjs.containsKey(structureRL))
            return null;

        // if cached, return cached map
        if(cachedRequirePiecesMap.containsKey(structureRL)) {
            return cachedRequirePiecesMap.get(structureRL);
        }
        // otherwise, compute the required pieces map to return and cache
        else {
            Map<ResourceLocation, RequiredPieceNeeds> requirePiecesMap = new HashMap<>();
            List<StructurePieceCountsObj> structurePieceCountsObjs = this.StructureToPieceCountsObjs.get(structureRL);
            if(structurePieceCountsObjs != null) {
                structurePieceCountsObjs.forEach(entry -> {
                    if (entry.alwaysSpawnThisMany != null)
                        requirePiecesMap.put(new ResourceLocation(entry.nbtPieceName), new RequiredPieceNeeds(entry.alwaysSpawnThisMany, entry.minimumDistanceFromCenterPiece != null ? entry.minimumDistanceFromCenterPiece : 0));
                });
            }
            cachedRequirePiecesMap.put(structureRL, requirePiecesMap);
            return requirePiecesMap;
        }
    }

    @MethodsReturnNonnullByDefault
    public Map<ResourceLocation, Integer> getMaximumCountForPieces(ResourceLocation structureRL) {
        // if cached, return cached map
        if(cachedMaxCountPiecesMap.containsKey(structureRL)) {
            return cachedMaxCountPiecesMap.get(structureRL);
        }
        // otherwise, compute the max count pieces map to return and cache
        else {
            Map<ResourceLocation, Integer> maxCountPiecesMap = new HashMap<>();
            List<StructurePieceCountsObj> structurePieceCountsObjs = this.StructureToPieceCountsObjs.get(structureRL);
            if(structurePieceCountsObjs != null) {
                structurePieceCountsObjs.forEach(entry -> {
                    if(entry.neverSpawnMoreThanThisMany != null)
                        maxCountPiecesMap.put(new ResourceLocation(entry.nbtPieceName), entry.neverSpawnMoreThanThisMany);
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

    @Override
    public ResourceLocation getFabricId() {
        return STRUCTURE_PIECE_COUNT_MANAGER_ID;
    }
}
