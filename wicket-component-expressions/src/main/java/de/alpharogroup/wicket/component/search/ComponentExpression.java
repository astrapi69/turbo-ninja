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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;

/**
 * This class provides several methods for the search of wicket {@link org.apache.wicket.Component}
 * s.
 */
public class ComponentExpression
{

	/**
	 * Simple pojo class that holds an attribute(visible or enabled) and a condition.
	 */
	private static final class Condition
	{
		/**
		 * Simple enum that holds two states of an attribute.
		 */
		private enum Attr
		{
			/**
			 * The visible state.
			 */
			VISIBLE,
			/**
			 * The enabled state.
			 */
			ENABLED;
		}

		/**
		 * The state of the condition.
		 */
		private final Attr attr;
		/**
		 * The flag condition.
		 */
		private final boolean condition;

		/**
		 * Default constructor.
		 * 
		 * @param attr
		 *            The state of the condition.
		 * @param condition
		 *            The flag condition.
		 */
		public Condition(final Attr attr, final boolean condition)
		{
			this.attr = attr;
			this.condition = condition;
		}

		/**
		 * Gets the state of the condition.
		 * 
		 * @return the state of the condition.
		 */
		public Attr getAttr()
		{
			return attr;
		}

		/**
		 * Gets the flag condition.
		 * 
		 * @return the flag condition.
		 */
		public boolean getCondition()
		{
			return condition;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String toString()
		{
			return String.format("Condition [attr=%s, condition=%s]", attr, condition);
		}
	}

	private static final String ANY_COMPONENT_MATCHER = "*";

	private static final String ANY_COMPONENT_RECURSIVE_MATCHER = "**";

	/**
	 * Evaluates the given {@link org.apache.wicket.Component} with the given collection of
	 * {@link Condition}s.
	 * 
	 * @param conditions
	 *            a collection of {@link Condition}s to evaluate.
	 * @param component
	 *            the {@link org.apache.wicket.Component} that the evaluation will be executed.
	 * @return true if the evaluation is successful otherwise false.
	 */
	private static final boolean evaluateConditions(final Collection<Condition> conditions,
		final Component component)
	{
		boolean result = true;
		for (final Condition condition : conditions)
		{
			switch (condition.getAttr())
			{
				case ENABLED : {
					result = result
						&& (component.isEnabledInHierarchy() == condition.getCondition());
					break;
				}
				case VISIBLE : {
					result = result
						&& (component.isVisibleInHierarchy() == condition.getCondition());
				}
			}
			if (!result)
			{
				break;
			}
		}
		return result;
	}

	/**
	 * Search for all {@link org.apache.wicket.Component}s with the given expression in the given
	 * parent with a {@link org.apache.wicket.Component} class type restriction.
	 *
	 * @param parent
	 *            the parent that will be the start point to search.
	 * @param expression
	 *            the expression for search.
	 * @return all found {@link org.apache.wicket.Component}s in a {@link java.util.List} with the
	 *         given expression in the given parent with the given class type restriction or an
	 *         empty {@link java.util.List} if nothing is found.
	 */
	public static List<Component> findAllComponents(final Component parent, final String expression)
	{
		return findAllComponents(parent, expression, Component.class);

	}

	/**
	 * Search for all {@link org.apache.wicket.Component}s with the given expression in the given
	 * parent with the given class type restriction.
	 *
	 * @param parent
	 *            the parent that will be the start point to search.
	 * @param expression
	 *            the expression for search.
	 * @param typeRestriction
	 *            the class type restriction for the search.
	 * @return all found {@link org.apache.wicket.Component}s in a {@link java.util.List} with the
	 *         given expression in the given parent with the given class type restriction or an
	 *         empty {@link java.util.List} if nothing is found.
	 */
	public static List<Component> findAllComponents(final Component parent,
		final String expression, final Class<? extends Component> typeRestriction)
	{
		if (expression == null || expression.equals(""))
		{
			return Collections.emptyList();
		}
		return findComponent(
			parent,
			new LinkedList<String>(Arrays.asList(expression.split(Character
				.toString(Component.PATH_SEPARATOR)))), typeRestriction);
	}

