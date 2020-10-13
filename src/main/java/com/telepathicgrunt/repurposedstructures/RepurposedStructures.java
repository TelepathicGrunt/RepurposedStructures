package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.configs.RSAllConfig;
import com.telepathicgrunt.repurposedstructures.misc.VillagerTrades;
import com.telepathicgrunt.repurposedstructures.utils.MobSpawnerManager;
import com.telepathicgrunt.repurposedstructures.world.placements.RSPlacements;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.server.ServerStartCallback;
import net.fabricmc.loader.FabricLoader;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
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
    public static MobSpawnerManager mobSpawnerManager = null;
    public static boolean yungsBetterMineshaftIsNotOn = true;

	public static RSAllConfig RSAllConfig = null;

    //TODO: make zombie badlands village
    //TODO: add 1 Piglin Brute to outposts
    //TODO: fix ocean dungeon chests
    //TODO NEXT: overworld biome specific outposts

    @Override
    public void onInitialize() {
        // LoadNbtBlock.instantiateNbtBlock();
        RSAddFeaturesAndStructures.allowStructureSpawningPerDimension();
    }

    public static void initialize() {
        AutoConfig.register(RSAllConfig.class, Toml4jConfigSerializer::new);
        RSAllConfig = AutoConfig.getConfigHolder(RSAllConfig.class).getConfig();

        ServerStartCallback.EVENT.register(minecraftServer -> VillagerTrades.addMapTrades());

        RSPlacements.registerPlacements();
        RSFeatures.registerFeatures();
        RSStructures.registerStructures();
        RSConfiguredFeatures.registerConfiguredFeatures();
        RSConfiguredStructures.registerConfiguredStructures();

        yungsBetterMineshaftIsNotOn = !FabricLoader.INSTANCE.isModLoaded("bettermineshafts");
    }

    /*
     * Here, we will use this to add our structures/features to all biomes.
     */
    public static void addFeaturesAndStructuresToBiomes(Biome biome, Identifier biomeID, Map<String, List<String>> allBiomeBlacklists) {

        if(isBiomeAllowed("mineshaft", biomeID, allBiomeBlacklists))
            RSAddFeaturesAndStructures.addMineshafts(biome, biomeID);
        if(isBiomeAllowed("fortress", biomeID, allBiomeBlacklists))
            RSAddFeaturesAndStructures.addJungleFortress(biome, biomeID);
        if(isBiomeAllowed("dungeon", biomeID, allBiomeBlacklists))
            RSAddFeaturesAndStructures.addDungeons(biome, biomeID);
        if(isBiomeAllowed("well", biomeID, allBiomeBlacklists))
            RSAddFeaturesAndStructures.addWells(biome, biomeID);
        if(isBiomeAllowed("swamp_tree", biomeID, allBiomeBlacklists))
            RSAddFeaturesAndStructures.addSwampTreeFeatures(biome, biomeID);
        if(isBiomeAllowed("boulder", biomeID, allBiomeBlacklists))
            RSAddFeaturesAndStructures.addBoulderFeatures(biome, biomeID);
        if(isBiomeAllowed("temple", biomeID, allBiomeBlacklists))
            RSAddFeaturesAndStructures.addTemples(biome, biomeID);
        if(isBiomeAllowed("pyramid", biomeID, allBiomeBlacklists))
            RSAddFeaturesAndStructures.addPyramids(biome, biomeID);
        if(isBiomeAllowed("igloo", biomeID, allBiomeBlacklists))
            RSAddFeaturesAndStructures.addIgloos(biome, biomeID);
        if(isBiomeAllowed("outpost", biomeID, allBiomeBlacklists))
            RSAddFeaturesAndStructures.addOutposts(biome, biomeID);
        if(isBiomeAllowed("shipwreck", biomeID, allBiomeBlacklists))
            RSAddFeaturesAndStructures.addShipwrecks(biome, biomeID);
        if(isBiomeAllowed("village", biomeID, allBiomeBlacklists))
            RSAddFeaturesAndStructures.addVillages(biome, biomeID);
        if(isBiomeAllowed("stronghold", biomeID, allBiomeBlacklists))
            RSAddFeaturesAndStructures.addStrongholds(biome, biomeID);
    }

    private static boolean isBiomeAllowed(String structureType, Identifier biomeID, Map<String, List<String>> allBiomeBlacklists){
        return allBiomeBlacklists.get(structureType).stream().noneMatch(blacklistedBiome -> blacklistedBiome.equals(biomeID.toString()));
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
     * @return - A map of structure/feature type to their biome blacklist
     */
    public static Map<String, List<String>> getBiomeBlacklists(){
        Map<String, List<String>> allBiomeBlacklists = new HashMap<>();

        allBiomeBlacklists.put("dungeon", Arrays.asList(RepurposedStructures.RSAllConfig.RSDungeonsConfig.blacklistedDungeonBiomes.split(",")));
        allBiomeBlacklists.put("boulder", Arrays.asList(RepurposedStructures.RSAllConfig.RSMainConfig.misc.blacklistedBoulderBiomes.split(",")));
        allBiomeBlacklists.put("swamp_tree", Arrays.asList(RepurposedStructures.RSAllConfig.RSMainConfig.misc.blacklistedSwampTreeBiomes.split(",")));
        allBiomeBlacklists.put("fortress", Arrays.asList(RepurposedStructures.RSAllConfig.RSMainConfig.jungleFortress.blacklistedFortressBiomes.split(",")));
        allBiomeBlacklists.put("igloo", Arrays.asList(RepurposedStructures.RSAllConfig.RSMainConfig.igloos.blacklistedIglooBiomes.split(",")));
        allBiomeBlacklists.put("mineshaft", Arrays.asList(RepurposedStructures.RSAllConfig.RSMineshaftsConfig.blacklistedMineshaftBiomes.split(",")));
        allBiomeBlacklists.put("outpost", Arrays.asList(RepurposedStructures.RSAllConfig.RSOutpostsConfig.blacklistedOutpostBiomes.split(",")));
        allBiomeBlacklists.put("shipwreck", Arrays.asList(RepurposedStructures.RSAllConfig.RSShipwrecksConfig.blacklist.blacklistedShipwreckBiomes.split(",")));
        allBiomeBlacklists.put("stronghold", Arrays.asList(RepurposedStructures.RSAllConfig.RSStrongholdsConfig.blacklistedStrongholdBiomes.split(",")));
        allBiomeBlacklists.put("temple", Arrays.asList(RepurposedStructures.RSAllConfig.RSTemplesConfig.temples.blacklistedTempleBiomes.split(",")));
        allBiomeBlacklists.put("pyramid", Arrays.asList(RepurposedStructures.RSAllConfig.RSTemplesConfig.pyramids.blacklistedPyramidBiomes.split(",")));
        allBiomeBlacklists.put("village", Arrays.asList(RepurposedStructures.RSAllConfig.RSVillagesConfig.blacklistedVillageBiomes.split(",")));
        allBiomeBlacklists.put("well", Arrays.asList(RepurposedStructures.RSAllConfig.RSWellsConfig.blacklistedWellBiomes.split(",")));

        return allBiomeBlacklists;
    }
}
