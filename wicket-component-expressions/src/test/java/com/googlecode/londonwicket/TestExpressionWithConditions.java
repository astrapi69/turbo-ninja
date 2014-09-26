package com.googlecode.londonwicket;

import static org.junit.Assert.assertEquals;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class TestExpressionWithConditions {

	/*
	 * one1
	 * -> two1
	 * -> two2
	 *  -> three1
	 *   -> four1
	 */

	WebMarkupContainer parent;
	WebMarkupContainer one1;
	WebMarkupContainer two1;
	WebMarkupContainer two2;
	WebMarkupContainer three1;
	WebMarkupContainer four1;

	WicketTester tester;
	@Before
	public void setup() {

		tester = new WicketTester();

		parent = new WebMarkupContainer("parent");
		one1 = new WebMarkupContainer("one1");
		two1 = new WebMarkupContainer("two1");
		two2 = new WebMarkupContainer("two2");
		three1 = new WebMarkupContainer("three1");
		four1 = new WebMarkupContainer("four1");

		parent.add(one1.add(two1, two2.add(three1.add(four1))));

		tester.startComponent(parent);
	}

	@Test
	public void testWithEnabled(){
		assertEquals(1, ComponentExpression.findAllComponents(parent,"one1[enabled=true]:two1[enabled=true]").size());
	}
	@Test
	public void testWithEnabled2(){
		two1.setEnabled(false);
		assertEquals(1, ComponentExpression.findAllComponents(parent,"one1[enabled=true]:two1[enabled=false]").size());
	}


	@Test
	public void testWithEnabledHigher(){

		one1.setEnabled(false);

		assertEquals(0, ComponentExpression.findAllComponents(parent,"one1[enabled=false]:two1[enabled=true]").size());

		assertEquals(1, ComponentExpression.findAllComponents(parent,"one1[enabled=false]:two1[enabled=false]").size());
	}

	@Test
	public void testWithVisible(){
		assertEquals(1, ComponentExpression.findAllComponents(parent,"one1[visible=true]:two1[visible=true]").size());

		two1.setVisible(false);
		assertEquals(1, ComponentExpression.findAllComponents(parent,"one1[visible=true]:two1[visible=false]").size());
	}

	@Test
	public void testWithVisibleHigher(){

		one1.setVisible(false);

		assertEquals(0, ComponentExpression.findAllComponents(parent,"one1[visible=false]:two1[visible=true]").size());

		assertEquals(1, ComponentExpression.findAllComponents(parent,"one1[visible=false]:two1[visible=false]").size());
	}
	
	@Test
	public void testWhitespaceParsing(){
		assertEquals(1, ComponentExpression.findAllComponents(parent,"one1[visible=true]:two1[visible=true]").size());
		assertEquals(1, ComponentExpression.findAllComponents(parent,"one1 [ visible  = true   ]:two1  [enabled=true]").size());
	}
	
	@Test
	public void testAnd(){
		assertEquals(1, ComponentExpression.findAllComponents(parent,"one1[visible=true and enabled = true]:two1[visible=true & enabled=true]").size());
		
		two1.setVisible(false);
		assertEquals(1, ComponentExpression.findAllComponents(parent,"one1[visible=true and enabled = true]:two1[visible=false & enabled=true]").size());
	}
	
	@Test
	public void testWildcards(){
		
		two1.setVisible(false);
		assertEquals(4, ComponentExpression.findAllComponents(parent, "**[visible=true]").size());
		
		assertEquals(1, ComponentExpression.findAllComponents(parent, "**[visible=false]").size());
		
		assertEquals(2, ComponentExpression.findAllComponents(parent, "**[visible=true]:two2:**[visible=true]").size());
	}

}
