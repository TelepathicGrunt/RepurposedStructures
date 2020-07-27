package com.telepathicgrunt.repurposedstructures.utils;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.tag.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.InvalidResourceLocationException;
import net.minecraft.util.Pair;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Level;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MobSpawnerManager extends JsonDataLoader{
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    private Map<ResourceLocation, List<MobSpawnerObj>> spawnerMap = ImmutableMap.of();
    public MobSpawnerManager() {
        super(GSON, "rs_spawners");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> loader, ResourceManager manager, Profiler profiler) {
        ImmutableMap.Builder<ResourceLocation, List<MobSpawnerObj>> builder = ImmutableMap.builder();
        loader.forEach((fileResourceLocation, jsonElement) -> {
            try {
                List<MobSpawnerObj> spawnerMobEntries = GSON.fromJson(jsonElement.getAsJsonObject().get("mobs"), new TypeToken<List<MobSpawnerObj>>(){}.getType());
                for(MobSpawnerObj entry : spawnerMobEntries)
                    entry.setEntityType();
                builder.put(fileResourceLocation, spawnerMobEntries);
            }
            catch (Exception e) {
                RepurposedStructures.LOGGER.error("Couldn't parse spawner mob list {}", fileResourceLocation, e);
            }
        });
        this.spawnerMap =  builder.build();
    }

    public EntityType<?> getSpawnerMob(ResourceLocation spawnerJsonEntry, Random random) {
        List<MobSpawnerObj> spawnerMobEntries = this.spawnerMap.get(spawnerJsonEntry);
        int totalWeight = spawnerMobEntries.stream().mapToInt(mobEntry -> mobEntry.weight).sum();
        int randomWeight = random.nextInt(totalWeight) + 1;
        int index = 0;

        while(randomWeight > 0){
            randomWeight -= spawnerMobEntries.get(index).weight;
            if(randomWeight <= 0)
                return  Registry.ENTITY_TYPE.get(new ResourceLocation(spawnerMobEntries.get(index).name));

            index++;
        }

        RepurposedStructures.LOGGER.log(Level.ERROR,"***************************************\nFailed to get mob. Please check that "+spawnerJsonEntry+".json is correct and let Telepathicgrunt (mod author) know he broke the mob spawner code!\n***************************************");
        return EntityType.PIG;
    }
}
