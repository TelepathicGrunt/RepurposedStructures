package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetAndLengthConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;


public class StructureCrimsonPlants extends Feature<StructureTargetAndLengthConfig> {

    public StructureCrimsonPlants(Codec<StructureTargetAndLengthConfig> config) {
        super(config);
    }


    @Override
    public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos position, StructureTargetAndLengthConfig config) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();
        BlockState crimsonFungus = Blocks.CRIMSON_FUNGUS.defaultBlockState();
        BlockState crimsonRoots = Blocks.CRIMSON_ROOTS.defaultBlockState();
        BlockState weepingVines = Blocks.WEEPING_VINES.defaultBlockState();
        BlockState weepingVinesPlant = Blocks.WEEPING_VINES_PLANT.defaultBlockState();

        for(int i = 0; i < config.attempts; i++){
            mutable.set(position).move(
                    random.nextInt(7) - 3,
                    random.nextInt(4) - 1,
                    random.nextInt(7) - 3
            );

            if(world.getBlockState(mutable).isAir()){
                if(random.nextFloat() < 0.8f && crimsonRoots.canSurvive(world, mutable)){
                    // expensive. Do this check very last
                    // This seems to sometimes deadlock only on Forge. But not Fabric. What the fuck?
                    //!world.getLevel().structureFeatureManager().getStructureAt(mutable, true, config.targetStructure).isValid()

                    // Alternative. Won't follow the structure's bounds perfectly tho...
                    if(!world.startsForFeature(SectionPos.of(mutable), config.targetStructure).findAny().isPresent()){
                        continue;
                    }

                    world.setBlock(mutable, crimsonRoots, 3);
                }
                else if(crimsonFungus.canSurvive(world, mutable)){
                    // expensive. Do this check very last
                    // This seems to sometimes deadlock only on Forge. But not Fabric. What the fuck?
                    //!world.getLevel().structureFeatureManager().getStructureAt(mutable, true, config.targetStructure).isValid()

                    // Alternative. Won't follow the structure's bounds perfectly tho...
                    if(!world.startsForFeature(SectionPos.of(mutable), config.targetStructure).findAny().isPresent()){
                        continue;
                    }

                    world.setBlock(mutable, crimsonFungus, 3);
                }
                else if(weepingVines.canSurvive(world, mutable)){
                    // expensive. Do this check very last
                    // This seems to sometimes deadlock only on Forge. But not Fabric. What the fuck?
                    //!world.getLevel().structureFeatureManager().getStructureAt(mutable, true, config.targetStructure).isValid()

                    // Alternative. Won't follow the structure's bounds perfectly tho...
                    if(!world.startsForFeature(SectionPos.of(mutable), config.targetStructure).findAny().isPresent()){
                        continue;
                    }

                    // Biased towards max length if greater than 3
                    int length = config.length > 3 ? config.length - random.nextInt(random.nextInt(config.length) + 1) : random.nextInt(config.length);
                    for(int currentLength = 0; currentLength <= length; currentLength++){
                        if(currentLength == length || !world.getBlockState(mutable.below()).isAir()){
                            world.setBlock(mutable, weepingVines, 3);
                            break;
                        }
                        world.setBlock(mutable, weepingVinesPlant, 3);
                        mutable.move(Direction.DOWN);
                    }
                }
            }
        }

        return true;
    }
}