package com.telepathicgrunt.repurposedstructures.misc;

import com.google.gson.annotations.Expose;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;

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
        if(Registry.ENTITY_TYPE.getOptional(entity_id).isEmpty())
            throw new Exception("Error: " + entity_id + " is not a valid entity ID!");
        entityType = Registry.ENTITY_TYPE.get(entity_id);
    }
}
