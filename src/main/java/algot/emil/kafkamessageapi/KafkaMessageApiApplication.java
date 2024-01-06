package algot.emil.kafkamessageapi;

import algot.emil.kafkamessageapi.DTO.SendMessageDTO;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;
import java.util.Properties;

/**
 * This application is meant for message handling (API)
 * Uses web sockets to communicate with front-end
 * Flow of the application;
 * when retreiving messages, the application stores the message in database as well as sends the message forward to the intenden recipient (through ID)
 * when retreiving messages: A kafka producer sends the message to a Kafka stream (Kafka stream is an unnessecary middle-man), and kafka stream sends it forward to a kafka-consumer;
 * the kafka consumer sends the message forward to the database, as well as sending the message to web-socket to forward the message to the intenden recipient.
 * When a front-end client calls GET-messages, then no Kafka is used at all, everything is then just retreived from the database as usual.
 */
@EnableKafka
@EnableKafkaStreams
@SpringBootApplication
@EnableScheduling
public class KafkaMessageApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaMessageApiApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String,SendMessageDTO> kafkaTemplate){
		return args -> {
		kafkaTemplate.send("algot_test", new SendMessageDTO(1L, 2L, "tjo", LocalDate.now()));
		};
	}
}
