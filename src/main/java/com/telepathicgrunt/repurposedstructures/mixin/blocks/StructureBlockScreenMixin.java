package com.telepathicgrunt.repurposedstructures.mixin.blocks;

import net.minecraft.client.gui.screen.ingame.StructureBlockScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Make Structure Blocks no longer capped at 64 characters which makes it harder to
 * load some of Repurposed Structures's village pieces.
 *
 * Mixin priority set to 995 so other mixins here will override this mixin by default if they edit the same field.
 */
@Mixin(value = StructureBlockScreen.class, priority = 995)
public class StructureBlockScreenMixin {

    @Shadow
    private TextFieldWidget inputName;

    @Inject(method = "init",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/TextFieldWidget;setText(Ljava/lang/String;)V", ordinal = 0))
    private void repurposedstructures_makeFileNameLonger(CallbackInfo ci) {
        inputName.setMaxLength(128);
    }
}

