package org.wicketstuff.chat.components.notifications;

import java.util.Map;
import java.util.UUID;

import de.alpharogroup.string.StringUtils;

import org.apache.wicket.model.IModel;
import org.apache.wicket.util.time.Duration;
import org.wicketstuff.chat.model.ChatroomModel;

public abstract class InvitationPanel extends NotificationPanel {

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

	public InvitationPanel(String id, IModel<ChatroomModel> model, Duration duration) {
		super(id, model, duration);
	}

	@Override
	protected String onGetJavaScript(Map<String, String> data) {
		long delay = getDuration().getMilliseconds();
		String invitationLabel = StringUtils.getValue(data, INVITATION_LABEL, "Chat invitation ");
		String chatLinkAddress = StringUtils.getValue(data, CHAT_LINK, "");
		String chatLinkLabel = StringUtils.getValue(data, CHAT_LINK_LABEL, "Chat now");
		String profileLinkAddress = StringUtils.getValue(data, PROFILE_LINK, "");
		String profileLinkLabel = StringUtils.getValue(data, PROFILE_LINK_LABEL, "Show profile");
		String closeLinkLabel = StringUtils.getValue(data, CLOSE_LINK_LABEL, "Close");
		String chatroom = StringUtils.getValue(data, CHATROOM, UUID.randomUUID().toString().substring(0, 7));	
		String divId = getNotification().getMarkupId() + "_" + chatroom;
		String closeId = divId + "_close";
				
		String replaceDiv = "var element = document.getElementById(\""
				+ divId
				+ "\"); element.parentNode.removeChild(element);";

		String closeFunction = "var cl = function() { " +replaceDiv
				+ "return false;};";
		String closeJs = "document.getElementById(\"" + closeId 
				+ "\").onclick = cl;";
		
		String jqueryHide = "setTimeout(function() { "
				+ replaceDiv
				+ "}, "
				+ delay
				+ ");";
		String jqueryShow = "$(\"#"
				+ getNotification().getMarkupId()
				+ "\").append('"
				+ "<div class=\"" + divId + " well\" id=\"" + divId	+ "\">"
				+ "		<span class=\"lead\">"
				+ 		invitationLabel
				+ "		</span>"
				+ "		<div class=\"btn-group\">"
				+ "			<a class=\"btn btn-success buttonPadding\" href=\"" + chatLinkAddress + "\" >"
				+ 			chatLinkLabel
				+ "			</a>"
				+ "			<a class=\"btn btn-info buttonPadding\" href=\"" + profileLinkAddress + "\" >"
				+ 			profileLinkLabel
				+ "			</a>"
				+ "			<a class=\"btn btn-inverse buttonPadding\" href=\"#\" id=\"" + closeId + "\" >"
				+ 			closeLinkLabel
				+ "			</a>"
				+ "		</div>"
				+ "</div>"
				+ "');";
		return jqueryShow + jqueryHide + closeFunction + closeJs;
	}

}
