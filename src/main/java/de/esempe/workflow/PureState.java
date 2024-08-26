package de.esempe.workflow;

import java.util.Objects;
import java.util.Optional;

import com.google.common.base.MoreObjects;

import jakarta.json.JsonObject;

public class PureState
{
	private final String name;
	private Optional<JsonObject> data;

	public PureState(final String name)
	{
		this.name = name;
		this.data = Optional.empty();
	}

	public String getName()
	{
		return this.name;
	}

	public Optional<JsonObject> getData()
	{
		return this.data;
	}

	public void setData(final JsonObject data)
	{
		this.data = Optional.ofNullable(data);
	}

	@Override
	public int hashCode()
	{
		final var result = Objects.hash(this.name);
		return result;
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (obj instanceof PureState)
		{
			final var other = (PureState) obj;
			return Objects.equals(this.name, other.name);
		}
		return false;
	}

	@Override
	public String toString()
	{
		final var result = MoreObjects.toStringHelper(this) //
				.add("name", this.name) //
				.toString();
		return result;
	}

}
