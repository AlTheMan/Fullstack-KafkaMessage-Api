package algot.emil.kafkamessageapi;

import algot.emil.kafkamessageapi.DTO.MessageRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping({"/messages/", "messages/"})
public class MessageController {

    private KafkaTemplate<String, String> kafkaTemplate;

    public MessageController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("send/")
    public void publish(@RequestBody MessageRequest request){
        kafkaTemplate.send("algot_test", request.message());
    }
}
