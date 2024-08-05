package de.esempe.workflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Workflow
{
	private Set<Transition> transitions;
	private Map<State, List<Transition>> fromStateTransitions;
	private Map<State, List<Transition>> toStateTransitions;

	public Workflow()
	{
		this.transitions = new HashSet<>();
		this.fromStateTransitions = new HashMap<>();
		this.toStateTransitions = new HashMap<>();
	}

	public void addTransition(final Transition transition)
	{
		if (this.transitions.isEmpty())
		{
			this.transitions.add(transition);

			final var fromTransitions = new ArrayList<Transition>();
			fromTransitions.add(transition);
			this.fromStateTransitions.put(transition.getFromState(), fromTransitions);

			final var toTransitions = new ArrayList<Transition>();
			toTransitions.add(transition);
			this.toStateTransitions.put(transition.getToState(), fromTransitions);
		}
		else
		{
			final var fromState = transition.getFromState();
			final var toState = transition.getToState();

			// From-State muss bereit im Workflow enthalten sein!
			if (!this.fromStateTransitions.containsKey(fromState) && !this.toStateTransitions.containsKey(this.fromStateTransitions))


		}

	}

}
