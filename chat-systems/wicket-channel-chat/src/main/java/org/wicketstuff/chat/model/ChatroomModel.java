/**
 * Copyright (C) 2015 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
