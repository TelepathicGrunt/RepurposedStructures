package com.telepathicgrunt.repurposedstructures.utils;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.InvalidIdentifierException;
import net.minecraft.util.Pair;
import net.minecraft.util.profiler.Profiler;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MobSpawnerManager extends JsonDataLoader{
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    private Map<Identifier, List<Pair<EntityType<?>, Integer>>> spawnerMap = ImmutableMap.of();
    public MobSpawnerManager() {
        super(GSON, "rs_spawners");
    }

    @Override
    protected void apply(Map<Identifier, JsonElement> loader, ResourceManager manager, Profiler profiler) {
        ImmutableMap.Builder<Identifier, List<Pair<EntityType<?>, Integer>>> builder = ImmutableMap.builder();
        JsonElement jsonElement = (JsonElement)loader.remove(LootTables.EMPTY);

        loader.forEach((identifier, jsonElementx) -> {
            try {
                Object spawnerList = GSON.fromJson(jsonElement, Object.class);
                builder.put(identifier, (List<Pair<EntityType<?>, Integer>>) spawnerList);
            } catch (Exception var4) {
                RepurposedStructures.LOGGER.error("Couldn't parse spawner mob list {}", identifier, var4);
            }

        });
        builder.put(new Identifier(RepurposedStructures.MODID,"empty_spawner"), new ArrayList<>());
        ImmutableMap<Identifier, List<Pair<EntityType<?>, Integer>>> immutableMap = builder.build();
        immutableMap.getClass();

        this.spawnerMap = immutableMap;
    }

    public EntityType<?> getSpawnerMob(Identifier spawnerJsonEntry) {
        try {
            return spawnerMap.get(spawnerJsonEntry).get(0).getLeft();
        }
        catch (IndexOutOfBoundsException e) {
            RepurposedStructures.LOGGER.log(Level.ERROR,"***************************************\nFailed to load mob spawner. Please check that "+spawnerJsonEntry+".json is correct. The mob weight must be a positive number greater than 0.\n***************************************");
            return EntityType.PIG;
        }
        catch (InvalidIdentifierException e) {
            RepurposedStructures.LOGGER.log(Level.ERROR,"***************************************\nFailed to load mob spawner. Please check that "+spawnerJsonEntry+".json is correct. The mob ID must exist.\n***************************************");
            return EntityType.PIG;
        }
    }
}
