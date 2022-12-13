package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructurePieces;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public class LegacyOceanBottomSinglePoolElement extends SinglePoolElement {
    public static final Codec<LegacyOceanBottomSinglePoolElement> CODEC = RecordCodecBuilder.create(
            (legacyOceanBottomSinglePoolElementInstance) -> legacyOceanBottomSinglePoolElementInstance
                    .group(templateCodec(), processorsCodec(), projectionCodec())
                    .apply(legacyOceanBottomSinglePoolElementInstance, LegacyOceanBottomSinglePoolElement::new));

    protected LegacyOceanBottomSinglePoolElement(Either<ResourceLocation, StructureTemplate> p_210348_, Holder<StructureProcessorList> p_210349_, StructureTemplatePool.Projection p_210350_) {
        super(p_210348_, p_210349_, p_210350_);
    }

    protected StructurePlaceSettings getSettings(Rotation rotation, BoundingBox boundingBox, boolean replaceJigsaw) {
        StructurePlaceSettings structureplacesettings = super.getSettings(rotation, boundingBox, replaceJigsaw);
        structureplacesettings.popProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
        structureplacesettings.addProcessor(BlockIgnoreProcessor.STRUCTURE_AND_AIR);
        return structureplacesettings;
    }

    public StructurePoolElementType<?> getType() {
        return RSStructurePieces.LEGACY_OCEAN_BOTTOM;
    }

    public String toString() {
        return "LegacyOceanBottomSingle[" + this.template + "]";
    }
}