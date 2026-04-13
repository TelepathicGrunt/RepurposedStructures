package com.telepathicgrunt.repurposedstructures.mixins.resources;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.telepathicgrunt.repurposedstructures.misc.lootmanager.StructureModdedLootImporterApplier;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LootTable.class)
public class LootTableMixin {

    /**
     * Allow use to import modded items to our structure's loottables
     *
     * @author TelepathicGrunt
     */
    @ModifyReturnValue(method = "getRandomItems(Lnet/minecraft/world/level/storage/loot/LootContext;)Lit/unimi/dsi/fastutil/objects/ObjectArrayList;",
            at = @At(value = "RETURN"))
    private ObjectArrayList<ItemStack> repurposedstructures_modifyLoot(ObjectArrayList<ItemStack> list, LootContext lootContext) {
        StructureModdedLootImporterApplier.checkAndGetModifiedLoot(lootContext, (LootTable)(Object)this, list);
        return list;
    }
}
