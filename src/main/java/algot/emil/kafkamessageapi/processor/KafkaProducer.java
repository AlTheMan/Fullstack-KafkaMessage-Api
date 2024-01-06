package algot.emil.kafkamessageapi.processor;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import algot.emil.kafkamessageapi.DTO.SendMessageDTO;

import java.util.Properties;

public class KafkaProducer {
    private final org.apache.kafka.clients.producer.KafkaProducer<String, SendMessageDTO> producer;

    public KafkaProducer(String bootstrapServers) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        this.producer = new org.apache.kafka.clients.producer.KafkaProducer<>(props);
    }

    public void send(String topic, SendMessageDTO message) {
        ProducerRecord<String, SendMessageDTO> record = new ProducerRecord<>(topic, message);
        producer.send(record);
    }

    // Optional: Close the producer
    public void close() {
        producer.close();
    }
}
