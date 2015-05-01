package de.alpharogroup.wicket.util.resource;

import org.apache.commons.lang.ArrayUtils;
import de.alpharogroup.wicket.base.util.resource.DatabaseImageResource;

import resource.system.model.Resources;

public class ByteArrayImageResource extends DatabaseImageResource {

	private static final long serialVersionUID = 1L;

	public ByteArrayImageResource(Resources resources) {
		super(resources.getContentType(), ArrayUtils.toPrimitive( resources.getContent() ));
	}

}