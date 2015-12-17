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