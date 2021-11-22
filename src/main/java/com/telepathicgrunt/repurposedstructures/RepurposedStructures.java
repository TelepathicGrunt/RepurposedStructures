package com.telepathicgrunt.repurposedstructures;

import com.google.common.collect.ImmutableMap;
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
import com.telepathicgrunt.repurposedstructures.configs.*;
import com.telepathicgrunt.repurposedstructures.configs.omegaconfig.OmegaConfig;
import com.telepathicgrunt.repurposedstructures.data.DataGenerators;
import com.telepathicgrunt.repurposedstructures.misc.BiomeDimensionAllowDisallow;
import com.telepathicgrunt.repurposedstructures.misc.BiomeSourceChecks;
import com.telepathicgrunt.repurposedstructures.misc.EndRemasteredDedicatedLoot;
import com.telepathicgrunt.repurposedstructures.misc.MobMapTrades;
import com.telepathicgrunt.repurposedstructures.misc.MobSpawnerManager;
import com.telepathicgrunt.repurposedstructures.misc.MobSpawningOverTime;
import com.telepathicgrunt.repurposedstructures.misc.NoiseSettingsDeepCopier;
import com.telepathicgrunt.repurposedstructures.misc.PoolAdditionMerger;
import com.telepathicgrunt.repurposedstructures.mixin.world.ChunkGeneratorAccessor;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSGlobalLootModifier;
import com.telepathicgrunt.repurposedstructures.modinit.RSNumberProviders;
import com.telepathicgrunt.repurposedstructures.modinit.RSPlacements;
import com.telepathicgrunt.repurposedstructures.modinit.RSPredicates;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.utils.LogSpamFiltering;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.StructurePiecesBehavior;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
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
import org.apache.maven.artifact.versioning.ArtifactVersion;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;

import java.util.HashMap;
import java.util.Map;


@Mod(RepurposedStructures.MODID)
public class RepurposedStructures {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "repurposed_structures";

    public static final RSBiomeDimConfig RSOmegaBiomeDimConfig = OmegaConfig.register(RSBiomeDimConfig.class);
    public static final RSNaturalMobSpawningConfig RSNaturalMobSpawningConfig = OmegaConfig.register(RSNaturalMobSpawningConfig.class);
    public static MobSpawnerManager mobSpawnerManager = new MobSpawnerManager();

    public static boolean yungsBetterMineshaftIsOn = true;
    public static boolean yungsBetterDungeonsIsOn = true;
    public static boolean isCharmOn = false;
    public static boolean isCavesAndCliffsBackportOn = false;

