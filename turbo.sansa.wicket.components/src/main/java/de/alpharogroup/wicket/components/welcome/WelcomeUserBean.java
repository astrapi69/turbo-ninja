package de.alpharogroup.wicket.components.welcome;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder=true)
public class WelcomeUserBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String genderEnumName;

	private String username;
}