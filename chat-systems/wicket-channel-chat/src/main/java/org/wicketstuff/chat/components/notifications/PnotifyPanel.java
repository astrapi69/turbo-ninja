package org.wicketstuff.chat.components.notifications;

import java.util.Map;

import org.apache.wicket.Application;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.time.Duration;
import org.wicketstuff.chat.model.ChatroomModel;

import de.alpharogroup.string.StringExtensions;
import de.alpharogroup.wicket.js.addon.pnotify.PnotifyJsGenerator;
import de.alpharogroup.wicket.js.addon.pnotify.PnotifyJsReference;
import de.alpharogroup.wicket.js.addon.pnotify.PnotifySettings;

public abstract class PnotifyPanel extends NotificationPanel
{

	private static final long serialVersionUID = 1L;


	public PnotifyPanel(final String id, final IModel<ChatroomModel> model, final Duration duration)
	{
		super(id, model, duration);
	}

	@Override
	protected String onGetJavaScript(final Map<String, String> data)
	{
		return onGetPnotifyJsScript(data);
	}


	protected String onGetPnotifyJsScript(final Map<String, String> datas)
	{
		final PnotifySettings settings = PnotifySettings.builder().build();
		settings.getTitle().setValue(StringExtensions.getValue(datas, "title", "Notification"));
		settings.getStyling().setValue(StringExtensions.getValue(datas, "styling", "jqueryui"));
		settings.getText().setValue(datas.get("message"));
		settings.getDelay().setValue((int)getDuration().getMilliseconds());
		final PnotifyJsGenerator generator = new PnotifyJsGenerator(settings);
		generator.generateJs();
		return generator.generateJs();
	}

	@Override
	public void renderHead(final IHeaderResponse response)
	{
		super.renderHead(response);
		response.render(JavaScriptHeaderItem.forReference(Application.get()
			.getJavaScriptLibrarySettings().getJQueryReference()));
		response.render(JavaScriptHeaderItem.forReference(PnotifyJsReference.INSTANCE));
	}

}
