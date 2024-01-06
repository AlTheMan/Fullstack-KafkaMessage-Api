package algot.emil.kafkamessageapi;

import algot.emil.kafkamessageapi.DTO.SendMessageDTO;
import algot.emil.kafkamessageapi.processor.KafkaProducer;
import algot.emil.kafkamessageapi.services.KafkaProducerService;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@SpringBootTest
public class KafkaProducerServiceTest {

    @Mock
    private KafkaProducer mockProducer;

    @InjectMocks
    private KafkaProducerService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendMessageToKafka() {
        SendMessageDTO testMessage = new SendMessageDTO(1L, 2L, "Test Message", LocalDate.now());

        // Call the method under test
        service.sendMessageToKafka(testMessage);

        // Verify that the producer's send method was called with the correct parameters
        verify(mockProducer, times(1)).send("algot_test", testMessage);
    }
}
