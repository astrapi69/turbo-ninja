package de.alpharogroup.wicket.components.address.countries;

import lombok.Getter;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.IChoiceRenderer;

import de.alpharogroup.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.i18n.dropdownchoice.panels.TwoDropDownChoicesPanel;
import de.alpharogroup.wicket.model.dropdownchoices.StringTwoDropDownChoicesModel;

/**
 * The class CountriesProvincesPanel.
 * 
 * @author Asterios Raptis
 */
public class CountriesProvincesPanel extends TwoDropDownChoicesPanel<String> {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The wmc root choice. */
	@Getter
	private WebMarkupContainer wmcRootChoice;

	/** The wmc child choice. */
	@Getter
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

		add(new Label("countryLabel", ResourceModelFactory.newResourceModel(ResourceBundleKey.builder()
			.key("sem.main.address.country.label").build(), this)));
		
		add(wmcRootChoice = newWmcRootChoice("wmcRootChoice"));
		wmcRootChoice.add(this.getRootChoice());
		
		add(new Label("federalStateLabel", ResourceModelFactory.newResourceModel(ResourceBundleKey.builder()
			.key("sem.main.address.federal.state.label").build(), this)));
		wmcChildChoice = newWmcChildChoice("wmcChildChoice");
		add(wmcChildChoice);
		wmcChildChoice.add(this.getChildChoice());
	}
	
	protected WebMarkupContainer newWmcRootChoice(final String id) {
		return ComponentFactory.newWebMarkupContainer(id);
	}
	
	protected WebMarkupContainer newWmcChildChoice(final String id) {
		return ComponentFactory.newWebMarkupContainer(id);
	}

}
