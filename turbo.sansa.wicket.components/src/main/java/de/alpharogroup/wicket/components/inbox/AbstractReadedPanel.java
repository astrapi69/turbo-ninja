package de.alpharogroup.wicket.components.inbox;

import org.apache.wicket.markup.html.panel.Panel;

import de.alpharogroup.message.system.enums.MessageState;

/**
 * The Class ContactedPanel.
 *
 * @author Asterios Raptis
 */
public abstract class AbstractReadedPanel extends Panel
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new contacted panel.
	 *
	 * @param id
	 *            the id
	 */
	public AbstractReadedPanel(final String id)
	{
		super(id);
		final AbstractMessagesPanel messagesPanel = newMessagesPanel("contactedMessagePanel",
			MessageState.CONTACTED);
		add(messagesPanel);
	}

	protected abstract AbstractMessagesPanel newMessagesPanel(final String id,
		final MessageState state);

}
