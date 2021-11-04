package com.telepathicgrunt.repurposedstructures.mixin.datagen;

import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.loot.StandaloneLootEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StandaloneLootEntry.Serializer.class)
public class StandaloneLootEntryMixin<T extends StandaloneLootEntry> {

    /**
     * If you are not datagenning within Repurposed Structures's project and this mixin runs, then let me know as this should not be.
     * @author TelepathicGrunt.
     */
    @Inject(method = "serializeCustom(Lcom/google/gson/JsonObject;Lnet/minecraft/loot/StandaloneLootEntry;Lcom/google/gson/JsonSerializationContext;)V",
            at = @At(value = "HEAD"))
    private void repurposedstructures_writeWeight(JsonObject jsonObject, T t, JsonSerializationContext jsonSerializationContext, CallbackInfo ci){
        if (((StandaloneLootEntryAccessor)t).getWeight() == 1) {
            jsonObject.addProperty("weight", ((StandaloneLootEntryAccessor)t).getWeight());
        }
    }
}
