package com.telepathicgrunt.repurposedstructures.mixins.features;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.levelgen.feature.MonsterRoomFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MonsterRoomFeature.class)
public interface DungeonFeatureAccessor {

    @Accessor("MOBS")
    static EntityType<?>[] getMOBS() {
        throw new UnsupportedOperationException();
    }
}