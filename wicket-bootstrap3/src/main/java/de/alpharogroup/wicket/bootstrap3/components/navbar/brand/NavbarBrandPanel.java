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
package de.alpharogroup.wicket.bootstrap3.components.navbar.brand;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * The class {@link NavbarBrandPanel}.
 */
public abstract class NavbarBrandPanel extends Panel
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link NavbarBrandPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public NavbarBrandPanel(final String id, final IModel<?> model)
	{
		super(id, model);
		add(newnavbarBrandButton("navbarBrandButton"));
		add(newNavbarBrandLink("navbarBrandLink"));
	}

	/**
	 * Factory method for create a new {@link Button}. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * new {@link Button}.
	 *
	 * @param id
	 *            the id
	 * @return the new {@link Button}
	 */
	protected Component newnavbarBrandButton(final String id)
	{
		return new Button(id);
	}

	/**
	 * Factory method for create a new {@link AbstractLink}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new {@link AbstractLink}.
	 *
	 * @param id
	 *            the id
	 * @return the new {@link AbstractLink}
	 */
	protected AbstractLink newNavbarBrandLink(final String id)
	{
		return new AjaxLink<Void>(id)
		{

			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void onClick(final AjaxRequestTarget target)
			{
				NavbarBrandPanel.this.onClick(target);

			}
		};
	}

	/**
	 * Abstract callback method that have to be overwritten to provide specific action on click.
	 *
	 * @param target
	 *            the target
	 */
	public abstract void onClick(final AjaxRequestTarget target);

}
