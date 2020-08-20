package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.configs.RSAllConfig;
import com.telepathicgrunt.repurposedstructures.misc.VillagerTrades;
import com.telepathicgrunt.repurposedstructures.utils.MobSpawnerManager;
import com.telepathicgrunt.repurposedstructures.world.placements.RSPlacements;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.*;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.server.ServerStartCallback;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.world.biome.Biome;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        //LoadNbtBlock.instantiateNbtBlock();
    }

    public static void initialize() {
        AutoConfig.register(RSAllConfig.class, Toml4jConfigSerializer::new);
        RSAllConfig = AutoConfig.getConfigHolder(RSAllConfig.class).getConfig();

        ServerStartCallback.EVENT.register(minecraftServer -> VillagerTrades.addMapTrades());

        RepurposedStructures.registerStructurePools();
        RSPlacements.registerPlacements();
        RSFeatures.registerFeatures();
        //LoadNbtBlock.instantiateNbtBlock();
    }

    /*
     * Here, we will use this to add our structures/features to all biomes.
     */
    public static void addFeaturesAndStructuresToBiomes(MutableRegistry<Biome> biomeReg, Biome biome, Identifier biomeID, Map<String, List<String>> allBiomeBlacklists) {
        String biomeNamespace = biomeID.getNamespace();
        String biomePath = biomeID.getPath();

        if(isBiomeAllowed("mineshaft", biomeID, allBiomeBlacklists))
            RSAddFeatures.addMineshafts(biomeReg, biome, biomeNamespace, biomePath);
        if(isBiomeAllowed("fortress", biomeID, allBiomeBlacklists))
            RSAddFeatures.addJungleFortress(biome, biomeNamespace, biomePath);
        if(isBiomeAllowed("dungeon", biomeID, allBiomeBlacklists))
            RSAddFeatures.addDungeons(biomeReg, biome, biomeNamespace, biomePath);
        if(isBiomeAllowed("well", biomeID, allBiomeBlacklists))
            RSAddFeatures.addWells(biome, biomeNamespace, biomePath);
        if(isBiomeAllowed("swamp_tree", biomeID, allBiomeBlacklists))
            RSAddFeatures.addSwampTreeFeatures(biomeReg, biome, biomeNamespace, biomePath);
        if(isBiomeAllowed("boulder", biomeID, allBiomeBlacklists))
            RSAddFeatures.addBoulderFeatures(biomeReg, biome, biomeNamespace, biomePath);
        if(isBiomeAllowed("temple", biomeID, allBiomeBlacklists))
            RSAddFeatures.addTemples(biome, biomeNamespace, biomePath);
        if(isBiomeAllowed("pyramid", biomeID, allBiomeBlacklists))
            RSAddFeatures.addPyramids(biome, biomeNamespace, biomePath);
        if(isBiomeAllowed("igloo", biomeID, allBiomeBlacklists))
            RSAddFeatures.addIgloos(biome, biomeNamespace, biomePath);
        if(isBiomeAllowed("outpost", biomeID, allBiomeBlacklists))
            RSAddFeatures.addOutposts(biome, biomeNamespace, biomePath);
        if(isBiomeAllowed("shipwreck", biomeID, allBiomeBlacklists))
            RSAddFeatures.addShipwrecks(biomeReg, biome, biomeNamespace, biomePath);
        if(isBiomeAllowed("village", biomeID, allBiomeBlacklists))
            RSAddFeatures.addVillages(biomeReg, biome, biomeNamespace, biomePath);
        if(isBiomeAllowed("stronghold", biomeID, allBiomeBlacklists))
            RSAddFeatures.addStrongholds(biome, biomeNamespace, biomePath);
    }

    private static boolean isBiomeAllowed(String structureType, Identifier biomeID, Map<String, List<String>> allBiomeBlacklists){
        return allBiomeBlacklists.get(structureType).stream().noneMatch(blacklistedBiome -> blacklistedBiome.equals(biomeID.toString()));
    }

    /**
     * Registers all jigsaw structures' pools
     */
    private static boolean lock = false;
    public static void registerStructurePools(){
        if(lock) return;
        lock = true;

        VillageBadlandsPools.init();
        VillageBirchPools.init();
        VillageCrimsonPools.init();
        VillageDarkForestPools.init();
        VillageGiantTaigaPools.init();
        VillageJunglePools.init();
        VillageMountainsPools.init();
        VillageSwampPools.init();
        VillageWarpedPools.init();
        ShipwreckPools.initPools();
        OutpostNetherPools.initPools();
        PyramidPools.initPools();
        TempleNetherPools.initPools();
    }
}
