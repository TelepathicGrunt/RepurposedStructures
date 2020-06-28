package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.configs.*;
import com.telepathicgrunt.repurposedstructures.world.features.RSFeatures;
import com.telepathicgrunt.repurposedstructures.world.placements.RSPlacements;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@SuppressWarnings("deprecation")
public class RepurposedStructures implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "repurposed_structures";

	public static RSDungeonsConfig RSDungeonsConfig = null;
	public static RSMainConfig RSMainConfig = null;
	public static RSMineshaftsConfig RSMineshaftsConfig = null;
	public static RSWellsConfig RSWellsConfig = null;
    public static RSVillagesConfig RSVillagesConfig = null;
    public static RSStrongholdsConfig RSStrongholdsConfig = null;

    @Override
    public void onInitialize() {
        AutoConfig.register(RSAllConfig.class, Toml4jConfigSerializer::new);
        AutoConfig.register(RSDungeonsConfig.class, Toml4jConfigSerializer::new);
        AutoConfig.register(RSMainConfig.class, Toml4jConfigSerializer::new);
        AutoConfig.register(RSMineshaftsConfig.class, Toml4jConfigSerializer::new);
        AutoConfig.register(RSWellsConfig.class, Toml4jConfigSerializer::new);
        AutoConfig.register(RSVillagesConfig.class, Toml4jConfigSerializer::new);
        AutoConfig.register(RSStrongholdsConfig.class, Toml4jConfigSerializer::new);

		RSDungeonsConfig = AutoConfig.getConfigHolder(RSDungeonsConfig.class).getConfig();
		RSMainConfig = AutoConfig.getConfigHolder(RSMainConfig.class).getConfig();
		RSMineshaftsConfig = AutoConfig.getConfigHolder(RSMineshaftsConfig.class).getConfig();
		RSWellsConfig = AutoConfig.getConfigHolder(RSWellsConfig.class).getConfig();
        RSVillagesConfig = AutoConfig.getConfigHolder(RSVillagesConfig.class).getConfig();
        RSStrongholdsConfig = AutoConfig.getConfigHolder(RSStrongholdsConfig.class).getConfig();


        RSPlacements.registerPlacements();
        RSFeatures.registerFeatures();
        RepurposedStructures.addFeaturesAndStructuresToBiomes();
    }


    /*
     * Here, we will use this to add our structures/features to all biomes.
     */
    private static void addFeaturesAndStructuresToBiomes() {
        RSFeatures.registerVillagePools();

        for (Biome biome : Registry.BIOME) {
            Identifier biomeID = Registry.BIOME.getId(biome);
            String biomeNamespace = biomeID.getNamespace();
            String biomePath = biomeID.getPath();

            RSAddFeatures.addMineshafts(biome, biomeNamespace, biomePath);
            RSAddFeatures.addJungleFortress(biome, biomeNamespace, biomePath);
            RSAddFeatures.addDungeons(biome, biomeNamespace, biomePath);
            RSAddFeatures.addWells(biome, biomeNamespace, biomePath);
            RSAddFeatures.addMiscFeatures(biome, biomeNamespace, biomePath);
            RSAddFeatures.addNetherTemple(biome, biomeNamespace, biomePath);
            RSAddFeatures.addBadlandsTemple(biome, biomeNamespace, biomePath);
            RSAddFeatures.addIgloos(biome, biomeNamespace, biomePath);
            RSAddFeatures.addVillages(biome, biomeNamespace, biomePath);
            RSAddFeatures.addStrongholds(biome, biomeNamespace, biomePath);

        }
    }
}
