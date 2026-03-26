package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.mixins.structures.PoolElementStructurePieceAccessor;
import com.telepathicgrunt.repurposedstructures.mixins.structures.SinglePoolElementAccessor;
import com.telepathicgrunt.repurposedstructures.mixins.structures.StructurePieceAccessor;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.MonumentPieces;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.CheckerboardColumnBiomeSource;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.List;
import java.util.Locale;
import java.util.Optional;


public class MonumentStructure extends Structure {

    public static final MapCodec<MonumentStructure> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            MonumentStructure.settingsCodec(instance),
            Codec.STRING.fieldOf("monument_type").forGetter(config -> config.monumentType),
            Codec.intRange(1, 100).optionalFieldOf("valid_biome_radius_check").forGetter(config -> config.biomeRadius),
            Codec.INT.optionalFieldOf("fixed_y_spawn").forGetter(config -> config.fixedYSpawn),
            Codec.FLOAT.optionalFieldOf("center_terrain_height_weight").forGetter(config -> config.centerTerrainHeightWeight),
            LiquidSettings.CODEC.optionalFieldOf("liquid_settings", JigsawStructure.DEFAULT_LIQUID_SETTINGS).forGetter(structure -> structure.liquidSettings)
    ).apply(instance, MonumentStructure::new));

    public final String monumentType;
    public final Optional<Integer> biomeRadius;
    public final Optional<Integer> fixedYSpawn;
    public final Optional<Float> centerTerrainHeightWeight;
    public final LiquidSettings liquidSettings;

    public MonumentStructure(Structure.StructureSettings config,
                             String monumentType,
                             Optional<Integer> biomeRadius,
                             Optional<Integer> fixedYSpawn,
                             Optional<Float> centerTerrainHeightWeight,
                             LiquidSettings liquidSettings
    ) {
        super(config);
        this.monumentType = monumentType.toLowerCase(Locale.ROOT);
        this.biomeRadius = biomeRadius;
        this.fixedYSpawn = fixedYSpawn;
        this.centerTerrainHeightWeight = centerTerrainHeightWeight;
        this.liquidSettings = liquidSettings;
    }

    protected boolean extraSpawningChecks(GenerationContext context, BlockPos blockPos) {
        ChunkPos chunkPos = context.chunkPos();

        if (this.biomeRadius.isPresent() && !(context.biomeSource() instanceof CheckerboardColumnBiomeSource)) {
            int validBiomeRange = this.biomeRadius.get();
            for (int curChunkX = chunkPos.x() - validBiomeRange; curChunkX <= chunkPos.x() + validBiomeRange; curChunkX++) {
                for (int curChunkZ = chunkPos.z() - validBiomeRange; curChunkZ <= chunkPos.z() + validBiomeRange; curChunkZ++) {
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
        random.setLargeFeatureSeed(context.seed(), context.chunkPos().x(), context.chunkPos().z());

        BlockPos centerPoint = chunkPos.getMiddleBlockPosition(0);
        int finalheight;
        if (this.fixedYSpawn.isPresent()) {
            finalheight = this.fixedYSpawn.get();
        }
        else {
            float centerWeight = this.centerTerrainHeightWeight.orElse(1F);
            int centerHeight = GeneralUtils.getCachedFreeHeight(context.chunkGenerator(), centerPoint.getX(), centerPoint.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState()) - 1;
            int highestLandPos = centerHeight;
            highestLandPos = Math.min(highestLandPos, GeneralUtils.getCachedFreeHeight(context.chunkGenerator(), centerPoint.getX() + 29, centerPoint.getZ() + 29, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState()) - 1);
            highestLandPos = Math.min(highestLandPos, GeneralUtils.getCachedFreeHeight(context.chunkGenerator(), centerPoint.getX() - 29, centerPoint.getZ() + 29, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState()) - 1);
            highestLandPos = Math.min(highestLandPos, GeneralUtils.getCachedFreeHeight(context.chunkGenerator(), centerPoint.getX() + 29, centerPoint.getZ() - 29, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState()) - 1);
            highestLandPos = Math.min(highestLandPos, GeneralUtils.getCachedFreeHeight(context.chunkGenerator(), centerPoint.getX() - 29, centerPoint.getZ() - 29, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState()) - 1);
            finalheight = (int)((highestLandPos - centerHeight) / centerWeight) + centerHeight;
        }

        if(finalheight <= context.chunkGenerator().getMinY())
            return Optional.empty();


        if (!extraSpawningChecks(context, chunkPos.getMiddleBlockPosition(finalheight))) {
            return Optional.empty();
        }

        return Optional.of(new Structure.GenerationStub(chunkPos.getMiddleBlockPosition(finalheight), (structurePiecesBuilder) -> {
            List<StructurePiece> list = MonumentPieces.createMonumentBuilding(
                    context.registryAccess(),
                    context.structureTemplateManager(),
                    random,
                    centerPoint.getX(),
                    finalheight,
                    centerPoint.getZ(),
                    this.monumentType,
                    this.liquidSettings);

            Rotation rotation = Rotation.getRandom(random);
            BlockPos mainOffset = new BlockPos(-29, 0, -29).rotate(rotation);
            for(StructurePiece structurePiece : list) {
                if (structurePiece instanceof PoolElementStructurePiece poolPiece) {
                    ((PoolElementStructurePieceAccessor)poolPiece).repurposedstructures$setRotation(
                            poolPiece.getRotation().getRotated(rotation));

                    // rotate
                    BlockPos piecePos = poolPiece.getPosition();
                    BlockPos offsetPos = piecePos.subtract(centerPoint);
                    BlockPos rotatedOffset = offsetPos.rotate(rotation);
                    GeneralUtils.movePieceProperly(poolPiece,
                            rotatedOffset.getX() - offsetPos.getX(),
                            0,
                            rotatedOffset.getZ() - offsetPos.getZ());

                    // center
                    GeneralUtils.movePieceProperly(poolPiece, mainOffset.getX(), 0, mainOffset.getZ());

                    // fix piece bounding boxes
                    if (poolPiece.getElement() instanceof SinglePoolElement singlePoolElement) {
                        StructureTemplate structuretemplate = ((SinglePoolElementAccessor)singlePoolElement).repurposedstructures$callGetTemplate(context.structureTemplateManager());
                        ((StructurePieceAccessor)poolPiece).repurposedstructures$setBoundingBox(structuretemplate.getBoundingBox((new StructurePlaceSettings()).setRotation(rotation), poolPiece.getPosition()));
                    }
                }
            }
            list.forEach(structurePiecesBuilder::addPiece);
        }));
    }

    @Override
    public StructureType<?> type() {
        return RSStructures.MONUMENT_STRUCTURE.get();
    }
}