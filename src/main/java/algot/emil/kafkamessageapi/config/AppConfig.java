package algot.emil.kafkamessageapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.CopyOnWriteArrayList;

@Configuration
public class AppConfig {

    @Bean
    public CopyOnWriteArrayList<SseEmitter> sseEmitterList() {
        return new CopyOnWriteArrayList<>();
    }
}
