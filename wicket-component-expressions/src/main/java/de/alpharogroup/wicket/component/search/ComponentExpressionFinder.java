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

import java.util.List;

import org.apache.wicket.Component;

/**
 * The Class ComponentFinder.
 */
public final class ComponentExpressionFinder
{

	/**
	 * Finds all children from the given parent class. The result can be added to a ajax target so
	 * the components can be automatically updated.
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
	public static Component[] findAllChildrenFromParent(final Component childComponent,
		final Class<? extends Component> parentClass, final Class<? extends Component> childClass)
	{
		final Component parent = childComponent.findParent(parentClass);
		final List<Component> children = ComponentExpression.findAllComponents(parent, "**",
			childClass);
		return children.toArray(new Component[children.size()]);
	}

}