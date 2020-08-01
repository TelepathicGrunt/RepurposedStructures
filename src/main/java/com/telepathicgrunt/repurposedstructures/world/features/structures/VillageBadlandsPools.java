package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.jigsaw.*;
import net.minecraft.world.gen.feature.template.*;

public class VillageBadlandsPools {
    public static void init() {
    }

    static {
        ImmutableList<StructureProcessor> crop_replacement = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
                new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.BEETROOTS.getDefaultState()),
                new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.MELON_STEM.getDefaultState()))));

        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID + ":village/badlands/town_centers"), new ResourceLocation("empty"),
                ImmutableList.of(
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/town_centers/center_1"), 98)),
                JigsawPattern.PlacementBehaviour.RIGID));

        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID + ":village/badlands/streets"),
                new ResourceLocation(RepurposedStructures.MODID + ":village/badlands/terminators"),
                ImmutableList.of(
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/streets/corner_01"), 3),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/streets/corner_02"), 3),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/streets/corner_03"), 3),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/streets/straight_01"), 6),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/streets/straight_02"), 6),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/streets/straight_03"), 2),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/streets/crossroad_01"), 3),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/streets/crossroad_02"), 3),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/streets/crossroad_03"), 3),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/streets/square_01"), 3),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/streets/square_02"), 3)),
                JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));

        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID + ":village/badlands/houses"), new ResourceLocation(RepurposedStructures.MODID + ":village/badlands/terminators"),
                ImmutableList.of(
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/small_house_1"), 2),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/small_house_2"), 2),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/small_house_3"), 2),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/small_house_4"), 2),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/small_house_5"), 2),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/medium_house_1"), 2),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/medium_house_2"), 2),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/medium_house_3"), 2),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/medium_house_4"), 2),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/butcher"), 1),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/tool_smith"), 4),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/fletcher"), 2),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/shepherd"), 1),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/saloon"), 6),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/armorer"), 3),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/fisher"), 1),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/tannery"), 2),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/cartographer"), 2),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/library"), 2),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/mason"), 4),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/weaponsmith"), 4),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/temple_1"), 4),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/temple_2"), 4),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/farm_1", crop_replacement), 11),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/farm_2", crop_replacement), 4),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/pen_1"), 2),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/meeting_point"), 3),
                        Pair.of(EmptyJigsawPiece.INSTANCE, 5)),
                JigsawPattern.PlacementBehaviour.RIGID));

        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID + ":village/badlands/terminators"), new ResourceLocation("empty"),
                ImmutableList.of(
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/terminators/terminator_01"), 1),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/terminators/terminator_02"), 1),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/terminators/terminator_03"), 1)),
                JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));

        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID + ":village/badlands/decor"), new ResourceLocation("empty"),
                ImmutableList.of(
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/decor/lamp_1"), 10),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/decor/lamp_2"), 10),
                        new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/decor/lamp_3"), 10),
                        new Pair<>(new FeatureJigsawPiece(Feature.field_227248_z_.configure(DefaultBiomeFeatures.CACTUS_CONFIG)), 5),
                        new Pair<>(new FeatureJigsawPiece(Feature.field_227248_z_.configure(DefaultBiomeFeatures.DEAD_BUSH_CONFIG)), 10),
                        new Pair<>(new FeatureJigsawPiece(Feature.field_227244_A_.configure(DefaultBiomeFeatures.HAY_PILE_CONFIG)), 1),
                        Pair.of(EmptyJigsawPiece.INSTANCE, 10)),
                JigsawPattern.PlacementBehaviour.RIGID));
    }

}