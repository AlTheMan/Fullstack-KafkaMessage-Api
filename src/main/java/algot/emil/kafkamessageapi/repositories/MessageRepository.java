package algot.emil.kafkamessageapi.repositories;

import algot.emil.kafkamessageapi.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByReceiverIdAndSenderIdOrderByTime(Long id1, Long id2);
}
