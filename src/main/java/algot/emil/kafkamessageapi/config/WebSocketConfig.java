package algot.emil.kafkamessageapi.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * This method registers /ws as the WebSocket endpoint.
     * Clients will connect to this endpoint to establish a WebSocket connection.
     * setAllowedOriginPatterns("*") allows cross-origin requests,
     * and withSockJS() enables SockJS fallback options for browsers that don't support native WebSockets.
     *
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }

    /**
     * setApplicationDestinationPrefixes("/app") sets /app as the prefix for all messages bound for methods annotated with @MessageMapping.
     * enableSimpleBroker("/chatroom", "/user") enables a simple in-memory message broker for handling subscriptions and broadcasting messages. It will handle destinations prefixed with /chatroom and /user.
     * setUserDestinationPrefix("/user") sets the prefix used to identify user-specific queues. For example, if a user with ID 42 subscribes to /user/queue/updates, the application will handle this subscription at /user/42/queue/updates.
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/chatroom","/user");
        registry.setUserDestinationPrefix("/user");
    }
}