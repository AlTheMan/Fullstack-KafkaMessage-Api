package algot.emil.kafkamessageapi;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class HeartbeatScheduler {

    private final CopyOnWriteArrayList<SseEmitter> emitters;

    public HeartbeatScheduler(CopyOnWriteArrayList<SseEmitter> emitters) {
        this.emitters = emitters;
    }

    @Scheduled(fixedRate = 10000) // 10000 milliseconds = 10 seconds
    public void sendHeartbeat() {
        for (SseEmitter emitter : emitters) {
            try {
                System.out.println("sending heartbeat");
                emitter.send(SseEmitter.event().comment("heartbeat"));
            } catch (IOException e) {
                emitter.completeWithError(e);
            }
        }
    }
}

