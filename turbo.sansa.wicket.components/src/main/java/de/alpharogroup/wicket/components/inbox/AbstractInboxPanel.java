package de.alpharogroup.wicket.components.inbox;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.alpharogroup.message.system.enums.MessageState;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;

/**
 * The Class AbstractInboxPanel.
 *
 * @author Asterios Raptis
 */
public abstract class AbstractInboxPanel extends Panel
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The messages panel. */
	private final AbstractMessagesPanel messagesPanel;

	/** The link for the send message. */
	private final Link<Void> sendMessage;

	/** The Label component. */
	protected final Label sendMessageLabel;

	/**
	 * Constructor for the AbstractInboxPanel.
	 *
	 * @param id
	 *            the id
	 */
	public AbstractInboxPanel(final String id)
	{
		super(id);
		add(messagesPanel = newMessagesPanel("inboxMessagePanel", MessageState.UNREPLIED));
		add(sendMessage = newSendMessage("sendMessage"));
		sendMessage
			.add(sendMessageLabel = newLabel("sendMessageLabel", newSendMessageLabelModel()));
	}

	/**
	 * Factory method for creating the Model of the Label of the send message link. This method is
	 * invoked in the constructor from the derived classes and can be overridden so users can
	 * provide their own version of the Model of the Label of the send message link.
	 *
	 * @return the Model of the Label of the send message link.
	 */
	protected IModel<String> newSendMessageLabelModel()
	{
		return ResourceModelFactory.newResourceModel("inbox.send.message.button.label", this);
	}

	/**
	 * Factory method for creating the Label of the send message link. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of Label of the send message link.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the label
	 */
	protected Label newLabel(final String id, final IModel<String> model)
	{
		final Label label = new Label(id, model);
		return label;
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
	 * Gets the link send message.
	 *
	 * @return the send message
	 */
	public Link<Void> getSendMessage()
	{
		return sendMessage;
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

	/**
	 * New send message.
	 *
	 * @param id
	 *            the id
	 * @return the link
	 */
	protected Link<Void> newSendMessage(final String id)
	{
		return new Link<Void>(id)
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				onSendMessage();
			}
		};
	}

	/**
	 * On send message.
	 */
	protected abstract void onSendMessage();

}
