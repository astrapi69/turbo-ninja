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
public interface Content<T> extends IDetachable {

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
	Component newContentComponent(String id, AbstractTree<T> tree, IModel<T> model);

}
