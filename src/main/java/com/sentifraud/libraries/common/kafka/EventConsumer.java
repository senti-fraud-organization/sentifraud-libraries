package com.sentifraud.libraries.common.kafka;

import com.sentifraud.libraries.common.contracts.events.GenericEvent;
import com.sentifraud.libraries.common.exceptions.KafkaConfigurationException;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * A reusable Kafka consumer for consuming GenericEvent objects from a Kafka topic.
 */
public class EventConsumer {
    private final KafkaConsumer<String, GenericEvent> consumer;

    /**
     * Initializes the Kafka consumer with the provided properties.
     *
     * @param kafkaProperties Kafka configuration properties.
     * @throws KafkaConfigurationException if required properties are missing.
     */
    public EventConsumer(Properties kafkaProperties) {
        validateKafkaProperties(kafkaProperties);
        this.consumer = new KafkaConsumer<>(kafkaProperties);
    }

    /**
     * Subscribes to the specified Kafka topic.
     *
     * @param topic The Kafka topic to subscribe to.
     */
    public void subscribe(String topic) {
        consumer.subscribe(Collections.singletonList(topic));
    }

    /**
     * Polls for events from the subscribed topic and passes them to the provided EventHandler.
     *
     * @param timeout The duration to wait for events.
     * @param eventHandler The handler to process the events.
     */
    public void pollEvents(Duration timeout, EventHandler eventHandler) {
        ConsumerRecords<String, GenericEvent> records = consumer.poll(timeout);
        records.forEach(record -> eventHandler.handleEvent(record.value()));
    }

    /**
     * Closes the Kafka consumer.
     */
    public void close() {
        consumer.close();
    }

    /**
     * Validates that required Kafka properties are present.
     *
     * @param properties The Kafka properties to validate.
     * @throws KafkaConfigurationException if required properties are missing.
     */
    private void validateKafkaProperties(Properties properties) {
        if (!properties.containsKey(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG)) {
            throw new KafkaConfigurationException("Missing required property: " + ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG);
        }
        if (!properties.containsKey("schema.registry.url")) {
            throw new KafkaConfigurationException("Missing required property: schema.registry.url");
        }
    }
}
