package com.telepathicgrunt.repurposedstructures.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public class DataGenerators {

    public static void gatherData(GatherDataEvent evt) {
        DataGenerator generator = evt.getGenerator();
        
        if (evt.includeServer()) {
            generator.addProvider(new LootTableGenerators(generator));
        }
    }
}
