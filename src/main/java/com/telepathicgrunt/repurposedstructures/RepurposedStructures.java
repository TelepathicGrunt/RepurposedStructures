package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.biomeinjection.Dungeons;
import com.telepathicgrunt.repurposedstructures.biomeinjection.Wells;
import com.telepathicgrunt.repurposedstructures.configs.RSAllConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSAllowDisallowOmegaConfig;
import com.telepathicgrunt.repurposedstructures.misc.BiomeDimensionAllowDisallow;
import com.telepathicgrunt.repurposedstructures.misc.lootmanager.EndRemasteredDedicatedLoot;
import com.telepathicgrunt.repurposedstructures.misc.maptrades.StructureMapManager;
import com.telepathicgrunt.repurposedstructures.misc.maptrades.StructureMapTradesEvents;
import com.telepathicgrunt.repurposedstructures.misc.mobspawners.MobSpawnerManager;
import com.telepathicgrunt.repurposedstructures.misc.pooladditions.PoolAdditionMerger;
import com.telepathicgrunt.repurposedstructures.misc.structurepiececounter.StructurePieceCountsManager;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSPlacements;
import com.telepathicgrunt.repurposedstructures.modinit.RSPredicates;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSTags;
import draylar.omegaconfig.OmegaConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.base.api.event.Event;
import org.quiltmc.qsl.lifecycle.api.event.ServerWorldLoadEvents;
import org.quiltmc.qsl.resource.loader.api.ResourceLoader;


public class RepurposedStructures implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "repurposed_structures";
    public static MobSpawnerManager mobSpawnerManager = new MobSpawnerManager();
    public static StructureMapManager structureMapManager = new StructureMapManager();
    public static StructurePieceCountsManager structurePieceCountsManager = new StructurePieceCountsManager();

	public static RSAllConfig RSAllConfig = null;
    public static final RSAllowDisallowOmegaConfig omegaBiomeDimConfig = OmegaConfig.register(RSAllowDisallowOmegaConfig.class);
    public static boolean initialized = false;


    @Override
    public void onInitialize(ModContainer mod) {
        AutoConfig.register(RSAllConfig.class, JanksonConfigSerializer::new);
        RSAllConfig = AutoConfig.getConfigHolder(RSAllConfig.class).getConfig();

        RSTags.initTags();
        RSPlacements.registerPlacements();
        RSFeatures.registerFeatures();
        RSProcessors.registerProcessors();
        RSPredicates.registerPredicates();
        RSStructures.registerStructures();
        RSConfiguredFeatures.registerConfiguredFeatures();
        RSConfiguredFeatures.registerPlacedFeatures();

        BiomeDimensionAllowDisallow.setupAllowDisallowMaps();
        setupBiomeModifications();
        PoolAdditionMerger.mergeAdditionPools();
        StructureMapTradesEvents.setupTradeEvent();
        ResourceLoader.get(PackType.SERVER_DATA).registerReloader(RepurposedStructures.mobSpawnerManager);
        ResourceLoader.get(PackType.SERVER_DATA).registerReloader(RepurposedStructures.structureMapManager);
        ResourceLoader.get(PackType.SERVER_DATA).registerReloader(RepurposedStructures.structurePieceCountsManager);

        // Controls the dimension blacklisting
        // Must run after fapi to undo its changes
        ResourceLocation runAfterFabricAPIPhase = new ResourceLocation(RepurposedStructures.MODID, "run_after_fabric_api");
        ServerWorldLoadEvents.LOAD.addPhaseOrdering(Event.DEFAULT_PHASE, runAfterFabricAPIPhase);
        initialized = true;

        //For mod compat by checking if other mod is on
        EndRemasteredDedicatedLoot.isEndRemasteredOn = QuiltLoader.isModLoaded("endrem");
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // BIOME MODIFICATION API //
    public static void setupBiomeModifications() {
        Dungeons.addDungeons();
        Wells.addWells();
    }
}
