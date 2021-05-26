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

	public static final RegistryObject<Feature<NbtDungeonConfig>> BADLANDS_DUNGEONS = FEATURES.register("dungeons_badlands", () -> new NbtDungeon(NbtDungeonConfig.CODEC));
	public static final RegistryObject<Feature<NbtDungeonConfig>> DARK_FOREST_DUNGEONS = FEATURES.register("dungeons_dark_forest", () -> new NbtDungeon(NbtDungeonConfig.CODEC));
	public static final RegistryObject<Feature<NbtDungeonConfig>> DESERT_DUNGEONS = FEATURES.register("dungeons_desert", () -> new NbtDungeon(NbtDungeonConfig.CODEC));
	public static final RegistryObject<Feature<NbtDungeonConfig>> END_DUNGEONS = FEATURES.register("dungeons_end", () -> new NbtDungeon(NbtDungeonConfig.CODEC));
	public static final RegistryObject<Feature<NbtDungeonConfig>> NETHER_DUNGEONS = FEATURES.register("dungeons_nether", () -> new NbtDungeon(NbtDungeonConfig.CODEC));
	public static final RegistryObject<Feature<NbtDungeonConfig>> SNOW_DUNGEONS = FEATURES.register("dungeons_snow", () -> new NbtDungeon(NbtDungeonConfig.CODEC));
	public static final RegistryObject<Feature<NbtDungeonConfig>> ICY_DUNGEONS = FEATURES.register("dungeons_icy", () -> new NbtDungeon(NbtDungeonConfig.CODEC));
	public static final RegistryObject<Feature<NbtDungeonConfig>> SWAMP_DUNGEONS = FEATURES.register("dungeons_swamp", () -> new NbtDungeon(NbtDungeonConfig.CODEC));
	public static final RegistryObject<Feature<NbtDungeonConfig>> MUSHROOM_DUNGEONS = FEATURES.register("dungeons_mushroom", () -> new NbtDungeon(NbtDungeonConfig.CODEC));
	public static final RegistryObject<Feature<NbtDungeonConfig>> JUNGLE_DUNGEONS = FEATURES.register("dungeons_jungle", () -> new NbtDungeon(NbtDungeonConfig.CODEC));
	public static final RegistryObject<Feature<NbtDungeonConfig>> OCEAN_DUNGEONS = FEATURES.register("dungeons_ocean", () -> new NbtDungeon(NbtDungeonConfig.CODEC));
	
	public static final RegistryObject<Feature<NoFeatureConfig>> BADLANDS_WELL = FEATURES.register("well_badlands", () -> new WellBadlands(NoFeatureConfig.CODEC));
	public static final RegistryObject<Feature<NoFeatureConfig>> NETHER_WELL = FEATURES.register("well_nether", () -> new WellNether(NoFeatureConfig.CODEC));
	public static final RegistryObject<Feature<NoFeatureConfig>> SNOW_WELL = FEATURES.register("well_snow", () -> new WellSnow(NoFeatureConfig.CODEC));
  	public static final RegistryObject<Feature<NoFeatureConfig>> MOSSY_STONE_WELL = FEATURES.register("well_mossy_stone", () -> new WellMossyStone(NoFeatureConfig.CODEC));
  	public static final RegistryObject<Feature<NoFeatureConfig>> FOREST_WELL = FEATURES.register("well_forest", () -> new WellForest(NoFeatureConfig.CODEC));

	public static final RegistryObject<Feature<NoFeatureConfig>> WITHER_SKELETON_WITH_BOW = FEATURES.register("wither_skeleton_with_bow", () -> new WitherSkeletonWithBow(NoFeatureConfig.CODEC));
	public static final RegistryObject<Feature<NoFeatureConfig>> SHULKER_MOB = FEATURES.register("shulker_mob", () -> new ShulkerMob(NoFeatureConfig.CODEC));
	public static final RegistryObject<Feature<NoFeatureConfig>> DROWNED_WITH_ARMOR = FEATURES.register("drowned_with_armor", DrownedWithArmor::new);

	public static final RegistryObject<Feature<NoFeatureConfig>> POST_PROCESS_CONNECTING_BLOCKS = FEATURES.register("post_process_connecting_blocks", StructurePostProcessConnectiveBlocks::new);
	public static final RegistryObject<Feature<StructureTargetChanceConfig>> STRUCTURE_BREAKAGE = FEATURES.register("structure_breakage", () -> new StructureBreakage(StructureTargetChanceConfig.CODEC));
	public static final RegistryObject<Feature<StructureTargetConfig>> STRUCTURE_CHAINS = FEATURES.register("structure_chains", () -> new StructureChains(StructureTargetConfig.CODEC));
	public static final RegistryObject<Feature<StructureTargetConfig>> STRUCTURE_CHORUS = FEATURES.register("structure_chorus", () -> new StructureChorus(StructureTargetConfig.CODEC));
	public static final RegistryObject<Feature<StructureTargetAndLengthConfig>> STRUCTURE_CRIMSON_PLANTS = FEATURES.register("structure_crimson_plants", () -> new StructureCrimsonPlants(StructureTargetAndLengthConfig.CODEC));
	public static final RegistryObject<Feature<StructureTargetConfig>> STRUCTURE_FIRE = FEATURES.register("structure_fire", () -> new StructureFire(StructureTargetConfig.CODEC));
	public static final RegistryObject<Feature<StructureTargetConfig>> STRUCTURE_NETHERWART = FEATURES.register("structure_netherwart", () -> new StructureNetherwart(StructureTargetConfig.CODEC));
	public static final RegistryObject<Feature<StructureTargetConfig>> STRUCTURE_SEAGRASS = FEATURES.register("structure_seagrass", () -> new StructureSeagrass(StructureTargetConfig.CODEC));
	public static final RegistryObject<Feature<StructureTargetLengthRangeConfig>> STRUCTURE_VINES = FEATURES.register("structure_vines", () -> new StructureVine(StructureTargetLengthRangeConfig.CODEC));
	public static final RegistryObject<Feature<StructureTargetAndLengthConfig>> STRUCTURE_VINES_AND_LEAVES = FEATURES.register("structure_vines_and_leaves", () -> new StructureVineAndLeaves(StructureTargetAndLengthConfig.CODEC));
	public static final RegistryObject<Feature<StructureTargetAndLengthConfig>> STRUCTURE_WARPED_PLANTS = FEATURES.register("structure_warped_plants", () -> new StructureWarpedPlants(StructureTargetAndLengthConfig.CODEC));
	public static final RegistryObject<Feature<StructureTargetAndLengthConfig>> STRUCTURE_VINE_BREAKAGE = FEATURES.register("structure_vine_breakage", () -> new StructureVineBreakage(StructureTargetAndLengthConfig.CODEC));
	public static final RegistryObject<Feature<StructureTargetAndRangeConfig>> STRUCTURE_GRASS = FEATURES.register("structure_grass", () -> new StructureGrass(StructureTargetAndRangeConfig.CODEC));
	public static final RegistryObject<Feature<StructureTargetAndRangeConfig>> STRUCTURE_FLOWERS = FEATURES.register("structure_flowers", () -> new StructureFlowers(StructureTargetAndRangeConfig.CODEC));
}
