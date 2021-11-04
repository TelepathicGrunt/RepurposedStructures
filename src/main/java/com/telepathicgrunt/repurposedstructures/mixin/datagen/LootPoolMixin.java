package com.telepathicgrunt.repurposedstructures.mixin.datagen;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.RandomRanges;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.lang.reflect.Type;

@Mixin(LootPool.Serializer.class)
public class LootPoolMixin {

    /**
     * If you are not datagenning within Repurposed Structures's project and this mixin runs, then let me know as this should not be.
     * @author TelepathicGrunt.
     */
    @Overwrite
    public JsonElement serialize(LootPool lootPool, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonobject = new JsonObject();
        LootPoolAccessor lootPoolAccessor = ((LootPoolAccessor)lootPool);

        if (lootPoolAccessor.getName() != null && !lootPoolAccessor.getName().startsWith("custom#"))
            jsonobject.add("name", jsonSerializationContext.serialize(lootPoolAccessor.getName()));
        jsonobject.add("rolls", RandomRanges.serialize(lootPoolAccessor.getRolls(), jsonSerializationContext));
        if (lootPoolAccessor.getBonusRolls().getMin() != 0.0F && lootPoolAccessor.getBonusRolls().getMax() != 0.0F) {
            jsonobject.add("bonus_rolls", jsonSerializationContext.serialize(lootPoolAccessor.getBonusRolls()));
        }

        jsonobject.add("entries", jsonSerializationContext.serialize(lootPoolAccessor.getEntries()));

        if (!lootPoolAccessor.getConditions().isEmpty()) {
            jsonobject.add("conditions", jsonSerializationContext.serialize(lootPoolAccessor.getConditions()));
        }

        if (!ArrayUtils.isEmpty(lootPoolAccessor.getFunctions())) {
            jsonobject.add("functions", jsonSerializationContext.serialize(lootPoolAccessor.getFunctions()));
        }

        return jsonobject;
    }
}
