package com.telepathicgrunt.repurposedstructures;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.telepathicgrunt.repurposedstructures.RSConfig.RSConfigValues;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.world.features.RSFeatures;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;


@SuppressWarnings("deprecation")
@Mod(RepurposedStructures.MODID)
public class RepurposedStructures
{
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "repurposed_structures";
	public static RSConfigValues RSConfig = null;


	public RepurposedStructures()
	{
		// Register the setup method for modloading
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::setup);
		
		RSConfig = ConfigHelper.register(ModConfig.Type.COMMON, RSConfig.RSConfigValues::new);
	}


	/*
	 * Here, we will use this to add our structures/features to all biomes.
	 */
	public void setup(final FMLCommonSetupEvent event)
	{
		DeferredWorkQueue.runLater(RepurposedStructures::addFeaturesAndStructuresToBiomes);
	}
	
	private static void addFeaturesAndStructuresToBiomes()
	{
		for (Biome biome : ForgeRegistries.BIOMES)
		{
			String biomeNamespace = biome.getRegistryName().getNamespace();
			String biomePath = biome.getRegistryName().getPath();
			
			RSAddFeatures.addMineshafts(biome, biomeNamespace, biomePath);
			RSAddFeatures.addJungleFortress(biome, biomeNamespace, biomePath);
			RSAddFeatures.addDungeons(biome, biomeNamespace, biomePath);
			RSAddFeatures.addWells(biome, biomeNamespace, biomePath);
			RSAddFeatures.addMiscFeatures(biome, biomeNamespace, biomePath);
			RSAddFeatures.addStronghold(biome, biomeNamespace, biomePath);
			RSAddFeatures.addNetherTemple(biome, biomeNamespace, biomePath);
			RSAddFeatures.addBadlandsTemple(biome, biomeNamespace, biomePath);
		}
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
			RSFeatures.registerFeatures(event);
		}
	}
}