	/**
	 * Search for all {@link org.apache.wicket.Component}s with the given list of expressions in the
	 * given parent with the given class type restriction.
	 *
	 * @param parent
	 *            the parent that will be the start point to search.
	 * @param expressionListIn
	 *            the list with the expressions for the search.
	 * @param typeRestriction
	 *            the class type restriction for the search.
	 * @return all found {@link org.apache.wicket.Component}s in a {@link java.util.List} with the
	 *         given expression in the given parent with the given class type restriction or an
	 *         empty {@link java.util.List} if nothing is found.
	 */
	private static List<Component> findComponent(final Component parent,
		final LinkedList<String> expressionListIn, final Class<? extends Component> typeRestriction)
	{

		final LinkedList<String> expressionList = new LinkedList<String>(expressionListIn);

		if (expressionList.isEmpty())
		{
			if (typeRestriction.isAssignableFrom(parent.getClass()))
			{
				return Arrays.asList(parent);
			}
			else
			{
				return Collections.emptyList();
			}
		}
		else
		{
			final String rawFirst = expressionList.getFirst();
			final Collection<Condition> conditions = parseConditions(rawFirst);
			final String first = rawFirst.replaceAll("\\s*\\[.*\\]\\s*", "");

			if (!first.equals(ANY_COMPONENT_RECURSIVE_MATCHER))
			{
				expressionList.removeFirst();
				final List<Component> allMatches = getChild(parent, first, conditions);
				if (allMatches.isEmpty())
				{
					return Collections.emptyList();
				}
				else
				{
					final List<Component> finallyMatchedComponents = new ArrayList<Component>();
					for (final Component aMatch : allMatches)
					{
						finallyMatchedComponents.addAll(findComponent(aMatch, expressionList,
							typeRestriction));
					}
					return finallyMatchedComponents;
				}
			}
			else if (expressionList.size() == 1)
			{
				final List<Component> allMatches = new ArrayList<Component>();
				if (parent instanceof MarkupContainer)
				{
					for (final Component aMatch : getAllChildren((MarkupContainer)parent))
					{
						if (typeRestriction.isAssignableFrom(aMatch.getClass())
							&& evaluateConditions(conditions, aMatch))
						{
							allMatches.add(aMatch);
						}
						allMatches.addAll(findComponent(aMatch, expressionList, typeRestriction));
					}
					return allMatches;
				}
				else
				{
					return Collections.emptyList();
				}
			}
			else
			{
				final List<Component> allMatches = new ArrayList<Component>();
				final LinkedList<String> fake = new LinkedList<String>();
				fake.add(ANY_COMPONENT_RECURSIVE_MATCHER);
				final List<Component> allPotentialParents = findComponent(parent, fake,
					Component.class);
				expressionList.removeFirst();
				for (final Component aParent : allPotentialParents)
				{
					if (evaluateConditions(conditions, aParent))
					{
						allMatches.addAll(findComponent(aParent, expressionList, typeRestriction));
					}
				}
				return allMatches;
			}
		}
	}

	/**
	 * Search for the first {@link org.apache.wicket.Component} with the given expression in the
	 * given parent. Internally this method calls the
	 * {@link ComponentExpression#findAllComponents(org.apache.wicket.Component, String)} that
	 * returns a list and if this list is not empty the first element will be returned.
	 * 
	 * @param parent
	 *            the parent that will be the start point to search.
	 * @param expression
	 *            the expression for search.
	 * @return the first {@link org.apache.wicket.Component} with the given expression in the given
	 *         parent or null if nothing is found.
	 */
	public static Component findComponent(final Component parent, final String expression)
	{
		final List<Component> results = findAllComponents(parent, expression);
		if (results.isEmpty())
		{
			return null;
		}
		else
		{
			return results.get(0);
		}
	}

