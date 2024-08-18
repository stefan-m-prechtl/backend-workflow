package de.esempe.workflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.tinylog.Logger;

import com.google.common.base.MoreObjects;

import de.esempe.workflow.Transition.TransistionType;

public class Workflow
{
	private final String name;
	private final Set<Transition> transitions;
	private final Map<State, List<Transition>> fromStateTransitions;
	private final Map<State, List<Transition>> toStateTransitions;

	private State currentState;

	public Workflow(final String name)
	{
		this.name = name;
		this.transitions = new HashSet<>();
		this.fromStateTransitions = new HashMap<>();
		this.toStateTransitions = new HashMap<>();

		this.currentState = null;
	}

	public void addTransition(final Transition transition)
	{
		if (!this.transitions.isEmpty())
		{
			final var fromState = transition.getFromState();
			final var toState = transition.getToState();

			// From-State muss bereit im Workflow enthalten sein!
			if (!this.fromStateTransitions.containsKey(fromState) && !this.toStateTransitions.containsKey(fromState))
			{
				throw new IllegalStateException("addTransition(): From-State nicht nicht enthalten");
			}

		}

		this.transitions.add(transition);

		final var fromTransitions = new ArrayList<Transition>();
		fromTransitions.add(transition);
		this.fromStateTransitions.put(transition.getFromState(), fromTransitions);

		final var toTransitions = new ArrayList<Transition>();
		toTransitions.add(transition);
		this.toStateTransitions.put(transition.getToState(), toTransitions);

	}

	public State calculateStartState()
	{
		final Set<State> tmp = new HashSet<>(this.fromStateTransitions.keySet());
		tmp.removeAll(this.toStateTransitions.keySet());

		final List<State> all = new ArrayList<>(tmp);
		return all.get(0);
	}

	public void setCurrentState(final State state)
	{
		this.currentState = state;
		final List<Transition> toTransitions = this.fromStateTransitions.get(this.currentState);

		for (final Transition t : toTransitions)
		{
			if (t.getType() == TransistionType.SYSTEM)
			{
				final Rule r = t.getRule();
				if (r.match(this.currentState.getData().get()))
				{
					final String msg = "Regel: '" + r.getName() + "' trifft zu";
					Logger.info(msg);
				}
				else
				{
					final String msg = "Regel: '" + r.getName() + "' trifft nicht zu";
					Logger.info(msg);
				}
			}
		}
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
