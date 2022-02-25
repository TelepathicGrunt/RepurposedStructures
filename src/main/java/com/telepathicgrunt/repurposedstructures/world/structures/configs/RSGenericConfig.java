package com.telepathicgrunt.repurposedstructures.world.structures.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class RSGenericConfig {

    public static final Codec<RSGenericConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(config -> config.startPool),
                    Codec.intRange(0, 30).fieldOf("size").forGetter(config -> config.maxDepth)
            ).apply(instance, RSGenericConfig::new));

    public final Holder<StructureTemplatePool> startPool;
    public final int maxDepth;

    public RSGenericConfig(Holder<StructureTemplatePool> startPool, int maxDepth) {
        this.startPool = startPool;
        this.maxDepth = maxDepth;
    }
}
