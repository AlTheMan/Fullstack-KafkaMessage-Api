package algot.emil.kafkamessageapi.DTO;

import java.time.LocalDate;

public record SendMessageDTO(Long senderId, Long receiverId, String message, LocalDate time) {
}
