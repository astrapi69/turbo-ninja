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

import java.util.List;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByLink;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigatorLabel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;

import de.alpharogroup.message.system.entities.Messages;
import de.alpharogroup.message.system.enums.MessageState;
import de.alpharogroup.wicket.model.provider.MessagesProvider;

/**
 * The Class MessagesPanel.
 *
 * @author Asterios Raptis
 */
public abstract class AbstractMessagesPanel extends Panel
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new messages panel.
	 *
	 * @param id
	 *            the id
	 * @param state
	 *            the message state
	 */
	public AbstractMessagesPanel(final String id, final MessageState state)
	{
		super(id);
		final List<Messages> inboxMessages = getInboxMessages(state);


		final MessagesProvider dataProvider = new MessagesProvider(inboxMessages)
		{
			private static final long serialVersionUID = 1L;

			@Override
			public List<Messages> getData()
			{
				return getInboxMessages(state);
			}
		};
		dataProvider.setSort("sentDate", SortOrder.ASCENDING);

		final WebMarkupContainer noresults = new WebMarkupContainer("noresults");
		add(noresults);
		final WebMarkupContainer results = new WebMarkupContainer("results");
		add(results);
		// Set visibility...
		if (0 < dataProvider.size())
		{
			results.setVisible(true);
			noresults.setVisible(false);
			noresults.add(new AttributeAppender("class", "displaynone"));
		}
		else
		{
			results.setVisible(false);
			results.add(new AttributeAppender("class", "displaynone"));
			noresults.setVisible(true);
		}

		final DataView<Messages> dataView = new DataView<Messages>("dataView", dataProvider)
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final Item<Messages> item)
			{

				final Messages messageModel = item.getModelObject();
				item.setDefaultModel(new CompoundPropertyModel<Messages>(messageModel));
				item.add(new Label("state"));
				item.add(new Label("sender.username"));
				item.add(new Label("subject"));
				item.add(new Label("sentDate"));

				item.add(new Link<String>("readMessageLink")
				{
					/**
					 * The serialVersionUID.
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick()
					{
						onRead(messageModel);
					}
				});
				item.add(new Link<String>("deleteMessageLink")
				{
					/**
					 * The serialVersionUID.
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick()
					{
						onDelete(messageModel);
					}
				});
			}
		};
		dataView.setItemsPerPage(10);

		results.add(new NavigatorLabel("label", dataView));
		results.add(new PagingNavigator("navigator", dataView));

		results.add(new OrderByLink<String>("sortState", "state", dataProvider));
		results.add(new OrderByLink<String>("sortSenderUsername", "sender.username", dataProvider));
		results.add(new OrderByLink<String>("sortSubject", "subject", dataProvider));

		results.add(new OrderByLink<String>("sortSentDate", "sentDate", dataProvider));

		results.add(dataView);

	}

	protected abstract List<Messages> getInboxMessages(final MessageState state);

	protected abstract void onDelete(final Messages message);

	protected abstract void onRead(final Messages message);

}
