package com.telepathicgrunt.repurposedstructures.utils;

import com.google.common.collect.ImmutableSet;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.apache.logging.log4j.message.Message;

import java.util.Set;

@Plugin(name = "LogSpamFiltering", category = Node.CATEGORY, elementType = Filter.ELEMENT_TYPE)
public class LogSpamFiltering extends AbstractFilter {

    private static final Set<Identifier> SILENCED_PIECES = ImmutableSet.of(
            new Identifier("minecraft:mineshaft_room_rs"),
            new Identifier("minecraft:mineshaft_corridor_rs"),
            new Identifier("minecraft:mineshaft_crossing_rs"),
            new Identifier("minecraft:mineshaft_stairs_rs"),
            new Identifier("minecraft:jungle_fortress_start"),
            new Identifier("minecraft:jungle_fortress_entrance"),
            new Identifier("minecraft:jungle_fortress_end"),
            new Identifier("minecraft:jungle_fortress_straight"),
            new Identifier("minecraft:jungle_fortress_corridor_5"),
            new Identifier("minecraft:jungle_fortress_corridor_4"),
            new Identifier("minecraft:jungle_fortress_corridor_3"),
            new Identifier("minecraft:jungle_fortress_corridor_2"),
            new Identifier("minecraft:jungle_fortress_corridor_1"),
            new Identifier("minecraft:jungle_fortress_crossing_3"),
            new Identifier("minecraft:jungle_fortress_crossing_2"),
            new Identifier("minecraft:jungle_fortress_crossing_1"),
            new Identifier("minecraft:jungle_fortress_mushroom_room"),
            new Identifier("minecraft:jungle_fortress_throne"),
            new Identifier("minecraft:jungle_fortress_stairs"),
            new Identifier("minecraft:stronghold_chest_corridor"),
            new Identifier("minecraft:stronghold_corridor"),
            new Identifier("minecraft:stronghold_portal_room"),
            new Identifier("minecraft:stronghold_library"),
            new Identifier("minecraft:stronghold_prison"),
            new Identifier("minecraft:stronghold_room_crossing"),
            new Identifier("minecraft:stronghold_crossing"),
            new Identifier("minecraft:stronghold_right_turn"),
            new Identifier("minecraft:stronghold_left_turn"),
            new Identifier("minecraft:stronghold_straight"),
            new Identifier("minecraft:stronghold_stairs"),
            new Identifier("minecraft:stronghold_stairs_straight"),
            new Identifier("minecraft:stronghold_entrance_stairs")
    );

    @Override
    public Result filter(LogEvent event) {
        Message message = event.getMessage();
        if (message.getFormat().equals("Unknown structure piece id: {}") && SILENCED_PIECES.contains((Identifier)message.getParameters()[0])) {
            return Result.DENY;
        } else {
            return Result.NEUTRAL;
        }
    }
}
