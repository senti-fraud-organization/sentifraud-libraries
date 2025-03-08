package com.sentifraud.libraries.common.exceptions;


/**
 * Thrown when required Kafka configuration properties are missing.
 */
public class KafkaConfigurationException extends RuntimeException {

    public KafkaConfigurationException(String message) {
        super(message);
    }
}
