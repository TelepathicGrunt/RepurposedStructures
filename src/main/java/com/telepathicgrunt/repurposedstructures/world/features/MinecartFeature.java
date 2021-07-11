package com.telepathicgrunt.repurposedstructures.world.features;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.features.configs.MinecartConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.MineshaftSupportConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.NbtFeatureConfig;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Optional;


public class MinecartFeature extends Feature<MinecartConfig> {

    public MinecartFeature(Codec<MinecartConfig> config) {
        super(config);
    }

    private final BlockIgnoreStructureProcessor IGNORE_STRUCTURE_VOID = new BlockIgnoreStructureProcessor(ImmutableList.of(Blocks.STRUCTURE_VOID));
    private final StructurePlacementData placementsettings = (new StructurePlacementData()).setMirror(BlockMirror.NONE).addProcessor(IGNORE_STRUCTURE_VOID).setIgnoreEntities(false);

    @Override
    public boolean generate(FeatureContext<MinecartConfig> context) {

        // Check if below block is solid
        BlockPos.Mutable mutable = new BlockPos.Mutable().set(context.getOrigin());
        if(!context.getWorld().getBlockState(mutable.down()).isOpaque()){
            return false;
        }

        BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable();
        StructureManager templatemanager = context.getWorld().toServerWorld().getServer().getStructureManager();
        Optional<Structure> template = templatemanager.getStructure(context.getConfig().nbtPath);

        if (template.isEmpty()) {
            RepurposedStructures.LOGGER.warn(context.getConfig().nbtPath.toString() + " NTB does not exist!");
            return false;
        }

        BlockPos halfLengths = new BlockPos(template.get().getSize().getX() / 2, 0, template.get().getSize().getZ() / 2);
        placementsettings.setRotation(BlockRotation.random(context.getRandom())).setPosition(halfLengths).setIgnoreEntities(false);
        blockpos$Mutable.set(context.getOrigin());
        BlockPos offset = new BlockPos(-template.get().getSize().getX() / 2, 0, -template.get().getSize().getZ() / 2);
        template.get().place(context.getWorld(), blockpos$Mutable.add(offset), blockpos$Mutable.add(offset), placementsettings, context.getRandom(), Block.NO_REDRAW);

        return true;
    }
}
