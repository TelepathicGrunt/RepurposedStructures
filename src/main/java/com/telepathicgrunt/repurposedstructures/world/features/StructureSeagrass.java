package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallSeaGrassBlock;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;


public class StructureSeagrass extends Feature<StructureTargetConfig> {

    public StructureSeagrass(Codec<StructureTargetConfig> config) {
        super(config);
    }


    @Override
    public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos position, StructureTargetConfig config) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();
        BlockState tallSeagrass = Blocks.TALL_SEAGRASS.defaultBlockState();
        BlockState seagrass = Blocks.SEAGRASS.defaultBlockState();

        for(int i = 0; i < config.attempts; i++){
            mutable.set(position).move(
                    random.nextInt(7) - 3,
                    -1,
                    random.nextInt(7) - 3
            );

            boolean isWater = world.getBlockState(mutable).is(Blocks.WATER);
            if(!isWater) continue;

            boolean isWaterAbove = world.getBlockState(mutable.above()).is(Blocks.WATER);
            if(isWaterAbove && random.nextFloat() < 0.33f && tallSeagrass.canSurvive(world, mutable)){
                // expensive. Do this check very last
                // This seems to sometimes deadlock only on Forge. But not Fabric. What the fuck?
                //!world.getLevel().structureFeatureManager().getStructureAt(mutable, true, config.targetStructure).isValid()

                // Alternative. Won't follow the structure's bounds perfectly tho...
                if(!world.startsForFeature(SectionPos.of(mutable), config.targetStructure).findAny().isPresent()){
                    continue;
                }

                world.setBlock(mutable, tallSeagrass.setValue(TallSeaGrassBlock.HALF, DoubleBlockHalf.LOWER), 3);
                world.setBlock(mutable.move(Direction.UP), tallSeagrass.setValue(TallSeaGrassBlock.HALF, DoubleBlockHalf.UPPER), 3);
            }
            else if(seagrass.canSurvive(world, mutable)){
                // expensive. Do this check very last
                // This seems to sometimes deadlock only on Forge. But not Fabric. What the fuck?
                //!world.getLevel().structureFeatureManager().getStructureAt(mutable, true, config.targetStructure).isValid()

                // Alternative. Won't follow the structure's bounds perfectly tho...
                if(!world.startsForFeature(SectionPos.of(mutable), config.targetStructure).findAny().isPresent()){
                    continue;
                }

                world.setBlock(mutable, Blocks.SEAGRASS.defaultBlockState(), 3);
            }
        }

        return true;
    }
}