package de.alpharogroup.wicket.util;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import de.alpharogroup.wicket.base.pageparameters.ParameterKeys;
import de.alpharogroup.wicket.base.util.url.WicketUrlExtensions;

/**
 * The Class WicketUrlResolver.
 */
public class WicketUrlResolver {

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
	public static <C extends WebPage> String getUrlForForgottenPassword(
			String username, final String generatedCode,
			Class<C> resetPasswordPage, boolean withServerPort) {
		PageParameters parameters = new PageParameters();
		parameters.add(ParameterKeys.USERNAME, username);
		parameters.add(ParameterKeys.CONFIRMATION_CODE, generatedCode);
		return WicketUrlExtensions.absoluteUrlFor(resetPasswordPage, parameters, withServerPort);
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
	 * @return the url for forgotten password
	 */
	public static <C extends WebPage> String getUrlForForgottenPassword(
			String username, final String generatedCode, 
			Class<C> resetPasswordPage) {
		return getUrlForForgottenPassword(username, generatedCode, resetPasswordPage, false);
	}
	
}
