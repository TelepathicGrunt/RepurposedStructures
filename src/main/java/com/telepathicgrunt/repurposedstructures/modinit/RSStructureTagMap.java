package com.telepathicgrunt.repurposedstructures.modinit;

import net.minecraft.world.gen.feature.structure.Structure;

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
        CITIES,
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
        VILLAGE_AVOID_STRUCTURE,
        
        // Other
        NO_LAKES,
        NO_LAVAFALLS,
        LESS_JUNGLE_BUSH
    }

    public static final Map<Structure<?>, Set<STRUCTURE_TAGS>> TAGGED_STRUCTURES = new HashMap<>();
    public static final Map<STRUCTURE_TAGS, Set<Structure<?>>> REVERSED_TAGGED_STRUCTURES = new HashMap<>();

    /**
     * Call this after structure registration. This will setup the tags for all structures so we can reference them easier.
     */
    public static void setupTags(){
        addTags(RSStructures.MINESHAFT_BIRCH.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.MINESHAFT_SAVANNA.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.MINESHAFT_OCEAN.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.MINESHAFT_JUNGLE.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.MINESHAFT_ICY.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT, STRUCTURE_TAGS.NO_LAVAFALLS).collect(Collectors.toSet()));
        addTags(RSStructures.MINESHAFT_DESERT.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.MINESHAFT_STONE.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.MINESHAFT_DARK_FOREST.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.MINESHAFT_SWAMP.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.MINESHAFT_TAIGA.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.MINESHAFT_NETHER.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.MINESHAFT_CRIMSON.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.MINESHAFT_WARPED.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.MINESHAFT_END.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));

        addTags(RSStructures.IGLOO_GRASSY.get(), Stream.of(STRUCTURE_TAGS.IGLOO).collect(Collectors.toSet()));
        addTags(RSStructures.IGLOO_STONE.get(), Stream.of(STRUCTURE_TAGS.IGLOO).collect(Collectors.toSet()));

        addTags(RSStructures.STRONGHOLD_NETHER.get(), Stream.of(STRUCTURE_TAGS.STRONGHOLD).collect(Collectors.toSet()));
        
        addTags(RSStructures.PYRAMID_BADLANDS.get(), Stream.of(STRUCTURE_TAGS.PYRAMID).collect(Collectors.toSet()));
        addTags(RSStructures.PYRAMID_NETHER.get(), Stream.of(STRUCTURE_TAGS.PYRAMID).collect(Collectors.toSet()));
        addTags(RSStructures.PYRAMID_SNOWY.get(), Stream.of(STRUCTURE_TAGS.PYRAMID).collect(Collectors.toSet()));
        addTags(RSStructures.PYRAMID_END.get(), Stream.of(STRUCTURE_TAGS.PYRAMID).collect(Collectors.toSet()));
        addTags(RSStructures.PYRAMID_ICY.get(), Stream.of(STRUCTURE_TAGS.PYRAMID).collect(Collectors.toSet()));
        addTags(RSStructures.PYRAMID_JUNGLE.get(), Stream.of(STRUCTURE_TAGS.PYRAMID).collect(Collectors.toSet()));
        addTags(RSStructures.PYRAMID_MUSHROOM.get(), Stream.of(STRUCTURE_TAGS.PYRAMID).collect(Collectors.toSet()));
        addTags(RSStructures.PYRAMID_OCEAN.get(), Stream.of(STRUCTURE_TAGS.PYRAMID).collect(Collectors.toSet()));
        addTags(RSStructures.PYRAMID_GIANT_TREE_TAIGA.get(), Stream.of(STRUCTURE_TAGS.PYRAMID).collect(Collectors.toSet()));
        addTags(RSStructures.PYRAMID_FLOWER_FOREST.get(), Stream.of(STRUCTURE_TAGS.PYRAMID).collect(Collectors.toSet()));
        // regexpos1

        addTags(RSStructures.TEMPLE_NETHER_BASALT.get(), Stream.of(STRUCTURE_TAGS.TEMPLE, STRUCTURE_TAGS.NETHER_TEMPLE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.TEMPLE_NETHER_CRIMSON.get(), Stream.of(STRUCTURE_TAGS.TEMPLE, STRUCTURE_TAGS.NETHER_TEMPLE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.TEMPLE_NETHER_SOUL.get(), Stream.of(STRUCTURE_TAGS.TEMPLE, STRUCTURE_TAGS.NETHER_TEMPLE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.TEMPLE_NETHER_WARPED.get(), Stream.of(STRUCTURE_TAGS.TEMPLE, STRUCTURE_TAGS.NETHER_TEMPLE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.TEMPLE_NETHER_WASTELAND.get(), Stream.of(STRUCTURE_TAGS.TEMPLE, STRUCTURE_TAGS.NETHER_TEMPLE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));

        addTags(RSStructures.SHIPWRECK_CRIMSON.get(), Stream.of(STRUCTURE_TAGS.SHIPWRECK, STRUCTURE_TAGS.NETHER_SHIPWRECK, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.SHIPWRECK_WARPED.get(), Stream.of(STRUCTURE_TAGS.SHIPWRECK, STRUCTURE_TAGS.NETHER_SHIPWRECK, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.SHIPWRECK_NETHER_BRICKS.get(), Stream.of(STRUCTURE_TAGS.SHIPWRECK, STRUCTURE_TAGS.NETHER_SHIPWRECK, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.SHIPWRECK_END.get(), Stream.of(STRUCTURE_TAGS.SHIPWRECK).collect(Collectors.toSet()));

        addTags(RSStructures.OUTPOST_CRIMSON.get(), Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.NETHER_OUTPOST, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_NETHER_BRICK.get(), Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.NETHER_OUTPOST, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_WARPED.get(), Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.NETHER_OUTPOST, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_BIRCH.get(), Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_JUNGLE.get(), Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_GIANT_TREE_TAIGA.get(), Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_DESERT.get(), Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_BADLANDS.get(), Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_SNOWY.get(), Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_ICY.get(), Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_TAIGA.get(), Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_OAK.get(), Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_END.get(), Stream.of(STRUCTURE_TAGS.OUTPOST).collect(Collectors.toSet()));

        addTags(RSStructures.VILLAGE_BADLANDS.get(), Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.VILLAGE_BIRCH.get(), Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.VILLAGE_DARK_FOREST.get(), Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.VILLAGE_GIANT_TAIGA.get(), Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.VILLAGE_JUNGLE.get(), Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE, STRUCTURE_TAGS.NO_LAKES, STRUCTURE_TAGS.LESS_JUNGLE_BUSH).collect(Collectors.toSet()));
        addTags(RSStructures.VILLAGE_MOUNTAINS.get(), Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.VILLAGE_SWAMP.get(), Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.VILLAGE_CRIMSON.get(), Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.VILLAGE_WARPED.get(), Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.VILLAGE_OAK.get(), Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OUTPOST_AVOID_STRUCTURE, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));

        addTags(RSStructures.FORTRESS_JUNGLE.get(), Stream.of(STRUCTURE_TAGS.FORTRESS).collect(Collectors.toSet()));
        addTags(RSStructures.RUINED_PORTAL_END.get(), Stream.of(STRUCTURE_TAGS.RUINED_PORTAL).collect(Collectors.toSet()));
        addTags(RSStructures.RUINS_NETHER.get(), Stream.of(STRUCTURE_TAGS.RUINS).collect(Collectors.toSet()));
        addTags(RSStructures.RUINS_LAND_WARM.get(), Stream.of(STRUCTURE_TAGS.RUINS, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.RUINS_LAND_HOT.get(), Stream.of(STRUCTURE_TAGS.RUINS, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));

        addTags(RSStructures.CITY_NETHER.get(), Stream.of(STRUCTURE_TAGS.CITIES, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));

        addTags(RSStructures.MANSION_BIRCH.get(), Stream.of(STRUCTURE_TAGS.MANSION, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.MANSION_JUNGLE.get(), Stream.of(STRUCTURE_TAGS.MANSION, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.MANSION_OAK.get(), Stream.of(STRUCTURE_TAGS.MANSION, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.MANSION_SAVANNA.get(), Stream.of(STRUCTURE_TAGS.MANSION, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.MANSION_TAIGA.get(), Stream.of(STRUCTURE_TAGS.MANSION, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.MANSION_DESERT.get(), Stream.of(STRUCTURE_TAGS.MANSION, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.MANSION_SNOWY.get(), Stream.of(STRUCTURE_TAGS.MANSION, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));

        addTags(RSStructures.WITCH_HUTS_OAK.get(), Stream.of(STRUCTURE_TAGS.WITCH_HUTS, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.WITCH_HUTS_TAIGA.get(), Stream.of(STRUCTURE_TAGS.WITCH_HUTS, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.WITCH_HUTS_BIRCH.get(), Stream.of(STRUCTURE_TAGS.WITCH_HUTS, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.WITCH_HUTS_DARK_FOREST.get(), Stream.of(STRUCTURE_TAGS.WITCH_HUTS, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.WITCH_HUTS_GIANT_TREE_TAIGA.get(), Stream.of(STRUCTURE_TAGS.WITCH_HUTS, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));

        addTags(RSStructures.BASTION_UNDERGROUND.get(), Stream.of(STRUCTURE_TAGS.BASTION, STRUCTURE_TAGS.NO_LAVAFALLS, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));

        addTags(Structure.NETHER_BRIDGE, Stream.of(STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(Structure.BASTION_REMNANT, Stream.of(STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(Structure.VILLAGE, Stream.of(STRUCTURE_TAGS.BASTION_AVOID_STRUCTURE, STRUCTURE_TAGS.VILLAGE_AVOID_STRUCTURE).collect(Collectors.toSet()));
        addTags(Structure.PILLAGER_OUTPOST, Stream.of(STRUCTURE_TAGS.VILLAGE_AVOID_STRUCTURE).collect(Collectors.toSet()));
        addTags(Structure.STRONGHOLD, Stream.of(STRUCTURE_TAGS.BASTION_AVOID_STRUCTURE).collect(Collectors.toSet()));
        addTags(Structure.END_CITY, Stream.of(STRUCTURE_TAGS.END_AVOID_STRUCTURE).collect(Collectors.toSet()));
    }


    /**
     * Helper method to setup the tags to structure and structure to tag maps.
     *
     * Only does additions and no replacements/deletions.
     */
    private static void addTags(Structure<?> structure, Set<STRUCTURE_TAGS> tags){
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
