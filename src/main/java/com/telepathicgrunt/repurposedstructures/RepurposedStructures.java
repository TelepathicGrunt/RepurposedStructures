package com.telepathicgrunt.repurposedstructures;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.telepathicgrunt.repurposedstructures.world.features.RSFeatures;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;


@Mod(RepurposedStructures.MODID)
public class RepurposedStructures
{
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "repurposed_structures";


	public RepurposedStructures()
	{
		// Register the setup method for modloading
		ModLoadingContext modLoadingContext = ModLoadingContext.get();
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::setup);
		modEventBus.addListener(this::modConfig);

		modLoadingContext.registerConfig(ModConfig.Type.COMMON, RSConfig.SERVER_SPEC);
	}


	/*
	 * Here, we will use this to add our structures/features to all biomes.
	 */
	public void setup(final FMLCommonSetupEvent event)
	{
		for (Biome biome : ForgeRegistries.BIOMES)
		{
			String biomeNamespace = biome.getRegistryName().getNamespace();
			String biomePath = biome.getRegistryName().getPath();
			
			RSAddFeatures.addMineshafts(biome, biomeNamespace, biomePath);
			RSAddFeatures.addJungleFortress(biome, biomeNamespace, biomePath);
			RSAddFeatures.addDungeons(biome, biomeNamespace, biomePath);
			RSAddFeatures.addMiscFeatures(biome, biomeNamespace, biomePath);
			RSAddFeatures.addStronghold(biome, biomeNamespace, biomePath);
		}
	}
	
	
	public void modConfig(final ModConfig.ModConfigEvent event)
	{
		ModConfig config = event.getConfig();
		if (config.getSpec() == RSConfig.SERVER_SPEC)
			RSConfig.refreshServer();
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
