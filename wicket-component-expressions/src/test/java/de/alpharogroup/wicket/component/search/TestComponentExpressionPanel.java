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
