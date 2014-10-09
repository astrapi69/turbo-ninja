package de.alpharogroup.wicket.components.address;

import org.apache.wicket.util.io.IClusterable;
import org.jaulp.wicket.model.dropdownchoices.StringTwoDropDownChoicesModel;

import address.book.model.Addresses;

/**
 * The Class AddressModel.
 *
 * @author Asterios Raptis
 */
public class AddressModel implements IClusterable {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	private Addresses address;

	/** The countries and provinces model. */
	private StringTwoDropDownChoicesModel countriesAndProvincesDropDownChoicesModel;

	/**
	 * Instantiates a new address model with the given .
	 */
	public AddressModel(StringTwoDropDownChoicesModel countriesAndProvincesDropDownChoicesModel) {
		this.countriesAndProvincesDropDownChoicesModel = countriesAndProvincesDropDownChoicesModel;
	}

	/**
	 * Instantiates a new address model.
	 */
	public AddressModel() {
	}

	public Addresses getAddress() {
		return address;
	}

	/**
	 * Gets the countries and provinces drop down choices model.
	 *
	 * @return the countries and provinces drop down choices model
	 */
	public StringTwoDropDownChoicesModel getCountriesAndProvincesDropDownChoicesModel() {
		return countriesAndProvincesDropDownChoicesModel;
	}

	public void setAddress(final Addresses address) {
		this.address = address;
	}

	/**
	 * Sets the countries and provinces drop down choices model.
	 *
	 * @param countriesAndProvincesDropDownChoicesModel
	 *            the new countries and provinces drop down choices model
	 */
	public void setCountriesAndProvincesDropDownChoicesModel(
			final StringTwoDropDownChoicesModel countriesAndProvincesDropDownChoicesModel) {
		this.countriesAndProvincesDropDownChoicesModel = countriesAndProvincesDropDownChoicesModel;
	}

	public String getGeohashSearchvalue() {
		String street = getAddress().getStreet()== null ? "": getAddress().getStreet();
		String streetnumber =getAddress().getStreetnumber()== null ? "": getAddress().getStreetnumber();
		String zipcode = getAddress().getZipcode().getZipcode()== null ? "": getAddress().getZipcode().getZipcode();
		String city = getAddress().getZipcode().getCity()== null ? "": getAddress().getZipcode().getCity();
		StringBuilder sb = new StringBuilder();
		if(0 < street.length()) {
			sb.append(street + " ");
			sb.append(streetnumber + " ");
		}
		sb.append(zipcode + " ");
		sb.append(city + " ");
		return sb.toString().trim();
	}

}
