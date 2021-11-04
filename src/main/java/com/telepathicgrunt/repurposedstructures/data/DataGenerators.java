package com.telepathicgrunt.repurposedstructures.data;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public final class DataGenerators {
    // Set to true when doing RS genData.
    public static boolean datagenLootTableModeOn = false;
    static {
        // Makes sure that RS's datagen mixins and datagen methods does not run outside RS's dev environment ever.
        if(RepurposedStructures.class.getPackage().getImplementationVersion() != null)
            datagenLootTableModeOn = false;
    }

    private DataGenerators() {}

    public static void gatherData(GatherDataEvent evt) {
        DataGenerator generator = evt.getGenerator();
        
        if (evt.includeServer()) {
            generator.addProvider(new LootTableGenerators(generator));
        }
    }
}
