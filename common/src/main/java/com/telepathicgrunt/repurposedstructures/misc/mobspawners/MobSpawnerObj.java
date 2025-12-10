package com.telepathicgrunt.repurposedstructures.misc.mobspawners;

import com.google.gson.annotations.Expose;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;

public class MobSpawnerObj {
    @Expose
    public String name;

    @Expose
    public float weight;

    @Expose
    public boolean optional = false;

    public transient EntityType<?> entityType = null;

    public MobSpawnerObj(String name, float weight) {
        this.name = name;
        this.weight = weight;
    }

    public MobSpawnerObj(String name, float weight, boolean optional) {
        this.name = name;
        this.weight = weight;
        this.optional = optional;
    }

    public void setEntityType() throws Exception {
        Identifier entityRl = Identifier.tryParse(this.name);
        if(BuiltInRegistries.ENTITY_TYPE.containsKey(entityRl)) {
            entityType = BuiltInRegistries.ENTITY_TYPE.getValue(entityRl);
        }
        else if(!optional) {
            throw new Exception("Error: " + entityRl + " is not a valid entity ID!");
        }
    }
}
