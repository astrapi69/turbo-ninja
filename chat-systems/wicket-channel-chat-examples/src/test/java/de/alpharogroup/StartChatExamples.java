package de.alpharogroup;

import java.io.File;

import net.sourceforge.jaulp.file.search.PathFinder;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.jaulp.wicket.base.application.jetty.Jetty9RunConfiguration;
import org.jaulp.wicket.base.application.jetty.Jetty9Runner;
import org.jaulp.wicket.base.application.jetty.ServletContextHandlerConfiguration;
import org.wicketstuff.chat.channel.examples.application.WicketApplication;


public class StartChatExamples {
	
    public static void main(String[] args) throws Exception {    	

		System.setProperty("wicket.configuration", "development");
		String projectname = "wicket-channel-chat-examples";
		File projectDirectory = PathFinder.getProjectDirectory();
		File webapp = PathFinder.getRelativePath(projectDirectory, projectname, "src", "main",
			"webapp");

		ServletContextHandler servletContextHandler = Jetty9Runner.getServletContextHandler(ServletContextHandlerConfiguration.builder()
				.applicationClass(WicketApplication.class)
				.contextPath("/")
				.webapp(webapp)
				.maxInactiveInterval(300)
				.filterPath("/*")
				.build());
		Jetty9Runner.run(Jetty9RunConfiguration.builder()
			.servletContextHandler(servletContextHandler)
			.httpPort(WicketApplication.DEFAULT_HTTP_PORT)
			.httpsPort(WicketApplication.DEFAULT_HTTPS_PORT)
			.keyStorePassword("wicket")
			.keyStorePathResource("/keystore")
			.build());

    }
}
