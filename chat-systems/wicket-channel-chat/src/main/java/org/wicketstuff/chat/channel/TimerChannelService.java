package org.wicketstuff.chat.channel;

import java.io.Serializable;
import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.util.time.Duration;
import org.wicketstuff.chat.channel.api.ChannelEvent;
import org.wicketstuff.chat.channel.api.EventStoreListener;
import org.wicketstuff.chat.channel.api.IChannelListener;
import org.wicketstuff.chat.channel.api.IChannelPublisher;
import org.wicketstuff.chat.channel.api.IChannelService;
import org.wicketstuff.chat.channel.api.IPushTarget;

public class TimerChannelService implements IChannelService, Serializable {
	private static final long serialVersionUID = 1L;

	private final Duration duration;
	private final IChannelPublisher publisher = new TimerChannelPublisher();

	public TimerChannelService(final Duration duration) {
		this.duration = duration;
	}

	public void addChannelListener(final Component component,
			final String listenerChannel, final IChannelListener listener) {
		
		final TimerChannelBehavior timerChannelBehavior = new TimerChannelBehavior(
				duration);
		final IPushTarget pushTarget = timerChannelBehavior.newPushTarget();
		component.add(timerChannelBehavior);
		EventStore.get().addEventStoreListener(new EventStoreListener() {
			public void eventTriggered(final String eventChannel,
					final Map<String, String> data) {
				if (eventChannel != null && !eventChannel.isEmpty() && listenerChannel != null && listenerChannel.equals(eventChannel)) {
					listener.onEvent(listenerChannel, data, pushTarget);
					pushTarget.trigger();
				}
			}
		});
	}

	public void publish(final ChannelEvent event) {
		publisher.publish(event);
	}

}
