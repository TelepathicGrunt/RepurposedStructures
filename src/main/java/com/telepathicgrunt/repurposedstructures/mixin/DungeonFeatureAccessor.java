package com.telepathicgrunt.repurposedstructures.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.world.gen.feature.DungeonFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(DungeonFeature.class)
public interface DungeonFeatureAccessor {

    @Accessor("MOB_SPAWNER_ENTITIES")
    static EntityType<?>[] repurposedstructures_getMOB_SPAWNER_ENTITIES() {
        throw new UnsupportedOperationException();
    }
}