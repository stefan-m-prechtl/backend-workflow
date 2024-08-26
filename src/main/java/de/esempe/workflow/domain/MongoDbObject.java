package de.esempe.workflow.domain;

import org.bson.types.ObjectId;

import dev.morphia.annotations.Id;

public class MongoDbObject
{
	@Id
	private ObjectId id;

	MongoDbObject()
	{

	}
}
