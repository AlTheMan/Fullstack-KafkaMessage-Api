package algot.emil.kafkamessageapi.processor;

import algot.emil.kafkamessageapi.DTO.SendMessageDTO;
import algot.emil.kafkamessageapi.controllers.ChatController;
import algot.emil.kafkamessageapi.services.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaReciever {

    private final MessageService messageService;
    private final ChatController chatController;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaReciever.class);

    public KafkaReciever(MessageService messageService, ChatController chatController) {
        this.messageService = messageService;
        this.chatController = chatController;
    }

    @KafkaListener(topics = "processed_algot_test", groupId = "algotsGruppId", containerFactory = "factory")
    public void recieveData(SendMessageDTO data) throws Exception {
        System.out.println("Kafka Listener: data received from: "+data.senderId() + ", to: " + data.receiverId()+", data: " + data.message());
        //LOGGER.info("Data - " + data + " recieved");
        messageService.sendMessage(data);
        chatController.recMessage(data); //sends the data to all relevant users
    }
}
