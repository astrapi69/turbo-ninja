package de.alpharogroup.wicket.util;

import java.util.Set;

import org.apache.wicket.markup.html.image.NonCachingImage;

import de.alpharogroup.resource.system.application.model.ResourcesModel;
import de.alpharogroup.resource.system.entities.Resources;
import de.alpharogroup.user.management.entities.UserDatas;
import de.alpharogroup.wicket.base.util.WicketImageExtensions;

/**
 * The Class WicketNonCachingImageExtensions.
 */
public class WicketNonCachingImageExtensions
{

	/**
	 * Gets the first Images object from the given Set or null if the given Set is null or empty.
	 *
	 * @param images
	 *            the Set of Images objects.
	 * @return the first Images object from the given Set or null if the given Set is null or empty.
	 */
	public static Resources getFirstImage(final Set<Resources> images)
	{
		if ((images != null) && !images.isEmpty())
		{
			final Resources img = images.iterator().next();
			return img;
		}
		return null;

	}

	/**
	 * Gets the first image from the given Set with the default wicket:id="image".
	 *
	 * @param images
	 *            the Set of images
	 * @return the first image in the Set or an empty image.
	 */
	public static NonCachingImage getImage(final Set<Resources> images)
	{
		return getNonCachingImage(images, "image");
	}

	/**
	 * Gets the wicket image from the given db Images object.
	 *
	 * @param img
	 *            the given db Images object.
	 * @param wicketId
	 *            the wicket id for the wicket image.
	 * @return the image corresponding to the given db Images object or an empty image if the Byte
	 *         array is null from the given db Images object.
	 */
	public static NonCachingImage getNonCachingImage(final Resources img, final String wicketId)
	{
		NonCachingImage image = null;
		if ((img != null) && (img.getContent() != null))
		{
			image = WicketImageExtensions.getNonCachingImage(wicketId, img.getContentType(),
				img.getContent());
		}
		else
		{
			image = WicketImageExtensions.getNonCachingImage(wicketId, "jpg", new byte[] { });
		}
		return image;
	}

	/**
	 * Gets the wicket image from the given db Images object.
	 *
	 * @param img
	 *            the given db Images object.
	 * @param wicketId
	 *            the wicket id for the wicket image.
	 * @return the image corresponding to the given db Images object or an empty image if the Byte
	 *         array is null from the given db Images object.
	 */
	public static NonCachingImage getNonCachingImage(final ResourcesModel img, final String wicketId)
	{
		NonCachingImage image = null;
		if ((img != null) && (img.getContent() != null))
		{
			image = WicketImageExtensions.getNonCachingImage(wicketId, img.getContentType(),
				img.getContent());
		}
		else
		{
			image = WicketImageExtensions.getNonCachingImage(wicketId, "jpg", new byte[] { });
		}
		return image;
	}

	/**
	 * Gets the first image from the given Set.
	 *
	 * @param images
	 *            the Set of images
	 * @param wicketId
	 *            the id from the image for the html template.
	 * @return the first image in the Set or an empty image.
	 */
	public static NonCachingImage getNonCachingImage(final Set<Resources> images,
		final String wicketId)
	{
		NonCachingImage image = null;
		final Resources img = getFirstImage(images);
		image = getNonCachingImage(img, wicketId);
		return image;
	}

	/**
	 * Gets the non caching image.
	 *
	 * @param wicketId
	 *            the wicket id
	 * @param data
	 *            the data
	 * @param contentType
	 *            the content type
	 * @return the non caching image
	 */
	public static NonCachingImage getNonCachingImage(final String wicketId, final byte[] data,
		final String contentType)
	{
		final NonCachingImage image = WicketImageExtensions.getNonCachingImage(wicketId,
			contentType, data);
		return image;
	}

	/**
	 * Gets the user image with the default wicket:id="image".
	 *
	 * @param user
	 *            the user
	 * @return the user image
	 */
	public static NonCachingImage getUserImage(final UserDatas userData)
	{
		final Set<Resources> resources = userData.getResources();
		return getImage(resources);
	}

	/**
	 * Gets the user image.
	 *
	 * @param user
	 *            the user
	 * @param wicketId
	 *            the id from the image for the html template.
	 * @return the user image
	 */
	public static NonCachingImage getUserImage(final UserDatas userData, final String wicketId)
	{
		final Set<Resources> images = userData.getResources();
		return getNonCachingImage(images, wicketId);
	}

	/**
	 * Gets the Images object from the given user or null if the user does not have any Images
	 * objects in the database.
	 *
	 * @param user
	 *            the user
	 * @return the user images
	 */
	public static Resources getUserImages(final UserDatas userData )
	{
		final Set<Resources> images = userData.getResources();
		return getFirstImage(images);
	}

	/**
	 * Gets the user image.
	 *
	 * @param user
	 *            the user
	 * @param wicketId
	 *            the id from the image for the html template.
	 * @return the user image
	 */
	public static NonCachingImage getUserNonCachingImage(final UserDatas userData, final String wicketId)
	{
		final Set<Resources> images = userData.getResources();
		return getNonCachingImage(images, wicketId);
	}

}
