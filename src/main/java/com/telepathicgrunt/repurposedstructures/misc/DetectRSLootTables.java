package com.telepathicgrunt.repurposedstructures.misc;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.reflect.TypeToken;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.loot.ILootSerializer;
import net.minecraft.loot.LootConditionType;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DetectRSLootTables implements ILootCondition
{
    public static final LootConditionType DETECT_RS_LOOT_TABLES = new LootConditionType(new DetectRSLootTables.Serializer());
    private final Set<ResourceLocation> blacklistedLootTableIds;

    private DetectRSLootTables(final Set<ResourceLocation> blacklistedLootTableIds)
    {
        this.blacklistedLootTableIds = blacklistedLootTableIds;
    }

    @Override
    public LootConditionType getType()
    {
        return DETECT_RS_LOOT_TABLES;
    }

    @Override
    public boolean test(LootContext lootContext)
    {
        ResourceLocation lootTableID = lootContext.getQueriedLootTableId();
        return lootTableID.getNamespace().equals(RepurposedStructures.MODID) && !this.blacklistedLootTableIds.contains(lootTableID);
    }

    public static Builder builder(final Set<ResourceLocation> blacklistedLootTableIds)
    {
        return new Builder(blacklistedLootTableIds);
    }

    public static class Builder implements ILootCondition.IBuilder
    {
        private final Set<ResourceLocation> blacklistedLootTableIds;

        public Builder(Set<ResourceLocation> blacklistedLootTableIds)
        {
            if (blacklistedLootTableIds == null) throw new IllegalArgumentException("Blacklisted loot table must not be null. Use \"blacklisted_loot_tables\":[] to denote no blacklist.");
            this.blacklistedLootTableIds = blacklistedLootTableIds;
        }

        @Override
        public ILootCondition build()
        {
            return new DetectRSLootTables(this.blacklistedLootTableIds);
        }
    }

    public static class Serializer implements ILootSerializer<DetectRSLootTables>
    {
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>(){}.getType();

        @Override
        public void toJson(JsonObject object, DetectRSLootTables instance, JsonSerializationContext ctx)
        {
            object.addProperty("loot_table_id", instance.blacklistedLootTableIds.toString());
        }

        @Override
        public DetectRSLootTables fromJson(JsonObject object, JsonDeserializationContext ctx)
        {
            List<String> unconvertedLootTableStrings = gson.fromJson(
                    JSONUtils.getJsonArray(object, "blacklisted_loot_tables").getAsString(), type);

            Set<ResourceLocation> convertedLootTableStrings = unconvertedLootTableStrings.stream()
                    .map(ResourceLocation::new).collect(Collectors.toSet());

            return new DetectRSLootTables(convertedLootTableStrings);
        }
    }
}