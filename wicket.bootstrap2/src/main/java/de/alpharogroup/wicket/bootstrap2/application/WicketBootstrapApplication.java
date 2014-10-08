package de.alpharogroup.wicket.bootstrap2.application;

import java.io.IOException;
import java.util.Properties;

import org.apache.wicket.Application;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.caching.FilenameWithVersionResourceCachingStrategy;
import org.apache.wicket.request.resource.caching.NoOpResourceCachingStrategy;
import org.apache.wicket.request.resource.caching.version.CachingResourceVersion;
import org.apache.wicket.serialize.java.DeflatedJavaSerializer;
import org.apache.wicket.util.string.Strings;
import org.jaulp.wicket.base.application.seo.DisableJSessionIDinUrlApplication;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

import com.google.javascript.jscomp.CompilationLevel;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.RenderJavaScriptToFooterHeaderResponseDecorator;
import de.agilecoders.wicket.core.markup.html.references.BootstrapPrettifyCssReference;
import de.agilecoders.wicket.core.markup.html.references.BootstrapPrettifyJavaScriptReference;
import de.agilecoders.wicket.core.markup.html.references.ModernizrJavaScriptReference;
import de.agilecoders.wicket.core.request.resource.caching.version.Adler32ResourceVersion;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.core.settings.ThemeProvider;
import de.agilecoders.wicket.extensions.javascript.GoogleClosureJavaScriptCompressor;
import de.agilecoders.wicket.extensions.javascript.YuiCssCompressor;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.html5player.Html5PlayerCssReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.html5player.Html5PlayerJavaScriptReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.OpenWebIconsCssReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUIJavaScriptReference;
import de.agilecoders.wicket.extensions.request.StaticResourceRewriteMapper;
import de.agilecoders.wicket.less.BootstrapLess;
import de.agilecoders.wicket.themes.markup.html.bootstrap3.Bootstrap3Theme;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchTheme;
import de.agilecoders.wicket.themes.markup.html.google.GoogleTheme;
import de.agilecoders.wicket.themes.markup.html.metro.MetroTheme;
import de.agilecoders.wicket.themes.markup.html.wicket.WicketTheme;
import de.agilecoders.wicket.themes.settings.BootswatchThemeProvider;
import de.alpharogroup.wicket.bootstrap2.resource.reference.fix.FixBootstrapStylesCssResourceReference;

/**
 * Application instance for wicket bootstrap version 0.8.4. Note: Do not try it with a newer version.
 */
public abstract class WicketBootstrapApplication extends DisableJSessionIDinUrlApplication {
	
	/** The properties. */
	private Properties properties;

	/**
	 * Get Application for current thread.
	 * 
	 * @return The current thread's Application
	 */
	public static WicketBootstrapApplication get() {
		return (WicketBootstrapApplication) Application.get();
	}

	/**
	 * Constructor.
	 */
	public WicketBootstrapApplication() {
		properties = loadProperties();
	}

	/**
	 * Inits the.
	 *
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();

		// deactivate ajax debug mode
//		getDebugSettings().setAjaxDebugModeEnabled(false);

		configureBootstrap();
		configureResourceBundles();

		optimizeForWebPerformance();

		new AnnotatedMountScanner().scanPackage(getPackageToScan()).mount(this);
	    if (Strings.isTrue(properties.getProperty("cdn.useCdn"))) {
            final String cdn = properties.getProperty("cdn.baseUrl");
            StaticResourceRewriteMapper.withBaseUrl(cdn).install(this);
        }
	}
	
	/**
	 * Gets the package to scan.
	 *
	 * @return the package to scan
	 */
	public abstract String getPackageToScan();

