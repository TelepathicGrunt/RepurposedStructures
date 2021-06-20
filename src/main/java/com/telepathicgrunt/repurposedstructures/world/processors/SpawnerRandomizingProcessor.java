package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.block.SpawnerBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtInt;
import net.minecraft.nbt.NbtList;
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
            BlockPos worldPos = structureBlockInfoWorld.pos;
            Random random = new ChunkRandom();
            random.setSeed(worldPos.asLong() * worldPos.getY());

            return new Structure.StructureBlockInfo(
                    worldPos,
                    structureBlockInfoWorld.state,
                    SetMobSpawnerEntity(random, structureBlockInfoWorld.nbt));
        }
        return structureBlockInfoWorld;
    }

    /**
     * Makes the given block entity now have the correct spawner mob
     */
    private NbtCompound SetMobSpawnerEntity(Random random, NbtCompound nbt) {
        EntityType<?> entity = RepurposedStructures.mobSpawnerManager.getSpawnerMob(rsSpawnerResourcelocation, random);
        if(entity != null){
            if(nbt != null){
                NbtCompound spawnDataTag = nbt.getCompound("SpawnData");
                if(spawnDataTag.isEmpty()){
                    spawnDataTag = new NbtCompound();
                    nbt.put("SpawnData", spawnDataTag);
                }
                spawnDataTag.putString("id", Registry.ENTITY_TYPE.getId(entity).toString());

                NbtCompound spawnEntityDataTag = new NbtCompound();
                spawnEntityDataTag.putString("id", Registry.ENTITY_TYPE.getId(entity).toString());
                NbtCompound spawnPotentialEntryTag = new NbtCompound();
                spawnPotentialEntryTag.put("Entity", spawnEntityDataTag);
                spawnPotentialEntryTag.put("Weight", NbtInt.of(1));
                NbtList spawnPotentialDataTag = nbt.getList("SpawnPotentials", spawnPotentialEntryTag.getType());
                if(spawnPotentialDataTag.isEmpty()){
                    spawnPotentialDataTag = new NbtList();
                    nbt.put("SpawnPotentials", spawnPotentialDataTag);
                }
                spawnPotentialDataTag.clear();
                spawnPotentialDataTag.add(0, spawnPotentialEntryTag);
                return nbt;
            }
            else{
                NbtCompound compound = new NbtCompound();
                compound.putShort("Delay", (short) 20);
                compound.putShort("MinSpawnDelay", (short) 200);
                compound.putShort("MaxSpawnDelay", (short) 800);
                compound.putShort("SpawnCount", (short) 4);
                compound.putShort("MaxNearbyEntities", (short) 6);
                compound.putShort("RequiredPlayerRange", (short) 16);
                compound.putShort("SpawnRange", (short) 4);

                NbtCompound spawnData = new NbtCompound();
                spawnData.putString("id", Registry.ENTITY_TYPE.getId(entity).toString());
                compound.put("SpawnData", spawnData);

                NbtCompound entityData = new NbtCompound();
                entityData.putString("id", Registry.ENTITY_TYPE.getId(entity).toString());

                NbtCompound listEntry = new NbtCompound();
                listEntry.put("Entity", entityData);
                listEntry.putInt("Weight", 1);

                NbtList listnbt = new NbtList();
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
    protected StructureProcessorType<?> getType() {
        return RSProcessors.SPAWNER_RANDOMIZING_PROCESSOR;
    }
}
