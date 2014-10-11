package de.alpharogroup.wicket.components.infringement;

import java.util.Arrays;
import java.util.List;

import net.sourceforge.jaulp.locale.ResourceBundleKey;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.jaulp.wicket.base.util.properties.ComponentPropertiesKeysListResolver;

import user.management.application.models.InfringementModel;
import de.alpharogroup.wicket.components.i18n.content.ContentPanel;
import de.alpharogroup.wicket.components.infringement.form.InfringementFormPanel;
import de.alpharogroup.wicket.components.listview.ListViewPanel;

/**
 * The Class InfringementPanel.
 */
public abstract class InfringementPanel extends Panel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The form panel. */
	private final Component formPanel;
	
	/** The introduction panel. */
	private final Component introductionPanel;
	
	/** The list view panel. */
	private final Component listViewPanel;
	
	/**
	 * Instantiates a new infringement panel.
	 *
	 * @param id the id
	 * @param model the model
	 */
	public InfringementPanel(String id, IModel<InfringementModel> model) {
		super(id, model);
		add(introductionPanel = newIntroductionPanel("introductionPanel", model));
		add(listViewPanel = newListViewPanel("listViewPanel", newDisplayValues()));
		add(formPanel = newFormPanel("formPanel", model));
	}

	/**
	 * Factory method to get the display values in the ListViewPanel.
	 *
	 * @return the list
	 */
	protected List<String> newDisplayValues() {
		List< ResourceBundleKey > values = Arrays.asList( 
				ResourceBundleKey.builder().key("1").build(), 
				ResourceBundleKey.builder().key("2").build(), 
				ResourceBundleKey.builder().key("3").build(), 
				ResourceBundleKey.builder().key("4").build(), 
				ResourceBundleKey.builder().key("5").build(),
				ResourceBundleKey.builder().key("6").build(), 
				ResourceBundleKey.builder().key("7").build() );
		ComponentPropertiesKeysListResolver renderer = new ComponentPropertiesKeysListResolver("infringement.list.entry", "label", this, values);
		List< String > listDisplayValues = renderer.getResultList();
		return listDisplayValues;
	}

	/**
	 * Gets the form panel.
	 *
	 * @return the form panel
	 */
	public Component getFormPanel() {
		return formPanel;
	}

	/**
	 * Gets the introduction panel.
	 *
	 * @return the introduction panel
	 */
	public Component getIntroductionPanel() {
		return introductionPanel;
	}
	

	
	/**
	 * Gets the list view panel.
	 *
	 * @return the list view panel
	 */
	public Component getListViewPanel() {
		return listViewPanel;
	}
	
	/**
	 * New form panel.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the component
	 */
	protected Component newFormPanel(String id, IModel<InfringementModel> model) {
		return new InfringementFormPanel(id, model) {
			private static final long serialVersionUID = 1L;
			protected void onFormSubmit() {
				onSend();
			}
		};
	}

	/**
	 * New introduction panel.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the component
	 */
	protected Component newIntroductionPanel(String id, IModel<?> model) {
		ContentPanel introductionPanel = new ContentPanel(id){
			private static final long serialVersionUID = 1L;

			@Override
			protected ResourceBundleKey newContentResourceKey() {
				return ResourceBundleKey.builder().key("introduction.label").build();
			}
		};
		return introductionPanel;
	}
	
	/**
	 * New list view panel.
	 *
	 * @param id the id
	 * @param list the list
	 * @return the component
	 */
	protected Component newListViewPanel(String id,
			List<String> list) {
		ListViewPanel<String> listViewPanel = new ListViewPanel<String>(id,
				list) {
			private static final long serialVersionUID = 1L;

			@Override
			protected Component newListComponent(String id,
					ListItem<String> item) {
				return new Label(id, item.getModel());
			}
		};
		return listViewPanel;
	}

	/**
	 * On send.
	 */
	protected abstract void onSend();

}
