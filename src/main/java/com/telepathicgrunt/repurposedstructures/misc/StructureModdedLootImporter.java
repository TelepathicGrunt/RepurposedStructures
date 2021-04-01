package com.telepathicgrunt.repurposedstructures.misc;

import com.google.gson.JsonObject;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.LootContextAccessor;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StructureModdedLootImporter extends LootModifier {

    // Need to map loottables by hand to the vanilla structure that our structure is based on. (usually...)
    private static final Map<ResourceLocation, ResourceLocation> TABLE_IMPORTS = createMap();
    private static Map<ResourceLocation, ResourceLocation> createMap() {
        Map<ResourceLocation, ResourceLocation> tableMap = new HashMap<>();
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/birch"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/crimson"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/desert"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/end"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/icy"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/jungle"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/nether"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/ocean"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/savanna"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/stone"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/swamp_dark_forest"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/taiga"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/warped"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/cities/nether"), new ResourceLocation("minecraft:chests/bastion_treasure")); // new ResourceLocation("minecraft:chests/end_city_treasure"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeon/badlands"), new ResourceLocation("minecraft:chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeon/dark_forest"), new ResourceLocation("minecraft:chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeon/desert"), new ResourceLocation("minecraft:chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeon/end"), new ResourceLocation("minecraft:chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeon/jungle"), new ResourceLocation("minecraft:chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeon/mushroom"), new ResourceLocation("minecraft:chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeon/nether"), new ResourceLocation("minecraft:chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeon/ocean"), new ResourceLocation("minecraft:chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeon/snow"), new ResourceLocation("minecraft:chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeon/swamp"), new ResourceLocation("minecraft:chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/fortress/jungle_center_chest"), new ResourceLocation("minecraft:chests/stronghold_crossing")); // new ResourceLocation("minecraft:chests/nether_bridge"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/fortress/jungle_hallway_chest"), new ResourceLocation("minecraft:chests/stronghold_corridor")); // new ResourceLocation("minecraft:chests/nether_bridge"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/fortress/jungle_shrine_chest"), new ResourceLocation("minecraft:chests/stronghold_crossing")); // new ResourceLocation("minecraft:chests/nether_bridge"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/igloos/grassy_chest"), new ResourceLocation("minecraft:chests/igloo_chest"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/igloos/stone_chest"), new ResourceLocation("minecraft:chests/igloo_chest"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mansion/birch"), new ResourceLocation("minecraft:chests/woodland_mansion"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mansion/desert"), new ResourceLocation("minecraft:chests/woodland_mansion"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mansion/jungle"), new ResourceLocation("minecraft:chests/woodland_mansion"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mansion/oak"), new ResourceLocation("minecraft:chests/woodland_mansion"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mansion/savanna"), new ResourceLocation("minecraft:chests/woodland_mansion"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mansion/snowy"), new ResourceLocation("minecraft:chests/woodland_mansion"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mansion/taiga"), new ResourceLocation("minecraft:chests/woodland_mansion"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outpost/badlands_chest"), new ResourceLocation("minecraft:chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outpost/birch_chest"), new ResourceLocation("minecraft:chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outpost/crimson_chest"), new ResourceLocation("minecraft:chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outpost/desert_chest"), new ResourceLocation("minecraft:chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outpost/giant_tree_taiga_chest"), new ResourceLocation("minecraft:chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outpost/icy_chest"), new ResourceLocation("minecraft:chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outpost/nether_brick_chest"), new ResourceLocation("minecraft:chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outpost/oak_chest"), new ResourceLocation("minecraft:chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outpost/snowy_chest"), new ResourceLocation("minecraft:chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outpost/taiga_chest"), new ResourceLocation("minecraft:chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outpost/warped_chest"), new ResourceLocation("minecraft:chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramid/badlands_chest"), new ResourceLocation("minecraft:chests/desert_pyramid"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramid/nether_chest"), new ResourceLocation("minecraft:chests/desert_pyramid"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramid/snowy_chest"), new ResourceLocation("minecraft:chests/desert_pyramid"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/ruin/nether"), new ResourceLocation("minecraft:chests/bastion_other")); // new ResourceLocation("minecraft:chests/underwater_ruin_big"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/ruined_portal/large_portal_chest"), new ResourceLocation("minecraft:chests/ruined_portal"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/ruined_portal/small_portal_chest"), new ResourceLocation("minecraft:chests/ruined_portal"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwreck/crimson/map_chest"), new ResourceLocation("minecraft:chests/shipwreck_map"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwreck/crimson/supply_chest"), new ResourceLocation("minecraft:chests/shipwreck_supply"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwreck/crimson/treasure_chest"), new ResourceLocation("minecraft:chests/shipwreck_treasure"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwreck/end/map_chest"), new ResourceLocation("minecraft:chests/shipwreck_map"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwreck/end/supply_chest"), new ResourceLocation("minecraft:chests/shipwreck_supply"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwreck/end/treasure_chest"), new ResourceLocation("minecraft:chests/end_city_treasure")); //  new ResourceLocation("minecraft:chests/shipwreck_treasure"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwreck/warped/map_chest"), new ResourceLocation("minecraft:chests/shipwreck_map"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwreck/warped/supply_chest"), new ResourceLocation("minecraft:chests/shipwreck_supply"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwreck/warped/treasure_chest"), new ResourceLocation("minecraft:chests/shipwreck_treasure"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwreck/nether_bricks/treasure_chest"), new ResourceLocation("minecraft:chests/shipwreck_treasure"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/stronghold/stonebrick_storage_room"), new ResourceLocation("minecraft:chests/stronghold_crossing"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/stronghold/stonebrick_hallway"), new ResourceLocation("minecraft:chests/stronghold_corridor"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/stronghold/stonebrick_library"), new ResourceLocation("minecraft:chests/stronghold_library"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/stronghold/nether_storage_room"), new ResourceLocation("minecraft:chests/stronghold_crossing"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/stronghold/nether_hallway"), new ResourceLocation("minecraft:chests/stronghold_corridor"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/stronghold/nether_library"), new ResourceLocation("minecraft:chests/stronghold_library"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/temple/nether_basalt_chest"), new ResourceLocation("minecraft:chests/nether_bridge")); // new ResourceLocation("minecraft:chests/jungle_temple"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/temple/nether_crimson_chest"), new ResourceLocation("minecraft:chests/nether_bridge")); // new ResourceLocation("minecraft:chests/jungle_temple"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/temple/nether_soul_chest"), new ResourceLocation("minecraft:chests/nether_bridge")); // new ResourceLocation("minecraft:chests/jungle_temple"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/temple/nether_warped_chest"), new ResourceLocation("minecraft:chests/nether_bridge")); //  new ResourceLocation("minecraft:chests/jungle_temple"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/temple/nether_warped_trapped_chest"), new ResourceLocation("minecraft:chests/nether_bridge")); // new ResourceLocation("minecraft:chests/jungle_temple"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/temple/nether_wasteland_chest"), new ResourceLocation("minecraft:chests/nether_bridge")); // new ResourceLocation("minecraft:chests/jungle_temple"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/temple/nether_basalt_dispenser"), new ResourceLocation("minecraft:chests/jungle_temple_dispenser"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/temple/nether_crimson_dispenser"), new ResourceLocation("minecraft:chests/jungle_temple_dispenser"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/temple/nether_soul_dispenser"), new ResourceLocation("minecraft:chests/jungle_temple_dispenser"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/temple/nether_warped_dispenser"), new ResourceLocation("minecraft:chests/jungle_temple_dispenser"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/temple/nether_wasteland_dispenser"), new ResourceLocation("minecraft:chests/jungle_temple_dispenser"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/village/village_badands_house"), new ResourceLocation("minecraft:chests/village/village_desert_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/village/village_birch_house"), new ResourceLocation("minecraft:chests/village/village_plains_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/village/village_dark_forest_house"), new ResourceLocation("minecraft:chests/village/village_plains_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/village/village_giant_taiga_house"), new ResourceLocation("minecraft:chests/village/village_taiga_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/village/village_jungle_house"), new ResourceLocation("minecraft:chests/village/village_savanna_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/village/village_mountains_house"), new ResourceLocation("minecraft:chests/village/village_snowy_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/village/village_oak_house"), new ResourceLocation("minecraft:chests/village/village_plains_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/village/village_swamp_house"), new ResourceLocation("minecraft:chests/village/village_plains_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/village/village_crimson_cartographer"), new ResourceLocation("minecraft:chests/village/village_cartographer"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/village/village_crimson_fisher"), new ResourceLocation("minecraft:chests/village/village_fisher"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/village/village_crimson_tanner"), new ResourceLocation("minecraft:chests/village/village_tannery"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/village/village_crimson_weaponsmith"), new ResourceLocation("minecraft:chests/village/village_weaponsmith"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/village/village_crimson_house"), new ResourceLocation("minecraft:chests/village/village_desert_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/village/village_warped_cartographer"), new ResourceLocation("minecraft:chests/village/village_cartographer"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/village/village_warped_fisher"), new ResourceLocation("minecraft:chests/village/village_fisher"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/village/village_warped_tanner"), new ResourceLocation("minecraft:chests/village/village_tannery"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/village/village_warped_weaponsmith"), new ResourceLocation("minecraft:chests/village/village_weaponsmith"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/village/village_warped_house"), new ResourceLocation("minecraft:chests/village/village_desert_house"));
        return tableMap;
    }

    public StructureModdedLootImporter(final ILootCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        ResourceLocation tableToImportLoot = TABLE_IMPORTS.get(context.getQueriedLootTableId());
        if(tableToImportLoot == null) return generatedLoot; // Safety net

        // Generate random loot that would've been in vanilla chests. (Need to make new context or else we recursively call ourselves infinitely)
        LootContext newContext = copyLootContextWithNewQueryID(context, tableToImportLoot);
        List<ItemStack> newlyGeneratedLoot = context.getSupplier(tableToImportLoot).generate(newContext);

        // Remove all vanilla loot so we only have modded loot
        newlyGeneratedLoot.removeIf(itemStack -> itemStack.getItem().getRegistryName().getNamespace().equals("minecraft"));

        // Add modded loot to my structure's chests
        generatedLoot.addAll(newlyGeneratedLoot);
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<StructureModdedLootImporter> {
        @Override
        public StructureModdedLootImporter read(ResourceLocation location, JsonObject object, ILootCondition[] conditions) {
             return new StructureModdedLootImporter(conditions);
        }

        @Override
        public JsonObject write(StructureModdedLootImporter instance) {
            return this.makeConditions(instance.conditions);
        }
    }

    private LootContext copyLootContextWithNewQueryID(LootContext oldLootContext, ResourceLocation newQueryID){
        LootContext newContext = new LootContext.Builder(oldLootContext).build(LootParameterSets.CHEST);
        ((LootContextAccessor)newContext).rs_setQueriedLootTableId(newQueryID); // The normal method won't set it as the newContext already has queriedID.
        return newContext;
    }
}