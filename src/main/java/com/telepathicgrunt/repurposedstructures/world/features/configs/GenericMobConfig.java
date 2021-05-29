package com.telepathicgrunt.repurposedstructures.world.features.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class GenericMobConfig implements IFeatureConfig {
    public static final Codec<GenericMobConfig> CODEC = RecordCodecBuilder.<GenericMobConfig>create((configInstance) -> configInstance.group(
            Registry.ITEM.fieldOf("held_item").forGetter(config -> config.heldItem),
            Registry.ITEM.fieldOf("helmet").forGetter(config -> config.helmet),
            Registry.ITEM.fieldOf("chestplate").forGetter(config -> config.chestplate),
            Registry.ITEM.fieldOf("leggings").forGetter(config -> config.leggings),
            Registry.ITEM.fieldOf("boots").forGetter(config -> config.boots),
            Codec.floatRange(0, Float.MAX_VALUE).fieldOf("speed_modifier").forGetter(config -> config.speedModifier),
            Codec.intRange(0, Integer.MAX_VALUE).fieldOf("health").forGetter(config -> config.health)
    ).apply(configInstance, GenericMobConfig::new));

    public final Item heldItem;
    public final Item helmet;
    public final Item chestplate;
    public final Item leggings;
    public final Item boots;
    public final float speedModifier;
    public final int health;

    public GenericMobConfig(Item heldItem, Item helmet, Item chestplate, Item leggings,
                            Item boots, float speedModifier, int health)
    {
        // The seralizing to and from json makes null turn into air which we don't want to store
        this.heldItem = heldItem != Items.AIR ? heldItem : null;
        this.helmet = helmet != Items.AIR ? helmet : null;
        this.chestplate = chestplate != Items.AIR ? chestplate : null;
        this.leggings = leggings != Items.AIR ? leggings : null;
        this.boots = boots != Items.AIR ? boots : null;
        this.speedModifier = speedModifier;
        this.health = health;
    }
}
