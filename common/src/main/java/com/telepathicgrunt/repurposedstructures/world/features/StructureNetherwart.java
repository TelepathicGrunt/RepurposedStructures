package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;


public record StructureNetherwart(int attempts) implements Feature {

    public static final MapCodec<StructureNetherwart> CODEC = RecordCodecBuilder.mapCodec((structureNetherwartInstance) -> structureNetherwartInstance.group(
            Codec.intRange(1, 1000000).fieldOf("attempts").forGetter(structureNetherwart -> structureNetherwart.attempts)
    ).apply(structureNetherwartInstance, StructureNetherwart::new));

    @Override
    public MapCodec<StructureNetherwart> codec() {
        return CODEC;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        BlockState netherwart = Blocks.NETHER_WART.defaultBlockState();

        for(int i = 0; i < attempts; i++) {
            mutable.set(origin).move(
                    random.nextInt(10) - 5,
                    -1,
                    random.nextInt(10) - 5
            );

            if(netherwart.canSurvive(level, mutable)) {
                

                level.setBlock(mutable, netherwart.setValue(NetherWartBlock.AGE, random.nextInt(4)), 3);
            }
        }

        return true;
    }
}