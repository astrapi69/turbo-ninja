package de.alpharogroup.wicket.components.inbox.send.response;

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

import de.alpharogroup.message.system.application.models.ReplyMessageModel;
import de.alpharogroup.message.system.application.models.utils.MessageModelConverter;
import de.alpharogroup.message.system.entities.Messages;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.labeled.label.LabeledEnumLabelPanel;
import de.alpharogroup.wicket.components.labeled.textarea.LabeledTextAreaPanel;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledTextFieldPanel;

public abstract class AbstractSendResponseMessagePanel extends Panel
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	protected final Form<ReplyMessageModel> form;
	protected final Button inboxButton;
	protected final Button upperSendButton;
	protected Component sender;
	protected Component subject;
	protected LabeledTextAreaPanel<String, ReplyMessageModel> messageContent;
	protected final Button lowerSendButton;
	/** The Label component. */
	protected final Label replyMessageLabel;


	public AbstractSendResponseMessagePanel(final String id, final PageParameters parameters)
	{
		super(id);

		final Messages message = onGetMessage(parameters);
		final ReplyMessageModel model = MessageModelConverter.convert(message);
		setDefaultModel(Model.of(model));
		String responseSubjectPart = "RE:";
		if (model.getMessageContentModel().getSubject() != null)
		{
			responseSubjectPart = responseSubjectPart + model.getMessageContentModel().getSubject();
		}
		model.setResponseSubject(responseSubjectPart);

		final CompoundPropertyModel<ReplyMessageModel> cpm = new CompoundPropertyModel<>(model);

		this.form = new Form<>("form", cpm);
		// add the form.
		add(this.form);

		this.form.add(
			this.replyMessageLabel = newLabel("replyMessageLabel", newReplyMessageLabelModel()));

		// Create inbox button for the form
		this.inboxButton = new Button("inboxButton")
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit()
			{
				onInbox();
			}
		};

		this.form.add(this.inboxButton);

		// Create upperSendButton button for the form
		this.upperSendButton = new Button("upperSendButton")
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit()
			{
				onSend();
			}
		};

		this.form.add(this.upperSendButton);

		this.form.add(this.sender = newSenderPanel("sendInformationModel.sender.username", cpm));

		this.form.add(this.subject = newSubjectPanel("responseSubject", cpm));

		// Create the label for content(the content of the message)...
		final IModel<String> messageContentLabelModel = new StringResourceModel(
			"inbox.message.content.label", this, null);
		this.messageContent = new LabeledTextAreaPanel<>("responseMessage", cpm,
			messageContentLabelModel);
		this.form.add(this.messageContent);

		// Create 'lowerSendButton' button for the form
		this.lowerSendButton = new Button("lowerSendButton")
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit()
			{
				onSend();
			}
		};

		this.form.add(this.lowerSendButton);
	}

	protected Component newSenderPanel(final String id, final IModel<ReplyMessageModel> model)
	{
		final LabeledEnumLabelPanel<ReplyMessageModel> panel = new LabeledEnumLabelPanel<>(id,
			model, ResourceModelFactory.newResourceModel("inbox.sender.label", this));
		return panel;
	}

	protected Component newSubjectPanel(final String id, final IModel<ReplyMessageModel> model)
	{
		final LabeledTextFieldPanel<String, ReplyMessageModel> panel = new LabeledTextFieldPanel<>(
			id, model, ResourceModelFactory.newResourceModel("inbox.subject.label", this));
		return panel;
	}

	/**
	 * Factory method for creating the Model of the Label of the read message link. This method is
	 * invoked in the constructor from the derived classes and can be overridden so users can
	 * provide their own version of the Model of the Label of the read message link.
	 *
	 * @return the Model of the Label of the send message link.
	 */
	protected IModel<String> newReplyMessageLabelModel()
	{
		return ResourceModelFactory.newResourceModel("inbox.reply.message.header.label", this);
	}

	/**
	 * Factory method for creating the Label of the read message link. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of Label of the read message link.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the label
	 */
	protected Label newLabel(final String id, final IModel<String> model)
	{
		final Label label = new Label(id, model);
		return label;
	}

	protected abstract void onSend();

	protected abstract void onInbox();

	protected abstract Messages onGetMessage(final PageParameters parameters);

}
