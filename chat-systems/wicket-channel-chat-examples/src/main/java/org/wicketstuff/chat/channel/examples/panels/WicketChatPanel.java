package org.wicketstuff.chat.channel.examples.panels;

import org.wicketstuff.chat.channel.api.IChannelService;
import org.wicketstuff.chat.channel.examples.application.WicketApplication;

public class WicketChatPanel extends WicketAbstractChatPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WicketChatPanel(String id, final String channel) {
		super(id, channel);
	}

	protected IChannelService getChannelService() {		
		return ((WicketApplication) WicketApplication.get()).getTimerChannelService();
	}

}
