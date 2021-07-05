package com.telepathicgrunt.repurposedstructures.modinit;

import net.minecraft.world.gen.feature.StructureFeature;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RSStructureTagMap {
    public enum STRUCTURE_TAGS {
        // RS Structures
        MINESHAFT,
        PYRAMID,
        TEMPLE,
        VILLAGE,
        STRONGHOLD,
        SHIPWRECK,
        FORTRESS,
        IGLOO,
        OUTPOST,
        RUINED_PORTAL,
        RUINS,
        MANSION,
        WITCH_HUTS,
        BASTION,

        // Structure sets to avoid for some structures
        OVERWORLD_OUTPOST,
        NETHER_OUTPOST,
        NETHER_TEMPLE,
        NETHER_SHIPWRECK,
        GENERIC_AVOID_NETHER_STRUCTURE,
        SHIPWRECK_AVOID_NETHER_STRUCTURE,
        END_AVOID_STRUCTURE,
        BASTION_AVOID_STRUCTURE,
        OUTPOST_AVOID_STRUCTURE,

        // Other
        NO_LAKES,
        NO_LAVAFALLS,
        LESS_JUNGLE_BUSH
    }

    public static final Map<StructureFeature<?>, Set<STRUCTURE_TAGS>> TAGGED_STRUCTURES = new HashMap<>();
    public static final Map<STRUCTURE_TAGS, Set<StructureFeature<?>>> REVERSED_TAGGED_STRUCTURES = new HashMap<>();

    /**
     * Call this after structure registration. This will setup the tags for all structures so we can reference them easier.
     */
    public static void setupTags(){
        addTags(RSStructures.MINESHAFT_BIRCH, Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.MINESHAFT_SAVANNA, Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.MINESHAFT_OCEAN, Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.MINESHAFT_JUNGLE, Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.MINESHAFT_ICY, Stream.of(STRUCTURE_TAGS.MINESHAFT, STRUCTURE_TAGS.NO_LAVAFALLS).collect(Collectors.toSet()));
        addTags(RSStructures.MINESHAFT_DESERT, Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.MINESHAFT_STONE, Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.MINESHAFT_DARK_FOREST, Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.MINESHAFT_SWAMP, Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.MINESHAFT_TAIGA, Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.MINESHAFT_NETHER, Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.MINESHAFT_CRIMSON, Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.MINESHAFT_WARPED, Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.MINESHAFT_END, Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));

        addTags(RSStructures.IGLOO_GRASSY, Stream.of(STRUCTURE_TAGS.IGLOO).collect(Collectors.toSet()));
        addTags(RSStructures.IGLOO_STONE, Stream.of(STRUCTURE_TAGS.IGLOO).collect(Collectors.toSet()));

        addTags(RSStructures.STRONGHOLD_NETHER, Stream.of(STRUCTURE_TAGS.STRONGHOLD).collect(Collectors.toSet()));
        
        addTags(RSStructures.PYRAMID_BADLANDS, Stream.of(STRUCTURE_TAGS.PYRAMID).collect(Collectors.toSet()));
        addTags(RSStructures.PYRAMID_NETHER, Stream.of(STRUCTURE_TAGS.PYRAMID).collect(Collectors.toSet()));
        addTags(RSStructures.PYRAMID_SNOWY, Stream.of(STRUCTURE_TAGS.PYRAMID).collect(Collectors.toSet()));
        addTags(RSStructures.PYRAMID_END, Stream.of(STRUCTURE_TAGS.PYRAMID).collect(Collectors.toSet()));
        addTags(RSStructures.PYRAMID_ICY, Stream.of(STRUCTURE_TAGS.PYRAMID).collect(Collectors.toSet()));
        addTags(RSStructures.PYRAMID_JUNGLE, Stream.of(STRUCTURE_TAGS.PYRAMID).collect(Collectors.toSet()));
        addTags(RSStructures.PYRAMID_MUSHROOM, Stream.of(STRUCTURE_TAGS.PYRAMID).collect(Collectors.toSet()));
        addTags(RSStructures.PYRAMID_OCEAN, Stream.of(STRUCTURE_TAGS.PYRAMID).collect(Collectors.toSet()));
        addTags(RSStructures.PYRAMID_GIANT_TREE_TAIGA, Stream.of(STRUCTURE_TAGS.PYRAMID).collect(Collectors.toSet()));
        addTags(RSStructures.PYRAMID_FLOWER_FOREST, Stream.of(STRUCTURE_TAGS.PYRAMID).collect(Collectors.toSet()));

        addTags(RSStructures.TEMPLE_NETHER_BASALT, Stream.of(STRUCTURE_TAGS.TEMPLE, STRUCTURE_TAGS.NETHER_TEMPLE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.TEMPLE_NETHER_CRIMSON, Stream.of(STRUCTURE_TAGS.TEMPLE, STRUCTURE_TAGS.NETHER_TEMPLE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.TEMPLE_NETHER_SOUL, Stream.of(STRUCTURE_TAGS.TEMPLE, STRUCTURE_TAGS.NETHER_TEMPLE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.TEMPLE_NETHER_WARPED, Stream.of(STRUCTURE_TAGS.TEMPLE, STRUCTURE_TAGS.NETHER_TEMPLE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.TEMPLE_NETHER_WASTELAND, Stream.of(STRUCTURE_TAGS.TEMPLE, STRUCTURE_TAGS.NETHER_TEMPLE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));

        addTags(RSStructures.SHIPWRECK_CRIMSON, Stream.of(STRUCTURE_TAGS.SHIPWRECK, STRUCTURE_TAGS.NETHER_SHIPWRECK, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.SHIPWRECK_WARPED, Stream.of(STRUCTURE_TAGS.SHIPWRECK, STRUCTURE_TAGS.NETHER_SHIPWRECK, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.SHIPWRECK_NETHER_BRICKS, Stream.of(STRUCTURE_TAGS.SHIPWRECK, STRUCTURE_TAGS.NETHER_SHIPWRECK, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.SHIPWRECK_END, Stream.of(STRUCTURE_TAGS.SHIPWRECK).collect(Collectors.toSet()));

        addTags(RSStructures.OUTPOST_CRIMSON, Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.NETHER_OUTPOST, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_NETHER_BRICK, Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.NETHER_OUTPOST, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_WARPED, Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.NETHER_OUTPOST, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_BIRCH, Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_JUNGLE, Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_GIANT_TREE_TAIGA, Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_DESERT, Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_BADLANDS, Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_SNOWY, Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_ICY, Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_TAIGA, Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_OAK, Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_END, Stream.of(STRUCTURE_TAGS.OUTPOST).collect(Collectors.toSet()));

        addTags(RSStructures.VILLAGE_BADLANDS, Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.VILLAGE_BIRCH, Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.VILLAGE_DARK_FOREST, Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.VILLAGE_GIANT_TAIGA, Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.VILLAGE_JUNGLE, Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE, STRUCTURE_TAGS.NO_LAKES, STRUCTURE_TAGS.LESS_JUNGLE_BUSH).collect(Collectors.toSet()));
        addTags(RSStructures.VILLAGE_MOUNTAINS, Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.VILLAGE_SWAMP, Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.VILLAGE_CRIMSON, Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.VILLAGE_WARPED, Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.VILLAGE_OAK, Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));

        addTags(RSStructures.FORTRESS_JUNGLE, Stream.of(STRUCTURE_TAGS.FORTRESS).collect(Collectors.toSet()));
        addTags(RSStructures.RUINED_PORTAL_END, Stream.of(STRUCTURE_TAGS.RUINED_PORTAL).collect(Collectors.toSet()));
        addTags(RSStructures.RUINS_NETHER, Stream.of(STRUCTURE_TAGS.RUINS).collect(Collectors.toSet()));
        addTags(RSStructures.RUINS_LAND_WARM, Stream.of(STRUCTURE_TAGS.RUINS, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.RUINS_LAND_HOT, Stream.of(STRUCTURE_TAGS.RUINS, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));

        addTags(RSStructures.CITY_NETHER, Stream.of(STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));

        addTags(RSStructures.MANSION_BIRCH, Stream.of(STRUCTURE_TAGS.MANSION, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.MANSION_JUNGLE, Stream.of(STRUCTURE_TAGS.MANSION, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.MANSION_OAK, Stream.of(STRUCTURE_TAGS.MANSION, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.MANSION_SAVANNA, Stream.of(STRUCTURE_TAGS.MANSION, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.MANSION_TAIGA, Stream.of(STRUCTURE_TAGS.MANSION, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.MANSION_DESERT, Stream.of(STRUCTURE_TAGS.MANSION, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.MANSION_SNOWY, Stream.of(STRUCTURE_TAGS.MANSION, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));

        addTags(RSStructures.WITCH_HUTS_OAK, Stream.of(STRUCTURE_TAGS.WITCH_HUTS, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.WITCH_HUTS_TAIGA, Stream.of(STRUCTURE_TAGS.WITCH_HUTS, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.WITCH_HUTS_BIRCH, Stream.of(STRUCTURE_TAGS.WITCH_HUTS, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.WITCH_HUTS_DARK_FOREST, Stream.of(STRUCTURE_TAGS.WITCH_HUTS, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.WITCH_HUTS_GIANT_TREE_TAIGA, Stream.of(STRUCTURE_TAGS.WITCH_HUTS, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));

        addTags(RSStructures.BASTION_UNDERGROUND, Stream.of(STRUCTURE_TAGS.BASTION, STRUCTURE_TAGS.NO_LAVAFALLS, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));

        addTags(StructureFeature.FORTRESS, Stream.of(STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(StructureFeature.BASTION_REMNANT, Stream.of(STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(StructureFeature.VILLAGE, Stream.of(STRUCTURE_TAGS.BASTION_AVOID_STRUCTURE, STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE).collect(Collectors.toSet()));
        addTags(StructureFeature.STRONGHOLD, Stream.of(STRUCTURE_TAGS.BASTION_AVOID_STRUCTURE).collect(Collectors.toSet()));
        addTags(StructureFeature.END_CITY, Stream.of(STRUCTURE_TAGS.END_AVOID_STRUCTURE).collect(Collectors.toSet()));
        // regexpos1
    }


    /**
     * Helper method to setup the tags to structure and structure to tag maps.
     *
     * Only does additions and no replacements/deletions.
     */
    private static void addTags(StructureFeature<?> structure, Set<STRUCTURE_TAGS> tags){
        if(!TAGGED_STRUCTURES.containsKey(structure)){
            TAGGED_STRUCTURES.put(structure, new HashSet<>());
        }
        TAGGED_STRUCTURES.get(structure).addAll(tags);

        for(STRUCTURE_TAGS tag : tags){
            if(!REVERSED_TAGGED_STRUCTURES.containsKey(tag)){
                REVERSED_TAGGED_STRUCTURES.put(tag, new HashSet<>());
            }

            REVERSED_TAGGED_STRUCTURES.get(tag).add(structure);
        }
    }
}
