package de.alpharogroup.wicket.components.inbox;

import java.util.ArrayList;
import java.util.List;

import message.system.model.Messages;
import de.alpharogroup.io.annotations.ImportResource;
import de.alpharogroup.io.annotations.ImportResources;

import org.apache.wicket.extensions.ajax.markup.html.tabs.AjaxTabbedPanel;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * The Class MemberInboxPanel.
 * 
 * @author Asterios Raptis
 */
@ImportResources(resources = { @ImportResource(resourceName = "MemberInboxPanel.css", resourceType = "css") })
public abstract class AbstractMemberInboxPanel extends Panel {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	
	protected final AjaxTabbedPanel<ITab> ajaxTabbedPanel;

	/**
	 * Instantiates a new provider inbox panel.
	 *
	 * @param id            the id
	 * @param parameters the parameters
	 */
	public AbstractMemberInboxPanel(final String id, final PageParameters parameters) {
		super(id);
		onSuccessfulSendMessage(parameters);
		final List<ITab> tabs = new ArrayList<ITab>();
		final List<Messages> sentList = onFindSentMessages();
		final List<Messages> unrepliedList = onFindUnrepliedMessages();
		final List<Messages> contactedList = onFindContactedMessages();		

		Object[] paramsinbox = { unrepliedList.size() };
		final IModel<String> inboxModel = new StringResourceModel(
				"global.inbox.tab.info", this, null, paramsinbox);
		tabs.add(new AbstractTab(inboxModel) {
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Panel getPanel(final String panelId) {
				return newInboxPanel(panelId);
			}
		});

		Object[] paramsSent = { sentList.size() };
		final IModel<String> sentModel = new StringResourceModel(
				"global.inbox.sent.tab.info", this, null, paramsSent);
		tabs.add(new AbstractTab(sentModel) {
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Panel getPanel(final String panelId) {
				return newMailSentPanel(panelId);
			}
		});
		Object[] paramsreaded = { contactedList.size() };
		final IModel<String> readedModel = new StringResourceModel(
				"global.inbox.readed.tab.info", this, null, paramsreaded);
		tabs.add(new AbstractTab(readedModel) {
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Panel getPanel(final String panelId) {
				return newReadedPanel(panelId);
			}
		});
		ajaxTabbedPanel = new AjaxTabbedPanel<ITab>("tabs", tabs);
		add(ajaxTabbedPanel);
	}

	protected abstract Panel newReadedPanel(String panelId);

	protected abstract Panel newMailSentPanel(String panelId);

	protected abstract Panel newInboxPanel(String panelId);

	protected abstract void onSuccessfulSendMessage(final PageParameters parameters);
	
	protected abstract List<Messages> onFindSentMessages();
	
	protected abstract List<Messages> onFindUnrepliedMessages();
	
	protected abstract List<Messages> onFindContactedMessages();

}
