package de.esempe.workflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.tinylog.Logger;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

import de.esempe.workflow.Transition.TransistionType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class Workflow
{
	@Inject
	Event<StatedChangedEvent> statechangedEvent;

	private final String name;
	private final Set<Transition> transitions;
	private final Map<State, List<Transition>> fromStateTransitions;
	private final Map<State, List<Transition>> toStateTransitions;

	private State currentState;

	public Workflow()
	{
		this.name = "Test";
		this.transitions = new HashSet<>();
		this.fromStateTransitions = new HashMap<>();
		this.toStateTransitions = new HashMap<>();

		this.currentState = null;
	}

	public void addTransition(final Transition transition)
	{

		final var fromState = transition.getFromState();
		final var toState = transition.getToState();

		if (!this.transitions.isEmpty())
		{
			// From-State muss bereit im Workflow enthalten sein!
			if (!this.fromStateTransitions.containsKey(fromState) && !this.toStateTransitions.containsKey(fromState))
			{
				throw new IllegalStateException("addTransition(): From-State noch nicht enthalten");
			}
		}

		List<Transition> fromList = null;
		if (this.fromStateTransitions.containsKey(fromState))
		{
			fromList = this.fromStateTransitions.get(fromState);
		}
		else
		{
			fromList = new ArrayList<>();
			this.fromStateTransitions.put(fromState, fromList);
		}
		fromList.add(transition);

		List<Transition> toList = null;
		if (this.toStateTransitions.containsKey(toState))
		{
			toList = this.toStateTransitions.get(toState);
		}
		else
		{
			toList = new ArrayList<>();
			this.toStateTransitions.put(toState, toList);
		}
		toList.add(transition);

		this.transitions.add(transition);

	}

	public State calculateStartState()
	{
		final Set<State> tmp = new HashSet<>(this.fromStateTransitions.keySet());
		tmp.removeAll(this.toStateTransitions.keySet());

		final List<State> all = new ArrayList<>(tmp);
		return all.get(0);
	}

	public boolean isFinalState(final State state)
	{
		final Set<State> tmp = new HashSet<>(this.toStateTransitions.keySet());
		tmp.removeAll(this.fromStateTransitions.keySet());

		final List<State> all = new ArrayList<>(tmp);

		final boolean result = all.contains(state);
		return result;

	}

	public void onEvent(@Observes final StatedChangedEvent event)
	{
		Logger.info("Neuer Zustand " + event.getState().toString());
		this.setCurrentState(event.getState());
	}

	public void setCurrentState(final State state)
	{
		this.currentState = state;

		if (this.isFinalState(this.currentState))
		{
			final String msg = "Worflow beendet mit Zustand: '" + this.currentState.getName();
			Logger.info(msg);
			return;
		}

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
					this.statechangedEvent.fire(new StatedChangedEvent(t.getToState()));
					return;
				}
				else
				{
					final String msg = "Regel: '" + r.getName() + "' trifft nicht zu";
					Logger.info(msg);
				}
			}
		}
	}

	public State getCurrentState()
	{
		return this.currentState;
	}

	@Override
	public String toString()
	{
		final var result = MoreObjects.toStringHelper(this) //
				.add("name", this.name) //
				.toString();
		return result;
	}

	public List<Transition> getCurrentTransistions()
	{
		final List<Transition> result = this.fromStateTransitions.get(this.currentState);
		return result;
	}

	public void fireTransition(final Transition t)
	{
		Preconditions.checkArgument(t.getType() == TransistionType.USER);
		this.statechangedEvent.fire(new StatedChangedEvent(t.getToState()));
	}

}
