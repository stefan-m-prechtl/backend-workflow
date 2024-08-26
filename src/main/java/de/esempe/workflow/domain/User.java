package de.esempe.workflow.domain;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Field;
import dev.morphia.annotations.Index;
import dev.morphia.annotations.Indexes;

@Entity("users")
@Indexes(@Index(fields = @Field("name")))
public class User extends MongoDbObject
{

	private String name;

	public User()
	{
		super();
	}

	public User(final String name)
	{
		super();
		this.name = name;
	}

}
