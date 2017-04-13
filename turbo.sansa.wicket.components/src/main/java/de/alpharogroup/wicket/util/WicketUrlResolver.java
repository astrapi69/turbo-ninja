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
package de.alpharogroup.wicket.util;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.wicket.base.pageparameters.ParameterKeys;
import de.alpharogroup.wicket.base.util.url.WicketUrlExtensions;

/**
 * The Class WicketUrlResolver.
 */
public class WicketUrlResolver
{

	/**
	 * Gets the url as String object for the forgotten password page.
	 * 
	 * @param <C>
	 *            the generic type
	 * @param username
	 *            the username
	 * @param generatedCode
	 *            the generated code
	 * @param resetPasswordPage
	 *            the reset password page
	 * @return the url for forgotten password
	 */
	public static <C extends WebPage> String getUrlForForgottenPassword(final String username,
		final String generatedCode, final Class<C> resetPasswordPage)
	{
		return getUrlForForgottenPassword(username, generatedCode, resetPasswordPage, false);
	}

	/**
	 * Gets the url as String object for the forgotten password page.
	 * 
	 * @param <C>
	 *            the generic type
	 * @param username
	 *            the username
	 * @param generatedCode
	 *            the generated code
	 * @param resetPasswordPage
	 *            the reset password page
	 * @param withServerPort
	 *            the with server port
	 * @return the url for forgotten password
	 */
	public static <C extends WebPage> String getUrlForForgottenPassword(final String username,
		final String generatedCode, final Class<C> resetPasswordPage, final boolean withServerPort)
	{
		final PageParameters parameters = new PageParameters();
		parameters.add(ParameterKeys.USERNAME, username);
		parameters.add(ParameterKeys.CONFIRMATION_CODE, generatedCode);
		return WicketUrlExtensions.absoluteUrlFor(resetPasswordPage, parameters, withServerPort);
	}

}
