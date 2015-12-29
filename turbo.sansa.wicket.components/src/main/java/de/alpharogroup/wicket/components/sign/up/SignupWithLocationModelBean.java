package de.alpharogroup.wicket.components.sign.up;

import java.util.List;
import java.util.Map;

import de.alpharogroup.address.book.application.model.LocationModel;
import de.alpharogroup.auth.models.BaseUsernameSignUpModel;
import de.alpharogroup.wicket.model.dropdownchoices.StringTwoDropDownChoicesModel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@EqualsAndHashCode(callSuper = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SignupWithLocationModelBean<T> extends BaseUsernameSignUpModel implements LocationModel<T>
{

	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private T address;
	@Getter
	@Setter
	private String location;
	@Getter
	@Setter
	private String selectedCountryName;
	@Getter
	@Setter
	private Map<String, List<String>> countriesToZipcodes;
	@Getter
	@Setter
	private StringTwoDropDownChoicesModel dropdownChoicesModel;

}