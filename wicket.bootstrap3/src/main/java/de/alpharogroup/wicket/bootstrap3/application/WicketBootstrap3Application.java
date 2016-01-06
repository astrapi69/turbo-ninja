package de.alpharogroup.wicket.bootstrap3.application;

import java.io.IOException;

import org.apache.wicket.Application;
import org.apache.wicket.markup.html.IPackageResourceGuard;
import org.apache.wicket.markup.html.SecurePackageResourceGuard;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.caching.FilenameWithVersionResourceCachingStrategy;
import org.apache.wicket.request.resource.caching.NoOpResourceCachingStrategy;
import org.apache.wicket.request.resource.caching.version.CachingResourceVersion;
import org.apache.wicket.serialize.java.DeflatedJavaSerializer;
import org.apache.wicket.util.string.Strings;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

import com.google.javascript.jscomp.CompilationLevel;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.RenderJavaScriptToFooterHeaderResponseDecorator;
import de.agilecoders.wicket.core.markup.html.bootstrap.block.prettyprint.PrettifyCssResourceReference;
import de.agilecoders.wicket.core.markup.html.bootstrap.block.prettyprint.PrettifyJavaScriptReference;
import de.agilecoders.wicket.core.markup.html.references.ModernizrJavaScriptReference;
import de.agilecoders.wicket.core.request.resource.caching.version.Adler32ResourceVersion;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.settings.SessionThemeProvider;
import de.agilecoders.wicket.core.settings.SingleThemeProvider;
import de.agilecoders.wicket.core.settings.Theme;
import de.agilecoders.wicket.core.settings.ThemeProvider;
import de.agilecoders.wicket.extensions.javascript.GoogleClosureJavaScriptCompressor;
import de.agilecoders.wicket.extensions.javascript.YuiCssCompressor;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.html5player.Html5PlayerCssReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.html5player.Html5PlayerJavaScriptReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.OpenWebIconsCssReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUICoreJavaScriptReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUIDraggableJavaScriptReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUIMouseJavaScriptReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUIResizableJavaScriptReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUIWidgetJavaScriptReference;
import de.agilecoders.wicket.extensions.request.StaticResourceRewriteMapper;
import de.agilecoders.wicket.less.BootstrapLess;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchThemeProvider;
import de.alpharogroup.wicket.PackageResourceReferences;
import de.alpharogroup.wicket.base.application.seo.DisableJSessionIDinUrlApplication;
import de.alpharogroup.wicket.bootstrap3.resource.reference.fix.FixBootstrapStylesCssResourceReference;

/**
 * Demo Application instance for wicket bootstrap version.
 */
public abstract class WicketBootstrap3Application extends DisableJSessionIDinUrlApplication
{

	// http://www.wicket-library.com/wicket-examples/resourceaggregation/wicket/bookmarkable/org.apache.wicket.examples.source.SourcesPage?0&SourcesPage_class=org.apache.wicket.examples.resourcedecoration.HomePage&source=HomePage.java
	public static final String FOOTER_FILTER_NAME = "footer-container";

	/**
	 * Get Application for current thread.
	 *
	 * @return The current thread's Application
	 */
	public static WicketBootstrap3Application get()
	{
		return (WicketBootstrap3Application)Application.get();
	}

	protected void configureBootstrap()
	{
		configureBootstrap(newThemeProvider());
	}

	protected void configureBootstrap(final ThemeProvider themeProvider)
	{
		initBootstrap(themeProvider);
	}

	/**
	 * configure all resource bundles (css and js).
	 */
	protected void configureResourceBundles()
	{
		getResourceBundles().addJavaScriptBundle(WicketBootstrap3Application.class, "core.js",
			(JavaScriptResourceReference)getJavaScriptLibrarySettings().getJQueryReference(),
			(JavaScriptResourceReference)getJavaScriptLibrarySettings().getWicketEventReference(),
			(JavaScriptResourceReference)getJavaScriptLibrarySettings().getWicketAjaxReference(),
			(JavaScriptResourceReference)ModernizrJavaScriptReference.instance());

		getResourceBundles().addJavaScriptBundle(WicketBootstrap3Application.class, "bootstrap.js",
			(JavaScriptResourceReference)Bootstrap.getSettings().getJsResourceReference(),
			(JavaScriptResourceReference)PrettifyJavaScriptReference.INSTANCE);

		getResourceBundles().addJavaScriptBundle(WicketBootstrap3Application.class,
			"bootstrap-extensions.js", JQueryUICoreJavaScriptReference.instance(),
			JQueryUIWidgetJavaScriptReference.instance(),
			JQueryUIMouseJavaScriptReference.instance(),
			JQueryUIDraggableJavaScriptReference.instance(),
			JQueryUIResizableJavaScriptReference.instance(),
			Html5PlayerJavaScriptReference.instance());

		getResourceBundles().addCssBundle(WicketBootstrap3Application.class,
			"bootstrap-extensions.css", Html5PlayerCssReference.instance(),
			OpenWebIconsCssReference.instance());

		getResourceBundles().addCssBundle(WicketBootstrap3Application.class, "application.css",
			(CssResourceReference)PrettifyCssResourceReference.INSTANCE,
			FixBootstrapStylesCssResourceReference.INSTANCE);
	}

