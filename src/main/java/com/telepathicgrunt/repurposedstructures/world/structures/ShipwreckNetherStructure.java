package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.NetherShipwreckConfig;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.MarginedStructureStart;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.settings.StructureSeparationSettings;

import java.util.List;


public class ShipwreckNetherStructure extends AbstractBaseStructure<NetherShipwreckConfig> {
    // Special thanks to cannon_foddr and miguelforge for allowing me to use their nether shipwreck design!

    private final ResourceLocation startPool;
    private final int sealevelOffset;


    public ShipwreckNetherStructure(ResourceLocation startPool, int sealevelOffset) {
        super(NetherShipwreckConfig.CODEC);
        this.startPool = startPool;
        this.sealevelOffset = sealevelOffset;
        RSStructures.RS_STRUCTURE_START_PIECES.add(startPool);
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NetherShipwreckConfig config) {

        // Check to see if there some air where the structure wants to spawn.
        // Doesn't account for rotation of structure.
        BlockPos blockPos;
        if(!config.isFlying){
            blockPos = new BlockPos(chunkX << 4, chunkGenerator.getSeaLevel() + 1, chunkZ << 4);
        }
        else{
            SharedSeedRandom random = new SharedSeedRandom(seed + (chunkX * (chunkZ * 17)));
            int height = chunkGenerator.getSeaLevel() + random.nextInt(Math.max(chunkGenerator.getGenDepth() - (chunkGenerator.getSeaLevel() + 30), 1));
            blockPos = new BlockPos(chunkX << 4, height, chunkZ << 4);
        }

        int checkRadius = 16;
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for(int xOffset = -checkRadius; xOffset <= checkRadius; xOffset += 8){
            for(int zOffset = -checkRadius; zOffset <= checkRadius; zOffset += 8){
                IBlockReader blockView = chunkGenerator.getBaseColumn(xOffset + blockPos.getX(), zOffset + blockPos.getZ());
                for(int yOffset = 0; yOffset <= 30; yOffset += 5){
                    mutable.set(blockPos).move(xOffset, yOffset, zOffset);
                    if (!blockView.getBlockState(mutable).isAir()) {
                        return false;
                    }
                }
            }
        }

        //cannot be near any other structure
        for (int curChunkX = chunkX - 3; curChunkX <= chunkX + 3; curChunkX++) {
            for (int curChunkZ = chunkZ - 3; curChunkZ <= chunkZ + 3; curChunkZ++) {
                if(curChunkX == chunkX && curChunkZ == chunkZ) continue; // Prevent detecting the structure itself and thus, never spawning if structure is in its own blacklist

                for(Structure<?> structure : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE)){
                    StructureSeparationSettings structureConfig = chunkGenerator.getSettings().getConfig(structure);
                    if(structureConfig != null && structureConfig.spacing() > 8) {
                        ChunkPos chunkPos2 = structure.getPotentialFeatureChunk(structureConfig, seed, chunkRandom, curChunkX, curChunkZ);
                        if (curChunkX == chunkPos2.x && curChunkZ == chunkPos2.z) {
                            return false;
                        }
                    }
                }
            }
        }

        return super.isFeatureChunk(chunkGenerator, biomeSource, seed, chunkRandom, chunkX, chunkZ, biome, chunkPos, config);
    }

    private static final List<MobSpawnInfo.Spawners> MONSTER_SPAWNS =
            Lists.newArrayList(new MobSpawnInfo.Spawners(EntityType.WITHER_SKELETON, 25, 1, 1));

    @Override
    public List<MobSpawnInfo.Spawners> getDefaultSpawnList() {
        return MONSTER_SPAWNS;
    }

    @Override
    public Structure.IStartFactory<NetherShipwreckConfig> getStartFactory() {
        return Start::new;
    }

    public class Start extends MarginedStructureStart<NetherShipwreckConfig> {
        private final long seed;

        public Start(Structure<NetherShipwreckConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
            seed = seedIn;
        }

        @Override
        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NetherShipwreckConfig config) {
            int placementHeight = chunkGenerator.getSeaLevel();

            if(!config.isFlying){
                placementHeight = placementHeight + sealevelOffset;
            }
            else{
                SharedSeedRandom random = new SharedSeedRandom(seed + (chunkX * (chunkZ * 17)));
                placementHeight = placementHeight + random.nextInt(Math.max(chunkGenerator.getGenDepth() - (placementHeight + 30), 6));
            }

            BlockPos blockPos = new BlockPos(chunkX * 16, placementHeight, chunkZ * 16);
            JigsawManager.addPieces(
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.registryOrThrow(
                            Registry.TEMPLATE_POOL_REGISTRY).get(startPool),
                            1),
                    AbstractVillagePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockPos,
                    this.pieces,
                    this.random,
                    false,
                    false);
            this.calculateBoundingBox();
        }
    }
}