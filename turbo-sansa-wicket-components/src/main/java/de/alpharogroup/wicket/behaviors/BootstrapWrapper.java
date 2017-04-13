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
package de.alpharogroup.wicket.behaviors;

import org.odlabs.wiquery.core.javascript.JsUtils;

/**
 * The Class BootstrapWrapper holder constants of behaviors that can component wrap with.
 */
public final class BootstrapWrapper
{

	/** The Constant ROW_ELEMENT. */
	public static final JqueryStatementsBehavior ROW_ELEMENT = new JqueryStatementsBehavior()
		.add(new BuildableChainableStatement.Builder().label("wrap")
			.args(JsUtils.quotes("<div class=\"row\"></div>")).build());


	/** The Constant CONTROLS_ELEMENT. */
	public static final JqueryStatementsBehavior CONTROLS_ELEMENT = new JqueryStatementsBehavior()
		.add(new BuildableChainableStatement.Builder().label("wrap")
			.args(JsUtils.quotes("<div class=\"controls\"></div>")).build());

	/** The Constant INPUT_ELEMENT. */
	public static final JqueryStatementsBehavior INPUT_ELEMENT = new JqueryStatementsBehavior()
		.add(new BuildableChainableStatement.Builder().label("wrap")
			.args(JsUtils.quotes("<div class=\"input-group\"></div>")).build());

	/** The Constant INPUT_GROUP_BUTTON_ELEMENT. */
	public static final JqueryStatementsBehavior INPUT_GROUP_BUTTON_ELEMENT = new JqueryStatementsBehavior()
		.add(new BuildableChainableStatement.Builder().label("wrap")
			.args(JsUtils.quotes("<span class=\"input-group-btn\"></span>")).build());

	/** The Constant CONTROL_GROUP_ELEMENT. */
	public static final JqueryStatementsBehavior CONTROL_GROUP_ELEMENT = new JqueryStatementsBehavior()
		.add(new BuildableChainableStatement.Builder().label("wrap")
			.args(JsUtils.quotes("<div class=\"control-group\"></div>")).build());

	/**
	 * Instantiates a new BootstrapWrapper.
	 */
	private BootstrapWrapper()
	{
	}
}
