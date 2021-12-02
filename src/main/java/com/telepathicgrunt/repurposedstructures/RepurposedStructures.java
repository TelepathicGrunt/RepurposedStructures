package com.telepathicgrunt.repurposedstructures;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
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
import com.telepathicgrunt.repurposedstructures.biomeinjection.TemporaryBiomeInjection;
import com.telepathicgrunt.repurposedstructures.biomeinjection.Villages;
import com.telepathicgrunt.repurposedstructures.biomeinjection.Wells;
import com.telepathicgrunt.repurposedstructures.biomeinjection.WitchHuts;
import com.telepathicgrunt.repurposedstructures.configs.*;
import com.telepathicgrunt.repurposedstructures.configs.omegaconfig.OmegaConfig;
import com.telepathicgrunt.repurposedstructures.misc.BiomeDimensionAllowDisallow;
import com.telepathicgrunt.repurposedstructures.misc.EndRemasteredDedicatedLoot;
import com.telepathicgrunt.repurposedstructures.misc.JSONConditionsRegistry;
import com.telepathicgrunt.repurposedstructures.misc.MobMapTrades;
import com.telepathicgrunt.repurposedstructures.misc.MobSpawnerManager;
import com.telepathicgrunt.repurposedstructures.misc.MobSpawningOverTime;
import com.telepathicgrunt.repurposedstructures.misc.NoiseSettingsDeepCopier;
import com.telepathicgrunt.repurposedstructures.misc.PoolAdditionMerger;
import com.telepathicgrunt.repurposedstructures.misc.StructurePieceCountsManager;
import com.telepathicgrunt.repurposedstructures.mixin.structures.StructureSettingsAccessor;
import com.telepathicgrunt.repurposedstructures.mixin.world.ChunkGeneratorAccessor;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSGlobalLootModifier;
import com.telepathicgrunt.repurposedstructures.modinit.RSPlacements;
import com.telepathicgrunt.repurposedstructures.modinit.RSPredicates;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
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
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;


@Mod(RepurposedStructures.MODID)
public class RepurposedStructures {
    public static final String MODID = "repurposed_structures";
    public static final Logger LOGGER = LogManager.getLogger();

    public static final RSBiomeDimConfig omegaBiomeDimConfig = OmegaConfig.register(RSBiomeDimConfig.class);
    public static final RSNaturalMobSpawningConfig omegaMobSpawnConfig = OmegaConfig.register(RSNaturalMobSpawningConfig.class);
    public static MobSpawnerManager mobSpawnerManager = new MobSpawnerManager();
    public static StructurePieceCountsManager structurePieceCountsManager = new StructurePieceCountsManager();

