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
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ConfigTracker;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
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
	public static boolean yungsBetterMineshaftIsNotOn = true;

	public RepurposedStructures()
	{
		// Register the setup method for modloading
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::setup);
		initialize();
	}

	public static void initialize() {
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
		DeferredWorkQueue.runLater(VillagerTradesModification::addMapTrades);
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
			RSFeatures.registerFeatures();
			RSStructures.registerStructures();
			RSConfiguredFeatures.registerConfiguredFeatures();
			RSConfiguredStructures.registerStructureFeatures();
		}

		@SubscribeEvent
		public static void onRegisterPlacements(final RegistryEvent.Register<Placement<?>> event)
		{
			//load the configs for structure spacing and placements
			loadRSConfigs();
			RSPlacements.registerPlacements();
		}
	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
	public static class ForgeEvents {
		@SubscribeEvent
		public static void registerDatapackListener(final AddReloadListenerEvent event) {
			//loads the RS specific json files for mob spawner chances
			RepurposedStructures.mobSpawnerManager = new MobSpawnerManager();
			event.addListener(RepurposedStructures.mobSpawnerManager);
		}

		@SubscribeEvent
		public static void biomeModification(final BiomeLoadingEvent event) {
			//Gets blacklisted biome IDs for each structure type
			//Done here so the map can be garbage collected later
			Map<String, List<String>> allBiomeBlacklists = RepurposedStructures.getBiomeBlacklists();
			
			//Add our structures and features
			RepurposedStructures.addFeaturesAndStructuresToBiomes(
					event, // Biome
					allBiomeBlacklists); // Blacklists
		}

		@SubscribeEvent
		public static void addDimensionalSpacing(final WorldEvent.Load event) {
			//add our structure spacing to all chunkgenerators including modded one and datapack ones.
			List<String> dimensionBlacklist = Arrays.asList(RepurposedStructures.RSMainConfig.blacklistedDimensions.get().split(","));

			if (event.getWorld() instanceof ServerWorld){
				ServerWorld serverWorld = (ServerWorld) event.getWorld();
				Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkProvider().generator.getStructuresConfig().getStructures());

				if(dimensionBlacklist.stream().anyMatch(blacklist -> blacklist.equals((serverWorld.getRegistryKey().getValue().toString())))) {
					// make absolutely sure dimension cannot spawn RS structures
					tempMap.keySet().removeAll(RSStructures.RS_STRUCTURES.keySet());
				}
				else{
					// make absolutely sure dimension can spawn RS structures
					tempMap.putAll(RSStructures.RS_STRUCTURES);
				}
				serverWorld.getChunkProvider().generator.getStructuresConfig().structures = tempMap;

				// Load up the nbt files for several structures at startup instead of during worldgen.
				for(ResourceLocation identifier : RSStructures.RS_STRUCTURE_START_PIECES){
					JigsawPattern structurePool = serverWorld.getRegistryManager().get(Registry.TEMPLATE_POOL_WORLDGEN).getOrDefault(identifier);
					if(structurePool != null){
						List<JigsawPiece> elements = structurePool.getShuffledPieces(new Random());
						for(JigsawPiece element: elements){
							// This loads the structure piece to nbt
							element.getBoundingBox(serverWorld.getStructureTemplateManager(), new BlockPos(0,0,0), Rotation.NONE);
						}
					}
				}
			}
		}
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

		allBiomeBlacklists.put("dungeon", Arrays.asList(RepurposedStructures.RSDungeonsConfig.blacklistedDungeonBiomes.get().split(",")));
		allBiomeBlacklists.put("boulder", Arrays.asList(RepurposedStructures.RSMainConfig.blacklistedBoulderBiomes.get().split(",")));
		allBiomeBlacklists.put("swamp_tree", Arrays.asList(RepurposedStructures.RSMainConfig.blacklistedSwampTreeBiomes.get().split(",")));
		allBiomeBlacklists.put("fortress", Arrays.asList(RepurposedStructures.RSMainConfig.blacklistedFortressBiomes.get().split(",")));
		allBiomeBlacklists.put("igloo", Arrays.asList(RepurposedStructures.RSMainConfig.blacklistedIglooBiomes.get().split(",")));
		allBiomeBlacklists.put("mineshaft", Arrays.asList(RepurposedStructures.RSMineshaftsConfig.blacklistedMineshaftBiomes.get().split(",")));
		allBiomeBlacklists.put("outpost", Arrays.asList(RepurposedStructures.RSOutpostsConfig.blacklistedOutpostBiomes.get().split(",")));
		allBiomeBlacklists.put("shipwreck", Arrays.asList(RepurposedStructures.RSShipwrecksConfig.blacklistedShipwreckBiomes.get().split(",")));
		allBiomeBlacklists.put("stronghold", Arrays.asList(RepurposedStructures.RSStrongholdsConfig.blacklistedStrongholdBiomes.get().split(",")));
		allBiomeBlacklists.put("temple", Arrays.asList(RepurposedStructures.RSTemplesConfig.blacklistedTempleBiomes.get().split(",")));
		allBiomeBlacklists.put("pyramid", Arrays.asList(RepurposedStructures.RSTemplesConfig.blacklistedPyramidBiomes.get().split(",")));
		allBiomeBlacklists.put("village", Arrays.asList(RepurposedStructures.RSVillagesConfig.blacklistedVillageBiomes.get().split(",")));
		allBiomeBlacklists.put("well", Arrays.asList(RepurposedStructures.RSWellsConfig.blacklistedWellBiomes.get().split(",")));

		return allBiomeBlacklists;
	}
}
