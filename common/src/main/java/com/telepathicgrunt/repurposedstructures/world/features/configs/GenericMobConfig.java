package com.telepathicgrunt.repurposedstructures.world.features.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import java.util.Optional;

public class GenericMobConfig implements FeatureConfiguration {
    public static final Codec<GenericMobConfig> CODEC = RecordCodecBuilder.create((configInstance) -> configInstance.group(
            BuiltInRegistries.ITEM.byNameCodec().optionalFieldOf("held_item").forGetter(config -> config.heldItem),
            BuiltInRegistries.ITEM.byNameCodec().optionalFieldOf("helmet").forGetter(config -> config.helmet),
            BuiltInRegistries.ITEM.byNameCodec().optionalFieldOf("chestplate").forGetter(config -> config.chestplate),
            BuiltInRegistries.ITEM.byNameCodec().optionalFieldOf("leggings").forGetter(config -> config.leggings),
            BuiltInRegistries.ITEM.byNameCodec().optionalFieldOf("boots").forGetter(config -> config.boots),
            Codec.floatRange(0, Float.MAX_VALUE).fieldOf("speed_modifier").forGetter(config -> config.speedModifier),
            Codec.intRange(0, Integer.MAX_VALUE).fieldOf("health").forGetter(config -> config.health)
    ).apply(configInstance, GenericMobConfig::new));

    public final Optional<Item> heldItem;
    public final Optional<Item> helmet;
    public final Optional<Item> chestplate;
    public final Optional<Item> leggings;
    public final Optional<Item> boots;
    public final float speedModifier;
    public final int health;

    public GenericMobConfig(Optional<Item> heldItem, Optional<Item> helmet, Optional<Item> chestplate, Optional<Item> leggings,
                            Optional<Item> boots, float speedModifier, int health) {
        this.heldItem = heldItem;
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
        this.speedModifier = speedModifier;
        this.health = health;
    }
}
