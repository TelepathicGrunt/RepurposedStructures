package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPatternRegistry;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.template.ProcessorLists;


public class ShipwreckPools {
    public static void initPools(){
        //End Shipwreck
        JigsawPatternRegistry.register(
                new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"shipwrecks/end"), new ResourceLocation("empty"),
                        ImmutableList.of(
                                Pair.of(JigsawPiece.method_30426(RepurposedStructures.MODID+":shipwrecks/end/rightsideup_backhalf", ProcessorLists.EMPTY), 1),
                                Pair.of(JigsawPiece.method_30426(RepurposedStructures.MODID+":shipwrecks/end/rightsideup_backhalf_degraded", ProcessorLists.EMPTY), 1),
                                Pair.of(JigsawPiece.method_30426(RepurposedStructures.MODID+":shipwrecks/end/rightsideup_fronthalf", ProcessorLists.EMPTY), 1),
                                Pair.of(JigsawPiece.method_30426(RepurposedStructures.MODID+":shipwrecks/end/rightsideup_fronthalf_degraded", ProcessorLists.EMPTY), 1),
                                Pair.of(JigsawPiece.method_30426(RepurposedStructures.MODID+":shipwrecks/end/rightsideup_full", ProcessorLists.EMPTY), 1),
                                Pair.of(JigsawPiece.method_30426(RepurposedStructures.MODID+":shipwrecks/end/rightsideup_full_degraded", ProcessorLists.EMPTY), 1),
                                Pair.of(JigsawPiece.method_30426(RepurposedStructures.MODID+":shipwrecks/end/sideways_backhalf", ProcessorLists.EMPTY), 1),
                                Pair.of(JigsawPiece.method_30426(RepurposedStructures.MODID+":shipwrecks/end/sideways_backhalf_degraded", ProcessorLists.EMPTY), 1),
                                Pair.of(JigsawPiece.method_30426(RepurposedStructures.MODID+":shipwrecks/end/sideways_fronthalf", ProcessorLists.EMPTY), 1),
                                Pair.of(JigsawPiece.method_30426(RepurposedStructures.MODID+":shipwrecks/end/sideways_fronthalf_degraded", ProcessorLists.EMPTY), 1),
                                Pair.of(JigsawPiece.method_30426(RepurposedStructures.MODID+":shipwrecks/end/sideways_full", ProcessorLists.EMPTY), 1),
                                Pair.of(JigsawPiece.method_30426(RepurposedStructures.MODID+":shipwrecks/end/sideways_full_degraded", ProcessorLists.EMPTY), 1),
                                Pair.of(JigsawPiece.method_30426(RepurposedStructures.MODID+":shipwrecks/end/upsidedown_backhalf", ProcessorLists.EMPTY), 1),
                                Pair.of(JigsawPiece.method_30426(RepurposedStructures.MODID+":shipwrecks/end/upsidedown_backhalf_degraded", ProcessorLists.EMPTY), 1),
                                Pair.of(JigsawPiece.method_30426(RepurposedStructures.MODID+":shipwrecks/end/upsidedown_fronthalf_degraded", ProcessorLists.EMPTY), 1),
                                Pair.of(JigsawPiece.method_30426(RepurposedStructures.MODID+":shipwrecks/end/upsidedown_fronthalf", ProcessorLists.EMPTY), 1),
                                Pair.of(JigsawPiece.method_30426(RepurposedStructures.MODID+":shipwrecks/end/upsidedown_full", ProcessorLists.EMPTY), 1),
                                Pair.of(JigsawPiece.method_30426(RepurposedStructures.MODID+":shipwrecks/end/upsidedown_full_degraded", ProcessorLists.EMPTY), 1)),
                        JigsawPattern.PlacementBehaviour.RIGID));
    }
}