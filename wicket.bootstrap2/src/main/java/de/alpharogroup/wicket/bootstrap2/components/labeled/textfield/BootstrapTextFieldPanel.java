package de.alpharogroup.wicket.bootstrap2.components.labeled.textfield;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.components.labeled.textfield.LabeledTextFieldPanel;

public class BootstrapTextFieldPanel<T, M> extends LabeledTextFieldPanel<T, M>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	public BootstrapTextFieldPanel(final String id, final IModel<M> model,
		final IModel<String> labelModel)
	{
		super(id, model, labelModel);
		// Add bootstrap css...
		getLabelComponent().add(new AttributeAppender("class", " control-label"));
	}

}
