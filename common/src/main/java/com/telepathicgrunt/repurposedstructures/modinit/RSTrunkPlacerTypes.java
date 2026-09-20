package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.registry.RegistryEntry;
import com.telepathicgrunt.repurposedstructures.modinit.registry.ResourcefulRegistry;
import com.telepathicgrunt.repurposedstructures.services.ResourcefulRegistriesService;
import com.telepathicgrunt.repurposedstructures.world.features.trunkplacer.ThickPaleOakTrunkPlacer;
import com.telepathicgrunt.repurposedstructures.world.predicates.MatterPhaseRuleTest;
import com.telepathicgrunt.repurposedstructures.world.predicates.PieceOriginAxisAlignedLinearPosRuleTest;
import com.telepathicgrunt.repurposedstructures.world.predicates.YValuePosRuleTest;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraft.world.level.levelgen.structure.templatesystem.PosRuleTestType;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTestType;

public final class RSTrunkPlacerTypes {
    public static final ResourcefulRegistry<TrunkPlacerType<? extends TrunkPlacer>> TRUNK_PLACER_TYPE = ResourcefulRegistriesService.INSTANCE.create(BuiltInRegistries.TRUNK_PLACER_TYPE, RepurposedStructures.MODID);

    public static final RegistryEntry<TrunkPlacerType<ThickPaleOakTrunkPlacer>> THICK_PALE_OAK_TRUNK_PLACER = TRUNK_PLACER_TYPE.register("thick_pale_oak_trunk_placer", () -> new TrunkPlacerType<>(ThickPaleOakTrunkPlacer.CODEC));
}
