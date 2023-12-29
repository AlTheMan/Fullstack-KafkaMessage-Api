package algot.emil.kafkamessageapi.config;

import algot.emil.kafkamessageapi.DTO.SendMessageDTO;
import algot.emil.kafkamessageapi.DTO.SendMessageDTO2;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    /**
     * configuration for our producer
     * @return
     */
    public Map<String, Object> producerConfig(){
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    /**
     * Creates producer instances.
     * Andra parameterna kan vara custom parameter, tex DTO
     */
    @Bean
    public ProducerFactory<String, SendMessageDTO> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    /**
     * enables us to send messages
     * andra  parametern kan vara custom parameter, t.ex. DTO
     */
    @Bean
    public KafkaTemplate<String, SendMessageDTO> kafkaTemplate(
            ProducerFactory<String, SendMessageDTO> producerFactory
    ){
        return new KafkaTemplate<>(producerFactory);
    }

}
