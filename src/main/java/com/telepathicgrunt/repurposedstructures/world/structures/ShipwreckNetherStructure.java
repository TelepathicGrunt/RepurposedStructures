package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.NetherShipwreckConfig;
import net.minecraft.entity.EntityType;
import net.minecraft.structure.MarginedStructureStart;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;


public class ShipwreckNetherStructure extends AbstractBaseStructure<NetherShipwreckConfig> {
    // Special thanks to cannon_foddr and miguelforge for allowing me to use their nether shipwreck design!

    private final Identifier startPool;
    private final int sealevelOffset;


    public ShipwreckNetherStructure(Identifier startPool, int sealevelOffset) {
        super(NetherShipwreckConfig.CODEC);
        this.startPool = startPool;
        this.sealevelOffset = sealevelOffset;
        RSStructures.RS_STRUCTURE_START_PIECES.add(startPool);
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, ChunkPos chunkPos1, Biome biome, ChunkPos chunkPos, NetherShipwreckConfig config, HeightLimitView heightLimitView) {

        // Check to see if there some air where the structure wants to spawn.
        // Doesn't account for rotation of structure.
        BlockPos blockPos;
        if(!config.isFlying){
            blockPos = new BlockPos(chunkPos1.getStartX(), chunkGenerator.getSeaLevel() + 1, chunkPos1.getStartZ());
        }
        else{
            ChunkRandom random = new ChunkRandom(seed + (chunkPos1.x * (chunkPos1.z * 17L)));
            int height = chunkGenerator.getSeaLevel() + random.nextInt(Math.max(chunkGenerator.getWorldHeight() - (chunkGenerator.getSeaLevel() + 30), 1));
            blockPos = new BlockPos(chunkPos1.getStartX(), height, chunkPos1.getStartZ());
        }

        int checkRadius = 16;
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for(int xOffset = -checkRadius; xOffset <= checkRadius; xOffset += 8){
            for(int zOffset = -checkRadius; zOffset <= checkRadius; zOffset += 8){
                VerticalBlockSample blockView = chunkGenerator.getColumnSample(xOffset + blockPos.getX(), zOffset + blockPos.getZ(), heightLimitView);
                for(int yOffset = 0; yOffset <= 30; yOffset += 5){
                    mutable.set(blockPos).move(xOffset, yOffset, zOffset);
                    if (!blockView.getState(mutable).isAir()) {
                        return false;
                    }
                }
            }
        }

        //cannot be near any other structure
        for (int curChunkX = chunkPos1.x - 3; curChunkX <= chunkPos1.x + 3; curChunkX++) {
            for (int curChunkZ = chunkPos1.z - 3; curChunkZ <= chunkPos1.z + 3; curChunkZ++) {
                if(curChunkX == chunkPos1.x && curChunkZ == chunkPos1.z) continue; // Prevent detecting the structure itself and thus, never spawning if structure is in its own blacklist

                for(StructureFeature<?> structureFeature : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE)){
                    StructureConfig structureConfig = chunkGenerator.getStructuresConfig().getForType(structureFeature);
                    if(structureConfig != null && structureConfig.getSpacing() > 8){
                        ChunkPos chunkPos2 = structureFeature.getStartChunk(structureConfig, seed, chunkRandom, curChunkX, curChunkZ);
                        if (curChunkX == chunkPos2.x && curChunkZ == chunkPos2.z) {
                            return false;
                        }
                    }
                }
            }
        }

        return super.shouldStartAt(chunkGenerator, biomeSource, seed, chunkRandom, chunkPos1, biome, chunkPos, config, heightLimitView);
    }

    @Override
    public StructureStartFactory<NetherShipwreckConfig> getStructureStartFactory() {
        return ShipwreckNetherStructure.Start::new;
    }

    public class Start extends MarginedStructureStart<NetherShipwreckConfig> {
        private final long seed;

        public Start(StructureFeature<NetherShipwreckConfig> structureIn, ChunkPos chunkPos1, int referenceIn, long seedIn) {
            super(structureIn, chunkPos1, referenceIn, seedIn);
            seed = seedIn;
        }

        @Override
        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos1, Biome biome, NetherShipwreckConfig config, HeightLimitView heightLimitView) {
            int placementHeight = chunkGenerator.getSeaLevel();

            if(!config.isFlying){
                placementHeight = placementHeight + sealevelOffset;
            }
            else{
                ChunkRandom random = new ChunkRandom(seed + (chunkPos1.x * (chunkPos1.z * 17L)));
                placementHeight = placementHeight + random.nextInt(Math.max(chunkGenerator.getWorldHeight() - (placementHeight + 30), 1));
            }

            BlockPos blockpos = new BlockPos(chunkPos1.getStartX(), placementHeight, chunkPos1.getStartZ());
            StructurePoolBasedGenerator.method_30419(
                    dynamicRegistryManager,
                    new StructurePoolFeatureConfig(() -> dynamicRegistryManager.get(Registry.STRUCTURE_POOL_KEY).get(startPool), 6),
                    PoolStructurePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this,
                    random,
                    false,
                    false,
                    heightLimitView);
            this.setBoundingBoxFromChildren();
        }
    }
}