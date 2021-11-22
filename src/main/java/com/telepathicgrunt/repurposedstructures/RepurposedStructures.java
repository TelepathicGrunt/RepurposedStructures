package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.biomeinjection.Bastions;
import com.telepathicgrunt.repurposedstructures.biomeinjection.Cities;
import com.telepathicgrunt.repurposedstructures.biomeinjection.Dungeons;
import com.telepathicgrunt.repurposedstructures.biomeinjection.Fortresses;
import com.telepathicgrunt.repurposedstructures.biomeinjection.Igloos;
import com.telepathicgrunt.repurposedstructures.biomeinjection.Mansions;
import com.telepathicgrunt.repurposedstructures.biomeinjection.Mineshafts;
import com.telepathicgrunt.repurposedstructures.biomeinjection.Outposts;
import com.telepathicgrunt.repurposedstructures.biomeinjection.Pyramids;
import com.telepathicgrunt.repurposedstructures.biomeinjection.RuinedPortals;
import com.telepathicgrunt.repurposedstructures.biomeinjection.Ruins;
import com.telepathicgrunt.repurposedstructures.biomeinjection.Shipwrecks;
import com.telepathicgrunt.repurposedstructures.biomeinjection.Strongholds;
import com.telepathicgrunt.repurposedstructures.biomeinjection.Temples;
import com.telepathicgrunt.repurposedstructures.biomeinjection.Villages;
import com.telepathicgrunt.repurposedstructures.biomeinjection.Wells;
import com.telepathicgrunt.repurposedstructures.biomeinjection.WitchHuts;
import com.telepathicgrunt.repurposedstructures.configs.RSAllConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSAllowDisallowOmegaConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSNaturalMobSpawningOmegaConfig;
import com.telepathicgrunt.repurposedstructures.misc.BiomeDimensionAllowDisallow;
import com.telepathicgrunt.repurposedstructures.misc.MobMapTrades;
import com.telepathicgrunt.repurposedstructures.misc.MobSpawnerManager;
import com.telepathicgrunt.repurposedstructures.misc.MobSpawningOverTime;
import com.telepathicgrunt.repurposedstructures.misc.PoolAdditionMerger;
import com.telepathicgrunt.repurposedstructures.mixin.structures.StructuresConfigAccessor;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSPlacements;
import com.telepathicgrunt.repurposedstructures.modinit.RSPredicates;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.StructurePiecesBehavior;
import draylar.omegaconfig.OmegaConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;


public class RepurposedStructures implements ModInitializer, DedicatedServerModInitializer, ClientModInitializer {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "repurposed_structures";
    public static MobSpawnerManager mobSpawnerManager = new MobSpawnerManager();

	public static RSAllConfig RSAllConfig = null;
    public static final RSAllowDisallowOmegaConfig omegaBiomeDimConfig = OmegaConfig.register(RSAllowDisallowOmegaConfig.class);
    public static final RSNaturalMobSpawningOmegaConfig omegaMobSpawnConfig = OmegaConfig.register(RSNaturalMobSpawningOmegaConfig.class);
    public static boolean isBetterMineshaftsOn = false;
    public static boolean isBetterDungeonsOn = false;
    public static boolean isCharmOn = false;
    public static boolean initialized = false;


    @Override
    public void onInitialize() {
        AutoConfig.register(RSAllConfig.class, JanksonConfigSerializer::new);
        RSAllConfig = AutoConfig.getConfigHolder(RSAllConfig.class).getConfig();

        if (FabricLoader.getInstance().isModLoaded("bettermineshafts")) {
            isBetterMineshaftsOn = true;
        }
        if (FabricLoader.getInstance().isModLoaded("betterdungeons")) {
            isBetterDungeonsOn = true;
        }
        if (FabricLoader.getInstance().isModLoaded("charm")) {
            isCharmOn = true;
        }

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
        MobSpawningOverTime.setupMobSpawningMaps();
        setupBiomeModifications();
        allowStructureSpawningPerDimension();
        PoolAdditionMerger.mergeAdditionPools();
        StructurePiecesBehavior.init();
        MobMapTrades.addMapTrades();
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(RepurposedStructures.mobSpawnerManager);

        initialized = true;
    }

    // These are for when we want to add compat with other mods but uses their configs/codes that isn't read in the regular onInitialize method
    @Override
    public void onInitializeServer() {
        //StructurePiecesBehavior.addDelayedRequiredPieces();
    }

    @Override
    public void onInitializeClient() {
        //StructurePiecesBehavior.addDelayedRequiredPieces();
    }

    public static void allowStructureSpawningPerDimension() {
        // This is for making sure our ServerWorldEvents.LOAD event always fires after Fabric API's so our changes don't get overwritten
        ResourceLocation runAfterFabricAPIPhase = new ResourceLocation(RepurposedStructures.MODID, "run_after_fabric_api");
        ServerWorldEvents.LOAD.addPhaseOrdering(Event.DEFAULT_PHASE, runAfterFabricAPIPhase);

        // Controls the dimension blacklisting
        ServerWorldEvents.LOAD.register(runAfterFabricAPIPhase, (MinecraftServer minecraftServer, ServerLevel serverWorld) -> {
            //add our structure spacing to all chunkgenerators including modded one and datapack ones.
            // Need temp map as some mods use custom chunk generators with immutable maps in themselves.
            Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(serverWorld.getChunkSource().getGenerator().getSettings().structureConfig());

            // make absolutely sure superflat dimension cannot spawn RS structures
            if (serverWorld.getChunkSource().getGenerator() instanceof FlatLevelSource && serverWorld.dimension().equals(Level.OVERWORLD)) {
                tempMap.keySet().removeAll(RSStructures.RS_STRUCTURES.keySet());
            }
            // Not superflat overworld. Do normal behavior now
            else{
                for(Map.Entry<StructureFeature<?>, StructureFeatureConfiguration> structureFeatureEntry : RSStructures.RS_STRUCTURES.entrySet()){
                    boolean isWorldBlacklisted = GeneralUtils.isBlacklistedForWorld(serverWorld, Registry.STRUCTURE_FEATURE.getKey(structureFeatureEntry.getKey()));
                    if (isWorldBlacklisted){
                        // make absolutely sure dimension cannot spawn the RS structure
                        tempMap.remove(structureFeatureEntry.getKey());
                    }
                    else {
                        // make absolutely sure dimension can spawn the RS structure
                        tempMap.putIfAbsent(structureFeatureEntry.getKey(), structureFeatureEntry.getValue());
                    }
                }
            }
            ((StructuresConfigAccessor) serverWorld.getChunkSource().getGenerator().getSettings()).repurposedstructures_setStructureConfig(tempMap);
        });
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // BIOME MODIFICATION API //
    public static void setupBiomeModifications() {
        Mineshafts.addMineshafts();
        Dungeons.addDungeons();
        Wells.addWells();
        Strongholds.addStrongholds();
        Outposts.addOutposts();
        Shipwrecks.addShipwrecks();
        Fortresses.addJungleFortress();
        Temples.addTemples();
        Pyramids.addPyramids();
        Igloos.addIgloos();
        Villages.addVillages();
        RuinedPortals.addRuinedPortals();
        Ruins.addRuins();
        Cities.addCities();
        Mansions.addMansions();
        WitchHuts.addWitchHuts();
        Bastions.addBastions();
    }
}
