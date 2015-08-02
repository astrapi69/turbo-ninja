package de.alpharogroup.wicket.bootstrap3.components.labeled.textfield;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.behaviors.JQueryJsAppenderBehavior;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledEmailTextFieldPanel;


public class BootstrapLabeledEmailTextfieldPanel<T> extends LabeledEmailTextFieldPanel<T>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	public BootstrapLabeledEmailTextfieldPanel(final String id, final IModel<T> model,
		final IModel<String> labelModel, final IModel<String> placeholderModel)
	{
		super(id, model, labelModel);
		// Add bootstrap css...
		add(new JQueryJsAppenderBehavior("wrap", "<div class=\"control-group\"></div>"));
		getLabelComponent().add(new AttributeAppender("class", " control-label"));
		getEmailTextField().add(
			new JQueryJsAppenderBehavior("wrap", "<div class=\"controls\"></div>"));
		getEmailTextField().add(new AttributeAppender("class", " input-xlarge"));
		if (placeholderModel != null)
		{
			getEmailTextField().add(new AttributeAppender("placeholder", placeholderModel));
		}

	}
}
