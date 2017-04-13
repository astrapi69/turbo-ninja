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
package org.wicketstuff.chat.components.chat;

import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.wicketstuff.chat.channel.api.ChannelEvent;
import org.wicketstuff.chat.channel.api.IChannelListener;
import org.wicketstuff.chat.channel.api.IChannelService;
import org.wicketstuff.chat.channel.api.IChannelTarget;
import org.wicketstuff.chat.model.ChatroomModel;

/**
 * The Class BaseChatPanel.
 */
public abstract class BaseChatPanel extends Panel
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The form. */
	private final Form<ChatroomModel> form;

	/** The chat. */
	private final Component chat;

	/** The message. */
	private final TextField<String> message;

	/** The send. */
	private final AjaxButton send;

	/**
	 * Instantiates a new base chat panel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public BaseChatPanel(final String id, final IModel<ChatroomModel> model)
	{
		super(id, model);
		add(form = newForm("chatForm", new CompoundPropertyModel<ChatroomModel>(model)));
		form.add(chat = newChatLabel("chat"));
		form.add(message = newMessageTextField("message"));
		form.add(send = newSendAjaxButton("send", form));
		addChannelListener(model);
	}

	/**
	 * Adds the channel listener to the channel service.
	 *
	 * @param model
	 *            the model
	 */
	protected void addChannelListener(final IModel<ChatroomModel> model)
	{
		getChannelService().addChannelListener(this, model.getObject().getChannel(),
			new IChannelListener()
			{
				private static final long serialVersionUID = 1L;

				@Override
				public void onEvent(final String channel, final Map<String, String> datas,
					final IChannelTarget target)
				{
					final String message = datas.get("message");
					final String user = datas.get("user");
					String cssClassValue = " left";
					if (user.equals(model.getObject().getUser()))
					{
						cssClassValue = " right";
					}
					final String decoratedUser = "<div class=\"header\">"
						+ "<strong class=\"primary-font\">" + user + "</strong>" + "</div>";
					final String decoratedMessage = "<p>" + message + "</p>";
					final String talktext = "<div class=\"talktext\">" + decoratedMessage
						+ "</div>";
					final String bubble = "<div class=\"talk-bubble triangle-right round "
						+ cssClassValue + "-top\">" + talktext + "</div>";

					final String div = "<div class=\"chat-body clearfix\">" + decoratedUser
						+ bubble + "</div>";
					final String listItemElement = "<li class=\"" + cssClassValue + " clearfix\">"
						+ div + "</li>";

					target.appendJavaScript("var chathistory = document.getElementById('"
						+ chat.getMarkupId() + "');" + "chathistory.innerHTML += '"
						+ listItemElement + "';"
						+ "chathistory.scrollTop = chathistory.scrollHeight;");
				}
			});
	}

	/**
	 * Abstract method that subclasses must implement. Gets the channel service. This service should
	 * be returned from the application class.
	 *
	 * @return the channel service
	 */
	protected abstract IChannelService getChannelService();

	/**
	 * Gets the chat.
	 *
	 * @return the chat
	 */
	public Component getChat()
	{
		return chat;
	}

	/**
	 * Gets the form.
	 *
	 * @return the form
	 */
	public Form<ChatroomModel> getForm()
	{
		return form;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public TextField<String> getMessage()
	{
		return message;
	}

	/**
	 * Gets the send.
	 *
	 * @return the send
	 */
	public AjaxButton getSend()
	{
		return send;
	}

	/**
	 * Factory method for creating the Chat component. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * Chat component.
	 * 
	 * @param id
	 *            the id
	 * @return the label
	 */
	protected Component newChatLabel(final String id)
	{
		final Label label = new Label(id);
		label.setOutputMarkupId(true);
		return label;
	}

	/**
	 * New form.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the form
	 */
	protected Form<ChatroomModel> newForm(final String id, final IModel<ChatroomModel> model)
	{
		return new Form<ChatroomModel>(id, model);
	}

	/**
	 * Factory method for creating the TextField for the message. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a TextField for the message.
	 *
	 * @param id
	 *            the id
	 * @return the text field
	 */
	protected TextField<String> newMessageTextField(final String id)
	{
		final TextField<String> textField = new TextField<String>(id);
		textField.setOutputMarkupId(true);
		return textField;
	}

	/**
	 * Factory method for creating the AjaxButton. This method is invoked in the constructor from
	 * the derived classes and can be overridden so users can provide their own version of an
	 * AjaxButton.
	 *
	 * @param id
	 *            the id
	 * @param form
	 *            the form
	 * @return the ajax button
	 */
	protected AjaxButton newSendAjaxButton(final String id, final Form<ChatroomModel> form)
	{
		final AjaxButton ajaxButton = new AjaxButton(id, form)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				final ChatroomModel chatroom = ((ChatroomModel)form.getModelObject());
				// Update message
				final String currentMessage = chatroom.getMessage();
				if (currentMessage != null && !currentMessage.isEmpty())
				{
					// send an event to refesh the chat area
					final ChannelEvent event = new ChannelEvent(chatroom.getChannel());
					event.addData("user", chatroom.getUser());
					event.addData("message", currentMessage);
					getChannelService().publish(event);
					// clear message area add focus it
					target.appendJavaScript("document.getElementById('" + message.getMarkupId()
						+ "').value =''");
				}
				target.focusComponent(message);
			}
		};
		return ajaxButton;
	}

}
