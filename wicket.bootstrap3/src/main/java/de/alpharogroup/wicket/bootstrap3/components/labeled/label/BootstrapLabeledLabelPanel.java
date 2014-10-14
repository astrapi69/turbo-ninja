package de.alpharogroup.wicket.bootstrap3.components.labeled.label;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.components.labeled.label.LabeledLabelPanel;

/**
 * Convenience class for labeled Label for form uneditable components. Adds some bootstrap css.
 * 
 * @param <T>
 *            the generic type
 */
public class BootstrapLabeledLabelPanel<T> extends LabeledLabelPanel<T> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new LabeledDateTextfieldPanel.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the label model
	 */
	public BootstrapLabeledLabelPanel(String id, IModel<T> model,
			IModel<String> labelModel) {
		super(id, model, labelModel);
		getLabel().add(new AttributeAppender("class", "span2 "));
	}
}
