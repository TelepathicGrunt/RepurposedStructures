package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.structure.processor.StructureProcessorLists;
import net.minecraft.util.Identifier;


public class ShipwreckPools {
    public static void initPools(){
        //End Shipwreck
        StructurePools.register(
                new StructurePool(new Identifier(RepurposedStructures.MODID,"shipwrecks/end"), new Identifier("empty"),
                        ImmutableList.of(
                                Pair.of(StructurePoolElement.method_30426(RepurposedStructures.MODID+":shipwrecks/end/rightsideup_backhalf", StructureProcessorLists.EMPTY), 1),
                                Pair.of(StructurePoolElement.method_30426(RepurposedStructures.MODID+":shipwrecks/end/rightsideup_backhalf_degraded", StructureProcessorLists.EMPTY), 1),
                                Pair.of(StructurePoolElement.method_30426(RepurposedStructures.MODID+":shipwrecks/end/rightsideup_fronthalf", StructureProcessorLists.EMPTY), 1),
                                Pair.of(StructurePoolElement.method_30426(RepurposedStructures.MODID+":shipwrecks/end/rightsideup_fronthalf_degraded", StructureProcessorLists.EMPTY), 1),
                                Pair.of(StructurePoolElement.method_30426(RepurposedStructures.MODID+":shipwrecks/end/rightsideup_full", StructureProcessorLists.EMPTY), 1),
                                Pair.of(StructurePoolElement.method_30426(RepurposedStructures.MODID+":shipwrecks/end/rightsideup_full_degraded", StructureProcessorLists.EMPTY), 1),
                                Pair.of(StructurePoolElement.method_30426(RepurposedStructures.MODID+":shipwrecks/end/sideways_backhalf", StructureProcessorLists.EMPTY), 1),
                                Pair.of(StructurePoolElement.method_30426(RepurposedStructures.MODID+":shipwrecks/end/sideways_backhalf_degraded", StructureProcessorLists.EMPTY), 1),
                                Pair.of(StructurePoolElement.method_30426(RepurposedStructures.MODID+":shipwrecks/end/sideways_fronthalf", StructureProcessorLists.EMPTY), 1),
                                Pair.of(StructurePoolElement.method_30426(RepurposedStructures.MODID+":shipwrecks/end/sideways_fronthalf_degraded", StructureProcessorLists.EMPTY), 1),
                                Pair.of(StructurePoolElement.method_30426(RepurposedStructures.MODID+":shipwrecks/end/sideways_full", StructureProcessorLists.EMPTY), 1),
                                Pair.of(StructurePoolElement.method_30426(RepurposedStructures.MODID+":shipwrecks/end/sideways_full_degraded", StructureProcessorLists.EMPTY), 1),
                                Pair.of(StructurePoolElement.method_30426(RepurposedStructures.MODID+":shipwrecks/end/upsidedown_backhalf", StructureProcessorLists.EMPTY), 1),
                                Pair.of(StructurePoolElement.method_30426(RepurposedStructures.MODID+":shipwrecks/end/upsidedown_backhalf_degraded", StructureProcessorLists.EMPTY), 1),
                                Pair.of(StructurePoolElement.method_30426(RepurposedStructures.MODID+":shipwrecks/end/upsidedown_fronthalf_degraded", StructureProcessorLists.EMPTY), 1),
                                Pair.of(StructurePoolElement.method_30426(RepurposedStructures.MODID+":shipwrecks/end/upsidedown_fronthalf", StructureProcessorLists.EMPTY), 1),
                                Pair.of(StructurePoolElement.method_30426(RepurposedStructures.MODID+":shipwrecks/end/upsidedown_full", StructureProcessorLists.EMPTY), 1),
                                Pair.of(StructurePoolElement.method_30426(RepurposedStructures.MODID+":shipwrecks/end/upsidedown_full_degraded", StructureProcessorLists.EMPTY), 1)),
                        StructurePool.Projection.RIGID));
    }
}