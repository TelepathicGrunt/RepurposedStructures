package com.telepathicgrunt.repurposedstructures.mixin;

import com.telepathicgrunt.repurposedstructures.misc.StructureModdedLootImporter;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(LootTable.class)
public class LootTableMixin {

    /**
     * Allow use to import modded items to our structure's loottables
     * @author TelepathicGrunt
     */
    @Inject(method = "generateLoot(Lnet/minecraft/loot/context/LootContext;)Ljava/util/List;",
            at = @At(value = "RETURN"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    private void modifyLoot(LootContext context, CallbackInfoReturnable<List<ItemStack>> cir, List<ItemStack> list){
        List<ItemStack> newList = StructureModdedLootImporter.checkAndGetModifiedLoot(context, (LootTable)(Object)this, list);
        if(!newList.isEmpty()) cir.setReturnValue(newList);
    }
}
