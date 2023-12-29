package algot.emil.kafkamessageapi;

import algot.emil.kafkamessageapi.DTO.SendMessageDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class KafkaMessageApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaMessageApiApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String,SendMessageDTO> kafkaTemplate){
		return args -> {
		kafkaTemplate.send("algot_test", new SendMessageDTO(1L, 2L, "tjo"));
		};
	}
}
