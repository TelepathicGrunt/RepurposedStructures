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
        MINESHAFT,
        PYRAMID,
        TEMPLE,
        VILLAGE,
        STRONGHOLD,
        SHIPWRECK,
        FORTRESS,
        IGLOO,
        OUTPOST,
        OVERWORLD,
        NETHER,
        END,
        OVERWORLD_VILLAGE,
        NETHER_VILLAGE,
        NETHER_OUTPOST,
        NETHER_TEMPLE,
        NETHER_SHIPWRECK,
        GENERIC_AVOID_NETHER_STRUCTURE,
        SHIPWRECK_AVOID_NETHER_STRUCTURE
    }

    public static final Map<Structure<?>, Set<STRUCTURE_TAGS>> TAGGED_STRUCTURES = new HashMap<>();
    public static final Map<STRUCTURE_TAGS, Set<Structure<?>>> REVERSED_TAGGED_STRUCTURES = new HashMap<>();

    /**
     * Call this after structure registration. This will setup the tags for all structures so we can reference them easier.
     */
    public static void setupTags(){
        addTags(RSStructures.BIRCH_MINESHAFT.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT, STRUCTURE_TAGS.OVERWORLD).collect(Collectors.toSet()));
        addTags(RSStructures.SAVANNA_MINESHAFT.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT, STRUCTURE_TAGS.OVERWORLD).collect(Collectors.toSet()));
        addTags(RSStructures.OCEAN_MINESHAFT.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT, STRUCTURE_TAGS.OVERWORLD).collect(Collectors.toSet()));
        addTags(RSStructures.JUNGLE_MINESHAFT.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT, STRUCTURE_TAGS.OVERWORLD).collect(Collectors.toSet()));
        addTags(RSStructures.ICY_MINESHAFT.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT, STRUCTURE_TAGS.OVERWORLD).collect(Collectors.toSet()));
        addTags(RSStructures.DESERT_MINESHAFT.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT, STRUCTURE_TAGS.OVERWORLD).collect(Collectors.toSet()));
        addTags(RSStructures.STONE_MINESHAFT.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT, STRUCTURE_TAGS.OVERWORLD).collect(Collectors.toSet()));
        addTags(RSStructures.SWAMP_OR_DARK_FOREST_MINESHAFT.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT, STRUCTURE_TAGS.OVERWORLD).collect(Collectors.toSet()));
        addTags(RSStructures.TAIGA_MINESHAFT.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT, STRUCTURE_TAGS.OVERWORLD).collect(Collectors.toSet()));
        addTags(RSStructures.NETHER_MINESHAFT.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT, STRUCTURE_TAGS.NETHER).collect(Collectors.toSet()));
        addTags(RSStructures.END_MINESHAFT.get(), Stream.of(STRUCTURE_TAGS.MINESHAFT, STRUCTURE_TAGS.END).collect(Collectors.toSet()));

        addTags(RSStructures.GRASSY_IGLOO.get(), Stream.of(STRUCTURE_TAGS.IGLOO, STRUCTURE_TAGS.OVERWORLD).collect(Collectors.toSet()));
        addTags(RSStructures.STONE_IGLOO.get(), Stream.of(STRUCTURE_TAGS.IGLOO, STRUCTURE_TAGS.OVERWORLD).collect(Collectors.toSet()));

        addTags(RSStructures.STONEBRICK_STRONGHOLD.get(), Stream.of(STRUCTURE_TAGS.STRONGHOLD, STRUCTURE_TAGS.OVERWORLD).collect(Collectors.toSet()));
        addTags(RSStructures.NETHER_STRONGHOLD.get(), Stream.of(STRUCTURE_TAGS.STRONGHOLD, STRUCTURE_TAGS.NETHER).collect(Collectors.toSet()));
        
        addTags(RSStructures.BADLANDS_PYRAMID.get(), Stream.of(STRUCTURE_TAGS.PYRAMID, STRUCTURE_TAGS.OVERWORLD).collect(Collectors.toSet()));
        addTags(RSStructures.NETHER_PYRAMID.get(), Stream.of(STRUCTURE_TAGS.PYRAMID, STRUCTURE_TAGS.NETHER).collect(Collectors.toSet()));

        addTags(RSStructures.NETHER_BASALT_TEMPLE.get(), Stream.of(STRUCTURE_TAGS.TEMPLE, STRUCTURE_TAGS.NETHER, STRUCTURE_TAGS.NETHER_TEMPLE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.NETHER_CRIMSON_TEMPLE.get(), Stream.of(STRUCTURE_TAGS.TEMPLE, STRUCTURE_TAGS.NETHER, STRUCTURE_TAGS.NETHER_TEMPLE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.NETHER_SOUL_TEMPLE.get(), Stream.of(STRUCTURE_TAGS.TEMPLE, STRUCTURE_TAGS.NETHER, STRUCTURE_TAGS.NETHER_TEMPLE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.NETHER_WARPED_TEMPLE.get(), Stream.of(STRUCTURE_TAGS.TEMPLE, STRUCTURE_TAGS.NETHER, STRUCTURE_TAGS.NETHER_TEMPLE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.NETHER_WASTELAND_TEMPLE.get(), Stream.of(STRUCTURE_TAGS.TEMPLE, STRUCTURE_TAGS.NETHER, STRUCTURE_TAGS.NETHER_TEMPLE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));

        addTags(RSStructures.CRIMSON_SHIPWRECK.get(), Stream.of(STRUCTURE_TAGS.SHIPWRECK, STRUCTURE_TAGS.NETHER, STRUCTURE_TAGS.NETHER_SHIPWRECK, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.WARPED_SHIPWRECK.get(), Stream.of(STRUCTURE_TAGS.SHIPWRECK, STRUCTURE_TAGS.NETHER, STRUCTURE_TAGS.NETHER_SHIPWRECK, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.NETHER_BRICKS_SHIPWRECK.get(), Stream.of(STRUCTURE_TAGS.SHIPWRECK, STRUCTURE_TAGS.NETHER, STRUCTURE_TAGS.NETHER_SHIPWRECK, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.END_SHIPWRECK.get(), Stream.of(STRUCTURE_TAGS.SHIPWRECK, STRUCTURE_TAGS.END).collect(Collectors.toSet()));

        addTags(RSStructures.CRIMSON_OUTPOST.get(), Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.NETHER, STRUCTURE_TAGS.NETHER_OUTPOST, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.NETHER_BRICK_OUTPOST.get(), Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.NETHER, STRUCTURE_TAGS.NETHER_OUTPOST, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.WARPED_OUTPOST.get(), Stream.of(STRUCTURE_TAGS.OUTPOST, STRUCTURE_TAGS.NETHER, STRUCTURE_TAGS.NETHER_OUTPOST, STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));

        addTags(RSStructures.BADLANDS_VILLAGE.get(), Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OVERWORLD, STRUCTURE_TAGS.OVERWORLD_VILLAGE).collect(Collectors.toSet()));
        addTags(RSStructures.BIRCH_VILLAGE.get(), Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OVERWORLD, STRUCTURE_TAGS.OVERWORLD_VILLAGE).collect(Collectors.toSet()));
        addTags(RSStructures.DARK_FOREST_VILLAGE.get(), Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OVERWORLD, STRUCTURE_TAGS.OVERWORLD_VILLAGE).collect(Collectors.toSet()));
        addTags(RSStructures.GIANT_TAIGA_VILLAGE.get(), Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OVERWORLD, STRUCTURE_TAGS.OVERWORLD_VILLAGE).collect(Collectors.toSet()));
        addTags(RSStructures.JUNGLE_VILLAGE.get(), Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OVERWORLD, STRUCTURE_TAGS.OVERWORLD_VILLAGE).collect(Collectors.toSet()));
        addTags(RSStructures.MOUNTAINS_VILLAGE.get(), Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OVERWORLD, STRUCTURE_TAGS.OVERWORLD_VILLAGE).collect(Collectors.toSet()));
        addTags(RSStructures.SWAMP_VILLAGE.get(), Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.OVERWORLD, STRUCTURE_TAGS.OVERWORLD_VILLAGE).collect(Collectors.toSet()));
        addTags(RSStructures.CRIMSON_VILLAGE.get(), Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.NETHER, STRUCTURE_TAGS.NETHER_VILLAGE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(RSStructures.WARPED_VILLAGE.get(), Stream.of(STRUCTURE_TAGS.VILLAGE, STRUCTURE_TAGS.NETHER, STRUCTURE_TAGS.NETHER_VILLAGE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        
        addTags(Structure.FORTRESS, Stream.of(STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
        addTags(Structure.BASTION_REMNANT, Stream.of(STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE, STRUCTURE_TAGS.SHIPWRECK_AVOID_NETHER_STRUCTURE).collect(Collectors.toSet()));
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
