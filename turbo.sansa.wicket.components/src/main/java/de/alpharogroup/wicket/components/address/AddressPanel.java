package de.alpharogroup.wicket.components.address;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import address.book.factories.AddressBookFactory;
import address.book.model.Addresses;
import address.book.model.Federalstates;
import address.book.model.Zipcodes;
import de.alpharogroup.collections.ListExtensions;
import de.alpharogroup.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.address.countries.CountriesProvincesPanel;
import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.i18n.dropdownchoice.renderers.PropertiesChoiceRenderer;
import de.alpharogroup.wicket.components.labeled.LabeledTwoFormComponentPanel;
import de.alpharogroup.wicket.model.dropdownchoices.StringTwoDropDownChoicesModel;

/**
 * The class AddressPanel.
 * 
 * @author Asterios Raptis
 */
public class AddressPanel extends GenericPanel<AddressBean>
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new address panel.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the address model
	 */
	public AddressPanel(final String id, final IModel<AddressBean> model)
	{
		super(id, model);

		add(newStreetNumberPanel("streetNumberPanel", model));

		add(newZipcodeCityPanel("zipcodeCityPanel", model));

		add(newCountriesProvincesPanel("countriesProvincesPanel", initializeModel(model)));

	}

	/**
	 * Initialize model.
	 *
	 * @param model
	 *            the model
	 * @return the string two drop down choices model
	 */
	private StringTwoDropDownChoicesModel initializeModel(final IModel<AddressBean> model)
	{
		final AddressBean modelObject = model.getObject();
		final StringTwoDropDownChoicesModel stringTwoDropDownChoicesModel = modelObject
			.getCountriesAndProvincesDropDownChoicesModel();
		final Addresses address = modelObject.getAddress();
		// Initialize the dropdown choices for the country and federal state...
		if (address != null)
		{
			final Federalstates federalState = modelObject.getAddress().getFederalstate();
			if (federalState != null)
			{
				stringTwoDropDownChoicesModel.setSelectedRootOption(federalState.getCountry()
					.getName());
				stringTwoDropDownChoicesModel.setSelectedChildOption(federalState
					.getIso3166A2code());

			}
		}
		else
		{
			final Zipcodes zc = AddressBookFactory.getInstance().newZipcodes(null, "", "");
			final Addresses initialAddress = AddressBookFactory.getInstance().newAddresses("",
				null, null, null, null, "", "", zc);
			modelObject.setAddress(initialAddress);
		}
		return stringTwoDropDownChoicesModel;
	}

	/**
	 * Factory method for create a new Label for what characters will be between the two components.
	 *
	 * @return the {@link IModel} with the characters.
	 */
	protected IModel<String> newBetweenLabelModel()
	{
		return Model.of("/");
	}

	/**
	 * Factory method for create a new {@link CountriesProvincesPanel}.
	 *
	 * @param id
	 *            the id
	 * @param stringTwoDropDownChoicesModel
	 *            the string two drop down choices model
	 * @return the countries provinces panel
	 */
	protected CountriesProvincesPanel newCountriesProvincesPanel(final String id,
		final StringTwoDropDownChoicesModel stringTwoDropDownChoicesModel)
	{
		final CountriesProvincesPanel countriesProvincesPanel = new CountriesProvincesPanel(
			"countriesProvincesPanel", stringTwoDropDownChoicesModel, new PropertiesChoiceRenderer(
				this, AddressPanel.class), new PropertiesChoiceRenderer(this, AddressPanel.class));

		final AttributeModifier samWmcRoot = new AttributeModifier("class", "countries");
		final AttributeModifier sam = new AttributeModifier("class", "countries");
		countriesProvincesPanel.getWmcRootChoice().add(samWmcRoot);
		countriesProvincesPanel.getRootChoice().add(sam);
		final AttributeModifier samWmcChild = new AttributeModifier("class", "federalstates");
		countriesProvincesPanel.getWmcChildChoice().add(samWmcChild);
		countriesProvincesPanel.getChildChoice().add(sam);
		countriesProvincesPanel.getChildChoice().setRequired(true);
		return countriesProvincesPanel;
	}

	/**
	 * Factory method for create a new {@link Component} for street and number.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the component
	 */
	protected Component newStreetNumberPanel(final String id, final IModel<AddressBean> model)
	{
		final String betweenLabel = AddressPanel.this.newBetweenLabelModel().getObject();
		final LabeledTwoFormComponentPanel<String, String> streetNumberPanel = new LabeledTwoFormComponentPanel<String, String>(
			id, ResourceModelFactory.newResourceModel(
				ResourceBundleKey.builder().key("sem.main.address.street.and.nr.label")
					.parameters(ListExtensions.toObjectArray(betweenLabel))
					.defaultValue("Street, Number:").build(), this))
		{
			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected IModel<String> newBetweenLabelModel(final String betweenLabel)
			{
				return AddressPanel.this.newBetweenLabelModel();
			}

			@Override
			protected FormComponent<String> newLeftFormComponent(final String id,
				final IModel<String> model)
			{
				return ComponentFactory.newTextField(id, new PropertyModel<String>(
					AddressPanel.this.getModelObject(), "address.street"));
			}

			@Override
			protected FormComponent<String> newRightFormComponent(final String id,
				final IModel<String> model)
			{
				return ComponentFactory.newTextField(id, new PropertyModel<String>(
					AddressPanel.this.getModelObject(), "address.streetnumber"));
			}
		};
		return streetNumberPanel;
	}

	/**
	 * Factory method for create a new {@link Component} for zipcode and city.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the component
	 */
	protected Component newZipcodeCityPanel(final String id, final IModel<AddressBean> model)
	{
		final String betweenLabel = AddressPanel.this.newBetweenLabelModel().getObject();
		final LabeledTwoFormComponentPanel<String, String> streetNumberPanel = new LabeledTwoFormComponentPanel<String, String>(
			id, ResourceModelFactory.newResourceModel(
				ResourceBundleKey.builder().key("sem.main.address.zipcode.and.city.label")
					.parameters(ListExtensions.toObjectArray(betweenLabel))
					.defaultValue("Zipcode, City:").build(), this))
		{
			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected IModel<String> newBetweenLabelModel(final String betweenLabel)
			{
				return AddressPanel.this.newBetweenLabelModel();
			}

			@Override
			protected FormComponent<String> newLeftFormComponent(final String id,
				final IModel<String> model)
			{
				return ComponentFactory.newTextField(id, new PropertyModel<String>(
					AddressPanel.this.getModelObject(), "address.zipcode.zipcode"));
			}

			@Override
			protected FormComponent<String> newRightFormComponent(final String id,
				final IModel<String> model)
			{
				return ComponentFactory.newTextField(id, new PropertyModel<String>(
					AddressPanel.this.getModelObject(), "address.zipcode.city"));
			}
		};
		return streetNumberPanel;
	}
}
