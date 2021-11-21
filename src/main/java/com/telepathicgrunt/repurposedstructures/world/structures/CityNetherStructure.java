package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.GenericJigsawStructureCodeConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PieceLimitedJigsawManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import org.apache.commons.lang3.mutable.Mutable;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class CityNetherStructure extends GenericJigsawStructure {

    public CityNetherStructure(Predicate<PieceGeneratorSupplier.Context> locationCheckPredicate, Function<PieceGeneratorSupplier.Context, Optional<PieceGenerator<NoneFeatureConfiguration>>> pieceCreationPredicate) {
        super(locationCheckPredicate, pieceCreationPredicate);
    }

    // Need this constructor wrapper so we can hackly call `this` in the predicates that Minecraft requires in constructors
    public static CityNetherStructure create(GenericJigsawStructureCodeConfig genericJigsawStructureCodeConfig) {
        final Mutable<CityNetherStructure> box = new MutableObject<>();
        final CityNetherStructure finalInstance = new CityNetherStructure(
                (context) -> box.getValue().isFeatureChunk(context, genericJigsawStructureCodeConfig),
                (context) -> box.getValue().generatePieces(context, genericJigsawStructureCodeConfig)
        );
        box.setValue(finalInstance);
        return finalInstance;
    }


    protected boolean isFeatureChunk(PieceGeneratorSupplier.Context context, GenericJigsawStructureCodeConfig config) {
        ChunkPos chunkPos = context.chunkPos();

        // do cheaper checks first
        if(super.isFeatureChunk(context, config)){

            // make sure land is open enough for city
            BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
            for (int curChunkX = chunkPos.x - 1; curChunkX <= chunkPos.x + 1; curChunkX++) {
                for (int curChunkZ = chunkPos.z - 1; curChunkZ <= chunkPos.z + 1; curChunkZ++) {
                    mutable.set(curChunkX << 4, context.chunkGenerator().getSeaLevel() + 10, curChunkZ << 4);
                    NoiseColumn blockView = context.chunkGenerator().getBaseColumn(mutable.getX(), mutable.getZ(), context.heightAccessor());
                    int minValidSpace = 65;
                    int maxHeight = Math.min(context.chunkGenerator().getGenDepth(), context.chunkGenerator().getSeaLevel() + minValidSpace);

                    while(mutable.getY() < maxHeight){
                        BlockState state = blockView.getBlock(mutable.getY());
                        if(!state.isAir()){
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

    public Optional<PieceGenerator<NoneFeatureConfiguration>> generatePieces(PieceGeneratorSupplier.Context context, GenericJigsawStructureCodeConfig config) {
        BlockPos blockpos = new BlockPos(context.chunkPos().getMinBlockX(), context.chunkGenerator().getSeaLevel(), context.chunkPos().getMinBlockZ());

        ResourceLocation structureID = Registry.STRUCTURE_FEATURE.getKey(this);
        return PieceLimitedJigsawManager.assembleJigsawStructure(
                context,
                new JigsawConfiguration(() -> context.registryAccess().registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(config.startPool), config.structureSize),
                structureID,
                blockpos,
                false,
                false,
                Integer.MAX_VALUE,
                Integer.MIN_VALUE,
                (pieces) -> {
                    pieces.get(0).move(0, config.centerOffset, 0);
                });
    }
}