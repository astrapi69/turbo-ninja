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
package de.alpharogroup.wicket.component.search.on.list;

import java.util.Arrays;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

public class TestComponentExpressionOnListPanel extends Panel
{

	private static class WMCSubClass extends WebMarkupContainer
	{

		private static final long serialVersionUID = 1L;

		public WMCSubClass(final String id)
		{
			super(id);
		}

	}

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	WebMarkupContainer one1;
	WebMarkupContainer two1;
	WebMarkupContainer two2;
	WebMarkupContainer three1;

	ListView<Component> listView;

	public TestComponentExpressionOnListPanel(final String id)
	{
		super(id);
		one1 = new WebMarkupContainer("one1");
		two1 = new WMCSubClass("two1");
		two2 = new WebMarkupContainer("two2");
		three1 = new WebMarkupContainer("three1");

		two1.add(three1);

		listView = new ListView<Component>("listView", Arrays.asList(two1, two2))
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final ListItem<Component> item)
			{
				item.add(item.getModelObject());
			}
		};

		add(one1.add(listView));
	}

	public ListView<Component> getListView()
	{
		return listView;
	}

	public WebMarkupContainer getOne1()
	{
		return one1;
	}

	public WebMarkupContainer getThree1()
	{
		return three1;
	}

	public WebMarkupContainer getTwo1()
	{
		return two1;
	}

	public WebMarkupContainer getTwo2()
	{
		return two2;
	}

}
