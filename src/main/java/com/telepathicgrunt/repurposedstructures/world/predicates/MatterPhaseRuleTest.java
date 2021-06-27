package com.telepathicgrunt.repurposedstructures.world.predicates;

import com.google.common.collect.Maps;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSPredicates;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.RuleTestType;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.Util;

import java.util.Map;
import java.util.Random;

public class MatterPhaseRuleTest extends RuleTest {
    public static final Codec<MatterPhaseRuleTest> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            StringIdentifiable.createCodec(MATTER_PHASE::values, MATTER_PHASE::byName).fieldOf("phase_to_test_for").stable().forGetter((ruletest) -> ruletest.phaseToTestFor),
            Codec.BOOL.fieldOf("invert_condition").orElse(false).forGetter((ruletest) -> ruletest.invertCondition))
            .apply(instance, instance.stable(MatterPhaseRuleTest::new)));

    private final MATTER_PHASE phaseToTestFor;
    private final boolean invertCondition;

    private MatterPhaseRuleTest(MATTER_PHASE phaseToTestFor, boolean invertCondition) {
        this.phaseToTestFor = phaseToTestFor;
        this.invertCondition = invertCondition;
    }

    public boolean test(BlockState state, Random random) {
        boolean phaseMatch = false;

        switch(phaseToTestFor){
            case AIR:
                if(state.isAir()) phaseMatch = true;
                break;
            case LIQUID:
                if(!state.getFluidState().isEmpty()) phaseMatch = true;
                break;
            case SOLID:
                if(!state.isAir() && state.getFluidState().isEmpty() && state.isOpaque()) phaseMatch = true;
                break;
            case ALL_DRY_NON_AIR:
                if(!state.isAir() && state.getFluidState().isEmpty()) phaseMatch = true;
                break;
            case AIR_RAIL_OR_CHAIN:
                if(state.isAir() || state.isOf(Blocks.CHAIN) || state.isOf(Blocks.RAIL)) phaseMatch = true;
                break;
        }

        if(invertCondition){
            phaseMatch = !phaseMatch;
        }

        return phaseMatch;
    }

    protected RuleTestType<?> getType() {
        return RSPredicates.MATTER_PHASE_RULE_TEST;
    }


    public enum MATTER_PHASE implements StringIdentifiable {
        SOLID("SOLID"),
        LIQUID("LIQUID"),
        AIR("AIR"),
        ALL_DRY_NON_AIR("ALL_DRY_NON_AIR"),
        AIR_RAIL_OR_CHAIN("AIR_RAIL_OR_CHAIN");

        private final String name;

        MATTER_PHASE(String name) {
            this.name = name;
        }

        private static final Map<String, MATTER_PHASE> BY_NAME = Util.make(Maps.newHashMap(), (hashMap) -> {
            MATTER_PHASE[] var1 = values();
            for (MATTER_PHASE type : var1) {
                hashMap.put(type.name, type);
            }
        });

        public static MATTER_PHASE byName(String name) {
            return BY_NAME.get(name.toUpperCase());
        }

        @Override
        public String asString() {
            return this.name;
        }
    }
}
