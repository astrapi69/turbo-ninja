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

import java.util.List;

import org.apache.wicket.markup.html.form.upload.FileUpload;

import de.alpharogroup.resource.system.application.model.ResourcesModel;


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