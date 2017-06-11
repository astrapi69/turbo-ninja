package de.alpharogroup.wicket.components.address.countries.zipcodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.DefaultCssAutoCompleteTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.string.Strings;

import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.i18n.dropdownchoice.LocalisedDropDownChoice;
import de.alpharogroup.wicket.model.dropdownchoices.TwoDropDownChoicesBean;
import lombok.Getter;

/**
 * The class {@link DropdownAutocompleteTextFieldPanel}.
 *
 * @param <String>
 *            the generic type
 */
public class DropdownAutocompleteTextFieldPanel extends FormComponentPanel<TwoDropDownChoicesBean<String>> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant ROOT_CHOICE_ID. */
	public static final String ROOT_CHOICE_ID = "rootChoice";

	/** The root choice. */
	@Getter
	private final DropDownChoice<String> rootChoice;

	/** The root renderer. */
	@Getter
	private final IChoiceRenderer<String> rootRenderer;

	/** The wmc root choice. */
	@Getter
	private WebMarkupContainer wmcRootChoice;

	/** The wmc child choice. */
	@Getter
	private WebMarkupContainer wmcChildChoice;

	/** The Label for root component. */
	@Getter
	protected Label rootLabel;

	/** The Label for child component. */
	@Getter
	protected Label childLabel;

	/** The {@link AutoCompleteTextField} for the zipcode. */
	@Getter
	private final AutoCompleteTextField<String> zipcode;

	/**
	 * Instantiates a new dropdown autocomplete text field panel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param rootRenderer
	 *            the root renderer
	 * @param rootLabelModel
	 *            the root label model
	 * @param childLabelModel
	 *            the child label model
	 * @param locationModel
	 *            the location model
	 */
	public DropdownAutocompleteTextFieldPanel(final String id, final IModel<TwoDropDownChoicesBean<String>> model,
			final IChoiceRenderer<String> rootRenderer, final IModel<String> rootLabelModel,
			final IModel<String> childLabelModel) {
		super(id, Args.notNull(model, "model"));
		this.rootRenderer = rootRenderer;

		rootChoice = newRootChoice(ROOT_CHOICE_ID, getModel());

		add(wmcRootChoice = ComponentFactory.newWebMarkupContainer("wmcRootChoice"));
		wmcRootChoice.add(rootLabel = newRootLabel(rootChoice.getMarkupId(), rootLabelModel));
		wmcRootChoice.add(rootChoice);

		zipcode = newAutoCompleteTextField("zipcode", getModel());

		add(wmcChildChoice = ComponentFactory.newWebMarkupContainer("wmcChildChoice", getModel()));
		wmcChildChoice.add(childLabel = newChildLabel(zipcode.getMarkupId(), childLabelModel));
		wmcChildChoice.add(zipcode);

	}

	/**
	 * Factory method for creating the new child {@link AutoCompleteTextField}.
	 * This method is invoked in the constructor from the derived classes and
	 * can be overridden so users can provide their own version of a new child
	 * {@link AutoCompleteTextField}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new child {@link AutoCompleteTextField}.
	 */
	protected AutoCompleteTextField<String> newAutoCompleteTextField(final String id, final IModel<TwoDropDownChoicesBean<String>> model) {

		final IModel<String> selectedChildOptionModel = new PropertyModel<>(model,
			"selectedChildOption");
		final DefaultCssAutoCompleteTextField<String> autoCompleteTextField = new DefaultCssAutoCompleteTextField<String>(
				id, selectedChildOptionModel) {

			private static final long serialVersionUID = 1L;

			@Override
			protected Iterator<String> getChoices(final String input) {
				if (Strings.isEmpty(input)) {
					final List<String> emptyList = Collections.emptyList();
					return emptyList.iterator();
				}

				final List<String> choices = new ArrayList<>(10);

				final List<String> childChoices = DropdownAutocompleteTextFieldPanel.this.getModelObject()
						.getChildChoices();
				for (final String choice : childChoices) {
					if (choice.toUpperCase().startsWith(input.toUpperCase())) {
						choices.add(choice);
						if (choices.size() == 20) {
							break;
						}
					}
				}
				return choices.iterator();
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void convertInput() {
				String convertedInput = getConvertedInput();
				if (convertedInput == null) {
					final String[] inputArray = getInputAsArray();
					convertedInput = convertChoiceValue(inputArray);
					if(DropdownAutocompleteTextFieldPanel.this.getModelObject()!=null) {
						DropdownAutocompleteTextFieldPanel.this.getModelObject().setSelectedChildOption(convertedInput);
						setConvertedInput(
								DropdownAutocompleteTextFieldPanel.this.getModelObject().getSelectedChildOption());
					}
				} else {
					setConvertedInput(convertedInput);
				}
			}

			/**
			 * Converts the given choice value array to the specific type.
			 *
			 * @param value
			 *            the value
			 * @return the converted value to the specific type
			 */
			protected String convertChoiceValue(final String[] value) {
				return value != null && value.length > 0 && value[0] != null ? trim(value[0]) : null;
			}
		};
		autoCompleteTextField.setOutputMarkupId(true);
		autoCompleteTextField.add(new AjaxFormComponentUpdatingBehavior("change") {
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onError(final AjaxRequestTarget target, final RuntimeException e) {
				DropdownAutocompleteTextFieldPanel.this.onChildChoiceError(target, e);
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onUpdate(final AjaxRequestTarget target) {
				DropdownAutocompleteTextFieldPanel.this.onChildChoiceUpdate(target);
			}
		});
		return autoCompleteTextField;
	}

	/**
	 * Factory method for creating the root Label. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a Label.
	 *
	 * @param forId
	 *            the for id
	 * @param model
	 *            the model
	 * @return the label
	 */
	protected Label newRootLabel(final String forId, final IModel<String> model) {
		return ComponentFactory.newLabel("rootLabel", forId, model);
	}

	/**
	 * Factory method for creating the root Label. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a Label.
	 *
	 * @param forId
	 *            the for id
	 * @param model
	 *            the model
	 * @return the label
	 */
	protected Label newChildLabel(final String forId, final IModel<String> model) {
		return ComponentFactory.newLabel("childLabel", forId, model);
	}

	/**
	 * Factory method for creating the new root {@link DropDownChoice}. This
	 * method is invoked in the constructor from the derived classes and can be
	 * overridden so users can provide their own version of a new root
	 * {@link DropDownChoice}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new root {@link DropDownChoice}.
	 */
	protected DropDownChoice<String> newRootChoice(final String id,
			final IModel<TwoDropDownChoicesBean<String>> model) {
		final IModel<String> selectedRootOptionModel = PropertyModel.of(model, "selectedRootOption");
		final IModel<List<String>> rootChoicesModel = PropertyModel.of(model, "rootChoices");

		final DropDownChoice<String> rc = new LocalisedDropDownChoice<String>(id, selectedRootOptionModel,
				rootChoicesModel, this.rootRenderer) {

			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void convertInput() {
				String convertedInput = getConvertedInput();
				if (convertedInput == null) {
					final String[] inputArray = getInputAsArray();
					convertedInput = convertChoiceValue(inputArray);
					if(DropdownAutocompleteTextFieldPanel.this.getModelObject()!=null) {
						DropdownAutocompleteTextFieldPanel.this.getModelObject().setSelectedRootOption(convertedInput);
						setConvertedInput(DropdownAutocompleteTextFieldPanel.this.getModelObject().getSelectedRootOption());
					}
				} else {
					setConvertedInput(convertedInput);
				}
			}

			@Override
			public List<? extends String> getChoices() {
				List<? extends String> choices = rootChoicesModel.getObject();
				if(choices == null) {
					choices = new ArrayList<>();
				}
				return choices;
			}

			/**
			 * Converts the given choice value array to the specific type.
			 *
			 * @param value
			 *            the value
			 * @return the converted value to the specific type
			 */
			protected String convertChoiceValue(final String[] value) {
				return value != null && value.length > 0 && value[0] != null ? trim(value[0]) : null;
			}
		};
		rc.add(new AjaxFormComponentUpdatingBehavior("change") {
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onError(final AjaxRequestTarget target, final RuntimeException e) {
				DropdownAutocompleteTextFieldPanel.this.onRootChoiceError(target, e);
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onUpdate(final AjaxRequestTarget target) {
				DropdownAutocompleteTextFieldPanel.this.onRootChoiceUpdate(target);
			}
		});
		return rc;
	}

	/**
	 * Callback method that can be overwritten to handle any error resulting
	 * from updating the root choice.
	 *
	 * @param target
	 *            the current request handler
	 * @param e
	 *            the {@link RuntimeException} error that occurred during the
	 *            update of the component.
	 */
	protected void onRootChoiceError(final AjaxRequestTarget target, final RuntimeException e) {
	}

	/**
	 * Callback method that can be overwritten to provide an additional action
	 * when root choice has updated.
	 *
	 * @param target
	 *            the current request handler
	 */
	protected void onRootChoiceUpdate(final AjaxRequestTarget target) {
		DropdownAutocompleteTextFieldPanel.this.getModelObject().setSelectedChildOption("");
		zipcode.setModelObject(getModelObject().getSelectedChildOption());
		target.add(DropdownAutocompleteTextFieldPanel.this.zipcode);
	}

	/**
	 * Callback method that can be overwritten to handle any error resulting
	 * from updating the child choice.
	 *
	 * @param target
	 *            the current request handler
	 * @param e
	 *            the {@link RuntimeException} error that occurred during the
	 *            update of the component.
	 */
	protected void onChildChoiceError(final AjaxRequestTarget target, final RuntimeException e) {
	}

	/**
	 * Callback method that can be overwritten to provide an additional action
	 * when child choice has updated.
	 *
	 * @param target
	 *            the current request handler
	 */
	protected void onChildChoiceUpdate(final AjaxRequestTarget target) {
	}

}
