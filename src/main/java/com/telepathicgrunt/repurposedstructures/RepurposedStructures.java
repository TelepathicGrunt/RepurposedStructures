package com.telepathicgrunt.repurposedstructures;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.biomeinjection.*;
import com.telepathicgrunt.repurposedstructures.configs.RSDungeonsConfig.RSDungeonsConfigValues;
import com.telepathicgrunt.repurposedstructures.configs.RSMainConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSMainConfig.RSConfigValues;
import com.telepathicgrunt.repurposedstructures.configs.RSMansionsConfig.RSMansionsConfigValues;
import com.telepathicgrunt.repurposedstructures.configs.RSMineshaftsConfig.RSMineshaftsConfigValues;
import com.telepathicgrunt.repurposedstructures.configs.RSOutpostsConfig.RSOutpostsConfigValues;
import com.telepathicgrunt.repurposedstructures.configs.RSShipwrecksConfig.RSShipwrecksConfigValues;
import com.telepathicgrunt.repurposedstructures.configs.RSStrongholdsConfig.RSStrongholdsConfigValues;
import com.telepathicgrunt.repurposedstructures.configs.RSTemplesConfig.RSTemplesConfigValues;
import com.telepathicgrunt.repurposedstructures.configs.RSVillagesConfig.RSVillagesConfigValues;
import com.telepathicgrunt.repurposedstructures.configs.RSWellsConfig.RSWellsConfigValues;
import com.telepathicgrunt.repurposedstructures.configs.RSWitchHutsConfig.RSWitchHutsConfigValues;
import com.telepathicgrunt.repurposedstructures.misc.MobMapTrades;
import com.telepathicgrunt.repurposedstructures.misc.MobSpawnerManager;
import com.telepathicgrunt.repurposedstructures.misc.PoolAdditionMerger;
import com.telepathicgrunt.repurposedstructures.misc.StructureModdedLootImporter;
import com.telepathicgrunt.repurposedstructures.mixin.ChunkGeneratorAccessor;
import com.telepathicgrunt.repurposedstructures.modinit.*;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.LogSpamFiltering;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.World;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;


@Mod(RepurposedStructures.MODID)
public class RepurposedStructures {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "repurposed_structures";
    public static RSMainConfig.RSConfigValues RSMainConfig = null;
    public static RSDungeonsConfigValues RSDungeonsConfig = null;
    public static RSMineshaftsConfigValues RSMineshaftsConfig = null;
    public static RSStrongholdsConfigValues RSStrongholdsConfig = null;
    public static RSWellsConfigValues RSWellsConfig = null;
    public static RSOutpostsConfigValues RSOutpostsConfig = null;
    public static RSVillagesConfigValues RSVillagesConfig = null;
    public static RSTemplesConfigValues RSTemplesConfig = null;
    public static RSShipwrecksConfigValues RSShipwrecksConfig = null;
    public static RSMansionsConfigValues RSMansionsConfig = null;
    public static RSWitchHutsConfigValues RSWitchHutsConfig = null;
    public static MobSpawnerManager mobSpawnerManager = new MobSpawnerManager();

    public static boolean yungsBetterMineshaftIsNotOn = true;

    public RepurposedStructures() {
        // Setup configs
        RSMainConfig = ConfigHelper.register(ModConfig.Type.COMMON, RSConfigValues::new, "repurposed_structures-common.toml");
        RSDungeonsConfig = ConfigHelper.register(ModConfig.Type.COMMON, RSDungeonsConfigValues::new, "repurposed_structures-dungeons.toml");
        RSMineshaftsConfig = ConfigHelper.register(ModConfig.Type.COMMON, RSMineshaftsConfigValues::new, "repurposed_structures-mineshafts.toml");
        RSStrongholdsConfig = ConfigHelper.register(ModConfig.Type.COMMON, RSStrongholdsConfigValues::new, "repurposed_structures-strongholds.toml");
        RSWellsConfig = ConfigHelper.register(ModConfig.Type.COMMON, RSWellsConfigValues::new, "repurposed_structures-wells.toml");
        RSShipwrecksConfig = ConfigHelper.register(ModConfig.Type.COMMON, RSShipwrecksConfigValues::new, "repurposed_structures-shipwrecks.toml");
        RSOutpostsConfig = ConfigHelper.register(ModConfig.Type.COMMON, RSOutpostsConfigValues::new, "repurposed_structures-outposts.toml");
        RSTemplesConfig = ConfigHelper.register(ModConfig.Type.COMMON, RSTemplesConfigValues::new, "repurposed_structures-temples.toml");
        RSVillagesConfig = ConfigHelper.register(ModConfig.Type.COMMON, RSVillagesConfigValues::new, "repurposed_structures-villages.toml");
        RSMansionsConfig = ConfigHelper.register(ModConfig.Type.COMMON, RSMansionsConfigValues::new, "repurposed_structures-mansions.toml");
        RSWitchHutsConfig = ConfigHelper.register(ModConfig.Type.COMMON, RSWitchHutsConfigValues::new, "repurposed_structures-witch_huts.toml");

        // Register the setup method for modloading
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        forgeBus.addListener(this::biomeModification);
        forgeBus.addListener(this::registerDatapackListener);
        forgeBus.addListener(this::addDimensionalSpacing);
        forgeBus.addListener(MobMapTrades::onVillagerTradesEvent);
        forgeBus.addListener(PoolAdditionMerger::mergeAdditionPools);
        //GeneralUtils.registerStructureDebugging(RSStructures.STONEBRICK_STRONGHOLD);

        modEventBus.addListener(this::setup);
        RSFeatures.FEATURES.register(modEventBus);
        RSStructures.STRUCTURE_FEATURES.register(modEventBus);
        RSPlacements.DECORATORS.register(modEventBus);
        RSGlobalLootModifier.GLM.register(modEventBus);

        //For mod compat by checking if other mod is on
        yungsBetterMineshaftIsNotOn = !ModList.get().isLoaded("bettermineshafts");
        StructureModdedLootImporter.isEndRemasteredOn = ModList.get().isLoaded("endrem");

        // Silences logspam due to me changing my piece's namespace from minecraft to my modid.
        Logger rootLogger = LogManager.getRootLogger();
        if (rootLogger instanceof org.apache.logging.log4j.core.Logger) {
            ((org.apache.logging.log4j.core.Logger) rootLogger).addFilter(new LogSpamFiltering());
        }
        else {
            LOGGER.error("Registration failed with unexpected class: {}", rootLogger.getClass());
        }

    }

