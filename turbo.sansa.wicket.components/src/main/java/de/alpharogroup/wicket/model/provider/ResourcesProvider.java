package de.alpharogroup.wicket.model.provider;

import java.util.List;

import resource.system.model.Resources;
import de.alpharogroup.wicket.data.provider.AbstractSortableDataProvider;

public class ResourcesProvider extends AbstractSortableDataProvider<Resources, String>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourcesProvider(final List<Resources> data)
	{
		super(data);
	}

}