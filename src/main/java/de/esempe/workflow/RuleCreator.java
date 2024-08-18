package de.esempe.workflow;

import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.tinylog.Logger;

public class RuleCreator
{

	public static Rule build(final String name, final String script)
	{
		final ScriptEngineManager factory = new ScriptEngineManager();
		final Compilable engine = (Compilable) factory.getEngineByName("groovy");

		try
		{
			final CompiledScript compiledScript = engine.compile(script);
			final Rule result = new Rule(name, compiledScript);
			return result;
		}
		catch (final ScriptException e)
		{
			Logger.error("Compile-Fehler", e);
			return null;
		}

	}
}
