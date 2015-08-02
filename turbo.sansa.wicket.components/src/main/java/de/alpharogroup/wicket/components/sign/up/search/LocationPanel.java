package de.alpharogroup.wicket.components.sign.up.search;

import java.util.List;
import java.util.Map;

import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import address.book.application.model.LocationModel;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.address.countries.zipcodes.DropDownChoiceTextFieldPanel;
import de.alpharogroup.wicket.components.i18n.dropdownchoice.renderers.PropertiesChoiceRenderer;
import de.alpharogroup.wicket.model.dropdownchoices.StringTwoDropDownChoicesModel;

public abstract class LocationPanel extends Panel
{
	private static final long serialVersionUID = 1L;
	private final DropDownChoiceTextFieldPanel dropDownChoiceTextFieldPanel;
	private final MultiLineLabel locationDescriptionLabel;

	@SuppressWarnings("unchecked")
	public LocationPanel(final String id, final IModel<? extends LocationModel> model)
	{
		super(id);
		add(locationDescriptionLabel = newLocationDescriptionLabel("locationDescriptionLabel"));
		add(dropDownChoiceTextFieldPanel = newDropDownChoiceTextFieldPanel(
			"dropDownChoiceTextFieldPanel", (IModel<LocationModel>)model));
	}

	public DropDownChoiceTextFieldPanel getDropDownChoiceTextFieldPanel()
	{
		return dropDownChoiceTextFieldPanel;
	};

	public MultiLineLabel getLocationDescriptionLabel()
	{
		return locationDescriptionLabel;
	}

	protected abstract Map<String, List<String>> newCountriesToZipcodesMap();

	protected DropDownChoiceTextFieldPanel newDropDownChoiceTextFieldPanel(final String id,
		final IModel<LocationModel> model)
	{
		final Map<String, List<String>> zipcodesMap = newCountriesToZipcodesMap();
		// Create the dropdown for countries with label...
		final IModel<String> rootLabelModel = ResourceModelFactory.newResourceModel(
			"countries.location.label", this, "Land");
		final IModel<String> childLabelModel = ResourceModelFactory.newResourceModel(
			"zipcode.location.label", this, "PLZ");

		final DropDownChoiceTextFieldPanel dropDownChoiceTextFieldPanel = new DropDownChoiceTextFieldPanel(
			id, new StringTwoDropDownChoicesModel("de.deu", zipcodesMap),
			new PropertiesChoiceRenderer(this, DropDownChoiceTextFieldPanel.class), rootLabelModel,
			childLabelModel, model);
		return dropDownChoiceTextFieldPanel;
	}

	protected MultiLineLabel newLocationDescriptionLabel(final String id)
	{
		final IModel<String> locationDescriptionLabelModel = ResourceModelFactory
			.newResourceModel(
				"global.location.error.label",
				this,
				"Wähle dein Land und gib deine Postleitzahl oder deine Stadt ein und wähle ein Eintrag von der vorgeschlagenen Liste");
		final MultiLineLabel locationDescriptionLabel = new MultiLineLabel(id,
			locationDescriptionLabelModel);
		return locationDescriptionLabel;
	}

}