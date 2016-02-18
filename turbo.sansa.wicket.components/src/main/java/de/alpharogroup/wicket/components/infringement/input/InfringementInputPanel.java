package de.alpharogroup.wicket.components.infringement.input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;

import de.alpharogroup.user.management.application.models.InfringementModel;
import de.alpharogroup.user.management.enums.RuleViolationReason;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.behaviors.JQueryJsAppenderBehavior;
import de.alpharogroup.wicket.bootstrap2.components.labeled.textarea.BootstrapLabeledTextAreaPanel;
import de.alpharogroup.wicket.components.i18n.dropdownchoice.EnumLocalizedDropdownChoicePanel;

public class InfringementInputPanel extends Panel
{

	private static final long serialVersionUID = 1L;
	private final Component reason;
	private final Component description;

	public InfringementInputPanel(final String id, final IModel<InfringementModel> model)
	{
		super(id, model);
		final IModel<InfringementModel> cpm = new CompoundPropertyModel<InfringementModel>(model);
		add(reason = newReason("reason", cpm));
		add(description = newDescription("description", cpm));
	}

	public Component getDescription()
	{
		return description;
	}

	public Component getReason()
	{
		return reason;
	}

	protected Component newDescription(final String id, final IModel<InfringementModel> model)
	{
		final IModel<String> labelModel = ResourceModelFactory.newResourceModel(
			"rule.violation.reason.description.label", this, "Description");

		final IModel<String> placeholderModel = ResourceModelFactory.newResourceModel(
			"global.enter.your.rule.violation.reason.description.label", this,
			"Enter your description for the rule violation");

		final BootstrapLabeledTextAreaPanel<String, InfringementModel> description = new BootstrapLabeledTextAreaPanel<String, InfringementModel>(
			"description", model, labelModel, placeholderModel);
		description.add(new AttributeAppender("class", "pull-left"));

		description.getTextArea().add(new AttributeAppender("class", "field span5"));
		description.getTextArea().add(new AttributeAppender("rows", "10"));
		description.setRequired(true);
		return description;
	}

	protected Component newReason(final String id, final IModel<InfringementModel> model)
	{
		final RuleViolationReason[] values = RuleViolationReason.values();

		final List<RuleViolationReason> violationReasons = new ArrayList<RuleViolationReason>(
			Arrays.asList(values));
		// Create the select options for search gender with label...
		final IModel<String> genderLabelModel = new StringResourceModel(
			"rule.violation.dropdown.reason.label", this, null);
		final EnumLocalizedDropdownChoicePanel<RuleViolationReason, InfringementModel> reason = new EnumLocalizedDropdownChoicePanel<RuleViolationReason, InfringementModel>(
			"reason", model, genderLabelModel, violationReasons);
		reason.getDropdownChoice().add(
			new JQueryJsAppenderBehavior("wrap", "<div class=\"span3dot5\"></div>"));
		reason.setRequired(true);
		return reason;
	}

}
