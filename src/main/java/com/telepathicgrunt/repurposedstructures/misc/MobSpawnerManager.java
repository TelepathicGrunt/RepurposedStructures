package com.telepathicgrunt.repurposedstructures.misc;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.DungeonFeatureAccessor;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.entity.EntityType;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.Level;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class MobSpawnerManager extends JsonReloadListener {
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    private Map<ResourceLocation, List<MobSpawnerObj>> spawnerMap = ImmutableMap.of();
    public MobSpawnerManager() {
        super(GSON, "rs_spawners");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> loader, IResourceManager manager, IProfiler profiler) {
        ImmutableMap.Builder<ResourceLocation, List<MobSpawnerObj>> builder = ImmutableMap.builder();
        loader.forEach((fileResourceLocation, jsonElement) -> {
            try {
                List<MobSpawnerObj> spawnerMobEntries = GSON.fromJson(jsonElement.getAsJsonObject().get("mobs"), new TypeToken<List<MobSpawnerObj>>(){}.getType());
                for(int i = spawnerMobEntries.size() - 1; i >= 0; i--){
                    MobSpawnerObj entry = spawnerMobEntries.get(i);
                    entry.setEntityType();
                    if(entry.weight == 0){
                        // Make 0 remove the mob automatically so it doesn't spawn.
                        spawnerMobEntries.remove(i);
                    }
                    else if(entry.weight < 0){
                        throw new Exception("Error: Found " + entry.name + " entry has a weight less than 0. Please remove the entry if you don't want a mob to be picked");
                    }
                }
                builder.put(fileResourceLocation, spawnerMobEntries);
            }
            catch (Exception e) {
                RepurposedStructures.LOGGER.error(" Couldn't parse spawner mob list {}", fileResourceLocation, e);
            }
        });
        this.spawnerMap = builder.build();
    }

    public EntityType<?> getSpawnerMob(ResourceLocation spawnerJsonEntry, Random random) {
        List<MobSpawnerObj> spawnerMobEntries = this.spawnerMap.get(spawnerJsonEntry);
        if(spawnerMobEntries == null){
            RepurposedStructures.LOGGER.log(Level.ERROR,"\n***************************************\nFailed to get mob. Please check that "+spawnerJsonEntry+".json is correct or that no other mod is interfering with how vanilla reads data folders. Let TelepathicGrunt know about this too!\n***************************************");
            return Util.getRandom(DungeonFeatureAccessor.repurposedstructures_getMOB_SPAWNER_ENTITIES(), random);
        }

        int totalWeight = spawnerMobEntries.stream().mapToInt(mobEntry -> mobEntry.weight).sum();
        if(totalWeight == 0){
            RepurposedStructures.LOGGER.log(Level.ERROR, "\n***************************************\nEmpty "+spawnerJsonEntry+".json found. At least 1 entitytype with a weight of 1 or more must be specified. If you want to remove spawner block, override the structure's nbt file or processor file and replace the spawner block that way instead.\n***************************************\n");
            return EntityType.PIG;
        }

        int randomWeight = random.nextInt(totalWeight) + 1;
        int index = 0;

        try{
            while(true){
                randomWeight -= spawnerMobEntries.get(index).weight;
                if(randomWeight <= 0)
                    return ForgeRegistries.ENTITIES.getValue(new ResourceLocation(spawnerMobEntries.get(index).name));

                index++;
            }
        }
        catch(Exception e){
            RepurposedStructures.LOGGER.log(Level.ERROR,"\n***************************************\nFailed to get mob. Please check that "+spawnerJsonEntry+".json is correct and let Telepathicgrunt (mod author) know he broke the mob spawner code!\n***************************************");
            return EntityType.PIG;
        }
    }
}
