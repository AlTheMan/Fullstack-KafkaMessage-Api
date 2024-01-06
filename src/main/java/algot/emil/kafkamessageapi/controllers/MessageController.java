package algot.emil.kafkamessageapi.controllers;

import algot.emil.kafkamessageapi.DTO.GetMessageDTO;
import algot.emil.kafkamessageapi.DTO.SendMessageDTO;
import algot.emil.kafkamessageapi.entities.Message;
import algot.emil.kafkamessageapi.services.KafkaProducerService;
import algot.emil.kafkamessageapi.services.MessageService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping({"/messages/", "messages/"})
public class MessageController {

    private final MessageService messageService;
    private  final KafkaProducerService kafkaProducerService;

    //@AutoWired ??
    private KafkaTemplate<String, SendMessageDTO> kafkaTemplate;

    public MessageController(MessageService messageService, KafkaProducerService kafkaProducerService, KafkaTemplate<String, SendMessageDTO> kafkaTemplate) {
        this.messageService = messageService;
        this.kafkaProducerService = kafkaProducerService;
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * publishes/stores a message that was retreived from the client
     * @param request
     */
    @PostMapping({"send","send/"})
    public void publish(@RequestBody SendMessageDTO request){
        //essageService.sendMessage(request);
        //kafkaTemplate.send("algot_test", request); //sends to producer/consumer
        kafkaProducerService.sendMessageToKafka(request); //sends to stream
    }

    /**
     * retreives all messages between two people
     * @param request
     * @return
     */
    @GetMapping({"get/", "get"})
    public List<Message> publish(GetMessageDTO request){
        return messageService.getMessage(request);
    }
}
