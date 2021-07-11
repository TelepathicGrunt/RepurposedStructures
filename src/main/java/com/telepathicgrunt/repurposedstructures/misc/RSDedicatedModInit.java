package com.telepathicgrunt.repurposedstructures.misc;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.fabricmc.api.DedicatedServerModInitializer;

public class RSDedicatedModInit implements DedicatedServerModInitializer {

    @Override
    public void onInitializeServer() {
        RepurposedStructures.allowStructureSpawningPerDimension();
    }
}
