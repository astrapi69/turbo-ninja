package de.alpharogroup.wicket.components.upload;

import java.util.List;

import org.apache.wicket.markup.html.form.upload.FileUpload;

import resource.system.application.model.ResourcesModel;


/**
 * The Class UploadFileModel.
 * 
 * @author Asterios Raptis
 */
public class UploadFileModel extends ResourcesModel
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	private List<FileUpload> fileInput;

	/**
	 * Instantiates a new upload file model.
	 */
	public UploadFileModel()
	{
		super();
	}

	public List<FileUpload> getFileInput()
	{
		return fileInput;
	}

	public void setFileInput(final List<FileUpload> fileInput)
	{
		this.fileInput = fileInput;
	}

}