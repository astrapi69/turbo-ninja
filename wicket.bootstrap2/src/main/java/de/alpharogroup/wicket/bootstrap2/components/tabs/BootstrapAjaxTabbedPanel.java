package de.alpharogroup.wicket.bootstrap2.components.tabs;

import java.util.List;

import org.apache.wicket.extensions.markup.html.tabs.TabbedPanel;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.util.Attributes;
import de.alpharogroup.wicket.components.ajax.editable.tabs.AjaxCloseableTabbedPanel;
import de.alpharogroup.wicket.components.ajax.editable.tabs.ICloseableTab;

/**
 * Styled version of {@link TabbedPanel}.
 *
 * @param <T> the generic type
 */
public class BootstrapAjaxTabbedPanel<T extends ICloseableTab> extends AjaxCloseableTabbedPanel<T> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

    public BootstrapAjaxTabbedPanel(String id, List<T> tabs) {
        super(id, tabs);

        commonInit();
    }

    public BootstrapAjaxTabbedPanel(String id, List<T> tabs, IModel<Integer> model) {
        super(id, tabs, model);

        commonInit();
    }

    /**
     * common initializer.
     */
    private void commonInit() {
        BootstrapBaseBehavior.addTo(this);
    }

    /**
     * On component tag.
     *
     * @param tag the tag
     */
    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);
        checkComponentTag(tag, "div");
        Attributes.addClass(tag, getTabbedPanelCssClass());
    }

    /**
     * Gets the TabbedPanel css class.
     *
     * @return the TabbedPanel css class
     */
    protected String getTabbedPanelCssClass() {
        return "tabbable";
    }

    /**
     * Gets the selected tab css class.
     *
     * @return the selected tab css class
     */
    @Override
    protected String getSelectedTabCssClass() {
        return "active";
    }

    /**
     * Gets the last tab css class.
     *
     * @return the last tab css class
     */
    @Override
    protected String getLastTabCssClass() {
        return "";
    }
}