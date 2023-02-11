package com.telepathicgrunt.repurposedstructures.events.lifecycle;

import com.telepathicgrunt.repurposedstructures.events.base.EventHandler;

public class ServerGoingToStopEvent {

    public static final ServerGoingToStopEvent INSTANCE = new ServerGoingToStopEvent();

    public static final EventHandler<ServerGoingToStopEvent> EVENT = new EventHandler<>();

    private ServerGoingToStopEvent() {}
}
