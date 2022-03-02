package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.RSShipwreckNetherConfig;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PieceLimitedJigsawManager;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;

import java.util.Optional;


public class ShipwreckNetherStructure <C extends RSShipwreckNetherConfig> extends AbstractBaseStructure<C> {

    // Special thanks to cannon_foddr and miguelforge for allowing me to use their nether shipwreck design!
    public ShipwreckNetherStructure(Codec<C> codec) {
        super(codec, ShipwreckNetherStructure::isShipwreckNetherFeatureChunk, ShipwreckNetherStructure::generateShipwreckNetherPieces);
    }

    protected static <CC extends RSShipwreckNetherConfig> boolean isShipwreckNetherFeatureChunk(PieceGeneratorSupplier.Context<CC> context) {
        // Check to see if there some air where the structure wants to spawn.
        // Doesn't account for rotation of structure.
        ChunkPos chunkPos = context.chunkPos();
        BlockPos blockPos = new BlockPos(chunkPos.getMinBlockX(), context.chunkGenerator().getSeaLevel() + 1, chunkPos.getMinBlockZ());
        CC config = context.config();

        int checkRadius = 16;
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for(int xOffset = -checkRadius; xOffset <= checkRadius; xOffset += 8) {
            for(int zOffset = -checkRadius; zOffset <= checkRadius; zOffset += 8) {
                NoiseColumn blockView = context.chunkGenerator().getBaseColumn(xOffset + blockPos.getX(), zOffset + blockPos.getZ(), context.heightAccessor());
                for(int yOffset = 0; yOffset <= 30; yOffset += 5) {
                    mutable.set(blockPos).move(xOffset, yOffset, zOffset);
                    if (!blockView.getBlock(mutable.getY()).isAir()) {
                        return false;
                    }
                }
            }
        }

        //cannot be near other specified structure
        for (ResourceKey<StructureSet> structureSetToAvoid : config.structureSetToAvoid) {
            if (context.chunkGenerator().hasFeatureChunkInRange(structureSetToAvoid, context.seed(), chunkPos.x, chunkPos.z, config.structureAvoidRadius)) {
                return false;
            }
        }

        return true;
    }

    public static <CC extends RSShipwreckNetherConfig> Optional<PieceGenerator<CC>> generateShipwreckNetherPieces(PieceGeneratorSupplier.Context<CC> context) {
        CC config = context.config();
        BlockPos blockpos = new BlockPos(context.chunkPos().getMinBlockX(), context.chunkGenerator().getSeaLevel() + config.sealevelOffset, context.chunkPos().getMinBlockZ());

        return PieceLimitedJigsawManager.assembleJigsawStructure(
                context,
                new JigsawConfiguration(config.startPool, config.size),
                GeneralUtils.getCsfNameForConfig(config, context.registryAccess()),
                blockpos,
                false,
                false,
                Integer.MAX_VALUE,
                Integer.MIN_VALUE,
                (structurePiecesBuilder, pieces) -> GeneralUtils.centerAllPieces(blockpos, pieces));
    }
}