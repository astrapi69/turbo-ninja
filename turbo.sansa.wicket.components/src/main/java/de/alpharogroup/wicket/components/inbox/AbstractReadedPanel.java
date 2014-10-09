package de.alpharogroup.wicket.components.inbox;

import message.system.enums.MessageState;

import org.apache.wicket.markup.html.panel.Panel;

/**
 * The Class ContactedPanel.
 * 
 * @author Asterios Raptis
 */
public abstract class AbstractReadedPanel extends Panel {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new contacted panel.
	 * 
	 * @param id
	 *            the id
	 * @param messageModels
	 *            the message models
	 */
	public AbstractReadedPanel(final String id) {
		super(id);
		final AbstractMessagesPanel messagesPanel = newMessagesPanel(
				"contactedMessagePanel", MessageState.CONTACTED);
		add(messagesPanel);
	}
	
	protected abstract AbstractMessagesPanel newMessagesPanel(final String id, final MessageState state);

}
