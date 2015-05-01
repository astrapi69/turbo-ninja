package de.alpharogroup.wicket.components.tree.content;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.tree.AbstractTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.ITreeProvider;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.CheckedFolder;
import org.apache.wicket.extensions.markup.html.repeater.util.ProviderSubset;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.cycle.RequestCycle;

import de.alpharogroup.tree.ifaces.ITreeNode;
import events.system.model.Topics;

/**
 * The Class CheckedFolderContent.
 * 
 * @author Asterios Raptis
 */
public class CheckedFolderContent implements
		ICheckFolderContent<ITreeNode<Topics>> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The checked. */
	private ProviderSubset<ITreeNode<Topics>> checked;

	/** The topics map. */
	private final Map<ITreeNode<Topics>, Boolean> topicsMap;

	/**
	 * Instantiates a new checked folder content.
	 * 
	 * @param provider
	 *            the provider
	 * @param topicsMap
	 *            the topics map
	 */
	public CheckedFolderContent(
			final ITreeProvider<ITreeNode<Topics>> provider,
			final Map<ITreeNode<Topics>, Boolean> topicsMap) {
		checked = new ProviderSubset<ITreeNode<Topics>>(provider, false);
		this.topicsMap = topicsMap;
		final Set<ITreeNode<Topics>> treenodes = this.topicsMap.keySet();
		for (final Iterator<ITreeNode<Topics>> iterator = treenodes.iterator(); iterator
				.hasNext();) {
			final ITreeNode<Topics> treeNode = iterator.next();
			check(treeNode, true, RequestCycle.get().find(AjaxRequestTarget.class));
		}
	}

	/**
	 * Check.
	 * 
	 * @param topic
	 *            the topic
	 * @param check
	 *            the check
	 * @param target
	 *            the target
	 */
	@Override
	public void check(final ITreeNode<Topics> topic, final boolean check,
			final AjaxRequestTarget target) {
		if (check) {
			checked.add(topic);
			topicsMap.put(topic, check);
		} else {
			checked.remove(topic);
			topicsMap.remove(topic);
		}
	}

	/**
	 * Detach.
	 * 
	 * {@inheritDoc}.
	 * 
	 * @see org.apache.wicket.model.IDetachable#detach()
	 */
	@Override
	public void detach() {
		checked.detach();
	}

	/**
	 * Gets the checked.
	 * 
	 * @return the checked
	 */
	public ProviderSubset<ITreeNode<Topics>> getChecked() {
		return checked;
	}

	/**
	 * Gets the checked topics.
	 * 
	 * @return the checked topics
	 */
	public List<Topics> getCheckedTopics() {
		ProviderSubset<ITreeNode<Topics>> ct = getChecked();
		List<Topics> checkedTopics = new ArrayList<Topics>();
		if (ct != null && !ct.isEmpty()) {
			for (ITreeNode<Topics> iTreeNode : ct) {
				checkedTopics.add(iTreeNode.getValue());
			}
		}
		return checkedTopics;
	}

	/**
	 * Checks if is checked.
	 * 
	 * @param topic
	 *            the topic
	 * @return true, if is checked
	 */
	@Override
	public boolean isChecked(final ITreeNode<Topics> topic) {
		return checked.contains(topic);
	}

	/**
	 * New content component.
	 * 
	 * @param id
	 *            the id
	 * @param tree
	 *            the tree
	 * @param model
	 *            the model
	 * @return the component {@inheritDoc}.
	 * @see com.sem.base.model.tree.content.Content#newContentComponent(java.lang.String,
	 *      wickettree.AbstractTree, org.apache.wicket.model.IModel)
	 */
	@Override
	public Component newContentComponent(final String id,
			final AbstractTree<ITreeNode<Topics>> tree,
			final IModel<ITreeNode<Topics>> model) {
		return new CheckedFolder<ITreeNode<Topics>>(id, tree, model) {
			private static final long serialVersionUID = 1L;

			@Override
			protected IModel<Boolean> newCheckBoxModel(
					final IModel<ITreeNode<Topics>> model) {
				return new IModel<Boolean>() {
					private static final long serialVersionUID = 1L;

					@Override
					public void detach() {
					}

					@Override
					public Boolean getObject() {
						final ITreeNode<Topics> treeNode = model.getObject();
						final boolean isChecked = isChecked(treeNode);
						topicsMap.put(treeNode, new Boolean(isChecked));
						return isChecked;
					}

					@Override
					public void setObject(final Boolean object) {
						check(model.getObject(), object,
								RequestCycle.get().find(AjaxRequestTarget.class));
					}
				};
			}
		};
	}


}