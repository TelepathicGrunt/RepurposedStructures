package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * FOR ELEMENTS THAT WANT TO CONVERT THEIR GRASS or DIRT INTO BIOME SPECIFIC BLOCKS
 */
public class BiomeSurfaceProcessor extends StructureProcessor {

    public static final Codec<BiomeSurfaceProcessor> CODEC = Codec.unit(BiomeSurfaceProcessor::new);

    private static final Map<IWorldReader, Map<Long, Biome>> MINI_BIOMEPOS_CACHE = new WeakHashMap<>();

    private BiomeSurfaceProcessor() { }

    @Override
    public Template.BlockInfo processBlock(IWorldReader worldView, BlockPos pos, BlockPos blockPos, Template.BlockInfo structureBlockInfoLocal, Template.BlockInfo structureBlockInfoWorld, PlacementSettings structurePlacementData) {
        BlockState structureBlock = structureBlockInfoWorld.state;

        if (structureBlock.is(Blocks.GRASS_BLOCK)) {
            BlockPos structurePos = structureBlockInfoWorld.pos;
            Biome biome = getCachedBiome(worldView, structurePos);

            BlockState topSurfaceBlock = biome.getGenerationSettings().getSurfaceBuilder().get().config.getTopMaterial();
            return new Template.BlockInfo(structurePos, topSurfaceBlock, structureBlockInfoWorld.nbt);
        }
        else if (structureBlock.is(Blocks.DIRT)) {
            BlockPos structurePos = structureBlockInfoWorld.pos;
            Biome biome = getCachedBiome(worldView, structurePos);

            BlockState underSurfaceBlock = biome.getGenerationSettings().getSurfaceBuilder().get().config.getUnderMaterial();
            return new Template.BlockInfo(structurePos, underSurfaceBlock, structureBlockInfoWorld.nbt);
        }
        return structureBlockInfoWorld;
    }

    private Biome getCachedBiome(IWorldReader worldView, BlockPos structurePos) {
        Map<Long, Biome> worldSpecificBiomes = MINI_BIOMEPOS_CACHE.computeIfAbsent(worldView, (keyPos) -> new HashMap<>());
        BlockPos biomePos = new BlockPos(structurePos.getX() >> 2, 0, structurePos.getZ() >> 2);
        Biome biome = worldSpecificBiomes.computeIfAbsent(biomePos.asLong(), (keyPos) -> worldView.getBiome(structurePos));
        if(worldSpecificBiomes.size() > 20) worldSpecificBiomes.clear();
        return biome;
    }

    @Override
    protected IStructureProcessorType<?> getType() {
        return RSProcessors.BIOME_SURFACE_PROCESSOR;
    }
}
