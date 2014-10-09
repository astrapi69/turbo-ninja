package de.alpharogroup.wicket.components.address;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.jaulp.wicket.components.i18n.dropdownchoice.renderers.PropertiesChoiceRenderer;
import org.jaulp.wicket.model.dropdownchoices.StringTwoDropDownChoicesModel;

import de.alpharogroup.wicket.components.address.countries.CountriesProvincesPanel;
import address.book.factories.AddressBookFactory;
import address.book.model.Addresses;
import address.book.model.Federalstates;
import address.book.model.Zipcodes;

/**
 * The class AddressPanel.
 * 
 * @author Asterios Raptis
 */
public class AddressPanel extends Panel {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new address panel.
	 * 
	 * @param id
	 *            the id
	 * @param addressModel
	 *            the address model
	 */
	public AddressPanel(final String id, final IModel<AddressModel> addressModel) {
        super(id, addressModel);
        setDefaultModel(addressModel);
        AddressModel modelObject = addressModel.getObject();
        StringTwoDropDownChoicesModel stringTwoDropDownChoicesModel = modelObject
                .getCountriesAndProvincesDropDownChoicesModel();
        Addresses address = modelObject.getAddress();
        // Initialize the dropdown choices for the country and federal state...
        if (address != null) {
            Federalstates federalState = modelObject.getAddress()
                    .getFederalstate();
            if(federalState != null){
                stringTwoDropDownChoicesModel.setSelectedRootOption(federalState
                        .getCountry().getName());
                stringTwoDropDownChoicesModel.setSelectedChildOption(federalState
                        .getIso3166A2code());
            	
            }
        } else {        	
    		final Zipcodes zc = AddressBookFactory.getInstance().newZipcodes(null,
					"", "");
    		final Addresses initialAddress = AddressBookFactory.getInstance()
    				.newAddresses("", null, null, null, null,
    						"", "", zc);
            modelObject.setAddress(initialAddress);
        }

        final RequiredTextField<String> street = new RequiredTextField<String>(
                "address.address.street");
        add(street);

        final RequiredTextField<String> streetnumber = new RequiredTextField<String>(
                "address.address.streetnumber");
        add(streetnumber);

        final RequiredTextField<String> zipcode = new RequiredTextField<String>(
                "address.address.zipcode.zipcode");
        add(zipcode);

        final RequiredTextField<String> city = new RequiredTextField<String>(
                "address.address.zipcode.city");
        add(city);

        CountriesProvincesPanel countriesProvincesPanel = getCountriesProvincesPanel(stringTwoDropDownChoicesModel);

        add(countriesProvincesPanel);

	}
	
	protected CountriesProvincesPanel getCountriesProvincesPanel(StringTwoDropDownChoicesModel stringTwoDropDownChoicesModel){
		CountriesProvincesPanel countriesProvincesPanel = new CountriesProvincesPanel(
                "countriesProvincesPanel", stringTwoDropDownChoicesModel,
                new PropertiesChoiceRenderer(this, AddressPanel.class),
                new PropertiesChoiceRenderer(this, AddressPanel.class));

        AttributeModifier samWmcRoot = new AttributeModifier(
                "class", "countries");
        AttributeModifier sam = new AttributeModifier("class",
                "countries");
        countriesProvincesPanel.getWmcRootChoice().add(samWmcRoot);
        countriesProvincesPanel.getRootChoice().add(sam);
        AttributeModifier samWmcChild = new AttributeModifier(
                "class", "federalstates");
        countriesProvincesPanel.getWmcChildChoice().add(samWmcChild);
        countriesProvincesPanel.getChildChoice().add(sam);
        countriesProvincesPanel.getChildChoice().setRequired(true);
        return countriesProvincesPanel;
	}
}
