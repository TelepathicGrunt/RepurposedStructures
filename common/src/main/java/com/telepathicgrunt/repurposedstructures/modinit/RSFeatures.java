package com.telepathicgrunt.repurposedstructures.modinit;

import com.mojang.serialization.MapCodec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.registry.RegistryEntry;
import com.telepathicgrunt.repurposedstructures.modinit.registry.ResourcefulRegistry;
import com.telepathicgrunt.repurposedstructures.services.ResourcefulRegistriesService;
import com.telepathicgrunt.repurposedstructures.world.features.ConfigurableCoralClaw;
import com.telepathicgrunt.repurposedstructures.world.features.ConfigurableCoralMushroom;
import com.telepathicgrunt.repurposedstructures.world.features.ConfigurableCoralTree;
import com.telepathicgrunt.repurposedstructures.world.features.DrownedWithArmor;
import com.telepathicgrunt.repurposedstructures.world.features.MinecartFeature;
import com.telepathicgrunt.repurposedstructures.world.features.MineshaftSupport;
import com.telepathicgrunt.repurposedstructures.world.features.NbtDungeon;
import com.telepathicgrunt.repurposedstructures.world.features.NbtFeature;
import com.telepathicgrunt.repurposedstructures.world.features.OceanTemperatureRandomSelector;
import com.telepathicgrunt.repurposedstructures.world.features.ShulkerMob;
import com.telepathicgrunt.repurposedstructures.world.features.SkeletonHorseman;
import com.telepathicgrunt.repurposedstructures.world.features.Skeletons;
import com.telepathicgrunt.repurposedstructures.world.features.StructureBreakage;
import com.telepathicgrunt.repurposedstructures.world.features.StructureChains;
import com.telepathicgrunt.repurposedstructures.world.features.StructureChorus;
import com.telepathicgrunt.repurposedstructures.world.features.StructureCrimsonPlants;
import com.telepathicgrunt.repurposedstructures.world.features.StructureEndRodChains;
import com.telepathicgrunt.repurposedstructures.world.features.StructureFire;
import com.telepathicgrunt.repurposedstructures.world.features.StructureFlowers;
import com.telepathicgrunt.repurposedstructures.world.features.StructureGrass;
import com.telepathicgrunt.repurposedstructures.world.features.StructureNetherwart;
import com.telepathicgrunt.repurposedstructures.world.features.StructurePostProcessConnectiveBlocks;
import com.telepathicgrunt.repurposedstructures.world.features.StructurePowderSnow;
import com.telepathicgrunt.repurposedstructures.world.features.StructureSeagrass;
import com.telepathicgrunt.repurposedstructures.world.features.StructureVine;
import com.telepathicgrunt.repurposedstructures.world.features.StructureVineAndLeaves;
import com.telepathicgrunt.repurposedstructures.world.features.StructureVineBreakage;
import com.telepathicgrunt.repurposedstructures.world.features.StructureWarpedPlants;
import com.telepathicgrunt.repurposedstructures.world.features.UnderwaterBlockPileFeature;
import com.telepathicgrunt.repurposedstructures.world.features.WitherSkeletonWithBow;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;

public final class RSFeatures {
	public static final ResourcefulRegistry<MapCodec<? extends Feature>> FEATURES = ResourcefulRegistriesService.INSTANCE.create(BuiltInRegistries.FEATURE_TYPE, RepurposedStructures.MODID);

	public static final RegistryEntry<MapCodec<? extends Feature>> NBT_DUNGEONS = FEATURES.register("nbt_dungeon", () -> NbtDungeon.CODEC);
	public static final RegistryEntry<MapCodec<? extends Feature>> NBT_FEATURE = FEATURES.register("nbt_feature", () -> NbtFeature.CODEC);

	public static final RegistryEntry<MapCodec<? extends Feature>> WITHER_SKELETON_WITH_BOW = FEATURES.register("wither_skeleton_with_bow", () -> WitherSkeletonWithBow.CODEC);
	public static final RegistryEntry<MapCodec<? extends Feature>> SHULKER_MOB = FEATURES.register("shulker_mob", () -> ShulkerMob.CODEC);
	public static final RegistryEntry<MapCodec<? extends Feature>> DROWNED_WITH_ARMOR = FEATURES.register("drowned_with_armor", () -> DrownedWithArmor.CODEC);
	public static final RegistryEntry<MapCodec<? extends Feature>> SKELETON = FEATURES.register("skeleton", () -> Skeletons.CODEC);
	public static final RegistryEntry<MapCodec<? extends Feature>> SKELETON_HORSEMAN = FEATURES.register("skeleton_horseman", () -> SkeletonHorseman.CODEC);

