package com.googlecode.cchlib.apps.reserveports.port;

/**
 *
 */
public class LockPortsRange extends LockPorts
{
	private int min;
	private int max;

	/**
	 * @param sleepMs 
	 * @param max 
	 * @param min 
	 */
	public LockPortsRange( int min, int max, long sleepMs) 
	{
		super( sleepMs );
		
		this.min     = min;
		this.max     = max;
	}

	@Override
	public void startServices() 
	{
        for( int port = min; port<=max; port++ ) {
            startService( port );
            sleep();
            }
	}

}
