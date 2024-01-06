package algot.emil.kafkamessageapi.Serde;

import algot.emil.kafkamessageapi.DTO.SendMessageDTO;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

/**
 * this class is used for serializing/deserializing SendMessageDTOs in kafka streams
 * using JsonSearlizer/JsonDeserializer
 */
public class SendMessageDTOSerde implements Serde<SendMessageDTO> {

    private final JsonSerializer<SendMessageDTO> serializer = new JsonSerializer<>();
    private final JsonDeserializer<SendMessageDTO> deserializer = new JsonDeserializer<>(SendMessageDTO.class);

    @Override
    public Serializer<SendMessageDTO> serializer() {
        return serializer;
    }

    @Override
    public Deserializer<SendMessageDTO> deserializer() {
        return deserializer;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        serializer.configure(configs, isKey);
        deserializer.configure(configs, isKey);
    }

    @Override
    public void close() {
        serializer.close();
        deserializer.close();
    }
}
