package com.telepathicgrunt.repurposedstructures.misc;

import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSpreadSettings;

import java.util.Optional;

public class NoiseSettingsDeepCopier {

    public static DimensionStructuresSettings deepCopyDimensionStructuresSettings(DimensionStructuresSettings settings)
    {
        // Grab old copy of stronghold spacing settings
        StructureSpreadSettings oldStrongholdSettings = settings.stronghold();

        // Make a deep copy and wrap it in an optional as DimensionStructuresSettings requires an optional
        Optional<StructureSpreadSettings> newStrongholdSettings = oldStrongholdSettings == null ?
                Optional.empty() :
                Optional.of(new StructureSpreadSettings(
                        oldStrongholdSettings.distance(),
                        oldStrongholdSettings.spread(),
                        oldStrongholdSettings.count()));

        // Create new deep copied DimensionStructuresSettings
        // We do not need to create a new structure spacing map instance here as our patch into
        // DimensionStructuresSettings will already create the new map instance for us.
        return new DimensionStructuresSettings(newStrongholdSettings, settings.structureConfig());
    }
}
