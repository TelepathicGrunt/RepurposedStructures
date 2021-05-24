package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.predicates.MatterPhaseRuleTest;
import com.telepathicgrunt.repurposedstructures.world.predicates.PieceOriginAxisAlignedLinearPosRuleTest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.template.IPosRuleTests;
import net.minecraft.world.gen.feature.template.IRuleTestType;

public class RSPredicates {


    public static IRuleTestType<MatterPhaseRuleTest> MATTER_PHASE_RULE_TEST = () -> MatterPhaseRuleTest.CODEC;

    public static IPosRuleTests<PieceOriginAxisAlignedLinearPosRuleTest> PIECE_ORIGIN_AXIS_ALIGNED_LINEAR_POS_RULE_TEST = () -> PieceOriginAxisAlignedLinearPosRuleTest.CODEC;

    public static void registerPredicates() {
        Registry.register(Registry.RULE_TEST, new ResourceLocation(RepurposedStructures.MODID, "matter_phase_rule_test"), MATTER_PHASE_RULE_TEST);

        Registry.register(Registry.POS_RULE_TEST, new ResourceLocation(RepurposedStructures.MODID, "piece_origin_axis_aligned_linear_pos_rule_test"), PIECE_ORIGIN_AXIS_ALIGNED_LINEAR_POS_RULE_TEST);
    }
}
