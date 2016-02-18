package de.alpharogroup.wicket.bootstrap2.components.labeled.textfield;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.behaviors.JQueryJsAppenderBehavior;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledTextFieldPanel;

public class BootstrapLabeledTextFieldPanel<T, M> extends LabeledTextFieldPanel<T, M>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	public BootstrapLabeledTextFieldPanel(final String id, final IModel<M> model,
		final IModel<String> labelModel, final IModel<String> placeholderModel)
	{
		super(id, model, labelModel);
		// Add bootstrap css...
		add(new JQueryJsAppenderBehavior("wrap", "<div class=\"control-group\"></div>"));
		getLabelComponent().add(new AttributeAppender("class", " control-label"));
		getTextField().add(new JQueryJsAppenderBehavior("wrap", "<div class=\"controls\"></div>"));
		getTextField().add(new AttributeAppender("class", " input-xlarge"));
		if (placeholderModel != null)
		{
			getTextField().add(new AttributeAppender("placeholder", placeholderModel));
		}

	}
}
