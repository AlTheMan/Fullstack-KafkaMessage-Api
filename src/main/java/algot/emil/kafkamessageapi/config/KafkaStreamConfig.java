package algot.emil.kafkamessageapi.config;

import algot.emil.kafkamessageapi.DTO.SendMessageDTO;
import algot.emil.kafkamessageapi.Serde.SendMessageDTOSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;



@Configuration
public class KafkaStreamConfig {

    /*
    @Bean
    public KafkaStreams kafkaStreams() {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-streams-app"); //spring-kafka-stream-id
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder builder = new StreamsBuilder();
        builder.stream("algot_test")
                .mapValues(value -> "Processed: " + value)
                .to("processed_algot_test");

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();
        return streams;
    } */


    /**
     * using custom Properties as properties.buildStreamsProperties() is depreciated without SSL-bundle
     *
     */
    @Bean
    public StreamsConfig streamsConfig(KafkaProperties properties){
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-streams-app");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        // Add any additional properties as needed
        return new StreamsConfig(props);
    }

    @Bean
    public KafkaStreams kafkaStreams(StreamsConfig config) {
        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, SendMessageDTO> stream = builder.stream("algot_test",
                Consumed.with(Serdes.String(), new SendMessageDTOSerde()));

        //kStream.filter((key, value) -> value.startsWith("Message_")).mapValues((k, v) -> v.toUpperCase()).peek((k, v) -> System.out.println("Key : " + k + " Value : " + v)).to("spring.boot.kafka.stream.output", Produced.with(Serdes.String(), Serdes.String()));

        stream.mapValues(value ->value)
                .peek((key, value) -> System.out.println("Kafka stream: Processed message: " + value)) // Print the value
                .to("processed_algot_test", Produced.with(Serdes.String(), new SendMessageDTOSerde())); //send the message forward.

        KafkaStreams streams = new KafkaStreams(builder.build(), config);
        streams.start();
        return streams;
    }
}

