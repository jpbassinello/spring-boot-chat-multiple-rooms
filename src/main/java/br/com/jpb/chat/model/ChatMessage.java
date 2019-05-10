package br.com.jpb.chat.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
	private MessageType type;
	private String content;
	private String sender;
	private LocalDateTime dateTime = LocalDateTime.now();

	public enum MessageType {
		CHAT,
		JOIN,
		LEAVE,
		TYPING
	}

}
