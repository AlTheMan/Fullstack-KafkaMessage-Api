package algot.emil.kafkamessageapi;

import algot.emil.kafkamessageapi.DTO.SendMessageDTO;
import algot.emil.kafkamessageapi.DTO.SendMessageDTO2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
/*
@Component
//@Service
public class KafkaListeners {

    @KafkaListener(topics = "algot_test", groupId = "algotsGruppId")
    void listener(SendMessageDTO data){
        System.out.println("listener received data");
        //System.out.println("listener received: senderId: " + data.senderId() + ", receiverId: " + data.receiverId()+  "message: " + data.message());
    }
} */