	/**
	 * optimize wicket for a better web performance.
	 */
	private void optimizeForWebPerformance() {
        if (usesDeploymentConfig()) {
            getResourceSettings().setCachingStrategy(new FilenameWithVersionResourceCachingStrategy(
                    "-v-",
                    new CachingResourceVersion(new Adler32ResourceVersion())
            ));

            getResourceSettings().setJavaScriptCompressor(new GoogleClosureJavaScriptCompressor(CompilationLevel.SIMPLE_OPTIMIZATIONS));
            getResourceSettings().setCssCompressor(new YuiCssCompressor());

            getFrameworkSettings().setSerializer(new DeflatedJavaSerializer(getApplicationKey()));
        } else {
            getResourceSettings().setCachingStrategy(new NoOpResourceCachingStrategy());
        }

        setHeaderResponseDecorator(new RenderJavaScriptToFooterHeaderResponseDecorator());
        getRequestCycleSettings().setRenderStrategy(org.apache.wicket.settings.IRequestCycleSettings.RenderStrategy.ONE_PASS_RENDER);
	}

	/**
	 * configure all resource bundles (css and js).
	 */
	private void configureResourceBundles() {
		getResourceBundles()
				.addJavaScriptBundle(
						WicketBootstrapApplication.class,
						"core.js",
						(JavaScriptResourceReference) getJavaScriptLibrarySettings()
								.getJQueryReference(),
						(JavaScriptResourceReference) getJavaScriptLibrarySettings()
								.getWicketEventReference(),
						(JavaScriptResourceReference) getJavaScriptLibrarySettings()
								.getWicketAjaxReference(),
						(JavaScriptResourceReference) ModernizrJavaScriptReference.INSTANCE);

		getResourceBundles()
				.addJavaScriptBundle(
						WicketBootstrapApplication.class,
						"bootstrap.js",
						(JavaScriptResourceReference) Bootstrap.getSettings()
								.getJsResourceReference(),
						(JavaScriptResourceReference) BootstrapPrettifyJavaScriptReference.INSTANCE);

		getResourceBundles().addJavaScriptBundle(
				WicketBootstrapApplication.class, "bootstrap-extensions.js",
				JQueryUIJavaScriptReference.instance(),
				Html5PlayerJavaScriptReference.instance());

		getResourceBundles().addCssBundle(WicketBootstrapApplication.class,
				"bootstrap-extensions.css", Html5PlayerCssReference.instance(),
				OpenWebIconsCssReference.instance());

		getResourceBundles().addCssBundle(WicketBootstrapApplication.class,
				"application.css",
				(CssResourceReference) BootstrapPrettifyCssReference.INSTANCE,
				FixBootstrapStylesCssResourceReference.INSTANCE);
	}

	/**
	 * configures wicket-bootstrap and installs the settings.
	 */
	protected void configureBootstrap() {
		final ThemeProvider themeProvider = new BootswatchThemeProvider() {
			{
				add(new MetroTheme());
				add(new GoogleTheme());
				add(new WicketTheme());
				add(new Bootstrap3Theme());
				defaultTheme(newDefaultTheme());
			}
		};		

		final BootstrapSettings settings = new BootstrapSettings();
		settings.setJsResourceFilterName("footer-container").setThemeProvider(
				themeProvider);
		Bootstrap.install(this, settings);

		BootstrapLess.install(this);
	}
	
	/**
	 * Factory method for set the default theme of the application. This method
	 * is invoked in the {@code WicketBootstrapApplication.configureBootstrap()}
	 * method and can be overridden from the derived classes so users can
	 * provide their own version of the default theme of the application.
	 *
	 * @return the default theme as string.
	 */
	protected String newDefaultTheme() {
		return BootswatchTheme.CERULEAN.name();
	}

	/**
	 * Gets the properties.
	 *
	 * @return used configuration properties
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * loads all configuration properties from disk.
	 *
	 * @return configuration properties
	 */
	private Properties loadProperties() {
		Properties properties = new Properties();
		try {
			properties.load(getClass()
					.getResourceAsStream("/config.properties"));
		} catch (IOException e) {
			throw new WicketRuntimeException(e);
		}
		return properties;
	}
}
