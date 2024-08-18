package de.esempe.workflow;

import javax.script.Bindings;
import javax.script.CompiledScript;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import org.tinylog.Logger;

import jakarta.json.JsonObject;

public class Rule
{
	private final String name;
	private final CompiledScript script;

	public Rule(final String name, final CompiledScript script)
	{
		this.name = name;
		this.script = script;
	}

	public boolean match(final JsonObject data)
	{
		final Bindings b = new SimpleBindings();
		final String dataString = data.toString();
		b.put("data", dataString);
		try
		{
			return (boolean) this.script.eval(b);
		}
		catch (final ScriptException e)
		{
			Logger.error("Fehler beim Ausführen vom Script", e);
			return false;
		}
	}

	public String getName()
	{
		return this.name;
	}
}
