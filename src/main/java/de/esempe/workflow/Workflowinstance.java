package de.esempe.workflow;

import java.util.List;

import jakarta.json.JsonObject;

public class Workflowinstance
{
	private final Workflow workflow;

	public Workflowinstance(final Workflow workflow)
	{
		this.workflow = workflow;
	}

	public void start(final JsonObject data)
	{
		final State start = this.workflow.calculateStartState();
		start.setData(data);
		this.workflow.setCurrentState(start);
	}

	public State getCurrentState()
	{
		final State result = this.workflow.getCurrentState();
		return result;
	}

	public List<Transition> getCurrentTransistions()
	{
		final List<Transition> result = this.workflow.getCurrentTransistions();
		return result;
	}

	public void fireTransition(final Transition t)
	{
		this.workflow.fireTransition(t);
	}

}
