package de.alpharogroup.wicket.util.resource;

import org.apache.commons.lang.ArrayUtils;

import de.alpharogroup.resource.system.entities.Resources;
import de.alpharogroup.wicket.base.util.resource.DatabaseImageResource;

public class ByteArrayImageResource extends DatabaseImageResource
{

	private static final long serialVersionUID = 1L;

	public ByteArrayImageResource(final Resources resources)
	{
		super(resources.getContentType(), ArrayUtils.toPrimitive(resources.getContent()));
	}

}