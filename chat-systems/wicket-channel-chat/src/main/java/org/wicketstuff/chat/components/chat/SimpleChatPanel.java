package org.wicketstuff.chat.components.chat;

import org.apache.wicket.model.IModel;
import org.wicketstuff.chat.model.ChatroomModel;

public abstract class SimpleChatPanel extends BaseChatPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SimpleChatPanel(final String id, final IModel<ChatroomModel> model) {
		super(id, model);
	}

}
