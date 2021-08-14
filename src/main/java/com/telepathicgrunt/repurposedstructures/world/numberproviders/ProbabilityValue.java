package com.telepathicgrunt.repurposedstructures.world.numberproviders;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.telepathicgrunt.repurposedstructures.modinit.RSNumberProviders;
import net.minecraft.loot.IRandomRange;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;

import java.lang.reflect.Type;
import java.util.Random;

public class ProbabilityValue implements IRandomRange {
    final float probability;
    final int resultCount;

    ProbabilityValue(float probability, int resultCount) {
        this.probability = probability;
        this.resultCount = resultCount;
    }

    @Override
    public ResourceLocation getType() {
        return RSNumberProviders.PROBABILITY;
    }

    @Override
    public int getInt(Random random) {
        return random.nextFloat() <= this.probability ? this.resultCount : 0;
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

    public static class Serializer implements JsonDeserializer<ProbabilityValue>, JsonSerializer<ProbabilityValue> {
        public JsonElement serialize(ProbabilityValue probabilityValue, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("probability", probabilityValue.probability);
            jsonObject.addProperty("result_count", probabilityValue.resultCount);
            return jsonObject;
        }

        public ProbabilityValue deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject jsonobject = JSONUtils.convertToJsonObject(jsonElement, "value");
            float probability = JSONUtils.getAsFloat(jsonobject, "probability");
            int resultCount = JSONUtils.getAsInt(jsonobject, "result_count");
            return new ProbabilityValue(probability, resultCount);
        }
    }
}
