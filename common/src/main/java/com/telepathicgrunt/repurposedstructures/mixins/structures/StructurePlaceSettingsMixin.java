package com.telepathicgrunt.repurposedstructures.mixins.structures;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StructurePlaceSettings.class)
public class StructurePlaceSettingsMixin {

    @Inject(
            method = "clearProcessors()Lnet/minecraft/world/level/levelgen/structure/templatesystem/StructurePlaceSettings;",
            at = @At(value = "HEAD")
    )
    private void repurposedstructures_AAAA(CallbackInfoReturnable<StructurePlaceSettings> cir) {
        new Exception("Processor Cleared").printStackTrace();
    }

    @Inject(
            method = "addProcessor(Lnet/minecraft/world/level/levelgen/structure/templatesystem/StructureProcessor;)Lnet/minecraft/world/level/levelgen/structure/templatesystem/StructurePlaceSettings;",
            at = @At(value = "HEAD")
    )
    private void repurposedstructures_BBBB(CallbackInfoReturnable<StructurePlaceSettings> cir) {
        new Exception("Processor Added").printStackTrace();
    }

    @Inject(
            method = "popProcessor(Lnet/minecraft/world/level/levelgen/structure/templatesystem/StructureProcessor;)Lnet/minecraft/world/level/levelgen/structure/templatesystem/StructurePlaceSettings;",
            at = @At(value = "HEAD")
    )
    private void repurposedstructures_CCCC(CallbackInfoReturnable<StructurePlaceSettings> cir) {
        new Exception("Processor Popped").printStackTrace();
    }
}