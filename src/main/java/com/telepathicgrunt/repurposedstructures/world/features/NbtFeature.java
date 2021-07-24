package com.telepathicgrunt.repurposedstructures.world.features;

import com.google.common.collect.ImmutableList;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.features.configs.NbtDungeonConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.NbtFeatureConfig;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Optional;
import java.util.Random;


public class NbtFeature extends Feature<NbtFeatureConfig> {

    public NbtFeature() {
        super(NbtFeatureConfig.CODEC);
    }

    private final BlockIgnoreStructureProcessor IGNORE_STRUCTURE_VOID = new BlockIgnoreStructureProcessor(ImmutableList.of(Blocks.STRUCTURE_VOID));
    private final PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE).addProcessor(IGNORE_STRUCTURE_VOID).setIgnoreEntities(false);

    @Override
    public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos position, NbtFeatureConfig config) {
        if(GeneralUtils.isBlacklistedForWorld(world, config.cfID)) return false;

        // move to top land block below position
        BlockPos.Mutable mutable = new BlockPos.Mutable().set(position);
        for (mutable.move(Direction.UP); world.isEmptyBlock(mutable) && mutable.getY() > 2; ) {
            mutable.move(Direction.DOWN);
        }

        //check to make sure spot is valid and not a single block ledge
        if (!world.getBlockState(mutable).isAir() &&
                !world.isEmptyBlock(mutable.below()) &&
                !world.isEmptyBlock(mutable.below(2))) {

            //Creates the well centered on our spot
            mutable.move(Direction.DOWN);
        }
        else{
            return false;
        }

        // Person wants an empty feature for some reason.
        if (config.nbtResourcelocationsAndWeights.size() == 0) {
            return false;
        }

        BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable();
        TemplateManager templatemanager = world.getLevel().getServer().getStructureManager();
        ResourceLocation nbtRL = GeneralUtils.getRandomEntry(config.nbtResourcelocationsAndWeights, random);
        Template template = templatemanager.get(nbtRL);

        if (template == null) {
            RepurposedStructures.LOGGER.warn(config.nbtResourcelocationsAndWeights.toString() + " NTB does not exist!");
            return false;
        }

        int radius = template.getSize().getX() / 2;
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                if ((x * x) + (z * z) < radius * radius + 1) {
                    blockpos$Mutable.set(position).move(x, 0, z);

                    // Makes sure it doesn't generate in liquid if stated to not to.
                    if (!config.allowInWater && !world.getFluidState(blockpos$Mutable).isEmpty()) {
                        return false;
                    }
                    // No spawning on slopes
                    else if(world.getBlockState(blockpos$Mutable.move(Direction.UP)).canOcclude() ||
                            !world.getBlockState(blockpos$Mutable.move(Direction.DOWN, 3)).canOcclude()){
                        return false;
                    }

                    //context.getWorld().setBlockState(blockpos$Mutable.up(), Blocks.REDSTONE_BLOCK.getDefaultState(), 2);
                }
            }
        }

        BlockPos halfLengths = new BlockPos(template.getSize().getX() / 2, 0, template.getSize().getZ() / 2);
        placementsettings.setRotation(Rotation.getRandom(random)).setRotationPivot(halfLengths).setIgnoreEntities(false);
        if(config.processor != null){
            world.registryAccess().registryOrThrow(Registry.PROCESSOR_LIST_REGISTRY)
                    .getOptional(config.processor).ifPresent(processor -> processor.list().forEach(placementsettings::addProcessor));
        }
        blockpos$Mutable.set(position);
        BlockPos offset = new BlockPos(-template.getSize().getX() / 2, config.heightOffset, -template.getSize().getZ() / 2);
        template.placeInWorld(world, blockpos$Mutable.offset(offset), placementsettings, random);

        return true;
    }
}
