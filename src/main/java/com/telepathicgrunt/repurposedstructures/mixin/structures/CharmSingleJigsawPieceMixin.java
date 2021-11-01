package com.telepathicgrunt.repurposedstructures.mixin.structures;

import com.mojang.datafixers.util.Either;
import com.telepathicgrunt.repurposedstructures.misc.CharmFix;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.feature.jigsaw.SingleJigsawPiece;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(SingleJigsawPiece.class)
public class CharmSingleJigsawPieceMixin {
    @Shadow
    @Final
    protected Either<ResourceLocation, Template> template;

    // Undo charm's broken processors until they update.
    @Inject(
            method = "getSettings(Lnet/minecraft/util/Rotation;Lnet/minecraft/util/math/MutableBoundingBox;Z)Lnet/minecraft/world/gen/feature/template/PlacementSettings;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/feature/template/StructureProcessorList;list()Ljava/util/List;"),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private void repurposedstructures_undoCharmsProcessors(Rotation rotation, MutableBoundingBox mutableBoundingBox, boolean isNotJigsaw, CallbackInfoReturnable<PlacementSettings> cir, PlacementSettings placementsettings) {
        CharmFix.UndoCharmProcessors(template, isNotJigsaw, placementsettings);
    }
}
