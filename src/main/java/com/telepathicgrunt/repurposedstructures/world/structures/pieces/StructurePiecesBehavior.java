package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.util.Identifier;

import java.util.HashMap;

public class StructurePiecesBehavior {

    public static void init() {
    }

    public static class RequiredPieceNeeds {
        private final int maxLimit;
        private final int minDistanceFromCenter;
        public RequiredPieceNeeds(int maxLimit, int minDistanceFromCenter) {
            this.maxLimit = maxLimit;
            this.minDistanceFromCenter = minDistanceFromCenter;
        }

        public int getMaxLimit(){
            return maxLimit;
        }

        public int getMinDistanceFromCenter(){
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
    }
}