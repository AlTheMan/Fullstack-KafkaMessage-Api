package algot.emil.kafkamessageapi.DTO;

/**
 * Is used when a client want to retreive all messages between two people.
 * @param id1
 * @param id2
 */
public record GetMessageDTO(Long id1, Long id2) {
}
