package com.telepathicgrunt.repurposedstructures.mixin;

import com.google.gson.GsonBuilder;
import com.telepathicgrunt.repurposedstructures.world.numberproviders.ProbabilityValue;
import net.minecraft.loot.LootSerializers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LootSerializers.class)
public abstract class LootSerializersMixin {

    /**
     * Allow RS condition to be able to be used in loot tables.
     * Our probability condition allows us to better control chances of loot drops when using bonus rolls.
     * @author TelepathicGrunt
     */
    @Inject(method = "createConditionSerializer()Lcom/google/gson/GsonBuilder;",
            at = @At(value = "RETURN"))
    private static void repurposedstructures_addNewNumberProvider(CallbackInfoReturnable<GsonBuilder> cir){
       cir.getReturnValue().registerTypeAdapter(ProbabilityValue.class, new ProbabilityValue.Serializer());
    }
}
