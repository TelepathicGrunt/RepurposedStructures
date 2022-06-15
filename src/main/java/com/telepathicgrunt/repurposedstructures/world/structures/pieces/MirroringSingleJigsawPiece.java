package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.mixin.structures.SinglePoolElementAccessor;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructurePieces;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.JigsawReplacementProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.List;
import java.util.function.Function;

public class MirroringSingleJigsawPiece extends SinglePoolElement {
    public static final Codec<MirroringSingleJigsawPiece> CODEC = RecordCodecBuilder.create((jigsawPieceInstance) ->
            jigsawPieceInstance.group(
                    templateCodec(),
                    processorsCodec(),
                    projectionCodec(),
                    mirrorCodec())
            .apply(jigsawPieceInstance, MirroringSingleJigsawPiece::new));

    protected static <E extends MirroringSingleJigsawPiece> RecordCodecBuilder<E, Mirror> mirrorCodec() {
        return Codec.STRING.fieldOf("mirror")
                .xmap(Mirror::valueOf, Mirror::toString)
                .forGetter((jigsawPieceInstance) -> jigsawPieceInstance.mirror);
    }

    protected final Mirror mirror;

    public MirroringSingleJigsawPiece(SinglePoolElement singleJigsawPiece, Mirror mirror) {
        this(((SinglePoolElementAccessor)singleJigsawPiece).repurposedstructures_getTemplate(), ((SinglePoolElementAccessor)singleJigsawPiece).repurposedstructures_getProcessors(), singleJigsawPiece.getProjection(), mirror);
    }

    protected MirroringSingleJigsawPiece(Either<ResourceLocation, StructureTemplate> locationTemplateEither, Holder<StructureProcessorList> processorListSupplier, StructureTemplatePool.Projection placementBehaviour, Mirror mirror) {
        super(locationTemplateEither, processorListSupplier, placementBehaviour);
        this.mirror = mirror;
    }

    public MirroringSingleJigsawPiece(StructureTemplate template) {
        this(Either.right(template), ProcessorLists.EMPTY, StructureTemplatePool.Projection.RIGID, Mirror.NONE);
    }

    private StructureTemplate getTemplate(StructureTemplateManager templateManager) {
        return this.template.map(templateManager::getOrCreate, Function.identity());
    }

    @Override
    public List<StructureTemplate.StructureBlockInfo> getShuffledJigsawBlocks(StructureTemplateManager templateManager, BlockPos blockPos, Rotation rotation, RandomSource random) {
        StructureTemplate template = this.getTemplate(templateManager);
        ObjectArrayList<StructureTemplate.StructureBlockInfo> list = template.filterBlocks(blockPos, (new StructurePlaceSettings()).setRotation(rotation), Blocks.JIGSAW, true);
        Util.shuffle(list, random);
        return list;
    }

    @Override
    public BoundingBox getBoundingBox(StructureTemplateManager templateManager, BlockPos blockPos, Rotation rotation) {
        StructureTemplate template = this.getTemplate(templateManager);
        return template.getBoundingBox((new StructurePlaceSettings()).setRotation(rotation).setMirror(this.mirror), blockPos);
    }

    @Override
    public boolean place(StructureTemplateManager templateManager, WorldGenLevel worldGenLevel, StructureManager StructureTemplateManager, ChunkGenerator chunkGenerator, BlockPos blockPos, BlockPos blockPos1, Rotation rotation, BoundingBox mutableBoundingBox, RandomSource random, boolean doNotReplaceJigsaw) {
        StructureTemplate template = this.getTemplate(templateManager);
        StructurePlaceSettings placementsettings = this.getSettings(rotation, mutableBoundingBox, doNotReplaceJigsaw);
        if (!template.placeInWorld(worldGenLevel, blockPos, blockPos1, placementsettings, random, 18)) {
            return false;
        } else {
            for(StructureTemplate.StructureBlockInfo template$blockinfo : StructureTemplate.processBlockInfos(worldGenLevel, blockPos, blockPos1, placementsettings, this.getDataMarkers(templateManager, blockPos, rotation, false))) {
                this.handleDataMarker(worldGenLevel, template$blockinfo, blockPos, rotation, random, mutableBoundingBox);
            }

            return true;
        }
    }

    @Override
    protected StructurePlaceSettings getSettings(Rotation rotation, BoundingBox mutableBoundingBox, boolean doNotReplaceJigsaw) {
        StructurePlaceSettings placementsettings = new StructurePlaceSettings();
        placementsettings.setBoundingBox(mutableBoundingBox);
        placementsettings.setRotation(rotation);
        placementsettings.setMirror(mirror);
        placementsettings.setIgnoreEntities(false);
        placementsettings.addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
        placementsettings.setFinalizeEntities(true);
        if (!doNotReplaceJigsaw) {
            placementsettings.addProcessor(JigsawReplacementProcessor.INSTANCE);
        }

        this.processors.value().list().forEach(placementsettings::addProcessor);
        this.getProjection().getProcessors().forEach(placementsettings::addProcessor);
        return placementsettings;
    }

    @Override
    public StructurePoolElementType<?> getType() {
        return RSStructurePieces.MIRROR_SINGLE;
    }

    @Override
    public String toString() {
        return "Mirror_Single[" + this.template + "]";
    }
}
