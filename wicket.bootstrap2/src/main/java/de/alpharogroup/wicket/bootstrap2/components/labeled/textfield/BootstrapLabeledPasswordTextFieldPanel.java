package de.alpharogroup.wicket.bootstrap2.components.labeled.textfield;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.IModel;
import de.alpharogroup.wicket.behaviors.JQueryJsAppenderBehavior;

import de.alpharogroup.wicket.components.labeled.textfield.LabeledPasswordTextFieldPanel;

public class BootstrapLabeledPasswordTextFieldPanel<T> extends
		LabeledPasswordTextFieldPanel<T> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	public BootstrapLabeledPasswordTextFieldPanel(String id,
			IModel<T> model, IModel<String> labelModel, IModel<String> placeholderModel) {
		super(id, model, labelModel);
		// Add bootstrap css...	
		add(new JQueryJsAppenderBehavior("wrap", "<div class=\"control-group\"></div>"));
		getLabelComponent().add(new AttributeAppender("class", " control-label"));
		getPasswordTextField().add(new JQueryJsAppenderBehavior("wrap", "<div class=\"controls\"></div>"));
		getPasswordTextField().add(new AttributeAppender("class", " input-xlarge"));
		if(placeholderModel != null) {
			getPasswordTextField().add(new AttributeAppender("placeholder", placeholderModel));
		}
	}

}
