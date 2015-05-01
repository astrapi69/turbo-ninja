package de.alpharogroup.wicket.model.provider;

import java.util.List;

import de.alpharogroup.wicket.data.provider.AbstractSortableDataProvider;

import resource.system.model.Resources;

public class ResourcesProvider extends
		AbstractSortableDataProvider<Resources, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourcesProvider(List<Resources> data) {
		super(data);
	}

}