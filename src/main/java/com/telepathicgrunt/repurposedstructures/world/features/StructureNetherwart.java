package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;


public class StructureNetherwart extends Feature<StructureTargetConfig> {

    public StructureNetherwart(Codec<StructureTargetConfig> config) {
        super(config);
    }


    @Override
    public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos position, StructureTargetConfig config) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();
        BlockState netherwart = Blocks.NETHER_WART.defaultBlockState();

        for(int i = 0; i < config.attempts; i++){
            mutable.set(position).move(
                    random.nextInt(10) - 5,
                    -1,
                    random.nextInt(10) - 5
            );

            if(netherwart.canSurvive(world, mutable)){
                // expensive. Do this check very last
                // This seems to sometimes deadlock only on Forge. But not Fabric.
                //!world.getLevel().structureFeatureManager().getStructureAt(mutable, true, config.targetStructure).isValid()

                // Alternative. Won't follow the structure's bounds perfectly tho...
                if(!world.startsForFeature(SectionPos.of(mutable), config.targetStructure).findAny().isPresent()){
                    continue;
                }

                world.setBlock(mutable, netherwart.setValue(NetherWartBlock.AGE, random.nextInt(4)), 3);
            }
        }

        return true;
    }
}