package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.SingleJigsawPiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;


public class OutpostWarpedStructure extends Structure<NoFeatureConfig> {
    // Special thanks to /r/l-ll-ll-l_IsDisLoss for allowing me to mimic his Nether Outpost design!

    static {
        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"outposts/warped/base_plates"), new ResourceLocation("empty"),
                ImmutableList.of(Pair.of(
                        new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/warped/base_plate"), 1)),
                JigsawPattern.PlacementBehaviour.RIGID));

        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"outposts/warped/towers"), new ResourceLocation("empty"),
                ImmutableList.of(
                        Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/warped/tower"), 1),
                        Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/warped/tower_glowing"), 1)),
                JigsawPattern.PlacementBehaviour.RIGID));

        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"outposts/warped/plates"), new ResourceLocation("empty"),
                ImmutableList.of(Pair.of(
                        new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/warped/plate"), 1)),
                JigsawPattern.PlacementBehaviour.RIGID));

        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"outposts/warped/features"), new ResourceLocation("empty"),
                ImmutableList.of(
                        Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/warped/cage1"), 1),
                        Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/warped/cage2"), 1),
                        Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/warped/logs"), 1),
                        Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/warped/tent1"), 1),
                        Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/warped/tent2"), 1),
                        Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":outposts/warped/targets"), 1)
                ),
                JigsawPattern.PlacementBehaviour.RIGID));
    }

    private static final List<Biome.SpawnListEntry> MONSTER_SPAWNS = Lists.newArrayList(new Biome.SpawnListEntry(EntityType.field_233591_ai_, 10, 1, 1));

    public OutpostWarpedStructure(Codec<NoFeatureConfig> config) {
        super(config);
    }

    @Override
    public Structure.IStartFactory<NoFeatureConfig> getStartFactory() {
        return OutpostWarpedStructure.Start::new;
    }

    @Override
    public List<Biome.SpawnListEntry> getSpawnList() {
        return MONSTER_SPAWNS;
    }

    public static class Start extends AbstractNetherStructure.AbstractStart{
        ResourceLocation NETHER_OUTPOST_POOL = new ResourceLocation(RepurposedStructures.MODID,"outposts/warped/base_plates");

        public Start(Structure<NoFeatureConfig> structureFeature, int x, int z, MutableBoundingBox blockBox, int referenceIn, long seed) {
            super(structureFeature, x, z, blockBox, referenceIn, seed);
        }

        public void init(ChunkGenerator chunkGenerator, TemplateManager structureManager, int x, int z, Biome biome, NoFeatureConfig NoFeatureConfig) {
            BlockPos blockPos = new BlockPos(x * 16, 0, z * 16);
            GeneralJigsawGenerator.addPieces(chunkGenerator, structureManager, blockPos, this.components, this.rand, NETHER_OUTPOST_POOL, 11);
            this.recalculateStructureSize();

            BlockPos lowestLandPos = getHighestLand(chunkGenerator);
            if (lowestLandPos.getY() >= 108 || lowestLandPos.getY() <= 37) {
                this.func_214626_a(this.rand, 19, 20);
            }
            else {
                this.func_214626_a(this.rand, lowestLandPos.getY()-15, lowestLandPos.getY()-14);
            }
        }
    }
}