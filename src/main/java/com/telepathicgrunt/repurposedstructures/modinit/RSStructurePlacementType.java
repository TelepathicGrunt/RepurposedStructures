package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.placements.AdvancedRandomSpread;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;


public final class RSStructurePlacementType {
    private RSStructurePlacementType() {}

    public static final StructurePlacementType<AdvancedRandomSpread> ADVANCED_RANDOM_SPREAD = () -> AdvancedRandomSpread.CODEC;

    public static void registerStructurePlacementTypes() {
        Registry.register(BuiltInRegistries.STRUCTURE_PLACEMENT, new ResourceLocation(RepurposedStructures.MODID, "advanced_random_spread"), ADVANCED_RANDOM_SPREAD);
    }
}
