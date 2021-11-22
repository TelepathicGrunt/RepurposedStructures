package com.telepathicgrunt.repurposedstructures.mixin.structures;

import com.telepathicgrunt.repurposedstructures.misc.MobSpawningOverTime;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.StructureFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.structures.StructureTemplatePool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.BiConsumer;

@Mixin(StructureFeatures.class)
public abstract class StructureFeaturesMixin {

    @Shadow
    private static void register(BiConsumer<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>> biConsumer, ConfiguredStructureFeature<?, ?> configuredStructureFeature, ResourceKey<Biome> resourceKey) {}

    @Inject(
        method = "registerStructures(Ljava/util/function/BiConsumer;)V",
        at = @At(value = "HEAD")
    )
    private static void repurposedstructures_addStructuresToBiomes(BiConsumer<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>> biConsumer, CallbackInfo ci) {
        register(biConsumer, RSConfiguredStructures.VILLAGE_SWAMP, Biomes.SWAMP);
    }
}