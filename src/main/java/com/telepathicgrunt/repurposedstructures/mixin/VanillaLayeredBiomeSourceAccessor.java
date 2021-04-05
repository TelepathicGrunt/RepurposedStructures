package com.telepathicgrunt.repurposedstructures.mixin;

import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.VanillaLayeredBiomeSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(VanillaLayeredBiomeSource.class)
public interface VanillaLayeredBiomeSourceAccessor {
    @Accessor("BIOMES")
    static List<RegistryKey<Biome>> rs_getBIOMES() {
        throw new UnsupportedOperationException();
    }
}
