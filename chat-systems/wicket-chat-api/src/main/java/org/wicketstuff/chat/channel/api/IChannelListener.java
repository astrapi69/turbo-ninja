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
package org.wicketstuff.chat.channel.api;

import java.io.Serializable;
import java.util.EventListener;
import java.util.Map;

/**
 * Implementations of this interface can be notified of events triggered by a
 * {@link IChannelPublisher}
 * <p>
 * Implementation example:
 * 
 * <pre>
 * channelService.addChannelListener(this, &quot;channel&quot;, new IChannelListener()
 * {
 * 	public void onEvent(String channel, Map datas, IChannelTarget target)
 * 	{
 * 		target.addComponent(aComponent);
 * 	}
 * });
 * </pre>
 *
 * @author Vincent Demay
 * @author Xavier Hanin
 *
 * @see IChannelService
 */
public interface IChannelListener extends Serializable, EventListener
{
	/**
	 * Method called when an event is triggered on a channel.
	 *
	 * Implementations usually use the {@link IChannelTarget} to update the web page according to
	 * the event triggered.
	 *
	 * @param channel
	 *            channel which be used to listen to event
	 * @param datas
	 *            data sent by the event
	 * @param target
	 *            see {@link IChannelTarget}
	 */
	public abstract void onEvent(final String channel, final Map<String, String> datas,
		final IChannelTarget target);
}