    public RepurposedStructures() {
        // Classload and create custom registry. Other mods should add to this custom registry in FMLCommonSetupEvent.
        JSONConditionsRegistry.registerTestJSONCondition();

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
            RSProcessors.registerProcessors();
            RSPredicates.registerPredicates();
            RSPlacements.registerPlacements();
            RSConfiguredFeatures.registerConfiguredFeatures();
            RSConfiguredFeatures.registerPlacedFeatures();
            RSStructures.setupStructures();
            RSConfiguredStructures.registerStructureFeatures();
            RSStructureTagMap.setupTags();
            RSGlobalLootModifier.registerLootData();
            BiomeSelection.setupOverworldBiomesSet();
            BiomeDimensionAllowDisallow.setupAllowDisallowMaps();
            MobSpawningOverTime.setupMobSpawningMaps();

            // Workaround for Terraforged
            BuiltinRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(settings -> {
                Map<StructureFeature<?>, StructureFeatureConfiguration> structureMap = settings.getValue().structureSettings().structureConfig();
                if (structureMap instanceof ImmutableMap) {
                    Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(structureMap);
                    tempMap.putAll(RSStructures.RS_STRUCTURES);
                    ((StructureSettingsAccessor)settings.getValue().structureSettings()).setStructureConfig(tempMap);
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
        if (event.getWorld() instanceof ServerLevel serverLevel) {
            // Workaround for Terraforged
            ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey(((ChunkGeneratorAccessor) serverLevel.getChunkSource().getGenerator()).repurposedstructures_getCodec());
            if (cgRL != null && cgRL.getNamespace().equals("terraforged")) return;
            ChunkGenerator chunkGenerator = serverLevel.getChunkSource().getGenerator();
            ((ChunkGeneratorAccessor) chunkGenerator).repurposedstructures_setSettings(NoiseSettingsDeepCopier.deepCopyDimensionStructuresSettings(chunkGenerator.getSettings()));
        }
    }

    public void addDimensionalSpacing(final WorldEvent.Load event) {
        //add our structure spacing to all chunkgenerators including modded one and datapack ones.
        if (event.getWorld() instanceof ServerLevel serverLevel) {

            // We will need this a lot lol
            StructureSettings worldStructureSettings = serverLevel.getChunkSource().getGenerator().getSettings();

            //////////// BIOME BASED STRUCTURE SPAWNING ////////////
            /*
             * NOTE: Forge does not have a hook for injecting structures into biomes yet.
             * Instead, we will use the below to add our structure to overworld biomes.
             * Remember, this is temporary until Forge finds a better solution for adding structures to biomes.
             */

            // Grab the map that holds what ConfigureStructures a structure has and what biomes it can spawn in.
            // We will inject our structures into that map/multimap
            Map<StructureFeature<?>, Multimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> tempStructureToMultiMap = new HashMap<>();
            ((StructureSettingsAccessor) worldStructureSettings).getConfiguredStructures().forEach((key, value) -> tempStructureToMultiMap.put(key, HashMultimap.create(value)));
            TemporaryBiomeInjection.addStructureToBiomes(tempStructureToMultiMap, serverLevel.registryAccess().registryOrThrow(Registry.BIOME_REGISTRY));

            // Turn the entire map and the inner multimaps to immutable to match the source code's require type
            ImmutableMap.Builder<StructureFeature<?>, ImmutableMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> immutableOuterMap = ImmutableMap.builder();
            tempStructureToMultiMap.forEach((key, value) -> {
                ImmutableMultimap.Builder<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>> immutableInnerMultiMap = ImmutableMultimap.builder();
                immutableInnerMultiMap.putAll(value);
                immutableOuterMap.put(key, immutableInnerMultiMap.build());
            });

            // Set it in the field.
            ((StructureSettingsAccessor) worldStructureSettings).setConfiguredStructures(immutableOuterMap.build());


            //////////// DIMENSION BASED STRUCTURE SPAWNING ////////////

            // Workaround for Terraforged
            ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey(((ChunkGeneratorAccessor) serverLevel.getChunkSource().getGenerator()).repurposedstructures_getCodec());
            if (cgRL != null && cgRL.getNamespace().equals("terraforged")) return;

            //add our structure spacing to all chunkgenerators including modded one and datapack ones.
            // Need temp map as some mods use custom chunk generators with immutable maps in themselves.
            Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(worldStructureSettings.structureConfig());

            // make absolutely sure superflat dimension cannot spawn RS structures
            if (serverLevel.getChunkSource().getGenerator() instanceof FlatLevelSource && serverLevel.dimension().equals(Level.OVERWORLD)) {
                tempMap.keySet().removeAll(RSStructures.RS_STRUCTURES.keySet());
            }
            // Not superflat overworld. Do normal behavior now
            else{
                for(Map.Entry<StructureFeature<?>, StructureFeatureConfiguration> structureFeatureEntry : RSStructures.RS_STRUCTURES.entrySet()){
                    boolean isWorldBlacklisted = GeneralUtils.isBlacklistedForWorld(serverLevel, ForgeRegistries.STRUCTURE_FEATURES.getKey(structureFeatureEntry.getKey()));
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
            ((StructureSettingsAccessor)worldStructureSettings).setStructureConfig(tempMap);
        }
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
