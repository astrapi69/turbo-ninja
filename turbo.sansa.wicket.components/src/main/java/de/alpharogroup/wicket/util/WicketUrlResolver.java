package de.alpharogroup.wicket.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.wicket.base.pageparameters.ParameterKeys;
import org.jaulp.wicket.base.util.WicketComponentUtils;
import org.jaulp.wicket.base.util.WicketUrlUtils;

/**
 * The Class WicketUrlUtils.
 */
public class WicketUrlResolver {

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
			final String requestUrl, String username,
			final String generatedCode, Class<C> resetPasswordPage) {
		Map<String, String> parameters = new HashMap<>();
		parameters.put(ParameterKeys.USERNAME, username);
		parameters.put(ParameterKeys.CONFIRMATION_CODE, generatedCode);
		return getUrlFor(requestUrl, resetPasswordPage, parameters);
	}

	/**
	 * Appends the given requestUrl that is resolved from the HttpServletRequest
	 * and appends the urlFor from the RequestCycle from the given page and the
	 * given PageParameters.
	 * 
	 * @param <C>
	 *            the generic type
	 * @param requestUrl
	 *            the request url
	 * @param page
	 *            the page
	 * @param parameters
	 *            the parameters
	 * @return the url for
	 */
	public static <C extends WebPage> String getUrlFor(final String requestUrl,
			Class<C> page, Map<String, String> parameters) {
		PageParameters param = WicketComponentUtils.toPageParameters(parameters);
		return urlFor(requestUrl, page, param);
	}

	/**
	 * Appends the given requestUrl that is resolved from the HttpServletRequest
	 * and appends the urlFor from the RequestCycle from the given page and the
	 * given PageParameters.
	 * 
	 * @param <C>
	 *            the generic type
	 * @param requestUrl
	 *            the request url
	 * @param page
	 *            the page
	 * @param param
	 *            the param
	 * @return the string
	 */
	public static <C extends WebPage> String urlFor(final String requestUrl,
			Class<C> page, PageParameters param) {
		String ru = StringUtils.substringBeforeLast(
				requestUrl, "/");
		StringBuilder url = new StringBuilder(ru).append("/");
		url.append((RequestCycle.get()).urlFor(page, param));
		return url.toString();
	}

	/**
	 * Appends the given requestUrl that is resolved from the HttpServletRequest
	 * and appends the urlFor from the RequestCycle from the given page.
	 * 
	 * @param <C>
	 *            the generic type
	 * @param page
	 *            the page
	 * @return the string
	 */
	public static <C extends WebPage> String urlFor(Class<C> page) {
		return urlFor(WicketComponentUtils.getRequestURL(), page, new PageParameters());
	}

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
	 * The full url for the current page without the server port.
	 *
	 * @param withServerPort the with server port
	 * @return the string
	 */
	public static String toFullUrl() {		
			return toFullUrl(false);		
	}
	
	/**
	 * The full url for the current page.
	 *
	 * @param withServerPort the with server port
	 * @return the string
	 */
	public static String toFullUrl(boolean withServerPort) {		
			return WicketUrlUtils.getDomainUrl(withServerPort) + getBaseUrl();		
	}


	/**
	 * Gets the base Url.
	 * 
	 * @return base Url
	 */
	public static String getBaseUrl() {
		return RequestCycle.get().getUrlRenderer().getBaseUrl().canonical().toString();
	}

}
