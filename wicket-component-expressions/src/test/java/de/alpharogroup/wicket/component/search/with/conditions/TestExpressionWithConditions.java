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
package de.alpharogroup.wicket.component.search.with.conditions;

import static org.junit.Assert.assertEquals;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import de.alpharogroup.wicket.component.search.ComponentExpression;

public class TestExpressionWithConditions
{

	/*
	 * one1 -> two1 -> two2 -> three1 -> four1
	 */

	TestExpressionWithConditionsPanel parent;
	WebMarkupContainer one1;
	WebMarkupContainer two1;
	WebMarkupContainer two2;
	WebMarkupContainer three1;
	WebMarkupContainer four1;

	WicketTester tester;

	@Before
	public void setup()
	{

		tester = new WicketTester();

		parent = new TestExpressionWithConditionsPanel("parent");
		one1 = parent.getOne1();
		two1 = parent.getTwo1();
		two2 = parent.getTwo2();
		three1 = parent.getThree1();
		four1 = parent.getFour1();

		tester.startComponentInPage(parent);
	}

	@Test
	public void testAnd()
	{
		assertEquals(
			1,
			ComponentExpression.findAllComponents(parent,
				"one1[visible=true and enabled = true]:two1[visible=true & enabled=true]").size());

		two1.setVisible(false);
		assertEquals(
			1,
			ComponentExpression.findAllComponents(parent,
				"one1[visible=true and enabled = true]:two1[visible=false & enabled=true]").size());
	}

	@Test
	public void testWhitespaceParsing()
	{
		assertEquals(1,
			ComponentExpression.findAllComponents(parent, "one1[visible=true]:two1[visible=true]")
				.size());
		assertEquals(
			1,
			ComponentExpression.findAllComponents(parent,
				"one1 [ visible  = true   ]:two1  [enabled=true]").size());
	}


	@Test
	public void testWildcards()
	{

		two1.setVisible(false);
		assertEquals(4, ComponentExpression.findAllComponents(parent, "**[visible=true]").size());

		assertEquals(1, ComponentExpression.findAllComponents(parent, "**[visible=false]").size());

		assertEquals(2,
			ComponentExpression.findAllComponents(parent, "**[visible=true]:two2:**[visible=true]")
				.size());
	}

	@Test
	public void testWithEnabled()
	{
		assertEquals(1,
			ComponentExpression.findAllComponents(parent, "one1[enabled=true]:two1[enabled=true]")
				.size());
	}

	@Test
	public void testWithEnabled2()
	{
		two1.setEnabled(false);
		assertEquals(1,
			ComponentExpression.findAllComponents(parent, "one1[enabled=true]:two1[enabled=false]")
				.size());
	}

	@Test
	public void testWithEnabledHigher()
	{

		one1.setEnabled(false);

		assertEquals(0,
			ComponentExpression.findAllComponents(parent, "one1[enabled=false]:two1[enabled=true]")
				.size());

		assertEquals(1,
			ComponentExpression
				.findAllComponents(parent, "one1[enabled=false]:two1[enabled=false]").size());
	}

	@Test
	public void testWithVisible()
	{
		assertEquals(1,
			ComponentExpression.findAllComponents(parent, "one1[visible=true]:two1[visible=true]")
				.size());

		two1.setVisible(false);
		assertEquals(1,
			ComponentExpression.findAllComponents(parent, "one1[visible=true]:two1[visible=false]")
				.size());
	}

	@Test
	public void testWithVisibleHigher()
	{

		one1.setVisible(false);

		assertEquals(0,
			ComponentExpression.findAllComponents(parent, "one1[visible=false]:two1[visible=true]")
				.size());

		assertEquals(1,
			ComponentExpression
				.findAllComponents(parent, "one1[visible=false]:two1[visible=false]").size());
	}

}
