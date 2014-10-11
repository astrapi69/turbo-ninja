package de.alpharogroup.wicket.util.resource.loader;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.Session;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.resource.loader.IStringResourceLoader;

import db.resource.bundles.application.DatabaseControl;

/**
 * Implementation of a string resource loader that get the resources from the database. When this loader is created it should be given the name of the resource bundle.
 
 You can register the custom resource loader in your application's init() method like follows:

protected void init() { 
    getResourceSettings().addStringResourceLoader(new DatabaseResourceLoader("baseName"));
}


 */
public class DatabaseResourceLoader implements IStringResourceLoader {
 
    /** The bundle name. */
    private String bundleName;
     
    /**
     * Instantiates a new database resource loader.
     *
     * @param resourceBundleName the resource bundle name
     */
    public DatabaseResourceLoader(String resourceBundleName) {
        bundleName = resourceBundleName;
    }
     
    /**
     * Find resource.
     *
     * @param locale the locale
     * @param key the key
     * @return the string
     */
    private String findResource(Locale locale, String key) {
        String string = null;
		if (locale == null) {
			locale = Session.exists() ? Session.get().getLocale() : Locale.getDefault();
		}
        ResourceBundle resourceBundle = null;
        try {
            resourceBundle = ResourceBundle.getBundle(bundleName, locale, new DatabaseControl());
        } catch (MissingResourceException e) {
            if (shouldThrowExceptionForMissingResource()) {
                throw new WicketRuntimeException(String.format("Unable able to locate resource bundle for the specifed base name: %s", bundleName));
            }             
       }
         
        if (resourceBundle != null) {
            boolean caught = false;
             
            try {
                string = resourceBundle.getString(key);
            } catch (MissingResourceException e) {
                caught = true;
            }
             
            if (caught || string == null) {
                if (shouldThrowExceptionForMissingResource()) {
                    throw new WicketRuntimeException(String.format("Unable able to locate resource bundle for the specifed base name: %s", bundleName));
                }              
             }
        }         
        return string;
    }
     
    /**
     * Should throw exception for missing resource.
     *
     * @return true, if successful
     */
    private boolean shouldThrowExceptionForMissingResource() {
                return Application.get().getResourceSettings().getThrowExceptionOnMissingResource();
    }

	/**
     * {@inheritDoc}
     */
	@Override
	public String loadStringResource(Class<?> clazz, String key, Locale locale,
			String style, String variation) {
		return findResource(locale, key);
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public String loadStringResource(Component component, String key,
			Locale locale, String style, String variation) {
        return findResource(component.getLocale(), key);
	}
}