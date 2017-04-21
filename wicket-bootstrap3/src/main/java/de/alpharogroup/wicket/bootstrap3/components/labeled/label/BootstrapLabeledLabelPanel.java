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
package de.alpharogroup.wicket.bootstrap3.components.labeled.label;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.components.labeled.label.LabeledLabelPanel;

/**
 * Convenience class for labeled Label for form uneditable components. Adds some bootstrap css.
 *
 * @param <T>
 *            the generic type
 */
public class BootstrapLabeledLabelPanel<T> extends LabeledLabelPanel<T>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new LabeledDateTextfieldPanel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the label model
	 */
	public BootstrapLabeledLabelPanel(final String id, final IModel<T> model,
		final IModel<String> labelModel)
	{
		super(id, model, labelModel);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		onLayout();
	}

	/**
	 * Callback method for layout this component and the child components of it.
	 */
	protected void onLayout()
	{
		getLabel().add(new AttributeAppender("class", "col-lg-5 col-md-5 col-sm-5 "));
		getViewableLabel().add(new AttributeAppender("class", "col-lg-5 col-md-5 col-sm-5 "));
	}
}
