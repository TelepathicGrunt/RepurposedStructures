package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.configs.RSDungeonsConfig.RSDungeonsConfigValues;
import com.telepathicgrunt.repurposedstructures.configs.RSMainConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSMainConfig.RSConfigValues;
import com.telepathicgrunt.repurposedstructures.configs.RSMineshaftsConfig.RSMineshaftsConfigValues;
import com.telepathicgrunt.repurposedstructures.configs.RSOutpostsConfig.RSOutpostsConfigValues;
import com.telepathicgrunt.repurposedstructures.configs.RSShipwrecksConfig.RSShipwrecksConfigValues;
import com.telepathicgrunt.repurposedstructures.configs.RSStrongholdsConfig.RSStrongholdsConfigValues;
import com.telepathicgrunt.repurposedstructures.configs.RSTemplesConfig.RSTemplesConfigValues;
import com.telepathicgrunt.repurposedstructures.configs.RSVillagesConfig.RSVillagesConfigValues;
import com.telepathicgrunt.repurposedstructures.configs.RSWellsConfig.RSWellsConfigValues;
import com.telepathicgrunt.repurposedstructures.misc.VillagerTradesModification;
import com.telepathicgrunt.repurposedstructures.modinit.*;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.MobSpawnerManager;
import net.minecraft.util.ResourceLocation;
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
	public static MobSpawnerManager mobSpawnerManager = null;
	public static boolean yungsBetterMineshaftIsNotOn = true;

	public RepurposedStructures()
	{
		// Register the setup method for modloading
		IEventBus forgeBus = MinecraftForge.EVENT_BUS;
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		forgeBus.addListener(this::biomeModification);
		forgeBus.addListener(this::registerDatapackListener);
		forgeBus.addListener(RSAddFeaturesAndStructures::addDimensionalSpacing);
		forgeBus.addListener(VillagerTradesModification::onVillagerTradesEvent);

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

		yungsBetterMineshaftIsNotOn = !ModList.get().isLoaded("bettermineshafts");
	}

	/*
	 * Here, we will use this to add our structures/features to all biomes.
	 */
	public void setup(final FMLCommonSetupEvent event)
	{
		event.enqueueWork(() -> {
			//Moved the methods bellow into enqueue to make sure they dont cause issues during registration - andrew
			RSConfiguredFeatures.registerConfiguredFeatures();
			RSStructures.registerStructures();
			RSConfiguredStructures.registerStructureFeatures();
			RSStructureTagMap.setupTags();
		});
	}

	public void registerDatapackListener(final AddReloadListenerEvent event) {
		//loads the RS specific json files for mob spawner chances
		RepurposedStructures.mobSpawnerManager = new MobSpawnerManager();
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

		if(isBiomeAllowed("mineshaft", event.getName(), allBiomeBlacklists))
			RSAddFeaturesAndStructures.addMineshafts(event);
		if(isBiomeAllowed("fortress", event.getName(), allBiomeBlacklists))
			RSAddFeaturesAndStructures.addJungleFortress(event);
		if(isBiomeAllowed("dungeon", event.getName(), allBiomeBlacklists))
			RSAddFeaturesAndStructures.addDungeons(event);
		if(isBiomeAllowed("well", event.getName(), allBiomeBlacklists))
			RSAddFeaturesAndStructures.addWells(event);
		if(isBiomeAllowed("swamp_tree", event.getName(), allBiomeBlacklists))
			RSAddFeaturesAndStructures.addSwampTreeFeatures(event);
		if(isBiomeAllowed("boulder", event.getName(), allBiomeBlacklists))
			RSAddFeaturesAndStructures.addBoulderFeatures(event);
		if(isBiomeAllowed("temple", event.getName(), allBiomeBlacklists))
			RSAddFeaturesAndStructures.addTemples(event);
		if(isBiomeAllowed("pyramid", event.getName(), allBiomeBlacklists))
			RSAddFeaturesAndStructures.addPyramids(event);
		if(isBiomeAllowed("igloo", event.getName(), allBiomeBlacklists))
			RSAddFeaturesAndStructures.addIgloos(event);
		if(isBiomeAllowed("outpost", event.getName(), allBiomeBlacklists))
			RSAddFeaturesAndStructures.addOutposts(event);
		if(isBiomeAllowed("shipwreck", event.getName(), allBiomeBlacklists))
			RSAddFeaturesAndStructures.addShipwrecks(event);
		if(isBiomeAllowed("village", event.getName(), allBiomeBlacklists))
			RSAddFeaturesAndStructures.addVillages(event);
		if(isBiomeAllowed("stronghold", event.getName(), allBiomeBlacklists))
			RSAddFeaturesAndStructures.addStrongholds(event);
		if(isBiomeAllowed("ruinedPortal", event.getName(), allBiomeBlacklists))
			RSAddFeaturesAndStructures.addRuinedPortals(event);
	}
    
    private static boolean isBiomeAllowed(String structureType, ResourceLocation biomeID, Map<String, List<String>> allBiomeBlacklists){
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

		allBiomeBlacklists.put("dungeon", Arrays.asList(RepurposedStructures.RSDungeonsConfig.blacklistedDungeonBiomes.get().replace(" ", "").split(",")));
		allBiomeBlacklists.put("boulder", Arrays.asList(RepurposedStructures.RSMainConfig.blacklistedBoulderBiomes.get().replace(" ", "").split(",")));
		allBiomeBlacklists.put("swamp_tree", Arrays.asList(RepurposedStructures.RSMainConfig.blacklistedSwampTreeBiomes.get().replace(" ", "").split(",")));
		allBiomeBlacklists.put("fortress", Arrays.asList(RepurposedStructures.RSMainConfig.blacklistedFortressBiomes.get().replace(" ", "").split(",")));
		allBiomeBlacklists.put("igloo", Arrays.asList(RepurposedStructures.RSMainConfig.blacklistedIglooBiomes.get().replace(" ", "").split(",")));
		allBiomeBlacklists.put("mineshaft", Arrays.asList(RepurposedStructures.RSMineshaftsConfig.blacklistedMineshaftBiomes.get().replace(" ", "").split(",")));
		allBiomeBlacklists.put("outpost", Arrays.asList(RepurposedStructures.RSOutpostsConfig.blacklistedOutpostBiomes.get().replace(" ", "").split(",")));
		allBiomeBlacklists.put("shipwreck", Arrays.asList(RepurposedStructures.RSShipwrecksConfig.blacklistedShipwreckBiomes.get().replace(" ", "").split(",")));
		allBiomeBlacklists.put("stronghold", Arrays.asList(RepurposedStructures.RSStrongholdsConfig.blacklistedStrongholdBiomes.get().replace(" ", "").split(",")));
		allBiomeBlacklists.put("temple", Arrays.asList(RepurposedStructures.RSTemplesConfig.blacklistedTempleBiomes.get().replace(" ", "").split(",")));
		allBiomeBlacklists.put("pyramid", Arrays.asList(RepurposedStructures.RSTemplesConfig.blacklistedPyramidBiomes.get().replace(" ", "").split(",")));
		allBiomeBlacklists.put("village", Arrays.asList(RepurposedStructures.RSVillagesConfig.blacklistedVillageBiomes.get().replace(" ", "").split(",")));
		allBiomeBlacklists.put("well", Arrays.asList(RepurposedStructures.RSWellsConfig.blacklistedWellBiomes.get().replace(" ", "").split(",")));
		allBiomeBlacklists.put("ruinedPortal", Arrays.asList(RepurposedStructures.RSMainConfig.blacklistedRuinedPortalsBiomes.get().replace(" ", "").split(",")));

		return allBiomeBlacklists;
	}
}
