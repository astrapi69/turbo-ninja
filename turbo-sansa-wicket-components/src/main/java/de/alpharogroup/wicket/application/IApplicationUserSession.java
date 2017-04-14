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
package de.alpharogroup.wicket.application;

import org.apache.wicket.Application;
import org.apache.wicket.Session;

import de.alpharogroup.wicket.base.IApplicationModel;

public interface IApplicationUserSession<A extends Application, S extends Session, U, L>
	extends
		IApplicationModel<A, S>
{

	L getServiceLocator();

	U getUser();

}