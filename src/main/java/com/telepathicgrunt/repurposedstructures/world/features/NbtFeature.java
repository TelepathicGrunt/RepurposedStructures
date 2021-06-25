package com.telepathicgrunt.repurposedstructures.world.features;

import com.google.common.collect.ImmutableList;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
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


public class NbtFeature extends Feature<NbtFeatureConfig> {

    public NbtFeature() {
        super(NbtFeatureConfig.CODEC);
    }

    private final BlockIgnoreStructureProcessor IGNORE_STRUCTURE_VOID = new BlockIgnoreStructureProcessor(ImmutableList.of(Blocks.STRUCTURE_VOID));
    private final StructurePlacementData placementsettings = (new StructurePlacementData()).setMirror(BlockMirror.NONE).addProcessor(IGNORE_STRUCTURE_VOID).setIgnoreEntities(false);

    @Override
    public boolean generate(FeatureContext<NbtFeatureConfig> context) {
        if(GeneralUtils.isWorldBlacklisted(context.getWorld())) return false;

        // move to top land block below position
        BlockPos.Mutable mutable = new BlockPos.Mutable().set(context.getOrigin());
        for (mutable.move(Direction.UP); context.getWorld().isAir(mutable) && mutable.getY() > 2; ) {
            mutable.move(Direction.DOWN);
        }

        //check to make sure spot is valid and not a single block ledge
        if (!context.getWorld().getBlockState(mutable).isAir() &&
                !context.getWorld().isAir(mutable.down()) &&
                !context.getWorld().isAir(mutable.down(2))) {

            //Creates the well centered on our spot
            mutable.move(Direction.DOWN);
        }
        else{
            return false;
        }

        // Person wants an empty feature for some reason.
        if (context.getConfig().nbtResourcelocationsAndWeights.size() == 0) {
            return false;
        }

        BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable();
        StructureManager templatemanager = context.getWorld().toServerWorld().getServer().getStructureManager();
        Identifier nbtRL = GeneralUtils.getRandomEntry(context.getConfig().nbtResourcelocationsAndWeights, context.getRandom());
        Optional<Structure> template = templatemanager.getStructure(nbtRL);

        if (template.isEmpty()) {
            RepurposedStructures.LOGGER.warn(context.getConfig().nbtResourcelocationsAndWeights.toString() + " NTB does not exist!");
            return false;
        }

        int radius = template.get().getSize().getX() / 2;
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                if ((x * x) + (z * z) < radius * radius + 1) {
                    blockpos$Mutable.set(context.getOrigin()).move(x, 0, z);

                    // Makes sure it doesn't generate in liquid if stated to not to.
                    if (!context.getConfig().allowInWater && !context.getWorld().getFluidState(blockpos$Mutable).isEmpty()) {
                        return false;
                    }
                    // No spawning on slopes
                    else if(context.getWorld().getBlockState(blockpos$Mutable.move(Direction.UP)).isOpaque() ||
                            !context.getWorld().getBlockState(blockpos$Mutable.move(Direction.DOWN, 3)).isOpaque()){
                        return false;
                    }

                    //context.getWorld().setBlockState(blockpos$Mutable.up(), Blocks.REDSTONE_BLOCK.getDefaultState(), 2);
                }
            }
        }

        BlockPos halfLengths = new BlockPos(template.get().getSize().getX() / 2, 0, template.get().getSize().getZ() / 2);
        placementsettings.setRotation(BlockRotation.random(context.getRandom())).setPosition(halfLengths).setIgnoreEntities(false);
        if(context.getConfig().processor != null){
            context.getWorld().getRegistryManager().get(Registry.STRUCTURE_PROCESSOR_LIST_KEY)
                    .getOrEmpty(context.getConfig().processor).ifPresent(processor -> processor.getList().forEach(placementsettings::addProcessor));
        }
        blockpos$Mutable.set(context.getOrigin());
        BlockPos offset = new BlockPos(-template.get().getSize().getX() / 2, context.getConfig().heightOffset, -template.get().getSize().getZ() / 2);
        template.get().place(context.getWorld(), blockpos$Mutable.add(offset), blockpos$Mutable.add(offset), placementsettings, context.getRandom(), Block.NO_REDRAW);

        return true;
    }
}
