package algot.emil.kafkamessageapi.services;

import algot.emil.kafkamessageapi.DTO.GetMessageDTO;
import algot.emil.kafkamessageapi.DTO.SendMessageDTO;
import algot.emil.kafkamessageapi.entities.Message;
import algot.emil.kafkamessageapi.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository repository;

    public MessageService(MessageRepository messageRepository) {
        this.repository = messageRepository;
    }

    public void sendMessage(SendMessageDTO dto) {
        if (dto.receiverId() == null || dto.senderId() == null) return;
        if (dto.receiverId() < 0 || dto.senderId() < 0) return;
        if (dto.message() == null) return;
        if (dto.message().isEmpty()) return;
        Message newMessage = new Message(dto.senderId(), dto.receiverId(), dto.message(), LocalDate.now());
        repository.save(newMessage);
    }

    public List<Message> getMessage(GetMessageDTO dto) {
        if (dto.id1() == null || dto.id2() == null) return null;
        if (dto.id1() < 0 || dto.id2() < 0) return null; //TODO: kasta exception
        List<Message> messages = repository.findAllByReceiverIdAndSenderIdOrderByTime(dto.id1(), dto.id2());
        List<Message> messages2 = repository.findAllByReceiverIdAndSenderIdOrderByTime(dto.id2(), dto.id1());
        messages.addAll(messages2);
        //TODO: gör kopia av messages.
        return messages;
    }
}
