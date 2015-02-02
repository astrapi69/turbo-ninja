package de.alpharogroup.wicket.util;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.wicket.base.pageparameters.ParameterKeys;
import org.jaulp.wicket.base.util.url.WicketUrlUtils;

/**
 * The Class WicketUrlResolver.
 */
public class WicketUrlResolver {

	/**
	 * Gets the url for forgotten password.
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
		return WicketUrlUtils.absoluteUrlFor(resetPasswordPage, parameters, withServerPort);
	}
	
	/**
	 * Gets the url for forgotten password.
	 * 
	 * @param <C>
	 *            the generic type
	 * @param requestUrl
	 *            the request url
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
