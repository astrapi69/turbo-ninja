package de.alpharogroup.wicket.util.resource.loader;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.Session;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.resource.loader.IStringResourceLoader;

import de.alpharogroup.db.resource.bundles.application.DatabaseControl;


/**
 * Implementation of a string resource loader that get the resources from the database. When this
 * loader is created it should be given the name of the resource bundle.
 *
 * You can register the custom resource loader in your application's init() method like follows:
 *
 * protected void init() { getResourceSettings().addStringResourceLoader(new
 * DatabaseResourceLoader("baseName")); }
 */
public class DatabaseResourceLoader implements IStringResourceLoader
{

	/** The bundle name. */
	private final String bundleName;

	/**
	 * Instantiates a new database resource loader.
	 *
	 * @param resourceBundleName
	 *            the resource bundle name
	 */
	public DatabaseResourceLoader(final String resourceBundleName)
	{
		bundleName = resourceBundleName;
	}

	/**
	 * Find resource.
	 *
	 * @param locale
	 *            the locale
	 * @param key
	 *            the key
	 * @return the string
	 */
	private String findResource(Locale locale, final String key)
	{
		String string = null;
		if (locale == null)
		{
			locale = Session.exists() ? Session.get().getLocale() : Locale.getDefault();
		}
		ResourceBundle resourceBundle = null;
		try
		{
			resourceBundle = ResourceBundle.getBundle(bundleName, locale, DatabaseControl.getInstance());
		}
		catch (final MissingResourceException e)
		{
			if (shouldThrowExceptionForMissingResource())
			{
				throw new WicketRuntimeException(String.format(
					"Unable able to locate resource bundle for the specifed base name: %s",
					bundleName));
			}
		}

		if (resourceBundle != null)
		{
			boolean caught = false;

			try
			{
				string = resourceBundle.getString(key);
			}
			catch (final MissingResourceException e)
			{
				caught = true;
			}

			if (caught || string == null)
			{
				if (shouldThrowExceptionForMissingResource())
				{
					throw new WicketRuntimeException(String.format(
						"Unable able to locate resource bundle for the specifed base name: %s",
						bundleName));
				}
			}
		}
		return string;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String loadStringResource(final Class<?> clazz, final String key, final Locale locale,
		final String style, final String variation)
	{
		return findResource(locale, key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String loadStringResource(final Component component, final String key,
		final Locale locale, final String style, final String variation)
	{
		return findResource(component.getLocale(), key);
	}

	/**
	 * Should throw exception for missing resource.
	 *
	 * @return true, if successful
	 */
	private boolean shouldThrowExceptionForMissingResource()
	{
		return Application.get().getResourceSettings().getThrowExceptionOnMissingResource();
	}
}