	/**
	 * Gets the package to scan. Is called with the {@link AnnotatedMountScanner} to mount pages.
	 *
	 * @return the package to scan
	 */
	public abstract String getPackageToScan();

	private void initBootstrap(final ThemeProvider themeProvider)
	{
		final IBootstrapSettings settings = new BootstrapSettings();
		settings.setJsResourceFilterName(FOOTER_FILTER_NAME)
		.setThemeProvider(themeProvider)
        .setActiveThemeProvider(new SessionThemeProvider());
		Bootstrap.install(this, settings);
		BootstrapLess.install(this);
	}

	/**
	 * Factory method for set the default theme of the application. This method is invoked in the
	 * {@code WicketBootstrapApplication.configureBootstrap()} method and can be overridden from the
	 * derived classes so users can provide their own version of the default theme of the
	 * application.
	 *
	 * @return the default theme as string.
	 */
	protected String newDefaultTheme()
	{
		return "Cerulean";
	}

	protected Theme newTheme()
	{
		return null;
	}

	protected ThemeProvider newThemeProvider()
	{
		final Theme customTheme = newTheme();
		if (customTheme != null)
		{
			return new SingleThemeProvider(customTheme);
		}
		return new BootswatchThemeProvider(newDefaultTheme());
	}


	@Override
	protected void onDeploymentModeSettings()
	{
		super.onDeploymentModeSettings();
	}

	@Override
	protected void onGlobalSettings()
	{
		super.onGlobalSettings();

		getApplicationSettings().setUploadProgressUpdatesEnabled(true);
		// deactivate ajax debug mode
		// getDebugSettings().setAjaxDebugModeEnabled(false);

		configureBootstrap();
//		configureResourceBundles();

		optimizeForWebPerformance();

		new AnnotatedMountScanner().scanPackage(getPackageToScan()).mount(this);
		if (Strings.isTrue(getProperties().getProperty("cdn.useCdn")))
		{
			final String cdn = getProperties().getProperty("cdn.baseUrl");
			StaticResourceRewriteMapper.withBaseUrl(cdn).install(this);
		}

        final IPackageResourceGuard packageResourceGuard = getResourceSettings().getPackageResourceGuard();
        if (packageResourceGuard instanceof SecurePackageResourceGuard) {
            final SecurePackageResourceGuard securePackageResourceGuard = (SecurePackageResourceGuard) packageResourceGuard;
            securePackageResourceGuard.addPattern("+*.woff2");
        }
	}

	/**
	 * optimize wicket for a better web performance.
	 */
	private void optimizeForWebPerformance()
	{
		if (usesDeploymentConfig())
		{
			getResourceSettings().setCachingStrategy(
				new FilenameWithVersionResourceCachingStrategy("-v-", new CachingResourceVersion(
					new Adler32ResourceVersion())));

			getResourceSettings().setJavaScriptCompressor(
				new GoogleClosureJavaScriptCompressor(CompilationLevel.SIMPLE_OPTIMIZATIONS));
			getResourceSettings().setCssCompressor(new YuiCssCompressor());

			getFrameworkSettings().setSerializer(new DeflatedJavaSerializer(getApplicationKey()));
		}
		else
		{
			getResourceSettings().setCachingStrategy(new NoOpResourceCachingStrategy());
		}

		setHeaderResponseDecorator(new RenderJavaScriptToFooterHeaderResponseDecorator());
		getRequestCycleSettings().setRenderStrategy(
			org.apache.wicket.settings.IRequestCycleSettings.RenderStrategy.ONE_PASS_RENDER);
	}

	/**
	 * Initialize all resources that are returned from the factory callback method {@link WicketBootstrap3Application#newPackagesToScan()}.
	 *
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void initializeResources() throws ClassNotFoundException, IOException
	{
		final PackageResourceReferences prr = PackageResourceReferences.getInstance();
		prr.initializeResources(newPackagesToScan());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onBeforeApplicationConfigurations()
	{
		super.onBeforeApplicationConfigurations();
		// initialize all header contributors
		initializeAllHeaderContributors();
	}

	/**
	 * Initialize all header contributors.
	 */
	private void initializeAllHeaderContributors()
	{
		try
		{
			initializeResources();
		}
		catch (final ClassNotFoundException e)
		{
			LOGGER
				.error(
					"ClassNotFoundException in the initializeResources-Method from the WicketApplication.",
					e);
		}
		catch (final IOException e)
		{
			LOGGER.error(
				"IOException in the initializeResources-Method from the WicketApplication.", e);
		}
	}

	/**
	 * Factory callback method that returns the packages to scan as a {@link String} array object.
	 * Note: the first entry is called with the {@link AnnotatedMountScanner} to mount pages.
	 *
	 * @return the {@link String} array object with the packages to scan
	 */
	protected String[] newPackagesToScan()
	{
		final String[] packagesToScan = {""};
		return packagesToScan;
	}

}
