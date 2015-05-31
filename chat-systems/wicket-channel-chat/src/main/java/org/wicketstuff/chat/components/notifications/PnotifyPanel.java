package org.wicketstuff.chat.components.notifications;

import java.util.Map;

import org.apache.wicket.Application;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.time.Duration;
import org.wicketstuff.chat.model.ChatroomModel;

import de.alpharogroup.string.StringUtils;
import de.alpharogroup.wicket.behaviors.pnotify.PnotifyJsGenerator;
import de.alpharogroup.wicket.behaviors.pnotify.PnotifyJsReference;
import de.alpharogroup.wicket.behaviors.pnotify.PnotifySettings;


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
		PnotifySettings settings = new PnotifySettings();
		settings.getTitle().setValue(StringUtils.getValue(datas, "title", "Notification"));
		settings.getStyling().setValue(StringUtils.getValue(datas, "styling", "jqueryui"));
		settings.getText().setValue(datas.get("message"));
		settings.getDelay().setValue((int)getDuration().getMilliseconds());
		PnotifyJsGenerator generator = new PnotifyJsGenerator(settings);
		generator.generateJs();
		return generator.generateJs();
	}
	
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);		
		response.render(JavaScriptHeaderItem.forReference(Application.get()
			.getJavaScriptLibrarySettings().getJQueryReference()));
		 response.render(JavaScriptHeaderItem.forReference(PnotifyJsReference.INSTANCE));		
	}

}
