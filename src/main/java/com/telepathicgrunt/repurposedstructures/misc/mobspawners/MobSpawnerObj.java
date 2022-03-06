package com.telepathicgrunt.repurposedstructures.misc.mobspawners;

import com.google.gson.annotations.Expose;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;

public class MobSpawnerObj {
    @Expose
    public String name;

    @Expose
    public float weight;

    public transient EntityType<?> entityType;

    public MobSpawnerObj(String name, float weight) {
        this.name = name;
        this.weight = weight;
    }

    public void setEntityType() throws Exception {
        ResourceLocation entity_id = new ResourceLocation(this.name);
        if(Registry.ENTITY_TYPE.getOptional(entity_id).isEmpty())
            throw new Exception("Error: " + entity_id + " is not a valid entity ID!");
        entityType = Registry.ENTITY_TYPE.get(entity_id);
    }
}
