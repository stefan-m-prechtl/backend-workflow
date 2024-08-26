package de.esempe.workflow.domain;

import java.io.StringReader;
import java.util.Objects;
import java.util.Optional;

import com.google.common.base.MoreObjects;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.PostLoad;
import dev.morphia.annotations.PrePersist;
import dev.morphia.annotations.Transient;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Entity("states")
public class State extends MongoDbObject
{
	private String name;
	private String data;;
	@Transient
	private Optional<JsonObject> jsondata;

	public State()
	{
		super();
		this.name = "";
		this.data = "";
		this.jsondata = Optional.empty();
	}

	public State(final String name)
	{
		super();
		this.name = name;
		this.data = "";
		this.jsondata = Optional.empty();
	}

	@PrePersist
	void transformFromJson()
	{
		if (this.jsondata.isPresent())
		{
			this.data = this.jsondata.get().toString();
		}
	}

	@PostLoad
	void transformToJson()
	{
		if (!this.data.isEmpty())
		{
			try (final JsonReader jsonReader = Json.createReader(new StringReader(this.data)))
			{
				this.jsondata = Optional.of(jsonReader.readObject());
			}
		}
	}

	public String getName()
	{
		return this.name;
	}

	public Optional<JsonObject> getData()
	{
		return this.jsondata;
	}

	public void setData(final JsonObject data)
	{
		this.jsondata = Optional.ofNullable(data);
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
		if (obj instanceof State)
		{
			final var other = (State) obj;
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
