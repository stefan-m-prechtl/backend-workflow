package de.esempe.workflow.domain;

import com.google.common.base.MoreObjects;

public class Workflow extends MongoDbObject
{
	private final String name;

	public Workflow()
	{
		this.name = "";
	}

	public Workflow(final String name)
	{
		this.name = name;
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
