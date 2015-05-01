package de.alpharogroup.wicket.components.tree.provider;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.wicket.util.io.IClusterable;

import de.alpharogroup.tree.ifaces.ITreeNode;
import events.system.model.Topics;

/**
 * The Class TopicsTreeModel.
 * 
 * @author Asterios Raptis
 */
public class TopicsTreeModel implements IClusterable {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The topics map. */
	private Map<ITreeNode<Topics>, Boolean> topicsMap;

	/**
	 * Instantiates a new topics tree model.
	 */
	public TopicsTreeModel() {
		this(new LinkedHashMap<ITreeNode<Topics>, Boolean>());
	}

	/**
	 * Instantiates a new topics tree model.
	 * 
	 * @param topicsMap
	 *            the topics map
	 */
	public TopicsTreeModel(final Map<ITreeNode<Topics>, Boolean> topicsMap) {
		super();
		this.topicsMap = topicsMap;
	}

	/**
	 * Gets the topics map.
	 * 
	 * @return the topics map
	 */
	public Map<ITreeNode<Topics>, Boolean> getTopicsMap() {
		return topicsMap;
	}

	/**
	 * Sets the topics map.
	 * 
	 * @param topicsMap
	 *            the topics map
	 */
	public void setTopicsMap(final Map<ITreeNode<Topics>, Boolean> topicsMap) {
		this.topicsMap = topicsMap;
	}

}
