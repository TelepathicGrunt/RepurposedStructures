package com.telepathicgrunt.repurposedstructures.mixin;

import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.PillagerOutpostFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(PillagerOutpostFeature.class)
public interface PillagerOutpostFeatureAccessor {
    @Accessor("MONSTER_SPAWNS")
    static List<SpawnSettings.SpawnEntry> getMONSTER_SPAWNS() {
        throw new UnsupportedOperationException();
    }
}
