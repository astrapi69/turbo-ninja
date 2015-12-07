package de.alpharogroup.wicket.bootstrap3.components.navbar.dropdown;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.link.LinkItem;
import lombok.Getter;

/**
 * The class {@link NavbarDropdownPanel}.
 */
public class NavbarDropdownPanel extends Panel
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The list view. */

	/**
	 * Gets the list view.
	 *
	 * @return the list view
	 */
	@Getter
	private ListView<LinkItem> listView;

	/** The list view container. */

	/**
	 * Gets the list view container.
	 *
	 * @return the list view container
	 */
	@Getter
	private WebMarkupContainer listViewContainer;

	/**
	 * Gets the dropdown link.
	 *
	 * @return the dropdown link
	 */
	@Getter
	private WebMarkupContainer dropdownLink;

	/**
	 * Gets the dropdown link label.
	 *
	 * @return the dropdown link label
	 */
	@Getter
	private Label dropdownLinkLabel;

	/**
	 * Instantiates a new {@link NavbarDropdownPanel}.
	 *
	 * @param id the id
	 * @param model the model
	 * @param dropdownLinkLabelModel the dropdown link label model
	 */
	public NavbarDropdownPanel(final String id, final IModel<List<LinkItem>> model, final IModel<String> dropdownLinkLabelModel)
	{
		super(id, model);
		add(dropdownLink = newdropdownLink("dropdownLink"));
		dropdownLink.add(dropdownLinkLabel = newdropdownLinkLabel("dropdownLinkLabel", dropdownLinkLabelModel));
		add(listViewContainer = newListViewContainer("listViewContainer"));
		listViewContainer.add(listView = newListView("listView", model));
	}

	/**
	 * Instantiates a new {@link newdropdownLinkLabel}.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the label
	 */
	protected Label newdropdownLinkLabel(final String id, final IModel<String> model)
	{
		return ComponentFactory.newLabel(id, model);
	}

	/**
	 * Instantiates a new {@link newdropdownLink}.
	 *
	 * @param id the id
	 * @return the web markup container
	 */
	protected WebMarkupContainer newdropdownLink(final String id)
	{
		return ComponentFactory.newWebMarkupContainer(id);
	}

	/**
	 * Factory method for creating a new {@link WebMarkupContainer}. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of the
	 * new {@link WebMarkupContainer}.
	 *
	 * @param id
	 *            the id
	 * @return the new {@link WebMarkupContainer}.
	 */
	protected WebMarkupContainer newListViewContainer(final String id)
	{
		final WebMarkupContainer container = ComponentFactory.newWebMarkupContainer(id);
		container.add(new AttributeAppender("class", "dropdown-menu"));
		return container;
	}

	/**
	 * Factory method for creating a new {@link ListView}. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of the
	 * new {@link ListView}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link ListView}.
	 */
	protected ListView<LinkItem> newListView(final String id, final IModel<List<LinkItem>> model)
	{
		final ListView<LinkItem> listView = new ListView<LinkItem>(id, model)
		{
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void populateItem(final ListItem<LinkItem> item)
			{
				item.add(newListComponent("item", item));
			}
		};
		listView.setReuseItems(true);
		return listView;
	}

	/**
	 * Factory method for creating the new item {@link AbstractLink} in the list. This method is
	 * invoked in the constructor from the derived classes and can be overridden so users can
	 * provide their own version of a new item {@link AbstractLink} in the list.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new item {@link AbstractLink} in the list.
	 */
	protected AbstractLink newAbstractLink(final String id, final LinkItem model)
	{
		AttributeModifier target = null;
		AbstractLink link = null;
		if ((model.getTarget() != null) && !model.getTarget().isEmpty())
		{
			target = new AttributeModifier("target", Model.of(model.getTarget()));
		}
		if (model.getUrl() != null)
		{
			link = new ExternalLink(id, Model.of(model.getUrl()));
		}
		if (link == null)
		{
			link = new BookmarkablePageLink<String>(id, model.getPageClass());
		}
		// if target not null then set it...
		if (target != null)
		{
			link.add(target);
		}
		link.setOutputMarkupId(true);
		return link;
	}

	/**
	 * Factory method for create a new item link {@link Label}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new item link {@link Label}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new item link {@link Label}.
	 */
	protected Label newItemLinkLabel(final String id, final LinkItem model)
	{
		final Label itemLinkLabel = ComponentFactory.newLabel(id,
			ResourceModelFactory.newResourceModel(model.getResourceModelKey(), this));
		return itemLinkLabel;
	}

	/**
	 * Factory method for creating the new {@link Component} in the list. This method is
	 * invoked in the {@link ListView#populateItem(ListItem)} from the derived classes and can be
	 * overridden so users can provide their own version of a new {@link Component} in the list.
	 *
	 * @param id
	 *            the id
	 * @param item
	 *            the item
	 * @return the new {@link Component} in the list.
	 */
	protected Component newListComponent(final String id, final ListItem<LinkItem> item)
	{
		final LinkItem model = item.getModelObject();
		final Label itemLinkLabel = newItemLinkLabel("itemLinkLabel", model);
		final AbstractLink link = newAbstractLink(id, model);
		link.add(itemLinkLabel);
		return link;
	}

}
