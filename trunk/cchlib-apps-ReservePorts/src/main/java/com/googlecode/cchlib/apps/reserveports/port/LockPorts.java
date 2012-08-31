package com.googlecode.cchlib.apps.reserveports.port;

//import org.apache.log4j.Logger;

/**
 * 
 */
public abstract class LockPorts
{
	//private static final Logger logger = Logger.getLogger( LockPorts.class );
	private final long sleepMillis;

	/**
	 * 
	 * @param sleepMillis
	 */
	protected LockPorts( final long sleepMillis )
	{
		this.sleepMillis = sleepMillis;
	}

	/**
	 * 
	 * @param sleepMillis
	 */
	public static void sleep(final long sleepMillis)
	{
	    try { Thread.sleep( sleepMillis ); }
	    catch( InterruptedException ignore ) {}
	}

	/**
	 * 
	 */
	protected void sleep() 
	{
	    sleep( sleepMillis );
	}

	/**
	 * 
	 * @param serverPort
	 */
	protected void startService( final int serverPort ) 
	{
	    new Thread( new ServerWorker( serverPort ) ).start();
	}

	public abstract void startServices();

}