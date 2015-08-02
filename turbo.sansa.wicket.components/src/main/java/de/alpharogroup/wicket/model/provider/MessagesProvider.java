package de.alpharogroup.wicket.model.provider;

import java.util.List;

import message.system.model.Messages;
import de.alpharogroup.wicket.data.provider.AbstractSortableDataProvider;

/**
 * The Class MessagesProvider.
 * 
 * @author Asterios Raptis
 */
public class MessagesProvider extends AbstractSortableDataProvider<Messages, String>
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new messages provider.
	 * 
	 * @param data
	 *            the data
	 */
	public MessagesProvider(final List<Messages> data)
	{
		super(data);
	}

}