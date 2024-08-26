package de.esempe.workflow;

public class StatedChangedEvent
{
	private final PureState state;

	public StatedChangedEvent(final PureState state)
	{
		this.state = state;
	}

	public PureState getState()
	{
		return this.state;
	}

}
