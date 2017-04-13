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
package de.alpharogroup.wicket.bootstrap2.components.labeled.textfield;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.components.labeled.textfield.LabeledTextFieldPanel;

public class BootstrapTextFieldPanel<T, M> extends LabeledTextFieldPanel<T, M>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	public BootstrapTextFieldPanel(final String id, final IModel<M> model,
		final IModel<String> labelModel)
	{
		super(id, model, labelModel);
		// Add bootstrap css...
		getLabelComponent().add(new AttributeAppender("class", " control-label"));
	}

}
