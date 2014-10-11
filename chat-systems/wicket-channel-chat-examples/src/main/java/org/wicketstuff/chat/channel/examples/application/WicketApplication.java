package org.wicketstuff.chat.channel.examples.application;

import java.io.IOException;
import java.io.Serializable;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.time.Duration;
import org.wicketstuff.chat.channel.TimerChannelService;
import org.wicketstuff.chat.channel.api.IChannelService;
import org.wicketstuff.chat.channel.examples.pages.Index;
import org.jaulp.wicket.PackageResourceReferences;

/**
 * Runs the WicketApplication when invoked from command line.
 */
public class WicketApplication extends WebApplication implements Serializable {
	private static final long serialVersionUID = 1L;

	private IChannelService timerChannelService;

	/**
	 * Constructor
	 */
	public WicketApplication() {
	}
	
	@Override
	protected void init()
	{
		super.init();
		this.getMarkupSettings().setStripWicketTags(true); //IMPORTANT!
        try {
        	initResources();
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }

	}

	/**
	 * @see wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		return Index.class;
	}


	public IChannelService getTimerChannelService() {
		// lazy init...
		if(timerChannelService == null) {
			timerChannelService = new TimerChannelService(Duration.seconds(1));
		}
		return timerChannelService;
	}
	


    /**
     * Inits the all relevant resources like css and js files.
     *
     * @throws ClassNotFoundException the class not found exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void initResources() throws ClassNotFoundException, IOException {
    	PackageResourceReferences prr = PackageResourceReferences.getInstance();
    	prr.initializeResources("org.wicketstuff");
    }

}