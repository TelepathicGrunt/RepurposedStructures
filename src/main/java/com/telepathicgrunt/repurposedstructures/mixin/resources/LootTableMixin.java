package com.telepathicgrunt.repurposedstructures.mixin.resources;

import com.telepathicgrunt.repurposedstructures.misc.lootmanager.StructureModdedLootImporter;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
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
    @Inject(method = "getRandomItems(Lnet/minecraft/world/level/storage/loot/LootContext;)Ljava/util/List;",
            at = @At(value = "RETURN"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    private void repurposedstructures_modifyLoot(LootContext context, CallbackInfoReturnable<List<ItemStack>> cir, List<ItemStack> list) {
        List<ItemStack> newList = StructureModdedLootImporter.checkAndGetModifiedLoot(context, (LootTable)(Object)this, list);
        if(!newList.isEmpty()) cir.setReturnValue(newList);
    }
}
