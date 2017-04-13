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
public interface ICheckFolderContent<T> extends Content<T>
{

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
	void check(final T content, final boolean check, final AjaxRequestTarget target);

	/**
	 * Checks if is checked.
	 * 
	 * @param content
	 *            the content
	 * @return true, if is checked
	 */
	boolean isChecked(final T content);

}