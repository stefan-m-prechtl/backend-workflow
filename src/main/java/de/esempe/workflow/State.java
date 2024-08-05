package de.esempe.workflow;

import java.util.Objects;
import java.util.UUID;

import com.google.common.base.MoreObjects;

public class State
{
	private UUID objId;
	private String name;

	public State(final String name)
	{
		this.objId = UUID.fromString("objID" + name);
		this.name = name;
	}

	public String getName()
	{
		return this.name;
	}

	@Override
	public int hashCode()
	{
		final var result = Objects.hash(this.objId);
		return result;
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (obj instanceof State)
		{
			final var other = (State) obj;
			return Objects.equals(this.objId, other.objId);
		}
		return false;
	}

	@Override
	public String toString()
	{
		final var result = MoreObjects.toStringHelper(this) //
				.add("objid", this.objId) //
				.add("name", this.name) //
				.toString();
		return result;
	}

}
