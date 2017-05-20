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
package de.alpharogroup.wicket.components.sign.up.search;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.address.book.application.model.LocationModel;
import de.alpharogroup.address.book.entities.Addresses;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.sign.up.SignupWithLocationModelBean;
import de.alpharogroup.wicket.model.dropdownchoices.TwoDropDownChoicesBean;
import lombok.Getter;

public abstract class SearchLocationFormPanel extends GenericPanel<LocationModel<Addresses>>
{
	private static final long serialVersionUID = 1L;
	private final AjaxButton submitButton;
	/** The button label. */
	@Getter
	private final Label buttonLabel;
	@Getter
	private final LocationFormComponentPanel locationPanel;

	@Getter
	private final TwoDropDownChoicesBean<String> twoDropDownChoicesBean;

	@Getter
	private final Form<?> form;

	public SearchLocationFormPanel(final String id, final IModel<LocationModel<Addresses>> model)
	{
		super(id, model);

		locationPanel = newLocationPanel("locationPanel", model);
		this.twoDropDownChoicesBean = locationPanel.getTwoDropDownChoicesBean();

		add(form = new Form<>("form", Model.of(locationPanel.getTwoDropDownChoicesBean())));
		form.setOutputMarkupId(true);
		form.add(locationPanel);

		// Create submit button for the form
		submitButton = newButton("submitButton", model);
		buttonLabel = newButtonLabel("buttonLabel", newButtonResourceKey(), "Auswählen", this);
		submitButton.add(buttonLabel);
		form.add(submitButton);
	}

	public AjaxButton getSubmitButton()
	{
		return submitButton;
	}

	protected AjaxButton newButton(final String id, final IModel<LocationModel<Addresses>> model)
	{

		return new IndicatingAjaxButton(id, form)
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onError(final AjaxRequestTarget target, final Form<?> form)
			{
				SearchLocationFormPanel.this.onError(target, form);
			}

			@Override
			protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				target.add(form);
				SearchLocationFormPanel.this.onSubmit(target, form);				
			}
		};
	}
	
	protected void onSubmit(final AjaxRequestTarget target, final Form<?> form){
		final LocationModel<Addresses> object = getModel().getObject();
		final String countryName = this.twoDropDownChoicesBean.getSelectedRootOption();
		object.setSelectedCountryName(countryName);
		final String location =this.twoDropDownChoicesBean.getSelectedChildOption();
		object.setLocation(location);
		onSearch(target, object);
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
		return "global.choose.label";
	}

	/**
	 * New form.
	 *
	 * @param id
	 *            the id
	 * @return the form
	 */
	protected Form<?> newForm(final String id)
	{
		final Form<SignupWithLocationModelBean<Addresses>> form = new Form<>(id);
		form.setOutputMarkupId(true);
		return form;
	}

	protected abstract LocationFormComponentPanel newLocationPanel(final String id,
		final IModel<LocationModel<Addresses>> model);

	protected void onError(final AjaxRequestTarget target, final Form<?> form)
	{
	}

	public abstract void onSearch(final AjaxRequestTarget target,
		final LocationModel<Addresses> object);

}
