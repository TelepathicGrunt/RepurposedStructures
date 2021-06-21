package com.telepathicgrunt.repurposedstructures.mixin.structures;

import net.minecraft.util.collection.Pool;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.PillagerOutpostFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(PillagerOutpostFeature.class)
public interface PillagerOutpostFeatureAccessor {
    @Accessor("MONSTER_SPAWNS")
    static Pool<SpawnSettings.SpawnEntry> repurposedstructures_getMONSTER_SPAWNS() {
        throw new UnsupportedOperationException();
    }
}
