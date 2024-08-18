package de.esempe.workflow;

public class StatedChangedEvent
{
	private final State state;

	public StatedChangedEvent(final State state)
	{
		this.state = state;
	}

	public State getState()
	{
		return this.state;
	}

}
