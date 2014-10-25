package de.alpharogroup.wicket.behaviors;

import org.jaulp.wicket.behaviors.AddJsQueryStatementsBehavior;
import org.jaulp.wicket.behaviors.BuildableChainableStatement;
import org.odlabs.wiquery.core.javascript.JsUtils;

/**
 * The Class BootstrapWrapper holder constants of behaviors that can component wrap with.
 */
public final class BootstrapWrapper {

	/**
	 * Instantiates a new BootstrapWrapper.
	 */
	private BootstrapWrapper() {
	}

	/** The Constant ROW_ELEMENT. */
	public static final AddJsQueryStatementsBehavior ROW_ELEMENT = new AddJsQueryStatementsBehavior()
	.add(new BuildableChainableStatement.Builder().label("wrap")
			.args(JsUtils.quotes("<div class=\"row\"></div>"))
			.build());

	/** The Constant CONTROLS_ELEMENT. */
	public static final AddJsQueryStatementsBehavior CONTROLS_ELEMENT = new AddJsQueryStatementsBehavior()
			.add(new BuildableChainableStatement.Builder().label("wrap")
					.args(JsUtils.quotes("<div class=\"controls\"></div>"))
					.build());

	/** The Constant INPUT_ELEMENT. */
	public static final AddJsQueryStatementsBehavior INPUT_ELEMENT = new AddJsQueryStatementsBehavior()
	.add(new BuildableChainableStatement.Builder().label("wrap")
			.args(JsUtils.quotes("<div class=\"input-group\"></div>"))
			.build());

	/** The Constant INPUT_GROUP_BUTTON_ELEMENT. */
	public static final AddJsQueryStatementsBehavior INPUT_GROUP_BUTTON_ELEMENT = new AddJsQueryStatementsBehavior()
	.add(new BuildableChainableStatement.Builder().label("wrap")
			.args(JsUtils.quotes("<span class=\"input-group-btn\"></span>"))
			.build());

	/** The Constant CONTROL_GROUP_ELEMENT. */
	public static final AddJsQueryStatementsBehavior CONTROL_GROUP_ELEMENT = new AddJsQueryStatementsBehavior()
			.add(new BuildableChainableStatement.Builder().label("wrap")
					.args(JsUtils.quotes("<div class=\"control-group\"></div>"))
					.build());
}
