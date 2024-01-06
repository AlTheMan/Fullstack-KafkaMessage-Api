package algot.emil.kafkamessageapi.DTO;

import java.time.LocalDate;

/**
 * the message that a client has sent and should be stored in database, as well as forwarded to the recipient client in the front-end.
 * @param senderId
 * @param receiverId
 * @param message
 * @param time
 */
public record SendMessageDTO(Long senderId, Long receiverId, String message, LocalDate time) {
}
