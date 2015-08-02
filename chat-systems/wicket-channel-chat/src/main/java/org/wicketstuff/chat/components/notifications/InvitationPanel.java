package org.wicketstuff.chat.components.notifications;

import java.util.Map;
import java.util.UUID;

import org.apache.wicket.model.IModel;
import org.apache.wicket.util.time.Duration;
import org.wicketstuff.chat.model.ChatroomModel;

import de.alpharogroup.string.StringUtils;

public abstract class InvitationPanel extends NotificationPanel
{

	public static final String CHATROOM = "chatroom";
	public static final String CLOSE_LINK_LABEL = "closeLinkLabel";
	public static final String PROFILE_LINK_LABEL = "profileLinkLabel";
	public static final String PROFILE_LINK = "profileLink";
	public static final String CHAT_LINK_LABEL = "chatLinkLabel";
	public static final String CHAT_LINK = "chatLink";
	public static final String INVITATION_LABEL = "invitationLabel";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvitationPanel(final String id, final IModel<ChatroomModel> model,
		final Duration duration)
	{
		super(id, model, duration);
	}

	@Override
	protected String onGetJavaScript(final Map<String, String> data)
	{
		final long delay = getDuration().getMilliseconds();
		final String invitationLabel = StringUtils.getValue(data, INVITATION_LABEL,
			"Chat invitation ");
		final String chatLinkAddress = StringUtils.getValue(data, CHAT_LINK, "");
		final String chatLinkLabel = StringUtils.getValue(data, CHAT_LINK_LABEL, "Chat now");
		final String profileLinkAddress = StringUtils.getValue(data, PROFILE_LINK, "");
		final String profileLinkLabel = StringUtils.getValue(data, PROFILE_LINK_LABEL,
			"Show profile");
		final String closeLinkLabel = StringUtils.getValue(data, CLOSE_LINK_LABEL, "Close");
		final String chatroom = StringUtils.getValue(data, CHATROOM, UUID.randomUUID().toString()
			.substring(0, 7));
		final String divId = getNotification().getMarkupId() + "_" + chatroom;
		final String closeId = divId + "_close";

		final String replaceDiv = "var element = document.getElementById(\"" + divId
			+ "\"); element.parentNode.removeChild(element);";

		final String closeFunction = "var cl = function() { " + replaceDiv + "return false;};";
		final String closeJs = "document.getElementById(\"" + closeId + "\").onclick = cl;";

		final String jqueryHide = "setTimeout(function() { " + replaceDiv + "}, " + delay + ");";
		final String jqueryShow = "$(\"#" + getNotification().getMarkupId() + "\").append('"
			+ "<div class=\"" + divId + " well\" id=\"" + divId + "\">" + "		<span class=\"lead\">"
			+ invitationLabel + "		</span>" + "		<div class=\"btn-group\">"
			+ "			<a class=\"btn btn-success buttonPadding\" href=\"" + chatLinkAddress + "\" >"
			+ chatLinkLabel + "			</a>" + "			<a class=\"btn btn-info buttonPadding\" href=\""
			+ profileLinkAddress + "\" >" + profileLinkLabel + "			</a>"
			+ "			<a class=\"btn btn-inverse buttonPadding\" href=\"#\" id=\"" + closeId + "\" >"
			+ closeLinkLabel + "			</a>" + "		</div>" + "</div>" + "');";
		return jqueryShow + jqueryHide + closeFunction + closeJs;
	}

}
