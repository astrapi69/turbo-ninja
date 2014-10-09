package de.alpharogroup.wicket.model.provider;

import java.util.List;

import org.jaulp.wicket.data.provider.AbstractSortableDataProvider;

import resource.system.application.model.ResourcesModel;

public class ResourcesModelProvider extends
		AbstractSortableDataProvider<ResourcesModel, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourcesModelProvider(List<ResourcesModel> data) {
		super(data);
	}

}