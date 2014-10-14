package de.alpharogroup.wicket.bootstrap3.components.labeled.textarea;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.IModel;
import org.jaulp.wicket.behaviors.AddJsQueryBehavior;

import de.alpharogroup.wicket.components.labeled.textarea.LabeledTextAreaPanel;


public class BootstrapLabeledTextAreaPanel<T> extends LabeledTextAreaPanel<T> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	public BootstrapLabeledTextAreaPanel(String id, IModel<T> model, IModel<String> labelModel, IModel<String> placeholderModel) {
		super(id, model, labelModel);
		// Add bootstrap css...	
		add(new AddJsQueryBehavior("wrap", "<div class=\"control-group\"></div>"));
		getLabelComponent().add(new AttributeAppender("class", " control-label"));
		getTextArea().add(new AddJsQueryBehavior("wrap", "<div class=\"controls\"></div>"));	
		getTextArea().add(new AttributeAppender("class", " input-xlarge"));
		if(placeholderModel != null){
			getTextArea().add(new AttributeAppender("placeholder", placeholderModel));
		}
		
	}
}