package com.sentifraud.libraries.common.kafka;


import com.sentifraud.libraries.common.contracts.events.GenericEvent;

/**
 * Defines the contract for handling GenericEvent objects.
 */
public interface EventHandler {
    /**
     * Handles a GenericEvent.
     *
     * @param event The event to handle.
     */
    void handleEvent(GenericEvent event);
}
