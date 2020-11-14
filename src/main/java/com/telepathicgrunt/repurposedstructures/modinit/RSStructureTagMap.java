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
        // RS Structrues
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

        // Dimension based
        OVERWORLD,
        NETHER,
        END,

        // Structure sets to avoid for some structures
        OVERWORLD_VILLAGE,
        OVERWORLD_OUTPOST,
        NETHER_VILLAGE,
        NETHER_OUTPOST,
        NETHER_TEMPLE,
        NETHER_SHIPWRECK,
        GENERIC_AVOID_NETHER_STRUCTURE,
        SHIPWRECK_AVOID_NETHER_STRUCTURE
    }

    public static final Map<StructureFeature<?>, Set<STRUCTURE_TAGS>> TAGGED_STRUCTURES = new HashMap<>();
    public static final Map<STRUCTURE_TAGS, Set<StructureFeature<?>>> REVERSED_TAGGED_STRUCTURES = new HashMap<>();

    /**
     * Call this after structure registration. This will setup the tags for all structures so we can reference them easier.
     */
    public static void setupTags(){
        addTags(RSStructures.BIRCH_MINESHAFT, Stream.of(STRUCTURE_TAGS.MINESHAFT, STRUCTURE_TAGS.OVERWORLD).collect(Collectors.toSet()));
        addTags(RSStructures.SAVANNA_MINESHAFT, Stream.of(STRUCTURE_TAGS.MINESHAFT, STRUCTURE_TAGS.OVERWORLD).collect(Collectors.toSet()));
        addTags(RSStructures.OCEAN_MINESHAFT, Stream.of(STRUCTURE_TAGS.MINESHAFT, STRUCTURE_TAGS.OVERWORLD).collect(Collectors.toSet()));
        addTags(RSStructures.JUNGLE_MINESHAFT, Stream.of(STRUCTURE_TAGS.MINESHAFT, STRUCTURE_TAGS.OVERWORLD).collect(Collectors.toSet()));
        addTags(RSStructures.ICY_MINESHAFT, Stream.of(STRUCTURE_TAGS.MINESHAFT, STRUCTURE_TAGS.OVERWORLD).collect(Collectors.toSet()));
        addTags(RSStructures.DESERT_MINESHAFT, Stream.of(STRUCTURE_TAGS.MINESHAFT, STRUCTURE_TAGS.OVERWORLD).collect(Collectors.toSet()));
        addTags(RSStructures.STONE_MINESHAFT, Stream.of(STRUCTURE_TAGS.MINESHAFT, STRUCTURE_TAGS.OVERWORLD).collect(Collectors.toSet()));
        addTags(RSStructures.SWAMP_OR_DARK_FOREST_MINESHAFT, Stream.of(STRUCTURE_TAGS.MINESHAFT, STRUCTURE_TAGS.OVERWORLD).collect(Collectors.toSet()));
        addTags(RSStructures.TAIGA_MINESHAFT, Stream.of(STRUCTURE_TAGS.MINESHAFT, STRUCTURE_TAGS.OVERWORLD).collect(Collectors.toSet()));
        addTags(RSStructures.NETHER_MINESHAFT, Stream.of(STRUCTURE_TAGS.MINESHAFT, STRUCTURE_TAGS.NETHER).collect(Collectors.toSet()));
        addTags(RSStructures.END_MINESHAFT, Stream.of(STRUCTURE_TAGS.MINESHAFT, STRUCTURE_TAGS.END).collect(Collectors.toSet()));

        addTags(RSStructures.GRASSY_IGLOO, Stream.of(STRUCTURE_TAGS.IGLOO, STRUCTURE_TAGS.OVERWORLD).collect(Collectors.toSet()));
        addTags(RSStructures.STONE_IGLOO, Stream.of(STRUCTURE_TAGS.IGLOO, STRUCTURE_TAGS.OVERWORLD).collect(Collectors.toSet()));

        addTags(RSStructures.STONEBRICK_STRONGHOLD, Stream.of(STRUCTURE_TAGS.STRONGHOLD, STRUCTURE_TAGS.OVERWORLD).collect(Collectors.toSet()));
        addTags(RSStructures.NETHER_STRONGHOLD, Stream.of(STRUCTURE_TAGS.STRONGHOLD, STRUCTURE_TAGS.NETHER).collect(Collectors.toSet()));
        
        addTags(RSStructures.BADLANDS_PYRAMID, Stream.of(STRUCTURE_TAGS.PYRAMID, STRUCTURE_TAGS.OVERWORLD).collect(Collectors.toSet()));
        addTags(RSStructures.NETHER_PYRAMID, Stream.of(STRUCTURE_TAGS.PYRAMID, STRUCTURE_TAGS.NETHER).collect(Collectors.toSet()));

        addTags(RSStructures.NETHER_BASALT_TEMPLE, Stream.of(STRUCTURE_TAGS.TEMPLE, STRUCTURE_TAGS.NETHER, STRUCTURE_TAGS.NETHER_TEMPLE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.NETHER_CRIMSON_TEMPLE, Stream.of(STRUCTURE_TAGS.TEMPLE, STRUCTURE_TAGS.NETHER, STRUCTURE_TAGS.NETHER_TEMPLE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.NETHER_SOUL_TEMPLE, Stream.of(STRUCTURE_TAGS.TEMPLE, STRUCTURE_TAGS.NETHER, STRUCTURE_TAGS.NETHER_TEMPLE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.NETHER_WARPED_TEMPLE, Stream.of(STRUCTURE_TAGS.TEMPLE, STRUCTURE_TAGS.NETHER, STRUCTURE_TAGS.NETHER_TEMPLE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.NETHER_WASTELAND_TEMPLE, Stream.of(STRUCTURE_TAGS.TEMPLE, STRUCTURE_TAGS.NETHER, STRUCTURE_TAGS.NETHER_TEMPLE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));

        addTags(RSStructures.CRIMSON_SHIPWRECK, Stream.of(STRUCTURE_TAGS.SHIPWRECK, STRUCTURE_TAGS.NETHER, STRUCTURE_TAGS.NETHER_SHIPWRECK, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.WARPED_SHIPWRECK, Stream.of(STRUCTURE_TAGS.SHIPWRECK, STRUCTURE_TAGS.NETHER, STRUCTURE_TAGS.NETHER_SHIPWRECK, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.NETHER_BRICKS_SHIPWRECK, Stream.of(STRUCTURE_TAGS.SHIPWRECK, STRUCTURE_TAGS.NETHER, STRUCTURE_TAGS.NETHER_SHIPWRECK, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.END_SHIPWRECK, Stream.of(STRUCTURE_TAGS.SHIPWRECK, STRUCTURE_TAGS.END).collect(Collectors.toSet()));

        addTags(RSStructures.CRIMSON_OUTPOST, Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.NETHER, STRUCTURE_TAGS.NETHER_OUTPOST, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.NETHER_BRICK_OUTPOST, Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.NETHER, STRUCTURE_TAGS.NETHER_OUTPOST, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.WARPED_OUTPOST, Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.NETHER, STRUCTURE_TAGS.NETHER_OUTPOST, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_BIRCH, Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_JUNGLE, Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_GIANT_TREE_TAIGA, Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_DESERT, Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_BADLANDS, Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_SNOWY, Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));
        addTags(RSStructures.OUTPOST_ICY, Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.OVERWORLD, STRUCTURE_TAGS.OVERWORLD_OUTPOST).collect(Collectors.toSet()));

        addTags(RSStructures.BADLANDS_VILLAGE, Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OVERWORLD, STRUCTURE_TAGS.OVERWORLD_VILLAGE).collect(Collectors.toSet()));
        addTags(RSStructures.BIRCH_VILLAGE, Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OVERWORLD, STRUCTURE_TAGS.OVERWORLD_VILLAGE).collect(Collectors.toSet()));
        addTags(RSStructures.DARK_FOREST_VILLAGE, Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OVERWORLD, STRUCTURE_TAGS.OVERWORLD_VILLAGE).collect(Collectors.toSet()));
        addTags(RSStructures.GIANT_TAIGA_VILLAGE, Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OVERWORLD, STRUCTURE_TAGS.OVERWORLD_VILLAGE).collect(Collectors.toSet()));
        addTags(RSStructures.JUNGLE_VILLAGE, Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OVERWORLD, STRUCTURE_TAGS.OVERWORLD_VILLAGE).collect(Collectors.toSet()));
        addTags(RSStructures.MOUNTAINS_VILLAGE, Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OVERWORLD, STRUCTURE_TAGS.OVERWORLD_VILLAGE).collect(Collectors.toSet()));
        addTags(RSStructures.SWAMP_VILLAGE, Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OVERWORLD, STRUCTURE_TAGS.OVERWORLD_VILLAGE).collect(Collectors.toSet()));
        addTags(RSStructures.CRIMSON_VILLAGE, Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.NETHER, STRUCTURE_TAGS.NETHER_VILLAGE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.WARPED_VILLAGE, Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.NETHER, STRUCTURE_TAGS.NETHER_VILLAGE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.VILLAGE_OAK, Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OVERWORLD, STRUCTURE_TAGS.OVERWORLD_VILLAGE).collect(Collectors.toSet()));

        addTags(StructureFeature.FORTRESS, Stream.of(STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(StructureFeature.BASTION_REMNANT, Stream.of(STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(StructureFeature.VILLAGE, Stream.of(STRUCTURE_TAGS.OVERWORLD_VILLAGE).collect(Collectors.toSet()));

        addTags(RSStructures.RUINED_PORTAL_END, Stream.of(STRUCTURE_TAGS.RUINED_PORTAL, STRUCTURE_TAGS.END).collect(Collectors.toSet()));
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
