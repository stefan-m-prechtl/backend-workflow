package de.esempe.workflow;

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
}
