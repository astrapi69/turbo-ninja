package de.alpharogroup.wicket.components.welcome;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import user.management.model.Users;
import de.alpharogroup.wicket.components.i18n.label.LocalizedLabel;

/**
 * @author Asterios Raptis
 */
public abstract class WelcomeUserPanel extends Panel {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	public WelcomeUserPanel(final String id) {
		super(id);
		String username = null;
		StringBuilder resourceKey = new StringBuilder("welcome.");
		Users user = getUser();
		if (user != null) {
			if(user.getUserData().getGender() != null){
				resourceKey.append(user.getUserData().getGender().name());
			} else {
				resourceKey.append("UNDEFINED");
			}
			username = user.getUsername();
		} else {
			resourceKey.append("guest");
			username = "";
		}

		final Object[] params = { username };
		Label lblWelcome = new LocalizedLabel("lblWelcome", resourceKey
				.toString().trim(), this, params);
		add(lblWelcome);

	}

	protected abstract Users getUser();

}
