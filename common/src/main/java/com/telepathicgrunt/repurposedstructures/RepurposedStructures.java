package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.events.RegisterVillagerTradesEvent;
import com.telepathicgrunt.repurposedstructures.events.RegisterWanderingTradesEvent;
import com.telepathicgrunt.repurposedstructures.events.lifecycle.RegisterReloadListenerEvent;
import com.telepathicgrunt.repurposedstructures.events.lifecycle.ServerGoingToStartEvent;
import com.telepathicgrunt.repurposedstructures.events.lifecycle.ServerGoingToStopEvent;
import com.telepathicgrunt.repurposedstructures.events.lifecycle.SetupEvent;
import com.telepathicgrunt.repurposedstructures.misc.lootmanager.EndRemasteredDedicatedLoot;
import com.telepathicgrunt.repurposedstructures.misc.lootmanager.StructureModdedLootImporter;
import com.telepathicgrunt.repurposedstructures.misc.maptrades.StructureMapManager;
import com.telepathicgrunt.repurposedstructures.misc.maptrades.StructureMapTradesEvents;
import com.telepathicgrunt.repurposedstructures.misc.mobspawners.MobSpawnerManager;
import com.telepathicgrunt.repurposedstructures.misc.pooladditions.PoolAdditionMerger;
import com.telepathicgrunt.repurposedstructures.modinit.RSConditionsRegistry;
import com.telepathicgrunt.repurposedstructures.misc.structurepiececounter.StructurePieceCountsManager;
import com.telepathicgrunt.repurposedstructures.modinit.RSFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSPlacements;
import com.telepathicgrunt.repurposedstructures.modinit.RSPredicates;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructurePieces;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructurePlacementType;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSTags;
import com.telepathicgrunt.repurposedstructures.utils.AsyncLocator;
import com.telepathicgrunt.repurposedstructures.utils.PlatformHooks;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class RepurposedStructures {
    public static final String MODID = "repurposed_structures";
    public static final Logger LOGGER = LogManager.getLogger();

    public static void init() {
        RSTags.initTags();

        EndRemasteredDedicatedLoot.isEndRemasteredOn = PlatformHooks.isModLoaded("endrem");
        StructureModdedLootImporter.createMap();

        RSFeatures.FEATURES.init();
        RSPredicates.RULE_TEST.init();
        RSPredicates.POS_RULE_TEST.init();
        RSStructures.STRUCTURE_TYPE.init();
        RSPlacements.PLACEMENT_MODIFIER.init();
        RSProcessors.STRUCTURE_PROCESSOR.init();
        RSStructurePieces.STRUCTURE_PIECE.init();
        RSStructurePieces.STRUCTURE_POOL_ELEMENT.init();
        RSStructurePlacementType.STRUCTURE_PLACEMENT_TYPE.init();
        RSConditionsRegistry.RS_JSON_CONDITIONS_REGISTRY.init();

        SetupEvent.EVENT.addListener(RepurposedStructures::setup);
        RegisterReloadListenerEvent.EVENT.addListener(RepurposedStructures::registerDatapackListener);
        ServerGoingToStartEvent.EVENT.addListener(RepurposedStructures::serverAboutToStart);
        ServerGoingToStopEvent.EVENT.addListener(RepurposedStructures::onServerStopping);
        RegisterVillagerTradesEvent.EVENT.addListener(RepurposedStructures::onAddVillagerTrades);
        RegisterWanderingTradesEvent.EVENT.addListener(RepurposedStructures::onWanderingTrades);
    }

    private static void setup(final SetupEvent event) {
    }

    private static void serverAboutToStart(final ServerGoingToStartEvent event) {
        PoolAdditionMerger.mergeAdditionPools(event);

        if (PlatformHooks.isDevEnvironment()) {
            StructureModdedLootImporter.checkLoottables(event.getServer());
            EndRemasteredDedicatedLoot.checkLoottables(event.getServer());
        }

        AsyncLocator.handleServerAboutToStartEvent();
    }

    private static void onServerStopping(final ServerGoingToStopEvent event) {
        AsyncLocator.handleServerStoppingEvent();
    }

    private static void onAddVillagerTrades(final RegisterVillagerTradesEvent event) {
        StructureMapTradesEvents.addVillagerTrades(event);
    }

    private static void onWanderingTrades(final RegisterWanderingTradesEvent event) {
        StructureMapTradesEvents.addWanderingTrades(event);
    }

    public static void registerDatapackListener(final RegisterReloadListenerEvent event) {
        event.register(new ResourceLocation(RepurposedStructures.MODID, "rs_spawners"), MobSpawnerManager.MOB_SPAWNER_MANAGER);
        event.register(new ResourceLocation(RepurposedStructures.MODID, "structure_map_trades"), StructureMapManager.STRUCTURE_MAP_MANAGER);
        event.register(new ResourceLocation(RepurposedStructures.MODID, "rs_pieces_spawn_counts"), StructurePieceCountsManager.STRUCTURE_PIECE_COUNTS_MANAGER);
    }
}
