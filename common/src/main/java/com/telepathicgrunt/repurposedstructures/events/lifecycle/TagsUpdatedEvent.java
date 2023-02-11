package com.telepathicgrunt.repurposedstructures.events.lifecycle;

import com.telepathicgrunt.repurposedstructures.events.base.EventHandler;
import net.minecraft.core.RegistryAccess;

public record TagsUpdatedEvent(RegistryAccess registryAccess, boolean fromPacket) {

    public static final EventHandler<TagsUpdatedEvent> EVENT = new EventHandler<>();
}
