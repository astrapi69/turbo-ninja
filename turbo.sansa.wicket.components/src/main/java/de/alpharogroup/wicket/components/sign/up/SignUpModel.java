package de.alpharogroup.wicket.components.sign.up;

import de.alpharogroup.address.book.application.model.LocationModel;
import de.alpharogroup.address.book.entities.Addresses;
import de.alpharogroup.auth.models.BaseUsernameSignUpModel;
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
public class SignUpModel extends BaseUsernameSignUpModel implements LocationModel<Addresses>
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