package com.telepathicgrunt.repurposedstructures;

import com.google.common.collect.ImmutableMap;
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
import com.telepathicgrunt.repurposedstructures.configs.RSWitchHutsConfig;
import com.telepathicgrunt.repurposedstructures.misc.MobMapTrades;
import com.telepathicgrunt.repurposedstructures.modinit.*;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.MobSpawnerManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
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


@Mod(RepurposedStructures.MODID)
public class RepurposedStructures
{
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
	public static boolean yungsBetterStrongholdsIsNotOn = true;

	public RepurposedStructures()
	{
		// Register the setup method for modloading
		IEventBus forgeBus = MinecraftForge.EVENT_BUS;
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		forgeBus.addListener(this::biomeModification);
		forgeBus.addListener(this::registerDatapackListener);
		forgeBus.addListener(RSAddFeaturesAndStructures::addDimensionalSpacing);
		forgeBus.addListener(MobMapTrades::onVillagerTradesEvent);

		modEventBus.addListener(this::setup);
		RSFeatures.FEATURES.register(modEventBus);
		RSStructures.STRUCTURE_FEATURES.register(modEventBus);
		RSPlacements.DECORATORS.register(modEventBus);

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

		yungsBetterMineshaftIsNotOn = !ModList.get().isLoaded("bettermineshafts");
		yungsBetterStrongholdsIsNotOn = !ModList.get().isLoaded("betterstrongholds");
	}

