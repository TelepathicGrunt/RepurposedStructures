package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

/**
 * FOR ELEMENTS THAT CANNOT SPAWN IN VIEW OF THE SKY
 */
public class MineshaftSkyViewProcessor extends StructureProcessor {

    public static final Codec<MineshaftSkyViewProcessor> CODEC = Codec.unit(MineshaftSkyViewProcessor::new);

    private MineshaftSkyViewProcessor() {}

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader worldView, BlockPos pos, BlockPos blockPos, StructureTemplate.StructureBlockInfo structureBlockInfoLocal, StructureTemplate.StructureBlockInfo structureBlockInfoWorld, StructurePlaceSettings structurePlacementData) {

        // Mimic Mineshaft rails visible even in sky if block below is solid
        if(structureBlockInfoWorld.state.is(Blocks.RAIL) && worldView.getBlockState(structureBlockInfoWorld.pos.below()).canOcclude()){
            return structureBlockInfoWorld;
        }

        // Will not place blocks if out in open and visible to sky.
        if (!structureBlockInfoWorld.state.is(Blocks.CAVE_AIR) || worldView.getBlockState(structureBlockInfoWorld.pos).getFluidState().is(FluidTags.WATER)) {
            if(structureBlockInfoWorld.pos.getY() > worldView.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, structureBlockInfoWorld.pos.getX(), structureBlockInfoWorld.pos.getZ())) {
                return null;
            }
        }

        return structureBlockInfoWorld;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.MINESHAFT_SKY_VIEW_PROCESSOR;
    }
}
