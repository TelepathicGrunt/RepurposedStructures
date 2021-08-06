package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.structures.configs.NetherShipwreckConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.NoiseAffectingStructureStart;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;


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
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, WorldgenRandom chunkRandom, ChunkPos chunkPos1, Biome biome, ChunkPos chunkPos, NetherShipwreckConfig config, LevelHeightAccessor heightLimitView) {

        // Check to see if there some air where the structure wants to spawn.
        // Doesn't account for rotation of structure.
        BlockPos blockPos;
        if(!config.isFlying){
            blockPos = new BlockPos(chunkPos1.getMinBlockX(), chunkGenerator.getSeaLevel() + 1, chunkPos1.getMinBlockZ());
        }
        else{
            WorldgenRandom random = new WorldgenRandom(seed + (chunkPos1.x * (chunkPos1.z * 17L)));
            int height = chunkGenerator.getSeaLevel() + random.nextInt(Math.max(chunkGenerator.getGenDepth() - (chunkGenerator.getSeaLevel() + 30), 1));
            blockPos = new BlockPos(chunkPos1.getMinBlockX(), height, chunkPos1.getMinBlockZ());
        }

        int checkRadius = 16;
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for(int xOffset = -checkRadius; xOffset <= checkRadius; xOffset += 8){
            for(int zOffset = -checkRadius; zOffset <= checkRadius; zOffset += 8){
                NoiseColumn blockView = chunkGenerator.getBaseColumn(xOffset + blockPos.getX(), zOffset + blockPos.getZ(), heightLimitView);
                for(int yOffset = 0; yOffset <= 30; yOffset += 5){
                    mutable.set(blockPos).move(xOffset, yOffset, zOffset);
                    if (!blockView.getBlockState(mutable).isAir()) {
                        return false;
                    }
                }
            }
        }

        //cannot be near any other structure
        int structureCheckRadius = 3;
        for (int curChunkX = chunkPos1.x - structureCheckRadius; curChunkX <= chunkPos1.x + structureCheckRadius; curChunkX++) {
            for (int curChunkZ = chunkPos1.z - structureCheckRadius; curChunkZ <= chunkPos1.z + structureCheckRadius; curChunkZ++) {
                for(StructureFeature<?> structureFeature : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE)){
                    if(structureFeature == this) continue;

                    StructureFeatureConfiguration structureConfig = chunkGenerator.getSettings().getConfig(structureFeature);
                    if(structureConfig != null && structureConfig.spacing() > 8){
                        ChunkPos chunkPos2 = structureFeature.getPotentialFeatureChunk(structureConfig, seed, chunkRandom, curChunkX, curChunkZ);
                        if (curChunkX == chunkPos2.x && curChunkZ == chunkPos2.z) {
                            return false;
                        }
                    }
                }
            }
        }

        return super.isFeatureChunk(chunkGenerator, biomeSource, seed, chunkRandom, chunkPos1, biome, chunkPos, config, heightLimitView);
    }

    @Override
    public StructureStartFactory<NetherShipwreckConfig> getStartFactory() {
        return ShipwreckNetherStructure.Start::new;
    }

    public class Start extends NoiseAffectingStructureStart<NetherShipwreckConfig> {
        private final long seed;

        public Start(StructureFeature<NetherShipwreckConfig> structureIn, ChunkPos chunkPos1, int referenceIn, long seedIn) {
            super(structureIn, chunkPos1, referenceIn, seedIn);
            seed = seedIn;
        }

        @Override
        public void generatePieces(RegistryAccess dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos1, Biome biome, NetherShipwreckConfig config, LevelHeightAccessor heightLimitView) {
            int placementHeight = chunkGenerator.getSeaLevel();

            if(!config.isFlying){
                placementHeight = placementHeight + sealevelOffset;
            }
            else{
                WorldgenRandom random = new WorldgenRandom(seed + (chunkPos1.x * (chunkPos1.z * 17L)));
                placementHeight = placementHeight + random.nextInt(Math.max(chunkGenerator.getGenDepth() - (placementHeight + 30), 1));
            }

            BlockPos blockpos = new BlockPos(chunkPos1.getMinBlockX(), placementHeight, chunkPos1.getMinBlockZ());
            JigsawPlacement.addPieces(
                    dynamicRegistryManager,
                    new JigsawConfiguration(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(startPool), 6),
                    PoolElementStructurePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this,
                    random,
                    false,
                    false,
                    heightLimitView);
            GeneralUtils.centerAllPieces(blockpos, this.pieces);
            this.getBoundingBox();
        }
    }
}