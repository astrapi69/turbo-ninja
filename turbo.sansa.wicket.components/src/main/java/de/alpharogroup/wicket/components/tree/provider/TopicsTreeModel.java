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

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.wicket.util.io.IClusterable;

import de.alpharogroup.tree.ifaces.ITreeNode;
import de.alpharogroup.event.system.entities.Topics;

/**
 * The Class TopicsTreeModel.
 * 
 * @author Asterios Raptis
 */
public class TopicsTreeModel implements IClusterable
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The topics map. */
	private Map<ITreeNode<Topics>, Boolean> topicsMap;

	/**
	 * Instantiates a new topics tree model.
	 */
	public TopicsTreeModel()
	{
		this(new LinkedHashMap<ITreeNode<Topics>, Boolean>());
	}

	/**
	 * Instantiates a new topics tree model.
	 * 
	 * @param topicsMap
	 *            the topics map
	 */
	public TopicsTreeModel(final Map<ITreeNode<Topics>, Boolean> topicsMap)
	{
		super();
		this.topicsMap = topicsMap;
	}

	/**
	 * Gets the topics map.
	 * 
	 * @return the topics map
	 */
	public Map<ITreeNode<Topics>, Boolean> getTopicsMap()
	{
		return topicsMap;
	}

	/**
	 * Sets the topics map.
	 * 
	 * @param topicsMap
	 *            the topics map
	 */
	public void setTopicsMap(final Map<ITreeNode<Topics>, Boolean> topicsMap)
	{
		this.topicsMap = topicsMap;
	}

}
