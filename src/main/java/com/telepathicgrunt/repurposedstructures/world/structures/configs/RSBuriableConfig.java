package com.telepathicgrunt.repurposedstructures.world.structures.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class RSBuriableConfig implements FeatureConfiguration {

    public static final Codec<RSBuriableConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(config -> config.startPool),
            Codec.intRange(0, 30).fieldOf("size").forGetter(config -> config.size),
            Codec.INT.fieldOf("offset_amount").orElse(0).forGetter(config -> config.offsetAmount),
            Codec.BOOL.fieldOf("use_ocean_heightmap").orElse(false).forGetter(config -> config.useOceanHeightmap),
            Codec.BOOL.fieldOf("can_spawn_in_liquid").orElse(true).forGetter(config -> config.cannotSpawnInWater)
    ).apply(instance, RSBuriableConfig::new));

    public final Holder<StructureTemplatePool> startPool;
    public final int size;
    public final int offsetAmount;
    public final boolean useOceanHeightmap;
    public final boolean cannotSpawnInWater;

    public RSBuriableConfig(Holder<StructureTemplatePool> startPool, int size, int offsetAmount, boolean useOceanHeightmap, boolean cannotSpawnInWater) {
        this.startPool = startPool;
        this.size = size;
        this.offsetAmount = offsetAmount;
        this.useOceanHeightmap = useOceanHeightmap;
        this.cannotSpawnInWater = cannotSpawnInWater;
    }
}
