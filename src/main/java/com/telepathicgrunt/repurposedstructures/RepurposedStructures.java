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

	public static RSAllConfig RSAllConfig = null;

//    static Block LOAD_NBT_BLOCK = new LoadNbtBlock();
//    public static final Item LOAD_NBT_ITEM = new BlockItem(LOAD_NBT_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE));

    @Override
    public void onInitialize() {
        AutoConfig.register(RSAllConfig.class, Toml4jConfigSerializer::new);
        RSAllConfig = AutoConfig.getConfigHolder(RSAllConfig.class).getConfig();

        RSPlacements.registerPlacements();
        RSFeatures.registerFeatures();
        RepurposedStructures.addFeaturesAndStructuresToBiomes();

//        Registry.register(Registry.BLOCK, new Identifier(MODID, "load_nbt_block"), LOAD_NBT_BLOCK);
//        Registry.register(Registry.ITEM, new Identifier(MODID, "load_nbt_block"), LOAD_NBT_ITEM);
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
            RSAddFeatures.addTemples(biome, biomeNamespace, biomePath);
            RSAddFeatures.addIgloos(biome, biomeNamespace, biomePath);
            RSAddFeatures.addVillages(biome, biomeNamespace, biomePath);
            RSAddFeatures.addStrongholds(biome, biomeNamespace, biomePath);

        }
    }
}
