package com.telepathicgrunt.repurposedstructures.world.features;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.Optional;


public record MinecartFeature(Identifier nbtPath, boolean waterBased) implements Feature {

    public static final MapCodec<MinecartFeature> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            Identifier.CODEC.fieldOf("minecart_nbt_file").forGetter(feature -> feature.nbtPath),
            Codec.BOOL.fieldOf("is_water_based").orElse(false).forGetter(feature -> feature.waterBased)
    ).apply(instance, MinecartFeature::new));

    private static final BlockIgnoreProcessor IGNORE_STRUCTURE_VOID = new BlockIgnoreProcessor(ImmutableList.of(Blocks.STRUCTURE_VOID));
    private static final StructurePlaceSettings structurePlaceSettings = (new StructurePlaceSettings()).setMirror(Mirror.NONE).addProcessor(IGNORE_STRUCTURE_VOID).setIgnoreEntities(false);

    @Override
    public MapCodec<MinecartFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {

        // Check if below block is solid
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(origin);
        if (!level.getBlockState(mutable.below()).canOcclude()) {
            return false;
        }

        // Check if spot allows for cart (liquid or non-liquid spot)
        BlockState worldBlock = level.getBlockState(mutable);
        if (waterBased ? !worldBlock.getFluidState().is(FluidTags.WATER) : !worldBlock.getFluidState().isEmpty()) {
            return false;
        }

        BlockPos.MutableBlockPos blockpos$Mutable = new BlockPos.MutableBlockPos();
        StructureTemplateManager templatemanager = level.getLevel().getServer().getStructureTemplateManager();
        Optional<StructureTemplate> template = templatemanager.get(nbtPath);

        if (template.isEmpty()) {
            RepurposedStructures.LOGGER.warn(nbtPath.toString() + " NTB does not exist!");
            return false;
        }

        BlockPos halfLengths = new BlockPos(template.get().getSize().getX() / 2, 0, template.get().getSize().getZ() / 2);
        structurePlaceSettings.setRotation(Rotation.getRandom(random)).setRotationPivot(halfLengths).setIgnoreEntities(false);
        blockpos$Mutable.set(origin);
        BlockPos offset = new BlockPos(-template.get().getSize().getX() / 2, 0, -template.get().getSize().getZ() / 2);
        template.get().placeInWorld(level, blockpos$Mutable.offset(offset), blockpos$Mutable.offset(offset), structurePlaceSettings, random, Block.UPDATE_CLIENTS);

        return true;
    }
}
