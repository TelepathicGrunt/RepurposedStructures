package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.SingleJigsawPiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;


public class OutpostNetherPools {
    public static void initPools(){
    }

    static {
        // Special thanks to /r/l-ll-ll-l_IsDisLoss for allowing me to mimic his Nether Outpost design!
        // Crimson Outpost
        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"outposts/crimson/base_plates"), new ResourceLocation("empty"),
                ImmutableList.of(Pair.of(
                        new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/crimson/base_plate"), 1)),
                JigsawPattern.PlacementBehaviour.RIGID));

        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"outposts/crimson/towers"), new ResourceLocation("empty"),
                ImmutableList.of(
                        Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/crimson/tower"), 1),
                        Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/crimson/tower_glowing"), 1)),
                JigsawPattern.PlacementBehaviour.RIGID));

        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"outposts/crimson/plates"), new ResourceLocation("empty"),
                ImmutableList.of(Pair.of(
                        new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/crimson/plate"), 1)),
                JigsawPattern.PlacementBehaviour.RIGID));

        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"outposts/crimson/features"), new ResourceLocation("empty"),
                ImmutableList.of(
                        Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/crimson/cage1"), 1),
                        Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/crimson/cage2"), 1),
                        Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/crimson/logs"), 1),
                        Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/crimson/tent1"), 1),
                        Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/crimson/tent2"), 1),
                        Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/crimson/targets"), 1)
                ),
                JigsawPattern.PlacementBehaviour.RIGID));


        // Special thanks to /r/l-ll-ll-l_IsDisLoss for allowing me to mimic his Nether Outpost design!
        // Warped Outpost
        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"outposts/warped/base_plates"), new ResourceLocation("empty"),
                ImmutableList.of(Pair.of(
                        new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/warped/base_plate"), 1)),
                JigsawPattern.PlacementBehaviour.RIGID));

        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"outposts/warped/towers"), new ResourceLocation("empty"),
                ImmutableList.of(
                        Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/warped/tower"), 1),
                        Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/warped/tower_glowing"), 1)),
                JigsawPattern.PlacementBehaviour.RIGID));

        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"outposts/warped/plates"), new ResourceLocation("empty"),
                ImmutableList.of(Pair.of(
                        new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/warped/plate"), 1)),
                JigsawPattern.PlacementBehaviour.RIGID));

        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"outposts/warped/features"), new ResourceLocation("empty"),
                ImmutableList.of(
                        Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/warped/cage1"), 1),
                        Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/warped/cage2"), 1),
                        Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/warped/logs"), 1),
                        Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/warped/tent1"), 1),
                        Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/warped/tent2"), 1),
                        Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/warped/targets"), 1)
                ),
                JigsawPattern.PlacementBehaviour.RIGID));


        //Special thanks to cannon_foddr for the this Nether Outpost design!
        //Nether Brick Outpost
        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"outposts/nether_brick/base_plates"), new ResourceLocation("empty"),
                ImmutableList.of(Pair.of(
                        new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/nether_brick/base_plate"), 1)),
                JigsawPattern.PlacementBehaviour.RIGID));

        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"outposts/nether_brick/towers"), new ResourceLocation("empty"),
                ImmutableList.of(Pair.of(
                        new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/nether_brick/tower"), 1)),
                JigsawPattern.PlacementBehaviour.RIGID));

        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"outposts/nether_brick/plates"), new ResourceLocation("empty"),
                ImmutableList.of(Pair.of(
                        new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/nether_brick/plate"), 1)),
                JigsawPattern.PlacementBehaviour.RIGID));

        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"outposts/nether_brick/features"), new ResourceLocation("empty"),
                ImmutableList.of(
                        Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/nether_brick/cage1"), 1),
                        Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/nether_brick/cage2"), 1),
                        Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/nether_brick/fossil"), 1),
                        Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/nether_brick/tent1"), 1),
                        Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/nether_brick/tent2"), 1),
                        Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/nether_brick/targets"), 1)
                ),
                JigsawPattern.PlacementBehaviour.RIGID));
    }
}