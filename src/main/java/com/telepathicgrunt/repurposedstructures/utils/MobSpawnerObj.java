package com.telepathicgrunt.repurposedstructures.utils;

import com.google.gson.annotations.Expose;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

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
        Identifier entity_id = new Identifier(this.name);
        if(Registry.ENTITY_TYPE.get(entity_id) != null)
            throw new Exception("Error: "+entity_id+" is not a valid entity ID!");
        entityType = Registry.ENTITY_TYPE.get(entity_id);
    }
}
