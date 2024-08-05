package de.esempe.workflow;

import com.google.common.base.MoreObjects;

public class Transition
{
	private State fromState;
	private State toState;

	public Transition(final State fromState, final State toState)
	{
		super();
		this.fromState = fromState;
		this.toState = toState;
	}

	public State getFromState()
	{
		return this.fromState;
	}

	public State getToState()
	{
		return this.toState;
	}

	@Override
	public String toString()
	{
		final var result = MoreObjects.toStringHelper(this) //
				.add("fromState", this.fromState.toString()) //
				.add("toState", this.toState.toString()) //
				.toString();
		return result;
	}

}
