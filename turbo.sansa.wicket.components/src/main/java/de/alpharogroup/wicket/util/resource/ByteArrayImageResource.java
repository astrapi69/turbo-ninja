package de.alpharogroup.wicket.util.resource;

import org.apache.commons.lang.ArrayUtils;

import resource.system.model.Resources;
import de.alpharogroup.wicket.base.util.resource.DatabaseImageResource;

public class ByteArrayImageResource extends DatabaseImageResource
{

	private static final long serialVersionUID = 1L;

	public ByteArrayImageResource(final Resources resources)
	{
		super(resources.getContentType(), ArrayUtils.toPrimitive(resources.getContent()));
	}

}