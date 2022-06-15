package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public class SpawnerRandomizingProcessor extends StructureProcessor {

    public static final Codec<SpawnerRandomizingProcessor> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            ResourceLocation.CODEC.fieldOf("rs_spawner_resourcelocation").forGetter(spawnerRandomizingProcessor -> spawnerRandomizingProcessor.rsSpawnerResourcelocation))
            .apply(instance, instance.stable(SpawnerRandomizingProcessor::new)));

    public final ResourceLocation rsSpawnerResourcelocation;

    private SpawnerRandomizingProcessor(ResourceLocation rsSpawnerResourcelocation) {
        this.rsSpawnerResourcelocation = rsSpawnerResourcelocation;
    }

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader worldView, BlockPos pos, BlockPos blockPos, StructureTemplate.StructureBlockInfo structureBlockInfoLocal, StructureTemplate.StructureBlockInfo structureBlockInfoWorld, StructurePlaceSettings structurePlacementData) {
        if (structureBlockInfoWorld.state.getBlock() instanceof SpawnerBlock) {
            BlockPos worldPos = structureBlockInfoWorld.pos;
            RandomSource random = new WorldgenRandom(new LegacyRandomSource(0L));
            random.setSeed(worldPos.asLong() * worldPos.getY());
            CompoundTag spawnerNBT = SetMobSpawnerEntity(random, structureBlockInfoWorld.nbt);

            if(spawnerNBT == null) {
                return new StructureTemplate.StructureBlockInfo(worldPos, Blocks.AIR.defaultBlockState(), null);
            }
            else {
                return new StructureTemplate.StructureBlockInfo(worldPos, structureBlockInfoWorld.state, spawnerNBT);
            }
        }
        return structureBlockInfoWorld;
    }

    /**
     * Makes the given block entity now have the correct spawner mob
     */
    private CompoundTag SetMobSpawnerEntity(RandomSource random, CompoundTag nbt) {
        EntityType<?> entity = RepurposedStructures.mobSpawnerManager.getSpawnerMob(rsSpawnerResourcelocation, random);
        if(entity != null) {
            if(nbt != null) {
                CompoundTag spawnDataTag = nbt.getCompound("SpawnData");
                if(spawnDataTag.isEmpty()) {
                    spawnDataTag = new CompoundTag();
                    nbt.put("SpawnData", spawnDataTag);
                }
                CompoundTag entityTag = nbt.getCompound("entity");
                if(entityTag.isEmpty()) {
                    entityTag = new CompoundTag();
                    spawnDataTag.put("entity", entityTag);
                }
                entityTag.putString("id", Registry.ENTITY_TYPE.getKey(entity).toString());

                CompoundTag spawnEntityDataTag = new CompoundTag();
                spawnEntityDataTag.putString("id", Registry.ENTITY_TYPE.getKey(entity).toString());
                CompoundTag spawnPotentialDataEntryTag = new CompoundTag();
                spawnPotentialDataEntryTag.put("entity", spawnEntityDataTag);
                CompoundTag spawnPotentialEntryTag = new CompoundTag();
                spawnPotentialEntryTag.put("data", spawnPotentialDataEntryTag);
                spawnPotentialEntryTag.put("weight", IntTag.valueOf(1));
                nbt.put("SpawnPotentials", new ListTag());
                return nbt;
            }
            else {
                CompoundTag compound = new CompoundTag();
                compound.putShort("Delay", (short) 20);
                compound.putShort("MinSpawnDelay", (short) 200);
                compound.putShort("MaxSpawnDelay", (short) 800);
                compound.putShort("SpawnCount", (short) 4);
                compound.putShort("MaxNearbyEntities", (short) 6);
                compound.putShort("RequiredPlayerRange", (short) 16);
                compound.putShort("SpawnRange", (short) 4);

                CompoundTag spawnDataEntity = new CompoundTag();
                spawnDataEntity.putString("id", Registry.ENTITY_TYPE.getKey(entity).toString());
                CompoundTag spawnData = new CompoundTag();
                spawnData.put("entity", spawnDataEntity);
                compound.put("SpawnData", spawnData);

                CompoundTag entityData = new CompoundTag();
                entityData.putString("id", Registry.ENTITY_TYPE.getKey(entity).toString());

                CompoundTag spawnPotentialData = new CompoundTag();
                spawnPotentialData.put("entity", entityData);
                CompoundTag listEntry = new CompoundTag();
                listEntry.put("data", spawnPotentialData);
                listEntry.putInt("weight", 1);
                compound.put("SpawnPotentials", new ListTag());

                return compound;
            }
        }

        return null;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.SPAWNER_RANDOMIZING_PROCESSOR;
    }
}
