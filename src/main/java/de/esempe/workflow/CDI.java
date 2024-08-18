package de.esempe.workflow;

import org.jboss.weld.proxy.WeldClientProxy;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

/**
 * CDI-Container als Singleton gekapselt.
 *
 * @author Stefan Prechtl (www.esempe.de)
 */
public enum CDI
{
	CONTAINER;

	private SeContainer container;

	CDI()
	{
		this.container = SeContainerInitializer.newInstance().initialize();
	}

	public boolean isRunning()
	{
		return this.container.isRunning();
	}

	public void shutdown()
	{
		if (this.container.isRunning())
		{
			this.container.close();
		}
	}

	public <T extends Object> T getType(final Class<T> clazz)
	{
		final T result = this.container.select(clazz).get();
		return result;
	}

	public <T extends Object> T getTypeWithoutProxy(final Class<T> clazz)
	{
		T result = this.container.select(clazz).get();
		if (result instanceof WeldClientProxy)
		{
			result = (T) ((WeldClientProxy) result).getMetadata().getContextualInstance();
		}
		return result;
	}

}