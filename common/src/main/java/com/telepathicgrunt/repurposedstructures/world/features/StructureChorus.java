package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChorusFlowerBlock;
import net.minecraft.world.level.block.ChorusPlantBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;


public record StructureChorus(int attempts) implements Feature {

    public static final MapCodec<StructureChorus> CODEC = RecordCodecBuilder.mapCodec((structureChorusInstance) -> structureChorusInstance.group(
            Codec.intRange(1, 1000000).fieldOf("attempts").forGetter(structureChorus -> structureChorus.attempts)
    ).apply(structureChorusInstance, StructureChorus::new));

    @Override
    public MapCodec<StructureChorus> codec() {
        return CODEC;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        BlockState chorusFlower = Blocks.CHORUS_FLOWER.defaultBlockState();

        for(int i = 0; i < attempts; i++) {
            mutable.set(origin).move(
                    random.nextInt(7) - 3,
                    -1,
                    random.nextInt(7) - 3
            );

            if(level.getBlockState(mutable).isAir() && level.getBlockState(mutable.above()).isAir() && level.getBlockState(mutable.move(Direction.DOWN)).canOcclude()) {
                

                level.setBlock(mutable, Blocks.END_STONE.defaultBlockState(), 3);
                if(random.nextFloat() < 0.33f) {
                    level.setBlock(
                            mutable.move(Direction.UP),
                            chorusFlower.setValue(ChorusFlowerBlock.AGE, 5 - random.nextInt(random.nextInt(6) + 1)),
                            3);
                    continue;
                }

                // check to make sure this chorus stem can be placed
                boolean isValidSpot = true;
                for(Direction direction : Direction.Plane.HORIZONTAL) {
                    mutable.move(direction);
                    if(level.getBlockState(mutable).is(Blocks.CHORUS_PLANT)) {
                        isValidSpot = false;
                        break;
                    }
                    mutable.move(direction.getOpposite());
                }
                if(!isValidSpot) continue;

                mutable.move(Direction.UP);
                level.setBlock(mutable,
                        Blocks.CHORUS_PLANT.defaultBlockState()
                                .setValue(ChorusPlantBlock.DOWN, true)
                                .setValue(ChorusPlantBlock.UP, true),
                        3);

                Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
                if(random.nextFloat() < 0.33f || !level.getBlockState(mutable.relative(direction)).isAir()) {
                    level.setBlock(
                            mutable.move(Direction.UP),
                            chorusFlower.setValue(ChorusFlowerBlock.AGE, 5 - random.nextInt(random.nextInt(6) + 1)),
                            3);
                    continue;
                }

                level.setBlock(mutable.move(Direction.UP),
                        Blocks.CHORUS_PLANT.defaultBlockState()
                            .setValue(ChorusPlantBlock.DOWN, true)
                            .setValue(PipeBlock.PROPERTY_BY_DIRECTION.get(direction), true),
                        3);

                level.setBlock(mutable.move(direction), chorusFlower.setValue(ChorusFlowerBlock.AGE, random.nextInt(5)), 3);
            }
        }

        return true;
    }
}