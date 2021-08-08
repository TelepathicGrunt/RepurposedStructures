package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.google.common.collect.ImmutableMap;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class StructurePiecesBehavior {

    // No need for Lazy in this class because it is init in FMLCommonSetupEvent where config values now exists
    public static void init() {
    }

    public static class RequiredPieceNeeds {
        private final int maxLimit;
        private final Integer minDistanceFromCenter;
        public RequiredPieceNeeds(int maxLimit, Integer minDistanceFromCenter) {
            this.maxLimit = maxLimit;
            this.minDistanceFromCenter = minDistanceFromCenter;
        }

        public int getRequiredAmount(){
            return maxLimit;
        }

        public int getMinDistanceFromCenter(){
            return minDistanceFromCenter;
        }
    }

    public static HashMap<ResourceLocation, Map<ResourceLocation, RequiredPieceNeeds>> REQUIRED_PIECES_COUNT = new HashMap<>();
    static {
        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "stronghold_nether"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "strongholds/nether/portal_room"), new StructurePiecesBehavior.RequiredPieceNeeds(1, (int) (RepurposedStructures.RSStrongholdsConfig.netherStrongholdSize.get() * 0.6D))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "stronghold_end"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "strongholds/end/portal_room"), new StructurePiecesBehavior.RequiredPieceNeeds(1, (int) (RepurposedStructures.RSStrongholdsConfig.strongholdEndSize.get() * 0.6D)),
                new ResourceLocation(RepurposedStructures.MODID, "strongholds/end/empty_crossing"), new StructurePiecesBehavior.RequiredPieceNeeds(2, (int) (RepurposedStructures.RSStrongholdsConfig.strongholdEndSize.get() * 0.35D))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "fortress_jungle"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "fortresses/jungle/spawner"), new StructurePiecesBehavior.RequiredPieceNeeds(2, (int) (RepurposedStructures.RSFortressesConfig.jungleFortressSize.get() * 0.6D)),
                new ResourceLocation(RepurposedStructures.MODID, "fortresses/jungle/balcony"), new StructurePiecesBehavior.RequiredPieceNeeds(1, (int) (RepurposedStructures.RSFortressesConfig.jungleFortressSize.get() * 0.2D)),
                new ResourceLocation(RepurposedStructures.MODID, "fortresses/jungle/turn_inside_chest"), new StructurePiecesBehavior.RequiredPieceNeeds(4, (int) (RepurposedStructures.RSFortressesConfig.jungleFortressSize.get() * 0.6D))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_birch"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/birch/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RepurposedStructures.RSMineshaftsConfig.birchMineshaftSize.get()))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_dark_forest"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/dark_forest/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RepurposedStructures.RSMineshaftsConfig.darkForestMineshaftSize.get()))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_desert"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/desert/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RepurposedStructures.RSMineshaftsConfig.desertMineshaftSize.get()))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_nether"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/nether/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RepurposedStructures.RSMineshaftsConfig.netherMineshaftSize.get()))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_crimson"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/crimson/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RepurposedStructures.RSMineshaftsConfig.crimsonMineshaftSize.get()))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_warped"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/warped/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RepurposedStructures.RSMineshaftsConfig.warpedMineshaftSize.get()))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_icy"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/icy/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RepurposedStructures.RSMineshaftsConfig.icyMineshaftSize.get()))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_jungle"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/jungle/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RepurposedStructures.RSMineshaftsConfig.jungleMineshaftSize.get()))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_ocean"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/ocean/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RepurposedStructures.RSMineshaftsConfig.oceanMineshaftSize.get()))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_savanna"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/savanna/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RepurposedStructures.RSMineshaftsConfig.savannaMineshaftSize.get()))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_stone"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/stone/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RepurposedStructures.RSMineshaftsConfig.stoneMineshaftSize.get()))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_swamp"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/swamp/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RepurposedStructures.RSMineshaftsConfig.swampMineshaftSize.get()))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_taiga"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/taiga/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RepurposedStructures.RSMineshaftsConfig.taigaMineshaftSize.get()))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_end"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/end/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RepurposedStructures.RSMineshaftsConfig.endMineshaftSize.get()))));
    }
    
    public static HashMap<ResourceLocation, Integer> PIECES_COUNT = new HashMap<>();
    static {
        double netherStrongholdLimitScale = 0.066D;
        int netherStrongholdSize = RepurposedStructures.RSStrongholdsConfig.netherStrongholdSize.get();
        double netherStrongholdPieceLimit = netherStrongholdSize * netherStrongholdLimitScale;
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "strongholds/nether/library_big"), (int) (4 * netherStrongholdPieceLimit));
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "strongholds/nether/library_small"), (int) (2 * netherStrongholdPieceLimit));
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "strongholds/nether/prison"), (int) (8 * netherStrongholdPieceLimit));
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "strongholds/nether/crossing"), (int) (7 * netherStrongholdPieceLimit));
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "strongholds/nether/pillar_crossing"), (int) (3 * netherStrongholdPieceLimit));
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "strongholds/nether/fountain_crossing"), (int) (3 * netherStrongholdPieceLimit));
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "strongholds/nether/storage_crossing"), (int) (4 * netherStrongholdPieceLimit));
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "strongholds/nether/stairs_straight"), (int) (7 * netherStrongholdPieceLimit));
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "strongholds/nether/stairs"), (int) (7 * netherStrongholdPieceLimit));
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "strongholds/nether/chest_corridor"), (int) (16 * netherStrongholdPieceLimit));

        double endStrongholdLimitScale = 0.072D;
        int endStrongholdSize = RepurposedStructures.RSStrongholdsConfig.strongholdEndSize.get();
        double endStrongholdPieceLimit = endStrongholdSize * endStrongholdLimitScale;
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "strongholds/end/library_big"), (int) (4 * endStrongholdPieceLimit));
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "strongholds/end/library_small"), (int) (2 * endStrongholdPieceLimit));
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "strongholds/end/prison"), (int) (8 * endStrongholdPieceLimit));
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "strongholds/end/crossing"), (int) (7 * endStrongholdPieceLimit));
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "strongholds/end/pillar_crossing"), (int) (3 * endStrongholdPieceLimit));
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "strongholds/end/fountain_crossing"), (int) (3 * endStrongholdPieceLimit));
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "strongholds/end/storage_crossing"), (int) (4 * endStrongholdPieceLimit));
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "strongholds/end/stairs_straight"), (int) (7 * endStrongholdPieceLimit));
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "strongholds/end/stairs"), (int) (7 * endStrongholdPieceLimit));
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "strongholds/end/chest_corridor"), (int) (16 * endStrongholdPieceLimit));

        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/birch/spawner_1"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/birch/spawner_2"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/birch/spawner_3_end"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/birch/spawner_3_middle"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/birch/spawner_4_middle"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/crimson/spawner_1"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/crimson/spawner_2"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/crimson/spawner_3_end"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/crimson/spawner_3_middle"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/crimson/spawner_4_middle"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/dark_forest/spawner_1"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/dark_forest/spawner_2"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/dark_forest/spawner_3_end"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/dark_forest/spawner_3_middle"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/dark_forest/spawner_4_middle"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/desert/spawner_1"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/desert/spawner_2"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/desert/spawner_3_end"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/desert/spawner_3_middle"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/desert/spawner_4_middle"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/icy/spawner_1"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/icy/spawner_2"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/icy/spawner_3_end"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/icy/spawner_3_middle"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/icy/spawner_4_middle"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/jungle/spawner_1"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/jungle/spawner_2"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/jungle/spawner_3_end"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/jungle/spawner_3_middle"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/jungle/spawner_4_middle"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/nether/spawner_1"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/nether/spawner_2"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/nether/spawner_3_end"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/nether/spawner_3_middle"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/nether/spawner_4_middle"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/ocean/spawner_1"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/ocean/spawner_2"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/ocean/spawner_3_end"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/ocean/spawner_3_middle"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/ocean/spawner_4_middle"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/savanna/spawner_1"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/savanna/spawner_2"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/savanna/spawner_3_end"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/savanna/spawner_3_middle"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/savanna/spawner_4_middle"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/stone/spawner_1"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/stone/spawner_2"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/stone/spawner_3_end"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/stone/spawner_3_middle"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/stone/spawner_4_middle"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/swamp/spawner_1"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/swamp/spawner_2"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/swamp/spawner_3_end"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/swamp/spawner_3_middle"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/swamp/spawner_4_middle"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/taiga/spawner_1"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/taiga/spawner_2"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/taiga/spawner_3_end"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/taiga/spawner_3_middle"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/taiga/spawner_4_middle"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/warped/spawner_1"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/warped/spawner_2"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/warped/spawner_3_end"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/warped/spawner_3_middle"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/warped/spawner_4_middle"), 1);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "fortresses/jungle/balcony"), 3);
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "fortresses/jungle/4_way_outside"), 6);
    }
}
