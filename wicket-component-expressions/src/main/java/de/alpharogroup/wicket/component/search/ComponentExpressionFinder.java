package de.alpharogroup.wicket.component.search;

import java.util.List;

import org.apache.wicket.Component;

/**
 * The Class ComponentFinder.
 */
public final class ComponentExpressionFinder {

	/**
	 * Finds all children from the given parent class. The result can be added
	 * to a ajax target so the components can be automatically updated.
	 * 
	 * @param childComponent
	 *            This should be a child component from the given parent class
	 * @param parentClass
	 *            The parent class
	 * @param childClass
	 *            The child class for the result
	 * 
	 * @return An array from the child components from the parameter childClass.
	 **/
	public static Component[] findAllChildrenFromParent(
			Component childComponent, Class<? extends Component> parentClass,
			Class<? extends Component> childClass) {
		Component parent = childComponent.findParent(parentClass);
		List<Component> children = ComponentExpression.findAllComponents(
				parent, "**", childClass);
		return children.toArray(new Component[children.size()]);
	}
	
}