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
