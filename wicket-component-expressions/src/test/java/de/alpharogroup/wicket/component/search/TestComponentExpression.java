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
package de.alpharogroup.wicket.component.search;

import static org.junit.Assert.assertEquals;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import de.alpharogroup.wicket.component.search.with.conditions.TestExpressionWithConditionsPanel;

public class TestComponentExpression
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

	TestExpressionWithConditionsPanel parent;
	WebMarkupContainer one1;
	WebMarkupContainer two1;
	WebMarkupContainer two2;
	WebMarkupContainer three1;

	WebMarkupContainer four1;

	@Before
	public void setup()
	{

		final WicketTester tester = new WicketTester();

		parent = new TestExpressionWithConditionsPanel("parent");
		one1 = parent.getOne1();
		two1 = parent.getTwo1();
		two2 = parent.getTwo2();
		three1 = parent.getThree1();
		four1 = parent.getFour1();


		tester.startComponentInPage(parent);
	}

	@Test
	public void testNormalExpression()
	{

		assertEquals(two1, ComponentExpression.findComponent(parent, "one1:two1"));
		assertEquals(1, ComponentExpression.findAllComponents(parent, "one1:two1").size());

		assertEquals(three1, ComponentExpression.findComponent(parent, "one1:two2:three1"));
		assertEquals(1, ComponentExpression.findAllComponents(parent, "one1:two2:three1").size());

	}

	@Ignore
	@Test
	public void testNormalExpressionWithSubType()
	{

		assertEquals(1, ComponentExpression
			.findAllComponents(parent, "one1:two1", WebMarkupContainer.class).size());
		assertEquals(1,
			ComponentExpression.findAllComponents(parent, "one1:two1", WMCSubClass.class).size());
	}

	@Test
	public void testNormalExpressionWithSuperType()
	{

		assertEquals(1, ComponentExpression
			.findAllComponents(parent, "one1:two1", WebMarkupContainer.class).size());
	}

	@Test
	public void testStarExpression()
	{

		assertEquals(two1, ComponentExpression.findComponent(parent, "one1:*"));
		assertEquals(three1, ComponentExpression.findComponent(parent, "one1:*:three1"));
		assertEquals(2, ComponentExpression.findAllComponents(parent, "one1:*").size());
		assertEquals(two1, ComponentExpression.findAllComponents(parent, "one1:*").get(0));
		assertEquals(two2, ComponentExpression.findAllComponents(parent, "one1:*").get(1));

		assertEquals(three1, ComponentExpression.findComponent(parent, "one1:two2:*"));
		assertEquals(1, ComponentExpression.findAllComponents(parent, "one1:two2:*").size());

	}


	@Ignore
	@Test
	public void testStarExpressionWithSubType()
	{

		assertEquals(2, ComponentExpression
			.findAllComponents(parent, "one1:*", WebMarkupContainer.class).size());
		assertEquals(1,
			ComponentExpression.findAllComponents(parent, "one1:*", WMCSubClass.class).size());

	}

	@Test
	public void testStarExpressionWithSuperType()
	{

		assertEquals(2, ComponentExpression
			.findAllComponents(parent, "one1:*", WebMarkupContainer.class).size());

	}

	@Test
	public void testStarStarExpression1()
	{

		assertEquals(5, ComponentExpression.findAllComponents(parent, "**").size());

		assertEquals(two1, ComponentExpression.findComponent(parent, "one1:**"));
		assertEquals(4, ComponentExpression.findAllComponents(parent, "one1:**").size());
		assertEquals(two1, ComponentExpression.findAllComponents(parent, "one1:**").get(0));
		assertEquals(two2, ComponentExpression.findAllComponents(parent, "one1:**").get(1));
		assertEquals(three1, ComponentExpression.findAllComponents(parent, "one1:**").get(2));
		assertEquals(four1, ComponentExpression.findAllComponents(parent, "one1:**").get(3));
	}


	@Ignore
	@Test
	public void testStarStarExpression1WithSubType()
	{

		assertEquals(5,
			ComponentExpression.findAllComponents(parent, "**", WebMarkupContainer.class).size());
		assertEquals(2,
			ComponentExpression.findAllComponents(parent, "**", WMCSubClass.class).size());

	}

	@Test
	public void testStarStarExpression1WithSuperType()
	{

		assertEquals(5,
			ComponentExpression.findAllComponents(parent, "**", WebMarkupContainer.class).size());

	}

	@Test
	public void testStarStarExpression2()
	{
		assertEquals(1, ComponentExpression.findAllComponents(parent, "one1:**:three1").size());
		assertEquals(three1, ComponentExpression.findComponent(parent, "one1:**:three1"));
		assertEquals(1, ComponentExpression.findAllComponents(parent, "one1:**:four1").size());
		assertEquals(four1, ComponentExpression.findComponent(parent, "one1:**:four1"));
	}

	@Test
	public void testStarStarExpression2WithSubType()
	{
		assertEquals(1, ComponentExpression
			.findAllComponents(parent, "one1:**:three1", WebMarkupContainer.class).size());
		assertEquals(0, ComponentExpression
			.findAllComponents(parent, "one1:**:three1", WMCSubClass.class).size());
	}

	@Test
	public void testStarStarExpression2WithSuperType()
	{
		assertEquals(1, ComponentExpression
			.findAllComponents(parent, "one1:**:three1", WebMarkupContainer.class).size());
	}

	@Test
	public void testStarStarExpression3()
	{
		assertEquals(0, ComponentExpression.findAllComponents(parent, "**:two1:**:four1").size());
		assertEquals(1, ComponentExpression.findAllComponents(parent, "**:two2:**:four1").size());
		assertEquals(four1, ComponentExpression.findComponent(parent, "**:two2:**:four1"));
	}


	@Ignore
	@Test
	public void testStarStarExpression3WithSubType()
	{
		assertEquals(0, ComponentExpression
			.findAllComponents(parent, "**:two1:**:four1", WebMarkupContainer.class).size());
		assertEquals(1, ComponentExpression
			.findAllComponents(parent, "**:two2:**:four1", WebMarkupContainer.class).size());

		assertEquals(0, ComponentExpression
			.findAllComponents(parent, "**:two1:**:four1", WMCSubClass.class).size());
		assertEquals(1, ComponentExpression
			.findAllComponents(parent, "**:two2:**:four1", WMCSubClass.class).size());

	}

	@Test
	public void testStarStarExpression3WithSuperType()
	{
		assertEquals(0, ComponentExpression
			.findAllComponents(parent, "**:two1:**:four1", WebMarkupContainer.class).size());
		assertEquals(1, ComponentExpression
			.findAllComponents(parent, "**:two2:**:four1", WebMarkupContainer.class).size());
	}

}
