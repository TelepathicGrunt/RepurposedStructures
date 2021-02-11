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
        
        // Structure sets to avoid for some structures
        OVERWORLD_OUTPOST,
        NETHER_OUTPOST,
        NETHER_TEMPLE,
        NETHER_SHIPWRECK,
        GENERIC_AVOID_NETHER_STRUCTURE,
        SHIPWRECK_AVOID_NETHER_STRUCTURE,

        // For mob spawning
        REPLACE_NATURAL_MOBS,
        APPEND_WITH_NATURAL_MOBS,
        
        // Other
        NO_LAKES
    }

    public static final Map<Structure<?>, Set<STRUCTURE_TAGS>> TAGGED_STRUCTURES = new HashMap<>();
    public static final Map<STRUCTURE_TAGS, Set<Structure<?>>> REVERSED_TAGGED_STRUCTURES = new HashMap<>();

    /**
     * Call this after structure registration. This will setup the tags for all structures so we can reference them easier.
     */
    public static void setupTags(){
        addTags(RSStructures.BIRCH_MINESHAFT.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.SAVANNA_MINESHAFT.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.OCEAN_MINESHAFT.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.JUNGLE_MINESHAFT.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.ICY_MINESHAFT.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.DESERT_MINESHAFT.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.STONE_MINESHAFT.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.SWAMP_OR_DARK_FOREST_MINESHAFT.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.TAIGA_MINESHAFT.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.NETHER_MINESHAFT.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.CRIMSON_MINESHAFT.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.WARPED_MINESHAFT.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));
        addTags(RSStructures.END_MINESHAFT.get(), Stream.of(STRUCTURE_TAGS.APPEND_WITH_NATURAL_MOBS, STRUCTURE_TAGS.MINESHAFT).collect(Collectors.toSet()));

        addTags(RSStructures.GRASSY_IGLOO.get(), Stream.of(STRUCTURE_TAGS.IGLOO).collect(Collectors.toSet()));
        addTags(RSStructures.STONE_IGLOO.get(), Stream.of(STRUCTURE_TAGS.IGLOO).collect(Collectors.toSet()));

        addTags(RSStructures.STONEBRICK_STRONGHOLD.get(), Stream.of(STRUCTURE_TAGS.STRONGHOLD).collect(Collectors.toSet()));
        addTags(RSStructures.NETHER_STRONGHOLD.get(), Stream.of(STRUCTURE_TAGS.STRONGHOLD).collect(Collectors.toSet()));
        
        addTags(RSStructures.BADLANDS_PYRAMID.get(), Stream.of(STRUCTURE_TAGS.PYRAMID).collect(Collectors.toSet()));
        addTags(RSStructures.NETHER_PYRAMID.get(), Stream.of(STRUCTURE_TAGS.REPLACE_NATURAL_MOBS, STRUCTURE_TAGS.PYRAMID).collect(Collectors.toSet()));

        addTags(RSStructures.NETHER_BASALT_TEMPLE.get(), Stream.of(STRUCTURE_TAGS.TEMPLE, STRUCTURE_TAGS.NETHER_TEMPLE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.NETHER_CRIMSON_TEMPLE.get(), Stream.of(STRUCTURE_TAGS.TEMPLE, STRUCTURE_TAGS.NETHER_TEMPLE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.NETHER_SOUL_TEMPLE.get(), Stream.of(STRUCTURE_TAGS.TEMPLE, STRUCTURE_TAGS.NETHER_TEMPLE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.NETHER_WARPED_TEMPLE.get(), Stream.of(STRUCTURE_TAGS.TEMPLE, STRUCTURE_TAGS.NETHER_TEMPLE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.NETHER_WASTELAND_TEMPLE.get(), Stream.of(STRUCTURE_TAGS.TEMPLE, STRUCTURE_TAGS.NETHER_TEMPLE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));

        addTags(RSStructures.CRIMSON_SHIPWRECK.get(), Stream.of(STRUCTURE_TAGS.APPEND_WITH_NATURAL_MOBS, STRUCTURE_TAGS.SHIPWRECK, STRUCTURE_TAGS.NETHER_SHIPWRECK, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.WARPED_SHIPWRECK.get(), Stream.of(STRUCTURE_TAGS.APPEND_WITH_NATURAL_MOBS, STRUCTURE_TAGS.SHIPWRECK, STRUCTURE_TAGS.NETHER_SHIPWRECK, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.NETHER_BRICKS_SHIPWRECK.get(), Stream.of(STRUCTURE_TAGS.APPEND_WITH_NATURAL_MOBS, STRUCTURE_TAGS.SHIPWRECK, STRUCTURE_TAGS.NETHER_SHIPWRECK, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.END_SHIPWRECK.get(), Stream.of(STRUCTURE_TAGS.SHIPWRECK).collect(Collectors.toSet()));

        addTags(RSStructures.CRIMSON_OUTPOST.get(), Stream.of(STRUCTURE_TAGS.REPLACE_NATURAL_MOBS, STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.NETHER_OUTPOST, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.NETHER_BRICK_OUTPOST.get(), Stream.of(STRUCTURE_TAGS.REPLACE_NATURAL_MOBS, STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.NETHER_OUTPOST, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.WARPED_OUTPOST.get(), Stream.of(STRUCTURE_TAGS.REPLACE_NATURAL_MOBS, STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.NETHER_OUTPOST, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_BIRCH.get(), Stream.of(STRUCTURE_TAGS.REPLACE_NATURAL_MOBS, STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_JUNGLE.get(), Stream.of(STRUCTURE_TAGS.REPLACE_NATURAL_MOBS, STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_GIANT_TREE_TAIGA.get(), Stream.of(STRUCTURE_TAGS.REPLACE_NATURAL_MOBS, STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_DESERT.get(), Stream.of(STRUCTURE_TAGS.REPLACE_NATURAL_MOBS, STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_BADLANDS.get(), Stream.of(STRUCTURE_TAGS.REPLACE_NATURAL_MOBS, STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_SNOWY.get(), Stream.of(STRUCTURE_TAGS.REPLACE_NATURAL_MOBS, STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_ICY.get(), Stream.of(STRUCTURE_TAGS.REPLACE_NATURAL_MOBS, STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_TAIGA.get(), Stream.of(STRUCTURE_TAGS.REPLACE_NATURAL_MOBS, STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_OAK.get(), Stream.of(STRUCTURE_TAGS.REPLACE_NATURAL_MOBS, STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));

        addTags(RSStructures.BADLANDS_VILLAGE.get(), Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.BIRCH_VILLAGE.get(), Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.DARK_FOREST_VILLAGE.get(), Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.GIANT_TAIGA_VILLAGE.get(), Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.JUNGLE_VILLAGE.get(), Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.MOUNTAINS_VILLAGE.get(), Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.SWAMP_VILLAGE.get(), Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
        addTags(RSStructures.CRIMSON_VILLAGE.get(), Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.WARPED_VILLAGE.get(), Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.VILLAGE_OAK.get(), Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));

        addTags(Structure.FORTRESS, Stream.of(STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(Structure.BASTION_REMNANT, Stream.of(STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(Structure.VILLAGE, Stream.of(STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));

        addTags(RSStructures.JUNGLE_FORTRESS.get(), Stream.of(STRUCTURE_TAGS.APPEND_WITH_NATURAL_MOBS, STRUCTURE_TAGS.FORTRESS).collect(Collectors.toSet()));
        addTags(RSStructures.RUINED_PORTAL_END.get(), Stream.of(STRUCTURE_TAGS.RUINED_PORTAL).collect(Collectors.toSet()));
        addTags(RSStructures.RUINS_NETHER.get(), Stream.of(STRUCTURE_TAGS.RUINS).collect(Collectors.toSet()));
        addTags(RSStructures.CITY_NETHER.get(), Stream.of(STRUCTURE_TAGS.REPLACE_NATURAL_MOBS, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));

        addTags(RSStructures.MANSION_BIRCH.get(), Stream.of(STRUCTURE_TAGS.MANSION, STRUCTURE_TAGS.NO_LAKES).collect(Collectors.toSet()));
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
