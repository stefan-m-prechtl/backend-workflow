package de.esempe.workflow.domain;

import dev.morphia.annotations.Entity;

@Entity
public class Rule
{
	private String name;
	private String script;

	public Rule()
	{

	}

	public Rule(final String name, final String script)
	{
		this.name = name;
		this.script = script;
	}

	public String getScript() throws Exception
	{
		return this.script;
	}
}
