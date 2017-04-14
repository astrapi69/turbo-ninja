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

import java.util.List;
import java.util.Map;

import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;

import de.alpharogroup.address.book.application.model.LocationModel;
import de.alpharogroup.address.book.entities.Addresses;
import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.address.countries.zipcodes.DropDownChoiceTextFieldPanel;
import de.alpharogroup.wicket.components.i18n.dropdownchoice.renderers.PropertiesChoiceRenderer;
import de.alpharogroup.wicket.model.dropdownchoices.StringTwoDropDownChoicesModel;
import lombok.Getter;

/**
 * The class {@link LocationPanel}.
 */
public abstract class LocationPanel extends BasePanel<LocationModel<Addresses>>
{
	private static final long serialVersionUID = 1L;

	/** The drop down choice text field panel. */
	@Getter
	private final DropDownChoiceTextFieldPanel countryWithZipDropDownChoiceTextFieldPanel;

	/** The location description label. */
	@Getter
	private final MultiLineLabel locationDescriptionLabel;

	/** The countries to zipcodes map. */
	@Getter
	private final Map<String, List<String>> countriesToZipcodes;

	@Getter
	private final StringTwoDropDownChoicesModel dropdownChoicesModel;

	/**
	 * Instantiates a new {@link LocationPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public LocationPanel(final String id, final IModel<LocationModel<Addresses>> model)
	{
		super(id, model);
		Args.notNull(model, "model");
		this.countriesToZipcodes = newCountriesToZipcodesMap();
		this.dropdownChoicesModel = new StringTwoDropDownChoicesModel("de.deu",
			this.countriesToZipcodes);
		add(locationDescriptionLabel = newLocationDescriptionLabel("locationDescriptionLabel"));
		add(countryWithZipDropDownChoiceTextFieldPanel = newDropDownChoiceTextFieldPanel(
			"dropDownChoiceTextFieldPanel", model));
	}

	/**
	 * Abstract factory method for creating a new map for countries to zipcodes.
	 *
	 * @return the {@link Map} for countries to zipcodes.
	 */
	protected abstract Map<String, List<String>> newCountriesToZipcodesMap();

	/**
	 * Factory method for creating a new {@link DropDownChoiceTextFieldPanel}. This method is
	 * invoked in the constructor from the derived classes and can be overridden so users can
	 * provide their own version of a new {@link DropDownChoiceTextFieldPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link DropDownChoiceTextFieldPanel}.
	 */
	protected DropDownChoiceTextFieldPanel newDropDownChoiceTextFieldPanel(final String id,
		final IModel<LocationModel<Addresses>> model)
	{
		// Create the dropdown for countries with label...
		final IModel<String> rootLabelModel = ResourceModelFactory
			.newResourceModel("countries.location.label", this, "Land");
		final IModel<String> childLabelModel = ResourceModelFactory
			.newResourceModel("zipcode.location.label", this, "PLZ");
		final IChoiceRenderer<String> choiceRenderer = new PropertiesChoiceRenderer(this,
			DropDownChoiceTextFieldPanel.class);
		final DropDownChoiceTextFieldPanel countryWithZipDropDownChoiceTextFieldPanel = new DropDownChoiceTextFieldPanel(
			id, this.dropdownChoicesModel, choiceRenderer, rootLabelModel, childLabelModel, model);
		return countryWithZipDropDownChoiceTextFieldPanel;
	}

	/**
	 * Factory method for creating a new {@link MultiLineLabel} for the description of the location.
	 * This method is invoked in the constructor from the derived classes and can be overridden so
	 * users can provide their own version of a new {@link MultiLineLabel} for the description of
	 * the location.
	 *
	 * @param id
	 *            the id
	 * @return the new {@link MultiLineLabel} for the description of the location.
	 */
	protected MultiLineLabel newLocationDescriptionLabel(final String id)
	{
		final IModel<String> locationDescriptionLabelModel = ResourceModelFactory.newResourceModel(
			"global.location.error.label", this,
			"Wähle dein Land und gib deine Postleitzahl oder deine Stadt ein und wähle ein Eintrag von der vorgeschlagenen Liste");
		final MultiLineLabel locationDescriptionLabel = new MultiLineLabel(id,
			locationDescriptionLabelModel);
		return locationDescriptionLabel;
	}

}