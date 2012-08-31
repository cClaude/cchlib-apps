package com.googlecode.cchlib.apps.reserveports.port;

import java.util.List;
import java.util.Set;

/**
 *
 */
public class LockPortsList extends LockPorts
{
	private Iterable<Integer> portsList;
	
	/**
	 * 
	 * @param ports
	 * @param sleepMillis
	 */
    public LockPortsList(
    	final List<Integer> ports,
    	final long          sleepMillis
    	) 
    {
		super( sleepMillis );

		this.portsList = ports;
    }

    /**
     * 
     * @param ports
     * @param sleepMs
     */
    public LockPortsList(
		final Set<Integer> 	ports, 
		final long 			sleepMillis
		) 
    {
		super( sleepMillis );

		this.portsList = ports;
	}

	/**
     *
     */
    @Override
    public void startServices()
    {
        for( int port : this.portsList ) {
            startService( port );
            sleep();
            }
    }

}
