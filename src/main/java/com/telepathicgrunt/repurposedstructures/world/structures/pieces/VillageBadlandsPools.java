package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Lifecycle;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.StructureProcessorListAccessor;
import net.minecraft.block.Blocks;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.structure.processor.RuleStructureProcessor;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.RandomBlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeatures;

public class VillageBadlandsPools {
    public static void init(MutableRegistry<StructurePool> poolRegistry) {
        StructureProcessorList crop_replacement = StructureProcessorListAccessor.invokeRegister(
                RepurposedStructures.MODID+":village/badlands/crop_replacement", ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
                new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.BEETROOTS.getDefaultState()),
                new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.MELON_STEM.getDefaultState())))));

        StructurePools.register(new StructurePool(new Identifier(RepurposedStructures.MODID + ":village/badlands/town_centers"), new Identifier("empty"),
                ImmutableList.of(
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/town_centers/center_1"), 98)),
                StructurePool.Projection.RIGID));

        StructurePools.register(new StructurePool(new Identifier(RepurposedStructures.MODID + ":village/badlands/streets"),
                new Identifier(RepurposedStructures.MODID + ":village/badlands/terminators"),
                ImmutableList.of(
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/streets/corner_01"), 3),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/streets/corner_02"), 3),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/streets/corner_03"), 3),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/streets/straight_01"), 6),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/streets/straight_02"), 6),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/streets/straight_03"), 2),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/streets/crossroad_01"), 3),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/streets/crossroad_02"), 3),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/streets/crossroad_03"), 3),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/streets/square_01"), 3),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/streets/square_02"), 3)),
                StructurePool.Projection.TERRAIN_MATCHING));

        StructurePools.register(new StructurePool(new Identifier(RepurposedStructures.MODID + ":village/badlands/houses"), new Identifier(RepurposedStructures.MODID + ":village/badlands/terminators"),
                ImmutableList.of(
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/houses/small_house_1"), 2),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/houses/small_house_2"), 2),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/houses/small_house_3"), 2),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/houses/small_house_4"), 2),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/houses/small_house_5"), 2),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/houses/medium_house_1"), 2),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/houses/medium_house_2"), 2),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/houses/medium_house_3"), 2),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/houses/medium_house_4"), 2),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/houses/butcher"), 1),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/houses/tool_smith"), 4),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/houses/fletcher"), 2),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/houses/shepherd"), 1),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/houses/saloon"), 6),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/houses/armorer"), 3),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/houses/fisher"), 1),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/houses/tannery"), 2),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/houses/cartographer"), 2),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/houses/library"), 2),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/houses/mason"), 4),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/houses/weaponsmith"), 4),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/houses/temple_1"), 4),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/houses/temple_2"), 4),
                        new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID + ":village/badlands/houses/farm_1", crop_replacement), 11),
                        new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID + ":village/badlands/houses/farm_2", crop_replacement), 4),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/houses/pen_1"), 2),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/houses/meeting_point"), 3),
                        Pair.of(StructurePoolElement.method_30438(), 5)),
                StructurePool.Projection.RIGID));

        StructurePools.register(new StructurePool(new Identifier(RepurposedStructures.MODID + ":village/badlands/terminators"), new Identifier("empty"),
                ImmutableList.of(
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/terminators/terminator_01"), 1),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/terminators/terminator_02"), 1),
                        new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/terminators/terminator_03"), 1)),
                StructurePool.Projection.TERRAIN_MATCHING));

        StructurePools.register(new StructurePool(new Identifier(RepurposedStructures.MODID + ":village/badlands/decor"), new Identifier("empty"),
                ImmutableList.of(
                        Pair.of(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/decor/lamp_1"), 10),
                        Pair.of(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/decor/lamp_2"), 10),
                        Pair.of(StructurePoolElement.method_30425(RepurposedStructures.MODID + ":village/badlands/decor/lamp_3"), 10),
                        Pair.of(StructurePoolElement.method_30421(ConfiguredFeatures.PATCH_CACTUS), 5),
                        Pair.of(StructurePoolElement.method_30421(ConfiguredFeatures.PATCH_DEAD_BUSH), 10),
                        Pair.of(StructurePoolElement.method_30421(ConfiguredFeatures.PILE_HAY), 1),
                        Pair.of(StructurePoolElement.method_30438(), 10)),
                StructurePool.Projection.RIGID));
    }

}