	public static final RegistryEntry<MapCodec<? extends Feature>> POST_PROCESS_CONNECTING_BLOCKS = FEATURES.register("post_process_connecting_blocks", () -> StructurePostProcessConnectiveBlocks.CODEC);
	public static final RegistryEntry<MapCodec<? extends Feature>> STRUCTURE_BREAKAGE = FEATURES.register("structure_breakage", () -> StructureBreakage.CODEC);
	public static final RegistryEntry<MapCodec<? extends Feature>> STRUCTURE_CHAINS = FEATURES.register("structure_chains", () -> StructureChains.CODEC);
	public static final RegistryEntry<MapCodec<? extends Feature>> STRUCTURE_END_ROD_CHAINS = FEATURES.register("structure_end_rod_chains", () -> StructureEndRodChains.CODEC);
	public static final RegistryEntry<MapCodec<? extends Feature>> STRUCTURE_CHORUS = FEATURES.register("structure_chorus", () -> StructureChorus.CODEC);
	public static final RegistryEntry<MapCodec<? extends Feature>> STRUCTURE_CRIMSON_PLANTS = FEATURES.register("structure_crimson_plants", () -> StructureCrimsonPlants.CODEC);
	public static final RegistryEntry<MapCodec<? extends Feature>> STRUCTURE_FIRE = FEATURES.register("structure_fire", () -> StructureFire.CODEC);
	public static final RegistryEntry<MapCodec<? extends Feature>> STRUCTURE_NETHERWART = FEATURES.register("structure_netherwart", () -> StructureNetherwart.CODEC);
	public static final RegistryEntry<MapCodec<? extends Feature>> STRUCTURE_SEAGRASS = FEATURES.register("structure_seagrass", () -> StructureSeagrass.CODEC);
	public static final RegistryEntry<MapCodec<? extends Feature>> STRUCTURE_VINES = FEATURES.register("structure_vines", () -> StructureVine.CODEC);
	public static final RegistryEntry<MapCodec<? extends Feature>> STRUCTURE_VINES_AND_LEAVES = FEATURES.register("structure_vines_and_leaves", () -> StructureVineAndLeaves.CODEC);
	public static final RegistryEntry<MapCodec<? extends Feature>> STRUCTURE_WARPED_PLANTS = FEATURES.register("structure_warped_plants", () -> StructureWarpedPlants.CODEC);
	public static final RegistryEntry<MapCodec<? extends Feature>> STRUCTURE_VINE_BREAKAGE = FEATURES.register("structure_vine_breakage", () -> StructureVineBreakage.CODEC);
	public static final RegistryEntry<MapCodec<? extends Feature>> STRUCTURE_GRASS = FEATURES.register("structure_grass", () -> StructureGrass.CODEC);
	public static final RegistryEntry<MapCodec<? extends Feature>> STRUCTURE_FLOWERS = FEATURES.register("structure_flowers", () -> StructureFlowers.CODEC);
	public static final RegistryEntry<MapCodec<? extends Feature>> STRUCTURE_POWDER_SNOW = FEATURES.register("structure_powder_snow", () -> StructurePowderSnow.CODEC);
	public static final RegistryEntry<MapCodec<? extends Feature>> UNDERWATER_BLOCK_PILE = FEATURES.register("underwater_block_pile", () -> UnderwaterBlockPileFeature.CODEC);
	public static final RegistryEntry<MapCodec<? extends Feature>> CONFIGURABLE_CORAL_TREE = FEATURES.register("configurable_coral_tree", () -> ConfigurableCoralTree.CODEC);
	public static final RegistryEntry<MapCodec<? extends Feature>> CONFIGURABLE_CORAL_MUSHROOM = FEATURES.register("configurable_coral_mushroom", () -> ConfigurableCoralMushroom.CODEC);
	public static final RegistryEntry<MapCodec<? extends Feature>> CONFIGURABLE_CORAL_CLAW = FEATURES.register("configurable_coral_claw", () -> ConfigurableCoralClaw.CODEC);
	public static final RegistryEntry<MapCodec<? extends Feature>> OCEAN_TEMPERATURE_RANDOM_SELECTOR = FEATURES.register("ocean_temperature_random_selector", () -> OceanTemperatureRandomSelector.CODEC);

	public static final RegistryEntry<MapCodec<? extends Feature>> MINESHAFT_MINECARTS = FEATURES.register("mineshaft_minecarts", () -> MinecartFeature.CODEC);
	public static final RegistryEntry<MapCodec<? extends Feature>> MINESHAFT_SUPPORTS = FEATURES.register("mineshaft_supports", () -> MineshaftSupport.CODEC);
}
