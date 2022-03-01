package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;

public final class RSTags {
    public static void initTags() {}

    public static TagKey<ConfiguredStructureFeature<?, ?>> SPAWNS_BLACK_CATS = TagKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY,
            new ResourceLocation(RepurposedStructures.MODID, "spawn_black_cats"));

    public static TagKey<ConfiguredStructureFeature<?, ?>> NO_LAKES = TagKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY,
            new ResourceLocation(RepurposedStructures.MODID, "no_lakes"));

    public static TagKey<ConfiguredStructureFeature<?, ?>> LESS_JUNGLE_BUSHES = TagKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY,
            new ResourceLocation(RepurposedStructures.MODID, "less_jungle_bushes"));

    public static TagKey<ConfiguredStructureFeature<?, ?>> NO_LAVAFALLS = TagKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY,
            new ResourceLocation(RepurposedStructures.MODID, "no_lavafalls"));

    public static TagKey<ConfiguredStructureFeature<?, ?>> NO_BASALT = TagKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY,
            new ResourceLocation(RepurposedStructures.MODID, "no_basalt"));


    public static TagKey<Biome> DESERTS = TagKey.create(Registry.BIOME_REGISTRY,
            new ResourceLocation(RepurposedStructures.MODID, "collections/deserts"));

    public static TagKey<Biome> MUSHROOMS = TagKey.create(Registry.BIOME_REGISTRY,
            new ResourceLocation(RepurposedStructures.MODID, "collections/mushrooms"));

    public static TagKey<Biome> SWAMPS = TagKey.create(Registry.BIOME_REGISTRY,
            new ResourceLocation(RepurposedStructures.MODID, "collections/swamps"));

    public static TagKey<Biome> ICY = TagKey.create(Registry.BIOME_REGISTRY,
            new ResourceLocation(RepurposedStructures.MODID, "collections/icy"));

    public static TagKey<Biome> MOUNTAINS = TagKey.create(Registry.BIOME_REGISTRY,
            new ResourceLocation(RepurposedStructures.MODID, "collections/mountains"));

    public static TagKey<Biome> END_ISLAND_LAND = TagKey.create(Registry.BIOME_REGISTRY,
            new ResourceLocation(RepurposedStructures.MODID, "collections/end_island_land"));

    public static TagKey<Biome> END_VOIDS = TagKey.create(Registry.BIOME_REGISTRY,
            new ResourceLocation(RepurposedStructures.MODID, "collections/end_voids"));

    public static TagKey<Biome> OCEANS = TagKey.create(Registry.BIOME_REGISTRY,
            new ResourceLocation(RepurposedStructures.MODID, "collections/oceans"));
}
