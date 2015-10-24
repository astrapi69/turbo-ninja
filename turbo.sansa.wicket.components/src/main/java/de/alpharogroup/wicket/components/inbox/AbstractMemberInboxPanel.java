package de.alpharogroup.wicket.components.inbox;

import java.util.ArrayList;
import java.util.List;

import message.system.model.Messages;

import org.apache.wicket.extensions.ajax.markup.html.tabs.AjaxTabbedPanel;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.io.annotations.ImportResource;
import de.alpharogroup.io.annotations.ImportResources;
import de.alpharogroup.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;

/**
 * The Class MemberInboxPanel.
 *
 * @author Asterios Raptis
 */
@ImportResources(resources = { @ImportResource(resourceName = "MemberInboxPanel.css", resourceType = "css") })
public abstract class AbstractMemberInboxPanel extends Panel
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	protected final AjaxTabbedPanel<ITab> ajaxTabbedPanel;

	/**
	 * Instantiates a new provider inbox panel.
	 *
	 * @param id
	 *            the id
	 * @param parameters
	 *            the parameters
	 */
	public AbstractMemberInboxPanel(final String id, final PageParameters parameters)
	{
		super(id);
		onSuccessfulSendMessage(parameters);
		final List<ITab> tabs = new ArrayList<ITab>();
		final List<Messages> sentList = onFindSentMessages();
		final List<Messages> unrepliedList = onFindUnrepliedMessages();
		final List<Messages> contactedList = onFindContactedMessages();

		final Object[] paramsinbox = { unrepliedList.size() };
		final IModel<String> inboxModel = ResourceModelFactory.newResourceModel(ResourceBundleKey
			.builder().key("global.inbox.tab.info").parameters(paramsinbox).build(), this);
		tabs.add(new AbstractTab(inboxModel)
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Panel getPanel(final String panelId)
			{
				return newInboxPanel(panelId);
			}
		});

		final Object[] paramsSent = { sentList.size() };
		final IModel<String> sentModel = ResourceModelFactory.newResourceModel(ResourceBundleKey
			.builder().key("global.inbox.sent.tab.info").parameters(paramsSent).build(), this);
		tabs.add(new AbstractTab(sentModel)
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Panel getPanel(final String panelId)
			{
				return newMailSentPanel(panelId);
			}
		});
		final Object[] paramsreaded = { contactedList.size() };
		final IModel<String> readedModel = ResourceModelFactory.newResourceModel(ResourceBundleKey
			.builder().key("global.inbox.readed.tab.info").parameters(paramsreaded).build(), this);

		tabs.add(new AbstractTab(readedModel)
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Panel getPanel(final String panelId)
			{
				return newReadedPanel(panelId);
			}
		});
		ajaxTabbedPanel = new AjaxTabbedPanel<ITab>("tabs", tabs);
		add(ajaxTabbedPanel);
	}

	protected abstract Panel newReadedPanel(final String panelId);

	protected abstract Panel newMailSentPanel(final String panelId);

	protected abstract Panel newInboxPanel(final String panelId);

	protected abstract void onSuccessfulSendMessage(final PageParameters parameters);

	protected abstract List<Messages> onFindSentMessages();

	protected abstract List<Messages> onFindUnrepliedMessages();

	protected abstract List<Messages> onFindContactedMessages();

}
