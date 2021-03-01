package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.configs.RSAllConfig;
import com.telepathicgrunt.repurposedstructures.misc.MobMapTrades;
import com.telepathicgrunt.repurposedstructures.modinit.*;
import com.telepathicgrunt.repurposedstructures.utils.LogSpamFiltering;
import com.telepathicgrunt.repurposedstructures.utils.MobSpawnerManager;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.server.ServerStartCallback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SuppressWarnings("deprecation")
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

        ServerStartCallback.EVENT.register(minecraftServer -> MobMapTrades.addMapTrades());

        RSPlacements.registerPlacements();
        RSFeatures.registerFeatures();
        RSProcessors.registerProcessors();
        RSStructures.registerStructures();
        RSStructureTagMap.setupTags();
        RSConfiguredFeatures.registerConfiguredFeatures();
        RSConfiguredStructures.registerConfiguredStructures();

        RSAddFeaturesAndStructures.allowStructureSpawningPerDimension();
        RSAddFeaturesAndStructures.setupBiomeModifications();


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
        ALL_BIOME_BLACKLISTS.put("boulders", Arrays.asList(RepurposedStructures.RSAllConfig.RSMainConfig.misc.blacklistedBoulderBiomes.replace(" ", "").split(",")));
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
}
