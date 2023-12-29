package algot.emil.kafkamessageapi.config;

import algot.emil.kafkamessageapi.DTO.SendMessageDTO;
import algot.emil.kafkamessageapi.DTO.SendMessageDTO2;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    public static final String GROUP_ID = "algotsGruppId";


    /**
     * configuration for our producer
     * @return
     */
    public Map<String, Object> consumerConfig(){
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        // Use StringDeserializer for key
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        // Use JsonDeserializer for value
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        // Set trusted packages for deserialization
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return props;
    }

    /**
     * Creates consumer instances.
     * Andra parameterna kan vara custom parameter, tex DTO
     */
    @Bean
    public ConsumerFactory<String, SendMessageDTO> consumerFactory(){
        /*
        JsonDeserializer<SendMessageDTO2> deserializer = new JsonDeserializer<>(SendMessageDTO2.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);
        */
        return new DefaultKafkaConsumerFactory<>(consumerConfig(), new StringDeserializer(),
                new JsonDeserializer<>(SendMessageDTO.class));
        //new JsonDeserializer<>(SendMessageDTO.class)
    }

    /**
     * enables us to receive messages
     * andra  parametern kan vara custom parameter, t.ex. DTO
     */
    /*@Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, SendMessageDTO>> factory(ConsumerFactory<String, SendMessageDTO> consumerFactory){
        ConcurrentKafkaListenerContainerFactory<String, SendMessageDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    } */

    //ConsumerFactory<String, SendMessageDTO> consumerFactory   as input
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, SendMessageDTO> factory() {
        ConcurrentKafkaListenerContainerFactory<String, SendMessageDTO> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
