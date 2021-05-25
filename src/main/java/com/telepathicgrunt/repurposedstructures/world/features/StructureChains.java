package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LanternBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;


public class StructureChains extends Feature<StructureTargetConfig> {

    public StructureChains(Codec<StructureTargetConfig> config) {
        super(config);
    }


    @Override
    public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos position, StructureTargetConfig config) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for(int i = 0; i < config.attempts; i++){
            mutable.set(position).move(
                    random.nextInt(11) - 5,
                    random.nextInt(3) - 1,
                    random.nextInt(11) - 5
            );

            if(!world.getBlockState(mutable).isAir() ||
                    // This seems to sometimes deadlock only on Forge. But not Fabric. What the fuck?
                    //!world.getLevel().structureFeatureManager().getStructureAt(mutable, true, config.targetStructure).isValid()
                    // Alternative. Won't follow the structure's bounds perfectly tho...
                   !world.startsForFeature(SectionPos.of(mutable), config.targetStructure).findAny().isPresent()
            ){
                continue;
            }

            // generates chains from given position down 1-8 blocks if path is clear and the given position is valid
            int length = 0;
            BlockState aboveBlockstate;
            boolean exitEarly = false;

            for (; mutable.getY() > 3 && length < random.nextInt(random.nextInt(random.nextInt(8) + 1) + 1) + 1; mutable.move(Direction.DOWN)) {
                if (world.isEmptyBlock(mutable)) {
                    aboveBlockstate = world.getBlockState(mutable.above());

                    if (aboveBlockstate.isFaceSturdy(world, mutable.above(), Direction.DOWN) || aboveBlockstate.is(Blocks.CHAIN)) {
                        world.setBlock(mutable, Blocks.CHAIN.defaultBlockState(), 2);
                        length++;
                    }
                }
                else {
                    exitEarly = true;
                }
            }

            if(exitEarly) continue;

            //attaches lantern at end at a rare chance
            if(mutable.getY() != 3 && random.nextFloat() < 0.075f && world.isEmptyBlock(mutable)){
                if(world.getBiome(mutable).getBiomeCategory() == Biome.Category.NETHER){
                    world.setBlock(mutable, Blocks.SOUL_LANTERN.defaultBlockState().setValue(LanternBlock.HANGING, true), 2);
                }
                else{
                    world.setBlock(mutable, Blocks.LANTERN.defaultBlockState().setValue(LanternBlock.HANGING, true), 2);
                }
            }
        }

        return true;
    }
}