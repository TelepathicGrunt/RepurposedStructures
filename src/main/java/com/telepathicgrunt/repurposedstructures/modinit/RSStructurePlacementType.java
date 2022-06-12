package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.placements.AdvancedRandomSpread;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


public final class RSStructurePlacementType {
    public static final DeferredRegister<StructurePlacementType<?>> STRUCTURE_PLACEMENT_TYPE = DeferredRegister.create(Registry.STRUCTURE_PLACEMENT_TYPE_REGISTRY, RepurposedStructures.MODID);

    public static final RegistryObject<StructurePlacementType<AdvancedRandomSpread>> ADVANCED_RANDOM_SPREAD = STRUCTURE_PLACEMENT_TYPE.register("advanced_random_spread", () -> () -> AdvancedRandomSpread.CODEC);
}
