package de.alpharogroup.wicket.components.address.countries;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.jaulp.wicket.components.i18n.dropdownchoice.panels.TwoDropDownChoicesPanel;
import org.jaulp.wicket.model.dropdownchoices.StringTwoDropDownChoicesModel;

/**
 * The class CountriesProvincesPanel.
 * 
 * @author Asterios Raptis
 */
public class CountriesProvincesPanel extends TwoDropDownChoicesPanel {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The wmc root choice. */
	private WebMarkupContainer wmcRootChoice;

	/** The wmc child choice. */
	private WebMarkupContainer wmcChildChoice;

	/**
	 * Instantiates a new countries provinces panel.
	 * 
	 * @param id
	 *            the id
	 * @param stringTwoDropDownChoicesModel
	 *            the string two drop down choices model
	 * @param rootRenderer
	 *            the root renderer
	 * @param childRenderer
	 *            the child renderer
	 */
	public CountriesProvincesPanel(String id,
			StringTwoDropDownChoicesModel stringTwoDropDownChoicesModel,
			IChoiceRenderer<String> rootRenderer,
			IChoiceRenderer<String> childRenderer) {
		super(id, stringTwoDropDownChoicesModel, rootRenderer, childRenderer);
		wmcRootChoice = new WebMarkupContainer("wmcRootChoice");
		add(wmcRootChoice);
		wmcRootChoice.add(this.getRootChoice());
		wmcChildChoice = new WebMarkupContainer("wmcChildChoice");
		add(wmcChildChoice);
		wmcChildChoice.add(this.getChildChoice());
	}

	/**
	 * Gets the wmc child choice.
	 * 
	 * @return the wmc child choice
	 */
	public WebMarkupContainer getWmcChildChoice() {
		return wmcChildChoice;
	}

	/**
	 * Gets the wmc root choice.
	 * 
	 * @return the wmc root choice
	 */
	public WebMarkupContainer getWmcRootChoice() {
		return wmcRootChoice;
	}

}
