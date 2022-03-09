package com.telepathicgrunt.repurposedstructures.mixin.features;

import com.telepathicgrunt.repurposedstructures.mixin.world.WorldGenRegionAccessor;
import com.telepathicgrunt.repurposedstructures.modinit.RSTags;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.SectionPos;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BushFoliagePlacer;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(TreeFeature.class)
public class LessJungleBushInStructuresMixin {

    @Inject(
            method = "place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void repurposedstructures_lessJungleBushInStructures(FeaturePlaceContext<TreeConfiguration> context, CallbackInfoReturnable<Boolean> cir) {
        // Detect jungle bush like tree
        if(context.config().foliagePlacer instanceof BushFoliagePlacer && context.config().minimumSize.minClippedHeight().orElse(0) < 2) {
            // Rate for removal of bush
            if(context.random().nextFloat() < 0.9f) {
                SectionPos sectionPos = SectionPos.of(context.origin());
                ChunkAccess chunkAccess = context.level().getChunk(context.origin());

                Registry<ConfiguredStructureFeature<?,?>> configuredStructureFeatureRegistry = context.level().registryAccess().registryOrThrow(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY);
                StructureFeatureManager structureFeatureManager = ((WorldGenRegionAccessor)context.level()).getStructureFeatureManager();

                for (Holder<ConfiguredStructureFeature<?, ?>> configuredStructureFeature : configuredStructureFeatureRegistry.getOrCreateTag(RSTags.LESS_JUNGLE_BUSHES)) {
                    StructureStart startForFeature = structureFeatureManager.getStartForFeature(sectionPos, configuredStructureFeature.value(), chunkAccess);
                    if (startForFeature != null && startForFeature.isValid()) {
                        cir.setReturnValue(false);
                        return;
                    }
                }
            }
        }
    }
}
