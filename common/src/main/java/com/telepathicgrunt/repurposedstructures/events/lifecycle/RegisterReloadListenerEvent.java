package com.telepathicgrunt.repurposedstructures.events.lifecycle;

import com.telepathicgrunt.repurposedstructures.events.base.EventHandler;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.PreparableReloadListener;

import java.util.function.BiConsumer;

public record RegisterReloadListenerEvent(BiConsumer<Identifier, PreparableReloadListener> registrar) {

    public static final EventHandler<RegisterReloadListenerEvent> EVENT = new EventHandler<>();

    public void register(Identifier id, PreparableReloadListener listener) {
        registrar.accept(id, listener);
    }
}
