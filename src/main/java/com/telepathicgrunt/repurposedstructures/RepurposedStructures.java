package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.biomeinjection.BiomeInjection;
import com.telepathicgrunt.repurposedstructures.biomeinjection.Dungeons;
import com.telepathicgrunt.repurposedstructures.biomeinjection.Wells;
import com.telepathicgrunt.repurposedstructures.configs.RSAllConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSAllowDisallowOmegaConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSMapTradeOmegaConfig;
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
    public static final RSMapTradeOmegaConfig omegaMapTradeConfig = OmegaConfig.register(RSMapTradeOmegaConfig.class);
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
        MobMapTrades.setupMapTrades();
        MobMapTrades.addMapTrades();
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(RepurposedStructures.mobSpawnerManager);
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(RepurposedStructures.structurePieceCountsManager);

        // Controls the dimension blacklisting
        // Must run after fapi to undo its changes
        ResourceLocation runAfterFabricAPIPhase = new ResourceLocation(RepurposedStructures.MODID, "run_after_fabric_api");
        ServerWorldEvents.LOAD.addPhaseOrdering(Event.DEFAULT_PHASE, runAfterFabricAPIPhase);
        initialized = true;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // BIOME MODIFICATION API //
    public static void setupBiomeModifications() {
        Dungeons.addDungeons();
        Wells.addWells();
    }
}
