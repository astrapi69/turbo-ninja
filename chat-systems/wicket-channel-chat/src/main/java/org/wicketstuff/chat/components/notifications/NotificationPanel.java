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

	public Component getNotification()
	{
		return notification;
	}

	private final Duration duration;

	public Duration getDuration()
	{
		return duration;
	}

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
		add(notification = newNotificationLabel("notification",
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
