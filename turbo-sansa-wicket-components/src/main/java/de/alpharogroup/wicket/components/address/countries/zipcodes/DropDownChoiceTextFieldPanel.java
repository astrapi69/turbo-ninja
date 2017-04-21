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
package de.alpharogroup.wicket.components.address.countries.zipcodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.DefaultCssAutoCompleteTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.string.Strings;

import de.alpharogroup.address.book.application.model.LocationModel;
import de.alpharogroup.address.book.entities.Addresses;
import de.alpharogroup.wicket.components.i18n.dropdownchoice.LocalisedDropDownChoice;
import de.alpharogroup.wicket.model.dropdownchoices.StringTwoDropDownChoicesModel;
import lombok.Getter;

/**
 * The class {@link DropDownChoiceTextFieldPanel}.
 *
 * @author Asterios Raptis
 */
public class DropDownChoiceTextFieldPanel extends GenericPanel<StringTwoDropDownChoicesModel>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Getter
	private final LocalisedDropDownChoice<String> rootChoice;

	@Getter
	private final StringTwoDropDownChoicesModel stringTwoDropDownChoicesModel;


	/** The wmc child choice. */
	@Getter
	private final WebMarkupContainer wmcChildChoice;

	/** The Label for root component. */
	@Getter
	protected final Label rootLabel;

	/** The Label for child component. */
	@Getter
	protected Label childLabel;

	/** The wmc root choice. */
	@Getter
	private final WebMarkupContainer wmcRootChoice;

	@Getter
	private final AutoCompleteTextField<String> zipcode;

	/**
	 * Instantiates a new DropDownChoiceTextFieldPanel.
	 *
	 * @param id
	 *            the id
	 * @param stringTwoDropDownChoicesModel
	 *            the string two drop down choices model
	 * @param rootRenderer
	 *            the root renderer
	 * @param rootLabelModel
	 *            the root label model
	 * @param childLabelModel
	 *            the child label model
	 * @param locationModel
	 *            the location model
	 */
	public DropDownChoiceTextFieldPanel(final String id,
		final StringTwoDropDownChoicesModel stringTwoDropDownChoicesModel,
		final IChoiceRenderer<String> rootRenderer, final IModel<String> rootLabelModel,
		final IModel<String> childLabelModel, final IModel<LocationModel<Addresses>> locationModel)
	{
		super(id,
			Model.of(Args.notNull(stringTwoDropDownChoicesModel, "stringTwoDropDownChoicesModel")));
		this.stringTwoDropDownChoicesModel = stringTwoDropDownChoicesModel;

		rootChoice = newLocalisedDropDownChoice("rootChoice",
			new PropertyModel<String>(this.stringTwoDropDownChoicesModel, "selectedRootOption"),
			this.stringTwoDropDownChoicesModel.getRootChoices(), rootRenderer);

		final String rootMarkupId = rootChoice.getMarkupId();

		wmcRootChoice = new WebMarkupContainer("wmcRootChoice");
		wmcRootChoice.setOutputMarkupId(true);
		add(wmcRootChoice);
		wmcRootChoice.add(rootLabel = newRootLabel(rootMarkupId, rootLabelModel));
		wmcRootChoice.add(this.getRootChoice());

		zipcode = newAutoCompleteTextField("zipcode",
			new PropertyModel<String>(locationModel, "location"));

		final String childMarkupId = zipcode.getMarkupId();
		zipcode.setOutputMarkupId(true);

		wmcChildChoice = new WebMarkupContainer("wmcChildChoice");
		wmcChildChoice.setOutputMarkupId(true);
		add(wmcChildChoice);
		wmcChildChoice.add(childLabel = newChildLabel(childMarkupId, childLabelModel));
		wmcChildChoice.add(zipcode);

		rootChoice.add(new AjaxFormComponentUpdatingBehavior("change")
		{

			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(final AjaxRequestTarget target)
			{
				target.add(zipcode);
			}
		});
	}

	protected AutoCompleteTextField<String> newAutoCompleteTextField(final String id,
		final IModel<String> model)
	{
		final DefaultCssAutoCompleteTextField<String> autoCompleteTextField = new DefaultCssAutoCompleteTextField<String>(
			id, model)
		{

			private static final long serialVersionUID = 1L;

			@Override
			protected Iterator<String> getChoices(final String input)
			{
				if (Strings.isEmpty(input))
				{
					final List<String> emptyList = Collections.emptyList();
					return emptyList.iterator();
				}

				final List<String> choices = new ArrayList<>(10);

				final List<String> childChoices = DropDownChoiceTextFieldPanel.this.stringTwoDropDownChoicesModel
					.getChildChoices().getObject();
				for (final String choice : childChoices)
				{
					if (choice.toUpperCase().startsWith(input.toUpperCase()))
					{
						choices.add(choice);
						if (choices.size() == 20)
						{
							break;
						}
					}
				}
				return choices.iterator();
			}
		};
		return autoCompleteTextField;
	}

	/**
	 * Factory method for creating the root Label. This method is invoked in the constructor from
	 * the derived classes and can be overridden so users can provide their own version of a Label.
	 *
	 * @param forId
	 *            the for id
	 * @param model
	 *            the model
	 * @return the label
	 */
	protected Label newChildLabel(final String forId, final IModel<String> model)
	{
		return newLabel("childLabel", forId, model);
	}

	/**
	 * Factory method for creating the Label. This method is invoked in the constructor from the
	 * derived classes and can be overridden so users can provide their own version of a Label.
	 *
	 * @param id
	 *            the id
	 * @param forId
	 *            the for id
	 * @param model
	 *            the model
	 * @return the label
	 */
	private Label newLabel(final String id, final String forId, final IModel<String> model)
	{
		final Label label = new Label(id, model);
		label.setOutputMarkupId(true);
		label.add(new AttributeAppender("for", Model.of(forId), " "));
		return label;
	}

	protected LocalisedDropDownChoice<String> newLocalisedDropDownChoice(final String id,
		final IModel<String> model, final IModel<? extends List<? extends String>> choices,
		final IChoiceRenderer<? super String> renderer)
	{
		final LocalisedDropDownChoice<String> dropdown = new LocalisedDropDownChoice<>(id, model,
			choices, renderer);
		dropdown.setOutputMarkupId(true);
		return dropdown;
	}

	/**
	 * Factory method for creating the root Label. This method is invoked in the constructor from
	 * the derived classes and can be overridden so users can provide their own version of a Label.
	 *
	 * @param forId
	 *            the for id
	 * @param model
	 *            the model
	 * @return the label
	 */
	protected Label newRootLabel(final String forId, final IModel<String> model)
	{
		return newLabel("rootLabel", forId, model);
	}
}