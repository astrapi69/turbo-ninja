package de.alpharogroup.wicket.model.provider;

import java.util.List;

import user.management.model.Users;
import de.alpharogroup.wicket.data.provider.AbstractSortableDataProvider;

public class UsersProvider extends AbstractSortableDataProvider<Users, String>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsersProvider(final List<Users> data)
	{
		super(data);
	}

}