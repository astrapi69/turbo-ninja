/**
 * Copyright (C) 2015 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alpharogroup.wicket.bootstrap3.themes;

import de.agilecoders.wicket.core.settings.Theme;
import de.agilecoders.wicket.less.LessResourceReference;

/**
 * You can provide your own version of customized bootstrap components wiht the Class CustumTheme.
 * All you have to do is to generate your own less file on
 * <a href="http://getbootstrap.com/customize/">http://getbootstrap.com/customize/</a> and give that
 * generated less file in the lessfile path.
 */
public class CustomTheme extends Theme
{

	/**
	 * Instantiates a new custum theme.
	 */
	public CustomTheme()
	{
		this("customTheme", "css/customTheme-bootstrap.less");
	}

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
}