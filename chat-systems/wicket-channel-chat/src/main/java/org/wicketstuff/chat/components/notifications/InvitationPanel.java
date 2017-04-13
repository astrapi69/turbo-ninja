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
package org.wicketstuff.chat.components.notifications;

import java.util.Map;
import java.util.UUID;

import org.apache.wicket.model.IModel;
import org.apache.wicket.util.time.Duration;
import org.wicketstuff.chat.model.ChatroomModel;

import de.alpharogroup.string.StringExtensions;

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
		final String invitationLabel = StringExtensions.getValue(data, INVITATION_LABEL,
			"Chat invitation ");
		final String chatLinkAddress = StringExtensions.getValue(data, CHAT_LINK, "");
		final String chatLinkLabel = StringExtensions.getValue(data, CHAT_LINK_LABEL, "Chat now");
		final String profileLinkAddress = StringExtensions.getValue(data, PROFILE_LINK, "");
		final String profileLinkLabel = StringExtensions.getValue(data, PROFILE_LINK_LABEL,
			"Show profile");
		final String closeLinkLabel = StringExtensions.getValue(data, CLOSE_LINK_LABEL, "Close");
		final String chatroom = StringExtensions.getValue(data, CHATROOM, UUID.randomUUID().toString()
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
