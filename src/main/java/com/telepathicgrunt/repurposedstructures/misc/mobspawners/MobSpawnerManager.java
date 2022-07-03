package com.telepathicgrunt.repurposedstructures.misc.mobspawners;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.features.DungeonFeatureAccessor;
import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.RandomSource;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.EntityType;
import org.apache.logging.log4j.Level;
import org.quiltmc.qsl.resource.loader.api.reloader.IdentifiableResourceReloader;

import java.util.List;
import java.util.Map;

public class MobSpawnerManager extends SimpleJsonResourceReloadListener implements IdentifiableResourceReloader {
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().setLenient().disableHtmlEscaping().excludeFieldsWithoutExposeAnnotation().create();
    private Map<ResourceLocation, List<MobSpawnerObj>> spawnerMap = ImmutableMap.of();
    private final ResourceLocation MOB_SPAWNER_MANAGER_ID = new ResourceLocation(RepurposedStructures.MODID, "mob_spawner_manager");

    public MobSpawnerManager() {
        super(GSON, "rs_spawners");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> loader, ResourceManager manager, ProfilerFiller profiler) {
        ImmutableMap.Builder<ResourceLocation, List<MobSpawnerObj>> builder = ImmutableMap.builder();
        loader.forEach((fileIdentifier, jsonElement) -> {
            try {
                List<MobSpawnerObj> spawnerMobEntries = GSON.fromJson(jsonElement.getAsJsonObject().get("mobs"), new TypeToken<List<MobSpawnerObj>>() {}.getType());
                for(int i = spawnerMobEntries.size() - 1; i >= 0; i--) {
                    MobSpawnerObj entry = spawnerMobEntries.get(i);
                    entry.setEntityType();
                    if(entry.weight == 0 || entry.entityType == null) {
                        // Make 0 remove the mob automatically so it doesn't spawn.
                        spawnerMobEntries.remove(i);
                    }
                    else if(entry.weight < 0) {
                        throw new Exception("Error: Found " + entry.name + " entry has a weight less than 0. Please remove the entry if you don't want a mob to be picked");
                    }
                }
                builder.put(fileIdentifier, spawnerMobEntries);
            }
            catch (Exception e) {
                RepurposedStructures.LOGGER.error("Repurposed Structures Error: Couldn't parse spawner mob list {}", fileIdentifier, e);
            }
        });
        this.spawnerMap = builder.build();
    }

    public EntityType<?> getSpawnerMob(ResourceLocation spawnerJsonEntry, RandomSource random) {
        List<MobSpawnerObj> spawnerMobEntries = this.spawnerMap.get(spawnerJsonEntry);
        if(spawnerMobEntries == null) {
            RepurposedStructures.LOGGER.log(Level.ERROR,"\n***************************************\nFailed to get mob. Please check that "+spawnerJsonEntry+".json is correct or that no other mod is interfering with how vanilla reads data folders. Let TelepathicGrunt know about this too!\n***************************************");
            return Util.getRandom(DungeonFeatureAccessor.getMOBS(), random);
        }

        // Already did a check to make sure all entries do not have a negative weight earlier.
        float totalWeight = 0;
        for(MobSpawnerObj mobSpawnerObj : spawnerMobEntries) {
            totalWeight += mobSpawnerObj.weight;
        }
        if(totalWeight == 0) {
            return null;
        }

        float randomWeight = random.nextFloat() * totalWeight;
        int index = 0;

        try {
            while(true) {
                randomWeight -= spawnerMobEntries.get(index).weight;
                if(randomWeight <= 0)
                    return Registry.ENTITY_TYPE.get(new ResourceLocation(spawnerMobEntries.get(index).name));

                index++;
            }
        }
        catch(Exception e) {
            RepurposedStructures.LOGGER.log(Level.ERROR,"\n***************************************\nFailed to get mob. Please check that "+spawnerJsonEntry+".json is correct and let Telepathicgrunt (mod author) know he broke the mob spawner code!\n***************************************");
            return EntityType.PIG;
        }
    }

    @Override
    public ResourceLocation getQuiltId() {
        return MOB_SPAWNER_MANAGER_ID;
    }
}
