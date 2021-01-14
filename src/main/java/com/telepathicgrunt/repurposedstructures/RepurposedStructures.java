package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.configs.RSAllConfig;
import com.telepathicgrunt.repurposedstructures.misc.VillagerTrades;
import com.telepathicgrunt.repurposedstructures.modinit.*;
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

    //TODO: make zombie badlands village
    //TODO: replace cobblestone in oak village

    @Override
    public void onInitialize() {
        AutoConfig.register(RSAllConfig.class, Toml4jConfigSerializer::new);
        RSAllConfig = AutoConfig.getConfigHolder(RSAllConfig.class).getConfig();

        ServerStartCallback.EVENT.register(minecraftServer -> VillagerTrades.addMapTrades());

        RSPlacements.registerPlacements();
        RSFeatures.registerFeatures();
        RSStructures.registerStructures();
        RSStructureTagMap.setupTags();
        RSConfiguredFeatures.registerConfiguredFeatures();
        RSConfiguredStructures.registerConfiguredStructures();

        RSAddFeaturesAndStructures.allowStructureSpawningPerDimension();
        RSAddFeaturesAndStructures.setupBiomeModifications();
    }

    /**
     * Grabs and parses the Biome blacklist from configs and stores it into
     * a map of structure/feature type to their specific blacklist.
     *
     * The structure/feature types are:
     *
     * "dungeon", "boulder", "swamp_tree", "fortress", "igloo",
     * "mineshaft", "outpost", "shipwreck", "stronghold", "temple",
     * "pyramid", "village", "well"
     *
     */
    public static void getBiomeBlacklists(){
        ALL_BIOME_BLACKLISTS.put("dungeon", Arrays.asList(RepurposedStructures.RSAllConfig.RSDungeonsConfig.blacklistedDungeonBiomes.replace(" ", "").split(",")));
        ALL_BIOME_BLACKLISTS.put("boulder", Arrays.asList(RepurposedStructures.RSAllConfig.RSMainConfig.misc.blacklistedBoulderBiomes.replace(" ", "").split(",")));
        ALL_BIOME_BLACKLISTS.put("swamp_tree", Arrays.asList(RepurposedStructures.RSAllConfig.RSMainConfig.misc.blacklistedSwampTreeBiomes.replace(" ", "").split(",")));
        ALL_BIOME_BLACKLISTS.put("fortress", Arrays.asList(RepurposedStructures.RSAllConfig.RSMainConfig.jungleFortress.blacklistedFortressBiomes.replace(" ", "").split(",")));
        ALL_BIOME_BLACKLISTS.put("igloo", Arrays.asList(RepurposedStructures.RSAllConfig.RSMainConfig.igloos.blacklistedIglooBiomes.replace(" ", "").split(",")));
        ALL_BIOME_BLACKLISTS.put("mineshaft", Arrays.asList(RepurposedStructures.RSAllConfig.RSMineshaftsConfig.blacklistedMineshaftBiomes.replace(" ", "").split(",")));
        ALL_BIOME_BLACKLISTS.put("outpost", Arrays.asList(RepurposedStructures.RSAllConfig.RSOutpostsConfig.blacklistedOutpostBiomes.replace(" ", "").split(",")));
        ALL_BIOME_BLACKLISTS.put("shipwreck", Arrays.asList(RepurposedStructures.RSAllConfig.RSShipwrecksConfig.blacklist.blacklistedShipwreckBiomes.replace(" ", "").split(",")));
        ALL_BIOME_BLACKLISTS.put("stronghold", Arrays.asList(RepurposedStructures.RSAllConfig.RSStrongholdsConfig.blacklistedStrongholdBiomes.replace(" ", "").split(",")));
        ALL_BIOME_BLACKLISTS.put("temple", Arrays.asList(RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.blacklistedTempleBiomes.replace(" ", "").split(",")));
        ALL_BIOME_BLACKLISTS.put("pyramid", Arrays.asList(RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.blacklistedPyramidBiomes.replace(" ", "").split(",")));
        ALL_BIOME_BLACKLISTS.put("village", Arrays.asList(RepurposedStructures.RSAllConfig.RSVillagesConfig.blacklistedVillageBiomes.replace(" ", "").split(",")));
        ALL_BIOME_BLACKLISTS.put("well", Arrays.asList(RepurposedStructures.RSAllConfig.RSWellsConfig.blacklistedWellBiomes.replace(" ", "").split(",")));
        ALL_BIOME_BLACKLISTS.put("ruined_portals", Arrays.asList(RepurposedStructures.RSAllConfig.RSMainConfig.ruinedPortals.blacklistedRuinedPortalsBiomes.replace(" ", "").split(",")));
    }
}
