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
package de.alpharogroup.wicket.bootstrap3.components.labeled.textfield;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.behaviors.JQueryJsAppenderBehavior;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledEmailTextFieldPanel;


public class BootstrapLabeledEmailTextfieldPanel<T, M> extends LabeledEmailTextFieldPanel<T, M>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	public BootstrapLabeledEmailTextfieldPanel(final String id, final IModel<M> model,
		final IModel<String> labelModel, final IModel<String> placeholderModel)
	{
		super(id, model, labelModel);
		// Add bootstrap css...
		add(new JQueryJsAppenderBehavior("wrap", "<div class=\"control-group\"></div>"));
		getLabelComponent().add(new AttributeAppender("class", " control-label"));
		getEmailTextField()
			.add(new JQueryJsAppenderBehavior("wrap", "<div class=\"controls\"></div>"));
		getEmailTextField().add(new AttributeAppender("class", " input-xlarge"));
		if (placeholderModel != null)
		{
			getEmailTextField().add(new AttributeAppender("placeholder", placeholderModel));
		}

	}
}
