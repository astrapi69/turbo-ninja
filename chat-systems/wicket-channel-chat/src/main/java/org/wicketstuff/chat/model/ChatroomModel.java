package org.wicketstuff.chat.model;

import java.io.Serializable;

public class ChatroomModel implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String channel;

	private String user;

	private String message;

	private String chat;

	private String receiver;

	public String getChannel()
	{
		return channel;
	}

	public String getChat()
	{
		return chat;
	}

	public String getMessage()
	{
		return message;
	}

	public String getReceiver()
	{
		return receiver;
	}

	public String getUser()
	{
		return user;
	}

	public void setChannel(final String channel)
	{
		this.channel = channel;
	}

	public void setChat(final String chat)
	{
		this.chat = chat;
	}

	public void setMessage(final String message)
	{
		this.message = message;
	}

	public void setReceiver(final String receiver)
	{
		this.receiver = receiver;
	}

	public void setUser(final String user)
	{
		this.user = user;
	}

}
