package de.esempe.workflow.domain;

import com.google.common.base.MoreObjects;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Reference;

@Entity("transitions")
public class Transition extends MongoDbObject
{
	enum TransistionType
	{
		USER, SYSTEM;
	}

	private String name;
	@Reference
	private State fromState;
	@Reference
	private State toState;

	private TransistionType type;
	private Rule rule;

	public Transition()
	{
		this.name = "";

	}

	public Transition(final String name, final State fromState, final State toState)
	{
		super();
		this.name = name;
		this.fromState = fromState;
		this.toState = toState;
		this.type = TransistionType.USER;
	}

	public void setRule(final Rule rule)
	{
		this.rule = rule;

	}

	public Rule getRule()
	{
		return this.rule;
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
				.add("name", this.name) //
				.add("fromState", this.fromState.toString()) //
				.add("toState", this.toState.toString()) //
				.add("type", this.type.toString()) //
				.toString();
		return result;
	}

}
