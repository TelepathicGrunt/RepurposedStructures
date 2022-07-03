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
import net.minecraft.server.packs.PackType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.lifecycle.api.event.ServerLifecycleEvents;
import org.quiltmc.qsl.resource.loader.api.ResourceLoader;


public class RepurposedStructures implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "repurposed_structures";
    public static MobSpawnerManager mobSpawnerManager = new MobSpawnerManager();
    public static StructureMapManager structureMapManager = new StructureMapManager();
    public static StructurePieceCountsManager structurePieceCountsManager = new StructurePieceCountsManager();

    @Override
    public void onInitialize(ModContainer modContainer) {
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
        ResourceLoader.get(PackType.SERVER_DATA).registerReloader(RepurposedStructures.mobSpawnerManager);
        ResourceLoader.get(PackType.SERVER_DATA).registerReloader(RepurposedStructures.structureMapManager);
        ResourceLoader.get(PackType.SERVER_DATA).registerReloader(RepurposedStructures.structurePieceCountsManager);

        //For mod compat by checking if other mod is on
        EndRemasteredDedicatedLoot.isEndRemasteredOn = QuiltLoader.isModLoaded("endrem");

        ServerLifecycleEvents.STARTING.register((minecraftServer) -> {
            if (QuiltLoader.isDevelopmentEnvironment()) {
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
