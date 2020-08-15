package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Lifecycle;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.StructureProcessorListAccessor;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.structure.processor.RuleStructureProcessor;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.structure.processor.StructureProcessorLists;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.RandomBlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.RegistryKey;


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