    public RepurposedStructures() {

        // Setup configs
        FileUtils.getOrCreateDirectory(FMLPaths.CONFIGDIR.get().resolve("repurposed_structures-forge"), "repurposed_structures-forge");

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RSModdedLootConfig.GENERAL_SPEC, "repurposed_structures-forge/modded_loot.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RSDungeonsConfig.GENERAL_SPEC, "repurposed_structures-forge/dungeons.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RSMineshaftsConfig.GENERAL_SPEC, "repurposed_structures-forge/mineshafts.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RSStrongholdsConfig.GENERAL_SPEC, "repurposed_structures-forge/strongholds.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RSWellsConfig.GENERAL_SPEC, "repurposed_structures-forge/wells.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RSShipwrecksConfig.GENERAL_SPEC, "repurposed_structures-forge/shipwrecks.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RSOutpostsConfig.GENERAL_SPEC, "repurposed_structures-forge/outposts.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RSTemplesConfig.GENERAL_SPEC, "repurposed_structures-forge/temples.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RSVillagesConfig.GENERAL_SPEC, "repurposed_structures-forge/villages.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RSMansionsConfig.GENERAL_SPEC, "repurposed_structures-forge/mansions.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RSWitchHutsConfig.GENERAL_SPEC, "repurposed_structures-forge/witch_huts.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RSBastionsConfig.GENERAL_SPEC, "repurposed_structures-forge/bastions.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RSCitiesConfig.GENERAL_SPEC, "repurposed_structures-forge/cities.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RSFortressesConfig.GENERAL_SPEC, "repurposed_structures-forge/fortresses.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RSIgloosConfig.GENERAL_SPEC, "repurposed_structures-forge/igloos.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RSRuinedPortalsConfig.GENERAL_SPEC, "repurposed_structures-forge/ruined_portals.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RSRuinsConfig.GENERAL_SPEC, "repurposed_structures-forge/ruins.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RSPyramidsConfig.GENERAL_SPEC, "repurposed_structures-forge/pyramids.toml");

        // Register the setup method for modloading
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        forgeBus.addListener(this::biomeModification);
        forgeBus.addListener(this::registerDatapackListener);
        forgeBus.addListener(EventPriority.HIGHEST, this::deepCopyDimensionalSpacing);
        forgeBus.addListener(this::addDimensionalSpacing);
        forgeBus.addListener(MobMapTrades::onVillagerTradesEvent);
        forgeBus.addListener(MobMapTrades::onWandererTradesEvent);
        forgeBus.addListener(PoolAdditionMerger::mergeAdditionPools);
        //GeneralUtils.registerStructureDebugging(RSStructures.STONEBRICK_STRONGHOLD);

        if(DataGenerators.datagenLootTableModeOn) modEventBus.addListener(DataGenerators::gatherData);
        modEventBus.addListener(this::setup);
        RSFeatures.FEATURES.register(modEventBus);
        RSStructures.STRUCTURE_FEATURES.register(modEventBus);
        RSPlacements.DECORATORS.register(modEventBus);
        RSGlobalLootModifier.GLM.register(modEventBus);

        //For mod compat by checking if other mod is on
        yungsBetterMineshaftIsOn = ModList.get().isLoaded("bettermineshafts");
        yungsBetterDungeonsIsOn = ModList.get().isLoaded("betterdungeons");
        isCharmOn = ModList.get().isLoaded("charm");

        // CCB added mineshafts that replaces vanilla's. We do not want to remove the vanilla mineshafts ourselves.
        if(ModList.get().isLoaded("cavesandcliffs")) {
            ArtifactVersion modVersion = ModList.get().getModContainerById("cavesandcliffs").get().getModInfo().getVersion();
            if(modVersion.compareTo(new DefaultArtifactVersion("1.16.5-7.1.0")) > 0) {
                isCavesAndCliffsBackportOn = true;
            }
        }
        
        EndRemasteredDedicatedLoot.isEndRemasteredOn = ModList.get().isLoaded("endrem");
        BiomeSourceChecks.hexlandsiiIsOn = ModList.get().isLoaded("hexlands");

        // Silences logspam due to me changing my piece's namespace from minecraft to my modid.
        Logger rootLogger = LogManager.getRootLogger();
        if (rootLogger instanceof org.apache.logging.log4j.core.Logger) {
            ((org.apache.logging.log4j.core.Logger) rootLogger).addFilter(new LogSpamFiltering());
        }
        else {
            LOGGER.error("Registration failed with unexpected class: {}", rootLogger.getClass());
        }

    }

    public void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            //Moved the methods below into enqueue to make sure they dont cause issues during registration - andrew
            StructurePiecesBehavior.init();
            RSProcessors.registerProcessors();
            RSPredicates.registerPredicates();
            RSNumberProviders.registerNumberProviders();
            RSConfiguredFeatures.registerConfiguredFeatures();
            RSStructures.setupStructures();
            RSConfiguredStructures.registerStructureFeatures();
            RSStructureTagMap.setupTags();
            RSGlobalLootModifier.registerLootData();
            BiomeSelection.setupOverworldBiomesSet();
            BiomeDimensionAllowDisallow.setupAllowDisallowMaps();
            MobSpawningOverTime.setupMobSpawningMaps();

