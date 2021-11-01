package com.telepathicgrunt.repurposedstructures.mixin.structures;

import com.mojang.datafixers.util.Either;
import com.telepathicgrunt.repurposedstructures.misc.CharmFix;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.feature.structures.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(SinglePoolElement.class)
public class CharmSinglePoolElementMixin {
    @Shadow
    @Final
    protected Either<ResourceLocation, StructureTemplate> template;

    // Undo charm's broken processors until they update.
    @Inject(
            method = "getSettings(Lnet/minecraft/world/level/block/Rotation;Lnet/minecraft/world/level/levelgen/structure/BoundingBox;Z)Lnet/minecraft/world/level/levelgen/structure/templatesystem/StructurePlaceSettings;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/structure/templatesystem/StructureProcessorList;list()Ljava/util/List;"),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private void repurposedstructures_undoCharmsProcessors(Rotation rotation, BoundingBox mutableBoundingBox, boolean isNotJigsaw, CallbackInfoReturnable<StructurePlaceSettings> cir, StructurePlaceSettings placementsettings) {
        CharmFix.UndoCharmProcessors(template, isNotJigsaw, placementsettings);
    }
}
