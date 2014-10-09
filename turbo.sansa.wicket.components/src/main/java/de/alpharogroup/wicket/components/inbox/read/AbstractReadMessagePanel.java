package de.alpharogroup.wicket.components.inbox.read;

import message.system.model.Messages;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.wicket.base.BasePanel;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;
import org.jaulp.wicket.components.img.WicketImage;
import org.jaulp.wicket.components.labeled.label.LabeledEnumLabelPanel;
import org.jaulp.wicket.components.labeled.textarea.LabeledTextAreaPanel;

/**
 * The Class AbstractReadMessagePanel.
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractReadMessagePanel extends BasePanel {

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
	protected LabeledTextAreaPanel<Messages> messageContent;
	
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
	 * @param id the id
	 * @param parameters the parameters
	 */
	public AbstractReadMessagePanel(String id, final PageParameters parameters) {
		super(id);
		final Messages message = onMessageRead(parameters);
		setDefaultModel(Model.of(message));
		final CompoundPropertyModel<Messages> cpm = new CompoundPropertyModel<Messages>(
				message);

		form = new Form<Messages>("form", cpm);
		// add the form.
		add(form);

		form.add(readMessageLabel = newLabel("readMessageLabel", newReadMessageLabelModel()));
		
		final IModel<String> junkImageModel = Model.of("/images/junkmail.png");

		junkmail = new WicketImage("junkmail", junkImageModel);
		junkmail.setVisible(message.isSpamFlag());

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

		// Create delete button for the form
		deleteButton = new Button("deleteButton") {
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				onDelete();
			}
		};

		form.add(deleteButton);
		// Create junk button for the form
		junkButton = new Button("junkButton") {
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				onJunk();
			}
		};
		junkButton.setEnabled(!message.isSpamFlag());

		form.add(junkButton);
		// Create 'no junk' button for the form
		noJunkButton = new Button("noJunkButton") {
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				onNoJunk();
			}
		};
		noJunkButton.setEnabled(message.isSpamFlag());
		form.add(noJunkButton);

		form.add(junkmail);

		form.add(sender = newSenderPanel("sender.username", cpm));

		form.add(sentDate = newSentDatePanel("sentDate", cpm));

		form.add(subject = newSubjectPanel("subject", cpm));

		// Create the label for content(the content of the message)...
		IModel<String> messageContentLabelModel = new StringResourceModel(
				"inbox.message.content.label", this, null);
		messageContent = new LabeledTextAreaPanel<Messages>(
				"messageContent", cpm, messageContentLabelModel);
		messageContent.getTextArea().setEnabled(false);
		form.add(messageContent);

		// Create 'reply' button for the form
		replyButton = new Button("replyButton") {
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				onReply();
			}
		};

		form.add(replyButton);

	}
	
	protected Component newSenderPanel(String id, IModel<Messages> model) {
		LabeledEnumLabelPanel<Messages> panel = new LabeledEnumLabelPanel<Messages>(
				id, model, ResourceModelFactory.newResourceModel("inbox.sender.label", this));
		return panel;
	}
	
	protected Component newSentDatePanel(String id, IModel<Messages> model) {
		LabeledEnumLabelPanel<Messages> panel = new LabeledEnumLabelPanel<Messages>(
				id, model, ResourceModelFactory.newResourceModel("inbox.sent.date.label", this));
		return panel;
	}
	
	protected Component newSubjectPanel(String id, IModel<Messages> model) {
		LabeledEnumLabelPanel<Messages> panel = new LabeledEnumLabelPanel<Messages>(
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
	protected IModel<String> newReadMessageLabelModel(){
		return ResourceModelFactory.newResourceModel("inbox.read.message.header.label", this);
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
	 * @param parameters the parameters
	 * @return the messages
	 */
	protected abstract Messages onMessageRead(PageParameters parameters);
	
	/**
	 * Callback method for the button inbox.
	 */
	protected abstract void onInbox();
}
