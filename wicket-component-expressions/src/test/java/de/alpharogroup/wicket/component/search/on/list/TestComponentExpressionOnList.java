/*
 *    Copyright 2009 Richard Wilkinson

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package de.alpharogroup.wicket.component.search.on.list;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import de.alpharogroup.wicket.component.search.ComponentExpression;

public class TestComponentExpressionOnList
{

	/*
	 * one1 -> two1 -> two2 -> three1 -> four1
	 */

	private static class WMCSubClass extends WebMarkupContainer
	{

		private static final long serialVersionUID = 1L;

		public WMCSubClass(final String id)
		{
			super(id);
		}

	}

	TestComponentExpressionOnListPanel parent;
	WebMarkupContainer one1;
	WebMarkupContainer two1;
	WebMarkupContainer two2;
	WebMarkupContainer three1;

	ListView<Component> listView;

	@Before
	public void setup()
	{

		final WicketTester tester = new WicketTester();

		parent = new TestComponentExpressionOnListPanel("parent");
		one1 = parent.getOne1();
		two1 = parent.getTwo1();
		two2 = parent.getTwo2();
		three1 = parent.getThree1();


		two1.add(three1);

		listView = parent.getListView();

		tester.startComponentInPage(parent);
	}

	@Ignore
	@Test
	public void testListNoWildcard()
	{

		assertEquals(three1,
			ComponentExpression.findComponent(parent, "one1:listView:0:two1:three1"));

	}

	@Ignore
	@Test
	public void testListWithWildcard()
	{

		assertEquals(three1, ComponentExpression.findComponent(parent, "one1:**:three1"));

		assertEquals(three1, ComponentExpression.findComponent(parent, "**:listView:**:three1"));

		assertEquals(three1,
			ComponentExpression.findComponent(parent, "**:listView:**:two1:three1"));

		assertEquals(three1, ComponentExpression.findComponent(parent, "*:listView:*:two1:three1"));

		assertEquals(three1, ComponentExpression.findComponent(parent, "*:listView:**:three1"));
	}

}
