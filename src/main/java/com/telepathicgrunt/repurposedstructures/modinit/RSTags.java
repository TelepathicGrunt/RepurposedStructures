package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;

public final class RSTags {
    public static void initTags() {}

    public static TagKey<Structure> NO_LAKES = TagKey.create(Registry.STRUCTURE_REGISTRY,
            new ResourceLocation(RepurposedStructures.MODID, "no_lakes"));

    public static TagKey<Structure> LESS_JUNGLE_BUSHES = TagKey.create(Registry.STRUCTURE_REGISTRY,
            new ResourceLocation(RepurposedStructures.MODID, "less_jungle_bushes"));

    public static TagKey<Structure> NO_LAVAFALLS = TagKey.create(Registry.STRUCTURE_REGISTRY,
            new ResourceLocation(RepurposedStructures.MODID, "no_lavafalls"));

    public static TagKey<Structure> NO_WATERFALLS = TagKey.create(Registry.STRUCTURE_REGISTRY,
            new ResourceLocation(RepurposedStructures.MODID, "no_waterfalls"));

    public static TagKey<Structure> NO_BASALT = TagKey.create(Registry.STRUCTURE_REGISTRY,
            new ResourceLocation(RepurposedStructures.MODID, "no_basalt"));

    public static TagKey<Structure> LARGER_LOCATE_SEARCH = TagKey.create(Registry.STRUCTURE_REGISTRY,
            new ResourceLocation(RepurposedStructures.MODID, "larger_locate_search"));
}
