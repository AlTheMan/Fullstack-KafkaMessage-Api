package algot.emil.kafkamessageapi;

import algot.emil.kafkamessageapi.DTO.GetMessageDTO;
import algot.emil.kafkamessageapi.DTO.SendMessageDTO;
import algot.emil.kafkamessageapi.entities.Message;
import algot.emil.kafkamessageapi.repositories.MessageRepository;
import algot.emil.kafkamessageapi.services.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class MessageServiceTest {

    @Mock
    private MessageRepository mockRepository;

    private MessageService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new MessageService(mockRepository);
    }

    @Test
    void sendMessage_ValidMessage_SavesMessage() {
        SendMessageDTO dto = new SendMessageDTO(1L, 2L, "Test Message", LocalDate.now());

        service.sendMessage(dto);

        // Verify that the repository's save method was called once
        verify(mockRepository, times(1)).save(any(Message.class));
    }

    @Test
    void getMessage_ValidRequest_ReturnsMessages() {
        GetMessageDTO dto = new GetMessageDTO(1L, 2L);

        List<Message> mockMessages = new ArrayList<>();
        when(mockRepository.findAllByReceiverIdAndSenderIdOrderByTime(dto.id1(), dto.id2())).thenReturn(mockMessages);
        when(mockRepository.findAllByReceiverIdAndSenderIdOrderByTime(dto.id2(), dto.id1())).thenReturn(mockMessages);

        List<Message> result = service.getMessage(dto);

        // Verify that the repository methods were called and messages are returned
        verify(mockRepository, times(1)).findAllByReceiverIdAndSenderIdOrderByTime(dto.id1(), dto.id2());
        verify(mockRepository, times(1)).findAllByReceiverIdAndSenderIdOrderByTime(dto.id2(), dto.id1());
        assertNotNull(result);
    }
}