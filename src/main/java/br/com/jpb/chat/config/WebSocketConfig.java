package br.com.jpb.chat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Value("${broker.relay.host}")
	private String host;
	@Value("${broker.relay.port}")
	private int port;
	@Value("${broker.relay.client.login}")
	private String login;
	@Value("${broker.relay.client.password}")
	private String password;

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws")
				.setAllowedOrigins("*")
				.withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/chat");

		// Use this for enabling a Full featured broker like RabbitMQ
		registry.enableStompBrokerRelay("/topic")
				.setRelayHost(host)
				.setRelayPort(port)
				.setClientLogin(login)
				.setClientPasscode(password);
	}
}
