package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.block.SpawnerBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;

import java.util.Random;

public class SpawnerRandomizingProcessor extends StructureProcessor {

    public static final Codec<SpawnerRandomizingProcessor> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            ResourceLocation.CODEC.fieldOf("rs_spawner_resourcelocation").forGetter(spawnerRandomizingProcessor -> spawnerRandomizingProcessor.rsSpawnerResourcelocation))
            .apply(instance, instance.stable(SpawnerRandomizingProcessor::new)));

    public final ResourceLocation rsSpawnerResourcelocation;

    private SpawnerRandomizingProcessor(ResourceLocation rsSpawnerResourcelocation) {
        this.rsSpawnerResourcelocation = rsSpawnerResourcelocation;
    }

    @Override
    public Template.BlockInfo process(IWorldReader worldView, BlockPos pos, BlockPos blockPos, Template.BlockInfo structureBlockInfoLocal, Template.BlockInfo structureBlockInfoWorld, PlacementSettings structurePlacementData) {
        if (structureBlockInfoWorld.state.getBlock() instanceof SpawnerBlock) {
            Random random = new SharedSeedRandom();
            random.setSeed(structureBlockInfoWorld.pos.toLong() * structureBlockInfoWorld.pos.getY());
            SetMobSpawnerEntity(random, structureBlockInfoWorld.nbt);
        }
        return structureBlockInfoWorld;
    }

    /**
     * Makes the given block entity now have the correct spawner mob
     */
    private void SetMobSpawnerEntity(Random random, CompoundNBT nbt) {
        EntityType<?> entity = RepurposedStructures.mobSpawnerManager.getSpawnerMob(rsSpawnerResourcelocation, random);
        if(entity != null){
            CompoundNBT spawnDataTag = nbt.getCompound("SpawnData");
            if(spawnDataTag.isEmpty()){
                spawnDataTag = new CompoundNBT();
                nbt.put("SpawnData", spawnDataTag);
            }
            spawnDataTag.putString("id", Registry.ENTITY_TYPE.getKey(entity).toString());

            CompoundNBT spawnEntityDataTag = new CompoundNBT();
            spawnEntityDataTag.putString("id", Registry.ENTITY_TYPE.getKey(entity).toString());
            CompoundNBT spawnPotentialEntryTag = new CompoundNBT();
            spawnPotentialEntryTag.put("Entity", spawnEntityDataTag);
            spawnPotentialEntryTag.put("Weight", IntNBT.of(1));
            ListNBT spawnPotentialDataTag = nbt.getList("SpawnPotentials", spawnPotentialEntryTag.getId());
            if(spawnPotentialDataTag.isEmpty()){
                spawnPotentialDataTag = new ListNBT();
                nbt.put("SpawnPotentials", spawnPotentialDataTag);
            }
            spawnPotentialDataTag.clear();
            spawnPotentialDataTag.add(0, spawnPotentialEntryTag);
        }
        else{
            RepurposedStructures.LOGGER.warn("EntityType in a dungeon does not exist in registry! : {}", rsSpawnerResourcelocation);
        }
    }

    @Override
    protected IStructureProcessorType<?> getType() {
        return RSProcessors.SPAWNER_RANDOMIZING_PROCESSOR;
    }
}
