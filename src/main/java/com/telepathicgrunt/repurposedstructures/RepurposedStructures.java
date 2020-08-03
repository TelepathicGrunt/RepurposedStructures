package com.telepathicgrunt.repurposedstructures;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.toml.TomlFormat;
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
	private static final String[] CONFIG_NAMES = new String[]{
			"\\repurposed_structures-common.toml",
			"\\repurposed_structures-dungeons.toml",
			"\\repurposed_structures-mineshafts.toml",
			"\\repurposed_structures-strongholds.toml",
			"\\repurposed_structures-wells.toml",
			"\\repurposed_structures-shipwrecks.toml",
			"\\repurposed_structures-outposts.toml",
			"\\repurposed_structures-temples.toml",
			"\\repurposed_structures-villages.toml"
	};

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
		for (Biome biome : ForgeRegistries.BIOMES)
		{
			addFeaturesAndStructuresToBiomes(biome, biome.getRegistryName());
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
			ConfigTracker.INSTANCE.loadConfigs(ModConfig.Type.COMMON, FMLPaths.CONFIGDIR.get());
			int spacing = RepurposedStructures.RSTemplesConfig.netherWastelandTempleSpawnrate.get();
			RSFeatures.registerFeatures(event);
		}

		@SubscribeEvent
		public static void onRegisterPlacements(final RegistryEvent.Register<Placement<?>> event)
		{
			//load the configs for structure spacing and placements
			ConfigTracker.INSTANCE.loadConfigs(ModConfig.Type.COMMON, FMLPaths.CONFIGDIR.get());
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
    public static void addFeaturesAndStructuresToBiomes(Biome biome, ResourceLocation biomeID) {
        String biomeNamespace = biomeID.getNamespace();
        String biomePath = biomeID.getPath();

        RSAddFeatures.addMineshafts(biome, biomeNamespace, biomePath);
        RSAddFeatures.addJungleFortress(biome, biomeNamespace, biomePath);
        RSAddFeatures.addDungeons(biome, biomeNamespace, biomePath);
        RSAddFeatures.addWells(biome, biomeNamespace, biomePath);
        RSAddFeatures.addMiscFeatures(biome, biomeNamespace, biomePath);
        RSAddFeatures.addTemplesAndPyramids(biome, biomeNamespace, biomePath);
        RSAddFeatures.addIgloos(biome, biomeNamespace, biomePath);
        RSAddFeatures.addOutposts(biome, biomeNamespace, biomePath);
        RSAddFeatures.addShipwrecks(biome, biomeNamespace, biomePath);
        RSAddFeatures.addVillages(biome, biomeNamespace, biomePath);
        RSAddFeatures.addStrongholds(biome, biomeNamespace, biomePath);
    }
}
