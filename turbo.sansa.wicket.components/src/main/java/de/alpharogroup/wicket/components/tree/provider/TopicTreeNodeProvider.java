package de.alpharogroup.wicket.components.tree.provider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import net.sourceforge.jaulp.locale.ResourceBundleUtils;
import net.sourceforge.jaulp.tree.ifaces.ITreeNode;

import org.apache.wicket.Session;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.tree.ISortableTreeProvider;
import org.apache.wicket.extensions.markup.html.repeater.util.SingleSortState;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import events.system.model.Topics;

/**
 * The Class TopicTreeNodeProvider.
 * 
 * @author Asterios Raptis
 */
public class TopicTreeNodeProvider implements
		ISortableTreeProvider<ITreeNode<Topics>, String> {

	/**
	 * The Class TreeNodeModel.
	 * 
	 * @author Asterios Raptis
	 */
	private class TreeNodeModel extends
			LoadableDetachableModel<ITreeNode<Topics>> {

		/**
		 * The serialVersionUID.
		 */
		private static final long serialVersionUID = 1L;

		/** The id. */
		private String id;

		/**
		 * Instantiates a new tree node model.
		 * 
		 * @param treeNode
		 *            the tree node
		 */
		public TreeNodeModel(final ITreeNode<Topics> treeNode) {
			super(treeNode);
			id = treeNode.getValue().getId().toString();
			final String propertiesKey = treeNode.getValue().getName();
			String topicTreeName = ResourceBundleUtils.getString(ResourceBundle.getBundle(
					TopicsTreePanel.class.getName(), Session.get().getLocale()), propertiesKey);
			treeNode.setDisplayValue(topicTreeName);
		}	

		/**
		 * {@inheritDoc}.
		 * 
		 * @param obj
		 *            the obj
		 * @return true, if successful
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(final Object obj) {
			if (obj instanceof TreeNodeModel) {
				return ((TreeNodeModel) obj).id.equals(id);
			}
			return false;
		}

		/**
		 * {@inheritDoc}.
		 * 
		 * @return the int
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}

		/**
		 * {@inheritDoc}.
		 * 
		 * @return the tree node
		 * @see org.apache.wicket.model.LoadableDetachableModel#load()
		 */
		@Override
		protected ITreeNode<Topics> load() {
			return get(id);
		}
	}

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * All root {@link Topics}s.
	 */
	private List<ITreeNode<Topics>> roots;

	/** The sort state. */
	private SingleSortState<String> sortState;

	/**
	 * Instantiates a new topic tree node provider.
	 * 
	 * @param root
	 *            the root
	 */
	public TopicTreeNodeProvider(final ITreeNode<Topics> root) {
		super();
		roots = new ArrayList<ITreeNode<Topics>>();
		roots.add(root);
		sortState = new SingleSortState<String>();
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @see org.apache.wicket.model.IDetachable#detach()
	 */
	@Override
	public void detach() {
		// Not implemented...
	}

	/**
	 * Gets the.
	 * 
	 * @param treeNodes
	 *            the tree nodes
	 * @param id
	 *            the id
	 * @return the tree node
	 */
	private ITreeNode<Topics> get(final List<ITreeNode<Topics>> treeNodes,
			final String id) {
		for (final ITreeNode<Topics> treeNode : treeNodes) {
			if (treeNode.getValue().getId().toString().equals(id)) {
				return treeNode;
			}

			final ITreeNode<Topics> tmp = get(treeNode.getChildren(), id);
			if (tmp != null) {
				return tmp;
			}
		}
		return null;
	}

	/**
	 * Get a {@link Foo} by its id.
	 * 
	 * @param id
	 *            the id
	 * @return the topics
	 */
	public ITreeNode<Topics> get(final String id) {
		return get(roots, id);
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @param treeNode
	 *            the tree node
	 * @return the children
	 * @see wickettree.ITreeProvider#getChildren(java.lang.Object)
	 */
	@Override
	public Iterator<? extends ITreeNode<Topics>> getChildren(
			final ITreeNode<Topics> treeNode) {
		return treeNode.getChildren().iterator();
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @return the roots
	 * @see wickettree.ITreeProvider#getRoots()
	 */
	@Override
	public Iterator<? extends ITreeNode<Topics>> getRoots() {
		return roots.iterator();
	}

	/**
	 * Returns current sort sortState.
	 * 
	 * @return current sort sortState
	 */
	public SortParam<String> getSort() {
		return sortState.getSort();
	}

	/**
	 * Gets the sort state.
	 * 
	 * @return the sort state
	 * @see ISortableDataProvider#getSortState()
	 */
	@Override
	public final ISortState<String> getSortState() {
		return sortState;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @param treeNode
	 *            the tree node
	 * @return true, if successful
	 * @see wickettree.ITreeProvider#hasChildren(java.lang.Object)
	 */
	@Override
	public boolean hasChildren(final ITreeNode<Topics> treeNode) {
		return treeNode.hasChildren();
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @param treeNode
	 *            the tree node
	 * @return the i model
	 * @see wickettree.ITreeProvider#model(java.lang.Object)
	 */
	@Override
	public IModel<ITreeNode<Topics>> model(final ITreeNode<Topics> treeNode) {
		return new TreeNodeModel(treeNode);
	}

	/**
	 * Sets the current sort sortState.
	 * 
	 * @param param
	 *            parameter containing new sorting information
	 */
	public void setSort(final SortParam<String> param) {
		sortState.setSort(param);
	}

	/**
	 * Sets the current sort sortState.
	 * 
	 * @param property
	 *            sort property
	 * @param ascending
	 *            sort direction
	 */
	public void setSort(final String property, final boolean ascending) {
		setSort(new SortParam<String>(property, ascending));
	}

	/**
	 * Sets the sort state.
	 * 
	 * @param state
	 *            the new sort state
	 * @see ISortableDataProvider#setSortState(org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState)
	 */
	public final void setSortState(final ISortState<String> state) {
		if (!(state instanceof SingleSortState)) {
			throw new IllegalArgumentException(
					"argument [sortState] must be an instance of SingleSortState, but it is ["
							+ state.getClass().getName() + "]:["
							+ state.toString() + "]");
		}
		sortState = (SingleSortState<String>) state;
	}

}
