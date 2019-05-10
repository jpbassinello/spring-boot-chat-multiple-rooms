package br.com.jpb.chat.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import br.com.jpb.chat.model.ChatMessage;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatController {

	private final SimpMessageSendingOperations messagingTemplate;

	@MessageMapping("/sendMessage/room/{roomId}")
	@SendTo("/topic/{roomId}")
	public ChatMessage sendMessage(@DestinationVariable String roomId, @Payload ChatMessage chatMessage) {
		return chatMessage;
	}

	@MessageMapping("/addUser/room/{roomId}")
	@SendTo("/topic/{roomId}")
	public ChatMessage addUser(@DestinationVariable String roomId,
							   @Payload ChatMessage chatMessage,
							   SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		headerAccessor.getSessionAttributes().put("roomId", roomId);

		return chatMessage;
	}

}
