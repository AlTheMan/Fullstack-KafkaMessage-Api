package algot.emil.kafkamessageapi;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "algot_test", groupId = "algotsGruppId")
    void listener(String data){
        System.out.println("listener received: " + data);
    }
}
