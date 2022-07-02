package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.configs.RSModdedLootConfig;
import com.telepathicgrunt.repurposedstructures.misc.lootmanager.EndRemasteredDedicatedLoot;
import com.telepathicgrunt.repurposedstructures.misc.lootmanager.StructureModdedLootImporter;
import com.telepathicgrunt.repurposedstructures.misc.maptrades.StructureMapManager;
import com.telepathicgrunt.repurposedstructures.misc.maptrades.StructureMapTradesEvents;
import com.telepathicgrunt.repurposedstructures.misc.mobspawners.MobSpawnerManager;
import com.telepathicgrunt.repurposedstructures.misc.pooladditions.PoolAdditionMerger;
import com.telepathicgrunt.repurposedstructures.misc.structurepiececounter.StructurePieceCountsManager;
import com.telepathicgrunt.repurposedstructures.modinit.RSFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSPlacements;
import com.telepathicgrunt.repurposedstructures.modinit.RSPredicates;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructurePlacementType;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSTags;
import com.telepathicgrunt.repurposedstructures.world.biomemodifiers.BiomeModifier;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class RepurposedStructures implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "repurposed_structures";
    public static MobSpawnerManager mobSpawnerManager = new MobSpawnerManager();
    public static StructureMapManager structureMapManager = new StructureMapManager();
    public static StructurePieceCountsManager structurePieceCountsManager = new StructurePieceCountsManager();
    public static boolean initialized = false;

    @Override
    public void onInitialize() {
        MidnightConfig.init(MODID, RSModdedLootConfig.class);

        RSTags.initTags();
        RSFeatures.registerFeatures();
        RSPlacements.registerPlacements();
        RSProcessors.registerProcessors();
        RSPredicates.registerPredicates();
        RSStructures.registerStructures();
        RSStructurePlacementType.registerStructurePlacementTypes();

        setupBiomeModifications();
        PoolAdditionMerger.mergeAdditionPools();
        StructureMapTradesEvents.setupTradeEvent();
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(RepurposedStructures.mobSpawnerManager);
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(RepurposedStructures.structureMapManager);
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(RepurposedStructures.structurePieceCountsManager);

        // Controls the dimension blacklisting
        // Must run after fapi to undo its changes
        ResourceLocation runAfterFabricAPIPhase = new ResourceLocation(RepurposedStructures.MODID, "run_after_fabric_api");
        ServerWorldEvents.LOAD.addPhaseOrdering(Event.DEFAULT_PHASE, runAfterFabricAPIPhase);
        initialized = true;

        //For mod compat by checking if other mod is on
        EndRemasteredDedicatedLoot.isEndRemasteredOn = FabricLoader.getInstance().isModLoaded("endrem");

        ServerLifecycleEvents.SERVER_STARTING.register((minecraftServer) -> {
            if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
                StructureModdedLootImporter.checkLoottables(minecraftServer);
                EndRemasteredDedicatedLoot.checkLoottables(minecraftServer);
            }
        });
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // BIOME MODIFICATION API //
    public static void setupBiomeModifications() {
        BiomeModifier.addFeatures();
    }
}
