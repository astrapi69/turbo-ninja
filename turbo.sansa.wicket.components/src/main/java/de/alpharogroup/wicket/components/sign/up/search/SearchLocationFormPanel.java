package de.alpharogroup.wicket.components.sign.up.search;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.alpharogroup.address.book.application.model.LocationModel;
import de.alpharogroup.address.book.entities.Addresses;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.sign.up.SignUpModel;

public abstract class SearchLocationFormPanel extends Panel
{
	private static final long serialVersionUID = 1L;
	private final AjaxButton submitButton;
	/** The button label. */
	private final Label buttonLabel;
	private final LocationPanel locationPanel;

	public LocationPanel getLocationPanel()
	{
		return locationPanel;
	}

	private final Form<SignUpModel> form;

	public SearchLocationFormPanel(final String id, final IModel<LocationModel<Addresses>> model)
	{
		super(id);

		add(form = newForm("form"));
		form.setOutputMarkupId(true);
		locationPanel = newLocationPanel("locationPanel", model);
		form.add(locationPanel);

		// Create submit button for the form
		submitButton = newButton("submitButton", model);
		buttonLabel = newButtonLabel("buttonLabel", newButtonResourceKey(), "Auswählen", this);
		submitButton.add(buttonLabel);
		form.add(submitButton);
	}

	protected abstract LocationPanel newLocationPanel(final String id,
		final IModel<? extends LocationModel<Addresses>> model);

	protected AjaxButton newButton(final String id, final IModel<LocationModel<Addresses>> model)
	{

		return new IndicatingAjaxButton(id, form)
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onError(final AjaxRequestTarget target, final Form<?> form)
			{
			}

			@Override
			protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				target.add(form);
				final LocationModel<Addresses> object = model.getObject();
				final String countryName = locationPanel.getDropDownChoiceTextFieldPanel()
					.getStringTwoDropDownChoicesModel().getSelectedRootOption();
				object.setSelectedCountryName(countryName);
				final String location = locationPanel.getDropDownChoiceTextFieldPanel()
					.getZipcode().getDefaultModelObjectAsString();

				object.setLocation(location);
				onSearch(target, model.getObject());
			}
		};
	}

	/**
	 * New form.
	 *
	 * @param id
	 *            the id
	 * @return the form
	 */
	protected Form<SignUpModel> newForm(final String id)
	{
		final Form<SignUpModel> form = new Form<SignUpModel>(id);
		form.setOutputMarkupId(true);
		return form;
	}

	/**
	 * Factory method for creating the Label. This method is invoked in the constructor from the
	 * derived classes and can be overridden so users can provide their own version of a Label.
	 *
	 * @param id
	 *            the id
	 * @param resourceKey
	 *            the resource key
	 * @param defaultValue
	 *            the default value
	 * @param component
	 *            the component
	 * @return the label
	 */
	protected Label newButtonLabel(final String id, final String resourceKey,
		final String defaultValue, final Component component)
	{
		final IModel<String> labelModel = ResourceModelFactory.newResourceModel(resourceKey,
			component, defaultValue);
		final Label label = new Label(id, labelModel);
		label.setOutputMarkupId(true);
		return label;
	}

	protected String newButtonResourceKey()
	{
		return "global.choose.label";
	}

	public AjaxButton getSubmitButton()
	{
		return submitButton;
	}

	public abstract void onSearch(final AjaxRequestTarget target, final LocationModel<Addresses> object);

}
