package com.telepathicgrunt.repurposedstructures.mixin.features;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.levelgen.feature.MonsterRoomFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MonsterRoomFeature.class)
public interface DungeonFeatureAccessor {

    @Accessor("MOB_SPAWNER_ENTITIES")
    static EntityType<?>[] repurposedstructures_getMOB_SPAWNER_ENTITIES() {
        throw new UnsupportedOperationException();
    }
}