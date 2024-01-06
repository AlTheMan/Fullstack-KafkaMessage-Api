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
 * Kafka streams is running in paralell with Kafka producer/consumer
 * To run;
 * open powershell
 * cd InsertYourKafkaFolderHere (for me it's Cd C:\kafka_2.13-3.6.1)
 * Start Kafka in windows:
 * bin/windows/zookeeper-server-start.bat config/zookeeper.properties
 * then open new powershell terminal and;
 * bin/windows/kafka-server-start.bat config/server.properties
 * To stop the server;
 * bin/windows/kafka-server-stop.bat config/server.properties
 * Open a new terminal, read messages; (topic namnet is set to "algot_test");
 * bin/windows/kafka-console-consumer.bat --topic algot_test --from-beginning --bootstrap-server localhost:9092
 * Open a new terminal, send messages (topic is set to "spring.boot.kafka.stream.input"
 * bin/windows/kafka-console-producer.bat --topic spring.boot.kafka.stream.input --broker-list localhost:9092
 *
 */
@EnableKafka
@EnableKafkaStreams
@SpringBootApplication
@EnableScheduling
public class KafkaMessageApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaMessageApiApplication.class, args);
	}

	/*
	@Bean
	public KafkaStreams kafkaStreams() {
		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-streams-app");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

		StreamsBuilder builder = new StreamsBuilder();
		KStream<String, String> inputTopicStream = builder.stream("input_topic");

		inputTopicStream.mapValues(value -> {
			// Process the message
			return "Processed: " + value;
		}).to("output_topic");

		KafkaStreams streams = new KafkaStreams(builder.build(), props);
		streams.start();

		Runtime.getRuntime().addShutdownHook(new Thread(streams::close));

		return streams;
	}

	 */

	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String,SendMessageDTO> kafkaTemplate){
		return args -> {
		kafkaTemplate.send("algot_test", new SendMessageDTO(1L, 2L, "tjo", LocalDate.now()));
		};
	}
}
