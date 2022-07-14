package com.telepathicgrunt.repurposedstructures.misc.mobspawners;

import com.google.gson.annotations.Expose;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.ForgeRegistries;

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
        ResourceLocation entity_id = new ResourceLocation(this.name);
        if(ForgeRegistries.ENTITY_TYPES.containsKey(entity_id)) {
            entityType = ForgeRegistries.ENTITY_TYPES.getValue(entity_id);
        }
        else if(!optional) {
            throw new Exception("Error: " + entity_id + " is not a valid entity ID!");
        }
    }
}
