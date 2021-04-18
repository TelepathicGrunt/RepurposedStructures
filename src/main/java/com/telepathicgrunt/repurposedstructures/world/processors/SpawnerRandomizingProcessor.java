package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.block.SpawnerBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.WorldView;
import net.minecraft.world.gen.ChunkRandom;

import java.util.Random;

public class SpawnerRandomizingProcessor extends StructureProcessor {

    public static final Codec<SpawnerRandomizingProcessor> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Identifier.CODEC.fieldOf("rs_spawner_resourcelocation").forGetter(spawnerRandomizingProcessor -> spawnerRandomizingProcessor.rsSpawnerResourcelocation))
            .apply(instance, instance.stable(SpawnerRandomizingProcessor::new)));

    public final Identifier rsSpawnerResourcelocation;

    private SpawnerRandomizingProcessor(Identifier rsSpawnerResourcelocation) {
        this.rsSpawnerResourcelocation = rsSpawnerResourcelocation;
    }

    @Override
    public Structure.StructureBlockInfo process(WorldView worldView, BlockPos pos, BlockPos blockPos, Structure.StructureBlockInfo structureBlockInfoLocal, Structure.StructureBlockInfo structureBlockInfoWorld, StructurePlacementData structurePlacementData) {
        if (structureBlockInfoWorld.state.getBlock() instanceof SpawnerBlock) {
            Random random = new ChunkRandom();
            random.setSeed(structureBlockInfoWorld.pos.asLong() * structureBlockInfoWorld.pos.getY());
            SetMobSpawnerEntity(random, structureBlockInfoWorld.tag);
        }
        return structureBlockInfoWorld;
    }

    /**
     * Makes the given block entity now have the correct spawner mob
     */
    private void SetMobSpawnerEntity(Random random, CompoundTag nbt) {
        EntityType<?> entity = RepurposedStructures.mobSpawnerManager.getSpawnerMob(rsSpawnerResourcelocation, random);
        if(entity != null){
            CompoundTag spawnDataTag = nbt.getCompound("SpawnData");
            if(spawnDataTag.isEmpty()){
                spawnDataTag = new CompoundTag();
                nbt.put("SpawnData", spawnDataTag);
            }
            spawnDataTag.putString("id", Registry.ENTITY_TYPE.getId(entity).toString());

            CompoundTag spawnEntityDataTag = new CompoundTag();
            spawnEntityDataTag.putString("id", Registry.ENTITY_TYPE.getId(entity).toString());
            CompoundTag spawnPotentialEntryTag = new CompoundTag();
            spawnPotentialEntryTag.put("Entity", spawnEntityDataTag);
            spawnPotentialEntryTag.put("Weight", IntTag.of(1));
            ListTag spawnPotentialDataTag = nbt.getList("SpawnPotentials", spawnPotentialEntryTag.getType());
            if(spawnPotentialDataTag.isEmpty()){
                spawnPotentialDataTag = new ListTag();
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
    protected StructureProcessorType<?> getType() {
        return RSProcessors.SPAWNER_RANDOMIZING_PROCESSOR;
    }
}
