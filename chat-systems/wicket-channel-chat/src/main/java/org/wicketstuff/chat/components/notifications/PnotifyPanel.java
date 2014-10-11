package org.wicketstuff.chat.components.notifications;

import java.util.Map;

import net.sourceforge.jaulp.string.StringUtils;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.time.Duration;
import org.odlabs.wiquery.core.util.WiQueryUtil;
import org.wicketstuff.chat.components.notifications.pnotify.PnotifyJsReference;
import org.wicketstuff.chat.model.ChatroomModel;


public abstract class PnotifyPanel extends NotificationPanel {

	private static final long serialVersionUID = 1L;


	public PnotifyPanel(String id, IModel<ChatroomModel> model,
			Duration duration) {
		super(id, model, duration);
	}

	@Override
	protected String onGetJavaScript(Map<String, String> data) {
		return onGetPnotifyJsScript(data);
	}
	

	protected String onGetPnotifyJsScript(final Map<String, String> datas) {
		long delay = getDuration().getMilliseconds();
		String title = StringUtils.getValue(datas, "title", "Notification");
		String styling = StringUtils.getValue(datas, "styling", "jqueryui");
		String message = datas.get("message");
		String pnotify = 
				  "$("
				+ "function(){"
				+ " new PNotify({ "
				+ "title: '" + title + "', "
				+ "text: '"	+ message + "', "
				+ "animation: 'show', "
				+ "delay: " + delay + ", "
				+ "styling: '" + styling + "'"
				+ " });"
				+ " });";
		return pnotify;
	}
	
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);		
		 response.render(JavaScriptHeaderItem.forReference(WiQueryUtil.getJQueryResourceReference()));
		 response.render(JavaScriptHeaderItem.forReference(PnotifyJsReference.INSTANCE));		
	}

}
