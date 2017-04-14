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
package de.alpharogroup.wicket.components.upload;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.form.upload.UploadProgressBar;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Bytes;

import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;

/**
 * The Class UploadFilePanel.
 * 
 * @author Asterios Raptis
 */
public abstract class UploadFilePanel extends GenericPanel<UploadFileModel>
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The file upload field. */
	private final FileUploadField fileUploadField;

	/** The form. */
	private final Form<?> form;

	/** The submit button. */
	private final Button submitButton;

	/** The button label. */
	private final Label buttonLabel;

	/** The fileInput Label. */
	private final Label fileInputLabel;

	/** The max size of the upload file. */
	private final Bytes maxSize;

	/**
	 * Instantiates a new upload file panel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public UploadFilePanel(final String id, final IModel<UploadFileModel> model)
	{
		super(id, model);
		this.setOutputMarkupId(true);
		this.maxSize = newMaxSize();
		add(form = newForm("form", model));
		form.add(fileInputLabel = newFileInputLabel("fileInputLabel", "upload.file.input.label",
			"File", this));
		form.add(fileUploadField = newFileUploadField("fileInput"));
		form.add(submitButton = newButton("submitButton", form));
		submitButton.add(buttonLabel = newButtonLabel("buttonLabel",
			"upload.file.submit.value.label", "Send", this));
	}


	/**
	 * Gets the button label.
	 *
	 * @return the button label
	 */
	public Label getButtonLabel()
	{
		return buttonLabel;
	}

	/**
	 * Gets the file input label.
	 *
	 * @return the file input label
	 */
	public Label getFileInputLabel()
	{
		return fileInputLabel;
	}

	/**
	 * Gets the file upload field.
	 *
	 * @return the file upload field
	 */
	public FileUploadField getFileUploadField()
	{
		return fileUploadField;
	}

	/**
	 * Gets the form.
	 *
	 * @return the form
	 */
	public Form<?> getForm()
	{
		return form;
	}

	/**
	 * Gets the max size.
	 *
	 * @return the max size
	 */
	public Bytes getMaxSize()
	{
		return maxSize;
	}

	/**
	 * Gets the submit button.
	 *
	 * @return the submit button
	 */
	public Button getSubmitButton()
	{
		return submitButton;
	}

	/**
	 * 
	 * Factory method for creating the Button. This method is invoked in the constructor from the
	 * derived classes and can be overridden so users can provide their own version of a Button.
	 *
	 * @param id
	 *            the id
	 * @param form
	 *            the form
	 * @return the button
	 */
	protected Button newButton(final String id, final Form<?> form)
	{
		return new IndicatingAjaxButton(id, form)
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onError(final AjaxRequestTarget target, final Form<?> form)
			{
				target.add(form);
				onUpload(target, form, true);
			}

			@Override
			protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				target.add(form);
				onUpload(target, form, false);
			}
		};
	}

	/**
	 * Factory method for creating the button Label. This method is invoked in the constructor from
	 * the derived classes and can be overridden so users can provide their own version of the
	 * button Label.
	 * 
	 * @param id
	 *            the id
	 * @param resourceKey
	 *            the resource key
	 * @param defaultValue
	 *            the default value
	 * @param component
	 *            the component
	 * @return the label
	 */
	protected Label newButtonLabel(final String id, final String resourceKey,
		final String defaultValue, final Component component)
	{
		final IModel<String> labelModel = ResourceModelFactory.newResourceModel(resourceKey,
			component, defaultValue);
		final Label label = new Label(id, labelModel);
		label.setOutputMarkupId(true);
		return label;
	}

	/**
	 * Factory method for creating the fileInput Label. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of the
	 * fileInput Label.
	 * 
	 * @param id
	 *            the id
	 * @param resourceKey
	 *            the resource key
	 * @param defaultValue
	 *            the default value
	 * @param component
	 *            the component
	 * @return the label
	 */
	protected Label newFileInputLabel(final String id, final String resourceKey,
		final String defaultValue, final Component component)
	{
		final IModel<String> labelModel = ResourceModelFactory.newResourceModel(resourceKey,
			component, defaultValue);
		final Label label = new Label(id, labelModel);
		label.setOutputMarkupId(true);
		return label;
	}

	/**
	 * Factory method for creating the FileUploadField. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * FileUploadField.
	 *
	 * @param id
	 *            the id
	 * @return the file upload field
	 */
	protected FileUploadField newFileUploadField(final String id)
	{
		return new FileUploadField(id);
	}

	/**
	 * Factory method for creating the Form. This method is invoked in the constructor from the
	 * derived classes and can be overridden so users can provide their own version of a Form.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the form
	 */
	@SuppressWarnings("unchecked")
	protected Form<?> newForm(final String id, final IModel<?> model)
	{
		final Form<UploadFileModel> form = new Form<UploadFileModel>(id,
			(IModel<UploadFileModel>)model);
		form.setOutputMarkupId(true);
		// Set to true to use enctype='multipart/form-data',
		// and to process file uploads by default multiPart = false
		form.setMultiPart(true);
		// Set maximum size of the uploaded file.
		form.setMaxSize(getMaxSize());
		return form;
	}

	/**
	 * Factory method for that sets the maximum size of the uploaded file. This method is invoked in
	 * the constructor from the derived classes and can be overridden so users can provide their own
	 * version of the maximum size of the uploaded file.
	 *
	 * @return the bytes
	 */
	protected Bytes newMaxSize()
	{
		return Bytes.kilobytes(500);
	}

	/**
	 * Factory method for creating the UploadProgressBar. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * UploadProgressBar.
	 *
	 * @param id
	 *            the id
	 * @param form
	 *            the form
	 * @param uploadField
	 *            the upload field
	 * @return the upload progress bar
	 */
	protected UploadProgressBar newUploadProgressBar(final String id, final Form<?> form,
		final FileUploadField uploadField)
	{
		return new UploadProgressBar(id, form, uploadField);
	}

	/**
	 * Template method that must be implemented from all derived classes. Here comes the logic what
	 * shell happen when an upload is started. Note: Add the feedback to the target so the info and
	 * error messages will be displayed.
	 *
	 * @param target
	 *            the target
	 * @param form
	 *            the form
	 * @param error
	 *            flag that indicates that an error occured.
	 */
	protected abstract void onUpload(final AjaxRequestTarget target, final Form<?> form,
		final boolean error);

}
