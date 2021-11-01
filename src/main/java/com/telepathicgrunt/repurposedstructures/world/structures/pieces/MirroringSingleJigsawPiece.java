package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.mixin.structures.SingleJigsawPieceAccessor;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructurePieces;
import net.minecraft.block.Blocks;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.jigsaw.IJigsawDeserializer;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.SingleJigsawPiece;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.JigsawReplacementStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.ProcessorLists;
import net.minecraft.world.gen.feature.template.StructureProcessorList;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

public class MirroringSingleJigsawPiece extends SingleJigsawPiece {
    private static final Codec<Either<ResourceLocation, Template>> TEMPLATE_CODEC = Codec.of(MirroringSingleJigsawPiece::encodeTemplate, ResourceLocation.CODEC.map(Either::left));
    public static final Codec<MirroringSingleJigsawPiece> CODEC = RecordCodecBuilder.create((jigsawPieceInstance) ->
            jigsawPieceInstance.group(
                    templateCodec(),
                    processorsCodec(),
                    projectionCodec(),
                    mirrorCodec())
            .apply(jigsawPieceInstance, MirroringSingleJigsawPiece::new));

    private static <T> DataResult<T> encodeTemplate(Either<ResourceLocation, Template> templateEither, DynamicOps<T> dynamicOps, T t) {
        Optional<ResourceLocation> optional = templateEither.left();
        return !optional.isPresent() ? DataResult.error("Can not serialize a runtime pool element") : ResourceLocation.CODEC.encode(optional.get(), dynamicOps, t);
    }

    protected static <E extends MirroringSingleJigsawPiece> RecordCodecBuilder<E, Mirror> mirrorCodec() {
        return Codec.STRING.fieldOf("mirror")
                .xmap(Mirror::valueOf, Mirror::toString)
                .forGetter((jigsawPieceInstance) -> jigsawPieceInstance.mirror);
    }

    protected final Mirror mirror;

    public MirroringSingleJigsawPiece(SingleJigsawPiece singleJigsawPiece, Mirror mirror) {
        this(((SingleJigsawPieceAccessor)singleJigsawPiece).getTemplate(), ((SingleJigsawPieceAccessor)singleJigsawPiece).getProcessors(), singleJigsawPiece.getProjection(), mirror);
    }

    protected MirroringSingleJigsawPiece(Either<ResourceLocation, Template> locationTemplateEither, Supplier<StructureProcessorList> processorListSupplier, JigsawPattern.PlacementBehaviour placementBehaviour, Mirror mirror) {
        super(locationTemplateEither, processorListSupplier, placementBehaviour);
        this.mirror = mirror;
    }

    public MirroringSingleJigsawPiece(Template template) {
        this(Either.right(template), () -> ProcessorLists.EMPTY, JigsawPattern.PlacementBehaviour.RIGID, Mirror.NONE);
    }

    private Template getTemplate(TemplateManager templateManager) {
        return this.template.map(templateManager::getOrCreate, Function.identity());
    }

    @Override
    public List<Template.BlockInfo> getShuffledJigsawBlocks(TemplateManager templateManager, BlockPos blockPos, Rotation rotation, Random random) {
        Template template = this.getTemplate(templateManager);
        List<Template.BlockInfo> list = template.filterBlocks(blockPos, (new PlacementSettings()).setRotation(rotation), Blocks.JIGSAW, true);
        Collections.shuffle(list, random);
        return list;
    }

    @Override
    public MutableBoundingBox getBoundingBox(TemplateManager templateManager, BlockPos blockPos, Rotation rotation) {
        Template template = this.getTemplate(templateManager);
        return template.getBoundingBox((new PlacementSettings()).setRotation(rotation).setMirror(this.mirror), blockPos);
    }

    @Override
    public boolean place(TemplateManager templateManager, ISeedReader seedReader, StructureManager structureManager, ChunkGenerator chunkGenerator, BlockPos blockPos, BlockPos blockPos1, Rotation rotation, MutableBoundingBox mutableBoundingBox, Random random, boolean doNotReplaceJigsaw) {
        Template template = this.getTemplate(templateManager);
        PlacementSettings placementsettings = this.getSettings(rotation, mutableBoundingBox, doNotReplaceJigsaw);
        if (!template.placeInWorld(seedReader, blockPos, blockPos1, placementsettings, random, 18)) {
            return false;
        } else {
            for(Template.BlockInfo template$blockinfo : Template.processBlockInfos(seedReader, blockPos, blockPos1, placementsettings, this.getDataMarkers(templateManager, blockPos, rotation, false), template)) {
                this.handleDataMarker(seedReader, template$blockinfo, blockPos, rotation, random, mutableBoundingBox);
            }

            return true;
        }
    }

    @Override
    protected PlacementSettings getSettings(Rotation rotation, MutableBoundingBox mutableBoundingBox, boolean doNotReplaceJigsaw) {
        PlacementSettings placementsettings = new PlacementSettings();
        placementsettings.setBoundingBox(mutableBoundingBox);
        placementsettings.setRotation(rotation);
        placementsettings.setMirror(mirror);
        placementsettings.setIgnoreEntities(false);
        placementsettings.addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
        placementsettings.setFinalizeEntities(true);
        if (!doNotReplaceJigsaw) {
            placementsettings.addProcessor(JigsawReplacementStructureProcessor.INSTANCE);
        }

        this.processors.get().list().forEach(placementsettings::addProcessor);
        this.getProjection().getProcessors().forEach(placementsettings::addProcessor);
        return placementsettings;
    }

    @Override
    public IJigsawDeserializer<?> getType() {
        return RSStructurePieces.MIRROR_SINGLE;
    }

    @Override
    public String toString() {
        return "Mirror_Single[" + this.template + "]";
    }
}
