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

public class ComponentExpression {

	private static final String ANY_COMPONENT_MATCHER = "*";
	private static final String ANY_COMPONENT_RECURSIVE_MATCHER = "**";

	public static Component findComponent(Component parent, String expression) {
		List<Component> results = findAllComponents(parent, expression);
		if (results.isEmpty()) {
			return null;
		} else {
			return results.get(0);
		}
	}

	public static Component findComponent(Component parent, String expression, Class<? extends Component> typeRestriction) {
		List<Component> results = findAllComponents(parent, expression, typeRestriction);
		if (results.isEmpty()) {
			return null;
		} else {
			return results.get(0);
		}
	}

	public static List<Component> findAllComponents(Component parent, String expression) {
		return findAllComponents(parent, expression, Component.class);

	}

	public static List<Component> findAllComponents(Component parent, String expression, Class<? extends Component> typeRestriction) {
		if (expression == null || expression.equals("")) {
			return Collections.emptyList();
		}
		return findComponent(parent, new LinkedList<String>(Arrays.asList(expression.split(Character.toString(Component.PATH_SEPARATOR)))), typeRestriction);
	}

	private static List<Component> findComponent(Component parent, LinkedList<String> expressionListIn, Class<? extends Component> typeRestriction) {

		LinkedList<String> expressionList = new LinkedList<String>(expressionListIn);

		if (expressionList.isEmpty()) {
			if (typeRestriction.isAssignableFrom(parent.getClass())) {
				return Arrays.asList(parent);
			} else {
				return Collections.emptyList();
			}
		} else {
			String rawFirst = expressionList.getFirst();
			Collection<Condition> conditions = parseConditions(rawFirst);
			final String first = rawFirst.replaceAll("\\s*\\[.*\\]\\s*", "");

			if (!first.equals(ANY_COMPONENT_RECURSIVE_MATCHER)) {
				expressionList.removeFirst();
				List<Component> allMatches = getChild(parent, first, conditions);
				if (allMatches.isEmpty()) {
					return Collections.emptyList();
				} else {
					List<Component> finallyMatchedComponents = new ArrayList<Component>();
					for (Component aMatch : allMatches) {
						finallyMatchedComponents.addAll(findComponent(aMatch, expressionList, typeRestriction));
					}
					return finallyMatchedComponents;
				}
			} else if (expressionList.size() == 1) {
				List<Component> allMatches = new ArrayList<Component>();
				if (parent instanceof MarkupContainer) {
					for (Component aMatch : getAllChildren((MarkupContainer) parent)) {
						if (typeRestriction.isAssignableFrom(aMatch.getClass())
								&& evaluateConditions(conditions, aMatch)) {
							allMatches.add(aMatch);
						}
						allMatches.addAll(findComponent(aMatch, expressionList, typeRestriction));
					}
					return allMatches;
				} else {
					return Collections.emptyList();
				}
			} else {
				List<Component> allMatches = new ArrayList<Component>();
				LinkedList<String> fake = new LinkedList<String>();
				fake.add(ANY_COMPONENT_RECURSIVE_MATCHER);
				List<Component> allPotentialParents = findComponent(parent, fake, Component.class);
				expressionList.removeFirst();
				for (Component aParent : allPotentialParents) {
					if(evaluateConditions(conditions, aParent)){
						allMatches.addAll(findComponent(aParent, expressionList, typeRestriction));
					}
				}
				return allMatches;
			}
		}
	}

	private static List<Component> getChild(Component parent, String expression, Collection<Condition> conditions) {

		if (parent instanceof MarkupContainer) {
			MarkupContainer parentContainer = (MarkupContainer) parent;
			if (expression.equals(ANY_COMPONENT_MATCHER)) {
				List<Component> allChildren = getAllChildren(parentContainer);
				List<Component> allChildrenMatchingCondition = new ArrayList<Component>();
				for (Component child : allChildren) {
					if (child != null && evaluateConditions(conditions, child)) {
						allChildrenMatchingCondition.add(child);
					}
				}
				return allChildrenMatchingCondition;
			} else {

				Component comp = parentContainer.get(expression);

				if (comp == null || !evaluateConditions(conditions, comp)) {
					return Collections.emptyList();
				} else {
					return Arrays.asList(comp);
				}
			}
		} else {
			return Collections.emptyList();
		}
	}

	private static final boolean evaluateConditions(Collection<Condition> conditions, Component component) {
		boolean result = true;
		for (Condition condition : conditions) {
			switch (condition.getAttr()) {
			case ENABLED: {
				result = result && (component.isEnabledInHierarchy() == condition.getCondition());
				break;
			}
			case VISIBLE: {
				result = result && (component.isVisibleInHierarchy() == condition.getCondition());
			}
			}
			if (!result) {
				break;
			}
		}
		return result;
	}

	private static final Collection<Condition> parseConditions(String expression) {
		List<Condition> parsedConditions = new ArrayList<Condition>();
		Pattern pattern = Pattern.compile(".*\\[\\s*(.*?)\\s*\\]\\w*", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(expression);
		if (matcher.matches()) {
			String conditionString = matcher.group(1);
			String[] conditions = conditionString.split("\\s*(and|&)\\s*");
			for (String aCondition : conditions) {
				Condition.Attr attr = null;
				Boolean condition = null;
				String[] parts = null;
				if (aCondition.contains("=")) {
					parts = aCondition.split("\\s*=\\s*");
					if (parts.length == 2) {
						if (parts[0].toUpperCase().equals(Condition.Attr.VISIBLE.toString())) {
							attr = Condition.Attr.VISIBLE;
						} else if (parts[0].toUpperCase().equals(Condition.Attr.ENABLED.toString())) {
							attr = Condition.Attr.ENABLED;
						}
						if (parts[1].toUpperCase().equals(Boolean.TRUE.toString().toUpperCase())) {
							condition = Boolean.TRUE;
						} else if (parts[1].toUpperCase().equals(Boolean.FALSE.toString().toUpperCase())) {
							condition = Boolean.FALSE;
						}
						if (attr != null && condition != null) {
							parsedConditions.add(new Condition(attr, condition));
						}
					}
				}

			}
		}
		return parsedConditions;
	}

	private static final class Condition {
		private enum Attr {
			VISIBLE, ENABLED
		}

		private final Attr attr;
		private final boolean condition;

		public Condition(Attr attr, boolean condition) {
			this.attr = attr;
			this.condition = condition;
		}

		public Attr getAttr() {
			return attr;
		}

		public boolean getCondition() {
			return condition;
		}

		@Override
		public String toString() {
			return String.format("Condition [attr=%s, condition=%s]", attr, condition);
		}
	}

	private static List<Component> getAllChildren(MarkupContainer parent) {
		List<Component> children = new ArrayList<Component>();
		Iterator<? extends Component> iter = parent.iterator();
		while (iter.hasNext()) {
			children.add(iter.next());
		}
		return children;
	}

}
