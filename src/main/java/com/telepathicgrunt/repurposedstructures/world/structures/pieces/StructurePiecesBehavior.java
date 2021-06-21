package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.util.Identifier;

import java.util.HashMap;

public class StructurePiecesBehavior {

    public static void init() {
    }

    public record RequiredPieceNeeds(int maxLimit, int minDistanceFromCenter) {

        public int getRequiredAmount() {
            return maxLimit;
        }

        public int getMinDistanceFromCenter() {
            return minDistanceFromCenter;
        }
    }
    

    public static HashMap<Identifier, Integer> PIECES_COUNT = new HashMap<>();
    static {
        double scaleLimitBasedOnSize = 0.066D;
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "strongholds/nether/library_big"), (int) (4 * (RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdSize * scaleLimitBasedOnSize)));
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "strongholds/nether/library_small"), (int) (2 * (RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdSize * scaleLimitBasedOnSize)));
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "strongholds/nether/prison"), (int) (8 * (RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdSize * scaleLimitBasedOnSize)));
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "strongholds/nether/crossing"), (int) (7 * (RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdSize * scaleLimitBasedOnSize)));
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "strongholds/nether/empty_crossing"), (int) (2 * (RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdSize * scaleLimitBasedOnSize)));
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "strongholds/nether/pillar_crossing"), (int) (3 * (RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdSize * scaleLimitBasedOnSize)));
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "strongholds/nether/fountain_crossing"), (int) (3 * (RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdSize * scaleLimitBasedOnSize)));
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "strongholds/nether/storage_crossing"), (int) (4 * (RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdSize * scaleLimitBasedOnSize)));
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "strongholds/nether/stairs_straight"), (int) (7 * (RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdSize * scaleLimitBasedOnSize)));
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "strongholds/nether/stairs"), (int) (7 * (RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdSize * scaleLimitBasedOnSize)));
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "strongholds/nether/chest_corridor"), (int) (16 * (RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdSize * scaleLimitBasedOnSize)));
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/birch/spawner_1"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/birch/spawner_2"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/birch/spawner_3_end"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/birch/spawner_3_middle"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/birch/spawner_4_middle"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/crimson/spawner_1"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/crimson/spawner_2"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/crimson/spawner_3_end"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/crimson/spawner_3_middle"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/crimson/spawner_4_middle"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/dark_forest/spawner_1"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/dark_forest/spawner_2"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/dark_forest/spawner_3_end"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/dark_forest/spawner_3_middle"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/dark_forest/spawner_4_middle"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/desert/spawner_1"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/desert/spawner_2"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/desert/spawner_3_end"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/desert/spawner_3_middle"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/desert/spawner_4_middle"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/icy/spawner_1"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/icy/spawner_2"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/icy/spawner_3_end"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/icy/spawner_3_middle"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/icy/spawner_4_middle"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/jungle/spawner_1"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/jungle/spawner_2"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/jungle/spawner_3_end"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/jungle/spawner_3_middle"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/jungle/spawner_4_middle"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/nether/spawner_1"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/nether/spawner_2"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/nether/spawner_3_end"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/nether/spawner_3_middle"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/nether/spawner_4_middle"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/ocean/spawner_1"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/ocean/spawner_2"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/ocean/spawner_3_end"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/ocean/spawner_3_middle"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/ocean/spawner_4_middle"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/savanna/spawner_1"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/savanna/spawner_2"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/savanna/spawner_3_end"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/savanna/spawner_3_middle"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/savanna/spawner_4_middle"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/stone/spawner_1"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/stone/spawner_2"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/stone/spawner_3_end"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/stone/spawner_3_middle"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/stone/spawner_4_middle"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/swamp/spawner_1"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/swamp/spawner_2"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/swamp/spawner_3_end"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/swamp/spawner_3_middle"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/swamp/spawner_4_middle"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/taiga/spawner_1"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/taiga/spawner_2"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/taiga/spawner_3_end"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/taiga/spawner_3_middle"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/taiga/spawner_4_middle"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/warped/spawner_1"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/warped/spawner_2"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/warped/spawner_3_end"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/warped/spawner_3_middle"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "mineshafts/warped/spawner_4_middle"), 1);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "fortresses/jungle/balcony"), 3);
        PIECES_COUNT.put(new Identifier(RepurposedStructures.MODID, "fortresses/jungle/4_way_outside"), 6);
    }
}
