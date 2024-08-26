package de.esempe.workflow;

import java.util.List;

import jakarta.json.JsonObject;

public class Workflowinstance
{
	private final PureWorkflow workflow;

	public Workflowinstance(final PureWorkflow workflow)
	{
		this.workflow = workflow;
	}

	public void start(final JsonObject data)
	{
		final PureState start = this.workflow.calculateStartState();
		start.setData(data);
		this.workflow.setCurrentState(start);
	}

	public PureState getCurrentState()
	{
		final PureState result = this.workflow.getCurrentState();
		return result;
	}

	public List<PureTransition> getCurrentTransistions()
	{
		final List<PureTransition> result = this.workflow.getCurrentTransistions();
		return result;
	}

	public void fireTransition(final PureTransition t)
	{
		this.workflow.fireTransition(t);
	}

}
