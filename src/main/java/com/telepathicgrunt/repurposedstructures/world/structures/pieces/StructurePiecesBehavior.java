package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.misc.WaystonesBlaysCompat;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.Version;
import net.fabricmc.loader.api.VersionParsingException;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.fabricmc.loader.impl.util.version.SemanticVersionImpl;
import net.minecraft.resources.ResourceLocation;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class StructurePiecesBehavior {
    private StructurePiecesBehavior() {}

    public static void init() {}

    public record RequiredPieceNeeds(int maxLimit, int minDistanceFromCenter) {

        public int getRequiredAmount() {
            return maxLimit;
        }

        public int getMinDistanceFromCenter() {
            return minDistanceFromCenter;
        }
    }

    public static void addRequiredVillagePiece(String structure, ResourceLocation pieceRL, int configSize) {
        ResourceLocation structureRL = new ResourceLocation(RepurposedStructures.MODID, structure);
        Map<ResourceLocation, RequiredPieceNeeds> requiredPieceMap = REQUIRED_PIECES_COUNT.get(structureRL);

        // Will not be immutable so we can add more than one entry.
        // -1 from config because the max size is where terminators are and terminators cannot spawn houses.
        if(requiredPieceMap != null) {
            requiredPieceMap.put(pieceRL, new RequiredPieceNeeds(1, Math.min(4, configSize - 1)));
        }
        else {
            REQUIRED_PIECES_COUNT.put(structureRL, Stream.of(new AbstractMap.SimpleImmutableEntry<>(pieceRL, new RequiredPieceNeeds(1, Math.min(4, configSize - 1)))).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        }
    }

    public static HashMap<ResourceLocation, Map<ResourceLocation, RequiredPieceNeeds>> REQUIRED_PIECES_COUNT = new HashMap<>();
    static {
        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "stronghold_nether"), Map.of(
                new ResourceLocation(RepurposedStructures.MODID, "strongholds/nether/portal_room"), new StructurePiecesBehavior.RequiredPieceNeeds(1, (int) (RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdSize * 0.6D))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "stronghold_end"), Map.of(
                new ResourceLocation(RepurposedStructures.MODID, "strongholds/end/portal_room"), new StructurePiecesBehavior.RequiredPieceNeeds(1, (int) (RepurposedStructures.RSAllConfig.RSStrongholdsConfig.end.endStrongholdSize * 0.6D)),
                new ResourceLocation(RepurposedStructures.MODID, "strongholds/end/empty_crossing"), new StructurePiecesBehavior.RequiredPieceNeeds(2, (int) (RepurposedStructures.RSAllConfig.RSStrongholdsConfig.end.endStrongholdSize * 0.35D))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "fortress_jungle"), Map.of(
                new ResourceLocation(RepurposedStructures.MODID, "fortresses/jungle/spawner"), new StructurePiecesBehavior.RequiredPieceNeeds(2, (int) (RepurposedStructures.RSAllConfig.RSFortressesConfig.jungleFortress.jungleFortressSize * 0.6D)),
                new ResourceLocation(RepurposedStructures.MODID, "fortresses/jungle/balcony"), new StructurePiecesBehavior.RequiredPieceNeeds(1, (int) (RepurposedStructures.RSAllConfig.RSFortressesConfig.jungleFortress.jungleFortressSize * 0.2D)),
                new ResourceLocation(RepurposedStructures.MODID, "fortresses/jungle/turn_inside_chest"), new StructurePiecesBehavior.RequiredPieceNeeds(4, (int) (RepurposedStructures.RSAllConfig.RSFortressesConfig.jungleFortress.jungleFortressSize * 0.6D))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_birch"), Map.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/birch/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.size.birchMineshaftSize))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_dark_forest"), Map.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/dark_forest/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.size.darkForestMineshaftSize))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_desert"), Map.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/desert/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.size.desertMineshaftSize))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_nether"), Map.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/nether/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.size.netherMineshaftSize))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_crimson"), Map.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/crimson/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.size.crimsonMineshaftSize))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_warped"), Map.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/warped/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.size.warpedMineshaftSize))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_icy"), Map.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/icy/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.size.icyMineshaftSize))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_jungle"), Map.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/jungle/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.size.jungleMineshaftSize))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_ocean"), Map.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/ocean/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.size.oceanMineshaftSize))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_savanna"), Map.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/savanna/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.size.savannaMineshaftSize))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_stone"), Map.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/stone/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.size.stoneMineshaftSize))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_swamp"), Map.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/swamp/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.size.swampMineshaftSize))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_taiga"), Map.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/taiga/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.size.taigaMineshaftSize))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_end"), Map.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/end/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RepurposedStructures.RSAllConfig.RSMineshaftsConfig.size.endMineshaftSize))));
    }

    // Delayed to onInitializeServer/onInitializeClient so it is ran after the mod's config is setup
    public static void addDelayedRequiredPieces() {
        // Blays Waystones
        if(FabricLoader.getInstance().isModLoaded("waystones")) {
            ModMetadata modMetadata = FabricLoader.getInstance().getModContainer("waystones").get().getMetadata();
            if (modMetadata.getAuthors().stream().anyMatch(author -> author.getName().equals("BlayTheNinth")) && WaystonesBlaysCompat.waystonesVillageAndForcedSpawning()) {
                // Waystones 8.1.1+0 and older does not add waystone to any rs village. Newer version will.
                try {
                    Version modVersion = FabricLoader.getInstance().getModContainer("waystones").get().getMetadata().getVersion();
                    SemanticVersionImpl thresholdVersion = new SemanticVersionImpl("8.1.1+0", false);
                    if (modVersion.compareTo(thresholdVersion) > 0) {
                        addRequiredVillagePiece("village_badlands", new ResourceLocation("waystones", "village/desert/waystone"), RepurposedStructures.RSAllConfig.RSVillagesConfig.size.badlandsVillageSize);
                        addRequiredVillagePiece("village_birch", new ResourceLocation("waystones", "village/common/waystone"), RepurposedStructures.RSAllConfig.RSVillagesConfig.size.birchVillageSize);
                        addRequiredVillagePiece("village_crimson", new ResourceLocation("waystones", "village/common/waystone"), RepurposedStructures.RSAllConfig.RSVillagesConfig.size.crimsonVillageSize);
                        addRequiredVillagePiece("village_dark_forest", new ResourceLocation("waystones", "village/common/waystone"), RepurposedStructures.RSAllConfig.RSVillagesConfig.size.darkForestVillageSize);
                        addRequiredVillagePiece("village_giant_taiga", new ResourceLocation("waystones", "village/common/waystone"), RepurposedStructures.RSAllConfig.RSVillagesConfig.size.giantTaigaVillageSize);
                        addRequiredVillagePiece("village_jungle", new ResourceLocation("waystones", "village/common/waystone"), RepurposedStructures.RSAllConfig.RSVillagesConfig.size.jungleVillageSize);
                        addRequiredVillagePiece("village_mountains", new ResourceLocation("waystones", "village/common/waystone"), RepurposedStructures.RSAllConfig.RSVillagesConfig.size.mountainsVillageSize);
                        addRequiredVillagePiece("village_mushroom", new ResourceLocation("waystones", "village/common/waystone"), RepurposedStructures.RSAllConfig.RSVillagesConfig.size.mushroomVillageSize);
                        addRequiredVillagePiece("village_oak", new ResourceLocation("waystones", "village/common/waystone"), RepurposedStructures.RSAllConfig.RSVillagesConfig.size.oakVillageSize);
                        addRequiredVillagePiece("village_swamp", new ResourceLocation("waystones", "village/common/waystone"), RepurposedStructures.RSAllConfig.RSVillagesConfig.size.swampVillageSize);
                        addRequiredVillagePiece("village_warped", new ResourceLocation("waystones", "village/common/waystone"), RepurposedStructures.RSAllConfig.RSVillagesConfig.size.warpedVillageSize);
                    }
                }
                catch (VersionParsingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static HashMap<ResourceLocation, Integer> PIECES_COUNT = new HashMap<>();
    static {
        double netherStrongholdLimitScale = 0.066D;
        int netherStrongholdSize = RepurposedStructures.RSAllConfig.RSStrongholdsConfig.nether.netherStrongholdSize;
        double netherStrongholdPieceLimit = netherStrongholdSize * netherStrongholdLimitScale;
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "strongholds/nether/library_big"), (int) (4 * netherStrongholdPieceLimit));
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "strongholds/nether/library_small"), (int) (2 * netherStrongholdPieceLimit));
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "strongholds/nether/prison"), (int) (8 * netherStrongholdPieceLimit));
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "strongholds/nether/crossing"), (int) (7 * netherStrongholdPieceLimit));
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "strongholds/nether/empty_crossing"), (int) (2 * netherStrongholdPieceLimit));
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "strongholds/nether/pillar_crossing"), (int) (3 * netherStrongholdPieceLimit));
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "strongholds/nether/fountain_crossing"), (int) (3 * netherStrongholdPieceLimit));
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "strongholds/nether/storage_crossing"), (int) (4 * netherStrongholdPieceLimit));
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "strongholds/nether/stairs_straight"), (int) (7 * netherStrongholdPieceLimit));
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "strongholds/nether/stairs"), (int) (7 * netherStrongholdPieceLimit));
        PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "strongholds/nether/chest_corridor"), (int) (16 * netherStrongholdPieceLimit));

        double endStrongholdLimitScale = 0.072D;
        int endStrongholdSize = RepurposedStructures.RSAllConfig.RSStrongholdsConfig.end.endStrongholdSize;
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

        // Add other mod pieces here to limit them too.
        // (for datapacks and to prevent villages from being overwhelmed in the same injected piece)
        PIECES_COUNT.put(new ResourceLocation("farmersdelight", "villages/badlands/houses/compost_pile_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("farmersdelight", "villages/birch/houses/compost_pile_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("farmersdelight", "villages/dark_forest/houses/compost_pile_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("farmersdelight", "villages/giant_taiga/houses/compost_pile_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("farmersdelight", "villages/jungle/houses/compost_pile_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("farmersdelight", "villages/mountains/houses/compost_pile_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("farmersdelight", "villages/mushroom/houses/compost_pile_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("farmersdelight", "villages/oak/houses/compost_pile_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("farmersdelight", "villages/swamp/houses/compost_pile_1"), 1);

        PIECES_COUNT.put(new ResourceLocation("charm", "villages/badlands/houses/badlands_beekeeper_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("charm", "villages/badlands/houses/badlands_lumberjack_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("charm", "villages/badlands/houses/badlands_lumberjack_2"), 1);
        PIECES_COUNT.put(new ResourceLocation("charm", "villages/birch/houses/birch_beejack"), 1);
        PIECES_COUNT.put(new ResourceLocation("charm", "villages/birch/houses/birch_beekeeper_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("charm", "villages/birch/houses/birch_lumberjack_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("charm", "villages/dark_forest/houses/dark_forest_beejack"), 1);
        PIECES_COUNT.put(new ResourceLocation("charm", "villages/dark_forest/houses/dark_forest_beekeeper_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("charm", "villages/dark_forest/houses/dark_forest_lumberjack_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("charm", "villages/giant_taiga/houses/giant_taiga_lumberbee_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("charm", "villages/giant_taiga/houses/giant_taiga_lumberjack_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("charm", "villages/jungle/houses/jungle_beekeeper_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("charm", "villages/jungle/houses/jungle_beekeeper_2"), 1);
        PIECES_COUNT.put(new ResourceLocation("charm", "villages/jungle/houses/jungle_lumberjack_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("charm", "villages/mountains/houses/mountains_beekeeper_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("charm", "villages/mountains/houses/mountains_lumberjack_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("charm", "villages/mushroom/houses/mushroom_beekeeper_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("charm", "villages/mushroom/houses/mushroom_lumberjack_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("charm", "villages/oak/houses/oak_beejack"), 1);
        PIECES_COUNT.put(new ResourceLocation("charm", "villages/oak/houses/oak_beekeeper_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("charm", "villages/oak/houses/oak_lumberjack_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("charm", "villages/swamp/houses/swamp_beejack"), 1);
        PIECES_COUNT.put(new ResourceLocation("charm", "villages/swamp/houses/swamp_beekeeper_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("charm", "villages/swamp/houses/swamp_lumberjack_1"), 1);

        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/badlands/badlands_engineer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/badlands/badlands_florist"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/badlands/badlands_hunter"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/badlands/badlands_hunter_engineer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/badlands/badlands_oceanographer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/badlands/badlands_woodworker"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/birch/birch_engineer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/birch/birch_florist"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/birch/birch_hunter"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/birch/birch_oceanographer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/birch/birch_woodworker"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/dark_forest/dark_forest_engineer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/dark_forest/dark_forest_florist"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/dark_forest/dark_forest_hunter"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/dark_forest/dark_forest_oceanographer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/dark_forest/dark_forest_woodworker"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/giant_taiga/giant_taiga_engineer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/giant_taiga/giant_taiga_florist"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/giant_taiga/giant_taiga_hunter"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/giant_taiga/giant_taiga_oceanographer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/giant_taiga/giant_taiga_woodworker"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/jungle/jungle_engineer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/jungle/jungle_florist"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/jungle/jungle_hunter"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/jungle/jungle_oceanographer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/jungle/jungle_woodworker"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/mountains/mountains_engineer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/mountains/mountains_florist"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/mountains/mountains_hunter"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/mountains/mountains_oceanographer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/mountains/mountains_woodworker"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/mushroom/mushroom_engineer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/mushroom/mushroom_florist"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/mushroom/mushroom_hunter"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/mushroom/mushroom_oceanographer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/mushroom/mushroom_woodworker"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/oak/oak_engineer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/oak/oak_florist"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/oak/oak_hunter"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/oak/oak_oceanographer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/oak/oak_woodworker"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/swamp/swamp_engineer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/swamp/swamp_florist"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/swamp/swamp_hunter"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/swamp/swamp_oceanographer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "villages/swamp/swamp_woodworker"), 1);

        PIECES_COUNT.put(new ResourceLocation("waystones", "village/common/waystone"), 1);
        PIECES_COUNT.put(new ResourceLocation("waystones", "village/desert/waystone"), 1);

        PIECES_COUNT.put(new ResourceLocation("bountiful", "village/common/bounty_gazebo"), 1);
        PIECES_COUNT.put(new ResourceLocation("bountiful", "village/common/bounty_gazebo_old"), 1);
    }
}
