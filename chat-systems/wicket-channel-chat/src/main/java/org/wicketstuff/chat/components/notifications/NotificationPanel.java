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
package org.wicketstuff.chat.components.notifications;

import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.time.Duration;
import org.wicketstuff.chat.channel.api.IChannelListener;
import org.wicketstuff.chat.channel.api.IChannelService;
import org.wicketstuff.chat.channel.api.IChannelTarget;
import org.wicketstuff.chat.model.ChatroomModel;

/**
 * The Class NotificationPanel can notify a message over the channelservice from the application.
 */
public abstract class NotificationPanel extends Panel
{


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private final Component notification;

	private final Duration duration;

	/**
	 * Instantiates a new notification panel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param duration
	 *            the duration
	 */
	public NotificationPanel(final String id, final IModel<ChatroomModel> model,
		final Duration duration)
	{
		super(id, model);
		this.duration = duration;
		add(this.notification = newNotificationLabel("notification",
			new PropertyModel<String>(model, "chat")));
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
				public void onEvent(final String channel, final Map<String, String> data,
					final IChannelTarget target)
				{
					final String pnotify = onGetJavaScript(data);
					target.appendJavaScript(pnotify);
				}
			});
	}

	/**
	 * Gets the channel service.
	 *
	 * @return the channel service
	 */
	protected abstract IChannelService getChannelService();

	public Duration getDuration()
	{
		return this.duration;
	}

	public Component getNotification()
	{
		return this.notification;
	}

	/**
	 * Factory method for creating the notification component. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a notification component.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the component
	 */
	protected Component newNotificationLabel(final String id, final IModel<String> model)
	{
		final Label label = new Label(id, model);
		label.setOutputMarkupId(true);
		return label;
	}

	protected abstract String onGetJavaScript(final Map<String, String> data);

}
