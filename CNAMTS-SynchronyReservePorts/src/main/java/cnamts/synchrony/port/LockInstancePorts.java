package cnamts.synchrony.port;

import org.apache.log4j.Logger;

/**
 *
 */
public class LockInstancePorts
{
    private final static Logger logger = Logger.getLogger( LockInstancePorts.class );
    private final int instanceNumber;
    private final long sleepMillis;

    /**
     *
     * @param instanceNumber
     * @param sleepMilliSecs
     */
    public LockInstancePorts(
        final int instanceNumber,
        final long sleepMilliSecs
        )
    {
        this.instanceNumber = instanceNumber;
        this.sleepMillis = sleepMilliSecs;
    }

    /**
     *
     */
    public void startServices()
    {
        logger.info( "Reserve instance: " + instanceNumber );

        final int portBase    = 20000 + (100 * instanceNumber);
        final int portBaseMax = 20000 + (100 * (instanceNumber + 1) );

        logger.info( String.format( "Instance range : [%d-%d]", portBase, portBaseMax ) );

        for( int port = portBase; port<portBaseMax; port++ ) {
            startService( port );
            sleep();
            }
    }

    private void sleep()
    {
        try { Thread.sleep( sleepMillis ); }
        catch( InterruptedException ignore ) {}
    }

    private void startService( int serverPort )
    {
        new Thread( new ServerWorker( serverPort ) ).start();
    }
}
