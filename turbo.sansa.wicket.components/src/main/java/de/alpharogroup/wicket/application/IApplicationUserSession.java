package de.alpharogroup.wicket.application;

import org.apache.wicket.Application;
import org.apache.wicket.Session;
import org.jaulp.wicket.base.IApplicationModel;

public interface IApplicationUserSession<A extends Application, S extends Session, U, L> extends
IApplicationModel<A, S> {

	L getServiceLocator();

	U getUser();

}