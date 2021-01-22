package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;


public class GenericNetherJigsawHighStructure extends AbstractBaseStructure {
    // Special thanks to /r/l-ll-ll-l_IsDisLoss for allowing me to mimic his nether pyramid design!

    private final ResourceLocation startPool;
    private final int size;
    private final int heightOffset;
    private final int lavaOffset;
    public GenericNetherJigsawHighStructure(ResourceLocation poolRL, int size, int heightOffset, int lavaOffset) {
        super(NoFeatureConfig.CODEC);
        startPool = poolRL;
        this.size = size;
        this.heightOffset = heightOffset;
        this.lavaOffset = lavaOffset;
        RSStructures.RS_STRUCTURE_START_PIECES.add(startPool);
    }

    @Override
    public Structure.IStartFactory<NoFeatureConfig> getStartFactory() {
        return GenericNetherJigsawHighStructure.Start::new;
    }

    public class Start extends AbstractNetherStructure.AbstractStart {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public void init(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig NoFeatureConfig) {
            BlockPos blockPos = new BlockPos(chunkX << 4, 0, chunkZ << 4);
            JigsawManager.method_30419(
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.get(
                            Registry.TEMPLATE_POOL_WORLDGEN).getOrDefault(startPool),
                            size),
                    AbstractVillagePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockPos,
                    this.components,
                    this.rand,
                    true,
                    false);
            this.recalculateStructureSize();

            // Needed because the offsetting method offsets the bounds but the structure piece
            // is actually 10 blocks higher than the bound's minimum Y. Wack.
            int boundOffset = -10;
            BlockPos highestLandPos = getHighestLand(chunkGenerator);
            this.func_214626_a(this.rand,
                    Math.max((highestLandPos.getY() + heightOffset) - 1, 29 + lavaOffset) + boundOffset,
                    Math.max(highestLandPos.getY() + heightOffset, 30 + lavaOffset) + boundOffset);
        }
    }
}