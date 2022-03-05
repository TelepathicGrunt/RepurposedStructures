package com.telepathicgrunt.repurposedstructures.misc.mobspawner;

import com.google.gson.annotations.Expose;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.ForgeRegistries;

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
        if(!ForgeRegistries.ENTITIES.containsKey(entity_id))
            throw new Exception("Error: " + entity_id + " is not a valid entity ID!");
        entityType = ForgeRegistries.ENTITIES.getValue(entity_id);
    }
}
