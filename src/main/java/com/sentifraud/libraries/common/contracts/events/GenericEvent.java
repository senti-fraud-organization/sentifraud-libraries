package com.sentifraud.libraries.common.contracts.events;

import java.util.Map;
import java.util.UUID;

/**
 * Represents a generic event structure that can encapsulate events from different client applications.
 */
public class GenericEvent {
    private String eventId; // Unique ID for the event
    private String eventType; // Type of event (e.g., "FINANCIAL_TRANSACTION", "INSURANCE_CLAIM")
    private String clientId; // Identifier for the client application
    private String schemaVersion; // Schema version for deserialization
    private long timestamp; // Event creation timestamp
    private Map<String, Object> payload; // Dynamic payload to hold event-specific data

    // Default constructor for deserialization
    public GenericEvent() {}

    public GenericEvent(String eventType, String clientId, String schemaVersion, Map<String, Object> payload) {
        this.eventId = UUID.randomUUID().toString();
        this.eventType = eventType;
        this.clientId = clientId;
        this.schemaVersion = schemaVersion;
        this.timestamp = System.currentTimeMillis();
        this.payload = payload;
    }

    // Getters and Setters
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSchemaVersion() {
        return schemaVersion;
    }

    public void setSchemaVersion(String schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "GenericEvent{" +
                "eventId='" + eventId + '\'' +
                ", eventType='" + eventType + '\'' +
                ", clientId='" + clientId + '\'' +
                ", schemaVersion='" + schemaVersion + '\'' +
                ", timestamp=" + timestamp +
                ", payload=" + payload +
                '}';
    }
}