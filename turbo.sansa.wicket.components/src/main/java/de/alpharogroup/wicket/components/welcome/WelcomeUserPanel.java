package de.alpharogroup.wicket.components.welcome;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import user.management.model.Users;
import de.alpharogroup.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.factory.ComponentFactory;

/**
 * @author Asterios Raptis
 */
public abstract class WelcomeUserPanel extends Panel
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	public WelcomeUserPanel(final String id)
	{
		super(id);
		String username = null;
		final StringBuilder resourceKey = new StringBuilder("welcome.");
		final Users user = getUser();
		if (user != null)
		{
			if (user.getUserData().getGender() != null)
			{
				resourceKey.append(user.getUserData().getGender().name());
			}
			else
			{
				resourceKey.append("UNDEFINED");
			}
			username = user.getUsername();
		}
		else
		{
			resourceKey.append("guest");
			username = "";
		}

		final Object[] params = { username };
		ResourceModelFactory.newResourceModel(
			ResourceBundleKey.builder().key(resourceKey.toString().trim()).parameters(params)
				.defaultValue(resourceKey.toString().trim()).build(), this);
		final Label lblWelcome = ComponentFactory.newLabel(
			"lblWelcome",
			ResourceModelFactory.newResourceModel(
				ResourceBundleKey.builder().key(resourceKey.toString().trim()).parameters(params)
					.defaultValue(resourceKey.toString().trim()).build(), this));
		add(lblWelcome);

	}

	protected abstract Users getUser();

}
