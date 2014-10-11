package org.wicketstuff.chat.components.chat;

import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.wicketstuff.chat.channel.api.ChannelEvent;
import org.wicketstuff.chat.channel.api.IChannelListener;
import org.wicketstuff.chat.channel.api.IChannelService;
import org.wicketstuff.chat.channel.api.IChannelTarget;
import org.wicketstuff.chat.model.ChatroomModel;

public abstract class ChatFragment extends Fragment {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The form. */
	private final Form<ChatroomModel> form;

	/** The chat. */
	private final Component chat;

	/** The message. */
	private final TextField<String> message;

	/** The send. */
	private final AjaxButton send;
	
	public ChatFragment(String id, String markupId,	MarkupContainer markupProvider, final IModel<ChatroomModel> model) {
		super(id, markupId, markupProvider);
		add(form = newForm("chatForm",
				new CompoundPropertyModel<ChatroomModel>(model)));
		form.add(chat = newChatLabel("chat"));
		form.add(message = newMessageTextField("message"));
		form.add(send = newSendAjaxButton("send", form));
		addChannelListener(model);
	}
	

	/**
	 * Adds the channel listener to the channel service.
	 *
	 * @param model
	 *            the model
	 */
	protected void addChannelListener(final IModel<ChatroomModel> model) {
		getChannelService().addChannelListener(this,
				model.getObject().getChannel(), new IChannelListener() {
					private static final long serialVersionUID = 1L;
					public void onEvent(final String channel,
							final Map<String, String> datas,
							final IChannelTarget target) {
						String message = datas.get("message");
						String user = datas.get("user");
						String cssClassValue = " left"; 
							if(user.equals(model.getObject().getUser())){
								cssClassValue = " right";
							}
							String decoratedUser = "<div class=\"header\">"
									+ "<strong class=\"primary-font\">"
									+ user
									+ "</strong>"
									+ "</div>";
							String decoratedMessage = "<p>" + message + "</p>";
							String talktext = "<div class=\"talktext\">"+decoratedMessage+"</div>";
							String bubble = "<div class=\"talk-bubble triangle-right round "
									+ cssClassValue
									+ "-top\">"+talktext+"</div>";

							String div = 
									"<div class=\"chat-body clearfix\">"
									+decoratedUser + bubble
									+"</div>";
						String listItemElement =
								"<li class=\"" +cssClassValue+ " clearfix\">"
								+ div 
								+ "</li>";
						
						target.appendJavaScript("var chathistory = document.getElementById('"
								+ chat.getMarkupId() 
								+ "');"
								+ "chathistory.innerHTML += '"
								+ listItemElement+ "';"
								+ "chathistory.scrollTop = chathistory.scrollHeight;");
					}
				});
	}

	/**
	 * Factory method for creating the AjaxButton. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of an AjaxButton.
	 *
	 * @param id
	 *            the id
	 * @param form
	 *            the form
	 * @return the ajax button
	 */
	protected AjaxButton newSendAjaxButton(String id, Form<ChatroomModel> form) {
		AjaxButton ajaxButton = new AjaxButton(id, form) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(final AjaxRequestTarget target,
					final Form<?> form) {
				ChatroomModel chatroom = ((ChatroomModel) form.getModelObject());
				// Update message
				String currentMessage = chatroom.getMessage();
				if(currentMessage != null && !currentMessage.isEmpty()){
					// send an event to refesh the chat area
					final ChannelEvent event = new ChannelEvent(
							chatroom.getChannel());
					event.addData("user", chatroom.getUser());
					event.addData("message", currentMessage);
					getChannelService().publish(event);
					// clear message area add focus it
					target.appendJavaScript("document.getElementById('"
							+ message.getMarkupId() + "').value =''");
				}				
				target.focusComponent(message);
			}
		};
		return ajaxButton;
	}

	/**
	 * New form.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the form
	 */
	protected Form<ChatroomModel> newForm(String id, IModel<ChatroomModel> model) {
		return new Form<ChatroomModel>(id, model);
	}

	/**
	 * Factory method for creating the Chat component. This method is invoked in
	 * the constructor from the derived classes and can be overridden so users
	 * can provide their own version of a Chat component.
	 * 
	 * @param id
	 *            the id
	 * @return the label
	 */
	protected Component newChatLabel(String id) {
		Label label = new Label(id);
		label.setOutputMarkupId(true);
		return label;
	}

	/**
	 * Factory method for creating the TextField for the message. This method is
	 * invoked in the constructor from the derived classes and can be overridden
	 * so users can provide their own version of a TextField for the message.
	 *
	 * @param id
	 *            the id
	 * @return the text field
	 */
	protected TextField<String> newMessageTextField(String id) {
		TextField<String> textField = new TextField<String>(id);
		textField.setOutputMarkupId(true);
		return textField;
	}

	/**
	 * Abstract method that subclasses must implement. Gets the channel service.
	 * This service should be returned from the application class.
	 *
	 * @return the channel service
	 */
	protected abstract IChannelService getChannelService();

	/**
	 * Gets the form.
	 *
	 * @return the form
	 */
	public Form<ChatroomModel> getForm() {
		return form;
	}

	/**
	 * Gets the chat.
	 *
	 * @return the chat
	 */
	public Component getChat() {
		return chat;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public TextField<String> getMessage() {
		return message;
	}

	/**
	 * Gets the send.
	 *
	 * @return the send
	 */
	public AjaxButton getSend() {
		return send;
	}
	

}
