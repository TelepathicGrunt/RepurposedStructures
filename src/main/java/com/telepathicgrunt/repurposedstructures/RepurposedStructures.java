package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.biomeinjection.Dungeons;
import com.telepathicgrunt.repurposedstructures.biomeinjection.Wells;
import com.telepathicgrunt.repurposedstructures.configs.RSBiomeDimConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSDungeonsConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSModdedLootConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSWellsConfig;
import com.telepathicgrunt.repurposedstructures.configs.omegaconfig.OmegaConfig;
import com.telepathicgrunt.repurposedstructures.misc.BiomeDimensionAllowDisallow;
import com.telepathicgrunt.repurposedstructures.misc.lootmanager.EndRemasteredDedicatedLoot;
import com.telepathicgrunt.repurposedstructures.misc.maptrades.StructureMapManager;
import com.telepathicgrunt.repurposedstructures.misc.structurepiececounter.JSONConditionsRegistry;
import com.telepathicgrunt.repurposedstructures.misc.maptrades.StructureMapTradesEvents;
import com.telepathicgrunt.repurposedstructures.misc.mobspawner.MobSpawnerManager;
import com.telepathicgrunt.repurposedstructures.misc.structurepiececounter.PoolAdditionMerger;
import com.telepathicgrunt.repurposedstructures.misc.lootmanager.StructureModdedLootImporter;
import com.telepathicgrunt.repurposedstructures.misc.structurepiececounter.StructurePieceCountsManager;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSGlobalLootModifier;
import com.telepathicgrunt.repurposedstructures.modinit.RSPlacements;
import com.telepathicgrunt.repurposedstructures.modinit.RSPredicates;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSTags;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.loading.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(RepurposedStructures.MODID)
public class RepurposedStructures {
    public static final String MODID = "repurposed_structures";
    public static final Logger LOGGER = LogManager.getLogger();

    public static final RSBiomeDimConfig omegaBiomeDimConfig = OmegaConfig.register(RSBiomeDimConfig.class);
    public static MobSpawnerManager mobSpawnerManager = new MobSpawnerManager();
    public static StructureMapManager structureMapManager = new StructureMapManager();
    public static StructurePieceCountsManager structurePieceCountsManager = new StructurePieceCountsManager();

    public RepurposedStructures() {
        // Classload and create custom registry. Other mods should add to this custom registry in FMLCommonSetupEvent.
        JSONConditionsRegistry.registerTestJSONCondition();

        // Setup configs
        FileUtils.getOrCreateDirectory(FMLPaths.CONFIGDIR.get().resolve("repurposed_structures-forge"), "repurposed_structures-forge");

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RSModdedLootConfig.GENERAL_SPEC, "repurposed_structures-forge/modded_loot.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RSDungeonsConfig.GENERAL_SPEC, "repurposed_structures-forge/dungeons.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RSWellsConfig.GENERAL_SPEC, "repurposed_structures-forge/wells.toml");

        // Register the setup method for modloading
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        forgeBus.addListener(this::biomeModification);
        forgeBus.addListener(this::serverStarted);
        forgeBus.addListener(this::registerDatapackListener);
        forgeBus.addListener(StructureMapTradesEvents::onVillagerTradesEvent);
        forgeBus.addListener(StructureMapTradesEvents::onWandererTradesEvent);
        forgeBus.addListener(PoolAdditionMerger::mergeAdditionPools);
        // GeneralUtils.registerStructureDebugging(RSStructures.STONEBRICK_STRONGHOLD);

        modEventBus.addListener(this::setup);
        RSFeatures.FEATURES.register(modEventBus);
        RSStructures.STRUCTURE_FEATURES.register(modEventBus);
        RSGlobalLootModifier.GLM.register(modEventBus);

        //For mod compat by checking if other mod is on
        EndRemasteredDedicatedLoot.isEndRemasteredOn = ModList.get().isLoaded("endrem");
    }

    public void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            RSTags.initTags();
            RSProcessors.registerProcessors();
            RSPredicates.registerPredicates();
            RSPlacements.registerPlacements();
            RSConfiguredFeatures.registerConfiguredFeatures();
            RSConfiguredFeatures.registerPlacedFeatures();
            RSStructures.setupStructures();
            RSGlobalLootModifier.registerLootData();
            BiomeDimensionAllowDisallow.setupAllowDisallowMaps();
        });
    }

    public void registerDatapackListener(final AddReloadListenerEvent event) {
        //loads the RS specific json files for mob spawner chances
        event.addListener(RepurposedStructures.mobSpawnerManager);
        event.addListener(RepurposedStructures.structureMapManager);
        event.addListener(RepurposedStructures.structurePieceCountsManager);
    }

    public void biomeModification(final BiomeLoadingEvent event) {
        //Add our structures and features
        RepurposedStructures.addFeaturesAndStructuresToBiomes(event);
    }

    public void serverStarted(final ServerStartedEvent event) {
        GeneralUtils.clearCache();
        StructureModdedLootImporter.checkLoottables(event.getServer());
        EndRemasteredDedicatedLoot.checkLoottables(event.getServer());
    }

    /*
     * Here, we will use this to add our features to all biomes.
     * Structures are not stored in biomes anymore so we cannot use BiomeLoadingEvent for them.
     */
    public static void addFeaturesAndStructuresToBiomes(BiomeLoadingEvent event) {
        Dungeons.addDungeons(event);
        Wells.addWells(event);
    }
}
