package com.telepathicgrunt.repurposedstructures.world.features;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.features.configs.MinecartConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.Optional;


public class MinecartFeature extends Feature<MinecartConfig> {

    public MinecartFeature(Codec<MinecartConfig> config) {
        super(config);
    }

    private static final BlockIgnoreProcessor IGNORE_STRUCTURE_VOID = new BlockIgnoreProcessor(ImmutableList.of(Blocks.STRUCTURE_VOID));
    private final StructurePlaceSettings structurePlaceSettings = (new StructurePlaceSettings()).setMirror(Mirror.NONE).addProcessor(IGNORE_STRUCTURE_VOID).setIgnoreEntities(false);

    @Override
    public boolean place(FeaturePlaceContext<MinecartConfig> context) {

        // Check if below block is solid
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(context.origin());
        if(!context.level().getBlockState(mutable.below()).canOcclude()) {
            return false;
        }

        // Check if spot allows for cart (liquid or non-liquid spot)
        BlockState worldBlock = context.level().getBlockState(mutable);
        if(context.config().waterBased ? !worldBlock.getFluidState().is(FluidTags.WATER) : !worldBlock.getFluidState().isEmpty()) {
            return false;
        }

        BlockPos.MutableBlockPos blockpos$Mutable = new BlockPos.MutableBlockPos();
        StructureTemplateManager templatemanager = context.level().getLevel().getServer().getStructureManager();
        Optional<StructureTemplate> template = templatemanager.get(context.config().nbtPath);

        if (template.isEmpty()) {
            RepurposedStructures.LOGGER.warn(context.config().nbtPath.toString() + " NTB does not exist!");
            return false;
        }

        BlockPos halfLengths = new BlockPos(template.get().getSize().getX() / 2, 0, template.get().getSize().getZ() / 2);
        structurePlaceSettings.setRotation(Rotation.getRandom(context.random())).setRotationPivot(halfLengths).setIgnoreEntities(false);
        blockpos$Mutable.set(context.origin());
        BlockPos offset = new BlockPos(-template.get().getSize().getX() / 2, 0, -template.get().getSize().getZ() / 2);
        template.get().placeInWorld(context.level(), blockpos$Mutable.offset(offset), blockpos$Mutable.offset(offset), structurePlaceSettings, context.random(), Block.UPDATE_CLIENTS);

        return true;
    }
}
