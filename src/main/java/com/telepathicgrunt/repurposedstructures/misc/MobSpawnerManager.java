package com.telepathicgrunt.repurposedstructures.misc;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.features.DungeonFeatureAccessor;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.entity.EntityType;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Level;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class MobSpawnerManager extends JsonDataLoader implements IdentifiableResourceReloadListener {
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().setLenient().disableHtmlEscaping().create();
    private Map<Identifier, List<MobSpawnerObj>> spawnerMap = ImmutableMap.of();
    private final Identifier MOB_SPAWNER_MANAGER_ID = new Identifier(RepurposedStructures.MODID, "mob_spawner_manager");

    public MobSpawnerManager() {
        super(GSON, "rs_spawners");
    }

    @Override
    protected void apply(Map<Identifier, JsonElement> loader, ResourceManager manager, Profiler profiler) {
        ImmutableMap.Builder<Identifier, List<MobSpawnerObj>> builder = ImmutableMap.builder();
        loader.forEach((fileIdentifier, jsonElement) -> {
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
                builder.put(fileIdentifier, spawnerMobEntries);
            }
            catch (Exception e) {
                RepurposedStructures.LOGGER.error("Repurposed Structures Error: Couldn't parse spawner mob list {}", fileIdentifier, e);
            }
        });
        this.spawnerMap =  builder.build();
    }

    public EntityType<?> getSpawnerMob(Identifier spawnerJsonEntry, Random random) {
        List<MobSpawnerObj> spawnerMobEntries = this.spawnerMap.get(spawnerJsonEntry);
        if(spawnerMobEntries == null){
            RepurposedStructures.LOGGER.log(Level.ERROR,"\n***************************************\nFailed to get mob. Please check that "+spawnerJsonEntry+".json is correct or that no other mod is interfering with how vanilla reads data folders. Let TelepathicGrunt know about this too!\n***************************************");
            return Util.getRandom(DungeonFeatureAccessor.repurposedstructures_getMOB_SPAWNER_ENTITIES(), random);
        }

        int totalWeight = spawnerMobEntries.stream().mapToInt(mobEntry -> mobEntry.weight).sum();
        if(totalWeight == 0){
            RepurposedStructures.LOGGER.log(Level.ERROR, """
                    ***************************************
                    Empty %s.json found. At least 1 entitytype with a weight of 1 or more must be specified. If you want to remove spawner block, override the structure's nbt file or processor file and replace the spawner block that way instead.
                    ***************************************
                    """.formatted(spawnerJsonEntry));
            return EntityType.PIG;
        }

        int randomWeight = random.nextInt(totalWeight) + 1;
        int index = 0;

        try{
            while(true){
                randomWeight -= spawnerMobEntries.get(index).weight;
                if(randomWeight <= 0)
                    return Registry.ENTITY_TYPE.get(new Identifier(spawnerMobEntries.get(index).name));

                index++;
            }
        }
        catch(Exception e){
            RepurposedStructures.LOGGER.log(Level.ERROR,"\n***************************************\nFailed to get mob. Please check that "+spawnerJsonEntry+".json is correct and let Telepathicgrunt (mod author) know he broke the mob spawner code!\n***************************************");
            return EntityType.PIG;
        }
    }

    @Override
    public Identifier getFabricId() {
        return MOB_SPAWNER_MANAGER_ID;
    }
}
