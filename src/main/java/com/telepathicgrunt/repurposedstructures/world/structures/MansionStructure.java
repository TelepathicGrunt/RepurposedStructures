package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.MansionPieces;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.MansionStructurePiece;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.CheckerboardColumnBiomeSource;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.PiecesContainer;
import net.minecraft.world.level.material.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;


public class MansionStructure extends Structure {

    public static final Codec<MansionStructure> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            MansionStructure.settingsCodec(instance),
            Codec.STRING.fieldOf("mansion_type").forGetter(structure -> structure.mansionType),
            BlockState.CODEC.fieldOf("foundation_block").forGetter(structure -> structure.foundationBlock),
            Codec.BOOL.fieldOf("pillar_only_to_land").orElse(true).forGetter(structure -> structure.pillarOnlyToLand),
            Codec.intRange(1, 100).optionalFieldOf("valid_biome_radius_check").forGetter(structure -> structure.biomeRadius)
    ).apply(instance, MansionStructure::new));

    public final String mansionType;
    public final BlockState foundationBlock;
    public final boolean pillarOnlyToLand;
    public final Optional<Integer> biomeRadius;
    public MansionStructure(Structure.StructureSettings config,
                            String mansionType, 
                            BlockState foundationBlock, 
                            boolean pillarOnlyToLand,
                            Optional<Integer> biomeRadius
    ) {
        super(config);
        this.mansionType = mansionType.toLowerCase(Locale.ROOT);
        this.foundationBlock = foundationBlock;
        this.pillarOnlyToLand = pillarOnlyToLand;
        this.biomeRadius = biomeRadius;
    }
    
    protected boolean extraSpawningChecks(GenerationContext context, BlockPos blockPos) {
        ChunkPos chunkPos = context.chunkPos();

        if (this.biomeRadius.isPresent() && !(context.biomeSource() instanceof CheckerboardColumnBiomeSource)) {
            int validBiomeRange = this.biomeRadius.get();
            for (int curChunkX = chunkPos.x - validBiomeRange; curChunkX <= chunkPos.x + validBiomeRange; curChunkX++) {
                for (int curChunkZ = chunkPos.z - validBiomeRange; curChunkZ <= chunkPos.z + validBiomeRange; curChunkZ++) {
                    Holder<Biome> biome = context.biomeSource().getNoiseBiome(QuartPos.fromSection(curChunkX), QuartPos.fromBlock(blockPos.getY()), QuartPos.fromSection(curChunkZ), context.randomState().sampler());
                    if (!context.validBiome().test(biome)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public Optional<Structure.GenerationStub> findGenerationPoint(Structure.GenerationContext context) {
        ChunkPos chunkPos = context.chunkPos();

        WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));
        random.setLargeFeatureSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);
        Rotation blockRotation = Rotation.getRandom(random);

        int xOffset = 5;
        int zOffset = 5;
        if (blockRotation == Rotation.CLOCKWISE_90) {
            xOffset = -5;
        }
        else if (blockRotation == Rotation.CLOCKWISE_180) {
            xOffset = -5;
            zOffset = -5;
        }
        else if (blockRotation == Rotation.COUNTERCLOCKWISE_90) {
            zOffset = -5;
        }

        int centerX = chunkPos.getMiddleBlockX();
        int centerZ = chunkPos.getMiddleBlockZ();
        int firstHeight = context.chunkGenerator().getFirstOccupiedHeight(centerX, centerZ, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState());
        int secondHeight = context.chunkGenerator().getFirstOccupiedHeight(centerX, centerZ + zOffset, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState());
        int thirdHeight = context.chunkGenerator().getFirstOccupiedHeight(centerX + xOffset, centerZ, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState());
        int forthheight = context.chunkGenerator().getFirstOccupiedHeight(centerX + xOffset, centerZ + zOffset, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState());
        int finalheight = Math.min(Math.min(firstHeight, secondHeight), Math.min(thirdHeight, forthheight));

        if(finalheight <= context.chunkGenerator().getMinY())
            return Optional.empty();

        if (!extraSpawningChecks(context, chunkPos.getMiddleBlockPosition(finalheight))) {
            return Optional.empty();
        }

        return Optional.of(new Structure.GenerationStub(new BlockPos(centerX, finalheight + 1, centerZ), (structurePiecesBuilder) -> {
            BlockPos blockPos = new BlockPos(chunkPos.getMiddleBlockX(), finalheight + 1, chunkPos.getMiddleBlockZ());
            List<StructurePiece> list = new ArrayList<>();
            MansionPieces.createMansionLayout(context.registryAccess(), context.structureTemplateManager(), blockPos, blockRotation, list, random, this.mansionType);
            list.forEach(piece -> {
                if (piece instanceof PoolElementStructurePiece poolElementStructurePiece) {
                    structurePiecesBuilder.addPiece(new MansionStructurePiece(poolElementStructurePiece, this.mansionType, this.foundationBlock, this.pillarOnlyToLand));
                }
                else {
                    structurePiecesBuilder.addPiece(piece);
                }
            });
        }));
    }

    @Override
    public void afterPlace(WorldGenLevel level, StructureManager structureManager , ChunkGenerator chunkGenerator, RandomSource random, BoundingBox boundingBox, ChunkPos chunkPos, PiecesContainer piecesContainer) {
        if (!piecesContainer.isEmpty() && piecesContainer.pieces().get(0) instanceof MansionStructurePiece mansionStructurePiece) {
            BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
            BoundingBox box = piecesContainer.calculateBoundingBox();
            int structureBottomY = box.minY();
            int terrainY = Integer.MIN_VALUE;

            for(int x = box.minX(); x <= box.maxX(); ++x) {
                for(int z = box.minZ(); z <= box.maxZ(); ++z) {
                    if (chunkPos.x != x >> 4 || chunkPos.z != z >> 4) {
                        continue;
                    }

                    mutableBlockPos.set(x, structureBottomY, z);
                    if(mansionStructurePiece.pillarOnlyToLand) {
                        terrainY = GeneralUtils.getFirstLandYFromPos(level, mutableBlockPos.below());
                        if(terrainY <= chunkGenerator.getMinY()) {
                            continue;
                        }
                    }

                    if (!level.isEmptyBlock(mutableBlockPos) && box.isInside(mutableBlockPos) && piecesContainer.isInsidePiece(mutableBlockPos)) {
                        for(int currentY = structureBottomY - 1; currentY > chunkGenerator.getMinY(); --currentY) {
                            if(mansionStructurePiece.pillarOnlyToLand) {
                                if(currentY <= terrainY) {
                                    break;
                                }
                            }

                            BlockPos blockPos2 = new BlockPos(x, currentY, z);
                            Material material = level.getBlockState(blockPos2).getMaterial();
                            if (!level.isEmptyBlock(blockPos2) &&
                                !material.isLiquid() &&
                                material != Material.REPLACEABLE_PLANT &&
                                material != Material.REPLACEABLE_FIREPROOF_PLANT &&
                                material != Material.REPLACEABLE_WATER_PLANT)
                            {
                                break;
                            }

                            level.setBlock(blockPos2, mansionStructurePiece.foundationBlock, 2);
                        }
                    }
                }
            }
        }
    }

    @Override
    public StructureType<?> type() {
        return RSStructures.MANSION_STRUCTURE;
    }
}