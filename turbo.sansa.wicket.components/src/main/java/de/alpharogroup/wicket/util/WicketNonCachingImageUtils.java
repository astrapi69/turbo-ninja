package de.alpharogroup.wicket.util;

import java.util.Set;

import org.apache.wicket.markup.html.image.NonCachingImage;
import de.alpharogroup.wicket.base.util.WicketImageUtils;

import resource.system.application.model.ResourcesModel;
import resource.system.model.Resources;
import user.management.model.Users;

/**
 * The Class WicketImageUtils.
 */
public class WicketNonCachingImageUtils {

	/**
	 * Gets the user image with the default wicket:id="image".
	 * 
	 * @param user
	 *            the user
	 * @return the user image
	 */
	public static NonCachingImage getUserImage(Users user) {
		final Set<Resources> resources = user.getUserData().getResources();
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
	public static NonCachingImage getUserImage(Users user, String wicketId) {
		final Set<Resources> images = user.getUserData().getResources();
		return getNonCachingImage(images, wicketId);
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
	public static NonCachingImage getUserNonCachingImage(Users user, String wicketId) {
		final Set<Resources> images = user.getUserData().getResources();
		return getNonCachingImage(images, wicketId);
	}

	/**
	 * Gets the first image from the given Set with the default
	 * wicket:id="image".
	 * 
	 * @param images
	 *            the Set of images
	 * @return the first image in the Set or an empty image.
	 */
	public static NonCachingImage getImage(final Set<Resources> images) {
		return getNonCachingImage(images, "image");
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
	public static NonCachingImage getNonCachingImage(final Set<Resources> images, String wicketId) {
		NonCachingImage image = null;
		Resources img = getFirstImage(images);
		image = getNonCachingImage(img, wicketId);
		return image;
	}


	/**
	 * Gets the Images object from the given user or null if the user does not
	 * have any Images objects in the database.
	 * 
	 * @param user
	 *            the user
	 * @return the user images
	 */
	public static Resources getUserImages(Users user) {
		final Set<Resources> images = user.getUserData().getResources();
		return getFirstImage(images);
	}

	/**
	 * Gets the first Images object from the given Set or null if the given Set
	 * is null or empty.
	 * 
	 * @param images
	 *            the Set of Images objects.
	 * @return the first Images object from the given Set or null if the given
	 *         Set is null or empty.
	 */
	public static Resources getFirstImage(final Set<Resources> images) {
		if (images != null && !images.isEmpty()) {
			Resources img = images.iterator().next();
			return img;
		}
		return null;

	}

	/**
	 * Gets the wicket image from the given db Images object.
	 * 
	 * @param img
	 *            the given db Images object.
	 * @param wicketId
	 *            the wicket id for the wicket image.
	 * @return the image corresponding to the given db Images object or an empty
	 *         image if the Byte array is null from the given db Images object.
	 */
	public static NonCachingImage getNonCachingImage(Resources img, String wicketId) {
		NonCachingImage image = null;
		if (img != null && img.getContent() != null) {
			image = WicketImageUtils.getNonCachingImage(wicketId,
					img.getContentType(), img.getContent());
		} else {
			image = WicketImageUtils.getNonCachingImage(wicketId, "jpg",
					new byte[] {});
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
	 * @return the image corresponding to the given db Images object or an empty
	 *         image if the Byte array is null from the given db Images object.
	 */
	public static NonCachingImage getNonCachingImage(ResourcesModel img, String wicketId) {
		NonCachingImage image = null;
		if (img != null && img.getContent() != null) {
			image = WicketImageUtils.getNonCachingImage(wicketId,
					img.getContentType(), img.getContent());
		} else {
			image = WicketImageUtils.getNonCachingImage(wicketId, "jpg",
					new byte[] {});
		}
		return image;
	}
	
	/**
	 * Gets the non caching image.
	 *
	 * @param wicketId the wicket id
	 * @param data the data
	 * @param contentType the content type
	 * @return the non caching image
	 */
	public static NonCachingImage getNonCachingImage(String wicketId, byte[] data, String contentType){
		NonCachingImage image = WicketImageUtils.getNonCachingImage(wicketId, contentType, data);
		return image;
	}

}