            // Workaround for Terraforged
            WorldGenRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(settings -> {
                Map<Structure<?>, StructureSeparationSettings> structureMap = settings.getValue().structureSettings().structureConfig();
                if (structureMap instanceof ImmutableMap) {
                    Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(structureMap);
                    tempMap.putAll(RSStructures.RS_STRUCTURES);
                    settings.getValue().structureSettings().structureConfig = tempMap;
                }
                else {
                    structureMap.putAll(RSStructures.RS_STRUCTURES);
                }
            });
        });
    }

    public void registerDatapackListener(final AddReloadListenerEvent event) {
        //loads the RS specific json files for mob spawner chances
        event.addListener(RepurposedStructures.mobSpawnerManager);
    }

    public void biomeModification(final BiomeLoadingEvent event) {
        //Add our structures and features
        RepurposedStructures.addFeaturesAndStructuresToBiomes(event);
    }

    /**
     * This is a high priority worldevent.load instead of a mixin because the mixin form is too early
     * and could break a potential future Forge PR that is currently being worked on.
     */
    public void deepCopyDimensionalSpacing(final WorldEvent.Load event) {
        if (event.getWorld() instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) event.getWorld();

            // Workaround for Terraforged
            ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey(((ChunkGeneratorAccessor) serverWorld.getChunkSource().generator).repurposedstructures_getCodec());
            if (cgRL != null && cgRL.getNamespace().equals("terraforged")) return;

            ChunkGenerator chunkGenerator = serverWorld.getChunkSource().generator;
            ((ChunkGeneratorAccessor) chunkGenerator).repurposedstructures_setSettings(NoiseSettingsDeepCopier.deepCopyDimensionStructuresSettings(chunkGenerator.getSettings()));
        }
    }

    public void addDimensionalSpacing(final WorldEvent.Load event) {
        //add our structure spacing to all chunkgenerators including modded one and datapack ones.
        if (event.getWorld() instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) event.getWorld();

            // Workaround for Terraforged
            ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey(((ChunkGeneratorAccessor) serverWorld.getChunkSource().generator).repurposedstructures_getCodec());
            if (cgRL != null && cgRL.getNamespace().equals("terraforged")) return;

            //add our structure spacing to all chunkgenerators including modded one and datapack ones.
            // Need temp map as some mods use custom chunk generators with immutable maps in themselves.
            Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkSource().getGenerator().getSettings().structureConfig());

            // make absolutely sure superflat dimension cannot spawn RS structures
            if (serverWorld.getChunkSource().getGenerator() instanceof FlatChunkGenerator && serverWorld.dimension().equals(World.OVERWORLD)) {
                tempMap.keySet().removeAll(RSStructures.RS_STRUCTURES.keySet());
            }
            // Not superflat overworld. Do normal behavior now
            else{
                for(Map.Entry<Structure<?>, StructureSeparationSettings> structureFeatureEntry : RSStructures.RS_STRUCTURES.entrySet()){
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
            serverWorld.getChunkSource().generator.getSettings().structureConfig = tempMap;
        }
    }

    /*
     * Here, we will use this to add our structures/features to all biomes.
     */
    public static void addFeaturesAndStructuresToBiomes(BiomeLoadingEvent event) {
        Mineshafts.addMineshafts(event);
        Dungeons.addDungeons(event);
        Wells.addWells(event);
        Strongholds.addStrongholds(event);
        Outposts.addOutposts(event);
        Shipwrecks.addShipwrecks(event);
        Fortresses.addJungleFortress(event);
        Temples.addTemples(event);
        Pyramids.addPyramids(event);
        Igloos.addIgloos(event);
        Villages.addVillages(event);
        RuinedPortals.addRuinedPortals(event);
        Ruins.addRuins(event);
        Cities.addCities(event);
        Mansions.addMansions(event);
        WitchHuts.addWitchHuts(event);
        Bastions.addBastions(event);
    }
}
