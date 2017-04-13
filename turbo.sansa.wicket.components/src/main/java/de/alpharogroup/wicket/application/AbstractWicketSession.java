package de.alpharogroup.wicket.application;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import de.alpharogroup.user.entities.Roles;
import de.alpharogroup.user.entities.Users;

/**
 * The Class WicketSession is the session object for this application.
 *
 * @author Asterios Raptis
 * @param <USERID>
 *            the generic type for the user id.
 */
public abstract class AbstractWicketSession<USERID> extends WebSession
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 4729651101693312649L;

	/**
	 * Gets the WicketSession for this application.
	 *
	 * @return the current WicketSession object.
	 */
	@SuppressWarnings("rawtypes")
	public static AbstractWicketSession get()
	{
		return ((AbstractWicketSession)Session.get());
	}

	/** The roles. */
	private List<Roles> roles;

	/**
	 * The user attributes. Holds data for the user session.
	 **/
	private final Map<String, Serializable> userAttributes = new LinkedHashMap<>();

	/** The user id. */
	protected USERID userId;

	/**
	 * Instantiates a new WicketSession.
	 *
	 * @param request
	 *            The current request
	 */
	public AbstractWicketSession(final Request request)
	{
		super(request);
	}

	/**
	 * Gets the roles.
	 *
	 * @return the roles
	 */
	public synchronized List<Roles> getRoles()
	{
		return roles;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public abstract Users getUser();

	/**
	 * Gets the user attribute.
	 *
	 * @param key
	 *            the key
	 * @return the user attribute
	 */
	public synchronized Serializable getUserAttribute(final String key)
	{
		return userAttributes.get(key);
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public synchronized USERID getUserId()
	{
		return userId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void invalidate()
	{
		super.invalidate();
		setUserId(null);
		setUser(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void invalidateNow()
	{
		super.invalidateNow();
		setUserId(null);
		setUser(null);
	}

	/**
	 * Checks if the user is a guest.
	 *
	 * @return true, the user is a guest.
	 */
	public synchronized boolean isGuest()
	{
		return !isSignedIn();
	}

	/**
	 * Checks if the user is in the given Roles object.
	 *
	 * @param role
	 *            the role
	 * @return true, if successful
	 */
	public synchronized boolean isInRole(final Roles role)
	{
		final List<Roles> roles = getRoles();
		if (roles != null && !roles.isEmpty())
		{
			if (roles.contains(role))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the user is in the role lookup is made over the role name.
	 *
	 * @param rolename
	 *            the role name
	 * @return true, if successful
	 */
	public synchronized boolean isInRole(final String rolename)
	{
		final List<Roles> roles = getRoles();
		if (null != roles && (!roles.isEmpty()))
		{
			for (final Roles role : roles)
			{
				if (role.getRolename().equals(rolename))
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks if is user signed in.
	 *
	 * @return true, if is user signed in
	 */
	public synchronized boolean isSignedIn()
	{
		return (userId != null);
	}

	/**
	 * Hook method that can be used when a user signs out from the application.
	 */
	public synchronized void onSignOut()
	{
	}

	/**
	 * Removes the user attribute.
	 *
	 * @param key
	 *            the key
	 * @return the user attribute
	 */
	public synchronized Serializable removeUserAttribute(final String key)
	{
		return userAttributes.remove(key);
	}

	/**
	 * Sets the roles.
	 *
	 * @param roles
	 *            the new roles
	 */
	public synchronized void setRoles(final List<Roles> roles)
	{
		this.roles = roles;
	}

	/**
	 * Sets the user.
	 *
	 * @param user
	 *            the user
	 */
	public abstract void setUser(final Users user);

	/**
	 * Sets the user attribute.
	 *
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public synchronized void setUserAttribute(final String key, final Serializable value)
	{
		userAttributes.put(key, value);
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId
	 *            the new user id
	 */
	public synchronized void setUserId(final USERID userId)
	{
		this.userId = userId;
	}

}
