package de.alpharogroup.wicket.model.provider;

import java.util.List;

import de.alpharogroup.resource.system.application.model.ResourcesModel;
import de.alpharogroup.wicket.data.provider.AbstractSortableDataProvider;

public class ResourcesModelProvider extends AbstractSortableDataProvider<ResourcesModel, String>
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ResourcesModelProvider(final List<ResourcesModel> data)
	{
		super(data);
	}

}