package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

public final class StructurePiecesBehavior {

//    // Delayed to onInitializeServer/onInitializeClient so it is ran after the mod's config is setup
//    public static void addDelayedRequiredPieces() {
//        // Blays Waystones
//        if(FabricLoader.getInstance().isModLoaded("waystones")) {
//            ModMetadata modMetadata = FabricLoader.getInstance().getModContainer("waystones").get().getMetadata();
//            if (modMetadata.getAuthors().stream().anyMatch(author -> author.getName().equals("BlayTheNinth")) && WaystonesBlaysCompat.waystonesVillageAndForcedSpawning()) {
//                // Waystones 8.1.1+0 and older does not add waystone to any rs village. Newer version will.
//                try {
//                    Version modVersion = FabricLoader.getInstance().getModContainer("waystones").get().getMetadata().getVersion();
//                    SemanticVersionImpl thresholdVersion = new SemanticVersionImpl("8.1.1+0", false);
//                    if (modVersion instanceof Comparable && ((Comparable<Version>) modVersion).compareTo(thresholdVersion) > 0) {
//                        addRequiredVillagePiece("village_badlands", new ResourceLocation("waystones", "village/desert/waystone"), RepurposedStructures.RSAllConfig.RSVillagesConfig.size.badlandsVillageSize);
//                        addRequiredVillagePiece("village_birch", new ResourceLocation("waystones", "village/common/waystone"), RepurposedStructures.RSAllConfig.RSVillagesConfig.size.birchVillageSize);
//                        addRequiredVillagePiece("village_crimson", new ResourceLocation("waystones", "village/common/waystone"), RepurposedStructures.RSAllConfig.RSVillagesConfig.size.crimsonVillageSize);
//                        addRequiredVillagePiece("village_dark_forest", new ResourceLocation("waystones", "village/common/waystone"), RepurposedStructures.RSAllConfig.RSVillagesConfig.size.darkForestVillageSize);
//                        addRequiredVillagePiece("village_giant_taiga", new ResourceLocation("waystones", "village/common/waystone"), RepurposedStructures.RSAllConfig.RSVillagesConfig.size.giantTaigaVillageSize);
//                        addRequiredVillagePiece("village_jungle", new ResourceLocation("waystones", "village/common/waystone"), RepurposedStructures.RSAllConfig.RSVillagesConfig.size.jungleVillageSize);
//                        addRequiredVillagePiece("village_mountains", new ResourceLocation("waystones", "village/common/waystone"), RepurposedStructures.RSAllConfig.RSVillagesConfig.size.mountainsVillageSize);
//                        addRequiredVillagePiece("village_mushroom", new ResourceLocation("waystones", "village/common/waystone"), RepurposedStructures.RSAllConfig.RSVillagesConfig.size.mushroomVillageSize);
//                        addRequiredVillagePiece("village_oak", new ResourceLocation("waystones", "village/common/waystone"), RepurposedStructures.RSAllConfig.RSVillagesConfig.size.oakVillageSize);
//                        addRequiredVillagePiece("village_swamp", new ResourceLocation("waystones", "village/common/waystone"), RepurposedStructures.RSAllConfig.RSVillagesConfig.size.swampVillageSize);
//                        addRequiredVillagePiece("village_warped", new ResourceLocation("waystones", "village/common/waystone"), RepurposedStructures.RSAllConfig.RSVillagesConfig.size.warpedVillageSize);
//                    }
//                }
//                catch (VersionParsingException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

