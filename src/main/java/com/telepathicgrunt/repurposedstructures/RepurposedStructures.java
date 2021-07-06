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
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resource.ResourceType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.gen.chunk.FlatChunkGenerator;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;


public class RepurposedStructures implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "repurposed_structures";
    public static MobSpawnerManager mobSpawnerManager = new MobSpawnerManager();

	public static RSAllConfig RSAllConfig = null;
    public static final RSAllowDisallowOmegaConfig omegaBiomeDimConfig = OmegaConfig.register(RSAllowDisallowOmegaConfig.class);
    public static final RSNaturalMobSpawningOmegaConfig omegaMobSpawnConfig = OmegaConfig.register(RSNaturalMobSpawningOmegaConfig.class);
    public static boolean betterMineshafts = false;
    public static boolean betterDungeons = false;


    @Override
    public void onInitialize() {
        AutoConfig.register(RSAllConfig.class, JanksonConfigSerializer::new);
        RSAllConfig = AutoConfig.getConfigHolder(RSAllConfig.class).getConfig();

        if (FabricLoader.getInstance().isModLoaded("bettermineshafts")) {
            betterMineshafts = true;
        }
        if (FabricLoader.getInstance().isModLoaded("betterdungeons")) {
            betterDungeons = true;
        }

        RSPlacements.registerPlacements();
        RSFeatures.registerFeatures();
        RSProcessors.registerProcessors();
        RSPredicates.registerPredicates();
        RSStructures.registerStructures();
        RSStructureTagMap.setupTags();
        RSConfiguredFeatures.registerConfiguredFeatures();
        RSConfiguredStructures.registerConfiguredStructures();

        BiomeDimensionAllowDisallow.setupAllowDisallowMaps();
        MobSpawningOverTime.setupMobSpawningMaps();
        setupBiomeModifications();
        allowStructureSpawningPerDimension();
        MobMapTrades.addMapTrades();
        StructurePiecesBehavior.init();
        PoolAdditionMerger.mergeAdditionPools();
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(RepurposedStructures.mobSpawnerManager);
    }

    public static void allowStructureSpawningPerDimension() {
        // Controls the dimension blacklisting
        ServerWorldEvents.LOAD.register((MinecraftServer minecraftServer, ServerWorld serverWorld) -> {

            //add our structure spacing to all chunkgenerators including modded one and datapack ones.
            // Need temp map as some mods use custom chunk generators with immutable maps in themselves.
            Map<StructureFeature<?>, StructureConfig> tempMap = new HashMap<>(serverWorld.getChunkManager().getChunkGenerator().getStructuresConfig().getStructures());

            // make absolutely sure superflat dimension cannot spawn RS structures
            if (serverWorld.getChunkManager().getChunkGenerator() instanceof FlatChunkGenerator && serverWorld.getRegistryKey().equals(World.OVERWORLD)) {
                tempMap.keySet().removeAll(RSStructures.RS_STRUCTURES.keySet());
            }
            // Not superflat overworld. Do normal behavior now
            else{
                for(Map.Entry<StructureFeature<?>, StructureConfig> structureFeatureEntry : RSStructures.RS_STRUCTURES.entrySet()){
                    boolean isWorldBlacklisted = GeneralUtils.isBlacklistedForWorld(serverWorld, Registry.STRUCTURE_FEATURE.getId(structureFeatureEntry.getKey()));
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
            ((StructuresConfigAccessor) serverWorld.getChunkManager().getChunkGenerator().getStructuresConfig()).repurposedstructures_setStructures(tempMap);
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
