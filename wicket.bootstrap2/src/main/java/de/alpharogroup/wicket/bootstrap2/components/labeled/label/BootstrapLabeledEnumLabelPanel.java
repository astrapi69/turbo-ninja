package de.alpharogroup.wicket.bootstrap2.components.labeled.label;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.IModel;
import org.jaulp.wicket.components.labeled.label.LabeledEnumLabelPanel;

/**
 * Convenience class for labeled EnumLabel and adds bootstrap css.
 * 
 * @param <T>
 *            the generic type
 */
public class BootstrapLabeledEnumLabelPanel<T> extends LabeledEnumLabelPanel<T> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new LabeledEnumLabelPanel.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param labelModel
	 *            the label model
	 */
	public BootstrapLabeledEnumLabelPanel(String id, IModel<T> model,
			IModel<String> labelModel) {
		super(id, model, labelModel);
		getLabel().add(new AttributeAppender("class", "span2 "));
	}
}
