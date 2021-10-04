package com.telepathicgrunt.repurposedstructures.misc;

import com.telepathicgrunt.repurposedstructures.mixin.world.BiomeProviderInvoker;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.CheckerboardBiomeProvider;

public class BiomeSourceChecks {
    public static boolean hexlandsiiIsOn = false;

    public static boolean isHexlandBiomeSource(BiomeProvider biomeSource){
        if(hexlandsiiIsOn){
            ResourceLocation biomeSourceRL = Registry.BIOME_SOURCE.getKey(((BiomeProviderInvoker)biomeSource).repurposedstructures_callCodec());
            return biomeSourceRL.toString().equals("hexlands:hexlands");
        }
        return false;
    }

    public static boolean isCheckeredBiomeSource(BiomeProvider biomeSource){
        return biomeSource instanceof CheckerboardBiomeProvider;
    }
}
