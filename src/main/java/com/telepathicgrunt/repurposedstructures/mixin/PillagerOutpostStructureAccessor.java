package com.telepathicgrunt.repurposedstructures.mixin;

import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.feature.structure.PillagerOutpostStructure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(PillagerOutpostStructure.class)
public interface PillagerOutpostStructureAccessor {
    @Accessor("PILLAGE_OUTPOST_ENEMIES")
    static List<MobSpawnInfo.Spawners> rs_getPILLAGE_OUTPOST_ENEMIES() {
        throw new UnsupportedOperationException();
    }
}
