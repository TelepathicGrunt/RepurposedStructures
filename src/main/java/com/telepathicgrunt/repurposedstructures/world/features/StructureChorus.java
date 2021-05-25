package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetConfig;
import net.minecraft.block.*;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;


public class StructureChorus extends Feature<StructureTargetConfig> {

    public StructureChorus(Codec<StructureTargetConfig> config) {
        super(config);
    }


    @Override
    public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos position, StructureTargetConfig config) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();
        BlockState chorusFlower = Blocks.CHORUS_FLOWER.defaultBlockState();

        for(int i = 0; i < config.attempts; i++){
            mutable.set(position).move(
                    random.nextInt(7) - 3,
                    -1,
                    random.nextInt(7) - 3
            );

            if(world.getBlockState(mutable).isAir() && world.getBlockState(mutable.above()).isAir() && world.getBlockState(mutable.move(Direction.DOWN)).canOcclude()){
                // expensive. Do this check very last
                // This seems to sometimes deadlock only on Forge. But not Fabric. What the fuck?
                //!world.getLevel().structureFeatureManager().getStructureAt(mutable, true, config.targetStructure).isValid()

                // Alternative. Won't follow the structure's bounds perfectly tho...
                if(!world.startsForFeature(SectionPos.of(mutable), config.targetStructure).findAny().isPresent()){
                    continue;
                }

                world.setBlock(mutable, Blocks.END_STONE.defaultBlockState(), 3);
                if(random.nextFloat() < 0.33f){
                    world.setBlock(
                            mutable.move(Direction.UP),
                            chorusFlower.setValue(ChorusFlowerBlock.AGE, 5 - random.nextInt(random.nextInt(6) + 1)),
                            3);
                    continue;
                }

                // check to make sure this chorus stem can be placed
                boolean isValidSpot = true;
                for(Direction direction : Direction.Plane.HORIZONTAL){
                    mutable.move(direction);
                    if(world.getBlockState(mutable).is(Blocks.CHORUS_PLANT)){
                        isValidSpot = false;
                        break;
                    }
                    mutable.move(direction.getOpposite());
                }
                if(!isValidSpot) continue;

                mutable.move(Direction.UP);
                world.setBlock(mutable,
                        Blocks.CHORUS_PLANT.defaultBlockState()
                                .setValue(ChorusPlantBlock.DOWN, true)
                                .setValue(ChorusPlantBlock.UP, true),
                        3);

                Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
                if(random.nextFloat() < 0.33f || !world.getBlockState(mutable.relative(direction)).isAir()) {
                    world.setBlock(
                            mutable.move(Direction.UP),
                            chorusFlower.setValue(ChorusFlowerBlock.AGE, 5 - random.nextInt(random.nextInt(6) + 1)),
                            3);
                    continue;
                }

                world.setBlock(mutable.move(Direction.UP),
                        Blocks.CHORUS_PLANT.defaultBlockState()
                            .setValue(ChorusPlantBlock.DOWN, true)
                            .setValue(SixWayBlock.PROPERTY_BY_DIRECTION.get(direction), true),
                        3);

                world.setBlock(mutable.move(direction), chorusFlower.setValue(ChorusFlowerBlock.AGE, random.nextInt(5)), 3);
            }
        }

        return true;
    }
}