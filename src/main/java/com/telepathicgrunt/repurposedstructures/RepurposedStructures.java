package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.configs.RSAllConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSMainConfig;
import com.telepathicgrunt.repurposedstructures.misc.VillagerTrades;
import com.telepathicgrunt.repurposedstructures.utils.MobSpawnerManager;
import com.telepathicgrunt.repurposedstructures.world.placements.RSPlacements;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.fabricmc.fabric.api.event.server.ServerStartCallback;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
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

	public static RSAllConfig RSAllConfig = null;

    @Override
    public void onInitialize() {
        AutoConfig.register(RSAllConfig.class, Toml4jConfigSerializer::new);
        RSAllConfig = AutoConfig.getConfigHolder(RSAllConfig.class).getConfig();

        RSPlacements.registerPlacements();
        RSFeatures.registerFeatures();
        ServerStartCallback.EVENT.register(minecraftServer -> VillagerTrades.addMapTrades());

        //LoadNbtBlock.instantiateNbtBlock();


        //Gets blacklisted biome IDs for each structure type
        //Done here so the map can be garbage collected later
        Map<String, List<String>> allBiomeBlacklists = new HashMap<>();
        allBiomeBlacklists.put("dungeon", Arrays.asList(RSAllConfig.RSDungeonsConfig.blacklistedDungeonBiomes.split(",")));
        allBiomeBlacklists.put("boulder", Arrays.asList(RSAllConfig.RSMainConfig.misc.blacklistedBoulderBiomes.split(",")));
        allBiomeBlacklists.put("swamp_tree", Arrays.asList(RSAllConfig.RSMainConfig.misc.blacklistedSwampTreeBiomes.split(",")));
        allBiomeBlacklists.put("fortress", Arrays.asList(RSAllConfig.RSMainConfig.jungleFortress.blacklistedFortressBiomes.split(",")));
        allBiomeBlacklists.put("igloo", Arrays.asList(RSAllConfig.RSMainConfig.igloos.blacklistedIglooBiomes.split(",")));
        allBiomeBlacklists.put("mineshaft", Arrays.asList(RSAllConfig.RSMineshaftsConfig.blacklistedMineshaftBiomes.split(",")));
        allBiomeBlacklists.put("outpost", Arrays.asList(RSAllConfig.RSOutpostsConfig.outposts.blacklistedOutpostBiomes.split(",")));
        allBiomeBlacklists.put("shipwreck", Arrays.asList(RSAllConfig.RSMainConfig.shipwrecks.blacklistedShipwreckBiomes.split(",")));
        allBiomeBlacklists.put("stronghold", Arrays.asList(RSAllConfig.RSStrongholdsConfig.blacklistedStrongholdBiomes.split(",")));
        allBiomeBlacklists.put("temple", Arrays.asList(RSAllConfig.RSTemplesConfig.temples.blacklistedTempleBiomes.split(",")));
        allBiomeBlacklists.put("pyramid", Arrays.asList(RSAllConfig.RSTemplesConfig.pyramids.blacklistedPyramidBiomes.split(",")));
        allBiomeBlacklists.put("village", Arrays.asList(RSAllConfig.RSVillagesConfig.blacklistedVillageBiomes.split(",")));
        allBiomeBlacklists.put("well", Arrays.asList(RSAllConfig.RSWellsConfig.blacklistedWellBiomes.split(",")));

        for (Biome biome : Registry.BIOME) {
            addFeaturesAndStructuresToBiomes(biome, Registry.BIOME.getId(biome), allBiomeBlacklists);
        }
        RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> addFeaturesAndStructuresToBiomes(biome, identifier, allBiomeBlacklists));
    }

    /*
     * Here, we will use this to add our structures/features to all biomes.
     */
    public static void addFeaturesAndStructuresToBiomes(Biome biome, Identifier biomeID, Map<String, List<String>> allBiomeBlacklists) {
        String biomeNamespace = biomeID.getNamespace();
        String biomePath = biomeID.getPath();

        if(isBiomeAllowed("mineshaft",biomeID, allBiomeBlacklists))
            RSAddFeatures.addMineshafts(biome, biomeNamespace, biomePath);
        if(isBiomeAllowed("fortress",biomeID, allBiomeBlacklists))
            RSAddFeatures.addJungleFortress(biome, biomeNamespace, biomePath);
        if(isBiomeAllowed("dungeon",biomeID, allBiomeBlacklists))
            RSAddFeatures.addDungeons(biome, biomeNamespace, biomePath);
        if(isBiomeAllowed("well",biomeID, allBiomeBlacklists))
            RSAddFeatures.addWells(biome, biomeNamespace, biomePath);
        if(isBiomeAllowed("swamp_tree",biomeID, allBiomeBlacklists))
            RSAddFeatures.addSwampTreeFeatures(biome, biomeNamespace, biomePath);
        if(isBiomeAllowed("boulder",biomeID, allBiomeBlacklists))
            RSAddFeatures.addBoulderFeatures(biome, biomeNamespace, biomePath);
        if(isBiomeAllowed("temple",biomeID, allBiomeBlacklists))
            RSAddFeatures.addTemples(biome, biomeNamespace, biomePath);
        if(isBiomeAllowed("pyramid",biomeID, allBiomeBlacklists))
            RSAddFeatures.addPyramids(biome, biomeNamespace, biomePath);
        if(isBiomeAllowed("igloo",biomeID, allBiomeBlacklists))
            RSAddFeatures.addIgloos(biome, biomeNamespace, biomePath);
        if(isBiomeAllowed("outpost",biomeID, allBiomeBlacklists))
            RSAddFeatures.addOutposts(biome, biomeNamespace, biomePath);
        if(isBiomeAllowed("shipwreck",biomeID, allBiomeBlacklists))
            RSAddFeatures.addShipwrecks(biome, biomeNamespace, biomePath);
        if(isBiomeAllowed("village",biomeID, allBiomeBlacklists))
            RSAddFeatures.addVillages(biome, biomeNamespace, biomePath);
        if(isBiomeAllowed("stronghold",biomeID, allBiomeBlacklists))
            RSAddFeatures.addStrongholds(biome, biomeNamespace, biomePath);
    }

    private static boolean isBiomeAllowed(String structureType, Identifier biomeID, Map<String, List<String>> allBiomeBlacklists){
        return allBiomeBlacklists.get(structureType).stream().noneMatch(blacklistedBiome -> blacklistedBiome.equals(biomeID.toString()));
    }
}
