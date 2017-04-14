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
package de.alpharogroup.wicket.components.tree.model;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.tree.AbstractTree;
import org.apache.wicket.model.IDetachable;
import org.apache.wicket.model.IModel;

/**
 * Generic Tree content factory.
 * 
 * @param <T>
 *            the generic type
 * @author Asterios Raptis
 */
public interface Content<T> extends IDetachable
{

	/**
	 * New content component.
	 * 
	 * @param id
	 *            the id
	 * @param tree
	 *            the tree
	 * @param model
	 *            the model
	 * @return the component
	 */
	Component newContentComponent(final String id, final AbstractTree<T> tree,
		final IModel<T> model);

}
