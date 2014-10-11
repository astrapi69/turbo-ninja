package de.alpharogroup.wicket.components.infringement.form;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;

import user.management.application.models.InfringementModel;
import de.alpharogroup.wicket.components.infringement.input.InfringementInputPanel;

public abstract class InfringementFormPanel extends Panel {
	private static final long serialVersionUID = 1L;

	/** The button label. */
	private final Label buttonLabel;
	/** The form. */
	private final Form<?> form;
	
	private final Component infringementInputPanel;

	/** The submit button. */
	private final Button submitButton;
	public InfringementFormPanel(String id, IModel<InfringementModel> model) {
		super(id, model);
		add(form = newForm("form", model));
		form.add(infringementInputPanel = newInfringementInputPanel("infringementInputPanel", model));
		form.add(submitButton = newButton("submitButton"));
		submitButton.add(buttonLabel = newButtonLabel("buttonLabel", 
				newButtonResourceKey(),
				"Send", this));	
	}
	
	protected String newButtonResourceKey() {
		return "global.button.send.email.label";		
	}

	public Label getButtonLabel() {
		return buttonLabel;
	}

	public Button getSubmitButton() {
		return submitButton;
	}

	public Form<?> getForm() {
		return form;
	}

	public Component getInfringementInputPanel() {
		return infringementInputPanel;
	}
	
	protected Component newInfringementInputPanel(String id, IModel<InfringementModel> model) {
		return new InfringementInputPanel(id, model);
	}

	protected Button newButton(String id) {
		return new Button(id) {
			private static final long serialVersionUID = 1L;

			public void onSubmit() {
				onFormSubmit();
			}
		};
	}

	/**
	 * Factory method for creating the Label. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a Label.
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
	protected Label newButtonLabel(String id, final String resourceKey,
			final String defaultValue, final Component component) {
		final IModel<String> labelModel = ResourceModelFactory.newResourceModel(resourceKey, component, defaultValue);		
		Label label = new Label(id, labelModel);
		label.setOutputMarkupId(true);
		return label;
	}

	/**
	 * New form.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the form
	 */
	@SuppressWarnings("unchecked")
	protected Form<?> newForm(String id, IModel<?> model) {
		return new Form<InfringementModel>(id,
				(IModel<InfringementModel>) model);
	}

	protected abstract void onFormSubmit();

}
