package com.telepathicgrunt.repurposedstructures.mixin.features;

import net.minecraft.entity.EntityType;
import net.minecraft.world.gen.feature.DungeonsFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(DungeonsFeature.class)
public interface DungeonFeatureAccessor {

    @Accessor("MOBS")
    static EntityType<?>[] repurposedstructures_getMOB_SPAWNER_ENTITIES() {
        throw new UnsupportedOperationException();
    }
}