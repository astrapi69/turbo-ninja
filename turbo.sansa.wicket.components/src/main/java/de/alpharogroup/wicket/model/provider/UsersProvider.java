package de.alpharogroup.wicket.model.provider;

import java.util.List;

import de.alpharogroup.wicket.data.provider.AbstractSortableDataProvider;

import user.management.model.Users;

public class UsersProvider extends
		AbstractSortableDataProvider<Users, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsersProvider(List<Users> data) {
		super(data);
	}

}