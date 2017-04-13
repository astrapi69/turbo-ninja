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
package de.alpharogroup.wicket.components.address;

import java.io.Serializable;

import de.alpharogroup.address.book.entities.Addresses;
import de.alpharogroup.wicket.model.dropdownchoices.StringTwoDropDownChoicesModel;

/**
 * The Class AddressBean.
 *
 * @author Asterios Raptis
 */
public abstract class AddressBean implements Serializable
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	private Addresses address;

	/** The countries and provinces model. */
	private StringTwoDropDownChoicesModel countriesAndProvincesDropDownChoicesModel;

	/**
	 * Instantiates a new address model.
	 */
	public AddressBean()
	{
		this.countriesAndProvincesDropDownChoicesModel = newCountriesAndProvincesDropDownChoicesModel();
	}

	/**
	 * Instantiates a new address model with the given .
	 *
	 * @param countriesAndProvincesDropDownChoicesModel
	 *            the countries and provinces drop down choices model
	 */
	public AddressBean(final StringTwoDropDownChoicesModel countriesAndProvincesDropDownChoicesModel)
	{
		this.countriesAndProvincesDropDownChoicesModel = countriesAndProvincesDropDownChoicesModel;
	}

	public Addresses getAddress()
	{
		return address;
	}

	/**
	 * Gets the countries and provinces drop down choices model.
	 *
	 * @return the countries and provinces drop down choices model
	 */
	public StringTwoDropDownChoicesModel getCountriesAndProvincesDropDownChoicesModel()
	{
		return countriesAndProvincesDropDownChoicesModel;
	}

	public String getGeohashSearchvalue()
	{
		final String street = getAddress().getStreet() == null ? "" : getAddress().getStreet();
		final String streetnumber = getAddress().getStreetnumber() == null ? "" : getAddress()
			.getStreetnumber();
		final String zipcode = getAddress().getZipcode().getZipcode() == null ? "" : getAddress()
			.getZipcode().getZipcode();
		final String city = getAddress().getZipcode().getCity() == null ? "" : getAddress()
			.getZipcode().getCity();
		final StringBuilder sb = new StringBuilder();
		if (0 < street.length())
		{
			sb.append(street + " ");
			sb.append(streetnumber + " ");
		}
		sb.append(zipcode + " ");
		sb.append(city + " ");
		return sb.toString().trim();
	}

	protected abstract StringTwoDropDownChoicesModel newCountriesAndProvincesDropDownChoicesModel();

	public void setAddress(final Addresses address)
	{
		this.address = address;
	}

	/**
	 * Sets the countries and provinces drop down choices model.
	 *
	 * @param countriesAndProvincesDropDownChoicesModel
	 *            the new countries and provinces drop down choices model
	 */
	public void setCountriesAndProvincesDropDownChoicesModel(
		final StringTwoDropDownChoicesModel countriesAndProvincesDropDownChoicesModel)
	{
		this.countriesAndProvincesDropDownChoicesModel = countriesAndProvincesDropDownChoicesModel;
	}

}
