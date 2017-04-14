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
package de.alpharogroup.wicket.components.infringement.form;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.alpharogroup.user.management.application.models.InfringementModel;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.infringement.input.InfringementInputPanel;

public abstract class InfringementFormPanel extends Panel
{
	private static final long serialVersionUID = 1L;

	/** The button label. */
	private final Label buttonLabel;
	/** The form. */
	private final Form<?> form;

	private final Component infringementInputPanel;

	/** The submit button. */
	private final Button submitButton;

	public InfringementFormPanel(final String id, final IModel<InfringementModel> model)
	{
		super(id, model);
		add(form = newForm("form", model));
		form.add(
			infringementInputPanel = newInfringementInputPanel("infringementInputPanel", model));
		form.add(submitButton = newButton("submitButton"));
		submitButton
			.add(buttonLabel = newButtonLabel("buttonLabel", newButtonResourceKey(), "Send", this));
	}

	public Label getButtonLabel()
	{
		return buttonLabel;
	}

	public Form<?> getForm()
	{
		return form;
	}

	public Component getInfringementInputPanel()
	{
		return infringementInputPanel;
	}

	public Button getSubmitButton()
	{
		return submitButton;
	}

	protected Button newButton(final String id)
	{
		return new Button(id)
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit()
			{
				onFormSubmit();
			}
		};
	}

	/**
	 * Factory method for creating the Label. This method is invoked in the constructor from the
	 * derived classes and can be overridden so users can provide their own version of a Label.
	 *
	 * @param id
	 *            the id
	 * @param resourceKey
	 *            the resource key
	 * @param defaultValue
	 *            the default value
	 * @param component
	 *            the component
	 * @return the label
	 */
	protected Label newButtonLabel(final String id, final String resourceKey,
		final String defaultValue, final Component component)
	{
		final IModel<String> labelModel = ResourceModelFactory.newResourceModel(resourceKey,
			component, defaultValue);
		final Label label = new Label(id, labelModel);
		label.setOutputMarkupId(true);
		return label;
	}

	protected String newButtonResourceKey()
	{
		return "global.button.send.email.label";
	}

	/**
	 * New form.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the form
	 */
	@SuppressWarnings("unchecked")
	protected Form<?> newForm(final String id, final IModel<?> model)
	{
		return new Form<InfringementModel>(id, (IModel<InfringementModel>)model);
	}

	protected Component newInfringementInputPanel(final String id,
		final IModel<InfringementModel> model)
	{
		return new InfringementInputPanel(id, model);
	}

	protected abstract void onFormSubmit();

}
