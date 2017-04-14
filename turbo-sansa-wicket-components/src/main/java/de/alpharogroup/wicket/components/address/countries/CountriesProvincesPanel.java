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
package de.alpharogroup.wicket.components.address.countries;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

import de.alpharogroup.resourcebundle.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.i18n.dropdownchoice.panels.TwoDropDownChoicesPanel;
import de.alpharogroup.wicket.model.dropdownchoices.TwoDropDownChoicesModel;
import lombok.Getter;

/**
 * The class CountriesProvincesPanel.
 *
 * @author Asterios Raptis
 */
public class CountriesProvincesPanel extends TwoDropDownChoicesPanel<String>
{

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The wmc root choice. */
	@Getter
	private WebMarkupContainer wmcRootChoice;

	/** The wmc child choice. */
	@Getter
	private final WebMarkupContainer wmcChildChoice;

	/**
	 * Instantiates a new countries provinces panel.
	 *
	 * @param id
	 *            the id
	 * @param stringTwoDropDownChoicesModel
	 *            the string two drop down choices model
	 * @param rootRenderer
	 *            the root renderer
	 * @param childRenderer
	 *            the child renderer
	 */
	public CountriesProvincesPanel(final String id,
		final IModel<TwoDropDownChoicesModel<String>> stringTwoDropDownChoicesModel,
		final IChoiceRenderer<String> rootRenderer, final IChoiceRenderer<String> childRenderer)
	{
		super(id, stringTwoDropDownChoicesModel, rootRenderer, childRenderer);

		add(new Label("countryLabel", ResourceModelFactory.newResourceModel(
			ResourceBundleKey.builder().key("sem.main.address.country.label").build(), this)));

		add(wmcRootChoice = newWmcRootChoice("wmcRootChoice"));
		wmcRootChoice.add(this.getRootChoice());

		add(new Label("federalStateLabel",
			ResourceModelFactory.newResourceModel(
				ResourceBundleKey.builder().key("sem.main.address.federal.state.label").build(),
				this)));
		wmcChildChoice = newWmcChildChoice("wmcChildChoice");
		add(wmcChildChoice);
		wmcChildChoice.add(this.getChildChoice());
	}

	protected WebMarkupContainer newWmcChildChoice(final String id)
	{
		return ComponentFactory.newWebMarkupContainer(id);
	}

	protected WebMarkupContainer newWmcRootChoice(final String id)
	{
		return ComponentFactory.newWebMarkupContainer(id);
	}

}
