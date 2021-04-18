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
            BlockPos worldPos = structureBlockInfoWorld.pos;
            Random random = new SharedSeedRandom();
            random.setSeed(worldPos.toLong() * worldPos.getY());

            return new Template.BlockInfo(
                    worldPos,
                    structureBlockInfoWorld.state,
                    SetMobSpawnerEntity(random, structureBlockInfoWorld.nbt));
        }
        return structureBlockInfoWorld;
    }

    /**
     * Makes the given block entity now have the correct spawner mob
     */
    private CompoundNBT SetMobSpawnerEntity(Random random, CompoundNBT nbt) {
        EntityType<?> entity = RepurposedStructures.mobSpawnerManager.getSpawnerMob(rsSpawnerResourcelocation, random);
        if(entity != null){
            if(nbt != null){

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
                return nbt;
            }
            else{
                CompoundNBT compound = new CompoundNBT();
                compound.putShort("Delay", (short) 20);
                compound.putShort("MinSpawnDelay", (short) 200);
                compound.putShort("MaxSpawnDelay", (short) 800);
                compound.putShort("SpawnCount", (short) 4);
                compound.putShort("MaxNearbyEntities", (short) 6);
                compound.putShort("RequiredPlayerRange", (short) 16);
                compound.putShort("SpawnRange", (short) 4);

                CompoundNBT spawnData = new CompoundNBT();
                spawnData.putString("id", Registry.ENTITY_TYPE.getKey(entity).toString());
                compound.put("SpawnData", spawnData);

                CompoundNBT entityData = new CompoundNBT();
                entityData.putString("id", Registry.ENTITY_TYPE.getKey(entity).toString());

                CompoundNBT listEntry = new CompoundNBT();
                listEntry.put("Entity", entityData);
                listEntry.putInt("Weight", 1);

                ListNBT listnbt = new ListNBT();
                listnbt.add(listEntry);

                compound.put("SpawnPotentials", listnbt);

                return compound;
            }
        }
        else{
            RepurposedStructures.LOGGER.warn("EntityType in a dungeon does not exist in registry! : {}", rsSpawnerResourcelocation);
        }
        return nbt;
    }

    @Override
    protected IStructureProcessorType<?> getType() {
        return RSProcessors.SPAWNER_RANDOMIZING_PROCESSOR;
    }
}
