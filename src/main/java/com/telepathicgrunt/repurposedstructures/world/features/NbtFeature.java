package com.telepathicgrunt.repurposedstructures.world.features;

import com.google.common.collect.ImmutableList;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.features.configs.NbtFeatureConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.Optional;


public class NbtFeature extends Feature<NbtFeatureConfig> {

    public NbtFeature() {
        super(NbtFeatureConfig.CODEC);
    }

    private final BlockIgnoreProcessor IGNORE_STRUCTURE_VOID = new BlockIgnoreProcessor(ImmutableList.of(Blocks.STRUCTURE_VOID));
    private final StructurePlaceSettings placementsettings = (new StructurePlaceSettings()).setMirror(Mirror.NONE).addProcessor(IGNORE_STRUCTURE_VOID).setIgnoreEntities(false);

    @Override
    public boolean place(FeaturePlaceContext<NbtFeatureConfig> context) {
        // move to top land block below position
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(context.origin());
        for (mutable.move(Direction.UP); context.level().isEmptyBlock(mutable) && mutable.getY() > 2; ) {
            mutable.move(Direction.DOWN);
        }

        //check to make sure spot is valid and not a single block ledge
        if (!context.level().getBlockState(mutable).isAir() &&
                !context.level().isEmptyBlock(mutable.below()) &&
                !context.level().isEmptyBlock(mutable.below(2))) {

            //Creates the well centered on our spot
            mutable.move(Direction.DOWN);
        }
        else{
            return false;
        }

        // Person wants an empty feature for some reason.
        if (context.config().nbtResourcelocationsAndWeights.size() == 0) {
            return false;
        }

        BlockPos.MutableBlockPos blockpos$Mutable = new BlockPos.MutableBlockPos();
        StructureTemplateManager templatemanager = context.level().getLevel().getServer().getStructureManager();
        ResourceLocation nbtRL = GeneralUtils.getRandomEntry(context.config().nbtResourcelocationsAndWeights, context.random());
        Optional<StructureTemplate> template = templatemanager.get(nbtRL);

        if (template.isEmpty()) {
            RepurposedStructures.LOGGER.warn(context.config().nbtResourcelocationsAndWeights.toString() + " NTB does not exist!");
            return false;
        }

        int radius = template.get().getSize().getX() / 2;
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                if ((x * x) + (z * z) < radius * radius + 1) {
                    blockpos$Mutable.set(context.origin()).move(x, 0, z);

                    // Makes sure it doesn't generate in liquid if stated to not to.
                    if (!context.config().allowInWater && !context.level().getFluidState(blockpos$Mutable).isEmpty()) {
                        return false;
                    }
                    // No spawning on slopes
                    else if(context.level().getBlockState(blockpos$Mutable.move(Direction.UP)).canOcclude() ||
                            !context.level().getBlockState(blockpos$Mutable.move(Direction.DOWN, 3)).canOcclude()) {
                        return false;
                    }

                    //context.getWorld().setBlockState(blockpos$Mutable.up(), Blocks.REDSTONE_BLOCK.getDefaultState(), 2);
                }
            }
        }

        BlockPos halfLengths = new BlockPos(template.get().getSize().getX() / 2, 0, template.get().getSize().getZ() / 2);
        placementsettings.setRotation(Rotation.getRandom(context.random())).setRotationPivot(halfLengths).setIgnoreEntities(false);
        if(context.config().processor != null) {
            context.level().registryAccess().registryOrThrow(Registry.PROCESSOR_LIST_REGISTRY)
                    .getOptional(context.config().processor).ifPresent(processor -> processor.list().forEach(placementsettings::addProcessor));
        }
        blockpos$Mutable.set(context.origin());
        BlockPos offset = new BlockPos(-template.get().getSize().getX() / 2, context.config().heightOffset, -template.get().getSize().getZ() / 2);
        template.get().placeInWorld(context.level(), blockpos$Mutable.offset(offset), blockpos$Mutable.offset(offset), placementsettings, context.random(), Block.UPDATE_CLIENTS);

        return true;
    }
}
