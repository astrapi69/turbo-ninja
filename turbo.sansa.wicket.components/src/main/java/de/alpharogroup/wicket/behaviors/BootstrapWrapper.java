package de.alpharogroup.wicket.behaviors;

import org.jaulp.wicket.behaviors.JqueryStatementsBehavior;
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
	public static final JqueryStatementsBehavior ROW_ELEMENT = new JqueryStatementsBehavior()
	.add(new BuildableChainableStatement.Builder().label("wrap")
			.args(JsUtils.quotes("<div class=\"row\"></div>"))
			.build());

	/** The Constant CONTROLS_ELEMENT. */
	public static final JqueryStatementsBehavior CONTROLS_ELEMENT = new JqueryStatementsBehavior()
			.add(new BuildableChainableStatement.Builder().label("wrap")
					.args(JsUtils.quotes("<div class=\"controls\"></div>"))
					.build());

	/** The Constant INPUT_ELEMENT. */
	public static final JqueryStatementsBehavior INPUT_ELEMENT = new JqueryStatementsBehavior()
	.add(new BuildableChainableStatement.Builder().label("wrap")
			.args(JsUtils.quotes("<div class=\"input-group\"></div>"))
			.build());

	/** The Constant INPUT_GROUP_BUTTON_ELEMENT. */
	public static final JqueryStatementsBehavior INPUT_GROUP_BUTTON_ELEMENT = new JqueryStatementsBehavior()
	.add(new BuildableChainableStatement.Builder().label("wrap")
			.args(JsUtils.quotes("<span class=\"input-group-btn\"></span>"))
			.build());

	/** The Constant CONTROL_GROUP_ELEMENT. */
	public static final JqueryStatementsBehavior CONTROL_GROUP_ELEMENT = new JqueryStatementsBehavior()
			.add(new BuildableChainableStatement.Builder().label("wrap")
					.args(JsUtils.quotes("<div class=\"control-group\"></div>"))
					.build());
}
