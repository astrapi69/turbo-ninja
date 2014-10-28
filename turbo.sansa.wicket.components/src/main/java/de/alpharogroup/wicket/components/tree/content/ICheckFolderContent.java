package de.alpharogroup.wicket.components.tree.content;

import org.apache.wicket.ajax.AjaxRequestTarget;

import de.alpharogroup.wicket.components.tree.model.Content;


/**
 * The Interface ICheckFolderContent.
 * 
 * @param <T>
 *            the generic type
 * @author Asterios Raptis
 */
public interface ICheckFolderContent<T> extends Content<T> {

	/**
	 * Check.
	 * 
	 * @param content
	 *            the content
	 * @param check
	 *            the check
	 * @param target
	 *            the target
	 */
	void check(final T content, final boolean check,
			final AjaxRequestTarget target);

	/**
	 * Checks if is checked.
	 * 
	 * @param content
	 *            the content
	 * @return true, if is checked
	 */
	boolean isChecked(final T content);

}