package algot.emil.kafkamessageapi;

import algot.emil.kafkamessageapi.DTO.SendMessageDTO;
import algot.emil.kafkamessageapi.services.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaReciever {

    private final MessageService messageService;
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaReciever.class);

    public KafkaReciever(MessageService messageService) {
        this.messageService = messageService;
    }

    @KafkaListener(topics = "algot_test", groupId = "algotsGruppId", containerFactory = "factory")
    public void recieveData(SendMessageDTO data) throws Exception {
        System.out.println("data received from: "+data.senderId() + ", to: " + data.receiverId()+", data: " + data.message());
        LOGGER.info("Data - " + data + " recieved");
        messageService.sendMessage(data);
    }
}
