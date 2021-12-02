package com.telepathicgrunt.repurposedstructures.misc;

import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.configurations.StrongholdConfiguration;

import java.util.Optional;

public final class NoiseSettingsDeepCopier {
    private NoiseSettingsDeepCopier() {}

    public static StructureSettings deepCopyDimensionStructuresSettings(StructureSettings settings) {
        // Grab old copy of stronghold spacing settings
        StrongholdConfiguration oldStrongholdSettings = settings.stronghold();

        // Make a deep copy and wrap it in an optional as DimensionStructuresSettings requires an optional
        Optional<StrongholdConfiguration> newStrongholdSettings = oldStrongholdSettings == null ?
                Optional.empty() :
                Optional.of(new StrongholdConfiguration(
                        oldStrongholdSettings.distance(),
                        oldStrongholdSettings.spread(),
                        oldStrongholdSettings.count()));

        // Create new deep copied DimensionStructuresSettings
        // We do not need to create a new structure spacing map instance here as our patch into
        // DimensionStructuresSettings will already create the new map instance for us.
        return new StructureSettings(newStrongholdSettings, settings.structureConfig());
    }
}
