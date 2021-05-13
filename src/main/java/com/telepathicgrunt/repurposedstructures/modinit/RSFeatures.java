package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.features.*;
import com.telepathicgrunt.repurposedstructures.world.features.configs.NbtDungeonConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class RSFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, RepurposedStructures.MODID);

	public static final RegistryObject<Feature<NbtDungeonConfig>> BADLANDS_DUNGEONS = createFeature("dungeons_badlands", () -> new NbtDungeon(NbtDungeonConfig.CODEC));
	public static final RegistryObject<Feature<NbtDungeonConfig>> DARK_FOREST_DUNGEONS = createFeature("dungeons_dark_forest", () -> new NbtDungeon(NbtDungeonConfig.CODEC));
	public static final RegistryObject<Feature<NbtDungeonConfig>> DESERT_DUNGEONS = createFeature("dungeons_desert", () -> new NbtDungeon(NbtDungeonConfig.CODEC));
	public static final RegistryObject<Feature<NbtDungeonConfig>> END_DUNGEONS = createFeature("dungeons_end", () -> new NbtDungeon(NbtDungeonConfig.CODEC));
	public static final RegistryObject<Feature<NbtDungeonConfig>> NETHER_DUNGEONS = createFeature("dungeons_nether", () -> new NbtDungeon(NbtDungeonConfig.CODEC));
	public static final RegistryObject<Feature<NbtDungeonConfig>> SNOW_DUNGEONS = createFeature("dungeons_snow", () -> new NbtDungeon(NbtDungeonConfig.CODEC));
	public static final RegistryObject<Feature<NbtDungeonConfig>> ICY_DUNGEONS = createFeature("dungeons_icy", () -> new NbtDungeon(NbtDungeonConfig.CODEC));
	public static final RegistryObject<Feature<NbtDungeonConfig>> SWAMP_DUNGEONS = createFeature("dungeons_swamp", () -> new NbtDungeon(NbtDungeonConfig.CODEC));
	public static final RegistryObject<Feature<NbtDungeonConfig>> MUSHROOM_DUNGEONS = createFeature("dungeons_mushroom", () -> new NbtDungeon(NbtDungeonConfig.CODEC));
	public static final RegistryObject<Feature<NbtDungeonConfig>> JUNGLE_DUNGEONS = createFeature("dungeons_jungle", () -> new NbtDungeon(NbtDungeonConfig.CODEC));
	public static final RegistryObject<Feature<NbtDungeonConfig>> OCEAN_DUNGEONS = createFeature("dungeons_ocean", () -> new NbtDungeon(NbtDungeonConfig.CODEC));
	
	public static final RegistryObject<Feature<NoFeatureConfig>> BADLANDS_WELL = createFeature("well_badlands", () -> new WellBadlands(NoFeatureConfig.CODEC));
	public static final RegistryObject<Feature<NoFeatureConfig>> NETHER_WELL = createFeature("well_nether", () -> new WellNether(NoFeatureConfig.CODEC));
	public static final RegistryObject<Feature<NoFeatureConfig>> SNOW_WELL = createFeature("well_snow", () -> new WellSnow(NoFeatureConfig.CODEC));
  	public static final RegistryObject<Feature<NoFeatureConfig>> MOSSY_STONE_WELL = createFeature("well_mossy_stone", () -> new WellMossyStone(NoFeatureConfig.CODEC));
  	public static final RegistryObject<Feature<NoFeatureConfig>> FOREST_WELL = createFeature("well_forest", () -> new WellForest(NoFeatureConfig.CODEC));

  	public static final RegistryObject<Feature<NoFeatureConfig>> SHORT_VINES = createFeature("short_vines", () -> new VinesShort(NoFeatureConfig.CODEC));
  	public static final RegistryObject<Feature<NoFeatureConfig>> SWAMP_VILLAGE_VINES = createFeature("swamp_village_vines", () -> new SwampVillageVines(NoFeatureConfig.CODEC));
  	public static final RegistryObject<Feature<NoFeatureConfig>> JUNGLE_STRUCTURES_VINES = createFeature("jungle_structures_vines", () -> new JungleStructuresVines(NoFeatureConfig.CODEC));
  	public static final RegistryObject<Feature<NoFeatureConfig>> FORTRESS_BREAKAGE = createFeature("fortress_breakage", () -> new FortressBreakage(NoFeatureConfig.CODEC));
	public static final RegistryObject<Feature<NoFeatureConfig>> STRONGHOLD_CHAINS = createFeature("stronghold_chains", () -> new StrongholdChains(NoFeatureConfig.CODEC));

	public static final RegistryObject<Feature<NoFeatureConfig>> WITHER_SKELETON_WITH_BOW = createFeature("wither_skeleton_with_bow", () -> new WitherSkeletonWithBow(NoFeatureConfig.CODEC));
	public static final RegistryObject<Feature<NoFeatureConfig>> SHULKER_MOB = createFeature("shulker_mob", () -> new ShulkerMob(NoFeatureConfig.CODEC));

	private static <F extends Feature<?>> RegistryObject<F> createFeature(String name, Supplier<F> feature)
    {
		return FEATURES.register(name, feature);
	}
}
