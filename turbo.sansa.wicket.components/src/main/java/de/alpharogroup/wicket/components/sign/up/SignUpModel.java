package de.alpharogroup.wicket.components.sign.up;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import address.book.application.model.LocationModel;
import address.book.model.Addresses;
import de.alpharogroup.auth.models.BaseUsernameSignUpModel;


@EqualsAndHashCode(callSuper = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SignUpModel extends BaseUsernameSignUpModel implements LocationModel
{

	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private Addresses address;
	@Getter
	@Setter
	private String location;
	@Getter
	@Setter
	private String selectedCountryName;


}