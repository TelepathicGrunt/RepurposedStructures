package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.registry.RegistryEntry;
import com.telepathicgrunt.repurposedstructures.modinit.registry.ResourcefulRegistries;
import com.telepathicgrunt.repurposedstructures.modinit.registry.ResourcefulRegistry;
import com.telepathicgrunt.repurposedstructures.world.predicates.MatterPhaseRuleTest;
import com.telepathicgrunt.repurposedstructures.world.predicates.PieceOriginAxisAlignedLinearPosRuleTest;
import com.telepathicgrunt.repurposedstructures.world.predicates.YValuePosRuleTest;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.templatesystem.PosRuleTestType;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTestType;

public final class RSPredicates {
    public static final ResourcefulRegistry<RuleTestType<?>> RULE_TEST = ResourcefulRegistries.create(BuiltInRegistries.RULE_TEST, RepurposedStructures.MODID);
    public static final ResourcefulRegistry<PosRuleTestType<?>> POS_RULE_TEST = ResourcefulRegistries.create(BuiltInRegistries.POS_RULE_TEST, RepurposedStructures.MODID);

    public static final RegistryEntry<RuleTestType<MatterPhaseRuleTest>, RuleTestType<MatterPhaseRuleTest>> MATTER_PHASE_RULE_TEST = RULE_TEST.register("matter_phase_rule_test", () -> () -> MatterPhaseRuleTest.CODEC);

    public static final RegistryEntry<PosRuleTestType<PieceOriginAxisAlignedLinearPosRuleTest>, PosRuleTestType<PieceOriginAxisAlignedLinearPosRuleTest>> PIECE_ORIGIN_AXIS_ALIGNED_LINEAR_POS_RULE_TEST = POS_RULE_TEST.register("piece_origin_axis_aligned_linear_pos_rule_test", () -> () -> PieceOriginAxisAlignedLinearPosRuleTest.CODEC);
    public static final RegistryEntry<PosRuleTestType<YValuePosRuleTest>, PosRuleTestType<YValuePosRuleTest>> Y_VALUE_POS_RULE_TEST = POS_RULE_TEST.register("y_value_pos_rule_test", () -> () -> YValuePosRuleTest.CODEC);
}