	/*
	 * Here, we will use this to add our structures/features to all biomes.
	 */
	public void setup(final FMLCommonSetupEvent event)
	{
		event.enqueueWork(() -> {
			//Moved the methods below into enqueue to make sure they dont cause issues during registration - andrew
			RSConfiguredFeatures.registerConfiguredFeatures();
			RSProcessors.registerProcessors();
			RSStructures.setupStructures();
			RSConfiguredStructures.registerStructureFeatures();
			RSStructureTagMap.setupTags();

			// Workaround for Terraforged
			WorldGenRegistries.CHUNK_GENERATOR_SETTINGS.getEntries().forEach(settings -> {
				Map<Structure<?>, StructureSeparationSettings> structureMap = settings.getValue().getStructuresConfig().getStructures();
				if(structureMap instanceof ImmutableMap){
					Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(structureMap);
					tempMap.putAll(RSStructures.RS_STRUCTURES);
					settings.getValue().getStructuresConfig().structures = tempMap;
				}
				else{
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
		Map<String, List<String>> allBiomeBlacklists = RepurposedStructures.getBiomeBlacklists();

		//Add our structures and features
		RepurposedStructures.addFeaturesAndStructuresToBiomes(event, allBiomeBlacklists);
	}

    /*
     * Here, we will use this to add our structures/features to all biomes.
     */
	public static void addFeaturesAndStructuresToBiomes(BiomeLoadingEvent event, Map<String, List<String>> allBiomeBlacklists) {

		if(isBiomeAllowed("mineshafts", event.getName(), allBiomeBlacklists))
			RSAddFeaturesAndStructures.addMineshafts(event);
		if(isBiomeAllowed("fortresses", event.getName(), allBiomeBlacklists))
			RSAddFeaturesAndStructures.addJungleFortress(event);
		if(isBiomeAllowed("dungeons", event.getName(), allBiomeBlacklists))
			RSAddFeaturesAndStructures.addDungeons(event);
		if(isBiomeAllowed("wells", event.getName(), allBiomeBlacklists))
			RSAddFeaturesAndStructures.addWells(event);
		if(isBiomeAllowed("boulders", event.getName(), allBiomeBlacklists))
			RSAddFeaturesAndStructures.addBoulderFeatures(event);
		if(isBiomeAllowed("temples", event.getName(), allBiomeBlacklists))
			RSAddFeaturesAndStructures.addTemples(event);
		if(isBiomeAllowed("pyramids", event.getName(), allBiomeBlacklists))
			RSAddFeaturesAndStructures.addPyramids(event);
		if(isBiomeAllowed("igloos", event.getName(), allBiomeBlacklists))
			RSAddFeaturesAndStructures.addIgloos(event);
		if(isBiomeAllowed("outposts", event.getName(), allBiomeBlacklists))
			RSAddFeaturesAndStructures.addOutposts(event);
		if(isBiomeAllowed("shipwrecks", event.getName(), allBiomeBlacklists))
			RSAddFeaturesAndStructures.addShipwrecks(event);
		if(isBiomeAllowed("villages", event.getName(), allBiomeBlacklists))
			RSAddFeaturesAndStructures.addVillages(event);
		if(isBiomeAllowed("strongholds", event.getName(), allBiomeBlacklists))
			RSAddFeaturesAndStructures.addStrongholds(event);
		if(isBiomeAllowed("ruinedPortals", event.getName(), allBiomeBlacklists))
			RSAddFeaturesAndStructures.addRuinedPortals(event);
		if(isBiomeAllowed("ruins", event.getName(), allBiomeBlacklists))
			RSAddFeaturesAndStructures.addRuins(event);
		if(isBiomeAllowed("cities", event.getName(), allBiomeBlacklists))
			RSAddFeaturesAndStructures.addCities(event);
		if(isBiomeAllowed("mansions", event.getName(), allBiomeBlacklists))
			RSAddFeaturesAndStructures.addMansions(event);
		if(isBiomeAllowed("witch_huts", event.getName(), allBiomeBlacklists))
			RSAddFeaturesAndStructures.addWitchHuts(event);
	}
    
    private static boolean isBiomeAllowed(String structureType, ResourceLocation biomeID, Map<String, List<String>> allBiomeBlacklists){
    	return allBiomeBlacklists.get(structureType).stream().noneMatch(blacklistedBiome -> blacklistedBiome.equals(biomeID.toString()));
	}

	/**
	 * Grabs and parses the Biome blacklist from configs and stores it into
	 * a map of structure/feature type to their specific blacklist.
	 *
	 * @return - A map of structure/feature type to their biome blacklist
	 */
	public static Map<String, List<String>> getBiomeBlacklists(){
		Map<String, List<String>> allBiomeBlacklists = new HashMap<>();

		allBiomeBlacklists.put("dungeons", Arrays.asList(RepurposedStructures.RSDungeonsConfig.blacklistedDungeonBiomes.get().replace(" ", "").split(",")));
		allBiomeBlacklists.put("boulders", Arrays.asList(RepurposedStructures.RSMainConfig.blacklistedBoulderBiomes.get().replace(" ", "").split(",")));
		allBiomeBlacklists.put("fortresses", Arrays.asList(RepurposedStructures.RSMainConfig.blacklistedFortressBiomes.get().replace(" ", "").split(",")));
		allBiomeBlacklists.put("igloos", Arrays.asList(RepurposedStructures.RSMainConfig.blacklistedIglooBiomes.get().replace(" ", "").split(",")));
		allBiomeBlacklists.put("mineshafts", Arrays.asList(RepurposedStructures.RSMineshaftsConfig.blacklistedMineshaftBiomes.get().replace(" ", "").split(",")));
		allBiomeBlacklists.put("outposts", Arrays.asList(RepurposedStructures.RSOutpostsConfig.blacklistedOutpostBiomes.get().replace(" ", "").split(",")));
		allBiomeBlacklists.put("shipwrecks", Arrays.asList(RepurposedStructures.RSShipwrecksConfig.blacklistedShipwreckBiomes.get().replace(" ", "").split(",")));
		allBiomeBlacklists.put("strongholds", Arrays.asList(RepurposedStructures.RSStrongholdsConfig.blacklistedStrongholdBiomes.get().replace(" ", "").split(",")));
		allBiomeBlacklists.put("temples", Arrays.asList(RepurposedStructures.RSTemplesConfig.blacklistedTempleBiomes.get().replace(" ", "").split(",")));
		allBiomeBlacklists.put("pyramids", Arrays.asList(RepurposedStructures.RSTemplesConfig.blacklistedPyramidBiomes.get().replace(" ", "").split(",")));
		allBiomeBlacklists.put("villages", Arrays.asList(RepurposedStructures.RSVillagesConfig.blacklistedVillageBiomes.get().replace(" ", "").split(",")));
		allBiomeBlacklists.put("wells", Arrays.asList(RepurposedStructures.RSWellsConfig.blacklistedWellBiomes.get().replace(" ", "").split(",")));
		allBiomeBlacklists.put("ruinedPortals", Arrays.asList(RepurposedStructures.RSMainConfig.blacklistedRuinedPortalsBiomes.get().replace(" ", "").split(",")));
		allBiomeBlacklists.put("ruins", Arrays.asList(RepurposedStructures.RSMainConfig.blacklistedRuinsBiomes.get().replace(" ", "").split(",")));
		allBiomeBlacklists.put("cities", Arrays.asList(RepurposedStructures.RSMainConfig.blacklistedCitiesBiomes.get().replace(" ", "").split(",")));
		allBiomeBlacklists.put("mansions", Arrays.asList(RepurposedStructures.RSMansionsConfig.blacklistedMansionBiomes.get().replace(" ", "").split(",")));
		allBiomeBlacklists.put("witch_huts", Arrays.asList(RepurposedStructures.RSWitchHutsConfig.blacklistedWitchHutBiomes.get().replace(" ", "").split(",")));

		return allBiomeBlacklists;
	}
}
