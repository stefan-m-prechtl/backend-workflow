package de.esempe.workflow.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.google.common.base.MoreObjects;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Reference;

@Entity("workflows")
public class Workflow extends MongoDbObject
{
	private String name;
	@Reference
	private Set<Transition> transitions;

	private Map<UUID, List<Transition>> fromStateTransitions;
	private Map<UUID, List<Transition>> toStateTransitions;

	public Workflow()
	{
		super();
		this.name = "";
		this.transitions = new HashSet<>();
		this.fromStateTransitions = new HashMap<>();
		this.toStateTransitions = new HashMap<>();
	}

	public Workflow(final String name)
	{
		this();
		this.name = name;
	}

	public Set<Transition> getTransitions()
	{
		return this.transitions;
	}

	public void addTransition(final Transition transition)
	{

		final var fromState = transition.getFromState();
		final var toState = transition.getToState();

		if (!this.transitions.isEmpty())
		{
			// From-State muss bereit im Workflow enthalten sein!
			if (!this.fromStateTransitions.containsKey(fromState.getObjId()) && !this.toStateTransitions.containsKey(fromState.getObjId()))
			{
				throw new IllegalStateException("addTransition(): From-State noch nicht enthalten");
			}
		}

		List<Transition> fromList = null;
		if (this.fromStateTransitions.containsKey(fromState.getObjId()))
		{
			fromList = this.fromStateTransitions.get(fromState.getObjId());
		}
		else
		{
			fromList = new ArrayList<>();
			this.fromStateTransitions.put(fromState.getObjId(), fromList);
		}
		fromList.add(transition);

		List<Transition> toList = null;
		if (this.toStateTransitions.containsKey(toState.getObjId()))
		{
			toList = this.toStateTransitions.get(toState.getObjId());
		}
		else
		{
			toList = new ArrayList<>();
			this.toStateTransitions.put(toState.getObjId(), toList);
		}
		toList.add(transition);

		this.transitions.add(transition);

	}

	@Override
	public String toString()
	{
		final var result = MoreObjects.toStringHelper(this) //
				.add("dbid", this.getDbId().get().toString())//
				.add("objid", this.getObjId().toString())//
				.add("name", this.name) //
				.toString();
		return result;
	}
}
