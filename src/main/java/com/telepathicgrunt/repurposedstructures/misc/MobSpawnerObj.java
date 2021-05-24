package com.telepathicgrunt.repurposedstructures.misc;

import com.google.gson.annotations.Expose;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class MobSpawnerObj {
    @Expose
    public String name;

    @Expose
    public int weight;

    public transient EntityType<?> entityType;

    public MobSpawnerObj(String name, double weight){
        this.name = name;
        this.weight = (int)weight;
    }

    public void setEntityType() throws Exception {
        ResourceLocation entity_id = new ResourceLocation(this.name);
        if(!ForgeRegistries.ENTITIES.containsKey(entity_id))
            throw new Exception("Error: "+entity_id+" is not a valid entity ID!");
        entityType = ForgeRegistries.ENTITIES.getValue(entity_id);
    }
}
