package org.wicketstuff.chat.components.notifications.pnotify;


import org.apache.wicket.request.resource.CssResourceReference;

public class PnotifyCssReference extends CssResourceReference {
	private static final long serialVersionUID = 1L;
	public static final PnotifyCssReference INSTANCE = new PnotifyCssReference();

	private PnotifyCssReference() {
		super(PnotifyCssReference.class, "css/pnotify.custom.min.css");
	}
}