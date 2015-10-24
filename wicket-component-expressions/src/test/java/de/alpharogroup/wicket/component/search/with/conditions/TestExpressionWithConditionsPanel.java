package de.alpharogroup.wicket.component.search.with.conditions;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;

public class TestExpressionWithConditionsPanel extends Panel
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	WebMarkupContainer one1;
	WebMarkupContainer two1;
	WebMarkupContainer two2;
	WebMarkupContainer three1;
	WebMarkupContainer four1;

	public TestExpressionWithConditionsPanel(final String id)
	{
		super(id);
		one1 = new WebMarkupContainer("one1");
		two1 = new WebMarkupContainer("two1");
		two2 = new WebMarkupContainer("two2");
		three1 = new WebMarkupContainer("three1");
		four1 = new WebMarkupContainer("four1");

		add(one1.add(two1, two2.add(three1.add(four1))));
	}

	public WebMarkupContainer getFour1()
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

	public WebMarkupContainer getTwo1()
	{
		return two1;
	}

	public WebMarkupContainer getTwo2()
	{
		return two2;
	}

}
