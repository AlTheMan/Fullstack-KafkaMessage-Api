package algot.emil.kafkamessageapi.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.CopyOnWriteArrayList;

@CrossOrigin
@RestController
@RequestMapping({"/messages/", "messages/"})
public class KafkaSseController {

    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @GetMapping({"/stream", "/stream/"})
    public SseEmitter stream() {
        SseEmitter emitter = new SseEmitter();
        this.emitters.add(emitter);
        emitter.onCompletion(() -> this.emitters.remove(emitter));
        emitter.onTimeout(() -> this.emitters.remove(emitter));
        return emitter;
        /*
        emitter.onTimeout(() -> {
            emitter.complete();
            this.emitters.remove(emitter);
        }); */
    }

    public void sendMessageToClients(String message) {
        for (SseEmitter emitter : this.emitters) {
            try {
                emitter.send(SseEmitter.event().comment("heartbeat"));
                emitter.send(message);
            } catch (Exception e) {
                // handle exception
            }
        }
    }
}