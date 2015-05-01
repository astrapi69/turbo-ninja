package de.alpharogroup.wicket.bootstrap3.components.labeled.textfield;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.IModel;
import de.alpharogroup.wicket.behaviors.AddJsQueryBehavior;

import de.alpharogroup.wicket.components.labeled.textfield.LabeledTextFieldPanel;

public class BootstrapLabeledTextFieldPanel<T>  extends LabeledTextFieldPanel<T> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	public BootstrapLabeledTextFieldPanel(String id, IModel<T> model, IModel<String> labelModel, IModel<String> placeholderModel) {
		super(id, model, labelModel);
		// Add bootstrap css...	
		add(new AddJsQueryBehavior("wrap", "<div class=\"control-group\"></div>"));
		getLabelComponent().add(new AttributeAppender("class", " control-label"));
		getTextField().add(new AddJsQueryBehavior("wrap", "<div class=\"controls\"></div>"));
		getTextField().add(new AttributeAppender("class", " input-xlarge"));
		if(placeholderModel != null){
			getTextField().add(new AttributeAppender("placeholder", placeholderModel));
		}
		
	}
}
