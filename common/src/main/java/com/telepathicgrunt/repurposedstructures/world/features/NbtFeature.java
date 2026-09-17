package com.telepathicgrunt.repurposedstructures.world.features;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.List;
import java.util.Optional;


public record NbtFeature(
        boolean allowInWater,
        int heightOffset,
        List<Pair<Identifier, Integer>> nbtIdentifiersAndWeights,
        Identifier processor
) implements Feature {

    public static final MapCodec<NbtFeature> CODEC = RecordCodecBuilder.mapCodec((configInstance) -> configInstance.group(
            Codec.BOOL.fieldOf("allow_liquid").orElse(false).forGetter(nbtFeature -> nbtFeature.allowInWater),
            Codec.INT.fieldOf("height_offset").orElse(0).forGetter(nbtFeature -> nbtFeature.heightOffset),
            Codec.mapPair(Identifier.CODEC.fieldOf("identifier"), Codec.intRange(1, Integer.MAX_VALUE).fieldOf("weight")).codec().listOf().fieldOf("nbt_entries").forGetter(nbtFeature -> nbtFeature.nbtIdentifiersAndWeights),
            Identifier.CODEC.fieldOf("processors").orElse(null).forGetter(nbtFeature -> nbtFeature.processor)
    ).apply(configInstance, NbtFeature::new));

    private static final BlockIgnoreProcessor ignoreStructureVoid = new BlockIgnoreProcessor(ImmutableList.of(Blocks.STRUCTURE_VOID));
    private static final StructurePlaceSettings placementSettings = (new StructurePlaceSettings()).setMirror(Mirror.NONE).addProcessor(ignoreStructureVoid).setIgnoreEntities(false);

    @Override
    public MapCodec<NbtFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {
        // move to top land block below position
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(origin);
        for (mutable.move(Direction.UP); level.isEmptyBlock(mutable) && mutable.getY() > 2; ) {
            mutable.move(Direction.DOWN);
        }

        //check to make sure spot is valid and not a single block ledge
        if (!level.getBlockState(mutable).isAir() &&
                !level.isEmptyBlock(mutable.below()) &&
                !level.isEmptyBlock(mutable.below(2))) {

            //Creates the well centered on our spot
            mutable.move(Direction.DOWN);
        }
        else{
            return false;
        }

        // Person wants an empty feature for some reason.
        if (nbtIdentifiersAndWeights.size() == 0) {
            return false;
        }

        BlockPos.MutableBlockPos blockpos$Mutable = new BlockPos.MutableBlockPos();
        StructureTemplateManager templatemanager = level.getLevel().getServer().getStructureTemplateManager();
        Identifier nbtRL = GeneralUtils.getRandomEntry(nbtIdentifiersAndWeights, random);
        Optional<StructureTemplate> template = templatemanager.get(nbtRL);

        if (template.isEmpty()) {
            RepurposedStructures.LOGGER.warn(nbtIdentifiersAndWeights.toString() + " NTB does not exist!");
            return false;
        }

        int radius = template.get().getSize().getX() / 2;
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                if ((x * x) + (z * z) < radius * radius + 1) {
                    blockpos$Mutable.set(origin).move(x, 0, z);

                    // Makes sure it doesn't generate in liquid if stated to not to.
                    if (!allowInWater && !level.getFluidState(blockpos$Mutable).isEmpty()) {
                        return false;
                    }
                    // No spawning on slopes
                    else if(level.getBlockState(blockpos$Mutable.move(Direction.UP)).canOcclude() ||
                            !level.getBlockState(blockpos$Mutable.move(Direction.DOWN, 3)).canOcclude()) {
                        return false;
                    }

                    //context.getWorld().setBlockState(blockpos$Mutable.up(), Blocks.REDSTONE_BLOCK.getDefaultState(), 2);
                }
            }
        }

        BlockPos halfLengths = new BlockPos(template.get().getSize().getX() / 2, 0, template.get().getSize().getZ() / 2);
        placementSettings.setRotation(Rotation.getRandom(random)).setRotationPivot(halfLengths).setIgnoreEntities(false);
        if(processor != null) {
            level.registryAccess().lookupOrThrow(Registries.PROCESSOR_LIST)
                    .getOptional(processor).ifPresent(processor -> processor.list().forEach(placementSettings::addProcessor));
        }
        blockpos$Mutable.set(origin);
        BlockPos offset = new BlockPos(-template.get().getSize().getX() / 2, heightOffset, -template.get().getSize().getZ() / 2);
        template.get().placeInWorld(level, blockpos$Mutable.offset(offset), blockpos$Mutable.offset(offset), placementSettings, random, Block.UPDATE_CLIENTS);

        return true;
    }
}
