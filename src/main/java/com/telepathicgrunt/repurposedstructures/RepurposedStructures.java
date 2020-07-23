package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.configs.*;
import com.telepathicgrunt.repurposedstructures.utils.LoadNbtBlock;
import com.telepathicgrunt.repurposedstructures.utils.MobSpawnerManager;
import com.telepathicgrunt.repurposedstructures.world.placements.RSPlacements;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@SuppressWarnings("deprecation")
public class RepurposedStructures implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "repurposed_structures";
    public static MobSpawnerManager mobSpawnerManager = null;

	public static RSAllConfig RSAllConfig = null;

    @Override
    public void onInitialize() {
        AutoConfig.register(RSAllConfig.class, Toml4jConfigSerializer::new);
        RSAllConfig = AutoConfig.getConfigHolder(RSAllConfig.class).getConfig();

        RSPlacements.registerPlacements();
        RSFeatures.registerFeatures();

        //LoadNbtBlock.instantiateNbtBlock();

        for (Biome biome : Registry.BIOME) {
            addFeaturesAndStructuresToBiomes(biome, Registry.BIOME.getId(biome));
        }
        RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> addFeaturesAndStructuresToBiomes(biome, identifier));
    }

    /*
     * Here, we will use this to add our structures/features to all biomes.
     */
    public static void addFeaturesAndStructuresToBiomes(Biome biome, Identifier biomeID) {
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