    /*
     * Here, we will use this to add our structures/features to all biomes.
     */
    public void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            //Moved the methods below into enqueue to make sure they dont cause issues during registration - andrew
            RSConfiguredFeatures.registerConfiguredFeatures();
            RSProcessors.registerProcessors();
            RSPredicates.registerPredicates();
            RSStructures.setupStructures();
            RSConfiguredStructures.registerStructureFeatures();
            RSStructureTagMap.setupTags();
            RSGlobalLootModifier.registerLootData();
            BiomeSelection.setupOverworldBiomesSet();

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
        //Gets blacklisted biome IDs for each structure type
        //Done here so the map can be garbage collected later
        Map<String, Pair<List<String>, Consumer<BiomeLoadingEvent>>> allBiomeBlacklists = RepurposedStructures.getBiomeBlacklists();

        //Add our structures and features
        RepurposedStructures.addFeaturesAndStructuresToBiomes(event, allBiomeBlacklists);
    }

    public void addDimensionalSpacing(final WorldEvent.Load event) {
        //add our structure spacing to all chunkgenerators including modded one and datapack ones.
        List<String> dimensionBlacklist = Arrays.stream(RepurposedStructures.RSMainConfig.blacklistedDimensions.get().split(",")).map(String::trim).collect(Collectors.toList());

        if (event.getWorld() instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) event.getWorld();

            // Workaround for Terraforged. Not thrilled they take control over my structure's configs but nothing I can do about that without breaking structure gen/locating or ASM into Terraforged.
            // They took the stance of locking down their ChunkGenerator and breaking mods that modifies the structure configs in it due to a perceived idea that malicious mods exist to mess with other structure's spacings... Dont ask me. I dont even know anymore.
            ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey(((ChunkGeneratorAccessor) serverWorld.getChunkSource().generator).rs_getCodec());
            if (cgRL != null && cgRL.getNamespace().equals("terraforged")) return;


            // Need temp map as some mods use custom chunk generators with immutable maps in themselves.
            Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkSource().generator.getSettings().structureConfig());
            if (dimensionBlacklist.stream().anyMatch(blacklist -> blacklist.equals((serverWorld.dimension().location().toString())))) {
                // make absolutely sure dimension cannot spawn RS structures
                tempMap.keySet().removeAll(RSStructures.RS_STRUCTURES.keySet());
            }
            else if (serverWorld.getChunkSource().getGenerator() instanceof FlatChunkGenerator && serverWorld.dimension().equals(World.OVERWORLD)) {
                // Make absolutely sure superflat dimension cannot spawn RS structures as it is glitchy and weird.
                // Also, users don't like structures in superflat worlds.
                tempMap.keySet().removeAll(RSStructures.RS_STRUCTURES.keySet());
            }
            else {
                // make absolutely sure dimension can spawn RS structures
                Map<Structure<?>, StructureSeparationSettings> spacingToAdd = new Reference2ObjectOpenHashMap<>();
                spacingToAdd.putAll(RSStructures.RS_STRUCTURES);

                // Do not spawn strongholds in end.
                if (serverWorld.dimension().equals(World.END)) {
                    spacingToAdd.remove(RSStructures.NETHER_STRONGHOLD.get());
                }

                spacingToAdd.forEach(tempMap::putIfAbsent);
            }
            serverWorld.getChunkSource().generator.getSettings().structureConfig = tempMap;
        }
    }

    /*
     * Here, we will use this to add our structures/features to all biomes.
     */
    public static void addFeaturesAndStructuresToBiomes(BiomeLoadingEvent event, Map<String, Pair<List<String>, Consumer<BiomeLoadingEvent>>> allBiomeBlacklists) {

        for (Map.Entry<String, Pair<List<String>, Consumer<BiomeLoadingEvent>>> entry : allBiomeBlacklists.entrySet()) {
            if (isBiomeAllowed(entry.getKey(), event.getName(), allBiomeBlacklists)) {
                entry.getValue().getSecond().accept(event);
            }
        }
    }

    private static boolean isBiomeAllowed(String structureType, ResourceLocation biomeID, Map<String, Pair<List<String>, Consumer<BiomeLoadingEvent>>> allBiomeBlacklists) {
        return allBiomeBlacklists.get(structureType).getFirst().stream().noneMatch(blacklistedBiome -> blacklistedBiome.equals(biomeID.toString()));
    }

    /**
     * Grabs and parses the Biome blacklist from configs and stores it into
     * a map of structure/feature type to their specific blacklist.
     *
     * @return - A map of structure/feature type to their biome blacklist
     */
    public static Map<String, Pair<List<String>, Consumer<BiomeLoadingEvent>>> getBiomeBlacklists() {
        Map<String, Pair<List<String>, Consumer<BiomeLoadingEvent>>> allBiomeBlacklists = new HashMap<>();

        allBiomeBlacklists.put("dungeons", Pair.of(Arrays.asList(RepurposedStructures.RSDungeonsConfig.blacklistedDungeonBiomes.get().replace(" ", "").split(",")), Dungeons::addDungeons));
        allBiomeBlacklists.put("fortresses", Pair.of(Arrays.asList(RepurposedStructures.RSMainConfig.blacklistedFortressBiomes.get().replace(" ", "").split(",")), Fortresses::addJungleFortress));
        allBiomeBlacklists.put("igloos", Pair.of(Arrays.asList(RepurposedStructures.RSMainConfig.blacklistedIglooBiomes.get().replace(" ", "").split(",")), Igloos::addIgloos));
        allBiomeBlacklists.put("mineshafts", Pair.of(Arrays.asList(RepurposedStructures.RSMineshaftsConfig.blacklistedMineshaftBiomes.get().replace(" ", "").split(",")), Mineshafts::addMineshafts));
        allBiomeBlacklists.put("outposts", Pair.of(Arrays.asList(RepurposedStructures.RSOutpostsConfig.blacklistedOutpostBiomes.get().replace(" ", "").split(",")), Outposts::addOutposts));
        allBiomeBlacklists.put("shipwrecks", Pair.of(Arrays.asList(RepurposedStructures.RSShipwrecksConfig.blacklistedShipwreckBiomes.get().replace(" ", "").split(",")), Shipwrecks::addShipwrecks));
        allBiomeBlacklists.put("strongholds", Pair.of(Arrays.asList(RepurposedStructures.RSStrongholdsConfig.blacklistedStrongholdBiomes.get().replace(" ", "").split(",")), Strongholds::addStrongholds));
        allBiomeBlacklists.put("temples", Pair.of(Arrays.asList(RepurposedStructures.RSTemplesConfig.blacklistedTempleBiomes.get().replace(" ", "").split(",")), Temples::addTemples));
        allBiomeBlacklists.put("pyramids", Pair.of(Arrays.asList(RepurposedStructures.RSTemplesConfig.blacklistedPyramidBiomes.get().replace(" ", "").split(",")), Pyramids::addPyramids));
        allBiomeBlacklists.put("villages", Pair.of(Arrays.asList(RepurposedStructures.RSVillagesConfig.blacklistedVillageBiomes.get().replace(" ", "").split(",")), Villages::addVillages));
        allBiomeBlacklists.put("wells", Pair.of(Arrays.asList(RepurposedStructures.RSWellsConfig.blacklistedWellBiomes.get().replace(" ", "").split(",")), Wells::addWells));
        allBiomeBlacklists.put("ruinedPortals", Pair.of(Arrays.asList(RepurposedStructures.RSMainConfig.blacklistedRuinedPortalsBiomes.get().replace(" ", "").split(",")), RuinedPortals::addRuinedPortals));
        allBiomeBlacklists.put("ruins", Pair.of(Arrays.asList(RepurposedStructures.RSMainConfig.blacklistedRuinsBiomes.get().replace(" ", "").split(",")), Ruins::addRuins));
        allBiomeBlacklists.put("cities", Pair.of(Arrays.asList(RepurposedStructures.RSMainConfig.blacklistedCitiesBiomes.get().replace(" ", "").split(",")), Cities::addCities));
        allBiomeBlacklists.put("mansions", Pair.of(Arrays.asList(RepurposedStructures.RSMansionsConfig.blacklistedMansionBiomes.get().replace(" ", "").split(",")), Mansions::addMansions));
        allBiomeBlacklists.put("witch_huts", Pair.of(Arrays.asList(RepurposedStructures.RSWitchHutsConfig.blacklistedWitchHutBiomes.get().replace(" ", "").split(",")), WitchHuts::addWitchHuts));
        allBiomeBlacklists.put("underground_bastions", Pair.of(Arrays.asList(RepurposedStructures.RSMainConfig.blacklistedUndergroundBastionBiomes.get().replace(" ", "").split(",")), Bastions::addBastions));

        return allBiomeBlacklists;
    }
}
