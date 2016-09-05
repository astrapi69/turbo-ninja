package de.alpharogroup.wicket.components.inbox.read;


import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.message.system.entities.Messages;
import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.img.WicketImage;
import de.alpharogroup.wicket.components.labeled.label.LabeledEnumLabelPanel;
import de.alpharogroup.wicket.components.labeled.textarea.LabeledTextAreaPanel;

/**
 * The Class AbstractReadMessagePanel.
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractReadMessagePanel extends BasePanel
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	/** The junk button. */
	protected final Button junkButton;

	/** The no junk button. */
	protected final Button noJunkButton;

	/** The junkmail. */
	protected final WicketImage junkmail;

	/** The message content. */
	protected LabeledTextAreaPanel<String, Messages> messageContent;

	protected final Form<Messages> form;

	protected final Button inboxButton;

	protected final Button deleteButton;

	protected final Component sender;

	protected final Component sentDate;

	protected final Component subject;

	protected final Button replyButton;

	/** The Label component. */
	protected final Label readMessageLabel;

	/**
	 * Instantiates a new abstract read message panel.
	 *
	 * @param id
	 *            the id
	 * @param parameters
	 *            the parameters
	 */
	public AbstractReadMessagePanel(final String id, final PageParameters parameters)
	{
		super(id);
		final Messages message = onMessageRead(parameters);
		setDefaultModel(Model.of(message));
		final CompoundPropertyModel<Messages> cpm = new CompoundPropertyModel<>(message);

		this.form = new Form<>("form", cpm);
		// add the form.
		add(this.form);

		this.form
			.add(this.readMessageLabel = newLabel("readMessageLabel", newReadMessageLabelModel()));

		final IModel<String> junkImageModel = Model.of("/images/junkmail.png");

		this.junkmail = new WicketImage("junkmail", junkImageModel);
		this.junkmail.setVisible(message.isSpamFlag());

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

		// Create delete button for the form
		this.deleteButton = new Button("deleteButton")
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit()
			{
				onDelete();
			}
		};

		this.form.add(this.deleteButton);
		// Create junk button for the form
		this.junkButton = new Button("junkButton")
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit()
			{
				onJunk();
			}
		};
		this.junkButton.setEnabled(!message.isSpamFlag());

		this.form.add(this.junkButton);
		// Create 'no junk' button for the form
		this.noJunkButton = new Button("noJunkButton")
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit()
			{
				onNoJunk();
			}
		};
		this.noJunkButton.setEnabled(message.isSpamFlag());
		this.form.add(this.noJunkButton);

		this.form.add(this.junkmail);

		this.form.add(this.sender = newSenderPanel("sender.username", cpm));

		this.form.add(this.sentDate = newSentDatePanel("sentDate", cpm));

		this.form.add(this.subject = newSubjectPanel("subject", cpm));

		// Create the label for content(the content of the message)...
		final IModel<String> messageContentLabelModel = new StringResourceModel(
			"inbox.message.content.label", this, null);
		this.messageContent = new LabeledTextAreaPanel<>("messageContent", cpm,
			messageContentLabelModel);
		this.messageContent.getTextArea().setEnabled(false);
		this.form.add(this.messageContent);

		// Create 'reply' button for the form
		this.replyButton = new Button("replyButton")
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit()
			{
				onReply();
			}
		};

		this.form.add(this.replyButton);

	}

	protected Component newSenderPanel(final String id, final IModel<Messages> model)
	{
		final LabeledEnumLabelPanel<Messages> panel = new LabeledEnumLabelPanel<>(id, model,
			ResourceModelFactory.newResourceModel("inbox.sender.label", this));
		return panel;
	}

	protected Component newSentDatePanel(final String id, final IModel<Messages> model)
	{
		final LabeledEnumLabelPanel<Messages> panel = new LabeledEnumLabelPanel<>(id, model,
			ResourceModelFactory.newResourceModel("inbox.sent.date.label", this));
		return panel;
	}

	protected Component newSubjectPanel(final String id, final IModel<Messages> model)
	{
		final LabeledEnumLabelPanel<Messages> panel = new LabeledEnumLabelPanel<>(id, model,
			ResourceModelFactory.newResourceModel("inbox.subject.label", this));
		return panel;
	}

	/**
	 * Factory method for creating the Model of the Label of the read message link. This method is
	 * invoked in the constructor from the derived classes and can be overridden so users can
	 * provide their own version of the Model of the Label of the read message link.
	 *
	 * @return the Model of the Label of the send message link.
	 */
	protected IModel<String> newReadMessageLabelModel()
	{
		return ResourceModelFactory.newResourceModel("inbox.read.message.header.label", this);
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

	/**
	 * Callback method for the button reply.
	 */
	protected abstract void onReply();

	/**
	 * Callback method for the button no junk.
	 */
	protected abstract void onNoJunk();

	/**
	 * Callback method for the button junk.
	 */
	protected abstract void onJunk();

	/**
	 * Callback method for the button delete.
	 */
	protected abstract void onDelete();

	/**
	 * Gets the message from db to read.
	 *
	 * @param parameters
	 *            the parameters
	 * @return the messages
	 */
	protected abstract Messages onMessageRead(final PageParameters parameters);

	/**
	 * Callback method for the button inbox.
	 */
	protected abstract void onInbox();
}
