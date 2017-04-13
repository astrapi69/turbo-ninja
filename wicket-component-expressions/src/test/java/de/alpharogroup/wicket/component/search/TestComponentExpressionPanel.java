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
package de.alpharogroup.wicket.component.search;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;

public class TestComponentExpressionPanel extends Panel
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
	WMCSubClass two1;
	WebMarkupContainer two2;
	WebMarkupContainer three1;
	WMCSubClass four1;

	public TestComponentExpressionPanel(final String id)
	{
		super(id);
		one1 = new WebMarkupContainer("one1");
		two1 = new WMCSubClass("two1");
		two2 = new WebMarkupContainer("two2");
		three1 = new WebMarkupContainer("three1");
		four1 = new WMCSubClass("four1");

		add(one1.add(two1, two2.add(three1.add(four1))));

	}

	public WMCSubClass getFour1()
	{
		return four1;
	}

	public WebMarkupContainer getOne1()
	{
		return one1;
	}

	public WebMarkupContainer getThree1()
	{
		return three1;
	}

	public WMCSubClass getTwo1()
	{
		return two1;
	}

	public WebMarkupContainer getTwo2()
	{
		return two2;
	}

}
