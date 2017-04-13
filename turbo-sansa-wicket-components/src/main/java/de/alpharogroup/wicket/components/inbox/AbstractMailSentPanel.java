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
