package de.alpharogroup.wicket.components.inbox.send.response;

import message.system.application.models.ReplyMessageModel;
import message.system.application.models.utlis.MessageModelConverter;
import message.system.model.Messages;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;
import org.jaulp.wicket.components.labeled.label.LabeledEnumLabelPanel;
import org.jaulp.wicket.components.labeled.textarea.LabeledTextAreaPanel;
import org.jaulp.wicket.components.labeled.textfield.LabeledTextFieldPanel;

public abstract class AbstractSendResponseMessagePanel extends Panel {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	protected final Form<ReplyMessageModel> form;
	protected final Button inboxButton;
	protected final Button upperSendButton;
	protected Component sender;
	protected Component subject;
	protected LabeledTextAreaPanel<ReplyMessageModel> messageContent;
	protected final Button lowerSendButton;
	/** The Label component. */
	protected final Label replyMessageLabel;
	

	public AbstractSendResponseMessagePanel(String id, final PageParameters parameters) {
		super(id);

		Messages message = onGetMessage(parameters);
		ReplyMessageModel model = MessageModelConverter.convert(message);
		setDefaultModel(Model.of(model));
		String responseSubjectPart = "RE:";
		if(model.getMessageContentModel().getSubject() != null){
			responseSubjectPart = responseSubjectPart + model.getMessageContentModel().getSubject();
		}
		model.setResponseSubject(responseSubjectPart);

		final CompoundPropertyModel<ReplyMessageModel> cpm = new CompoundPropertyModel<ReplyMessageModel>(
				model);

		form = new Form<ReplyMessageModel>(
				"form", cpm);
		// add the form.
		add(form);

		form.add(replyMessageLabel = newLabel("replyMessageLabel", newReplyMessageLabelModel()));

		// Create inbox button for the form
		inboxButton = new Button("inboxButton") {
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				onInbox();
			}
		};

		form.add(inboxButton);

		// Create upperSendButton button for the form
		upperSendButton = new Button("upperSendButton") {
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				onSend();
			}
		};

		form.add(upperSendButton);

		form.add(sender = newSenderPanel("sendInformationModel.sender.username", cpm));

		form.add(subject = newSubjectPanel("responseSubject", cpm));

		// Create the label for content(the content of the message)...
		IModel<String> messageContentLabelModel = new StringResourceModel(
				"inbox.message.content.label", this, null);
		messageContent = new LabeledTextAreaPanel<ReplyMessageModel>(
				"responseMessage", cpm, messageContentLabelModel);
		form.add(messageContent);

		// Create 'lowerSendButton' button for the form
		lowerSendButton = new Button("lowerSendButton") {
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				onSend();
			}
		};

		form.add(lowerSendButton);
	}
	
	protected Component newSenderPanel(String id, IModel<ReplyMessageModel> model) {
		LabeledEnumLabelPanel<ReplyMessageModel> panel = new LabeledEnumLabelPanel<ReplyMessageModel>(
				id, model, ResourceModelFactory.newResourceModel("inbox.sender.label", this));
		return panel;
	}
	
	protected Component newSubjectPanel(String id, IModel<ReplyMessageModel> model) {
		LabeledTextFieldPanel<ReplyMessageModel> panel = new LabeledTextFieldPanel<ReplyMessageModel>(
				id, model, ResourceModelFactory.newResourceModel("inbox.subject.label", this));
		return panel;
	}
	
	/**
	 * Factory method for creating the Model of the Label of the read message link. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of the Model of the Label of the read message link.
	 *
	 * @return the Model of the Label of the send message link.
	 */
	protected IModel<String> newReplyMessageLabelModel(){
		return ResourceModelFactory.newResourceModel("inbox.reply.message.header.label", this);
	}

	/**
	 * Factory method for creating the Label of the read message link. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of Label of the read message link.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the label
	 */
	protected Label newLabel(String id, IModel<String> model) {
		Label label = new Label(id, model);
		return label;
	}

	protected abstract void onSend();

	protected abstract void onInbox();

	protected abstract Messages onGetMessage(PageParameters parameters);

}
