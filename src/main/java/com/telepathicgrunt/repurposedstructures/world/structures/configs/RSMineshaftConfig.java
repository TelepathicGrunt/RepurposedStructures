package com.telepathicgrunt.repurposedstructures.world.structures.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.feature.MineshaftFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.MineshaftConfiguration;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class RSMineshaftConfig {

    public static final Codec<RSMineshaftConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter(config -> config.probability),
            StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(config -> config.startPool),
            Codec.intRange(0, 30).fieldOf("size").forGetter(config -> config.maxDepth),
            Codec.INT.fieldOf("max_y").forGetter(config -> config.maxY),
            Codec.INT.fieldOf("min_y").forGetter(config -> config.minY)
    ).apply(instance, RSMineshaftConfig::new));

    public final float probability;
    public final Holder<StructureTemplatePool> startPool;
    public final int maxDepth;
    public final int maxY;
    public final int minY;

    public RSMineshaftConfig(float probability, Holder<StructureTemplatePool> startPool, int maxDepth, int maxY, int minY) {
        this.probability = probability;
        this.startPool = startPool;
        this.maxDepth = maxDepth;
        this.maxY = maxY;
        this.minY = minY;

        if(maxY < minY) {
            throw new RuntimeException(RepurposedStructures.MODID + ": max y should never be below min y for RS Mineshafts : " + startPool.toString());
        }
    }
}
