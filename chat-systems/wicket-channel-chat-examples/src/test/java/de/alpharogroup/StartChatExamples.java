package de.alpharogroup;

import java.io.File;

import net.sourceforge.jaulp.file.search.PathFinder;

import org.apache.wicket.protocol.http.ContextParamWebApplicationFactory;
import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.util.time.Duration;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.wicketstuff.chat.channel.examples.application.WicketApplication;

import de.alpharogroup.jetty9.runner.Jetty9Runner;
import de.alpharogroup.jetty9.runner.config.FilterHolderConfiguration;
import de.alpharogroup.jetty9.runner.config.Jetty9RunConfiguration;
import de.alpharogroup.jetty9.runner.config.ServletContextHandlerConfiguration;
import de.alpharogroup.jetty9.runner.config.ServletHolderConfiguration;

public class StartChatExamples {
	
    public static void main(String[] args) throws Exception {    	

		System.setProperty("wicket.configuration", "development");
		String projectname = "wicket-channel-chat-examples";
		File projectDirectory = PathFinder.getProjectDirectory();
		File webapp = PathFinder.getRelativePath(projectDirectory, projectname, "src", "main",
			"webapp");

		int sessionTimeout = (int) Duration.minutes(30).seconds();// set timeout to 30min(60sec * 30min=1800sec)...

		String filterPath = "/*";

		ServletContextHandler servletContextHandler = Jetty9Runner
			.getNewServletContextHandler(ServletContextHandlerConfiguration
				.builder()
				.filterHolderConfiguration(
					FilterHolderConfiguration
						.builder()
						.filterClass(WicketFilter.class)
						.filterPath(filterPath)
						.initParameter(WicketFilter.FILTER_MAPPING_PARAM, filterPath)
						.initParameter(ContextParamWebApplicationFactory.APP_CLASS_PARAM,
							WicketApplication.class.getName()).build())
				.servletHolderConfiguration(
					ServletHolderConfiguration.builder().servletClass(DefaultServlet.class)
						.pathSpec(filterPath).build()).contextPath("/").webapp(webapp)
				.maxInactiveInterval(sessionTimeout).filterPath(filterPath).build());

		Jetty9Runner.run(Jetty9RunConfiguration.builder()
			.servletContextHandler(servletContextHandler)
			.httpPort(WicketApplication.DEFAULT_HTTP_PORT)
			.httpsPort(WicketApplication.DEFAULT_HTTPS_PORT)
			.keyStorePassword("wicket")
			.keyStorePathResource("/keystore")
			.build());

    }
}
