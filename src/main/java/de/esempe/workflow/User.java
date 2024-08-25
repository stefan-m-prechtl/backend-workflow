package de.esempe.workflow;

import org.bson.types.ObjectId;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Field;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Index;
import dev.morphia.annotations.Indexes;

@Entity("users")
@Indexes(@Index(fields = @Field("name")))
public class User
{
	@Id
	private final ObjectId id;
	private String name;

	public User()
	{
		this.id = new ObjectId();
	}

	public User(final String name)
	{
		this();
		this.name = name;
	}

}
