package algot.emil.kafkamessageapi;

import algot.emil.kafkamessageapi.DTO.MessageRequest;
import algot.emil.kafkamessageapi.DTO.SendMessageDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping({"/messages/", "messages/"})
public class MessageController {

    //@AutoWired ??
    private KafkaTemplate<String, SendMessageDTO> kafkaTemplate;

    public MessageController(KafkaTemplate<String, SendMessageDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("send/")
    public void publish(@RequestBody SendMessageDTO request){
        kafkaTemplate.send("algot_test", request);
    }
}
