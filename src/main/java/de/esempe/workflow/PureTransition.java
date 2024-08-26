package de.esempe.workflow;

import java.util.Optional;

import com.google.common.base.MoreObjects;

import jakarta.json.JsonObject;

public class PureTransition
{
	enum TransistionType
	{
		USER, SYSTEM;
	}

	private final String name;
	private final PureState fromState;
	private final PureState toState;

	private TransistionType type;
	private Optional<JsonObject> data;
	private PureRule rule;

	public PureTransition(final String name, final PureState fromState, final PureState toState)
	{
		super();
		this.name = name;
		this.fromState = fromState;
		this.toState = toState;

		this.type = TransistionType.USER;
		this.data = Optional.empty();

	}

	public PureState getFromState()
	{
		return this.fromState;
	}

	public PureState getToState()
	{
		return this.toState;
	}

	public TransistionType getType()
	{
		return this.type;
	}

	public void setType(final TransistionType type)
	{
		this.type = type;
	}

	public Optional<JsonObject> getData()
	{
		return this.data;
	}

	public void setData(final JsonObject data)
	{
		this.data = Optional.ofNullable(data);
	}

	public PureRule getRule()
	{
		return this.rule;
	}

	public void setRule(final PureRule rule)
	{
		this.rule = rule;
	}

	public String getName()
	{
		return this.name;
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
