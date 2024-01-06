package algot.emil.kafkamessageapi.services;

import algot.emil.kafkamessageapi.DTO.SendMessageDTO;
import algot.emil.kafkamessageapi.processor.KafkaProducer;
import org.springframework.stereotype.Service;

/**
 * this class initializes a kafka producer and sends messages according to topic
 */
@Service
public class KafkaProducerService {

    private KafkaProducer producer;

    public KafkaProducerService() {
        this.producer = new KafkaProducer("localhost:9092");
    }

    public void sendMessageToKafka(SendMessageDTO message) {
        System.out.println("Kafka producer: Sending message: " + message);
        producer.send("algot_test", message); // Replace "algot_test" with your actual topic
    }
}
