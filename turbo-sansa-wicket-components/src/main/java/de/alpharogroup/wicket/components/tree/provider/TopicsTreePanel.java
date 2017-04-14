/**
 * Copyright (C) 2015 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alpharogroup.wicket.components.tree.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.tree.AbstractTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.ITreeProvider;
import org.apache.wicket.extensions.markup.html.repeater.tree.NestedTree;
import org.apache.wicket.extensions.markup.html.repeater.util.ProviderSubset;
import org.apache.wicket.model.IModel;

import de.alpharogroup.event.system.entities.Topics;
import de.alpharogroup.tree.ifaces.ITreeNode;
import de.alpharogroup.wicket.components.tree.content.CheckedFolderContent;
import de.alpharogroup.wicket.components.tree.model.Content;
import de.alpharogroup.wicket.components.tree.panel.AbstractTreeFormComponentPanel;

/**
 * The Class TopicsTreePanel.
 * 
 * @author Asterios Raptis
 */
public class TopicsTreePanel extends AbstractTreeFormComponentPanel<ITreeNode<Topics>>
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The tree. */
	private NestedTree<ITreeNode<Topics>> tree;

	/**
	 * Instantiates a new topics tree panel.
	 * 
	 * @param id
	 *            the id
	 * @param provider
	 *            the provider
	 * @param state
	 *            the state
	 * @param content
	 *            the content
	 */
	public TopicsTreePanel(final String id, final ITreeProvider<ITreeNode<Topics>> provider,
		final ProviderSubset<ITreeNode<Topics>> state, final CheckedFolderContent content)
	{
		super(id, provider, state, content);

		add(getTree());

		setContents(new ArrayList<Content<ITreeNode<Topics>>>());
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @param provider
	 *            the provider
	 * @param state
	 *            the state
	 * @return the abstract tree
	 */
	@Override
	protected AbstractTree<ITreeNode<Topics>> createTree(
		final ITreeProvider<ITreeNode<Topics>> provider, final IModel<Set<ITreeNode<Topics>>> state)
	{
		tree = new NestedTree<ITreeNode<Topics>>("tree", provider, state)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected Component newContentComponent(final String id,
				final IModel<ITreeNode<Topics>> model)
			{
				return TopicsTreePanel.this.newContentComponent(id, model);
			}
		};
		return tree;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @param contents
	 *            the new contents
	 */
	@Override
	protected void setContents(final List<Content<ITreeNode<Topics>>> contents)
	{
		this.contents = contents;
	}

}
