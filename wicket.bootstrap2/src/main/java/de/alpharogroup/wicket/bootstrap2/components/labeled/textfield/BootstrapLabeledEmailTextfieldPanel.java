package de.alpharogroup.wicket.bootstrap2.components.labeled.textfield;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.IModel;
import org.jaulp.wicket.behaviors.AddJsQueryBehavior;
import org.jaulp.wicket.components.labeled.textfield.LabeledEmailTextFieldPanel;


public class BootstrapLabeledEmailTextfieldPanel<T>  extends LabeledEmailTextFieldPanel<T> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	public BootstrapLabeledEmailTextfieldPanel(String id, IModel<T> model, IModel<String> labelModel, IModel<String> placeholderModel) {
		super(id, model, labelModel);
		// Add bootstrap css...	
		add(new AddJsQueryBehavior("wrap", "<div class=\"control-group\"></div>"));
		getLabelComponent().add(new AttributeAppender("class", " control-label"));
		getEmailTextField().add(new AddJsQueryBehavior("wrap", "<div class=\"controls\"></div>"));		
		getEmailTextField().add(new AttributeAppender("class", " input-xlarge"));
		if(placeholderModel != null){
			getEmailTextField().add(new AttributeAppender("placeholder", placeholderModel));
		}
		
	}
}
