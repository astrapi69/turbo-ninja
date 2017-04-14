/**
 * Copyright (C) 2015 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alpharogroup.wicket.components.inbox.send;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.message.system.application.models.send.SendMessagePanelModel;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.labeled.textarea.LabeledTextAreaPanel;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledTextFieldPanel;


public abstract class AbstractSendMessagePanel extends Panel
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	protected final Form<SendMessagePanelModel> form;
	protected final Component recipient;
	protected final Component subject;
	protected final LabeledTextAreaPanel<String, SendMessagePanelModel> messageContent;
	protected final Button sendButton;

	/** The Label component. */
	protected final Label sendMessageLabel;

	public AbstractSendMessagePanel(final String id, final PageParameters parameters)
	{
		super(id);
		final SendMessagePanelModel modelObject = onSendMessageModel(parameters);
		setDefaultModel(Model.of(modelObject));
		final IModel<SendMessagePanelModel> cpm = new CompoundPropertyModel<>(modelObject);

		this.form = new Form<>("form", cpm);
		// add the form.
		add(this.form);

		this.form
			.add(this.sendMessageLabel = newLabel("sendMessageLabel", newSendMessageLabelModel()));

		this.form.add(this.recipient = newRecipientPanel("recipient", cpm));

		this.form.add(this.subject = newSubjectPanel("subject", cpm));

		// Create the labeled text area panel for the message content...
		final IModel<String> messageContentModel = new StringResourceModel("inbox.message.label",
			this, null);

		this.messageContent = new LabeledTextAreaPanel<>("messageContent", cpm,
			messageContentModel);
		this.messageContent.getTextArea().add(new AttributeAppender("class", "labeledFormElement"));
		this.form.add(this.messageContent);

		// Create submit button for the form
		this.sendButton = new Button("sendButton")
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit()
			{
				onSendMessage();
			}
		};
		final IModel<String> buttonLabelModel = new StringResourceModel("inbox.send.button.label",
			this, null);
		this.sendButton.add(new Label("buttonLabel", buttonLabelModel));

		this.form.add(this.sendButton);

	}

	/**
	 * Factory method for creating the Label of the send message link. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of Label of the send message link.
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

	protected Component newRecipientPanel(final String id,
		final IModel<SendMessagePanelModel> model)
	{
		final LabeledTextFieldPanel<String, SendMessagePanelModel> panel = new LabeledTextFieldPanel<>(
			id, model, ResourceModelFactory.newResourceModel("inbox.recipient.label", this));
		panel.getTextField().add(new AttributeAppender("class", "labeledFormElement"));

		if (model.getObject().getRecipient() != null)
		{
			panel.getTextField().setEnabled(false);
		}
		return panel;
	}

	/**
	 * Factory method for creating the Model of the Label of the send message link. This method is
	 * invoked in the constructor from the derived classes and can be overridden so users can
	 * provide their own version of the Model of the Label of the send message link.
	 *
	 * @return the Model of the Label of the send message link.
	 */
	protected IModel<String> newSendMessageLabelModel()
	{
		return ResourceModelFactory.newResourceModel("inbox.send.message.header.label", this);
	}

	protected Component newSubjectPanel(final String id, final IModel<SendMessagePanelModel> model)
	{
		final LabeledTextFieldPanel<String, SendMessagePanelModel> panel = new LabeledTextFieldPanel<>(
			id, model, ResourceModelFactory.newResourceModel("inbox.subject.label", this));
		panel.getTextField().add(new AttributeAppender("class", "labeledFormElement"));
		return panel;
	}

	protected abstract void onSendMessage();

	protected abstract SendMessagePanelModel onSendMessageModel(final PageParameters parameters);

}
