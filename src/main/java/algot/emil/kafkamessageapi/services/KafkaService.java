package algot.emil.kafkamessageapi.services;

import algot.emil.kafkamessageapi.DTO.SendMessageDTO;
import algot.emil.kafkamessageapi.processor.MyKafkaProducer;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    private MyKafkaProducer producer;

    public KafkaService() {
        this.producer = new MyKafkaProducer("localhost:9092");
    }

    public void sendMessageToKafka(SendMessageDTO message) {
        System.out.println("Kafka producer: Sending message: " + message);
        producer.send("algot_test", message); // Replace "algot_test" with your actual topic
    }
}
