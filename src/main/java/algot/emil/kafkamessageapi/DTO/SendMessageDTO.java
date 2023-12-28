package algot.emil.kafkamessageapi.DTO;

public record SendMessageDTO(Long senderId, Long receiverId, String message) {
}
