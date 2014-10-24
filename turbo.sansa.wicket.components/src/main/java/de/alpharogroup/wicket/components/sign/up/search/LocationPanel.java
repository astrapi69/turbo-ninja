package de.alpharogroup.wicket.components.sign.up.search;
import java.util.List;
import java.util.Map;

import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;
import org.jaulp.wicket.components.i18n.dropdownchoice.renderers.PropertiesChoiceRenderer;
import org.jaulp.wicket.model.dropdownchoices.StringTwoDropDownChoicesModel;

import address.book.application.model.LocationModel;
import de.alpharogroup.wicket.components.address.countries.zipcodes.DropDownChoiceTextFieldPanel;

public abstract class LocationPanel extends Panel {
	private static final long serialVersionUID = 1L;
	private final DropDownChoiceTextFieldPanel dropDownChoiceTextFieldPanel;
	private final MultiLineLabel locationDescriptionLabel;

	@SuppressWarnings("unchecked")
	public LocationPanel(final String id, final IModel<? extends LocationModel> model) {
		super(id);
		add(locationDescriptionLabel = newLocationDescriptionLabel("locationDescriptionLabel"));
		add(dropDownChoiceTextFieldPanel = newDropDownChoiceTextFieldPanel("dropDownChoiceTextFieldPanel", (IModel<LocationModel>)model));
	}

	protected MultiLineLabel newLocationDescriptionLabel(final String id) {
		IModel<String> locationDescriptionLabelModel = ResourceModelFactory
				.newResourceModel(
						"global.location.error.label",
						this,
						"Wähle dein Land und gib deine Postleitzahl oder deine Stadt ein und wähle ein Eintrag von der vorgeschlagenen Liste");
		MultiLineLabel locationDescriptionLabel = new MultiLineLabel(id,
				locationDescriptionLabelModel);
		return locationDescriptionLabel;
	};

	protected DropDownChoiceTextFieldPanel newDropDownChoiceTextFieldPanel(
			final String id, final IModel<LocationModel> model) {
		Map<String, List<String>> zipcodesMap = 
				newCountriesToZipcodesMap();
		// Create the dropdown for countries with label...
		IModel<String> rootLabelModel = ResourceModelFactory.newResourceModel(
				"countries.location.label", this, "Land");
		IModel<String> childLabelModel = ResourceModelFactory.newResourceModel(
				"zipcode.location.label", this, "PLZ");

		DropDownChoiceTextFieldPanel dropDownChoiceTextFieldPanel = new DropDownChoiceTextFieldPanel(
				id, new StringTwoDropDownChoicesModel("de.deu", zipcodesMap),
				new PropertiesChoiceRenderer(this,
						DropDownChoiceTextFieldPanel.class), rootLabelModel,
				childLabelModel, model);
		return dropDownChoiceTextFieldPanel;
	}

	protected abstract Map<String, List<String>> newCountriesToZipcodesMap();

	public MultiLineLabel getLocationDescriptionLabel() {
		return locationDescriptionLabel;
	}

	public DropDownChoiceTextFieldPanel getDropDownChoiceTextFieldPanel() {
		return dropDownChoiceTextFieldPanel;
	}

}