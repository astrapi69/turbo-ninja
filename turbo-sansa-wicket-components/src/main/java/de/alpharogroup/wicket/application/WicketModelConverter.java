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
package de.alpharogroup.wicket.application;

import java.util.List;

import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.model.util.ListModel;

import de.alpharogroup.date.CreateDateExtensions;
import de.alpharogroup.file.checksum.Algorithm;
import de.alpharogroup.file.checksum.ChecksumExtensions;
import de.alpharogroup.resource.system.application.model.ResourcesModel;
import de.alpharogroup.resource.system.application.util.ModelConverter;
import de.alpharogroup.resource.system.entities.Resources;

/**
 * The Class WicketModelConverter.
 */
public class WicketModelConverter
{

	/**
	 * To resource model list.
	 *
	 * @param resources
	 *            the resources
	 * @return the list model
	 */
	public static ListModel<ResourcesModel> toResourceModelList(final List<Resources> resources)
	{
		return new ListModel<ResourcesModel>(ModelConverter.toResourcesModel(resources));
	}


	/**
	 * Converts the given upload to a resources model.
	 *
	 * @param upload
	 *            the upload
	 * @param model
	 *            the model
	 * @param description
	 *            the description
	 * @return the resources model
	 */
	public static ResourcesModel toResourcesModel(final FileUpload upload,
		final ResourcesModel model, final String description)
	{
		model.setContent(upload.getBytes());
		model.setContentType(upload.getContentType());
		model.setDescription(description);
		model.setFilename(upload.getClientFileName());
		model.setFilesize(upload.getSize() + "");
		model.setCreated(CreateDateExtensions.now());
		model.setChecksum(ChecksumExtensions.getChecksumQuietly(upload.getBytes(),
			Algorithm.SHA_256.getAlgorithm()));
		model.setDeletedFlag(Boolean.FALSE);
		return model;
	}

}