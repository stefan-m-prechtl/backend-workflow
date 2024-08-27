package de.esempe.workflow.domain;

import java.util.Optional;
import java.util.UUID;

import org.bson.types.ObjectId;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.PostLoad;

@Entity
public class MongoDbObject
{
	@Id
	private ObjectId dbid;
	private UUID objd;

	MongoDbObject()
	{
		this.objd = UUID.randomUUID();
	}

	@PostLoad
	void setObjIDFromDbValue()
	{

	}

	public Optional<ObjectId> getDbId()
	{
		return Optional.ofNullable(this.dbid);
	}

	public UUID getObjId()
	{
		return this.objd;
	}

}
