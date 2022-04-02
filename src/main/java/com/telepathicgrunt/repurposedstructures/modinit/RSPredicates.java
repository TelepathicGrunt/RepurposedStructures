package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.predicates.MatterPhaseRuleTest;
import com.telepathicgrunt.repurposedstructures.world.predicates.PieceOriginAxisAlignedLinearPosRuleTest;
import com.telepathicgrunt.repurposedstructures.world.predicates.YValuePosRuleTest;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.templatesystem.PosRuleTestType;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTestType;

public final class RSPredicates {
    private RSPredicates() {}

    public static RuleTestType<MatterPhaseRuleTest> MATTER_PHASE_RULE_TEST = () -> MatterPhaseRuleTest.CODEC;

    public static PosRuleTestType<PieceOriginAxisAlignedLinearPosRuleTest> PIECE_ORIGIN_AXIS_ALIGNED_LINEAR_POS_RULE_TEST = () -> PieceOriginAxisAlignedLinearPosRuleTest.CODEC;
    public static PosRuleTestType<YValuePosRuleTest> Y_VALUE_POS_RULE_TEST = () -> YValuePosRuleTest.CODEC;

    public static void registerPredicates() {
        Registry.register(Registry.RULE_TEST, new ResourceLocation(RepurposedStructures.MODID, "matter_phase_rule_test"), MATTER_PHASE_RULE_TEST);

        Registry.register(Registry.POS_RULE_TEST, new ResourceLocation(RepurposedStructures.MODID, "piece_origin_axis_aligned_linear_pos_rule_test"), PIECE_ORIGIN_AXIS_ALIGNED_LINEAR_POS_RULE_TEST);
        Registry.register(Registry.POS_RULE_TEST, new ResourceLocation(RepurposedStructures.MODID, "y_value_pos_rule_test"), Y_VALUE_POS_RULE_TEST);
    }
}
