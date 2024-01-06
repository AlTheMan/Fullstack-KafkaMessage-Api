package algot.emil.kafkamessageapi.controllers;

import algot.emil.kafkamessageapi.DTO.GetMessageDTO;
import algot.emil.kafkamessageapi.DTO.SendMessageDTO;
import algot.emil.kafkamessageapi.entities.Message;
import algot.emil.kafkamessageapi.services.KafkaService;
import algot.emil.kafkamessageapi.services.MessageService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping({"/messages/", "messages/"})
public class MessageController {

    private final MessageService messageService;
    private  final KafkaService kafkaService;

    //@AutoWired ??
    private KafkaTemplate<String, SendMessageDTO> kafkaTemplate;

    public MessageController(MessageService messageService, KafkaService kafkaService, KafkaTemplate<String, SendMessageDTO> kafkaTemplate) {
        this.messageService = messageService;
        this.kafkaService = kafkaService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping({"send","send/"})
    public void publish(@RequestBody SendMessageDTO request){
        //essageService.sendMessage(request);
        //kafkaTemplate.send("algot_test", request); //sends to producer/consumer
        kafkaService.sendMessageToKafka(request); //sends to stream
    }

    @GetMapping({"get/", "get"})
    public List<Message> publish(GetMessageDTO request){
        return messageService.getMessage(request);
    }
}
