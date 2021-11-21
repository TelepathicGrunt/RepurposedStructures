package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs.ShipwreckNetherCodeConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PieceLimitedJigsawManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import org.apache.commons.lang3.mutable.Mutable;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;


public class ShipwreckNetherStructure extends AbstractBaseStructure<NoneFeatureConfiguration> {
    // Special thanks to cannon_foddr and miguelforge for allowing me to use their nether shipwreck design!

    public ShipwreckNetherStructure(Predicate<PieceGeneratorSupplier.Context> locationCheckPredicate, Function<PieceGeneratorSupplier.Context, Optional<PieceGenerator<NoneFeatureConfiguration>>> pieceCreationPredicate) {
        super(NoneFeatureConfiguration.CODEC, locationCheckPredicate, pieceCreationPredicate);
    }

    // Need this constructor wrapper so we can hackly call `this` in the predicates that Minecraft requires in constructors
    public static ShipwreckNetherStructure create(ShipwreckNetherCodeConfig shipwreckNetherCodeConfig) {
        final Mutable<ShipwreckNetherStructure> box = new MutableObject<>();
        final ShipwreckNetherStructure finalInstance = new ShipwreckNetherStructure(
                (context) -> box.getValue().isFeatureChunk(context, shipwreckNetherCodeConfig),
                (context) -> box.getValue().generatePieces(context, shipwreckNetherCodeConfig)
        );
        box.setValue(finalInstance);
        return finalInstance;
    }

    protected boolean isFeatureChunk(PieceGeneratorSupplier.Context context, ShipwreckNetherCodeConfig config) {
        // Check to see if there some air where the structure wants to spawn.
        // Doesn't account for rotation of structure.
        ChunkPos chunkPos = context.chunkPos();
        BlockPos blockPos = new BlockPos(chunkPos.getMinBlockX(), context.chunkGenerator().getSeaLevel() + 1, chunkPos.getMinBlockZ());

        int checkRadius = 16;
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for(int xOffset = -checkRadius; xOffset <= checkRadius; xOffset += 8){
            for(int zOffset = -checkRadius; zOffset <= checkRadius; zOffset += 8){
                NoiseColumn blockView = context.chunkGenerator().getBaseColumn(xOffset + blockPos.getX(), zOffset + blockPos.getZ(), context.heightAccessor());
                for(int yOffset = 0; yOffset <= 30; yOffset += 5){
                    mutable.set(blockPos).move(xOffset, yOffset, zOffset);
                    if (!blockView.getBlock(mutable.getY()).isAir()) {
                        return false;
                    }
                }
            }
        }

        //cannot be near any other structure
        int structureCheckRadius = 3;
        for (int curChunkX = chunkPos.x - structureCheckRadius; curChunkX <= chunkPos.x + structureCheckRadius; curChunkX++) {
            for (int curChunkZ = chunkPos.z - structureCheckRadius; curChunkZ <= chunkPos.z + structureCheckRadius; curChunkZ++) {
                for(StructureFeature<?> structureFeature : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE)){
                    if(structureFeature == this) continue;

                    StructureFeatureConfiguration structureConfig = context.chunkGenerator().getSettings().getConfig(structureFeature);
                    if(structureConfig != null && structureConfig.spacing() > 8){
                        ChunkPos chunkPos2 = structureFeature.getPotentialFeatureChunk(structureConfig, context.seed(), curChunkX, curChunkZ);
                        if (curChunkX == chunkPos2.x && curChunkZ == chunkPos2.z) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public Optional<PieceGenerator<NoneFeatureConfiguration>> generatePieces(PieceGeneratorSupplier.Context context, ShipwreckNetherCodeConfig config) {
        BlockPos blockpos = new BlockPos(context.chunkPos().getMinBlockX(), context.chunkGenerator().getSeaLevel() + config.sealevelOffset, context.chunkPos().getMinBlockZ());

        ResourceLocation structureID = Registry.STRUCTURE_FEATURE.getKey(this);
        return PieceLimitedJigsawManager.assembleJigsawStructure(
                context,
                new JigsawConfiguration(() -> context.registryAccess().registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(config.startPool), 6),
                structureID,
                blockpos,
                false,
                false,
                Integer.MAX_VALUE,
                Integer.MIN_VALUE,
                (pieces) -> {
                    GeneralUtils.centerAllPieces(blockpos, pieces);
                });
    }
}