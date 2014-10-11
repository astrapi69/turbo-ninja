package org.wicketstuff.chat.model;

import java.io.Serializable;

public class ChatroomModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String channel;
	
	private String user;	

	private String message;
	
	private String chat;
	
	private String receiver;

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getChat() {
		return chat;
	}

	public void setChat(String chat) {
		this.chat = chat;
	}

}