	/**
	 * Search for the first {@link org.apache.wicket.Component} with the given expression in the
	 * given parent with the given class type restriction. Internally this method calls the
	 * {@link ComponentExpression#findAllComponents(org.apache.wicket.Component, String, Class)}
	 * that returns a list and if this list is not empty the first element will be returned.
	 * 
	 * @param parent
	 *            the parent that will be the start point to search.
	 * @param expression
	 *            the expression for search.
	 * @param typeRestriction
	 *            the class type restriction for the search.
	 * @return the first {@link org.apache.wicket.Component} with the given expression in the given
	 *         parent or null if nothing is found.
	 */
	public static Component findComponent(final Component parent, final String expression,
		final Class<? extends Component> typeRestriction)
	{
		final List<Component> results = findAllComponents(parent, expression, typeRestriction);
		if (results.isEmpty())
		{
			return null;
		}
		else
		{
			return results.get(0);
		}
	}

	/**
	 * Search for all child {@link org.apache.wicket.Component}s from the given parent.
	 * 
	 * @param parent
	 *            The parent
	 * @return all child {@link org.apache.wicket.Component}s from the given parent.
	 */
	private static List<Component> getAllChildren(final MarkupContainer parent)
	{
		final List<Component> children = new ArrayList<Component>();
		final Iterator<? extends Component> iter = parent.iterator();
		while (iter.hasNext())
		{
			children.add(iter.next());
		}
		return children;
	}

	/**
	 * Search for all child {@link org.apache.wicket.Component}s with the given expression and the
	 * given parent with the given collection of {@link Condition}s as filter.
	 * 
	 * @param parent
	 *            the parent that will be the start point to search.
	 * @param expression
	 *            the expression for search.
	 * @param conditions
	 *            a collection of {@link Condition}s to filter.
	 * @return all found child {@link org.apache.wicket.Component}s in a {@link java.util.List}.
	 */
	private static List<Component> getChild(final Component parent, final String expression,
		final Collection<Condition> conditions)
	{

		if (parent instanceof MarkupContainer)
		{
			final MarkupContainer parentContainer = (MarkupContainer)parent;
			if (expression.equals(ANY_COMPONENT_MATCHER))
			{
				final List<Component> allChildren = getAllChildren(parentContainer);
				final List<Component> allChildrenMatchingCondition = new ArrayList<Component>();
				for (final Component child : allChildren)
				{
					if (child != null && evaluateConditions(conditions, child))
					{
						allChildrenMatchingCondition.add(child);
					}
				}
				return allChildrenMatchingCondition;
			}
			else
			{

				final Component comp = parentContainer.get(expression);

				if (comp == null || !evaluateConditions(conditions, comp))
				{
					return Collections.emptyList();
				}
				else
				{
					return Arrays.asList(comp);
				}
			}
		}
		else
		{
			return Collections.emptyList();
		}
	}

	/**
	 * Parses the given expression to a collection of {@link Condition}s.
	 * 
	 * @param expression
	 *            the expression to parse.
	 * @return a collection of {@link Condition}s.
	 */
	private static final Collection<Condition> parseConditions(final String expression)
	{
		final List<Condition> parsedConditions = new ArrayList<Condition>();
		final Pattern pattern = Pattern.compile(".*\\[\\s*(.*?)\\s*\\]\\w*",
			Pattern.CASE_INSENSITIVE);
		final Matcher matcher = pattern.matcher(expression);
		if (matcher.matches())
		{
			final String conditionString = matcher.group(1);
			final String[] conditions = conditionString.split("\\s*(and|&)\\s*");
			for (final String aCondition : conditions)
			{
				Condition.Attr attr = null;
				Boolean condition = null;
				String[] parts = null;
				if (aCondition.contains("="))
				{
					parts = aCondition.split("\\s*=\\s*");
					if (parts.length == 2)
					{
						if (parts[0].toUpperCase().equals(Condition.Attr.VISIBLE.toString()))
						{
							attr = Condition.Attr.VISIBLE;
						}
						else if (parts[0].toUpperCase().equals(Condition.Attr.ENABLED.toString()))
						{
							attr = Condition.Attr.ENABLED;
						}
						if (parts[1].toUpperCase().equals(Boolean.TRUE.toString().toUpperCase()))
						{
							condition = Boolean.TRUE;
						}
						else if (parts[1].toUpperCase().equals(
							Boolean.FALSE.toString().toUpperCase()))
						{
							condition = Boolean.FALSE;
						}
						if (attr != null && condition != null)
						{
							parsedConditions.add(new Condition(attr, condition));
						}
					}
				}

			}
		}
		return parsedConditions;
	}

}
