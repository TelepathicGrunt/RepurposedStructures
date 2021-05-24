package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.features.*;
import com.telepathicgrunt.repurposedstructures.world.features.configs.*;
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

	public static final RegistryObject<Feature<NoFeatureConfig>> WITHER_SKELETON_WITH_BOW = createFeature("wither_skeleton_with_bow", () -> new WitherSkeletonWithBow(NoFeatureConfig.CODEC));
	public static final RegistryObject<Feature<NoFeatureConfig>> SHULKER_MOB = createFeature("shulker_mob", () -> new ShulkerMob(NoFeatureConfig.CODEC));
	public static final RegistryObject<Feature<NoFeatureConfig>> DROWNED_WITH_ARMOR = createFeature("drowned_with_armor", DrownedWithArmor::new);

	public static final RegistryObject<Feature<NoFeatureConfig>> POST_PROCESS_CONNECTING_BLOCKS = createFeature("post_process_connecting_blocks", StructurePostProcessConnectiveBlocks::new);
	public static final RegistryObject<Feature<StructureTargetChanceConfig>> STRUCTURE_BREAKAGE = createFeature("structure_breakage", () -> new StructureBreakage(StructureTargetChanceConfig.CODEC));
	public static final RegistryObject<Feature<StructureTargetConfig>> STRUCTURE_CHAINS = createFeature("structure_chains", () -> new StructureChains(StructureTargetConfig.CODEC));
	public static final RegistryObject<Feature<StructureTargetConfig>> STRUCTURE_CHORUS = createFeature("structure_chorus", () -> new StructureChorus(StructureTargetConfig.CODEC));
	public static final RegistryObject<Feature<StructureTargetAndLengthConfig>> STRUCTURE_CRIMSON_PLANTS = createFeature("structure_crimson_plants", () -> new StructureCrimsonPlants(StructureTargetAndLengthConfig.CODEC));
	public static final RegistryObject<Feature<StructureTargetConfig>> STRUCTURE_FIRE = createFeature("structure_fire", () -> new StructureFire(StructureTargetConfig.CODEC));
	public static final RegistryObject<Feature<StructureTargetConfig>> STRUCTURE_NETHERWART = createFeature("structure_netherwart", () -> new StructureNetherwart(StructureTargetConfig.CODEC));
	public static final RegistryObject<Feature<StructureTargetConfig>> STRUCTURE_SEAGRASS = createFeature("structure_seagrass", () -> new StructureSeagrass(StructureTargetConfig.CODEC));
	public static final RegistryObject<Feature<StructureTargetLengthRangeConfig>> STRUCTURE_VINES = createFeature("structure_vines", () -> new StructureVine(StructureTargetLengthRangeConfig.CODEC));
	public static final RegistryObject<Feature<StructureTargetAndLengthConfig>> STRUCTURE_VINES_AND_LEAVES = createFeature("structure_vines_and_leaves", () -> new StructureVineAndLeaves(StructureTargetAndLengthConfig.CODEC));
	public static final RegistryObject<Feature<StructureTargetAndLengthConfig>> STRUCTURE_WARPED_PLANTS = createFeature("structure_warped_plants", () -> new StructureWarpedPlants(StructureTargetAndLengthConfig.CODEC));
	public static final RegistryObject<Feature<StructureTargetAndLengthConfig>> STRUCTURE_VINE_BREAKAGE = createFeature("structure_vine_breakage", () -> new StructureVineBreakage(StructureTargetAndLengthConfig.CODEC));

	private static <F extends Feature<?>> RegistryObject<F> createFeature(String name, Supplier<F> feature)
    {
		return FEATURES.register(name, feature);
	}
}
