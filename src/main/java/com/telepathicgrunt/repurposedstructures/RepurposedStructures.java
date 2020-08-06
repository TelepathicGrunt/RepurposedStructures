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
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.MobSpawnerManager;
import com.telepathicgrunt.repurposedstructures.world.placements.RSPlacements;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ConfigTracker;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.*;


@SuppressWarnings("deprecation")
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

	public RepurposedStructures()
	{
		// Register the setup method for modloading
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::setup);

		RSMainConfig = ConfigHelper.register(ModConfig.Type.COMMON, RSConfigValues::new, "repurposed_structures-common.toml");
		RSDungeonsConfig = ConfigHelper.register(ModConfig.Type.COMMON, RSDungeonsConfigValues::new, "repurposed_structures-dungeons.toml");
		RSMineshaftsConfig = ConfigHelper.register(ModConfig.Type.COMMON, RSMineshaftsConfigValues::new, "repurposed_structures-mineshafts.toml");
		RSStrongholdsConfig = ConfigHelper.register(ModConfig.Type.COMMON, RSStrongholdsConfigValues::new, "repurposed_structures-strongholds.toml");
		RSWellsConfig = ConfigHelper.register(ModConfig.Type.COMMON, RSWellsConfigValues::new, "repurposed_structures-wells.toml");
		RSShipwrecksConfig = ConfigHelper.register(ModConfig.Type.COMMON, RSShipwrecksConfigValues::new, "repurposed_structures-shipwrecks.toml");
		RSOutpostsConfig = ConfigHelper.register(ModConfig.Type.COMMON, RSOutpostsConfigValues::new, "repurposed_structures-outposts.toml");
		RSTemplesConfig = ConfigHelper.register(ModConfig.Type.COMMON, RSTemplesConfigValues::new, "repurposed_structures-temples.toml");
		RSVillagesConfig = ConfigHelper.register(ModConfig.Type.COMMON, RSVillagesConfigValues::new, "repurposed_structures-villages.toml");

	}

	/*
	 * Here, we will use this to add our structures/features to all biomes.
	 */
	public void setup(final FMLCommonSetupEvent event)
	{
		DeferredWorkQueue.runLater(RepurposedStructures::addFeaturesAndStructuresToBiomes);
		DeferredWorkQueue.runLater(VillagerTradesModification::addMapTrades);
	}
	
	
	private static void addFeaturesAndStructuresToBiomes()
	{
		//Gets blacklisted biome IDs for each structure type
		//Done here so the map can be garbage collected later
		Map<String, List<String>> allBiomeBlacklists = new HashMap<>();
		allBiomeBlacklists.put("dungeon", Arrays.asList(RSDungeonsConfig.blacklistedDungeonBiomes.get().split(",")));
		allBiomeBlacklists.put("boulder", Arrays.asList(RSMainConfig.blacklistedBoulderBiomes.get().split(",")));
		allBiomeBlacklists.put("swamp_tree", Arrays.asList(RSMainConfig.blacklistedSwampTreeBiomes.get().split(",")));
		allBiomeBlacklists.put("fortress", Arrays.asList(RSMainConfig.blacklistedFortressBiomes.get().split(",")));
		allBiomeBlacklists.put("igloo", Arrays.asList(RSMainConfig.blacklistedIglooBiomes.get().split(",")));
		allBiomeBlacklists.put("swamp", Arrays.asList(RSMainConfig.blacklistedSwampTreeBiomes.get().split(",")));
		allBiomeBlacklists.put("mineshaft", Arrays.asList(RSMineshaftsConfig.blacklistedMineshaftBiomes.get().split(",")));
		allBiomeBlacklists.put("outpost", Arrays.asList(RSOutpostsConfig.blacklistedOutpostBiomes.get().split(",")));
		allBiomeBlacklists.put("shipwreck", Arrays.asList(RSShipwrecksConfig.blacklistedShipwreckBiomes.get().split(",")));
		allBiomeBlacklists.put("stronghold", Arrays.asList(RSStrongholdsConfig.blacklistedStrongholdBiomes.get().split(",")));
		allBiomeBlacklists.put("temple", Arrays.asList(RSTemplesConfig.blacklistedTempleBiomes.get().split(",")));
		allBiomeBlacklists.put("pyramid", Arrays.asList(RSTemplesConfig.blacklistedPyramidBiomes.get().split(",")));
		allBiomeBlacklists.put("village", Arrays.asList(RSVillagesConfig.blacklistedVillageBiomes.get().split(",")));
		allBiomeBlacklists.put("well", Arrays.asList(RSWellsConfig.blacklistedWellBiomes.get().split(",")));

		for (Biome biome : ForgeRegistries.BIOMES)
		{
			addFeaturesAndStructuresToBiomes(biome, biome.getRegistryName(), allBiomeBlacklists);
		}

		RSFeatures.registerVillagePools();
	}
	
	
	/*
	 * You will use this to register anything for your mod. The most common things you will register are blocks, items,
	 * biomes, entities, features, and dimensions.
	 */
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{
		@SubscribeEvent
		public static void onRegisterFeatures(final RegistryEvent.Register<Feature<?>> event)
		{
			//load the configs for structure spacing and placements
			loadRSConfigs();
			RSFeatures.registerFeatures(event);
		}

		@SubscribeEvent
		public static void onRegisterPlacements(final RegistryEvent.Register<Placement<?>> event)
		{
			//load the configs for structure spacing and placements
			loadRSConfigs();
			RSPlacements.registerPlacements(event);
		}
	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
	public static class ForgeEvents {
		@SubscribeEvent
		public static void registerDatapackListener(final AddReloadListenerEvent event) {
			RepurposedStructures.mobSpawnerManager = new MobSpawnerManager();
			event.addListener(RepurposedStructures.mobSpawnerManager);
		}
	}

    /*
     * Here, we will use this to add our structures/features to all biomes.
     */
    public static void addFeaturesAndStructuresToBiomes(Biome biome, ResourceLocation biomeID, Map<String, List<String>> allBiomeBlacklists) {
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
			RSAddFeatures.addTemples(biome, biomeNamespace, biomePath);
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
    
    private static boolean isBiomeAllowed(String structureType, ResourceLocation biomeID, Map<String, List<String>> allBiomeBlacklists){
    	return allBiomeBlacklists.get(structureType).stream().noneMatch(blacklistedBiome -> blacklistedBiome.equals(biomeID.toString()));
	}


	/**
	 * Loads RS's configs as Forge won't load configs before registry events.
	 */
	private static void loadRSConfigs(){
		try {
			Field fld = ConfigTracker.class.getDeclaredField("configSets");
			fld.setAccessible(true);
			Class[] partypes = new Class[2];
			partypes[0] = ModConfig.class;
			partypes[1] = Path.class;
			Method method = ConfigTracker.class.getDeclaredMethod("openConfig", partypes);
			method.setAccessible(true);


			for(ModConfig modConfig : ((EnumMap<ModConfig.Type, Set<ModConfig>>)fld.get(ConfigTracker.INSTANCE)).get(ModConfig.Type.COMMON))
			{
				if(modConfig.getModId().equals(RepurposedStructures.MODID)){
					Object[] arglist = new Object[2];
					arglist[0] = modConfig;
					arglist[1] = FMLPaths.CONFIGDIR.get();
					method.invoke(ConfigTracker.INSTANCE, arglist);
				}
			}

			fld.setAccessible(false);
			method.setAccessible(false);
		}
		catch (Throwable e) {
			System.err.println(e);
		}
	}
}
