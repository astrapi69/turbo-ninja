package de.alpharogroup.wicket.bootstrap3.themes;

import de.agilecoders.wicket.core.settings.Theme;
import de.agilecoders.wicket.less.LessResourceReference;

/**
 * You can provide your own version of customized bootstrap components wiht the Class CustumTheme.
 * All you have to do is to generate your own less file on <a
 * href="http://getbootstrap.com/customize/">http://getbootstrap.com/customize/</a> and give that
 * generated less file in the lessfile path.
 */
public class CustomTheme extends Theme
{

	/**
	 * Instantiates a new custum theme.
	 *
	 * @param themeName
	 *            the theme name
	 * @param lessfilePath
	 *            the lessfile path with the file name.
	 */
	public CustomTheme(final String themeName, final String lessfilePath)
	{
		super(themeName, new LessResourceReference(CustomTheme.class, lessfilePath));
	}

	/**
	 * Instantiates a new custum theme.
	 */
	public CustomTheme()
	{
		this("customTheme", "css/customTheme-bootstrap.less");
	}
}