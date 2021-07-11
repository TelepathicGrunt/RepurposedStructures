package com.telepathicgrunt.repurposedstructures.misc;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.DedicatedServerModInitializer;

public class RSClientModInit implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        RepurposedStructures.allowStructureSpawningPerDimension();
    }
}
