package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.InclusiveRange;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.Optional;

public class SpawnerRandomizingProcessor extends StructureProcessor {

    public static final Codec<SpawnerRandomizingProcessor> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            ResourceLocation.CODEC.fieldOf("rs_spawner_resourcelocation").forGetter(spawnerRandomizingProcessor -> spawnerRandomizingProcessor.rsSpawnerResourcelocation),
            InclusiveRange.INT.optionalFieldOf("valid_block_light_level").forGetter(spawnerRandomizingProcessor -> spawnerRandomizingProcessor.validBlockLightLevel),
            InclusiveRange.INT.optionalFieldOf("valid_sky_light_level").forGetter(spawnerRandomizingProcessor -> spawnerRandomizingProcessor.validSkyLightLevel),
            Codec.intRange(0, Integer.MAX_VALUE).fieldOf("delay").orElse(20).forGetter(spawnerRandomizingProcessor -> spawnerRandomizingProcessor.delay),
            Codec.intRange(0, Integer.MAX_VALUE).fieldOf("max_nearby_entities").orElse(6).forGetter(spawnerRandomizingProcessor -> spawnerRandomizingProcessor.maxNearbyEntities),
            Codec.intRange(0, Integer.MAX_VALUE).fieldOf("max_spawn_delay").orElse(800).forGetter(spawnerRandomizingProcessor -> spawnerRandomizingProcessor.maxSpawnDelay),
            Codec.intRange(0, Integer.MAX_VALUE).fieldOf("min_spawn_delay").orElse(200).forGetter(spawnerRandomizingProcessor -> spawnerRandomizingProcessor.minSpawnDelay),
            Codec.intRange(0, Integer.MAX_VALUE).fieldOf("required_player_range").orElse(16).forGetter(spawnerRandomizingProcessor -> spawnerRandomizingProcessor.requiredPlayerRange),
            Codec.intRange(0, Integer.MAX_VALUE).fieldOf("spawn_count").orElse(4).forGetter(spawnerRandomizingProcessor -> spawnerRandomizingProcessor.spawnCount),
            Codec.intRange(0, Integer.MAX_VALUE).fieldOf("spawn_range").orElse(4).forGetter(spawnerRandomizingProcessor -> spawnerRandomizingProcessor.spawnRange),
            BlockState.CODEC.fieldOf("spawner_replacement_block").orElse(Blocks.AIR.defaultBlockState()).forGetter(spawnerRandomizingProcessor -> spawnerRandomizingProcessor.replacementState)
    ).apply(instance, instance.stable(SpawnerRandomizingProcessor::new)));

    public final ResourceLocation rsSpawnerResourcelocation;
    public final Optional<InclusiveRange<Integer>> validBlockLightLevel;
    public final Optional<InclusiveRange<Integer>> validSkyLightLevel;
    public final int delay;
    public final int maxNearbyEntities;
    public final int maxSpawnDelay;
    public final int minSpawnDelay;
    public final int requiredPlayerRange;
    public final int spawnCount;
    public final int spawnRange;
    public final BlockState replacementState;

    private SpawnerRandomizingProcessor(ResourceLocation rsSpawnerResourcelocation,
                                        Optional<InclusiveRange<Integer>> validBlockLightLevel,
                                        Optional<InclusiveRange<Integer>> validSkyLightLevel,
                                        int delay,
                                        int maxNearbyEntities,
                                        int maxSpawnDelay,
                                        int minSpawnDelay,
                                        int requiredPlayerRange,
                                        int spawnCount,
                                        int spawnRange,
                                        BlockState replacementState)
    {
        this.rsSpawnerResourcelocation = rsSpawnerResourcelocation;
        this.validBlockLightLevel = validBlockLightLevel;
        this.validSkyLightLevel = validSkyLightLevel;
        this.delay = delay;
        this.maxNearbyEntities = maxNearbyEntities;
        this.maxSpawnDelay = maxSpawnDelay;
        this.minSpawnDelay = minSpawnDelay;
        this.requiredPlayerRange = requiredPlayerRange;
        this.spawnCount = spawnCount;
        this.spawnRange = spawnRange;
        this.replacementState = replacementState;
    }

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader worldView, BlockPos pos, BlockPos blockPos, StructureTemplate.StructureBlockInfo structureBlockInfoLocal, StructureTemplate.StructureBlockInfo structureBlockInfoWorld, StructurePlaceSettings structurePlacementData) {
        if (structureBlockInfoWorld.state.getBlock() instanceof SpawnerBlock) {
            BlockPos worldPos = structureBlockInfoWorld.pos;
            RandomSource random = structurePlacementData.getRandom(structureBlockInfoWorld.pos);
            CompoundTag spawnerNBT = SetMobSpawnerEntity(random);

            if(spawnerNBT == null) {
                return new StructureTemplate.StructureBlockInfo(worldPos, replacementState, null);
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
    private CompoundTag SetMobSpawnerEntity(RandomSource random) {
        EntityType<?> entity = RepurposedStructures.mobSpawnerManager.getSpawnerMob(rsSpawnerResourcelocation, random);
        if(entity != null) {
            ResourceLocation entityRL = Registry.ENTITY_TYPE.getKey(entity);

            // Set spawn potentials
            CompoundTag compound = new CompoundTag();
            compound.putShort("Delay", (short) delay);
            compound.putShort("MinSpawnDelay", (short) minSpawnDelay);
            compound.putShort("MaxSpawnDelay", (short) maxSpawnDelay);
            compound.putShort("SpawnCount", (short) spawnCount);
            compound.putShort("MaxNearbyEntities", (short) maxNearbyEntities);
            compound.putShort("RequiredPlayerRange", (short) requiredPlayerRange);
            compound.putShort("SpawnRange", (short) spawnRange);

            CompoundTag spawnData = new CompoundTag();
            CompoundTag spawnPotentialData = new CompoundTag();
            CompoundTag entityData = new CompoundTag();
            entityData.putString("id", entityRL.toString());
            spawnPotentialData.put("entity", entityData);

            if (validBlockLightLevel.isPresent() || validSkyLightLevel.isPresent()) {
                CompoundTag customSpawnRule = new CompoundTag();

                validBlockLightLevel.ifPresent(blockLightLimit -> {
                    CompoundTag blockLightTag = new CompoundTag();
                    blockLightTag.putInt("min_inclusive", blockLightLimit.minInclusive());
                    blockLightTag.putInt("max_inclusive", blockLightLimit.maxInclusive());
                    customSpawnRule.put("block_light_limit", blockLightTag);
                });
                validSkyLightLevel.ifPresent(skyLightLimit -> {
                    CompoundTag skyLightTag = new CompoundTag();
                    skyLightTag.putInt("min_inclusive", skyLightLimit.minInclusive());
                    skyLightTag.putInt("max_exclusive", skyLightLimit.maxInclusive());
                    customSpawnRule.put("sky_light_limit", skyLightTag);
                });

                spawnPotentialData.put("custom_spawn_rules", customSpawnRule);
                spawnData.put("custom_spawn_rules", customSpawnRule);
            }

            CompoundTag listEntry = new CompoundTag();
            listEntry.put("data", spawnPotentialData);
            listEntry.putInt("weight", 1);
            ListTag listTag = new ListTag();
            listTag.add(listEntry);
            compound.put("SpawnPotentials", listTag);


            CompoundTag entityEntry = new CompoundTag();
            entityEntry.putString("id", entityRL.toString());
            spawnData.put("entity", entityEntry);
            compound.put("SpawnData", spawnData);

            return compound;
        }

        return null;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.SPAWNER_RANDOMIZING_PROCESSOR;
    }
}
