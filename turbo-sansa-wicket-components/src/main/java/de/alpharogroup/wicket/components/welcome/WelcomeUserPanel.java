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
package de.alpharogroup.wicket.components.welcome;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;

import de.alpharogroup.resourcebundle.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.factory.ComponentFactory;

/**
 * @author Asterios Raptis
 */
public class WelcomeUserPanel extends GenericPanel<WelcomeUserBean>
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	public WelcomeUserPanel(final String id, final IModel<WelcomeUserBean> model)
	{
		super(id, Args.notNull(model, "model"));

		add(newWelcomeLabel("lblWelcome", model));

	}

	protected Label newWelcomeLabel(final String id, final IModel<WelcomeUserBean> model) {
		final String username = model.getObject().getUsername();
		final StringBuilder resourceKey = new StringBuilder("welcome.");
		resourceKey.append(model.getObject().getGenderEnumName());

//		final Users user = getUser();
//		if (user != null)
//		{
//			if (user.getUserData().getGender() != null)
//			{
//				resourceKey.append(user.getUserData().getGender().name());
//			}
//			else
//			{
//				resourceKey.append("UNDEFINED");
//			}
//			username = user.getUsername();
//		}
//		else
//		{
//			resourceKey.append("guest");
//			username = "";
//		}

		final Object[] params = { username };
		ResourceModelFactory.newResourceModel(
			ResourceBundleKey.builder().key(resourceKey.toString().trim()).parameters(params)
				.defaultValue(resourceKey.toString().trim()).build(), this);
		final Label lblWelcome = ComponentFactory.newLabel(
			id,
			ResourceModelFactory.newResourceModel(
				ResourceBundleKey.builder().key(resourceKey.toString().trim()).parameters(params)
					.defaultValue(resourceKey.toString().trim()).build(), this));
		return lblWelcome;
	}

}
