package com.telepathicgrunt.repurposedstructures.world.predicates;

import com.google.common.collect.Maps;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSPredicates;
import net.minecraft.Util;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTestType;

import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class MatterPhaseRuleTest extends RuleTest {
    public static final Codec<MatterPhaseRuleTest> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            StringRepresentable.fromEnum(MATTER_PHASE::values).fieldOf("phase_to_test_for").stable().forGetter((ruletest) -> ruletest.phaseToTestFor),
            Codec.BOOL.fieldOf("invert_condition").orElse(false).forGetter((ruletest) -> ruletest.invertCondition))
            .apply(instance, instance.stable(MatterPhaseRuleTest::new)));

    private final MATTER_PHASE phaseToTestFor;
    private final boolean invertCondition;

    private MatterPhaseRuleTest(MATTER_PHASE phaseToTestFor, boolean invertCondition) {
        this.phaseToTestFor = phaseToTestFor;
        this.invertCondition = invertCondition;
    }

    public boolean test(BlockState state, RandomSource random) {
        boolean phaseMatch = false;

        switch(phaseToTestFor) {
            case AIR:
                if(state.isAir()) phaseMatch = true;
                break;
            case LIQUID:
                if(!state.getFluidState().isEmpty()) phaseMatch = true;
                break;
            case SOLID:
                if(!state.isAir() && state.getFluidState().isEmpty() && state.canOcclude()) phaseMatch = true;
                break;
            case AIR_RAIL_OR_CHAIN:
                if(state.isAir() || state.is(Blocks.CHAIN) || state.is(Blocks.RAIL)) phaseMatch = true;
                break;
            case LIQUID_RAIL_OR_CHAIN:
                if(!state.getFluidState().isEmpty() || state.is(Blocks.CHAIN) || state.is(Blocks.RAIL)) phaseMatch = true;
                break;
        }

        if(invertCondition) {
            phaseMatch = !phaseMatch;
        }

        return phaseMatch;
    }

    protected RuleTestType<?> getType() {
        return RSPredicates.MATTER_PHASE_RULE_TEST;
    }


    public enum MATTER_PHASE implements StringRepresentable {
        SOLID("SOLID"),
        LIQUID("LIQUID"),
        AIR("AIR"),
        AIR_RAIL_OR_CHAIN("AIR_RAIL_OR_CHAIN"),
        LIQUID_RAIL_OR_CHAIN("LIQUID_RAIL_OR_CHAIN");

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
            return BY_NAME.get(name.toUpperCase(Locale.ROOT));
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }
    }
}
