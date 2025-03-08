package com.sentifraud.libraries.common.kafka;


import com.sentifraud.libraries.common.contracts.events.GenericEvent;
import com.sentifraud.libraries.common.exceptions.KafkaConfigurationException;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.Producer;


import java.util.Properties;

/**
 * A reusable Kafka producer for sending GenericEvent objects to a Kafka topic.
 */
public class EventProducer {
    private final Producer<String, GenericEvent> producer;

    /**
     * Initializes the Kafka producer with the provided properties.
     *
     * @param kafkaProperties Kafka configuration properties.
     * @throws KafkaConfigurationException if required properties are missing.
     */
    public EventProducer(Properties kafkaProperties) {
        validateKafkaProperties(kafkaProperties);
        this.producer = new KafkaProducer<>(kafkaProperties);
    }

    /**
     * Sends a GenericEvent to the specified Kafka topic.
     *
     * @param topic The Kafka topic to send the event to.
     * @param event The GenericEvent to send.
     */
    public void sendEvent(String topic, GenericEvent event) {
        ProducerRecord<String, GenericEvent> record = new ProducerRecord<>(topic, event.getClientId(), event);
        producer.send(record, (metadata, exception) -> {
            if (exception != null) {
                System.err.println("Error sending event: " + exception.getMessage());
            } else {
                System.out.println("Event sent to topic: " + metadata.topic() + ", partition: " + metadata.partition());
            }
        });
    }

    /**
     * Closes the Kafka producer.
     */
    public void close() {
        producer.close();
    }

    /**
     * Validates that required Kafka properties are present.
     *
     * @param properties The Kafka properties to validate.
     * @throws KafkaConfigurationException if required properties are missing.
     */
    private void validateKafkaProperties(Properties properties) {
        if (!properties.containsKey(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG)) {
            throw new KafkaConfigurationException("Missing required property: " + ProducerConfig.BOOTSTRAP_SERVERS_CONFIG);
        }
        if (!properties.containsKey("schema.registry.url")) {
            throw new KafkaConfigurationException("Missing required property: schema.registry.url");
        }
    }
}
