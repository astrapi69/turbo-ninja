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
package de.alpharogroup.wicket.components.infringement;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.resourcebundle.locale.ResourceBundleKey;
import de.alpharogroup.user.management.application.models.InfringementModel;
import de.alpharogroup.wicket.base.util.properties.ComponentPropertiesKeysListResolver;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.i18n.content.ContentModelBean;
import de.alpharogroup.wicket.components.i18n.content.ContentPanel;
import de.alpharogroup.wicket.components.i18n.list.UnorderedListPanel;
import de.alpharogroup.wicket.components.infringement.form.InfringementFormPanel;

/**
 * The Class InfringementPanel.
 */
public abstract class InfringementPanel extends Panel
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The form panel. */
	private final Component formPanel;

	/** The introduction panel. */
	private final Component introductionPanel;

	/** The list view panel. */
	private final Component listViewPanel;

	/**
	 * Instantiates a new infringement panel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public InfringementPanel(final String id, final IModel<InfringementModel> model)
	{
		super(id, model);
		add(introductionPanel = newIntroductionPanel("introductionPanel", model));
		add(listViewPanel = newListViewPanel("listViewPanel", newDisplayValues()));
		add(formPanel = newFormPanel("formPanel", model));
	}

	/**
	 * Factory method to get the display values in the ListViewPanel.
	 *
	 * @return the list
	 */
	protected List<ResourceBundleKey> newDisplayValues()
	{
		final List<ResourceBundleKey> values = Arrays.asList(ResourceBundleKey.builder().key("1")
			.build(), ResourceBundleKey.builder().key("2").build(), ResourceBundleKey.builder()
			.key("3").build(), ResourceBundleKey.builder().key("4").build(), ResourceBundleKey
			.builder().key("5").build(), ResourceBundleKey.builder().key("6").build(),
			ResourceBundleKey.builder().key("7").build());
		final ComponentPropertiesKeysListResolver renderer = new ComponentPropertiesKeysListResolver(
			"infringement.list.entry", "label", this, values);
		final List<ResourceBundleKey> listDisplayValues = renderer.getDisplayValues();
		return listDisplayValues;
	}

	/**
	 * Gets the form panel.
	 *
	 * @return the form panel
	 */
	public Component getFormPanel()
	{
		return formPanel;
	}

	/**
	 * Gets the introduction panel.
	 *
	 * @return the introduction panel
	 */
	public Component getIntroductionPanel()
	{
		return introductionPanel;
	}


	/**
	 * Gets the list view panel.
	 *
	 * @return the list view panel
	 */
	public Component getListViewPanel()
	{
		return listViewPanel;
	}

	/**
	 * New form panel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the component
	 */
	protected Component newFormPanel(final String id, final IModel<InfringementModel> model)
	{
		return new InfringementFormPanel(id, model)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onFormSubmit()
			{
				onSend();
			}
		};
	}

	/**
	 * New introduction panel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the component
	 */
	protected Component newIntroductionPanel(final String id, final IModel<?> model)
	{
		final ContentModelBean modelBean = ContentModelBean.builder()
			.headerResourceKey(ResourceBundleKey.builder().key("header.label").build())
			.contentResourceKey(ResourceBundleKey.builder().key("introduction.label").build())
			.build();
		final ContentPanel introductionPanel = new ContentPanel(id, Model.of(modelBean));
		return introductionPanel;
	}

	/**
	 * New list view panel.
	 *
	 * @param id
	 *            the id
	 * @param list
	 *            the list
	 * @return the component
	 */
	protected Component newListViewPanel(final String id, final List<ResourceBundleKey> list)
	{
		final UnorderedListPanel listViewPanel = new UnorderedListPanel(id, list)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected Component newListComponent(final String id,
				final ListItem<ResourceBundleKey> item)
			{
				return new Label(id, ResourceModelFactory.newResourceModel(item.getModel()
					.getObject(), this));
			}
		};
		return listViewPanel;
	}

	/**
	 * On send.
	 */
	protected abstract void onSend();

}