        // Add other mod pieces here to limit them too.
        // (for datapacks and to prevent villages from being overwhelmed in the same injected piece)
//        PIECES_COUNT.put(new ResourceLocation("farmersdelight", "villages/badlands/houses/compost_pile_1"), 1);
//        PIECES_COUNT.put(new ResourceLocation("farmersdelight", "villages/birch/houses/compost_pile_1"), 1);
//        PIECES_COUNT.put(new ResourceLocation("farmersdelight", "villages/dark_forest/houses/compost_pile_1"), 1);
//        PIECES_COUNT.put(new ResourceLocation("farmersdelight", "villages/giant_taiga/houses/compost_pile_1"), 1);
//        PIECES_COUNT.put(new ResourceLocation("farmersdelight", "villages/jungle/houses/compost_pile_1"), 1);
//        PIECES_COUNT.put(new ResourceLocation("farmersdelight", "villages/mountains/houses/compost_pile_1"), 1);
//        PIECES_COUNT.put(new ResourceLocation("farmersdelight", "villages/mushroom/houses/compost_pile_1"), 1);
//        PIECES_COUNT.put(new ResourceLocation("farmersdelight", "villages/oak/houses/compost_pile_1"), 1);
//        PIECES_COUNT.put(new ResourceLocation("farmersdelight", "villages/swamp/houses/compost_pile_1"), 1);
//
//        PIECES_COUNT.put(new ResourceLocation("charm", "villages/badlands/houses/badlands_beekeeper_1"), 1);
//        PIECES_COUNT.put(new ResourceLocation("charm", "villages/badlands/houses/badlands_lumberjack_1"), 1);
//        PIECES_COUNT.put(new ResourceLocation("charm", "villages/badlands/houses/badlands_lumberjack_2"), 1);
//        PIECES_COUNT.put(new ResourceLocation("charm", "villages/birch/houses/birch_beejack"), 1);
//        PIECES_COUNT.put(new ResourceLocation("charm", "villages/birch/houses/birch_beekeeper_1"), 1);
//        PIECES_COUNT.put(new ResourceLocation("charm", "villages/birch/houses/birch_lumberjack_1"), 1);
//        PIECES_COUNT.put(new ResourceLocation("charm", "villages/dark_forest/houses/dark_forest_beejack"), 1);
//        PIECES_COUNT.put(new ResourceLocation("charm", "villages/dark_forest/houses/dark_forest_beekeeper_1"), 1);
//        PIECES_COUNT.put(new ResourceLocation("charm", "villages/dark_forest/houses/dark_forest_lumberjack_1"), 1);
//        PIECES_COUNT.put(new ResourceLocation("charm", "villages/giant_taiga/houses/giant_taiga_lumberbee_1"), 1);
//        PIECES_COUNT.put(new ResourceLocation("charm", "villages/giant_taiga/houses/giant_taiga_lumberjack_1"), 1);
//        PIECES_COUNT.put(new ResourceLocation("charm", "villages/jungle/houses/jungle_beekeeper_1"), 1);
//        PIECES_COUNT.put(new ResourceLocation("charm", "villages/jungle/houses/jungle_beekeeper_2"), 1);
//        PIECES_COUNT.put(new ResourceLocation("charm", "villages/jungle/houses/jungle_lumberjack_1"), 1);
//        PIECES_COUNT.put(new ResourceLocation("charm", "villages/mountains/houses/mountains_beekeeper_1"), 1);
//        PIECES_COUNT.put(new ResourceLocation("charm", "villages/mountains/houses/mountains_lumberjack_1"), 1);
//        PIECES_COUNT.put(new ResourceLocation("charm", "villages/mushroom/houses/mushroom_beekeeper_1"), 1);
//        PIECES_COUNT.put(new ResourceLocation("charm", "villages/mushroom/houses/mushroom_lumberjack_1"), 1);
//        PIECES_COUNT.put(new ResourceLocation("charm", "villages/oak/houses/oak_beejack"), 1);
//        PIECES_COUNT.put(new ResourceLocation("charm", "villages/oak/houses/oak_beekeeper_1"), 1);
//        PIECES_COUNT.put(new ResourceLocation("charm", "villages/oak/houses/oak_lumberjack_1"), 1);
//        PIECES_COUNT.put(new ResourceLocation("charm", "villages/swamp/houses/swamp_beejack"), 1);
//        PIECES_COUNT.put(new ResourceLocation("charm", "villages/swamp/houses/swamp_beekeeper_1"), 1);
//        PIECES_COUNT.put(new ResourceLocation("charm", "villages/swamp/houses/swamp_lumberjack_1"), 1);
//
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/badlands/badlands_engineer"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/badlands/badlands_florist"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/badlands/badlands_hunter"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/badlands/badlands_hunter_engineer"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/badlands/badlands_oceanographer"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/badlands/badlands_woodworker"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/birch/birch_engineer"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/birch/birch_florist"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/birch/birch_hunter"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/birch/birch_oceanographer"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/birch/birch_woodworker"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/dark_forest/dark_forest_engineer"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/dark_forest/dark_forest_florist"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/dark_forest/dark_forest_hunter"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/dark_forest/dark_forest_oceanographer"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/dark_forest/dark_forest_woodworker"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/giant_taiga/giant_taiga_engineer"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/giant_taiga/giant_taiga_florist"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/giant_taiga/giant_taiga_hunter"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/giant_taiga/giant_taiga_oceanographer"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/giant_taiga/giant_taiga_woodworker"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/jungle/jungle_engineer"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/jungle/jungle_florist"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/jungle/jungle_hunter"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/jungle/jungle_oceanographer"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/jungle/jungle_woodworker"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/mountains/mountains_engineer"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/mountains/mountains_florist"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/mountains/mountains_hunter"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/mountains/mountains_oceanographer"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/mountains/mountains_woodworker"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/mushroom/mushroom_engineer"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/mushroom/mushroom_florist"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/mushroom/mushroom_hunter"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/mushroom/mushroom_oceanographer"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/mushroom/mushroom_woodworker"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/oak/oak_engineer"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/oak/oak_florist"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/oak/oak_hunter"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/oak/oak_oceanographer"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/oak/oak_woodworker"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/swamp/swamp_engineer"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/swamp/swamp_florist"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/swamp/swamp_hunter"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/swamp/swamp_oceanographer"), 1);
//        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/swamp/swamp_woodworker"), 1);
//
//        PIECES_COUNT.put(new ResourceLocation("waystones", "village/common/waystone"), 1);
//        PIECES_COUNT.put(new ResourceLocation("waystones", "village/desert/waystone"), 1);
//
//        PIECES_COUNT.put(new ResourceLocation("bountiful", "village/common/bounty_gazebo"), 1);
//        PIECES_COUNT.put(new ResourceLocation("bountiful", "village/common/bounty_gazebo_old"), 1);
}
