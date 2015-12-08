package de.alpharogroup.wicket.components.inbox;

import org.apache.wicket.markup.html.panel.Panel;

import de.alpharogroup.message.system.enums.MessageState;

/**
 * The Class AbstractMailSentPanel.
 *
 * @author Asterios Raptis
 */
public abstract class AbstractMailSentPanel extends Panel
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	/** The messages panel. */
	private final AbstractMessagesPanel messagesPanel;

	/**
	 * Instantiates a new contacted panel.
	 *
	 * @param id
	 *            the id
	 */
	public AbstractMailSentPanel(final String id)
	{
		super(id);
		add(messagesPanel = newMessagesPanel("messageSentPanel", null));
	}

	/**
	 * Gets the messages panel.
	 *
	 * @return the messages panel
	 */
	public AbstractMessagesPanel getMessagesPanel()
	{
		return messagesPanel;
	}


	/**
	 * New messages panel.
	 *
	 * @param id
	 *            the id
	 * @param state
	 *            the state
	 * @return the abstract messages panel
	 */
	protected abstract AbstractMessagesPanel newMessagesPanel(final String id,
		final MessageState state);

}
