package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.google.common.collect.ImmutableMap;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.configs.RSFortressesConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSMineshaftsConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSStrongholdsConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSVillagesConfig;
import com.telepathicgrunt.repurposedstructures.misc.WaystonesBlaysCompat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.ModList;
import org.apache.maven.artifact.versioning.ArtifactVersion;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class StructurePiecesBehavior {
    private StructurePiecesBehavior() {}

    // No need for Lazy in this class because it is init in FMLCommonSetupEvent where config values now exists
    public static void init() {}

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

    private static void addRequiredVillagePiece(String structure, ResourceLocation pieceRL, int configSize) {
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
        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "stronghold_nether"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "strongholds/nether/portal_room"), new StructurePiecesBehavior.RequiredPieceNeeds(1, (int) (RSStrongholdsConfig.strongholdNetherSize.get() * 0.6D))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "stronghold_end"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "strongholds/end/portal_room"), new StructurePiecesBehavior.RequiredPieceNeeds(1, (int) (RSStrongholdsConfig.strongholdEndSize.get() * 0.6D)),
                new ResourceLocation(RepurposedStructures.MODID, "strongholds/end/empty_crossing"), new StructurePiecesBehavior.RequiredPieceNeeds(2, (int) (RSStrongholdsConfig.strongholdEndSize.get() * 0.35D))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "fortress_jungle"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "fortresses/jungle/spawner"), new StructurePiecesBehavior.RequiredPieceNeeds(2, (int) (RSFortressesConfig.jungleFortressSize.get() * 0.6D)),
                new ResourceLocation(RepurposedStructures.MODID, "fortresses/jungle/balcony"), new StructurePiecesBehavior.RequiredPieceNeeds(1, (int) (RSFortressesConfig.jungleFortressSize.get() * 0.2D)),
                new ResourceLocation(RepurposedStructures.MODID, "fortresses/jungle/turn_inside_chest"), new StructurePiecesBehavior.RequiredPieceNeeds(4, (int) (RSFortressesConfig.jungleFortressSize.get() * 0.6D))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_birch"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/birch/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RSMineshaftsConfig.birchMineshaftSize.get()))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_dark_forest"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/dark_forest/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RSMineshaftsConfig.darkForestMineshaftSize.get()))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_desert"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/desert/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RSMineshaftsConfig.desertMineshaftSize.get()))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_nether"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/nether/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RSMineshaftsConfig.netherMineshaftSize.get()))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_crimson"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/crimson/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RSMineshaftsConfig.crimsonMineshaftSize.get()))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_warped"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/warped/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RSMineshaftsConfig.warpedMineshaftSize.get()))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_icy"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/icy/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RSMineshaftsConfig.icyMineshaftSize.get()))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_jungle"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/jungle/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RSMineshaftsConfig.jungleMineshaftSize.get()))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_ocean"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/ocean/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RSMineshaftsConfig.oceanMineshaftSize.get()))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_savanna"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/savanna/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RSMineshaftsConfig.savannaMineshaftSize.get()))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_stone"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/stone/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RSMineshaftsConfig.stoneMineshaftSize.get()))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_swamp"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/swamp/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RSMineshaftsConfig.swampMineshaftSize.get()))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_taiga"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/taiga/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RSMineshaftsConfig.taigaMineshaftSize.get()))));

        REQUIRED_PIECES_COUNT.put(new ResourceLocation(RepurposedStructures.MODID, "mineshaft_end"), ImmutableMap.of(
                new ResourceLocation(RepurposedStructures.MODID, "mineshafts/end/spawner_4_end"), new StructurePiecesBehavior.RequiredPieceNeeds(1, Math.min(5, RSMineshaftsConfig.endMineshaftSize.get()))));

        if(ModList.get().isLoaded("waystones") && WaystonesBlaysCompat.waystonesVillageAndForcedSpawning()) {
            addRequiredVillagePiece("village_badlands", new ResourceLocation("waystones", "village/desert/waystone"), RSVillagesConfig.villageBadlandsSize.get());
            addRequiredVillagePiece("village_birch", new ResourceLocation("waystones", "village/common/waystone"), RSVillagesConfig.villageBirchSize.get());
            addRequiredVillagePiece("village_crimson", new ResourceLocation("waystones", "village/common/waystone"), RSVillagesConfig.villageCrimsonSize.get());
            addRequiredVillagePiece("village_dark_oak", new ResourceLocation("waystones", "village/common/waystone"), RSVillagesConfig.villageDarkForestSize.get());
            addRequiredVillagePiece("village_jungle", new ResourceLocation("waystones", "village/common/waystone"), RSVillagesConfig.villageJungleSize.get());
            addRequiredVillagePiece("village_mountains", new ResourceLocation("waystones", "village/common/waystone"), RSVillagesConfig.villageMountainsSize.get());
            addRequiredVillagePiece("village_mushroom", new ResourceLocation("waystones", "village/common/waystone"), RSVillagesConfig.villageMushroomSize.get());
            addRequiredVillagePiece("village_oak", new ResourceLocation("waystones", "village/common/waystone"), RSVillagesConfig.villageOakSize.get());
            addRequiredVillagePiece("village_swamp", new ResourceLocation("waystones", "village/common/waystone"), RSVillagesConfig.villageSwampSize.get());
            addRequiredVillagePiece("village_warped", new ResourceLocation("waystones", "village/common/waystone"), RSVillagesConfig.villageWarpedSize.get());

            // Waystones 7.6.4 and older does not add waystone to giant taiga village. Newer version will.
            ArtifactVersion modVersion = ModList.get().getModContainerById("waystones").get().getModInfo().getVersion();
            if(modVersion.compareTo(new DefaultArtifactVersion("7.6.4")) > 0) {
                addRequiredVillagePiece("village_giant_taiga", new ResourceLocation("waystones", "village/common/waystone"), RSVillagesConfig.villageGiantTaigaSize.get());
            }
        }
    }
    
    public static HashMap<ResourceLocation, Integer> PIECES_COUNT = new HashMap<>();
    static {
        double netherStrongholdLimitScale = 0.066D;
        int netherStrongholdSize = RSStrongholdsConfig.strongholdNetherSize.get();
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
        int endStrongholdSize = RSStrongholdsConfig.strongholdEndSize.get();
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
        PIECES_COUNT.put(new ResourceLocation("jellyfishing", "village/streets/krusty_krab_street_badlands"), 1);
        PIECES_COUNT.put(new ResourceLocation("jellyfishing", "village/streets/krusty_krab_street_birch"), 1);
        PIECES_COUNT.put(new ResourceLocation("jellyfishing", "village/streets/krusty_krab_street_dark_forest"), 1);
        PIECES_COUNT.put(new ResourceLocation("jellyfishing", "village/streets/krusty_krab_street_giant_taiga"), 1);
        PIECES_COUNT.put(new ResourceLocation("jellyfishing", "village/streets/krusty_krab_street_jungle"), 1);
        PIECES_COUNT.put(new ResourceLocation("jellyfishing", "village/streets/krusty_krab_street_mountains"), 1);
        PIECES_COUNT.put(new ResourceLocation("jellyfishing", "village/streets/krusty_krab_street_mushroom"), 1);
        PIECES_COUNT.put(new ResourceLocation("jellyfishing", "village/streets/krusty_krab_street_oak"), 1);
        PIECES_COUNT.put(new ResourceLocation("jellyfishing", "village/streets/krusty_krab_street_swamp"), 1);

        PIECES_COUNT.put(new ResourceLocation("tardis", "village/streets/observatory_street_badlands"), 1);
        PIECES_COUNT.put(new ResourceLocation("tardis", "village/streets/observatory_street_birch"), 1);
        PIECES_COUNT.put(new ResourceLocation("tardis", "village/streets/observatory_street_dark_forest"), 1);
        PIECES_COUNT.put(new ResourceLocation("tardis", "village/streets/observatory_street_giant_taiga"), 1);
        PIECES_COUNT.put(new ResourceLocation("tardis", "village/streets/observatory_street_jungle"), 1);
        PIECES_COUNT.put(new ResourceLocation("tardis", "village/streets/observatory_street_mountains"), 1);
        PIECES_COUNT.put(new ResourceLocation("tardis", "village/streets/observatory_street_mushroom"), 1);
        PIECES_COUNT.put(new ResourceLocation("tardis", "village/streets/observatory_street_oak"), 1);
        PIECES_COUNT.put(new ResourceLocation("tardis", "village/streets/observatory_street_swamp"), 1);

        PIECES_COUNT.put(new ResourceLocation("immersiveengineering", "village/houses/badlands_engineer"), 1);
        PIECES_COUNT.put(new ResourceLocation("immersiveengineering", "village/houses/birch_engineer"), 1);
        PIECES_COUNT.put(new ResourceLocation("immersiveengineering", "village/houses/dark_forest_engineer"), 1);
        PIECES_COUNT.put(new ResourceLocation("immersiveengineering", "village/houses/giant_taiga_engineer"), 1);
        PIECES_COUNT.put(new ResourceLocation("immersiveengineering", "village/houses/jungle_engineer"), 1);
        PIECES_COUNT.put(new ResourceLocation("immersiveengineering", "village/houses/mountains_engineer"), 1);
        PIECES_COUNT.put(new ResourceLocation("immersiveengineering", "village/houses/mushroom_engineer"), 1);
        PIECES_COUNT.put(new ResourceLocation("immersiveengineering", "village/houses/oak_engineer"), 1);
        PIECES_COUNT.put(new ResourceLocation("immersiveengineering", "village/houses/swamp_engineer"), 1);

        PIECES_COUNT.put(new ResourceLocation("iceandfire", "village/workstations/scriber"), 1);
        PIECES_COUNT.put(new ResourceLocation("iceandfire", "village/badlands/houses/scriber_street_badlands"), 1);
        PIECES_COUNT.put(new ResourceLocation("iceandfire", "village/birch/houses/birch_scriber_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("iceandfire", "village/dark_forest/houses/dark_forest_scriber_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("iceandfire", "village/giant_taiga/houses/giant_taiga_scriber_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("iceandfire", "village/jungle/houses/jungle_scriber_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("iceandfire", "village/mountains/houses/mountains_scriber_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("iceandfire", "village/mushroom/houses/mushroom_scriber_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("iceandfire", "village/oak/houses/oak_scriber_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("iceandfire", "village/swamp/houses/swamp_scriber_1"), 1);

        PIECES_COUNT.put(new ResourceLocation("farmersdelight", "village/badlands/houses/compost_pile_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("farmersdelight", "village/birch/houses/compost_pile_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("farmersdelight", "village/dark_forest/houses/compost_pile_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("farmersdelight", "village/giant_taiga/houses/compost_pile_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("farmersdelight", "village/jungle/houses/compost_pile_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("farmersdelight", "village/mountains/houses/compost_pile_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("farmersdelight", "village/mushroom/houses/compost_pile_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("farmersdelight", "village/oak/houses/compost_pile_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("farmersdelight", "village/swamp/houses/compost_pile_1"), 1);

        PIECES_COUNT.put(new ResourceLocation("etched", "village/badlands/badlands_bard_house_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("etched", "village/birch/birch_bard_house_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("etched", "village/dark_forest/dark_forest_bard_house_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("etched", "village/giant_taiga/giant_taiga_bard_house_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("etched", "village/mountains/mountains_bard_house_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("etched", "village/oak/oak_bard_house_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("etched", "village/swamp/swamp_bard_house_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("etched", "village/jungle/jungle_bard_street"), 1);
        PIECES_COUNT.put(new ResourceLocation("etched", "village/mushroom/mushroom_bard_street"), 1);

        PIECES_COUNT.put(new ResourceLocation("environmental", "village/carpenter_house_badlands_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("environmental", "village/streets/street_birch"), 1);
        PIECES_COUNT.put(new ResourceLocation("environmental", "village/streets/street_dark_forest"), 1);
        PIECES_COUNT.put(new ResourceLocation("environmental", "village/carpenter_house_giant_taiga_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("environmental", "village/carpenter_house_jungle_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("environmental", "village/carpenter_house_mountains_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("environmental", "village/streets/street_mushroom"), 1);
        PIECES_COUNT.put(new ResourceLocation("environmental", "village/streets/street_oak"), 1);
        PIECES_COUNT.put(new ResourceLocation("environmental", "village/carpenter_house_swamp_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("environmental", "village/ceramist_house_badlands_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("environmental", "village/ceramist_house_birch_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("environmental", "village/ceramist_house_dark_forest_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("environmental", "village/ceramist_house_giant_taiga_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("environmental", "village/ceramist_house_jungle_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("environmental", "village/ceramist_house_mountains_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("environmental", "village/ceramist_house_mushroom_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("environmental", "village/ceramist_house_oak_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("environmental", "village/ceramist_house_swamp_1"), 1);

        PIECES_COUNT.put(new ResourceLocation("buzzier_bees", "village/badlands/houses/apiarist_house_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("buzzier_bees", "village/birch/houses/apiarist_house_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("buzzier_bees", "village/dark_forest/houses/apiarist_house_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("buzzier_bees", "village/giant_taiga/houses/apiarist_house_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("buzzier_bees", "village/jungle/houses/apiarist_house_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("buzzier_bees", "village/mountains/houses/apiarist_house_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("buzzier_bees", "village/mushroom/houses/apiarist_house_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("buzzier_bees", "village/oak/houses/apiarist_house_1"), 1);
        PIECES_COUNT.put(new ResourceLocation("buzzier_bees", "village/swamp/houses/apiarist_house_1"), 1);

        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/badlands/badlands_engineer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/badlands/badlands_florist"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/badlands/badlands_hunter"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/badlands/badlands_hunter_engineer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/badlands/badlands_oceanographer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/badlands/badlands_woodworker"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/birch/birch_engineer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/birch/birch_florist"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/birch/birch_hunter"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/birch/birch_oceanographer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/birch/birch_woodworker"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/dark_forest/dark_forest_engineer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/dark_forest/dark_forest_florist"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/dark_forest/dark_forest_hunter"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/dark_forest/dark_forest_oceanographer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/dark_forest/dark_forest_woodworker"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/giant_taiga/giant_taiga_engineer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/giant_taiga/giant_taiga_florist"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/giant_taiga/giant_taiga_hunter"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/giant_taiga/giant_taiga_oceanographer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/giant_taiga/giant_taiga_woodworker"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/jungle/jungle_engineer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/jungle/jungle_florist"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/jungle/jungle_hunter"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/jungle/jungle_oceanographer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/jungle/jungle_woodworker"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/mountains/mountains_engineer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/mountains/mountains_florist"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/mountains/mountains_hunter"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/mountains/mountains_oceanographer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/mountains/mountains_woodworker"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/mushroom/mushroom_engineer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/mushroom/mushroom_florist"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/mushroom/mushroom_hunter"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/mushroom/mushroom_oceanographer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/mushroom/mushroom_woodworker"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/oak/oak_engineer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/oak/oak_florist"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/oak/oak_hunter"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/oak/oak_oceanographer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/oak/oak_woodworker"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/swamp/swamp_engineer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/swamp/swamp_florist"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/swamp/swamp_hunter"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/swamp/swamp_oceanographer"), 1);
        PIECES_COUNT.put(new ResourceLocation("morevillagers", "village/swamp/swamp_woodworker"), 1);

        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/streets/big_street_badlands"), 1);
        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/streets/big_street_giant_taiga"), 1);
        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/streets/big_street_jungle"), 1);
        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/streets/big_street_mountains"), 1);
        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/contractor_birch"), 1);
        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/contractor_dark_forest"), 1);
        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/contractor_giant_taiga"), 1);
        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/contractor_jungle"), 1);
        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/contractor_mountains"), 1);
        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/contractor_mushroom"), 1);
        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/contractor_oak"), 1);
        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/contractor_swamp"), 1);
        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/dj_stage_badlands"), 1);
        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/dj_stage_birch"), 1);
        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/dj_stage_dark_forest"), 1);
        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/dj_stage_mushroom"), 1);
        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/dj_stage_oak"), 1);
        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/dj_stage_swamp"), 1);
        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/engineer_badlands"), 1);
        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/engineer_birch"), 1);
        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/engineer_dark_forest"), 1);
        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/engineer_giant_taiga"), 1);
        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/engineer_jungle"), 1);
        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/engineer_mountains"), 1);
        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/engineer_mushroom"), 1);
        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/engineer_oak"), 1);
        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/engineer_swamp"), 1);
        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/lumberjack_birch"), 1);
        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/lumberjack_dark_forest"), 1);
        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/lumberjack_mushroom"), 1);
        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/lumberjack_oak"), 1);
        PIECES_COUNT.put(new ResourceLocation("tidbits", "village/lumberjack_swamp"), 1);

        PIECES_COUNT.put(new ResourceLocation("resourcefulbees", "village/badlands/houses/beekeeper_house"), 1);
        PIECES_COUNT.put(new ResourceLocation("resourcefulbees", "village/birch/houses/beekeeper_house"), 1);
        PIECES_COUNT.put(new ResourceLocation("resourcefulbees", "village/dark_forest/houses/beekeeper_house"), 1);
        PIECES_COUNT.put(new ResourceLocation("resourcefulbees", "village/giant_taiga/houses/beekeeper_house"), 1);
        PIECES_COUNT.put(new ResourceLocation("resourcefulbees", "village/jungle/houses/beekeeper_house"), 1);
        PIECES_COUNT.put(new ResourceLocation("resourcefulbees", "village/mountains/houses/beekeeper_house"), 1);
        PIECES_COUNT.put(new ResourceLocation("resourcefulbees", "village/mushroom/houses/beekeeper_house"), 1);
        PIECES_COUNT.put(new ResourceLocation("resourcefulbees", "village/oak/houses/beekeeper_house"), 1);
        PIECES_COUNT.put(new ResourceLocation("resourcefulbees", "village/swamp/houses/beekeeper_house"), 1);

        PIECES_COUNT.put(new ResourceLocation("pneumaticcraft", "villages/mechanic_street_badlands"), 1);
        PIECES_COUNT.put(new ResourceLocation("pneumaticcraft", "villages/mechanic_street_birch"), 1);
        PIECES_COUNT.put(new ResourceLocation("pneumaticcraft", "villages/mechanic_street_dark_forest"), 1);
        PIECES_COUNT.put(new ResourceLocation("pneumaticcraft", "villages/mechanic_street_giant_taiga"), 1);
        PIECES_COUNT.put(new ResourceLocation("pneumaticcraft", "villages/mechanic_street_jungle"), 1);
        PIECES_COUNT.put(new ResourceLocation("pneumaticcraft", "villages/mechanic_street_mountains"), 1);
        PIECES_COUNT.put(new ResourceLocation("pneumaticcraft", "villages/mechanic_street_mushroom"), 1);
        PIECES_COUNT.put(new ResourceLocation("pneumaticcraft", "villages/mechanic_street_oak"), 1);
        PIECES_COUNT.put(new ResourceLocation("pneumaticcraft", "villages/mechanic_street_swamp"), 1);

        PIECES_COUNT.put(new ResourceLocation("waystones", "village/common/waystone"), 1);
        PIECES_COUNT.put(new ResourceLocation("waystones", "village/desert/waystone"), 1);

        PIECES_COUNT.put(new ResourceLocation("bountiful", "village/common/bounty_gazebo"), 1);
        PIECES_COUNT.put(new ResourceLocation("bountiful", "village/common/bounty_gazebo_old"), 1);
    }
}
