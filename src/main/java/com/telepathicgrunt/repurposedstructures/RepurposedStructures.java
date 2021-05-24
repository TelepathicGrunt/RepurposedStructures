package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.biomeinjection.*;
import com.telepathicgrunt.repurposedstructures.configs.RSAllConfig;
import com.telepathicgrunt.repurposedstructures.misc.MobMapTrades;
import com.telepathicgrunt.repurposedstructures.mixin.StructuresConfigAccessor;
import com.telepathicgrunt.repurposedstructures.modinit.*;
import com.telepathicgrunt.repurposedstructures.utils.LogSpamFiltering;
import com.telepathicgrunt.repurposedstructures.misc.MobSpawnerManager;
import com.telepathicgrunt.repurposedstructures.misc.PoolAdditionMerger;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.StructurePiecesBehavior;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.chunk.FlatChunkGenerator;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class RepurposedStructures implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "repurposed_structures";
    public static MobSpawnerManager mobSpawnerManager = new MobSpawnerManager();

	public static RSAllConfig RSAllConfig = null;
    public static final Map<String, List<String>> ALL_BIOME_BLACKLISTS = new HashMap<>();

    @Override
    public void onInitialize() {
        AutoConfig.register(RSAllConfig.class, Toml4jConfigSerializer::new);
        RSAllConfig = AutoConfig.getConfigHolder(RSAllConfig.class).getConfig();
        RSPlacements.registerPlacements();
        RSFeatures.registerFeatures();
        RSProcessors.registerProcessors();
        RSPredicates.registerPredicates();
        RSStructures.registerStructures();
        RSStructureTagMap.setupTags();
        RSConfiguredFeatures.registerConfiguredFeatures();
        RSConfiguredStructures.registerConfiguredStructures();

        allowStructureSpawningPerDimension();
        setupBiomeModifications();
        MobMapTrades.addMapTrades();
        StructurePiecesBehavior.init();
        PoolAdditionMerger.mergeAdditionPools();
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(RepurposedStructures.mobSpawnerManager);

        // Silences logspam due to me changing my piece's namespace from minecraft to my modid.
        Logger rootLogger = LogManager.getRootLogger();
        if (rootLogger instanceof org.apache.logging.log4j.core.Logger) {
            ((org.apache.logging.log4j.core.Logger) rootLogger).addFilter(new LogSpamFiltering());
        } else {
            LOGGER.error("Registration failed with unexpected class: {}", rootLogger.getClass());
        }
    }

    /**
     * Grabs and parses the Biome blacklist from configs and stores it into
     * a map of structure/feature type to their specific blacklist.
     */
    public static void getBiomeBlacklists(){
        ALL_BIOME_BLACKLISTS.put("dungeons", Arrays.asList(RepurposedStructures.RSAllConfig.RSDungeonsConfig.blacklistedDungeonBiomes.replace(" ", "").split(",")));
        ALL_BIOME_BLACKLISTS.put("fortresses", Arrays.asList(RepurposedStructures.RSAllConfig.RSMainConfig.jungleFortress.blacklistedFortressBiomes.replace(" ", "").split(",")));
        ALL_BIOME_BLACKLISTS.put("igloos", Arrays.asList(RepurposedStructures.RSAllConfig.RSMainConfig.igloos.blacklistedIglooBiomes.replace(" ", "").split(",")));
        ALL_BIOME_BLACKLISTS.put("mineshafts", Arrays.asList(RepurposedStructures.RSAllConfig.RSMineshaftsConfig.blacklistedMineshaftBiomes.replace(" ", "").split(",")));
        ALL_BIOME_BLACKLISTS.put("outposts", Arrays.asList(RepurposedStructures.RSAllConfig.RSOutpostsConfig.blacklistedOutpostBiomes.replace(" ", "").split(",")));
        ALL_BIOME_BLACKLISTS.put("shipwrecks", Arrays.asList(RepurposedStructures.RSAllConfig.RSShipwrecksConfig.blacklist.blacklistedShipwreckBiomes.replace(" ", "").split(",")));
        ALL_BIOME_BLACKLISTS.put("strongholds", Arrays.asList(RepurposedStructures.RSAllConfig.RSStrongholdsConfig.blacklistedStrongholdBiomes.replace(" ", "").split(",")));
        ALL_BIOME_BLACKLISTS.put("temples", Arrays.asList(RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.blacklistedTempleBiomes.replace(" ", "").split(",")));
        ALL_BIOME_BLACKLISTS.put("pyramids", Arrays.asList(RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.blacklistedPyramidBiomes.replace(" ", "").split(",")));
        ALL_BIOME_BLACKLISTS.put("villages", Arrays.asList(RepurposedStructures.RSAllConfig.RSVillagesConfig.blacklistedVillageBiomes.replace(" ", "").split(",")));
        ALL_BIOME_BLACKLISTS.put("wells", Arrays.asList(RepurposedStructures.RSAllConfig.RSWellsConfig.blacklistedWellBiomes.replace(" ", "").split(",")));
        ALL_BIOME_BLACKLISTS.put("ruined_portals", Arrays.asList(RepurposedStructures.RSAllConfig.RSMainConfig.ruinedPortals.blacklistedRuinedPortalsBiomes.replace(" ", "").split(",")));
        ALL_BIOME_BLACKLISTS.put("ruins", Arrays.asList(RepurposedStructures.RSAllConfig.RSMainConfig.ruins.blacklistedRuinsBiomes.replace(" ", "").split(",")));
        ALL_BIOME_BLACKLISTS.put("cities", Arrays.asList(RepurposedStructures.RSAllConfig.RSMainConfig.cities.blacklistedCitiesBiomes.replace(" ", "").split(",")));
        ALL_BIOME_BLACKLISTS.put("mansions", Arrays.asList(RepurposedStructures.RSAllConfig.RSMansionsConfig.blacklist.blacklistedMansionBiomes.replace(" ", "").split(",")));
        ALL_BIOME_BLACKLISTS.put("witch_huts", Arrays.asList(RepurposedStructures.RSAllConfig.RSWitchHutsConfig.blacklist.blacklistedWitchHutsBiomes.replace(" ", "").split(",")));
    }

    public static void allowStructureSpawningPerDimension() {
        // Controls the dimension blacklisting
        ServerWorldEvents.LOAD.register((MinecraftServer minecraftServer, ServerWorld serverWorld) -> {

            //add our structure spacing to all chunkgenerators including modded one and datapack ones.
            List<String> dimensionBlacklist = Arrays.stream(RepurposedStructures.RSAllConfig.RSMainConfig.blacklistedDimensions.split(",")).map(String::trim).collect(Collectors.toList());

            // Need temp map as some mods use custom chunk generators with immutable maps in themselves.
            Map<StructureFeature<?>, StructureConfig> tempMap = new HashMap<>(serverWorld.getChunkManager().getChunkGenerator().getStructuresConfig().getStructures());
            if (dimensionBlacklist.stream().anyMatch(blacklist -> blacklist.equals((serverWorld.getRegistryKey().getValue().toString())))){
                // make absolutely sure dimension cannot spawn RS structures
                tempMap.keySet().removeAll(RSStructures.RS_STRUCTURES.keySet());
            }
            else if (serverWorld.getChunkManager().getChunkGenerator() instanceof FlatChunkGenerator && serverWorld.getRegistryKey().equals(World.OVERWORLD)) {
                // make absolutely sure superflat dimension cannot spawn RS structures
                tempMap.keySet().removeAll(RSStructures.RS_STRUCTURES.keySet());
            }
            else {
                // make absolutely sure dimension can spawn RS structures
                Map<StructureFeature<?>, StructureConfig> spacingToAdd = new Reference2ObjectOpenHashMap<>();
                spacingToAdd.putAll(RSStructures.RS_STRUCTURES);
                spacingToAdd.forEach(tempMap::putIfAbsent);
            }
            ((StructuresConfigAccessor) serverWorld.getChunkManager().getChunkGenerator().getStructuresConfig()).rs_setStructures(tempMap);
        });
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // BIOME MODIFICATION API //
    public static void setupBiomeModifications() {
        RepurposedStructures.getBiomeBlacklists();

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
    }
}
