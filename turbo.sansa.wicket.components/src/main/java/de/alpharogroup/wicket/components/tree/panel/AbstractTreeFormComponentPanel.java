package de.alpharogroup.wicket.components.tree.panel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.markup.html.repeater.tree.AbstractTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.ITreeProvider;
import org.apache.wicket.extensions.markup.html.repeater.tree.theme.HumanTheme;
import org.apache.wicket.extensions.markup.html.repeater.tree.theme.WindowsTheme;
import org.apache.wicket.extensions.markup.html.repeater.util.ProviderSubset;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IDetachable;
import org.apache.wicket.model.IModel;

import de.alpharogroup.io.annotations.ImportResource;
import de.alpharogroup.io.annotations.ImportResources;
import de.alpharogroup.wicket.components.tree.model.Content;

/**
 * The Class AbstractTreePanel.
 * 
 * @param <T>
 *            the generic type
 * @author Asterios Raptis
 */
@ImportResources(resources = { @ImportResource(resourceName = "AbstractTreePanel.css", resourceType = "css") })
public abstract class AbstractTreeFormComponentPanel<T> extends FormComponentPanel<T>
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The content. Initialize from constructor. */
	private Content<T> content;

	/** The contents. */
	protected List<Content<T>> contents;

	/** The treeProvider. Initialize from constructor. */
	private ITreeProvider<T> treeProvider;

	/** The state. Initialize from constructor. */
	private Set<T> state;

	/** The theme. */
	private Behavior theme;

	/** The themes. */
	private List<Behavior> themes;

	/** The tree. Initialize from constructor through the abstract method. */
	private AbstractTree<T> tree;

	/**
	 * Instantiates a new abstract tree panel.
	 * 
	 * @param id
	 *            the id
	 * @param treeProvider
	 *            the treeProvider
	 * @param state
	 *            the state
	 * @param content
	 *            the content
	 */
	public AbstractTreeFormComponentPanel(final String id, final ITreeProvider<T> treeProvider,
		final ProviderSubset<T> state, final Content<T> content)
	{
		super(id);
		this.treeProvider = treeProvider;
		this.content = content;
		this.state = state;

		tree = createTree(treeProvider, newStateModel());
		initThemes();
		tree.add(theme);
	}

	/**
	 * Creates the tree.
	 * 
	 * @param provider
	 *            the treeProvider
	 * @param state
	 *            the state
	 * @return the abstract tree
	 */
	protected abstract AbstractTree<T> createTree(final ITreeProvider<T> provider,
		final IModel<Set<T>> state);

	/**
	 * {@inheritDoc}.
	 * 
	 * @see org.apache.wicket.Component#detachModels()
	 */
	@Override
	public void detachModels()
	{
		for (final Content<T> content : contents)
		{
			content.detach();
		}

		super.detachModels();
	}

	/**
	 * Gets the content.
	 * 
	 * @return the content
	 */
	public Content<T> getContent()
	{
		return content;
	}

	/**
	 * Gets the contents.
	 * 
	 * @return the contents
	 */
	public List<Content<T>> getContents()
	{
		return contents;
	}

	/**
	 * Gets the tree.
	 * 
	 * @return the tree
	 */
	public AbstractTree<T> getTree()
	{
		return tree;
	}

	/**
	 * Gets the treeProvider.
	 * 
	 * @return the treeProvider
	 */
	public ITreeProvider<T> getTreeProvider()
	{
		return treeProvider;
	}

	/**
	 * Inits the themes.
	 * 
	 * @return the list
	 */
	private List<Behavior> initThemes()
	{
		themes = new ArrayList<Behavior>();

		themes.add(new WindowsTheme());
		themes.add(new HumanTheme());

		theme = themes.get(0);

		return themes;
	}

	/**
	 * New content component.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the component
	 */
	protected Component newContentComponent(final String id, final IModel<T> model)
	{
		return content.newContentComponent(id, tree, model);
	}

	/**
	 * New state model.
	 * 
	 * @return the i model
	 */
	private IModel<Set<T>> newStateModel()
	{
		return new AbstractReadOnlyModel<Set<T>>()
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * Super class doesn't detach - would be nice though.
			 */
			@Override
			public void detach()
			{
				((IDetachable)state).detach();
			}

			@Override
			public Set<T> getObject()
			{
				return state;
			}
		};
	}

	/**
	 * Sets the content.
	 * 
	 * @param content
	 *            the new content
	 */
	protected void setContent(final Content<T> content)
	{
		this.content = content;
	}

	/**
	 * Sets the contents.
	 * 
	 * @param contents
	 *            the new contents
	 */
	protected abstract void setContents(final List<Content<T>> contents);

	/**
	 * Sets the tree.
	 * 
	 * @param tree
	 *            the new tree
	 */
	protected void setTree(final AbstractTree<T> tree)
	{
		this.tree = tree;
	}

}
