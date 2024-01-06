package algot.emil.kafkamessageapi.controllers;


import algot.emil.kafkamessageapi.DTO.SendMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate; //is a helper class in Spring that simplifies sending messages to connected WebSocket clients.

    /**
     * handles messages sent to /app/message
     * The @MessageMapping("/message") annotation indicates that this method processes STOMP messages sent to the /app/message endpoint
     * (since /app is set as the application destination prefix in your WebSocket configuration).
     *The @SendTo("/chatroom/public") annotation routes the return value of this method to the /chatroom/public destination,
     * making it accessible to all subscribers of this topic.
     * The method simply returns the received SendMessageDTO object,
     * effectively broadcasting it to all subscribers of /chatroom/public.
     */
    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public SendMessageDTO receiveMessage(@Payload SendMessageDTO message){
        return message;
    }


    /**
     * This method is designed for handling private messages.
     * The @MessageMapping("/private-message") indicates this method processes messages sent to /app/private-message.
     * Instead of using @SendTo, this method manually sends the message to a specific user using simpMessagingTemplate.convertAndSendToUser().
     * The method sends the message to the /user/{receiverId}/private destination,
     * where {receiverId} is dynamically substituted with the receiver's ID.
     * This ensures that only the intended recipient receives the message.
     */
    @MessageMapping("/private-message")
    public SendMessageDTO recMessage(@Payload SendMessageDTO message){
        simpMessagingTemplate.convertAndSendToUser(message.receiverId().toString(),"/private",message);
        System.out.println("WebSocket: sending message to front-end-client: " + message.toString());
        return message;
    }
}