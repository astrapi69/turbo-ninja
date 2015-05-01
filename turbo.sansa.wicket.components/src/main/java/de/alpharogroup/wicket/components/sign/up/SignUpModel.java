package de.alpharogroup.wicket.components.sign.up;

import de.alpharogroup.auth.models.BaseUsernameSignUpModel;
import address.book.application.model.LocationModel;
import address.book.model.Addresses;

public class SignUpModel extends BaseUsernameSignUpModel implements LocationModel {

	private static final long serialVersionUID = 1L;
	private Addresses address;
	private String location;
	private String selectedCountryName;
	public Addresses getAddress() {
		return address;
	}
	public String getLocation() {
		return location;
	}
	public String getSelectedCountryName() {
		return selectedCountryName;
	}
	public void setAddress(Addresses address) {
		this.address = address;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setSelectedCountryName(String selectedCountryName) {
		this.selectedCountryName = selectedCountryName;
	}

}