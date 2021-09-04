package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * FOR ELEMENTS THAT WANT TO CONVERT THEIR GRASS or DIRT INTO BIOME SPECIFIC BLOCKS
 */
public class BiomeSurfaceProcessor extends StructureProcessor {

    public static final Codec<BiomeSurfaceProcessor> CODEC = Codec.unit(BiomeSurfaceProcessor::new);

    private static final Map<LevelReader, Map<Long, Biome>> MINI_BIOMEPOS_CACHE = new WeakHashMap<>();

    private BiomeSurfaceProcessor() { }

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader worldView, BlockPos pos, BlockPos blockPos, StructureTemplate.StructureBlockInfo structureBlockInfoLocal, StructureTemplate.StructureBlockInfo structureBlockInfoWorld, StructurePlaceSettings structurePlacementData) {
        BlockState structureBlock = structureBlockInfoWorld.state;

        if (structureBlock.is(Blocks.GRASS_BLOCK)) {
            BlockPos structurePos = structureBlockInfoWorld.pos;
            Biome biome = getCachedBiome(worldView, structurePos);

            BlockState topSurfaceBlock = biome.getGenerationSettings().getSurfaceBuilder().get().config.getTopMaterial();
            return new StructureTemplate.StructureBlockInfo(structurePos, topSurfaceBlock, structureBlockInfoWorld.nbt);
        }
        else if (structureBlock.is(Blocks.DIRT)) {
            BlockPos structurePos = structureBlockInfoWorld.pos;
            Biome biome = getCachedBiome(worldView, structurePos);

            BlockState underSurfaceBlock = biome.getGenerationSettings().getSurfaceBuilder().get().config.getUnderMaterial();
            return new StructureTemplate.StructureBlockInfo(structurePos, underSurfaceBlock, structureBlockInfoWorld.nbt);
        }
        return structureBlockInfoWorld;
    }

    private Biome getCachedBiome(LevelReader worldView, BlockPos structurePos) {
        Map<Long, Biome> worldSpecificBiomes = MINI_BIOMEPOS_CACHE.computeIfAbsent(worldView, (keyPos) -> new HashMap<>());
        BlockPos biomePos = new BlockPos(structurePos.getX() >> 2, 0, structurePos.getZ() >> 2);
        Biome biome = worldSpecificBiomes.computeIfAbsent(biomePos.asLong(), (keyPos) -> worldView.getBiome(structurePos));
        if(worldSpecificBiomes.size() > 20) worldSpecificBiomes.clear();
        return biome;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.BIOME_SURFACE_PROCESSOR;
    }
}
