package com.telepathicgrunt.repurposedstructures.world.numberproviders;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.telepathicgrunt.repurposedstructures.modinit.RSNumberProviders;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.providers.number.LootNumberProviderType;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;

public class ProbabilityValue implements NumberProvider {
    final float probability;
    final int resultCount;

    ProbabilityValue(float probability, int resultCount) {
        this.probability = probability;
        this.resultCount = resultCount;
    }

    @Override
    public LootNumberProviderType getType() {
        return RSNumberProviders.PROBABILITY;
    }

    @Override
    public float getFloat(LootContext lootContext) {
        return lootContext.getRandom().nextFloat() <= this.probability ? this.resultCount : 0;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object != null && this.getClass() == object.getClass()) {
            return Float.compare(((ProbabilityValue)object).probability, this.probability) == 0 && ((ProbabilityValue)object).resultCount == this.resultCount;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.probability != 0.0F ? Float.floatToIntBits(this.probability) : 0;
    }

    public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<ProbabilityValue> {
        public void serialize(JsonObject jsonObject, ProbabilityValue probabilityValue, JsonSerializationContext jsonSerializationContext) {
            jsonObject.addProperty("probability", probabilityValue.probability);
            jsonObject.addProperty("result_count", probabilityValue.resultCount);
        }

        public ProbabilityValue deserialize(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            float probability = GsonHelper.getAsFloat(jsonObject, "probability");
            int count = GsonHelper.getAsInt(jsonObject, "result_count");
            return new ProbabilityValue(probability, count);
        }
    }
}
