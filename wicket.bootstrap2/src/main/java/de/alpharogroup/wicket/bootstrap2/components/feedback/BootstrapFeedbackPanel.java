package de.alpharogroup.wicket.bootstrap2.components.feedback;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class BootstrapFeedbackPanel extends FeedbackPanel {
    private static final long serialVersionUID = 1L;

    public BootstrapFeedbackPanel(final String id) {
        super(id);
    }

    @Override
    protected String getCSSClass(final FeedbackMessage message) {
        if (message.isError() || message.isFatal()) {
            return "alert alert-error";
        } else if (message.isSuccess()) {
            return "alert alert-success";
        } else {
            return "alert";
        }
    }

}