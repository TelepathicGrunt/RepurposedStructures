package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.RSGenericNetherConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PieceLimitedJigsawManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;

import java.util.Optional;

public class CityNetherStructure <C extends RSGenericNetherConfig> extends GenericJigsawStructure<C> {

    public CityNetherStructure(Codec<C> codec) {
        super(codec, CityNetherStructure::isCityNetherFeatureChunk, CityNetherStructure::generateCityNetherPieces);
    }

    protected static <CC extends RSGenericNetherConfig> boolean isCityNetherFeatureChunk(PieceGeneratorSupplier.Context<CC> context) {
        ChunkPos chunkPos = context.chunkPos();

        // do cheaper checks first
        if(GenericJigsawStructure.isGenericFeatureChunk(context)) {

            // make sure land is open enough for city
            BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
            for (int curChunkX = chunkPos.x - 1; curChunkX <= chunkPos.x + 1; curChunkX++) {
                for (int curChunkZ = chunkPos.z - 1; curChunkZ <= chunkPos.z + 1; curChunkZ++) {
                    mutable.set(curChunkX << 4, context.chunkGenerator().getSeaLevel() + 10, curChunkZ << 4);
                    NoiseColumn blockView = context.chunkGenerator().getBaseColumn(mutable.getX(), mutable.getZ(), context.heightAccessor());
                    int minValidSpace = 65;
                    int maxHeight = Math.min(GeneralUtils.getMaxTerrainLimit(context.chunkGenerator()), context.chunkGenerator().getSeaLevel() + minValidSpace);

                    while(mutable.getY() < maxHeight) {
                        BlockState state = blockView.getBlock(mutable.getY());
                        if(!state.isAir()) {
                            return false;
                        }
                        mutable.move(Direction.UP);
                    }
                }
            }
        }
        else {
            return false;
        }

        return true;
    }

    public static <CC extends RSGenericNetherConfig> Optional<PieceGenerator<CC>> generateCityNetherPieces(PieceGeneratorSupplier.Context<CC> context) {
        CC config = context.config();
        BlockPos blockpos = new BlockPos(context.chunkPos().getMinBlockX(), context.chunkGenerator().getSeaLevel(), context.chunkPos().getMinBlockZ());

        return PieceLimitedJigsawManager.assembleJigsawStructure(
                context,
                new JigsawConfiguration(config.startPool, config.size),
                GeneralUtils.getCsfNameForConfig(config, context.registryAccess()),
                blockpos,
                false,
                false,
                Integer.MAX_VALUE,
                Integer.MIN_VALUE,
                config.poolsThatIgnoreBoundaries,
                (structurePiecesBuilder, pieces) -> pieces.get(0).move(0, config.centerYOffset, 0));
    }
}