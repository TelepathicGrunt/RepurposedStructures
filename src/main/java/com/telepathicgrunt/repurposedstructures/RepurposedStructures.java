package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.configs.RSModdedLootConfig;
import com.telepathicgrunt.repurposedstructures.misc.lootmanager.EndRemasteredDedicatedLoot;
import com.telepathicgrunt.repurposedstructures.misc.lootmanager.StructureModdedLootImporter;
import com.telepathicgrunt.repurposedstructures.misc.maptrades.StructureMapManager;
import com.telepathicgrunt.repurposedstructures.misc.maptrades.StructureMapTradesEvents;
import com.telepathicgrunt.repurposedstructures.misc.mobspawners.MobSpawnerManager;
import com.telepathicgrunt.repurposedstructures.misc.pooladditions.PoolAdditionMerger;
import com.telepathicgrunt.repurposedstructures.misc.structurepiececounter.StructurePieceCountsManager;
import com.telepathicgrunt.repurposedstructures.modinit.RSBiomeModifiers;
import com.telepathicgrunt.repurposedstructures.modinit.RSFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSGlobalLootModifier;
import com.telepathicgrunt.repurposedstructures.modinit.RSPlacements;
import com.telepathicgrunt.repurposedstructures.modinit.RSPredicates;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructurePieces;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSTags;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(RepurposedStructures.MODID)
public class RepurposedStructures {
    public static final String MODID = "repurposed_structures";
    public static final Logger LOGGER = LogManager.getLogger();

    public static MobSpawnerManager mobSpawnerManager = new MobSpawnerManager();
    public static StructureMapManager structureMapManager = new StructureMapManager();
    public static StructurePieceCountsManager structurePieceCountsManager = new StructurePieceCountsManager();

    public RepurposedStructures() {
        RSTags.initTags();

        // Setup configs
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RSModdedLootConfig.GENERAL_SPEC, "repurposed_structures-forge/modded_loot.toml");

        // Register the setup method for modloading
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        forgeBus.addListener(this::serverStarted);
        forgeBus.addListener(this::registerDatapackListener);
        forgeBus.addListener(StructureMapTradesEvents::onVillagerTradesEvent);
        forgeBus.addListener(StructureMapTradesEvents::onWandererTradesEvent);
        forgeBus.addListener(PoolAdditionMerger::mergeAdditionPools);

        modEventBus.addListener(this::setup);
        RSFeatures.FEATURES.register(modEventBus);
        RSPredicates.RULE_TEST.register(modEventBus);
        RSGlobalLootModifier.GLM.register(modEventBus);
        RSPredicates.POS_RULE_TEST.register(modEventBus);
        RSStructures.STRUCTURE_TYPE.register(modEventBus);
        RSPlacements.PLACEMENT_MODIFIER.register(modEventBus);
        RSProcessors.STRUCTURE_PROCESSOR.register(modEventBus);
        RSStructurePieces.STRUCTURE_PIECE.register(modEventBus);
        RSStructurePieces.STRUCTURE_POOL_ELEMENT.register(modEventBus);
        RSBiomeModifiers.BIOME_MODIFIER_SERIALIZERS.register(modEventBus);

        //For mod compat by checking if other mod is on
        EndRemasteredDedicatedLoot.isEndRemasteredOn = ModList.get().isLoaded("endrem");
    }

    public void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            RSGlobalLootModifier.registerLootData();
        });
    }

    public void registerDatapackListener(final AddReloadListenerEvent event) {
        //loads the RS specific json files for mob spawner chances
        event.addListener(RepurposedStructures.mobSpawnerManager);
        event.addListener(RepurposedStructures.structureMapManager);
        event.addListener(RepurposedStructures.structurePieceCountsManager);
    }

    public void serverStarted(final ServerStartedEvent event) {
        StructureModdedLootImporter.checkLoottables(event.getServer());
        EndRemasteredDedicatedLoot.checkLoottables(event.getServer());
    }
}
