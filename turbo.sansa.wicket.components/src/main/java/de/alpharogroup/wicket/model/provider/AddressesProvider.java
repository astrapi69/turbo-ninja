package de.alpharogroup.wicket.model.provider;

import java.util.List;

import de.alpharogroup.wicket.data.provider.AbstractSortableDataProvider;

import address.book.model.Addresses;

/**
 * The Class AddressesProvider.
 * 
 * @author Asterios Raptis
 */
public class AddressesProvider extends
		AbstractSortableDataProvider<Addresses, String> {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new addresses provider.
	 * 
	 * @param data
	 *            the data
	 */
	public AddressesProvider(List<Addresses> data) {
		super(data);
	}

}