package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.biomeinjection.BiomeInjection;
import com.telepathicgrunt.repurposedstructures.biomeinjection.Dungeons;
import com.telepathicgrunt.repurposedstructures.biomeinjection.Wells;
import com.telepathicgrunt.repurposedstructures.configs.RSAllConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSAllowDisallowOmegaConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSNaturalMobSpawningOmegaConfig;
import com.telepathicgrunt.repurposedstructures.misc.BiomeDimensionAllowDisallow;
import com.telepathicgrunt.repurposedstructures.misc.MobMapTrades;
import com.telepathicgrunt.repurposedstructures.misc.MobSpawnerManager;
import com.telepathicgrunt.repurposedstructures.misc.MobSpawningOverTime;
import com.telepathicgrunt.repurposedstructures.misc.PoolAdditionMerger;
import com.telepathicgrunt.repurposedstructures.misc.StructurePieceCountsManager;
import com.telepathicgrunt.repurposedstructures.mixin.structures.StructureSettingsAccessor;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSPlacements;
import com.telepathicgrunt.repurposedstructures.modinit.RSPredicates;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import draylar.omegaconfig.OmegaConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class RepurposedStructures implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "repurposed_structures";
    public static MobSpawnerManager mobSpawnerManager = new MobSpawnerManager();
    public static StructurePieceCountsManager structurePieceCountsManager = new StructurePieceCountsManager();

	public static RSAllConfig RSAllConfig = null;
    public static final RSAllowDisallowOmegaConfig omegaBiomeDimConfig = OmegaConfig.register(RSAllowDisallowOmegaConfig.class);
    public static final RSNaturalMobSpawningOmegaConfig omegaMobSpawnConfig = OmegaConfig.register(RSNaturalMobSpawningOmegaConfig.class);
    public static boolean initialized = false;


    @Override
    public void onInitialize() {
        AutoConfig.register(RSAllConfig.class, JanksonConfigSerializer::new);
        RSAllConfig = AutoConfig.getConfigHolder(RSAllConfig.class).getConfig();

        RSPlacements.registerPlacements();
        RSFeatures.registerFeatures();
        RSProcessors.registerProcessors();
        RSPredicates.registerPredicates();
        RSStructures.registerStructures();
        RSStructureTagMap.setupTags();
        RSConfiguredFeatures.registerConfiguredFeatures();
        RSConfiguredFeatures.registerPlacedFeatures();
        RSConfiguredStructures.registerConfiguredStructures();

        BiomeDimensionAllowDisallow.setupAllowDisallowMaps();
        BiomeInjection.addStructureToBiomes();
        MobSpawningOverTime.setupMobSpawningMaps();
        setupBiomeModifications();
        PoolAdditionMerger.mergeAdditionPools();
        MobMapTrades.addMapTrades();
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(RepurposedStructures.mobSpawnerManager);
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(RepurposedStructures.structurePieceCountsManager);

        // Controls the dimension blacklisting
        // Must run after fapi to undo its changes
        ResourceLocation runAfterFabricAPIPhase = new ResourceLocation(RepurposedStructures.MODID, "run_after_fabric_api");
        ServerWorldEvents.LOAD.addPhaseOrdering(Event.DEFAULT_PHASE, runAfterFabricAPIPhase);
        ServerWorldEvents.LOAD.register(runAfterFabricAPIPhase, (MinecraftServer minecraftServer, ServerLevel serverLevel) ->
                RepurposedStructures.runDimensionAllowingDisallowing(serverLevel));

        initialized = true;
    }

    public static void runDimensionAllowingDisallowing(ServerLevel serverLevel) {
        // We will need this a lot lol
        StructureSettings worldStructureSettings = serverLevel.getChunkSource().getGenerator().getSettings();
        Registry<Biome> biomeRegistry = serverLevel.registryAccess().registryOrThrow(Registry.BIOME_REGISTRY);
        Set<Biome> possibleBiomes = serverLevel.getChunkSource().getGenerator().getBiomeSource().possibleBiomes();

        //////////// DIMENSION BASED STRUCTURE SPAWNING ////////////

        //add our structure spacing to all chunkgenerators including modded one and datapack ones.
        // Need temp map as some mods use custom chunk generators with immutable maps in themselves.
        Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(worldStructureSettings.structureConfig());

        // make absolutely sure superflat dimension cannot spawn RS structures
        if (serverLevel.getChunkSource().getGenerator() instanceof FlatLevelSource && serverLevel.dimension().equals(Level.OVERWORLD)) {
            tempMap.keySet().removeAll(RSStructures.RS_STRUCTURES.keySet());
        }
        // Not superflat overworld. Do normal behavior now
        else {
            for(Map.Entry<StructureFeature<?>, StructureFeatureConfiguration> structureFeatureEntry : RSStructures.RS_STRUCTURES.entrySet()) {
                boolean isWorldBlacklisted = GeneralUtils.isBlacklistedForWorld(serverLevel, Registry.STRUCTURE_FEATURE.getKey(structureFeatureEntry.getKey()));

                // Remove structure spacing config if biome source cannot spawn biome.
                // Should help optimize the game to skip checking these structures as MC doesn't seem to do it by default? Weird.
                var validBiomesForStructure = serverLevel.getChunkSource().getGenerator().getSettings()
                        .structures(structureFeatureEntry.getKey()).values().stream().map(biomeRegistry::get).
                        collect(Collectors.toSet());

                if (isWorldBlacklisted || validBiomesForStructure.stream().noneMatch(possibleBiomes::contains)) {
                    // make absolutely sure dimension cannot spawn the RS structure
                    tempMap.remove(structureFeatureEntry.getKey());
                }
                else {
                    // make absolutely sure dimension can spawn the RS structure
                    tempMap.putIfAbsent(structureFeatureEntry.getKey(), structureFeatureEntry.getValue());
                }
            }
        }
        ((StructureSettingsAccessor) worldStructureSettings).repurposedstructures_setStructureConfig(tempMap);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // BIOME MODIFICATION API //
    public static void setupBiomeModifications() {
        Dungeons.addDungeons();
        Wells.addWells();
    }
}
