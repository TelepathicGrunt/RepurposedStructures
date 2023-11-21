package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.registry.RegistryEntry;
import com.telepathicgrunt.repurposedstructures.modinit.registry.ResourcefulRegistries;
import com.telepathicgrunt.repurposedstructures.modinit.registry.ResourcefulRegistry;
import com.telepathicgrunt.repurposedstructures.world.features.*;
import com.telepathicgrunt.repurposedstructures.world.features.configs.GenericMobConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.MinecartConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.MineshaftSupportConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.NbtDungeonConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.NbtFeatureConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureRangeConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetAndLengthConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetAndRangeConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetChanceConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetLengthRangeConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockPileConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public final class RSFeatures {
	public static final ResourcefulRegistry<Feature<?>> FEATURES = ResourcefulRegistries.create(BuiltInRegistries.FEATURE, RepurposedStructures.MODID);

	public static final RegistryEntry<Feature<NbtDungeonConfig>> NBT_DUNGEONS = FEATURES.register("nbt_dungeon", () -> new NbtDungeon(NbtDungeonConfig.CODEC));
	public static final RegistryEntry<Feature<NbtFeatureConfig>> NBT_FEATURE = FEATURES.register("nbt_feature", NbtFeature::new);

	public static final RegistryEntry<Feature<NoneFeatureConfiguration>> WITHER_SKELETON_WITH_BOW = FEATURES.register("wither_skeleton_with_bow", WitherSkeletonWithBow::new);
	public static final RegistryEntry<Feature<NoneFeatureConfiguration>> SHULKER_MOB = FEATURES.register("shulker_mob", ShulkerMob::new);
	public static final RegistryEntry<Feature<NoneFeatureConfiguration>> DROWNED_WITH_ARMOR = FEATURES.register("drowned_with_armor", DrownedWithArmor::new);
	public static final RegistryEntry<Feature<GenericMobConfig>> SKELETON = FEATURES.register("skeleton", Skeletons::new);
	public static final RegistryEntry<Feature<GenericMobConfig>> SKELETON_HORSEMAN = FEATURES.register("skeleton_horseman", SkeletonHorseman::new);

	public static final RegistryEntry<Feature<NoneFeatureConfiguration>> POST_PROCESS_CONNECTING_BLOCKS = FEATURES.register("post_process_connecting_blocks", StructurePostProcessConnectiveBlocks::new);
	public static final RegistryEntry<Feature<StructureTargetChanceConfig>> STRUCTURE_BREAKAGE = FEATURES.register("structure_breakage", () -> new StructureBreakage(StructureTargetChanceConfig.CODEC));
	public static final RegistryEntry<Feature<StructureTargetConfig>> STRUCTURE_CHAINS = FEATURES.register("structure_chains", () -> new StructureChains(StructureTargetConfig.CODEC));
	public static final RegistryEntry<Feature<StructureTargetConfig>> STRUCTURE_END_ROD_CHAINS = FEATURES.register("structure_end_rod_chains", () -> new StructureEndRodChains(StructureTargetConfig.CODEC));
	public static final RegistryEntry<Feature<StructureTargetConfig>> STRUCTURE_CHORUS = FEATURES.register("structure_chorus", () -> new StructureChorus(StructureTargetConfig.CODEC));
	public static final RegistryEntry<Feature<StructureTargetAndLengthConfig>> STRUCTURE_CRIMSON_PLANTS = FEATURES.register("structure_crimson_plants", () -> new StructureCrimsonPlants(StructureTargetAndLengthConfig.CODEC));
	public static final RegistryEntry<Feature<StructureTargetConfig>> STRUCTURE_FIRE = FEATURES.register("structure_fire", () -> new StructureFire(StructureTargetConfig.CODEC));
	public static final RegistryEntry<Feature<StructureTargetConfig>> STRUCTURE_NETHERWART = FEATURES.register("structure_netherwart", () -> new StructureNetherwart(StructureTargetConfig.CODEC));
	public static final RegistryEntry<Feature<StructureTargetConfig>> STRUCTURE_SEAGRASS = FEATURES.register("structure_seagrass", () -> new StructureSeagrass(StructureTargetConfig.CODEC));
	public static final RegistryEntry<Feature<StructureTargetLengthRangeConfig>> STRUCTURE_VINES = FEATURES.register("structure_vines", () -> new StructureVine(StructureTargetLengthRangeConfig.CODEC));
	public static final RegistryEntry<Feature<StructureTargetAndLengthConfig>> STRUCTURE_VINES_AND_LEAVES = FEATURES.register("structure_vines_and_leaves", () -> new StructureVineAndLeaves(StructureTargetAndLengthConfig.CODEC));
	public static final RegistryEntry<Feature<StructureTargetAndLengthConfig>> STRUCTURE_WARPED_PLANTS = FEATURES.register("structure_warped_plants", () -> new StructureWarpedPlants(StructureTargetAndLengthConfig.CODEC));
	public static final RegistryEntry<Feature<StructureTargetAndLengthConfig>> STRUCTURE_VINE_BREAKAGE = FEATURES.register("structure_vine_breakage", () -> new StructureVineBreakage(StructureTargetAndLengthConfig.CODEC));
	public static final RegistryEntry<Feature<StructureTargetAndRangeConfig>> STRUCTURE_GRASS = FEATURES.register("structure_grass", () -> new StructureGrass(StructureTargetAndRangeConfig.CODEC));
	public static final RegistryEntry<Feature<StructureTargetAndRangeConfig>> STRUCTURE_FLOWERS = FEATURES.register("structure_flowers", () -> new StructureFlowers(StructureTargetAndRangeConfig.CODEC));
	public static final RegistryEntry<Feature<StructureRangeConfig>> STRUCTURE_POWDER_SNOW = FEATURES.register("structure_powder_snow", () -> new StructurePowderSnow(StructureRangeConfig.CODEC));
	public static final RegistryEntry<Feature<BlockPileConfiguration>> UNDERWATER_BLOCK_PILE = FEATURES.register("underwater_block_pile", () -> new UnderwaterBlockPileFeature(BlockPileConfiguration.CODEC));

	public static final RegistryEntry<Feature<MinecartConfig>> MINESHAFT_MINECARTS = FEATURES.register("mineshaft_minecarts", () -> new MinecartFeature(MinecartConfig.CODEC));
	public static final RegistryEntry<Feature<MineshaftSupportConfig>> MINESHAFT_SUPPORTS = FEATURES.register("mineshaft_supports", () -> new MineshaftSupport(MineshaftSupportConfig.CODEC));
}
